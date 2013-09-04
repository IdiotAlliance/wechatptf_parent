package com.dt.wechatptf.member.test;

import com.dt.wechatptf.services.MailServiceImpl;

public class MailTest {
	
	public void testRegister(){
		MailServiceImpl msi = new MailServiceImpl();
		System.out.println(msi.sendMail("mlr", "909103970@qq.com"));
	}
	
	public static void main(String[] args){
		MailTest mt = new MailTest();
		mt.testRegister();
	}

}
