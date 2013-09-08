package com.dt.analyzer.dao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dt.analyzer.dao.DocumentsDAO;

public class DocumentsDAOTest {
	
	DocumentsDAO dd = null;
	
	@Before
	public void init(){
		dd = new DocumentsDAO();
	}
	
	@Test
	public void testInsertDocument(){
		dd.insertDOC("南大附近有啥好吃的？", "酱香饼", "1");
	}
	
	@Test
	public void testgetDocsByCid(){
		dd.insertDOC("南大附近有啥好吃的？", "酱香饼", "1");
		dd.insertDOC("南大附近有啥好吃的？", "酱香饼", "1");
		dd.insertDOC("南大附近有啥好吃的？", "酱香饼", "1");
		assertEquals(dd.getDocsByCid("1").size(), 3);
	}
	
	@After
	public void clear(){
		dd.clearCollection();
		dd.release();
	}

}
