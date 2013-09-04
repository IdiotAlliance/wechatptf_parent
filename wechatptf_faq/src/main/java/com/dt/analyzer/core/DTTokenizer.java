package com.dt.analyzer.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dt.analyzer.comparator.AvgComparator;
import com.dt.analyzer.comparator.LengthComparator;
import com.dt.analyzer.comparator.MorphemicFreedomComparator;
import com.dt.analyzer.comparator.VarianceComparator;
import com.dt.analyzer.entity.Chunk;
import com.dt.analyzer.entity.Strategy;
import com.dt.analyzer.entity.Token;

/***
 * 该类对输入的文本进行中文分词
 * @author lvxiang
 *
 */
public final class DTTokenizer {
	
	private static final Map<String, Token> atomTokens = new HashMap<String, Token>();
	
	public static final List<Token> tokenize(String input, Strategy strategy){
		switch(strategy){
		case SIMPLE:
			return simpleTokenize(input);
		case COMPLEX:
			return complexTokenize(input);
		default:
			return null;
		}
	}
	
	/***
	 * 用最大匹配算法对输入的中文语句进行分词。
	 * @param input
	 * @return
	 */
	private static final List<Token> simpleTokenize(String input){
		if(input == null)
			throw new NullPointerException();
		
		final List<Token> result = new ArrayList<Token>();
		final int len  = input.length();
		
	    int cursor = 0,
	    	ilen   = input.length();
	    
		while(cursor < len){
			String cchar = input.charAt(cursor) + "";
			List<Token> tokens = DTDict.wordsStartWith(cchar);
		
			String tstr = null;
			boolean find = false;
			// 某些字可能无法组成词
			if(tokens != null){
				int clen = Math.min(Token.MAX_LENGTH, ilen);
				tstr = input.substring(cursor, 
									   cursor + clen);
				char[] tchars = tstr.toCharArray();
				
				int i = tokens.size() - 1;
				while(tokens.get(i).getLen() > ilen)
					i --;
				
				for(; i >= 0; i--){
					Token ctoken = tokens.get(i);
					if(ctoken.getLen() < clen){
						clen = ctoken.getLen();
						tstr = new String(tchars, 0, clen);
					}
					if(ctoken.equalTo(tstr)){
						result.add(ctoken);
						find = true;
						cursor += clen;
						ilen    = len - cursor; 
						break;
					}
				}
			}
			
			// 如果没有找到任何匹配，返回当前的单字作为token
			if(!find){
				// 这里还能更快
				if(atomTokens.get(cchar) == null)
					atomTokens.put(cchar, new Token(cchar, DTDict.isStopword(cchar)));
				result.add(atomTokens.get(cchar));
				cursor ++;
				ilen = len - cursor;
			}
		}
		return result;
	}
	
	/***
	 * 使用复杂算法进行中文分词。该算法在遇到多重可能性时使用若干条规则选择出最有可能的分词方式。
	 * 
	 * @param input
	 * @return
	 */
	private static final List<Token> complexTokenize(String input){
		if(input == null)
			throw new NullPointerException();
		
		final List<Token> result = new ArrayList<Token>();
		final int total = input.length();
		int cursor = 0;
		int ilen   = total;
		int clen   = 0;
		while(cursor < total){
			clen = Math.min(Token.MAX_LENGTH, ilen);
			String tstr = input.substring(cursor, cursor + clen);
			List<Chunk> chunks = new ArrayList<Chunk>();
			List<Token> tokens = tokensInStr(tstr);
			if(tokens.size() > 1){
				for(Token token: tokens){
					int ccursor = cursor + token.getLen();
					if(ccursor < total){
						int tilen = Math.min(Token.MAX_LENGTH, total - ccursor);
						String ttstr = input.substring(ccursor, ccursor + tilen);
						List<Token> ttokens = tokensInStr(ttstr);
						for(Token ttoken: ttokens){
							int cccursor = ccursor + ttoken.getLen();
							if(cccursor < total){
								int ttilen = Math.min(Token.MAX_LENGTH, total - cccursor);
								String tttstr = input.substring(cccursor, cccursor + ttilen);
								List<Token> tttokens = tokensInStr(tttstr);
								for(Token tttoken: tttokens){
									Chunk chunk = new Chunk();
									chunk.addToken(token);
									chunk.addToken(ttoken);
									chunk.addToken(tttoken);
									chunks.add(chunk);
								}
							}
							else{
								Chunk chunk = new Chunk();
								chunk.addToken(token);
								chunk.addToken(ttoken);
								chunks.add(chunk);
							}
						}
					}else{
						Chunk chunk = new Chunk();
						chunk.addToken(token);
						chunks.add(chunk);
					}
				}
				Token chosen = choose(chunks);
				cursor += chosen.getLen();
				result.add(chosen);
				ilen = total - cursor;
			}
			else{
				result.add(tokens.get(0));
				cursor += tokens.get(0).getLen();
				ilen = total - cursor;
			}
		}
		
		return result;
	}
	
	protected static final List<Token> tokensInStr(String str){
		if(str == null)
			throw new NullPointerException();
		
		List<Token> result = new ArrayList<Token>();
		
		String local = str;
		char[] tchars = str.toCharArray();
		String fchar = str.charAt(0) + "";
		List<Token> tokens = DTDict.wordsStartWith(fchar);
		int clen = str.length();
		if(tokens != null){
			for(int i = tokens.size() - 1; i >= 0; i--){
				Token token = tokens.get(i);
				if(token.getLen() < clen){
					clen = token.getLen();
					local = new String(tchars, 0, clen);
				}
				if(token.equalTo(local))
					result.add(token);
			}
			if(result.size() > 0)
				return result;
		}
		
		if(!atomTokens.containsKey(fchar))
			atomTokens.put(fchar, new Token(fchar, DTDict.isStopword(fchar)));
		result.add(atomTokens.get(fchar));
		return result;
		
	}
	
	protected static final Token choose(final List<Chunk> chunks){
		if(chunks.size() < 2)
			throw new IllegalArgumentException();
		
		// 使用第一条规则，选择总长度最长的chunk
		List<Chunk> newChunks = new ArrayList<Chunk>();
		Collections.sort(chunks, new LengthComparator<Chunk>());
		int i = chunks.size() - 1;
		newChunks.add(chunks.get(i));
		while(i > 0 && 
			  chunks.get(i-1).getLength() == chunks.get(i).getLength()){
			i --;
			newChunks.add(chunks.get(i));
		}
		if(newChunks.size() == 1){
			return newChunks.get(0).getFirst();
		}
		
		// 使用第二条规则，选择平均长度最长的chunk
		List<Chunk> newChunks1 = new ArrayList<Chunk>();
		if(partial(newChunks)){
			Collections.sort(newChunks, new AvgComparator<Chunk>());
			i = newChunks.size() - 1;
			newChunks1.add(newChunks.get(i));
			while(i > 0 && 
				  newChunks.get(i-1).getAverage() == newChunks.get(i).getAverage()){
				i --;
				newChunks1.add(chunks.get(i));
			}
			if(newChunks1.size() == 1)
				return newChunks1.get(0).getFirst();
		}else{
			newChunks1 = newChunks;
		}
		
		// 使用第三条规则，选择方差最小的chunk
		List<Chunk> newChunks2 = new ArrayList<Chunk>();
		Collections.sort(newChunks1, new VarianceComparator<Chunk>());
		i = 0;
		newChunks2.add(newChunks1.get(i));
		while(i < newChunks1.size() - 1 && 
			  newChunks1.get(i).getVariance() == newChunks1.get(i + 1).getVariance()){
			i ++;
			newChunks2.add(newChunks1.get(i));
		}
		if(newChunks2.size() == 1)
			return newChunks2.get(0).getFirst();
		
		// 使用第四条规则，选择自由度最大的chunk
		Collections.sort(newChunks2, new MorphemicFreedomComparator<Chunk>());
		return newChunks2.get(newChunks2.size() - 1).getFirst();
	}
	
	protected static final boolean partial(final List<Chunk> chunks){
		for(Chunk chunk: chunks)
			if(!chunk.isFull())
				return true;
		return false;
	}
	
	public static void main(String[] args){
		tokensInStr("你好");
		
		List<Token> tokens = complexTokenize("蓝莲花");
		for(Token token: tokens){
			System.out.print(token.getToken() + "|");
		}
	}
	
}
