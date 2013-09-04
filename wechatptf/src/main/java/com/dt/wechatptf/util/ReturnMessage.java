package com.dt.wechatptf.util;

public class ReturnMessage {
	
	int fail;		//成功与否，0成功，1失败
	String message;	//错误信息
	
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
