package com.dt.wechatptf.mongodao;

import java.util.ArrayList;

import com.dt.wechatptf.entity.Recipe;
import com.dt.wechatptf.entity.ShopList;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ShopListDAO extends BaseDAO{

	public ShopListDAO() {
		super("shoplist");
	}
	
	/**
	 * 添加购物车项
	 * @param list
	 * @return
	 */
	public ReturnMessage addShopList(ShopList list){
		ReturnMessage msg = new ReturnMessage();
		try {
			String listid = UUIDUtil.genUUID();
			this.dc.save(new BasicDBObject("id", listid)
				.append("weiid", list.getWeiid())
				.append("rcpid", list.getRcpid()));
			msg.setFail(0);
			msg.setMessage("添加购物车项成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加购物车项失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除购物车项
	 * @param id
	 * @return
	 */
	public ReturnMessage deleteShopList(String id){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.remove(new BasicDBObject("id", id));
			msg.setFail(0);
			msg.setMessage("删除购物车项成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除购物车项失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除某个用户所有购物车项
	 * @param weiid
	 * @return
	 */
	public ReturnMessage deleteListOfUser(String weiid){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.remove(new BasicDBObject("weiid", weiid));
			msg.setFail(0);
			msg.setMessage("删除用户所有购物车项成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除用户所有购物车项失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 查询购物车信息
	 * @param weiid
	 * @return
	 */
	public ArrayList<Recipe> queryRcpInList(String weiid){
		ArrayList<Recipe> rcps = new ArrayList<Recipe>();
		DBCursor cur = this.dc.find(new BasicDBObject("weiid", weiid));
		while(cur != null && cur.hasNext()){
			DBObject o = cur.next();
			String rcpid = (String)o.get("rcpid");
			RecipeDAO rd = new RecipeDAO();
			Recipe rcp = rd.querySimpleRcp(rcpid);
			rcps.add(rcp);
		}
		return rcps;
	}
	
	/**
	 * 查询某个用户购物车中所有菜谱的id
	 * @param weiid
	 * @return
	 */
	public ArrayList<String> queryRcpIds(String weiid){
		ArrayList<String> rcpids = new ArrayList<String>();
		DBCursor cur = this.dc.find(new BasicDBObject("weiid", weiid));
		while(cur != null && cur.hasNext()){
			DBObject o = cur.next();
			rcpids.add((String)o.get("rcpid"));
		}
		return rcpids;
	}
	
	public static void main(String[] args){
		ShopListDAO sd = new ShopListDAO();
		
//		ShopList sl = new ShopList("mlr", "402041b3dad012af659cc225dffa944c6ce8ec2d");
//		ReturnMessage rm = sd.addShopList(sl);
//		System.out.println(rm.getMessage());
		
		ShopList sl = new ShopList("mlr", "9df71b1fd50a8f037da942ec55f70caf108885ec");
		ReturnMessage rm = sd.addShopList(sl);
		System.out.println(rm.getMessage());
		
//		 ArrayList<Recipe> rs = sd.queryRcpInList("mlr");
//		 for(int i=0; i<rs.size(); i++){
//			 System.out.println(rs.get(i).getName());
//		 }
		
//		ReturnMessage rm = sd.deleteShopList("3a8efb58b0f2897f87cbb0b5b79adf679a0d3edc");
//		System.out.println(rm.getMessage());
	}

}
