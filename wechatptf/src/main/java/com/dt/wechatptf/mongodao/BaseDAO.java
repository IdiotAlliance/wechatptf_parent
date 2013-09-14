package com.dt.wechatptf.mongodao;

import com.mongodb.DBCollection;

public class BaseDAO {
	
	protected DBCollection dc = null;
	protected String collectionName = null;

	public BaseDAO(String collectionName){
		this.collectionName = collectionName;
		this.dc = MongoConnectionManager.getCollection(
				MongoConstants.DB_NAME, 
				MongoConstants.DB_USERNAME, 
				MongoConstants.DB_PASSWORD, 
				collectionName);
	}

	public void clear(){
		this.dc.drop();
	}

}
