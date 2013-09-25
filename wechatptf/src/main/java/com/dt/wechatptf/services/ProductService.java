package com.dt.wechatptf.services;

import java.sql.Date;
import java.util.ArrayList;

import com.dt.wechatptf.entity.Graphic;

public interface ProductService {
	
	public String addType(String name);
	public String deleteType(int typeid);
	public String queryTypeName(int typeid);
	public int queryTypeId(String name);
	public String queryAllType();
	
	public String addProduct(String name, double price, String description, String cover, 
			Date start_date, Date end_date, int point, int stock, ArrayList<Graphic> gras, 
			int companyid);
	public String deleteProduct(int productid);
	public String updateProduct(String name, double price, String description, String cover, 
			Date start_date, Date end_date, int point, int stock, String productid);
	public String queryProduct(int productid);
	public String queryAllPro(int companyid);
	
	public String addPicture(int productid, String pic);
	public String deletePicture(int productid, String pic);
	public String queryPicture(int productid);
	
	public String addTypeOfPro(int productid, int typeid);
	public String deleteTypeOfPro(int productid, int typeid);
	public String queryTypeOfPro(int productid);
	public String queryProOfType(int typeid);

}
