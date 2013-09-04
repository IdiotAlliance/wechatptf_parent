package com.dt.wechatptf.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	private static final PropertiesUtil pu=new PropertiesUtil("mail.properties");
	String username=pu.getStringProperty("mail.username");
	
	private Message getMessage(){
		String password=pu.getStringProperty("mail.password");
		
		Properties p=new Properties();
		p.put("mail.transport.protocol",pu.getStringProperty("mail.protocol"));
		p.put("mail.smtp.host",pu.getStringProperty("mail.host"));
		p.put("mail.smtp.port",pu.getStringProperty("mail.port"));
		p.put("mail.smtp.auth","true");
		
		MyAuthen auth=new MyAuthen(username,password);
		Session session=Session.getDefaultInstance(p,auth);
		Message message=new MimeMessage(session);
		return message;
	}
	
	public void sendVerify(String mail, String account_md5, String random_md5) throws AddressException, MessagingException{
			Message message=getMessage();
			message.setFrom(new InternetAddress(username));
			message.setRecipient(RecipientType.TO,new InternetAddress(mail));
			message.setSentDate(new Date());
			message.setSubject("邮箱验证");
			String url=pu.getStringProperty("mail.url");
			String m="<a href="+url+"?account_md5="+account_md5+"&random_md5="+random_md5+">" +
					url+"?account_md5="+account_md5+"&random_md5="+random_md5+"</a>";
			message.setContent(m,"text/html;charset=utf8");
			Transport.send(message);
	}

}
