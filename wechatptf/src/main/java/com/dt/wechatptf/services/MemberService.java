package com.dt.wechatptf.services;

import java.sql.Date;

public interface MemberService {
	
	public String addMember(String weiid, String name, int gender, Date birthday, 
			String address, String mail, String phone, int companyid);
	public String deleteMember(String weiid, int companyid);
	public String updateMember(String weiid, String name, int gender, Date birthday, 
			String address, String mail, String phone, int companyid);
	public String queryMember(String weiid, int companyid);
	public int queryPoints(String weiid, int companyid);
	public String updatePoints(String weiid, int companyid, int addPoints);

}
