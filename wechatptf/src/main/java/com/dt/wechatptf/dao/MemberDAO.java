package com.dt.wechatptf.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dt.wechatptf.entity.Member;
import com.dt.wechatptf.util.ReturnMessage;

public class MemberDAO {
	
	Connection conn;
	
	public MemberDAO(){
		conn = DBConnection.getChatDBConnection();
	}
	
	/**
	 * 添加会员
	 * @param member
	 */
	public ReturnMessage addMember(Member member, int companyid){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps1 = conn.prepareStatement("insert into member (weiid,name,gender,birthday,address,mail,phone,points) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1, member.getWeiid());
			ps1.setString(2, member.getName());
			ps1.setInt(3, member.getGender());
			//ps1.setDate(4, member.getBirthday());
			ps1.setString(5, member.getAddress());
			ps1.setString(6, member.getMail());
			ps1.setString(7, member.getPhone());
			ps1.setInt(8, member.getPoints());
			ps1.executeUpdate();
			
			Serializable ret = null;
			ResultSet rs=ps1.getGeneratedKeys();
			if (rs.next()) {
                ret = (Serializable) rs.getObject(1);
            }
			long memberid = (Long)ret;
			
			PreparedStatement ps2 = conn.prepareStatement("insert into member_company (memberid,weiid,companyid) values(?,?,?)");
			ps2.setLong(1, memberid);
			ps2.setString(2, member.getWeiid());
			ps2.setInt(3, companyid);
			ps2.executeUpdate();
			
			message.setFail(0);
			message.setMessage("添加会员成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("添加会员失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 删除会员
	 * @param memberid
	 * @param companyid
	 * @return
	 */
	public ReturnMessage deleteMember(String weiid, int companyid){
		ReturnMessage message = new ReturnMessage();
		int memberid = this.queryMemberid(weiid, companyid);
		try {	
			PreparedStatement ps1=conn.prepareStatement("delete from member where id=?");
			ps1.setInt(1,memberid);
			ps1.executeUpdate();
				
			PreparedStatement ps2=conn.prepareStatement("delete from member_company where memberid=?");
			ps2.setInt(1,memberid);
			ps2.executeUpdate();
			
			message.setFail(0);
			message.setMessage("删除会员成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除会员失败，未知错误！");

		}
		return message;
	}
	
	/**
	 * 更新会员信息
	 * @param member
	 * @return
	 */
	public ReturnMessage updateMember(Member member, int companyid){
		ReturnMessage message = new ReturnMessage();
		int memberid = this.queryMemberid(member.getWeiid(), companyid);
		try {
			PreparedStatement ps=conn.prepareStatement("update member set name=?,gender=?,birthday=?,address=?,mail=?,phone=? where id=?");
			ps.setString(1, member.getName());
			ps.setInt(2, member.getGender());
			//ps.setDate(3, member.getBirthday());
			ps.setString(4, member.getAddress());
			ps.setString(5, member.getMail());
			ps.setString(6, member.getPhone());
			ps.setInt(7, memberid);
			ps.executeUpdate();
			message.setFail(0);
			message.setMessage("更新会员信息成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("更新会员信息失败，未知错误！");
		}
		return message;
	}
	
//	/**
//	 * 更新会员积分
//	 * @param memberid
//	 * @param addPoints 增加或减少的积分值
//	 * @return
//	 */
//	public ReturnMessage updatePoints(String weiid, int companyid, int addPoints){
//		ReturnMessage message = new ReturnMessage();
//		int memberid = this.queryMemberid(weiid, companyid);
//		try {
//			PreparedStatement ps1=conn.prepareStatement("select points from member where id=?");
//			ps1.setInt(1, memberid);
//			ResultSet rs=ps1.executeQuery();
//			int points = 0;
//			if (rs.next()){
//				points = rs.getInt(1);
//				points = points + addPoints;
//				
//				PreparedStatement ps2=conn.prepareStatement("update member set points=? where id=?");
//				ps2.setInt(1, points);
//				ps2.setInt(2, memberid);
//				ps2.executeUpdate();
//				message.setFail(0);
//				message.setMessage("更新会员积分成功！");
//			}
//			
//			else{
//				message.setFail(1);
//				message.setMessage("更新会员积分失败，该会员不存在！");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			message.setFail(1);
//			message.setMessage("更新会员积分失败，未知错误！");
//		}
//		return message;
//	}
	
	/**
	 * 查询会员信息
	 * @param memberid
	 * @return
	 */
	public Member queryMember(String weiid, int companyid){
		Member member = new Member();
		int memberid = this.queryMemberid(weiid, companyid);
		try {
			PreparedStatement ps=conn.prepareStatement("select * from member where id=?");
			ps.setInt(1, memberid);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				//member.setId(rs.getInt(1));
				member.setWeiid(rs.getString(2));
				member.setName(rs.getString(3));
				member.setGender(rs.getInt(4));
				member.setBirthday(rs.getDate(5));
				member.setAddress(rs.getString(6));
				member.setMail(rs.getString(7));
				member.setPhone(rs.getString(8));
				member.setPoints(rs.getInt(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	/**
	 * 查询id
	 * @param weiid
	 * @param companyid
	 * @return
	 */
	public int queryMemberid(String weiid, int companyid){
		int memberid = -1;
		try {
			PreparedStatement ps=conn.prepareStatement("select memberid from member_company where weiid=? and companyid=?");
			ps.setString(1, weiid);
			ps.setInt(2, companyid);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				memberid = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberid;
	}
	
	public static void main(String[] args){
//		MemberDAO md = new MemberDAO();
		
//		Member m = new Member();
//		m.setWeiid("mlr");
//		m.setName("Cassie");
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.YEAR, 1991);
//		c.set(Calendar.MONTH, 2);	//month从0开始
//		c.set(Calendar.DAY_OF_MONTH, 9);
//		long birthday = c.getTimeInMillis();
//		m.setBirthday(new Date(birthday));
//		ReturnMessage rm = md.addMember(m, 1);
//		System.out.println(rm.getMessage());
		
//		ReturnMessage rm = md.deleteMember("mlr", 1);
//		System.out.println(rm.getMessage());
		
//		Member m = new Member();
//		m.setId(5);
//		m.setName("Cassie");
//		m.setMail("909103970@qq.com");
//		ReturnMessage rm = md.updateMember(m);
//		System.out.println(rm.getMessage());
		
//		Member m = md.queryMember("mlr", 1);
//		System.out.println(m.getName());
	}

}
