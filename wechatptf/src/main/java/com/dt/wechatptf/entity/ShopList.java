package com.dt.wechatptf.entity;

public class ShopList {
	
	String id;
	String weiid;
	String rcpid;
	
	public ShopList(){}
	
	public ShopList(String weiid, String rcpid){
		this.weiid = weiid;
		this.rcpid = rcpid;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getWeiid() {
		return weiid;
	}
	public void setWeiid(String weiid) {
		this.weiid = weiid;
	}
	
	public String getRcpid() {
		return rcpid;
	}
	public void setRcpid(String rcpid) {
		this.rcpid = rcpid;
	}

}
