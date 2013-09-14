package com.dt.wechatptf.mongodao;

import java.util.Date;

import com.dt.wechatptf.entity.Member;
import com.dt.wechatptf.util.ReturnMessage;
import com.mongodb.BasicDBObject;

public class MemDAO extends BaseDAO{

	public MemDAO() {
		super("member");
		// TODO Auto-generated constructor stub
	}
	
	public ReturnMessage addMember(Member member, int companyid){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.save(new BasicDBObject("weiid", member.getWeiid())
				.append("name", member.getName())
				.append("gender", member.getGender())
				.append("birthday", member.getBirthday())
				.append("address", member.getAddress())
				.append("mail", member.getMail())
				.append("phone", member.getPhone())
				.append("points", member.getPoints()));
			msg.setFail(0);
			msg.setMessage("添加会员成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加会员失败，未知错误！");
		}
		return msg;
	}
	
	public static void main(String[] args){
		MemDAO md = new MemDAO();
		
		Member m = new Member();
		m.setWeiid("lx");
		m.setName("Harry");
		m.setBirthday(new Date(1992,0,12));
		ReturnMessage rm = md.addMember(m, 1);
		System.out.println(rm.getMessage());
	}

}
