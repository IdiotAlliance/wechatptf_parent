package com.dt.wechatptf.services;

public interface MailService {
	
	public String register(String account, String password, String mail);
	public String sendMail(String account, String mail);
	public String verify(String account_md5, String random_md5);

}
