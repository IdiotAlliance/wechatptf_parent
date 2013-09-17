package com.dt.wechatptf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dt.wechatptf.entity.Activity;
import com.dt.wechatptf.util.ReturnMessage;

public class ActivityDAO {
	
	Connection conn;
	
	public ActivityDAO(){
		conn = DBConnection.getChatDBConnection();
	}
	
	/**
	 * 添加活动
	 * @param act
	 * @return
	 */
	public ReturnMessage addActivity(Activity act){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into activity (weiid,companyid,type,goodsid,time,brief,detail,name,password) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, act.getWeiid());
			//ps.setInt(2, act.getCompanyid());
			ps.setInt(3, act.getType());
			//ps.setInt(4, act.getGoodsid());
			ps.setString(5, act.getTime());
			ps.setString(6, act.getBrief());
			ps.setString(7, act.getDetail());
			ps.setString(8, act.getName());
			ps.setString(9, act.getPassword());
			ps.executeUpdate();
			
			message.setFail(0);
			message.setMessage("添加活动成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("添加活动失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 删除活动
	 * @param weiid
	 * @param type
	 * @param goodsid
	 * @param time
	 * @return
	 */
	public ReturnMessage deleteActivity(String weiid, int type, int goodsid, String time){
		ReturnMessage message = new ReturnMessage();
		int actid = this.queryActId(weiid, type, goodsid, time);
		try {	
			PreparedStatement ps1=conn.prepareStatement("delete from activity where id=?");
			ps1.setInt(1,actid);
			ps1.executeUpdate();
				
			PreparedStatement ps2=conn.prepareStatement("delete from signup where activityid=?");
			ps2.setInt(1,actid);
			ps2.executeUpdate();
			
			message.setFail(0);
			message.setMessage("删除活动成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除活动失败，未知错误！");

		}
		return message;
	}
	
	/**
	 * 更新活动信息
	 * @param act
	 * @return
	 */
	public ReturnMessage updateActivity(Activity act){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps=conn.prepareStatement("update activity set brief=?,detail=? where id=?");
			ps.setString(1, act.getBrief());
			ps.setString(2, act.getDetail());
			//ps.setInt(3, act.getId());
			ps.executeUpdate();
			message.setFail(0);
			message.setMessage("更新活动信息成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("更新活动信息失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 查询活动信息
	 * @param weiid
	 * @param type
	 * @param goodsid
	 * @param time
	 * @return
	 */
	public Activity queryActivity(String weiid, int type, int goodsid, String time){
		Activity act = new Activity();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from activity where weiid=? and type=? and goodsid=? and time=?");
			ps.setString(1, weiid);
			ps.setInt(2, type);
			ps.setInt(3, goodsid);
			ps.setString(4, time);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				//act.setId(rs.getInt(1));
				act.setWeiid(weiid);
				act.setType(type);
				//act.setGoodsid(goodsid);
				act.setTime(time);
				act.setBrief(rs.getString(7));
				act.setDetail(rs.getString(8));
				act.setName(rs.getString(9));
				act.setPassword(rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return act;
	}
	
	/**
	 * 查询活动id
	 * @param weiid
	 * @param type
	 * @param goodsid
	 * @param time
	 * @return
	 */
	public int queryActId(String weiid, int type, int goodsid, String time){
		int actid = -1;
		try {
			PreparedStatement ps=conn.prepareStatement("select id from activity where weiid=? and type=? and goodsid=? and time=?");
			ps.setString(1, weiid);
			ps.setInt(2, type);
			ps.setInt(3, goodsid);
			ps.setString(4, time);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				actid = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actid;
	}
	
	public static void main(String[] args){
//		ActivityDAO ad = new ActivityDAO();
		
//		Activity a = new Activity("mlr",1,0,1,System.currentTimeMillis()+"","sweet ktv","Just you and me","Cassie","199139");
//		ReturnMessage rm = ad.addActivity(a);
//		System.out.println(rm.getMessage());
		
//		a.setId(1);
//		a.setDetail("Let's have fun");
//		ReturnMessage rm = ad.updateActivity(a);
//		System.out.println(rm.getMessage());
		
//		ReturnMessage rm = ad.deleteActivity("mlr", 0, 1, "1378277114383");
//		System.out.println(rm.getMessage());
	}

}
