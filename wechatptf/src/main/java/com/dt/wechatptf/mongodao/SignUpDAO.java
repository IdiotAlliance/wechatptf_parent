package com.dt.wechatptf.mongodao;

import java.util.ArrayList;

import com.dt.wechatptf.entity.SignUp;
import com.dt.wechatptf.util.ReturnMessage;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class SignUpDAO extends BaseDAO{

	public SignUpDAO() {
		super("signup");
	}
	
	/**
	 * 报名活动
	 * @param weiid
	 * @param type
	 * @param goodsid
	 * @param time
	 * @param name
	 * @param phone
	 * @return
	 */
	public ReturnMessage addSignUp(String weiid, int type, String goodsid, String time, 
			String name, String phone){
		ReturnMessage msg = new ReturnMessage();
		ActDAO ad = new ActDAO();
		String actid = ad.queryActId(weiid, type, goodsid, time);
		if(actid != null){
			try {
				this.dc.save(new BasicDBObject("activityid", actid)
					.append("name", name)
					.append("phone", phone));
				msg.setFail(0);
				msg.setMessage("报名活动成功！");
			} catch (Exception e) {
				e.printStackTrace();
				msg.setFail(1);
				msg.setMessage("报名活动失败，未知错误！");
			}
		}
		else{
			msg.setFail(1);
			msg.setMessage("报名活动失败，该活动不存在！");
		}
		return msg;
	}
	
	/**
	 * 删除某活动的所有报名信息
	 * @param actid
	 * @return
	 */
	public ReturnMessage deleteSignUp(String actid){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.remove(new BasicDBObject("activityid", actid));
			msg.setFail(0);
			msg.setMessage("删除报名活动成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除报名活动失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 查询报名某活动的总人数
	 * @param weiid
	 * @param type
	 * @param goodsid
	 * @param time
	 * @return
	 */
	public int queryNum(String weiid, int type, String goodsid, String time){
		int num = 0;
		ActDAO ad = new ActDAO();
		String actid = ad.queryActId(weiid, type, goodsid, time);
		DBCursor cur = this.dc.find(new BasicDBObject("activityid", actid));
		num = cur.length();
		return num;
	}
	
	/**
	 * 查询报名某活动的人的详细信息
	 * @param weiid
	 * @param type
	 * @param goodsid
	 * @param time
	 * @return
	 */
	public ArrayList<SignUp> querySignUp(String weiid, int type, String goodsid, String time){
		ArrayList<SignUp> signs = new ArrayList<SignUp>();
		ActDAO ad = new ActDAO();
		String actid = ad.queryActId(weiid, type, goodsid, time);
		DBCursor cur = this.dc.find(new BasicDBObject("activityid", actid));
		while(cur != null && cur.hasNext()){
			DBObject o = cur.next();
			SignUp sign = new SignUp();
			sign.setActivityid((String)o.get("activityid"));
			sign.setName((String)o.get("name"));
			sign.setPhone((String)o.get("phone"));
			signs.add(sign);
		}
		return signs;
	}
	
	public static void main(String[] args){
		SignUpDAO sud = new SignUpDAO();
		
		ReturnMessage rm1 = sud.addSignUp("mlr", 0, "1", "1379227186437", "Cassie", "15921575193");
		System.out.println(rm1.getMessage());
		ReturnMessage rm2 = sud.addSignUp("mlr", 0, "1", "1379227186437", "Harry", "15298387956");
		System.out.println(rm2.getMessage());
		
//		int num = sud.queryNum("mlr", 0, "1", "1379227186437");
//		System.out.println(num);
		
//		ArrayList<SignUp> signs = sud.querySignUp("mlr", 0, "1", "1379227186437");
//		for(int i=0; i<signs.size(); i++){
//			System.out.println(signs.get(i).getName()+"   "+signs.get(i).getPhone());
//		}
		
//		ReturnMessage rm = sud.deleteSignUp("4e482b0015c355d055cd8f8a1708c8b7b937f8b3");
//		System.out.println(rm.getMessage());
	}

}
