package com.dt.wechatptf.services;

import java.sql.Date;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.dt.wechatptf.dao.ProductDAO;
import com.dt.wechatptf.dao.ProductPictureDAO;
import com.dt.wechatptf.dao.ProductTypeDAO;
import com.dt.wechatptf.dao.TypeDAO;
import com.dt.wechatptf.entity.Product;
import com.google.gson.Gson;

@Path("product")
public class ProductServiceImpl implements ProductService {
	
	final TypeDAO td = new TypeDAO();
	final ProductDAO pd = new ProductDAO();
	final ProductPictureDAO ppd = new ProductPictureDAO();
	final ProductTypeDAO ptd = new ProductTypeDAO();
	Gson gson = new Gson();

	@POST
	@Path("/addType")
	public String addType(String name) {
		return gson.toJson(td.addType(name));
	}

	@DELETE
	@Path("/deleteType/{typeid}")
	public String deleteType(@PathParam("typeid") int typeid) {
		// TODO Auto-generated method stub
		return gson.toJson(td.deleteType(typeid));
	}

	@GET
	@Path("/queryTypeName")
	public String queryTypeName(int typeid) {
		return td.queryType(typeid);
	}
	
	@GET
	@Path("/queryTypeId")
	public int queryTypeId(String name) {
		return td.queryId(name);
	}

	@GET
	@Path("/queryAllType")
	public String queryAllType() {
		return gson.toJson(td.queryAllType());
	}

	@POST
	@Path("/addProduct/{companyid}")
	public String addProduct(@FormParam("name") String name, @FormParam("price") double price, 
			@FormParam("description") String description, @FormParam("cover") String cover, 
			@FormParam("start_date") Date start_date, @FormParam("end_date") Date end_date, 
			@FormParam("point") int point, @FormParam("stock") int stock, ArrayList<String> pics, 
			ArrayList<Integer> type, @PathParam("companyid") int companyid) {
		Product p = new Product(name,price,description,cover,start_date,end_date,point,stock,pics,type);
		return gson.toJson(pd.addProduct(p, companyid));
	}
	
	@DELETE
	@Path("/deleteProduct")
	public String deleteProduct(int productid){
		return gson.toJson(pd.deleteProduct(productid));
	}

	@POST
	@Path("/updateProduct/{productid}")
	public String updateProduct(@FormParam("name") String name, @FormParam("price") double price, 
			@FormParam("description") String description, @FormParam("cover") String cover, 
			@FormParam("start_date") Date start_date, @FormParam("end_date") Date end_date, 
			@FormParam("point") int point, @FormParam("stock") int stock, @PathParam("productid") int productid) {
		Product p = new Product(name,price,description,cover,start_date,end_date,point,stock,null,null);
		p.setId(productid);
		return gson.toJson(pd.updateProduct(p));
	}

	@GET
	@Path("/queryProduct")
	public String queryProduct(int productid) {
		return gson.toJson(pd.queryProduct(productid));
	}

	@GET
	@Path("/queryAllPro/{companyid}")
	public String queryAllPro(@PathParam("companyid") int companyid) {
		return gson.toJson(pd.queryAllPro(companyid));
	}

	@POST
	@Path("/addPicture/{productid}")
	public String addPicture(@PathParam("productid") int productid, String pic) {
		return gson.toJson(ppd.addPicture(productid, pic));
	}

	@DELETE
	@Path("/deletePicture/{productid}/{pic}")
	public String deletePicture(@PathParam("productid") int productid, @PathParam("pic") String pic) {
		return gson.toJson(ppd.deletePicture(productid, pic));
	}

	@GET
	@Path("/queryPicture/{productid}")
	public String queryPicture(@PathParam("productid") int productid) {
		return gson.toJson(ppd.queryPicture(productid));
	}

	@POST
	@Path("/addTypeOfPro/{productid}")
	public String addTypeOfPro(@PathParam("productid") int productid, int typeid) {
		return gson.toJson(ptd.addProType(productid, typeid));
	}

	@DELETE
	@Path("/deleteTypeOfPro/{productid}/{typeid}")
	public String deleteTypeOfPro(int productid, int typeid) {
		return gson.toJson(ptd.deleteProType(productid, typeid));
	}

	@GET
	@Path("/queryTypeOfPro/{productid}")
	public String queryTypeOfPro(int productid) {
		return gson.toJson(ptd.queryTypeOfPro(productid));
	}

	@GET
	@Path("/queryProOfType/{typeid}")
	public String queryProOfType(int typeid) {
		return gson.toJson(ptd.queryProOfType(typeid));
	}

}
