package com.dt.wechatptf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dt.wechatptf.util.PropertiesUtil;

public class DBConnection {
	
	private static Connection singleChatCon;
	private static Connection singleMailCon;
	private static final PropertiesUtil pu = new PropertiesUtil("db.properties");
	
	public static Connection getChatDBConnection(){
		if (singleChatCon == null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				String url = pu.getStringProperty("db.chat_url");
				String user = pu.getStringProperty("db.username");
				String pass = pu.getStringProperty("db.password");
				singleChatCon = DriverManager.getConnection(url,user,pass);
			}catch(SQLException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return singleChatCon;	
	}
	
	public static Connection getMailDBConnection(){
		if (singleMailCon == null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				String url = pu.getStringProperty("db.mail_url");
				String user = pu.getStringProperty("db.username");
				String pass = pu.getStringProperty("db.password");
				singleMailCon = DriverManager.getConnection(url,user,pass);
			}catch(SQLException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return singleMailCon;
	}

}
