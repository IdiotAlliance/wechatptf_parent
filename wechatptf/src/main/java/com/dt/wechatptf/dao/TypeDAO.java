package com.dt.wechatptf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dt.wechatptf.util.ReturnMessage;

public class TypeDAO {
	
	Connection conn;
	
	public TypeDAO(){
		conn = DBConnection.getChatDBConnection();
	}
	
	/**
	 * 添加商品类型
	 * @param name
	 * @return
	 */
	public ReturnMessage addType(String name){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into type (name) values(?)");
			ps.setString(1,name);
			ps.executeUpdate();
			message.setFail(0);
			message.setMessage("添加商品类型成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("添加商品类型失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 删除商品类型
	 * @param name
	 * @return
	 */
	public ReturnMessage deleteType(int typeid){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps1=conn.prepareStatement("delete from type where id=?");
			ps1.setInt(1, typeid);
			ps1.executeUpdate();
			
			PreparedStatement ps2=conn.prepareStatement("delete from product_type where typeid=?");
			ps2.setInt(1, typeid);
			ps2.executeUpdate();
			message.setFail(0);
			message.setMessage("删除商品类型成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除商品类型失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 查询商品类型名称
	 * @param typeid
	 * @return
	 */
	public String queryType(int typeid){
		String name = null;
		try {
			PreparedStatement ps=conn.prepareStatement("select name from type where id=?");
			ps.setInt(1, typeid);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	/**
	 * 根据名称查询商品类型的id
	 * @param name
	 * @return
	 */
	public int queryId(String name){
		int id = 0;
		try {
			PreparedStatement ps=conn.prepareStatement("select id from type where name=?");
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * 查询所有商品类型的名称
	 * @return
	 */
	public ArrayList<String> queryAllType(){
		ArrayList<String> types = new ArrayList<String>();
		try {
			PreparedStatement ps=conn.prepareStatement("select name from type");
			ResultSet rs=ps.executeQuery();
			while (rs.next()){
				String name = rs.getString(1);
				types.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}
	
	public static void main(String[] args){
		TypeDAO td = new TypeDAO();
		
//		ReturnMessage rm = td.addType("Food");
//		System.out.println(rm.getMessage());
		
//		ReturnMessage rm = td.deleteType(3);
//		System.out.println(rm.getMessage());
		
		String name = td.queryType(4);
		System.out.println(name);
	}

}
