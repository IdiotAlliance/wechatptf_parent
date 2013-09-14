package com.dt.wechatptf.mongodao;

import com.dt.wechatptf.entity.Member;
import com.dt.wechatptf.util.ReturnMessage;

public class MemDAO extends BaseDAO{

	public MemDAO() {
		super("member");
		// TODO Auto-generated constructor stub
	}
	
	public ReturnMessage addMember(Member member, int companyid){
		ReturnMessage msg = new ReturnMessage();
		return msg;
	}

}
