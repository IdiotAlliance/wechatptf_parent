package com.dt.wechatptf.entity;

import java.sql.Date;
import java.util.ArrayList;

public class Product {
	
	int id;
	String name;
	double price;
	String description;
	String cover;			//封面图片路径
	Date start_date;		//开始日期
	Date end_date;			//结束日期
	int point;
	int stock;				//库存
	ArrayList<String> pictures;
	ArrayList<Integer> type;
	
	public Product(){}
	
	public Product(String name, double price, String description, String cover, Date start_date, 
			Date end_date, int point, int stock, ArrayList<String> pics, ArrayList<Integer> type){
		this.name = name;
		this.price = price;
		this.description = description;
		this.cover = cover;
		this.start_date = start_date;
		this.end_date = end_date;
		this.point = point;
		this.stock = stock;
		this.pictures = pics;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public ArrayList<String> getPictures() {
		return pictures;
	}
	
	public void setPictures(ArrayList<String> pictures) {
		this.pictures = pictures;
	}
	
	public ArrayList<Integer> getType() {
		return type;
	}
	public void setType(ArrayList<Integer> type) {
		this.type = type;
	}

}
