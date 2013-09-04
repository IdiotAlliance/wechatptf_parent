package com.dt.wechatptf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dt.wechatptf.entity.Member;
import com.dt.wechatptf.util.ReturnMessage;

public class MemberCompanyDAO {
	
	Connection conn;
	
	public MemberCompanyDAO(){
		conn = DBConnection.getChatDBConnection();
	}
	
	/**
	 * 查询某个商家的所有会员信息
	 * @param companyid
	 * @return
	 */
	public ArrayList<Member> queryMembers(String account){
		ArrayList<Member> members = new ArrayList<Member>();
		try {
			PreparedStatement ps=conn.prepareStatement("select id from company where account=?");
			ps.setString(1, account);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				int companyid = rs.getInt(1);
				PreparedStatement ps1=conn.prepareStatement("select memberid from member_company where companyid=?");
				ps1.setInt(1, companyid);
				ResultSet rs1=ps1.executeQuery();
				while (rs1.next()){
					int memberid = rs1.getInt(1);
					PreparedStatement ps2=conn.prepareStatement("select * from member where id=?");
					ps2.setInt(1, memberid);
					ResultSet rs2=ps2.executeQuery();
					if(rs2.next()){
						Member member = new Member();
						member.setId(rs2.getInt(1));
						member.setWeiid(rs2.getString(2));
						member.setName(rs2.getString(3));
						member.setGender(rs2.getInt(4));
						member.setBirthday(rs2.getDate(5));
						member.setAddress(rs2.getString(6));
						member.setMail(rs2.getString(7));
						member.setPhone(rs2.getString(8));
						member.setPoints(rs2.getInt(9));
						members.add(member);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return members;
	}
	
	/**
	 * 查询某个商家的某个会员的积分
	 * @param weiid
	 * @param companyid
	 * @return
	 */
	public int queryPoints(String weiid, int companyid){
		int points = 0;
		try {
			PreparedStatement ps=conn.prepareStatement("select points from member_company where weiid=? and companyid=?");
			ps.setString(1, weiid);
			ps.setInt(2, companyid);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				points = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return points;
	}
	
	/**
	 * 更新某个会员在某个商家的积分
	 * @param weiid
	 * @param companyid
	 * @param addPoints
	 * @return
	 */
	public ReturnMessage updatePoints(String weiid, int companyid, int addPoints){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps1=conn.prepareStatement("select points from member_company where weiid=? and companyid=?");
			ps1.setString(1, weiid);
			ps1.setInt(2, companyid);
			ResultSet rs=ps1.executeQuery();
			int points = 0;
			if (rs.next()){
				points = rs.getInt(1);
				points = points + addPoints;
				
				PreparedStatement ps2=conn.prepareStatement("update member_company set points=? where weiid=? and companyid=?");
				ps2.setInt(1, points);
				ps2.setString(2, weiid);
				ps2.setInt(3, companyid);
				ps2.executeUpdate();
				message.setFail(0);
				message.setMessage("更新会员积分成功！该会员目前积分为"+points);
			}
			
			else{
				message.setFail(1);
				message.setMessage("更新会员积分失败，该会员不存在！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("更新会员积分失败，未知错误！");
		}
		return message;
	}
	
	public static void main(String[] args){
		MemberCompanyDAO mcd = new MemberCompanyDAO();
		
		ArrayList<Member> ms = mcd.queryMembers("google");
		for(int i=0; i<ms.size(); i++){
			System.out.println(ms.get(i).getId());
			System.out.println(ms.get(i).getName());
		}
		
//		int points = mcd.queryPoints("mlr", 1);
//		System.out.println(points);
		
//		ReturnMessage rm = mcd.updatePoints("mlr", 1, 30);
//		System.out.println(rm.getMessage());
	}

}
