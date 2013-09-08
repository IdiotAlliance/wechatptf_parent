package com.dt.analyzer.core.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.dt.analyzer.core.DTTokenizer;
import com.dt.analyzer.entity.Token;
public class DTTokenizerTest {

	@Test
	public void testSimpleTokenize(){
		List<Token> tokens = DTTokenizer.tokenize("我", DTTokenizer.Strategy.SIMPLE);
		assertEquals(tokens.size(), 1);
	}
	
	@Test
	public void testComplexTokenize(){
		List<Token> tokens = DTTokenizer.tokenize("研究生活动中心", DTTokenizer.Strategy.COMPLEX);
		assertNotNull(tokens);
		// 应该分词为 研究生 | 活动 | 中心
		assertEquals(tokens.size(), 3);
	}
	
}
