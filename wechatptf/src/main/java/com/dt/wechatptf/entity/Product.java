package com.dt.wechatptf.entity;

import java.util.ArrayList;
import java.util.Date;

public class Product {
	
	String id;
	String name;
	double price;
	String description;
	String cover;			//封面图片路径
	Date start_date;		//开始日期
	Date end_date;			//结束日期
	int point;
	int stock;				//库存
	ArrayList<Graphic> graphics;
	int sale;				//下架0，上架1
	//ArrayList<Integer> type;
	
	public Product(){}
	
	public Product(String name, double price, String description, String cover, Date start_date, 
			Date end_date, int point, int stock, ArrayList<Graphic> gras){
		this.name = name;
		this.price = price;
		this.description = description;
		this.cover = cover;
		this.start_date = start_date;
		this.end_date = end_date;
		this.point = point;
		this.stock = stock;
		this.graphics = gras;
		long today = System.currentTimeMillis();
		if(today > start_date.getTime()){
			this.sale = 1;
		}
		else{
			this.sale = 0;
		}
		//this.type = type;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
		long today = System.currentTimeMillis();
		if(today > start_date.getTime()){
			this.setSale(1);
		}
		else{
			this.setSale(0);
		}
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

	public ArrayList<Graphic> getGraphics() {
		return graphics;
	}

	public void setGraphics(ArrayList<Graphic> graphics) {
		this.graphics = graphics;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	
//	public ArrayList<Integer> getType() {
//		return type;
//	}
//	public void setType(ArrayList<Integer> type) {
//		this.type = type;
//	}

}
