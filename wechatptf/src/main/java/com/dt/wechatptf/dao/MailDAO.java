package com.dt.wechatptf.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dt.wechatptf.util.MD5;
import com.dt.wechatptf.util.ReturnMessage;

public class MailDAO {
	
	Connection conn;
	
	public MailDAO(){
		conn = DBConnection.getMailDBConnection();
	}
	
	/**
	 * 添加未验证用户
	 * @param account
	 * @param password
	 * @param mail
	 * @return
	 */
	public ReturnMessage addUserTemp(String account, String password, String mail){
		ReturnMessage message = new ReturnMessage();
		int account_exist = this.queryAccountExist(account);
		if(account_exist == 1 || account_exist == 2){
			message.setFail(1);
			message.setMessage("该用户名已存在！");
		}
		else{
			int mail_exist = this.queryMailExist(mail);
			if(mail_exist == 1){
				message.setFail(1);
				message.setMessage("该邮箱已被成功注册！");
			}
			else if(mail_exist == 2){
				message.setFail(1);
				message.setMessage("该邮箱已被注册，但未验证！");
			}
			else{
				try {
					PreparedStatement ps = conn.prepareStatement("insert into usertemp (account,password,mail) values(?,?,?)");
					ps.setString(1, account);
					ps.setString(2, password);
					ps.setString(3, mail);
					ps.executeUpdate();
					
					message.setFail(0);
					message.setMessage("添加未验证用户成功！");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message.setFail(1);
					message.setMessage("添加未验证用户失败，未知错误！");
				}
			}
		}
		return message;
	}
	
	/**
	 * 添加待验证用户
	 * @param account
	 * @param account_md5
	 * @param random_md5
	 * @return
	 */
	public ReturnMessage addVerify(String account, String account_md5, String random_md5){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into verify (account,account_md5,random_md5) values(?,?,?)");
			ps.setString(1, account);
			ps.setString(2, account_md5);
			ps.setString(3, random_md5);
			ps.executeUpdate();
			
			message.setFail(0);
			message.setMessage("添加待验证用户成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("添加待验证用户失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 添加已验证用户
	 * @param account
	 * @param password
	 * @param mail
	 * @return
	 */
	public ReturnMessage addUser(String account){
		ReturnMessage message = new ReturnMessage();
		String password = null;
		String mail = null;
		try {
			PreparedStatement ps1=conn.prepareStatement("select password,mail from usertemp where account=?");
			ps1.setString(1, account);
			ResultSet rs1=ps1.executeQuery();
			if (rs1.next()){
				password = rs1.getString(1);
				mail = rs1.getString(2);
				
				PreparedStatement ps = conn.prepareStatement("insert into user (account,password,mail) values(?,?,?)");
				ps.setString(1, account);
				ps.setString(2, password);
				ps.setString(3, mail);
				ps.executeUpdate();
				
				message.setFail(0);
				message.setMessage("添加已验证用户成功！");
			}
			else{
				message.setFail(1);
				message.setMessage("添加已验证用户失败，未查询到此未验证用户！");
			}
		} catch (SQLException e) {
			message.setFail(1);
			message.setMessage("添加已验证用户失败，未知错误！");
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * 删除未验证用户
	 * @param accout
	 * @return
	 */
	public ReturnMessage deleteUserTemp(String account){
		ReturnMessage message = new ReturnMessage();
		try {	
			PreparedStatement ps=conn.prepareStatement("delete from usertemp where account=?");
			ps.setString(1,account);
			ps.executeUpdate();
			
			message.setFail(0);
			message.setMessage("删除未验证用户成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除未验证用户失败，未知错误！");

		}
		return message;
	}
	
	/**
	 * 删除待验证用户
	 * @param accout
	 * @return
	 */
	public ReturnMessage deleteVerify(String accout){
		ReturnMessage message = new ReturnMessage();
		try {	
			PreparedStatement ps=conn.prepareStatement("delete from verify where account=?");
			ps.setString(1,accout);
			ps.executeUpdate();
			
			message.setFail(0);
			message.setMessage("删除待验证用户成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除待验证用户失败，未知错误！");

		}
		return message;
	}
	
	/**
	 * 删除已验证用户
	 * @param accout
	 * @return
	 */
	public ReturnMessage deleteUser(String accout){
		ReturnMessage message = new ReturnMessage();
		try {	
			PreparedStatement ps=conn.prepareStatement("delete from user where account=?");
			ps.setString(1,accout);
			ps.executeUpdate();
			
			message.setFail(0);
			message.setMessage("删除已验证用户成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除已验证用户失败，未知错误！");

		}
		return message;
	}
	
	/**
	 * 在根据account_md5和random_md5查询account
	 * @param account_md5
	 * @param random_md5
	 * @return
	 */
	public String queryAccount(String account_md5, String random_md5){
		String account = null;
		try {
			PreparedStatement ps=conn.prepareStatement("select account from verify where account_md5=? and random_md5=?");
			ps.setString(1, account_md5);
			ps.setString(2, random_md5);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				account = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
	
	/**
	 * 检查某账号是否已存在
	 * @param account
	 * @return 0不存在，1存在已验证用户，2存在未验证用户
	 */
	public int queryAccountExist(String account){
		int exist = 0;
		try {
			PreparedStatement ps1=conn.prepareStatement("select id from user where account=?");
			ps1.setString(1, account);
			ResultSet rs1=ps1.executeQuery();
			if (rs1.next()){
				exist = 1;
			}
			else{
				PreparedStatement ps2=conn.prepareStatement("select id from usertemp where account=?");
				ps2.setString(1, account);
				ResultSet rs2=ps2.executeQuery();
				if (rs2.next()){
					exist = 2;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
	}
	
	/**
	 * 检查某邮箱是否已存在
	 * @param mail
	 * @return 0不存在，1存在已验证用户，2存在未验证用户
	 */
	public int queryMailExist(String mail){
		int exist = 0;
		try {
			PreparedStatement ps1=conn.prepareStatement("select id from user where mail=?");
			ps1.setString(1, mail);
			ResultSet rs1=ps1.executeQuery();
			if (rs1.next()){
				exist = 1;
			}
			else{
				PreparedStatement ps2=conn.prepareStatement("select id from usertemp where mail=?");
				ps2.setString(1, mail);
				ResultSet rs2=ps2.executeQuery();
				if (rs2.next()){
					exist = 2;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
	}
	
	public static void main(String[] args){
		MailDAO md = new MailDAO();
		
//		ReturnMessage rm = md.addUserTemp("lx", "199139", "909103970@qq.com");
//		System.out.println(rm.getMessage());
		
//		ReturnMessage rm = md.addUser("mlr", "199139", "909103970@qq.com");
//		System.out.println(rm.getMessage());
		
//		ReturnMessage rm = md.deleteUserTemp("mlr");
//		System.out.println(rm.getMessage());
		
//		ReturnMessage rm = md.deleteUser("mlr");
//		System.out.println(rm.getMessage());
		
		ReturnMessage rm = new ReturnMessage();
		try {
			rm = md.addVerify("lx", MD5.getMD5Str("lx"), MD5.getMD5Str("909103970@qq.com"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rm.getMessage());
	}

}
