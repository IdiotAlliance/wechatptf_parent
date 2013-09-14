package com.dt.wechatptf.mongodao;

import java.util.ArrayList;

import com.dt.wechatptf.entity.Member;
import com.dt.wechatptf.util.ReturnMessage;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MemComDAO extends BaseDAO{
	
	public MemComDAO() {
		super("member_company");
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 添加会员至商家
	 * @param memberid
	 * @param weiid
	 * @param companyid
	 * @return
	 */
	public ReturnMessage addMemToCom(String memberid, String weiid, String companyid){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.save(new BasicDBObject("memberid", memberid)
				.append("weiid", weiid)
				.append("companyid", companyid)
				.append("points", 0));
			msg.setFail(0);
			msg.setMessage("添加会员至商家成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加会员至商家失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 根据weiid和companyid查询memberid
	 * @param weiid
	 * @param companyid
	 * @return
	 */
	public String queryMemberid(String weiid, String companyid){
		String memberid = null;
		DBObject o = this.dc.findOne(new BasicDBObject("weiid", weiid)
			.append("companyid", companyid));
		memberid = (String)o.get("memberid");
		return memberid;
	}
	
	/**
	 * 从商家删除会员
	 * @param memberid
	 * @return
	 */
	public ReturnMessage deleteMemFromCom(String memberid){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.remove(new BasicDBObject("memberid", memberid));
			msg.setFail(0);
			msg.setMessage("从商家删除会员成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("从商家删除会员失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除某商家所有会员
	 * @param companyid
	 * @return
	 */
	public ReturnMessage deleteMemInCom(String companyid){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.remove(new BasicDBObject("companyid", companyid));
			msg.setFail(0);
			msg.setMessage("删除商家所有会员成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除商家所有会员失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 查询某个商家的所有会员信息
	 * @param account
	 * @return
	 */
	public ArrayList<Member> queryMembers(String account){
		ArrayList<Member> mems = new ArrayList<Member>();
		ComDAO cd = new ComDAO();
		String companyid = cd.queryCompanyid(account);
		DBCursor cur = this.dc.find(new BasicDBObject("companyid", companyid));
		while(cur != null && cur.hasNext()){
			DBObject o1 = cur.next();
			String memberid = (String)o1.get("memberid");
			MemDAO md = new MemDAO();
			Member m = md.queryMemById(memberid);
			mems.add(m);
		}
		return mems;
	}
	
	/**
	 * 查询某个商家的某个会员的积分
	 * @param weiid
	 * @param companyid
	 * @return
	 */
	public int queryPoints(String weiid, String companyid){
		int points = 0;
		DBObject o = this.dc.findOne(new BasicDBObject("weiid", weiid)
			.append("companyid", companyid));
		points = (Integer)o.get("points");
		return points;
	}
	
	/**
	 * 更新某个会员在某个商家的积分
	 * @param weiid
	 * @param companyid
	 * @param addPoints
	 * @return
	 */
	public ReturnMessage updatePoints(String weiid, String companyid, int addPoints){
		ReturnMessage msg = new ReturnMessage();
		DBObject o = this.dc.findOne(new BasicDBObject("weiid", weiid)
			.append("companyid", companyid));
		int points = (Integer)o.get("points");
		points = points + addPoints;
		o.put("points", points);
		try {
			this.dc.update(new BasicDBObject("weiid", weiid).append("companyid", companyid), o);
			msg.setFail(0);
			msg.setMessage("更新会员积分成功！该会员目前积分为"+points);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("更新会员积分失败，未知错误！");
		}
		return msg;
	}
	
	public static void main(String[] args){
		MemComDAO mcd = new MemComDAO();
		
//		ArrayList<Member> ms = mcd.queryMembers("google_acc");
//		for(int i=0; i<ms.size(); i++){
//			System.out.println(ms.get(i).getId());
//			System.out.println(ms.get(i).getName());
//		}
		
//		int points = mcd.queryPoints("mlr", "57f29c5a6da59a269c7abe06790ac9b9e196a934");
//		System.out.println(points);
		
		ReturnMessage rm = mcd.updatePoints("mlr", "57f29c5a6da59a269c7abe06790ac9b9e196a934", 30);
		System.out.println(rm.getMessage());
	}

}
