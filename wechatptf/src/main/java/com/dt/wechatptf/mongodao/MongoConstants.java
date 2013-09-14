package com.dt.wechatptf.mongodao;

import com.dt.wechatptf.util.PropertiesUtil;

public class MongoConstants {
	
	private static final PropertiesUtil pu = new PropertiesUtil("db.properties");
	public static final String DB_NAME     = pu.getStringProperty("db.name"),
							   DB_HOST     = pu.getStringProperty("db.host"),
							   DB_PORT     = pu.getStringProperty("db.port"),
							   DB_USERNAME = pu.getStringProperty("db.username"),
							   DB_PASSWORD = pu.getStringProperty("db.password");

}
