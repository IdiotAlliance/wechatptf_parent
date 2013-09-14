package com.dt.wechatptf.util;

public class UUIDUtil {
	
	public synchronized static final String genUUID(){
		long time = System.currentTimeMillis();
		String sha1 = Encoder.encodeSHA1(time + "");
		return sha1;
	}
	
	public static void main(String[] args){
		System.out.println(genUUID());
	}
}
