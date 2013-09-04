package com.dt.wechatptf.entity;

public class Company {
	
	int id;
	String account;
	String password;
	String name;
	String address;
	String weiid;
	String director;	//负责人
	String phone;
	String description;
	
	public Company(){}
	
	public Company(String account, String password, String name, String address, 
			String weiid, String director, String phone, String description){
		this.account = account;
		this.password = password;
		this.name = name;
		this.address = address;
		this.weiid = weiid;
		this.director = director;
		this.phone = phone;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getWeiid() {
		return weiid;
	}
	public void setWeiid(String weiid) {
		this.weiid = weiid;
	}
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
