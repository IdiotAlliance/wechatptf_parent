package com.dt.analyzer.dao;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoConnectionManager {

	private static MongoClient mc = null;
	private static DB db = null;
	static{
		try {
			mc = new MongoClient("localhost", 27017);
			db = mc.getDB("mydb");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
