package com.dt.wechatptf.services;

public interface CompanyService {
	
	public String addCompany(String account, String password, String name, String address, 
			String weiid, String director, String phone, String description);
	public String deleteCompany(String account);
	public String updateCompany(String account, String password, String name, String address, 
			String weiid, String director, String phone, String description);
	public String queryCompany(String account);
	public String queryMembers(String account);

}
