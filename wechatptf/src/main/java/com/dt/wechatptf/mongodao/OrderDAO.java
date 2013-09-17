package com.dt.wechatptf.mongodao;

import java.util.ArrayList;

import com.dt.wechatptf.entity.Order;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class OrderDAO extends BaseDAO{

	public OrderDAO() {
		super("order");
	}
	
	/**
	 * 提交订单
	 * @param order
	 * @return
	 */
	public ReturnMessage addOrder(Order order){
		ReturnMessage msg = new ReturnMessage();
		try {
			String orderid = UUIDUtil.genUUID();
			this.dc.save(new BasicDBObject("id", orderid)
				.append("weiid", order.getWeiid())
				.append("time", order.getTime())
				.append("rcpids", order.getRcpids())
				.append("name", order.getName())
				.append("phone", order.getPhone())
				.append("address", order.getAddress()));
			
			ShopListDAO sd = new ShopListDAO();
			msg = sd.deleteListOfUser(order.getWeiid());
			if(msg.getFail() == 0){
				msg.setMessage("添加订单成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加订单失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 查询某个用户的所有订单
	 * @param weiid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Order> queryOrders(String weiid){
		ArrayList<Order> odrs = new ArrayList<Order>();
		DBCursor cur = this.dc.find(new BasicDBObject("weiid", weiid));
		while(cur != null && cur.hasNext()){
			DBObject o = cur.next();
			Order odr = new Order();
			odr.setId((String)o.get("id"));
			odr.setWeiid((String)o.get("weiid"));
			odr.setTime((Long)o.get("time"));
			odr.setRcpids((ArrayList<String>)o.get("rcpids"));
			odr.setName((String)o.get("name"));
			odr.setPhone((String)o.get("phone"));
			odr.setAddress((String)o.get("address"));
			odrs.add(odr);
		}
		return odrs;
	}
	
	public static void main(String[] args){
		OrderDAO od = new OrderDAO();
		
//		ShopListDAO sd = new ShopListDAO();
//		Order o = new Order("mlr",System.currentTimeMillis(),sd.queryRcpIds("mlr"),"Cassie",
//				"15921575193","德国中心");
//		ReturnMessage rm = od.addOrder(o);
//		System.out.println(rm.getMessage());
		
		ArrayList<Order> os = od.queryOrders("mlr");
		for(int i=0; i<os.size(); i++){
			System.out.println(os.get(i).getTime());
		}
	}

}
