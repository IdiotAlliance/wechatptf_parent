package com.dt.wechatptf.entity;

import java.util.Date;

public class Member {
	
	String id;
	String weiid;	//微信号
	String name;
	int gender;		//性别，0男，1女，2未知	
	Date birthday;
	String address;
	String mail;
	String phone;
	int points;
	
	public Member(){
		this.points = 0;
	}
	
	public Member(String weiid, String name, int gender, Date birthday, String address, 
			String mail, String phone, int points){
		this.weiid = weiid;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
		this.mail = mail;
		this.phone = phone;
		this.points = points;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

		
}
