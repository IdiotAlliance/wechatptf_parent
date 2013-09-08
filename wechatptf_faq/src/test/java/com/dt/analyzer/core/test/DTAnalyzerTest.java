package com.dt.analyzer.core.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dt.analyzer.core.DTAnalyzer;
import com.dt.analyzer.dao.DocumentsDAO;
import com.dt.analyzer.dao.InversedIndexDAO;
import com.dt.analyzer.entity.Document;

public class DTAnalyzerTest {

	DocumentsDAO dd = null;
	InversedIndexDAO iid = null;
	
	@Before
	public void init(){
		dd = new DocumentsDAO();
		iid = new InversedIndexDAO();
		
		dd.insertDOC("南大附近有啥好吃的？", "酱香饼", "123");
		dd.insertDOC("南大附近有啥好玩的？", "", "123");
		dd.insertDOC("南大附近那里可以看书？", "先锋书店", "123");
		dd.insertDOC("南大小粉桥门几点关门？", "下午5点", "123");
		
	}
	
	@Test
	public void testAsk(){
		List<Document> result = DTAnalyzer.ask("南大好吃的", "123");
		assertNotNull(result);
		assertEquals(result.get(0).getAnswer(), "酱香饼");
	}
	
	@After
	public void clear(){
		iid.clearIndex();
		dd.clearCollection();
		dd.release();
		iid.release();
	}
	
}
