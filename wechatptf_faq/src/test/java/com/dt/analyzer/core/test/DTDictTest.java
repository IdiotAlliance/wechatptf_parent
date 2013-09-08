package com.dt.analyzer.core.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.dt.analyzer.core.DTDict;
import com.dt.analyzer.entity.Token;

public class DTDictTest {

	@Test
	public void testLoadDict(){
		List<Token> list = DTDict.wordsStartWith("中");
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void testLoadFrequencies(){
		int freq = DTDict.getFrequency("你", 1);
		assertEquals(freq, 33449);
	}

	@Test
	public void testIsStopword(){
		assertTrue(DTDict.isStopword("?"));
		assertTrue(DTDict.isStopword("。"));
		assertTrue(DTDict.isStopword("不是"));
	}
}
