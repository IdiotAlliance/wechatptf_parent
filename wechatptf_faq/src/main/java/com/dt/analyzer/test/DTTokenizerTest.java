package com.dt.analyzer.test;

import org.junit.Test;

import com.dt.analyzer.core.DTTokenizer;
import com.dt.analyzer.entity.Strategy;

public class DTTokenizerTest {

	@Test
	public void testSimpleTokenize(){
		DTTokenizer.tokenize("我", DTTokenizer.Strategy.SIMPLE);
	}
	
}
