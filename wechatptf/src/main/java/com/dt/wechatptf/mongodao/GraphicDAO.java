package com.dt.wechatptf.mongodao;

import java.util.ArrayList;

import com.dt.wechatptf.entity.Graphic;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class GraphicDAO extends BaseDAO{

	public GraphicDAO() {
		super("graphic");
	}
	
	/**
	 * 添加图文描述
	 * @param productid
	 * @param gra
	 * @return
	 */
	public ReturnMessage addGraphic(String productid, Graphic gra){
		ReturnMessage msg = new ReturnMessage();
		try {
			String graid = UUIDUtil.genUUID();
			this.dc.save(new BasicDBObject("id", graid)
				.append("productid", productid)
				.append("picture", gra.getPicture())
				.append("description", gra.getDescription()));
			msg.setFail(0);
			msg.setMessage("添加图文描述成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加图文描述失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除图文描述
	 * @param graphicid
	 * @return
	 */
	public ReturnMessage deleteGraphic(String graphicid){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.remove(new BasicDBObject("id", graphicid));
			msg.setFail(0);
			msg.setMessage("删除图文描述成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除图文描述失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除指定商品的所有图文描述
	 * @param productid
	 * @return
	 */
	public ReturnMessage deleteGrasOfPro(String productid){
		ReturnMessage msg = new ReturnMessage();
		try {
			this.dc.remove(new BasicDBObject("productid", productid));
			msg.setFail(0);
			msg.setMessage("删除指定商品的所有图文描述成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除指定商品的所有图文描述失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 更新图片描述
	 * @param graid
	 * @param des
	 * @return
	 */
	public ReturnMessage updateDescription(String graid, String des){
		ReturnMessage msg = new ReturnMessage();
		DBObject o = this.dc.findOne(new BasicDBObject("id", graid));
		o.put("description", des);
		try {
			this.dc.update(new BasicDBObject("id", graid), o);
			msg.setFail(0);
			msg.setMessage("更新图片描述成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("更新图片描述失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 查询某个商品的所有图文描述
	 * @param productid
	 * @return
	 */
	public ArrayList<Graphic> queryGraphic(String productid){
		ArrayList<Graphic> gras = new ArrayList<Graphic>();
		DBCursor cur = this.dc.find(new BasicDBObject("productid", productid));
		while(cur != null && cur.hasNext()){
			DBObject o = cur.next();
			Graphic gra = new Graphic();
			gra.setId((String)o.get("id"));
			gra.setPicture((String)o.get("picture"));
			gra.setDescription((String)o.get("description"));
			gras.add(gra);
		}
		return gras;
	}

}
