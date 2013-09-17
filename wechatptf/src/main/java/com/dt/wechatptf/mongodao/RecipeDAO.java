package com.dt.wechatptf.mongodao;

import java.util.ArrayList;

import com.dt.wechatptf.entity.Recipe;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class RecipeDAO extends BaseDAO{

	public RecipeDAO() {
		super("recipe");
	}
	
	/**
	 * 添加菜谱
	 * @param rcp
	 * @return
	 */
	public ReturnMessage addRecipe(Recipe rcp){
		ReturnMessage msg = new ReturnMessage();
		try {
			String rcpid = UUIDUtil.genUUID();
			this.dc.save(new BasicDBObject("id", rcpid)
				.append("type", rcp.getType())
				.append("name", rcp.getName())
				.append("picture", rcp.getPicture())
				.append("price", rcp.getPrice())
				.append("weight", rcp.getWeight())
				.append("mainMaterial", rcp.getMainMaterial())
				.append("assistantMaterial", rcp.getAssistantMaterial())
				.append("making", rcp.getMaking()));
			msg.setFail(0);
			msg.setMessage("添加菜谱成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加菜谱失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除菜谱
	 * @param id
	 * @return
	 */
	public ReturnMessage deleteRecipe(String id){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.remove(new BasicDBObject("id", id));
			msg.setFail(0);
			msg.setMessage("删除菜谱成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除菜谱失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 更新菜谱信息
	 * @param rcp
	 * @return
	 */
	public ReturnMessage updateRecipe(Recipe rcp){
		ReturnMessage msg = new ReturnMessage();
		DBObject o = this.dc.findOne(new BasicDBObject("id", rcp.getId()));
		o.put("name", rcp.getName());
		o.put("price", rcp.getPrice());
		o.put("weight", rcp.getWeight());
		o.put("mainMaterial", rcp.getMainMaterial());
		o.put("assistantMaterial", rcp.getAssistantMaterial());
		o.put("making", rcp.getMaking());
		try {
			this.dc.update(new BasicDBObject("id", rcp.getId()), o);
			msg.setFail(0);
			msg.setMessage("更新菜谱信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("更新菜谱信息失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 查询某个菜谱的具体信息
	 * @param id
	 * @return
	 */
	public Recipe queryRecipe(String id){
		Recipe rcp = new Recipe();
		DBObject o = this.dc.findOne(new BasicDBObject("id", id));
		rcp.setId((String)o.get("id"));
		rcp.setType((Integer)o.get("type"));
		rcp.setName((String)o.get("name"));
		rcp.setPicture((String)o.get("picture"));
		rcp.setPrice((Double)o.get("price"));
		rcp.setWeight((Integer)o.get("weight"));
		rcp.setMainMaterial((String)o.get("mainMaterial"));
		rcp.setAssistantMaterial((String)o.get("assistantMaterial"));
		rcp.setMaking((String)o.get("making"));
		return rcp;
	}
	
	/**
	 * 查询某个菜谱的主要信息，供购物车、订单中展示
	 * @param id
	 * @return
	 */
	public Recipe querySimpleRcp(String id){
		Recipe rcp = new Recipe();
		DBObject o = this.dc.findOne(new BasicDBObject("id", id));
		rcp.setId((String)o.get("id"));
		rcp.setName((String)o.get("name"));
		rcp.setPicture((String)o.get("picture"));
		rcp.setPrice((Double)o.get("price"));
		rcp.setWeight((Integer)o.get("weight"));
		return rcp;
	}
	
	/**
	 * 查询某一类型的所有菜谱
	 * @param type
	 * @return
	 */
	public ArrayList<Recipe> queryRcpByType(int type){
		ArrayList<Recipe> rcps = new ArrayList<Recipe>();
		DBCursor cur = this.dc.find(new BasicDBObject("type", type));
		while(cur != null && cur.hasNext()){
			DBObject o = cur.next();
			Recipe rcp = new Recipe();
			rcp.setId((String)o.get("id"));
			rcp.setName((String)o.get("name"));
			rcp.setPicture((String)o.get("picture"));
			rcp.setPrice((Double)o.get("price"));
			rcp.setWeight((Integer)o.get("weight"));
			rcps.add(rcp);
		}
		return rcps;
	}
	
	public static void main(String[] args){
		RecipeDAO rd = new RecipeDAO();
		
//		Recipe r = new Recipe(1,"宫保鸡丁","/recipe/1/宫保鸡丁.jpg",15,2,"鸡肉、黄瓜、胡萝卜、花生",
//				"干辣椒、盐、鸡精、豆瓣酱、葱、糖、姜、花椒","1.锅内入少量油，小火炒豆瓣酱，炒出红油； "+
//						"2.放入黄瓜丁、胡萝卜丁略炒； "+"3.放入花生翻炒片刻楚国即可");
//		ReturnMessage rm = rd.addRecipe(r);
//		System.out.println(rm.getMessage());
		
		Recipe r = new Recipe(0,"青椒土豆丝","/recipe/0/青椒土豆丝.jpg",8,2,"土豆、青椒",
				"红辣椒根、盐、鸡精","1.把土豆丝泡在水里，备用； 2.起锅入油，倒土豆丝与青椒");
		ReturnMessage rm = rd.addRecipe(r);
		System.out.println(rm.getMessage());
		
//		Recipe r2 = r;
//		r2.setId("6d50d43933c275c1bf2d1bee0fa32bd3fabcfee6");
//		r2.setPrice(18);
//		ReturnMessage rm = rd.updateRecipe(r2);
//		System.out.println(rm.getMessage());
		
//		Recipe r = rd.queryRecipe("6d50d43933c275c1bf2d1bee0fa32bd3fabcfee6");
//		System.out.println(r.getMainMaterial());
		
//		ArrayList<Recipe> rs = rd.queryRcpByType(1);
//		for(int i=0; i<rs.size(); i++){
//			System.out.println(rs.get(i).getPrice());
//		}
		
//		ReturnMessage rm = rd.deleteRecipe("6d50d43933c275c1bf2d1bee0fa32bd3fabcfee6");
//		System.out.println(rm.getMessage());
	}

}
