package com.dt.wechatptf.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dt.wechatptf.util.TokenUtil;

public class TokenUtilTest {

	@Test
	public void testGenUUID(){
		String uuid = TokenUtil.genUUID();
		System.out.println(uuid);
		assertEquals(uuid.length(), TokenUtil.lenOfUUID());
	}
	
}
