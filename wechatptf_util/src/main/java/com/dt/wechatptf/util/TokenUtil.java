package com.dt.wechatptf.util;

public class TokenUtil {
	
	public static final String KEY_UUID = "uuid";

	private static final String[] ALPHEBAT = {
		"A", "B", "C", "D", "E", "F", "G", "H", "I",
		"J", "K", "L", "M", "N", "O", "P", "Q", "R",
		"S", "T", "U", "V", "W", "X", "Y", "Z", "_"
	};
	private static final int UUID_LENGTH = 32;
	
	public static String genUUID(){
		final int factor = 17;
		long ctime = System.currentTimeMillis();
		String uuid = "";
		for(;ctime > 0; ctime /= 10){
			uuid += ALPHEBAT[(int) (
								Math.pow(factor, (ctime % 10) + 
								Math.random() * factor) % ALPHEBAT.length)];
		}
		while(uuid.length() < UUID_LENGTH)
			uuid += ALPHEBAT[(int) (Math.random() * ALPHEBAT.length)];
		return uuid;
	}
	
	public static int lenOfUUID(){
		return UUID_LENGTH;
	}
}
