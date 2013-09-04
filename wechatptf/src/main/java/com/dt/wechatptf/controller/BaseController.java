package com.dt.wechatptf.controller;

import java.util.Map;

import org.apache.commons.collections.map.LRUMap;

public abstract class BaseController {
	
	private static final int DEFAULT_MAP_SIZE = (int) Math.pow(2, 22);
	private static final float DEFAULT_FACTOR = 0.7f; 
	
	@SuppressWarnings("unchecked")
	protected static final Map<String, Object> cachedValues = 
					new LRUMap(DEFAULT_MAP_SIZE, DEFAULT_FACTOR);
	
	protected void putValue(String uuid, Object value){
		cachedValues.put(uuid, value);
	}
	
	protected Object getValue(String uuid){
		Object value = cachedValues.remove(uuid);
		return value;
	}
	
}
