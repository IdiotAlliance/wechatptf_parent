package com.dt.analyzer.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.dt.analyzer.core.DTDict;
import com.dt.analyzer.entity.Token;

public class DTDictTest {

	@Test
	public void testLoadDict(){
		List<Token> list = DTDict.wordsStartWith("中");
		for(Token s: list){
			System.out.println(s.getToken());
		}
	}
	
	@Test
	public void testLoadFrequencies(){
		int freq = DTDict.getFrequency("你", 1);
		assertEquals(freq, 33449);
	}

}
