package com.dt.analyzer.test;

import org.junit.Test;

import com.dt.analyzer.entity.Token;

public class TokenTest {

	@Test
	public void testHash(){
		Token token = new Token("我是好人", false);
		System.out.println(token.getHash());
	}
	
}
