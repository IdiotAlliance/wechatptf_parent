package com.dt.wechatptf.services;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.dt.wechatptf.dao.CompanyDAO;
import com.dt.wechatptf.dao.MemberCompanyDAO;
import com.dt.wechatptf.entity.Company;
import com.google.gson.Gson;

@Path("company")
public class CompanyServiceImpl implements CompanyService {
	
	final CompanyDAO cd = new CompanyDAO();
	final MemberCompanyDAO mcd = new MemberCompanyDAO();
	Gson gson = new Gson();

	@POST
	@Path("/addCompany")
	public String addCompany(@FormParam("account") String account, @FormParam("password") String password,
			@FormParam("name") String name, @FormParam("address") String address, 
			@FormParam("weiid") String weiid, @FormParam("director") String director,
			@FormParam("phone") String phone, @FormParam("description") String description) {
		Company company = new Company(account,password,name,address,weiid,director,phone,description);
		return gson.toJson(cd.addCompany(company));
	}

	@DELETE
	@Path("/deleteCompany/{account}")
	public String deleteCompany(@PathParam("account") String account) {
		return gson.toJson(cd.deleteCompany(account));
	}

	@POST
	@Path("/updateCompany/{account}")
	public String updateCompany(@PathParam("account") String account, @FormParam("password") String password,
			@FormParam("name") String name, @FormParam("address") String address, 
			@FormParam("weiid") String weiid, @FormParam("director") String director,
			@FormParam("phone") String phone, @FormParam("description") String description) {
		Company company = new Company(account,password,name,address,weiid,director,phone,description);
		return gson.toJson(cd.updateCompany(company));
	}

	@GET
	@Path("/queryCompany/{account}")
	public String queryCompany(@PathParam("account") String account) {
		return gson.toJson(cd.queryCompany(account));
	}
	
	@GET
	@Path("/queryMembers/{account}")
	public String queryMembers(@PathParam("account") String account) {
		return gson.toJson(mcd.queryMembers(account));
	}

}
