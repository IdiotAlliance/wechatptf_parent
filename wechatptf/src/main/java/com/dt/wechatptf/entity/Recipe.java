package com.dt.wechatptf.entity;

public class Recipe {
	
	String id;
	int type;					//类型，0素菜，1荤菜，2汤
	String name;
	String picture;				//图片路径
	double price;
	int weight;					//份量
	String mainMaterial;		//主料
	String assistantMaterial;	//辅料
	String making;				//做法
	
	public Recipe(){}
	
	public Recipe(int type, String name, String picture, double price, int weight, 
			String mainMaterial, String assistantMaterial, String making){
		this.type = type;
		this.name = name;
		this.picture = picture;
		this.price = price;
		this.weight = weight;
		this.mainMaterial = mainMaterial;
		this.assistantMaterial = assistantMaterial;
		this.making = making;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getMainMaterial() {
		return mainMaterial;
	}
	public void setMainMaterial(String mainMaterial) {
		this.mainMaterial = mainMaterial;
	}
	
	public String getAssistantMaterial() {
		return assistantMaterial;
	}
	public void setAssistantMaterial(String assistantMaterial) {
		this.assistantMaterial = assistantMaterial;
	}
	
	public String getMaking() {
		return making;
	}
	public void setMaking(String making) {
		this.making = making;
	}

}
