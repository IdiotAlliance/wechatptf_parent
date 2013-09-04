package com.dt.wechatptf.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthen extends Authenticator {
	
	private String username;
	private String password;
	public MyAuthen(String username,String password){
		this.username=username;
		this.password=password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(username,password);
	}

}
