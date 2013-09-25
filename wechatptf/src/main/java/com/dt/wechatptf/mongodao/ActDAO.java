package com.dt.wechatptf.mongodao;

import com.dt.wechatptf.entity.Activity;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ActDAO extends BaseDAO{

	public ActDAO() {
		super("activity");
	}
	
	/**
	 * 添加活动
	 * @param act
	 * @return
	 */
	public ReturnMessage addActivity(Activity act){
		ReturnMessage msg = new ReturnMessage();
		try {
			String actid = UUIDUtil.genUUID();
			this.dc.save(new BasicDBObject("id", actid)
				.append("weiid", act.getWeiid())
				.append("companyid", act.getCompanyid())
				.append("type", act.getType())
				.append("goodsid", act.getGoodsid())
				.append("time", act.getTime())
				.append("brief", act.getBrief())
				.append("detail", act.getDetail())
				.append("name", act.getName())
				.append("password", act.getPassword()));
			msg.setFail(0);
			msg.setMessage("添加活动成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加活动失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除活动
	 * @param weiid
	 * @param type
	 * @param goodsid
	 * @param time
	 * @return
	 */
	public ReturnMessage deleteActivity(String weiid, int type, String goodsid, String time){
		ReturnMessage msg = new ReturnMessage();
		String actid = this.queryActId(weiid, type, goodsid, time);
		if(actid != null){
			try {
				SignUpDAO sud = new SignUpDAO();
				msg = sud.deleteSignUp(actid);
				if(msg.getFail() == 0){
					this.dc.remove(new BasicDBObject("id", actid));
					msg.setMessage("删除活动成功！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg.setFail(1);
				msg.setMessage("删除活动失败，未知错误！");
			}
		}
		else{
			msg.setFail(1);
			msg.setMessage("删除活动失败，该活动不存在！");
		}
		return msg;
	}
	
	/**
	 * 查询活动id
	 * @param weiid
	 * @param type
	 * @param goodsid
	 * @param time
	 * @return
	 */
	public String queryActId(String weiid, int type, String goodsid, String time){
		String actid = null;
		DBObject o = this.dc.findOne(new BasicDBObject("weiid", weiid)
			.append("type", type)
			.append("goodsid", goodsid)
			.append("time", time));
		actid = (String)o.get("id");
		return actid;
	}
	
	/**
	 * 更新活动信息
	 * @param act
	 * @return
	 */
	public ReturnMessage updateActivity(Activity act){
		ReturnMessage msg = new ReturnMessage();
		DBObject o = this.dc.findOne(new BasicDBObject("id", act.getId()));
		o.put("brief", act.getBrief());
		o.put("detail", act.getDetail());
		try {
			this.dc.update(new BasicDBObject("id", act.getId()), o);
			msg.setFail(0);
			msg.setMessage("更新活动信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("更新活动信息失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 查询活动信息
	 * @param weiid
	 * @param type
	 * @param goodsid
	 * @param time
	 * @return
	 */
	public Activity queryActivity(String weiid, int type, String goodsid, String time){
		Activity act = new Activity();
		DBObject o = this.dc.findOne(new BasicDBObject("weiid", weiid)
			.append("type", type)
			.append("goodsid", goodsid)
			.append("time", time));
		act.setId((String)o.get("id"));
		act.setWeiid((String)o.get("weiid"));
		act.setCompanyid((String)o.get("companyid"));
		act.setType((Integer)o.get("type"));
		act.setGoodsid((String)o.get("goodsid"));
		act.setTime((String)o.get("time"));
		act.setBrief((String)o.get("brief"));
		act.setDetail((String)o.get("detail"));
		act.setName((String)o.get("name"));
		act.setPassword((String)o.get("password"));
		return act;
	}
	
	public static void main(String[] args){
		ActDAO ad = new ActDAO();
		
		Activity a = new Activity("mlr","1",0,"1",System.currentTimeMillis()+"","sweet ktv","Just you and me","Cassie","199139");
		ReturnMessage rm = ad.addActivity(a);
		System.out.println(rm.getMessage());
		
//		a.setId("3b2cb210c59e55d9ea058262f4e2eb16214bb26c");
//		a.setDetail("Let's have fun");
//		ReturnMessage rm = ad.updateActivity(a);
//		System.out.println(rm.getMessage());
		
//		Activity a = ad.queryActivity("mlr", 0, "1", "1379226889567");
//		System.out.println(a.getBrief());
		
//		ReturnMessage rm = ad.deleteActivity("mlr", 0, "1", "1379226889567");
//		System.out.println(rm.getMessage());
	}

}
