package com.dt.wechatptf.entity;

import java.util.ArrayList;

public class Order {
	
	String id;
	String weiid;
	long time;
	ArrayList<String> rcpids;
	String name;
	String phone;
	String address;
	
	public Order(){}
	
	public Order(String weiid, long time, ArrayList<String> rcpids, String name, String phone, 
			String address){
		this.weiid = weiid;
		this.time = time;
		this.rcpids = rcpids;
		this.name = name;
		this.phone = phone;
		this.address = address;
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
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	public ArrayList<String> getRcpids() {
		return rcpids;
	}
	public void setRcpids(ArrayList<String> rcpids) {
		this.rcpids = rcpids;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
