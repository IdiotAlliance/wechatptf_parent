package com.dt.analyzer.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dt.analyzer.dao.DocumentsDAO;
import com.dt.analyzer.dao.InversedIndexDAO;
import com.dt.analyzer.entity.Document;
import com.dt.analyzer.entity.Token;
import com.dt.analyzer.entity.TokenProfile;

/***
 * 文档分析器，分析txt doc excel等文件；或者url指向的资源；或者内存中的资源。
 * 资源必须以{问题,答案}二元组的方式呈现，分析器首先对问题用分词器进行分词；
 * 然后构建逆向索引，构建时需要考虑同意词，根据分词结果，将文档编号添加到每个
 * 词及其同义词的逆向索引中去；最后，原文档本身也要放到对应的文档数据库中去。
 * 
 * @author lvxiang
 *
 */
public class DTAnalyzer {
	
	private static final int MAX_DOCS = 100;

	/****
	 * 分析一个faq文件，并将分析结果导入数据库
	 * @param filePath 文件的绝对路径
	 * @param fs 分析文件的策略
	 */
	public static void analyzeFile(String filePath, ResourceAnalyzerStrategy fs){
		
	}
	
	/***
	 * 分析来自URL的资源
	 * @param url
	 * @param fs
	 */
	public static void analyzeURL(String url,  ResourceAnalyzerStrategy fs){
		
	}
	
	/***
	 * 分析内存中的字符串资源
	 * @param str
	 * @param fs
	 */
	public static void analyzeString(String str, ResourceAnalyzerStrategy fs){
		
	}
	
	/***
	 * 查找一个问题的答案，返回候选的答案文档列表，按照匹配度由高到低进行排序。
	 * 首先根据问题和商家的id尝试寻找100%匹配的项，如果找不到就对问题进行分词，
	 * 到倒排索引中查找答案，并对答案用类似page rank的方式进行排序。返回的文
	 * 档采用默认的最大个数100.
	 * 
	 * @param q 问题
	 * @param cid 商家id
	 * @see #ask(String, String, int)
	 * @return
	 */
	public static List<Document> ask(String q, String cid){
		return ask(q, cid, MAX_DOCS);
	}
	
	/***
	 * 查找一个问题的答案，返回候选的答案文档列表，按照匹配度由高到低进行排序。
	 * 首先根据问题和商家的id尝试寻找100%匹配的项，如果找不到就对问题进行分词，
	 * 到倒排索引中查找答案，并对答案用类似page rank的方式进行排序。
	 * @param q
	 * @param cid
	 * @param max 返回列表中结果数目的上限
	 * @return
	 */
	public static List<Document> ask(String q, String cid, int max){
		if(q == null || cid == null)
			throw new NullPointerException();
		
		List<Document> candidates = new ArrayList<Document>();
		
		// 尝试寻找100%的匹配项
		DocumentsDAO dd = new DocumentsDAO();
		Document match = dd.getDocByQnCid(q, cid);
		if(match != null){
			candidates.add(match);
		}
		else{
			List<Token> tokens = DTTokenizer.tokenize(q, DTTokenizer.Strategy.COMPLEX);
			InversedIndexDAO iid = new InversedIndexDAO();
			List<TokenProfile> profiles = new ArrayList<TokenProfile>();
			for(Token token: tokens){
				if(!token.isStopword()){
					TokenProfile profile = iid.getDocs(token.getToken());
					profiles.add(profile);
				}
			}
			candidates = findCandidates(profiles, cid);
		}
		return candidates;
	}
	
	/***
	 * 找到对应关键字的候选文档列表，按照匹配度由高到低排序。
	 * @param profiles
	 * @return
	 */
	private static final List<Document> findCandidates(List<TokenProfile> profiles, String cid){
		List<Document> candidates = new ArrayList<Document>();
		Map<String, Document> temp = new HashMap<String, Document>();
		DocumentsDAO dd = new DocumentsDAO();
		for(TokenProfile profile: profiles){
			for(String did: profile.getDocs()){
				if(!temp.containsKey(did)){
					Document doc = dd.getDocById(did);
					if(doc != null){
						doc.addRank(profile.getTlen()/Math.log(profile.getFreq()));
						temp.put(did, doc);
					}
				}
				else{
					temp.get(did).addRank(profile.getTlen()/Math.log(profile.getFreq()));
				}
			}
		}
		for(Entry<String, Document> entry: temp.entrySet()){
			candidates.add(entry.getValue());
		}
		Collections.sort(candidates);
		return candidates;
	}
	
	public static enum ResourceAnalyzerStrategy{
		TEXT_STRATEGY, // 简单文本文件
		DOC_STRATEGY,  // word2003格式文件
		DOCX_STRATEGY, // word2007格式文件
		EXCEL_STRATEGY; // excel文件
	}
	
	private static interface FileAnalyzer{
		
	}
}
