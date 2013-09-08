package com.dt.analyzer.dao;

import com.dt.wechatptf.util.PropertiesUtil;

public abstract class BaseMongoDAO {

	protected static final PropertiesUtil pu = new PropertiesUtil("db.properties");
	protected static final String DB_NAME  = pu.getStringProperty("db.name"),
								  USERNAME = pu.getStringProperty("db.username"),
								  PASSWORD = pu.getStringProperty("db.password"),
								  IICOLLECTION_NAME  = pu.getStringProperty("db.iicollection.name"),
								  DOCCOLLECTION_NAME = pu.getStringProperty("db.doccollection.name");
	
	protected abstract void release();
}
