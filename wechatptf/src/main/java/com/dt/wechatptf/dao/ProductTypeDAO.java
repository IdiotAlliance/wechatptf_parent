package com.dt.wechatptf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dt.wechatptf.util.ReturnMessage;

public class ProductTypeDAO {
	
	Connection conn;
	
	public ProductTypeDAO(){
		conn = DBConnection.getChatDBConnection();
	}
	
	/**
	 * 添加商品到某个类型
	 * @param productid
	 * @param typeid
	 * @return
	 */
	public ReturnMessage addProType(int productid, int typeid){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into product_type (productid,typeid) values(?,?)");
			ps.setInt(1, productid);
			ps.setInt(2,typeid);
			ps.executeUpdate();
			message.setFail(0);
			message.setMessage("添加商品至类型成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("添加商品至类型失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 将商品从某个类型中删除
	 * @param productid
	 * @return
	 */
	public ReturnMessage deleteProType(int productid, int typeid){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps=conn.prepareStatement("delete from product_type where productid=? and typeid=?");
			ps.setInt(1,productid);
			ps.setInt(2, typeid);
			ps.executeUpdate();
			message.setFail(0);
			message.setMessage("删除商品指定类型成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除商品指定类型失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 查询某个商品所属的所有类型
	 * @param productid
	 * @return
	 */
	public ArrayList<Integer> queryTypeOfPro(int productid){
		ArrayList<Integer> types = new ArrayList<Integer>();
		try {
			PreparedStatement ps=conn.prepareStatement("select typeid from product_type where productid=?");
			ps.setInt(1, productid);
			ResultSet rs=ps.executeQuery();
			while (rs.next()){
				types.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}
	
	/**
	 * 查询某个属于某个类型的所有商品
	 * @param typeid
	 * @return
	 */
	public ArrayList<Integer> queryProOfType(int typeid){
		ArrayList<Integer> pros = new ArrayList<Integer>();
		try {
			PreparedStatement ps=conn.prepareStatement("select productid from product_type where typeid=?");
			ps.setInt(1, typeid);
			ResultSet rs=ps.executeQuery();
			while (rs.next()){
				pros.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pros;
	}
	
	public static void main(String[] args){
		ProductTypeDAO ptd = new ProductTypeDAO();
		
//		ArrayList<Integer> types = ptd.queryTypeOfPro(1);
//		for(int i =0; i<types.size();i++){
//			System.out.println(types.get(i));
//		}
//		
//		ArrayList<Integer> pics = ptd.queryProOfType(4);
//		for(int i =0; i<pics.size();i++){
//			System.out.println(pics.get(i));
//		}
		
		ReturnMessage rm = ptd.deleteProType(1, 5);
		System.out.println(rm.getMessage());
	}

}
