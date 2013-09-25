package com.dt.wechatptf.entity;

public class Graphic {
	
	public String id;
	public String picture;
	public String description;
	
	public Graphic(){}
	
	public Graphic(String pic, String des){
		this.picture = pic;
		this.description = des;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
