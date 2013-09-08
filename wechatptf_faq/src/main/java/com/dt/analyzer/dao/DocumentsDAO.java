package com.dt.analyzer.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.dt.analyzer.entity.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class DocumentsDAO extends BaseMongoDAO{
	
	private DBCollection dcc = null;
	private InversedIndexDAO iidao = null;
	private static final String QUESTION = "q",
						 		ANSWER   = "a",
						 		CID      = "cid",
						 		FAKE_ID  = "fake_id",
						 		_ID = "_id";
	private static final Object lock = new Object();
	
	public DocumentsDAO(){
		this.dcc = MongoConnectionManager.getCollection(
				DB_NAME, USERNAME, PASSWORD, DOCCOLLECTION_NAME);
		this.iidao = new InversedIndexDAO();
	}
	
	/***
	 * 插入一条新的问答记录, 然后将问题插入倒排索引中。在插入记录时会生成一个
	 * fake_id，却别于自动生成的id，这个id保证是全局唯一的。逆向索引使用该
	 * id索引文档。
	 * @param q 问题
	 * @param a 答案
	 * @param cid 商家id
	 */
	public final void insertDOC(String q, String a, String cid){
		String fakeId = genId(cid);
		dcc.insert(new BasicDBObject(QUESTION, q)
							.append(ANSWER, a)
							.append(CID, cid)
							.append(FAKE_ID, fakeId));
		iidao.createIndex(q, fakeId);
	}
	
	/***
	 * 根据问题和cid寻找答案，若找到完全匹配的项则直接返回
	 * @param q
	 * @param cid
	 * @return
	 */
	public final Document getDocByQnCid(String q, String cid){
		DBObject result = dcc.findOne(new BasicDBObject(QUESTION, q)
						.append(cid, cid));
		if(result != null){
			return new Document((String)result.get(QUESTION), 
								(String)result.get(ANSWER), 
								(String)result.get(CID));
		}
		return null;
	}
	
	/***
	 * 根据公司的id获取文档
	 * @param cid
	 * @return
	 */
	public final List<Document> getDocsByCid(String cid){
		List<Document> docs = new ArrayList<Document>();
		Pattern pattern  = Pattern.compile("^(" + cid + ")_.*");
		DBObject filter = new BasicDBObject(FAKE_ID, pattern);
		DBCursor cursor = dcc.find(filter);
		while(cursor.hasNext()){
			DBObject o = cursor.next();
			docs.add(new Document((String)o.get(QUESTION), 
								  (String)o.get(ANSWER), 
								  (String)o.get(CID)));
		}
		return docs;
	}
	
	public final Document getDocById(String id){
		DBObject o = dcc.findOne(new BasicDBObject(_ID, id));
		if(o != null)
			return new Document((String)o.get(QUESTION), (String)o.get(ANSWER), (String)o.get(CID));
		return null;
	}
	
	/***
	 * 删除一个公司的所有文档，使用正则表达式，删除所有以companyId开头的
	 * 
	 * @param cid
	 */
	public final void removeCompany(String cid){
		Pattern pattern = Pattern.compile("^(" + cid + "_).*");
		dcc.remove(new BasicDBObject(FAKE_ID, pattern));
	}
	
	public final void clearCollection(){
		dcc.drop();
	}

	public final void release(){
		this.dcc = null;
		this.iidao.release();
	}
	
	private static final String genId(String cid){
		long timestamp = 0;
		synchronized(lock){
			timestamp = System.currentTimeMillis();
		}
		return cid + "_" + timestamp;
	}
}
