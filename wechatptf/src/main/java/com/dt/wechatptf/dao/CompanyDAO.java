package com.dt.wechatptf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dt.wechatptf.entity.Company;
import com.dt.wechatptf.util.ReturnMessage;

public class CompanyDAO {
	
	Connection conn;
	
	public CompanyDAO(){
		conn = DBConnection.getChatDBConnection();
	}
	
	/**
	 * 添加企业
	 * @param company
	 * @return
	 */
	public ReturnMessage addCompany(Company company){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into company (account,password,name,address,weiid,director,phone,description) values(?,?,?,?,?,?,?,?)");
			ps.setString(1, company.getAccount());
			ps.setString(2, company.getPassword());
			ps.setString(3, company.getName());
			ps.setString(4, company.getAddress());
			ps.setString(5, company.getWeiid());
			ps.setString(6, company.getDirector());
			ps.setString(7, company.getPhone());
			ps.setString(8, company.getDescription());
			ps.executeUpdate();
			
			message.setFail(0);
			message.setMessage("添加企业成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("添加企业失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 删除企业
	 * @param account
	 * @return
	 */
	public ReturnMessage deleteCompany(String account){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps1=conn.prepareStatement("select id from company where account=?");
			ps1.setString(1, account);
			ResultSet rs=ps1.executeQuery();
			int id = 0;
			if (rs.next()){
				id=rs.getInt(1);
				PreparedStatement ps2=conn.prepareStatement("delete from company where account=?");
				ps2.setString(1,account);
				ps2.executeUpdate();
				
				PreparedStatement ps3=conn.prepareStatement("delete from member_company where companyid=?");
				ps3.setInt(1,id);
				ps3.executeUpdate();
				
				message.setFail(0);
				message.setMessage("删除企业成功！");
			}
			
			else{
				message.setFail(1);
				message.setMessage("删除企业失败，该企业不存在！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("删除企业失败，未知错误！");

		}
		return message;
	}
	
	/**
	 * 更新企业信息
	 * @param company
	 * @return
	 */
	public ReturnMessage updateCompany(Company company){
		ReturnMessage message = new ReturnMessage();
		try {
			PreparedStatement ps=conn.prepareStatement("update company set password=?,name=?,address=?,weiid=?,director=?,phone=?,description=? where account=?");
			ps.setString(1, company.getPassword());
			ps.setString(2, company.getName());
			ps.setString(3, company.getAddress());
			ps.setString(4, company.getWeiid());
			ps.setString(5, company.getDirector());
			ps.setString(6, company.getPhone());
			ps.setString(7, company.getDescription());
			ps.setString(8, company.getAccount());
			ps.executeUpdate();
			message.setFail(0);
			message.setMessage("更新企业信息成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setFail(1);
			message.setMessage("更新企业信息失败，未知错误！");
		}
		return message;
	}
	
	/**
	 * 查询企业信息
	 * @param account
	 * @return
	 */
	public Company queryCompany(String account){
		Company company = new Company();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from company where account=?");
			ps.setString(1, account);
			ResultSet rs=ps.executeQuery();
			if (rs.next()){
				//company.setId(rs.getInt(1));
				company.setAccount(rs.getString(2));
				company.setPassword(rs.getString(3));
				company.setName(rs.getString(4));
				company.setAddress(rs.getString(5));
				company.setWeiid(rs.getString(6));
				company.setDirector(rs.getString(7));
				company.setPhone(rs.getString(8));
				company.setDescription(rs.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}
	
	public static void main(String[] args){
		CompanyDAO cd = new CompanyDAO();
		Company c1 = new Company("google","123","google","USA","google","CEO","123456","google company");
		ReturnMessage rm = cd.addCompany(c1);
		System.out.println(rm.getMessage());
		
//		Company c2 = c1;
//		c2.setPhone("654321");
//		ReturnMessage rm = cd.updateCompany(c2);
//		System.out.println(rm.getMessage());
		
//		Company c = cd.queryCompany("google");
//		if(c != null){
//			System.out.println(c.getDescription());
//		}
		
//		ReturnMessage rm = cd.deleteCompany("google");
//		System.out.println(rm.getMessage());
	}
}
