package com.dt.analyzer.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/***
 * 在该类中加载同义词词库，同义词词库存储在文件synonym.txt中。词库以hashmap的形式
 * 存储，一个词可能对应多个同义词。
 * @author lvxiang
 *
 */
public class DTSynonyms {

	private static final Map<String, Set<String>> synonymDictionary = 
			new HashMap<String, Set<String>>();
	
	/***
	 * 在静态初始化中加载同义词典文件
	 */
	static{
		loadSynonyms();
	}
	
	/***
	 * 加载中文同义词词库，同义词用→分隔开，
	 */
	private static void loadSynonyms(){
		BufferedReader reader = null;
		String line = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("cnsynonym.txt")));
			
			while((line = reader.readLine()) != null){
				String[] words = line.split("→");
				if(words.length == 2){
					if(synonymDictionary.get(words[0]) == null)
						synonymDictionary.put(words[0], new HashSet<String>());
					if(synonymDictionary.get(words[1]) == null)
						synonymDictionary.put(words[1], new HashSet<String>());
					synonymDictionary.get(words[0]).add(words[1]);
					synonymDictionary.get(words[1]).add(words[0]);
				}
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
	 * 获得一个字或词的同义词
	 * @param word
	 * @return
	 */
	public Set<String> getSynonyms(String word){
		if(word != null){
			return synonymDictionary.get(word);
		}
		return null;
	}
	
}
