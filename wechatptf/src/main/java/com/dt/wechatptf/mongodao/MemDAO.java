package com.dt.wechatptf.mongodao;

import java.util.Calendar;
import java.util.Date;

import com.dt.wechatptf.entity.Member;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MemDAO extends BaseDAO{

	public MemDAO() {
		super("member");
	}
	
	/**
	 * 添加会员
	 * @param member
	 * @param companyid
	 * @return
	 */
	public ReturnMessage addMember(Member member, String companyid){
		ReturnMessage msg = new ReturnMessage();
		try {
			String memberid = UUIDUtil.genUUID();
			this.dc.save(new BasicDBObject("id", memberid)
				.append("weiid", member.getWeiid())
				.append("name", member.getName())
				.append("gender", member.getGender())
				.append("birthday", member.getBirthday())
				.append("address", member.getAddress())
				.append("mail", member.getMail())
				.append("phone", member.getPhone())
				.append("points", member.getPoints()));
			
			MemComDAO mcd = new MemComDAO();
			msg = mcd.addMemToCom(memberid, member.getWeiid(), companyid);
			if(msg.getFail() == 0){
				msg.setMessage("添加会员成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加会员失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除会员
	 * @param weiid
	 * @param companyid
	 * @return
	 */
	public ReturnMessage deleteMember(String weiid, String companyid){
		ReturnMessage msg = new ReturnMessage();
		MemComDAO mcd = new MemComDAO();
		String memberid = mcd.queryMemberid(weiid, companyid);
		try {
			this.dc.remove(new BasicDBObject("id", memberid));
			
			msg = mcd.deleteMemFromCom(memberid);
			if(msg.getFail() == 0){
				msg.setMessage("删除会员成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除会员失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 更新会员信息
	 * @param member
	 * @param companyid
	 * @return
	 */
	public ReturnMessage updateMember(Member member, String companyid){
		ReturnMessage msg = new ReturnMessage();
		MemComDAO mcd = new MemComDAO();
		String memberid = mcd.queryMemberid(member.getWeiid(), companyid);
		DBObject o = this.dc.findOne(new BasicDBObject("id", memberid));
		o.put("name", member.getName());
		o.put("gender", member.getGender());
		o.put("birthday", member.getBirthday());
		o.put("address", member.getAddress());
		o.put("mail", member.getMail());
		o.put("phone", member.getPhone());
		try {
			this.dc.update(new BasicDBObject("id", memberid), o);
			msg.setFail(0);
			msg.setMessage("更新会员信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("更新会员信息失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 根据weiid和companyid查询会员信息
	 * @param weiid
	 * @param companyid
	 * @return
	 */
	public Member queryMember(String weiid, String companyid){
		Member member = new Member();
		MemComDAO mcd = new MemComDAO();
		String memberid = mcd.queryMemberid(weiid, companyid);
		member = this.queryMemById(memberid);
		return member;
	}
	
	/**
	 * 根据memberid查询会员信息
	 * @param memberid
	 * @return
	 */
	public Member queryMemById(String memberid){
		Member member = new Member();
		DBObject o = this.dc.findOne(new BasicDBObject("id", memberid));
		member.setId((String)o.get("id"));
		member.setWeiid((String)o.get("weiid"));
		member.setName((String)o.get("name"));
		member.setGender((Integer)o.get("gender"));
		member.setBirthday((Date)o.get("birthday"));
		member.setAddress((String)o.get("address"));
		member.setMail((String)o.get("mail"));
		member.setPhone((String)o.get("phone"));
		member.setPoints((Integer)o.get("points"));
		return member;
	}
	
	public static void main(String[] args){
		MemDAO md = new MemDAO();

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 1992);
		c.set(Calendar.MONTH, 0);	//month从0开始
		c.set(Calendar.DAY_OF_MONTH, 12);
		long birthday = c.getTimeInMillis();
		Member m = new Member("mlr","Cassie",1,new Date(birthday),"上海","909103970@qq.com",
				"15921575193",0);
		ReturnMessage rm = md.addMember(m, "57f29c5a6da59a269c7abe06790ac9b9e196a934");
		System.out.println(rm.getMessage());
		
//		ReturnMessage rm = md.deleteMember("lx", "1");
//		System.out.println(rm.getMessage());
		
//		Member m = new Member();
//		m.setWeiid("mlr");
//		m.setMail("mlr09@software.nju.edu.cn");
//		ReturnMessage rm = md.updateMember(m, "1");
//		System.out.println(rm.getMessage());
		
//		Member m = md.queryMember("mlr", "1");
//		System.out.println(m.getId());
//		System.out.println(m.getMail());
	}

}
