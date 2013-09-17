package com.dt.wechatptf.entity;

public class Activity {
	
	String id;
	String weiid;
	String companyid;
	int type;			//相关的商品种类，0普通商品，1团购
	String goodsid;		//相对应的普通商品或团购等的id
	String time;		//发起活动时间戳
	String brief;
	String detail;
	String name;
	String password;
	
	public Activity(){}
	
	public Activity(String weiid, String companyid, int type, String goodsid, String time, String brief, 
			String detail, String name, String password){
		this.weiid = weiid;
		this.companyid = companyid;
		this.type = type;
		this.goodsid = goodsid;
		this.time = time;
		this.brief = brief;
		this.detail= detail;
		this.name = name;
		this.password = password;
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
	
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
