package com.dt.analyzer.entity;

import com.dt.analyzer.core.DTDict;

public class Chunk {

	private static final int MAX_SIZE = 3;
	private final Token[] tokens = new Token[MAX_SIZE];
	private int size       = 0;
	private int length     = 0;
	private float average  = 0,
				  variance = -1,
				  freedom  = 0;
	
	public final void addToken(Token token){
		tokens[size++] = token;
		length += token.getLen();
		average = (float)length / (float)(size);
	}
	
	public final Token getFirst(){
		return tokens[0];
	}
	
	public final int getLength(){
		return length;
	}
	
	public final float getAverage(){
		return average;
	}
	
	/***
	 * 计算该Chunk中token长度的方差，方差的计算使用lazy加载的方式，
	 * 在方法调用时才计算出方差的值。
	 * 
	 * @return
	 */
	public final float getVariance(){
		if(variance < 0){
			for(Token token: tokens)
				variance += Math.pow(token.getLen() - average, 2);
		}
		return variance;
	}
	
	/***
	 * 计算该Chunk中一元token的语素自由度，一个中文字符在文本中出现的频率越高，
	 * 那么它的自由度越大。
	 * @return
	 */
	public final float getFreedom(){
		if(freedom == 0){
			for(Token token: tokens){
				if(token.getLen() == 1)
					freedom += Math.log10(DTDict.getFrequency(token.getToken(), 1));
			}
		}
		return freedom;
	}
	
	public final boolean isFull(){
		return size == MAX_SIZE;
	}
}
