package com.dt.wechatptf.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dt.wechatptf.dao.MailDAO;
import com.dt.wechatptf.util.MD5;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.SendMail;
import com.dt.wechatptf.util.SixRandom;
import com.google.gson.Gson;

@Path("mail")
public class MailServiceImpl implements MailService {
	
	final MailDAO md = new MailDAO();
	Gson gson = new Gson();

	@POST
	@Path("/register")
	public String register(@FormParam("account") String account, 
			@FormParam("password") String password, @FormParam("mail") String mail) {
		return gson.toJson(md.addUserTemp(account, password, mail));
	}

	@POST
	@Path("/sendMail")
	public String sendMail(String account, String mail) {
		ReturnMessage rm = new ReturnMessage();
		String account_md5 = null;
		int random = SixRandom.getSixRan();
		String random_md5 = null;
		try {
			account_md5 = MD5.getMD5Str(account);
			random_md5 = MD5.getMD5Str(random+"");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendMail sm = new SendMail();
		try {
			sm.sendVerify(mail, account_md5, random_md5);
			rm = md.addVerify(account, account_md5, random_md5);
			if(rm.getFail() == 0){
				rm.setMessage("邮件发送成功，请前往验证！");
			}
		} catch (AddressException e) {
			rm.setFail(1);
			rm.setMessage("邮件发送失败，非法的邮件地址！");
			e.printStackTrace();
		} catch (MessagingException e) {
			rm.setFail(1);
			rm.setMessage("邮件发送失败，MessagingException！");
			e.printStackTrace();
		}
		return gson.toJson(rm);
	}

	@POST
	@Path("/verify")
	public String verify(String account_md5, String random_md5) {
		ReturnMessage rm = new ReturnMessage();
		String account = md.queryAccount(account_md5, random_md5);
		if(account != null){
			rm = md.deleteVerify(account);
			if(rm.getFail() == 0){
				rm = md.addUser(account);
				if(rm.getFail() == 0){
					rm = md.deleteUserTemp(account);
					if(rm.getFail() == 0){
						rm.setMessage("邮箱验证成功！");
					}
				}
			}
		}
		return gson.toJson(rm);
	}

}
