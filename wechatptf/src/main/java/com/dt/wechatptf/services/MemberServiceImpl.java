package com.dt.wechatptf.services;

import java.sql.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.dt.wechatptf.dao.MemberCompanyDAO;
import com.dt.wechatptf.dao.MemberDAO;
import com.dt.wechatptf.entity.Member;
import com.google.gson.Gson;

@Path("member/{companyid}")
public class MemberServiceImpl implements MemberService {
	
	final MemberDAO md = new MemberDAO();
	final MemberCompanyDAO mcd = new MemberCompanyDAO();
	Gson gson = new Gson();

	@POST
	@Path("/addMember/{weiid}")
	public String addMember(@PathParam("weiid") String weiid, @FormParam("name") String name, 
			@FormParam("gender") int gender, @FormParam("birthday") Date birthday, 
			@FormParam("address") String address, @FormParam("mail") String mail, 
			@FormParam("phone") String phone, @PathParam("companyid") int companyid) {
		Member member = new Member(weiid, name, gender, birthday, address, mail, phone, 0);
		return gson.toJson(md.addMember(member, companyid));
	}

	@DELETE
	@Path("/deleteMember/{weiid}")
	public String deleteMember(@PathParam("weiid") String weiid, @PathParam("companyid") int companyid) {
		return gson.toJson(md.deleteMember(weiid, companyid));
	}

	@POST
	@Path("/updateMember/{weiid}")
	public String updateMember(@PathParam("weiid") String weiid, @FormParam("name") String name, 
			@FormParam("gender") int gender, @FormParam("birthday") Date birthday, 
			@FormParam("address") String address, @FormParam("mail") String mail, 
			@FormParam("phone") String phone, @PathParam("companyid") int companyid) {
		Member member = new Member(weiid, name, gender, birthday, address, mail, phone, 0);
		return gson.toJson(md.updateMember(member, companyid));
	}

	@GET
	@Path("/queryMember/{weiid}")
	public String queryMember(@PathParam("weiid") String weiid, @PathParam("companyid") int companyid) {
		return gson.toJson(md.queryMember(weiid, companyid));
	}
	
	@GET
	@Path("/queryPoints/{weiid}")
	public int queryPoints(@PathParam("weiid") String weiid, @PathParam("companyid") int companyid) {
		return mcd.queryPoints(weiid, companyid);
	}
	
	@POST
	@Path("/updatePoints/{weiid}/{addPoints}")
	public String updatePoints(@PathParam("weiid") String weiid, 
			@PathParam("companyid") int companyid, @PathParam("addPoints") int addPoints) {
		return gson.toJson(mcd.updatePoints(weiid, companyid, addPoints));
	}

}
