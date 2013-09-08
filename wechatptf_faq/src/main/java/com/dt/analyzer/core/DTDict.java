package com.dt.analyzer.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dt.analyzer.entity.Token;

/***
 * 该类中加载停用词库和中文分词词库。
 * 貌似在静态上下文中不能直接用当前类的加载器来获取classpath中的资源？
 * 
 * @author lvxiang
 *
 */
public final class DTDict {
	
	private static final Map<String, List<Token>> dict = new HashMap<String, List<Token>>();
	private static final Map<String, Boolean> stopwords = new HashMap<String, Boolean>();
	private static final Map<String, Integer> frequencies = new HashMap<String, Integer>();
	
	static{
		load();
	}
	
	private static final void load(){
		loadStopWords();
		loadFrequencies();
		loadDict();
	}
	
	/***
	 * 加载中文常用词词典
	 */
	private static final void loadDict(){
		BufferedReader reader = null;
		String line = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("cndict.txt")));
			
			while((line = reader.readLine()) != null){
				String fchar = line.charAt(0) + "";
				if(dict.get(fchar) == null)
					dict.put(fchar, new LinkedList<Token>());
				Token token = new Token(line, stopwords.containsKey(line));
				dict.get(fchar).add(token);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * 加载中文停用词词典
	 */
	private static final void loadStopWords(){
		BufferedReader reader = null;
		String line = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("stopwords.txt")));
			
			while((line = reader.readLine()) != null){
				stopwords.put(line, true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * 加载中文常用字字频表
	 */
	private static final void loadFrequencies(){
		BufferedReader reader = null;
		String line = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("cnfreq.txt")));
			
			while((line = reader.readLine()) != null){
				line = line.replaceAll("\\s+", " ");
				String[] fields = line.split(" ");
				frequencies.put(fields[0], Integer.parseInt(fields[1]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static final List<Token> wordsStartWith(String fchar){
		return dict.get(fchar);
	}
	
	public static final boolean isStopword(String fchar){
		return stopwords.containsKey(fchar);
	}
	
	/***
	 * 获取一个字的频率，若该在不再字频表中，则返回默认的值
	 * @param fchar 查找的字
	 * @param defaultValue 默认值
	 * @return
	 */
	public static final int getFrequency(String fchar, int defaultValue){
		if(frequencies.containsKey(fchar))
			return frequencies.get(fchar);
		return defaultValue;
	}
	
	public static final void loadDictFromClassPath(String filename){
		
	}
	
	public static final void loadStopwordsFromClassPath(String filename){
		
	}
}
