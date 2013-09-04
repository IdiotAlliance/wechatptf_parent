package com.dt.wechatptf.util;

import java.util.Random;

public class SixRandom {
	
	public static int getSixRan(){
		Random ran=new Random();
		return ran.nextInt(999999);
	}

}
