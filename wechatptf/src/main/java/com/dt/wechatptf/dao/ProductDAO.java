package com.dt.wechatptf.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dt.wechatptf.entity.Product;
import com.dt.wechatptf.util.ReturnMessage;

public class ProductDAO {
	
	Connection conn;
	final ProductPictureDAO pd = new ProductPictureDAO();
	final ProductTypeDAO ptd = new ProductTypeDAO();
	
	public ProductDAO(){
		conn = DBConnection.getChatDBConnection();
	}
	
	/**
	 * 添加商品
	 * @param product
	 * @param companyid
	 * @return
	 */
	public ReturnMessage addProduct(Product product, int companyid){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps1 = conn.prepareStatement("insert into product (companyid,name,price,description,cover,start_date,end_date,point,stock) values(?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, companyid);
			ps1.setString(2, product.getName());
			ps1.setDouble(3, product.getPrice());
			ps1.setString(4, product.getDescription());
			ps1.setString(5, product.getCover());
			//ps1.setDate(6, product.getStart_date());
			//ps1.setDate(7, product.getEnd_date());
			ps1.setInt(8, product.getPoint());
			ps1.setInt(9, product.getStock());
			ps1.executeUpdate();
			
			Serializable ret = null;
			ResultSet rs=ps1.getGeneratedKeys();
			if (rs.next()) {
                ret = (Serializable) rs.getObject(1);
            }
			long id = (Long)ret;
			int productid = (int) id;
			
//			for(int i=0; i<product.getPictures().size(); i++){
//				pd.addPicture(productid, product.getPictures().get(i));
//			}
			
//			for(int i=0; i<product.getType().size(); i++){
//				ptd.addProType(productid, product.getType().get(i));
//			}
			
			message.setFail(0);
			message.setMessage("添加商品成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("添加商品失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 删除商品
	 * @param productid
	 * @return
	 */
	public ReturnMessage deleteProduct(int productid){
		ReturnMessage message = new ReturnMessage();
		try {	
			PreparedStatement ps1=conn.prepareStatement("delete from product where id=?");
			ps1.setInt(1,productid);
			ps1.executeUpdate();
				
			PreparedStatement ps2=conn.prepareStatement("delete from product_picture where productid=?");
			ps2.setInt(1,productid);
			ps2.executeUpdate();
			
			PreparedStatement ps3=conn.prepareStatement("delete from product_type where productid=?");
			ps3.setInt(1,productid);
			ps3.executeUpdate();
			
			message.setFail(0);
			message.setMessage("删除商品成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除商品失败，未知错误！");

		}
		return message;
	}
	
	/**
	 * 更新商品信息
	 * @param product
	 * @return
	 */
	public ReturnMessage updateProduct(Product product){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps1=conn.prepareStatement("update product set name=?,price=?,description=?,cover=?,start_date=?,end_date=?,point=?,stock=? where id=?");
			ps1.setString(1, product.getName());
			ps1.setDouble(2, product.getPrice());
			ps1.setString(3, product.getDescription());
			ps1.setString(4, product.getCover());
			//ps1.setDate(5, product.getStart_date());
			//ps1.setDate(6, product.getEnd_date());
			ps1.setInt(7, product.getPoint());
			ps1.setInt(8, product.getStock());
			//ps1.setInt(9, product.getId());
			ps1.executeUpdate();
			message.setFail(0);
			message.setMessage("更新商品信息成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("更新会员信息失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 根据商品id查询商品
	 * @param productid
	 * @return
	 */
	public Product queryProduct(int productid){
		Product pro = new Product();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from product where id=?");
			ps.setInt(1, productid);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				//pro.setId(rs.getInt(1));
				pro.setName(rs.getString(3));
				pro.setPrice(rs.getDouble(4));
				pro.setDescription(rs.getString(5));
				pro.setCover(rs.getString(6));
				pro.setStart_date(rs.getDate(7));
				pro.setEnd_date(rs.getDate(8));
				pro.setPoint(rs.getInt(9));
				pro.setStock(rs.getInt(10));
				//pro.setPictures(pd.queryPicture(productid));
				//pro.setType(ptd.queryTypeOfPro(productid));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}
	
	/**
	 * 查询某个商家的所有商品
	 * @param companyid
	 * @return
	 */
	public ArrayList<Product> queryAllPro(int companyid){
		ArrayList<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from product where companyid=?");
			ps.setInt(1, companyid);
			ResultSet rs=ps.executeQuery();
			while (rs.next()){
				Product pro = new Product();
				int productid = rs.getInt(1);
				//pro.setId(productid);
				pro.setName(rs.getString(3));
				pro.setPrice(rs.getDouble(4));
				pro.setDescription(rs.getString(5));
				pro.setCover(rs.getString(6));
				pro.setStart_date(rs.getDate(7));
				pro.setEnd_date(rs.getDate(8));
				pro.setPoint(rs.getInt(9));
				pro.setStock(rs.getInt(10));
				//pro.setPictures(pd.queryPicture(productid));
				//pro.setType(ptd.queryTypeOfPro(productid));
				pros.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pros;
	}
	
	public static void main(String[] args){
		ProductDAO pd = new ProductDAO();
		
//		Calendar s = Calendar.getInstance();
//		s.set(Calendar.YEAR, 2013);
//		s.set(Calendar.MONTH, 8);	//month从0开始
//		s.set(Calendar.DAY_OF_MONTH, 1);
//		long s_d = s.getTimeInMillis();
//		
//		Calendar e = Calendar.getInstance();
//		e.set(Calendar.YEAR, 2013);
//		e.set(Calendar.MONTH, 11);	//month从0开始
//		e.set(Calendar.DAY_OF_MONTH, 31);
//		long e_d = e.getTimeInMillis();
//		
//		ArrayList<String> pics = new ArrayList<String>();
//		pics.add("img/pics/2/7/1");
//		pics.add("img/pics/2/7/2");
//		
//		ArrayList<Integer> type = new ArrayList<Integer>();
//		type.add(4);
//		type.add(5);
//		
//		Product p = new Product("pizza",44.5,"delicious","/img/cover/2/7",new Date(s_d),new Date(e_d),45,20,pics,type);
		
//		ReturnMessage rm = pd.addProduct(p, 2);
//		System.out.println(rm.getMessage());
		
//		p.setId(1);
//		p.setPrice(48);
//		ReturnMessage rm = pd.updateProduct(p);
//		System.out.println(rm.getMessage());
		
//		Product p = pd.queryProduct(1);
//		System.out.println(p.getDescription());
//		
//		ArrayList<Product> pros = pd.queryAllPro(2);
//		for(int i=0; i<pros.size(); i++){
//			System.out.println(pros.get(i).getName());
//		}
		
		ReturnMessage rm = pd.deleteProduct(1);
		System.out.println(rm.getMessage());
	}

}
