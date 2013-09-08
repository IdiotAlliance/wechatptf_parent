package com.dt.analyzer.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import com.dt.analyzer.core.DTTokenizer;
import com.dt.analyzer.entity.Token;
import com.dt.analyzer.entity.TokenProfile;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class InversedIndexDAO extends BaseMongoDAO{

	private static final String TOKEN = "token",
								FREQ  = "freq",
								DOCS  = "docs",
								_ID   = "_id";
	
	private DBCollection iiCollection = null;
	
	public InversedIndexDAO(){
		this.iiCollection = MongoConnectionManager.getCollection(
				DB_NAME, USERNAME, PASSWORD, IICOLLECTION_NAME);
	}
	
	public final void createIndex(String q, String docId){
		List<Token> tokens = DTTokenizer.tokenize(q, DTTokenizer.Strategy.COMPLEX);
		for(Token token: tokens){
			if(!token.isStopword()){
				DBCursor cursor = iiCollection.find(new BasicDBObject(TOKEN, token.getToken()));
				if(cursor.hasNext()){
					DBObject o  = cursor.next();
					ObjectId id = (ObjectId) o.get(_ID); 
					int freq    = (Integer) o.get(FREQ);
					// 怎么直接往数组里面插东西？
					BasicDBList list = (BasicDBList) o.get(DOCS);
					list.add(docId);
					iiCollection.update(new BasicDBObject(_ID, id), 
							new BasicDBObject(TOKEN, token.getToken())
									.append(FREQ, freq + 1)
									.append(DOCS, list));
				}else{
					iiCollection.insert(new BasicDBObject(TOKEN, token.getToken())
						.append(FREQ, 1)
						.append(DOCS, new String[]{docId}));
				}
			}
		}
	}
	
	public final TokenProfile getDocs(String token){
		final DBObject result = iiCollection.findOne(new BasicDBObject(TOKEN, token));
		if(result != null){
			int freq = (Integer) result.get(FREQ);
			BasicDBList list = (BasicDBList) result.get(DOCS);
			String[] docs = new String[list.size()];
			int i = 0;
			Iterator<Object> iter = list.iterator();
			while(iter.hasNext()){
				docs[i] = (String) iter.next();
				i ++;
			}
			return new TokenProfile(docs, freq, token.length());
		}
		return null;
	}
	
	public final void removeToken(String token){
		iiCollection.remove(new BasicDBObject(TOKEN, token));
	}
	
	public final void clearIndex(){
		iiCollection.drop();
	}
	
	public final void release(){
		this.iiCollection = null;
	}
	
}
