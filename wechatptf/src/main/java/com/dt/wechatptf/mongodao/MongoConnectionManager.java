package com.dt.wechatptf.mongodao;

import java.net.UnknownHostException;

import com.dt.wechatptf.util.PropertiesUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoConnectionManager {
	
	private static MongoClient mc = null;
	private static PropertiesUtil pu = new PropertiesUtil("mongodb.properties");
	static{
		try {
			mc = new MongoClient(pu.getStringProperty("db.host"), pu.getIntegerProperty("db.port", 27017));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * 创建到一个数据库的连接，指定用户名和密码进行身份认证。
	 * @param db_name 需要链接到的数据库名称
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public static final DB getConnection(
			final String db_name, 
			final String username, 
			final String password){
		DB db = mc.getDB(db_name);
		if(db.authenticate(username, password.toCharArray())){
			return db;
		}
		throw new IllegalArgumentException("Incorrect username or password!");
	}

	public static final DBCollection getCollection(
			final String db_name, 
			final String username, 
			final String password,
			final String collection_name){
		return getConnection(db_name, username, password).getCollection(collection_name);
	}

	/***
	 * 结束mongoClient
	 */
	public static void closeClient(){
		mc.close();
	}

}
