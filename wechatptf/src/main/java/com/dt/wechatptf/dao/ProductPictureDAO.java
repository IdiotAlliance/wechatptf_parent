package com.dt.wechatptf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dt.wechatptf.util.ReturnMessage;

public class ProductPictureDAO {
	
	Connection conn;
	
	public ProductPictureDAO(){
		conn = DBConnection.getChatDBConnection();
	}
	
	/**
	 * 添加图片
	 * @param productid
	 * @param pic
	 * @return
	 */
	public ReturnMessage addPicture(int productid, String pic){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into product_picture (productid,picture) values(?,?)");
			ps.setInt(1, productid);
			ps.setString(2,pic);
			ps.executeUpdate();
			message.setFail(0);
			message.setMessage("添加商品图片成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("添加商品图片失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 删除指定图片
	 * @param productid
	 * @param pic
	 * @return
	 */
	public ReturnMessage deletePicture(int productid, String pic){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps=conn.prepareStatement("delete from product_picture where productid=? and picture=?");
			ps.setInt(1,productid);
			ps.setString(2, pic);
			ps.executeUpdate();
			message.setFail(0);
			message.setMessage("删除商品指定图片成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除商品指定图片失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 查询某个商品的所有图片
	 * @param productid
	 * @return
	 */
	public ArrayList<String> queryPicture(int productid){
		ArrayList<String> pics = new ArrayList<String>();
		try {
			PreparedStatement ps=conn.prepareStatement("select picture from product_picture where productid=?");
			ps.setInt(1, productid);
			ResultSet rs=ps.executeQuery();
			while (rs.next()){
				pics.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pics;
	}

}
