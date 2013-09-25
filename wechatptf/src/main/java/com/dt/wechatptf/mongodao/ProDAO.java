package com.dt.wechatptf.mongodao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.dt.wechatptf.entity.Graphic;
import com.dt.wechatptf.entity.Member;
import com.dt.wechatptf.entity.Product;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ProDAO extends BaseDAO{

	public ProDAO() {
		super("product");
	}
	
	/**
	 * 添加商品
	 * @param product
	 * @param companyid
	 * @return
	 */
	public ReturnMessage addProduct(Product product, String companyid){
		ReturnMessage msg = new ReturnMessage();
		try {
			String proid = UUIDUtil.genUUID();
			this.dc.save(new BasicDBObject("id", proid)
				.append("companyid", companyid)
				.append("name", product.getName())
				.append("price", product.getPrice())
				.append("description", product.getDescription())
				.append("cover", product.getCover())
				.append("start_date", product.getStart_date())
				.append("end_date", product.getEnd_date())
				.append("point", product.getPoint())
				.append("stock", product.getStock())
				.append("sale", product.getSale()));
			
			GraphicDAO gd = new GraphicDAO();
			for(int i=0; i<product.getGraphics().size(); i++){
				msg = gd.addGraphic(proid, product.getGraphics().get(i));
				if (msg.getFail() == 1){
					break;
				}
			}
			if(msg.getFail() == 0){
				msg.setMessage("添加商品成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加商品失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除商品
	 * @param productid
	 * @return
	 */
	public ReturnMessage deleteProduct(String productid){
		ReturnMessage msg = new ReturnMessage();
		try {
			GraphicDAO gd = new GraphicDAO();
			msg = gd.deleteGrasOfPro(productid);
			if(msg.getFail() == 0){
				this.dc.remove(new BasicDBObject("id", productid));
				msg.setMessage("删除商品成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除商品失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 更新商品信息
	 * @param pro
	 * @return
	 */
	public ReturnMessage updateProduct(Product pro){
		ReturnMessage msg = new ReturnMessage();
		DBObject o = this.dc.findOne(new BasicDBObject("id", pro.getId()));
		o.put("name", pro.getName());
		o.put("price", pro.getPrice());
		o.put("description", pro.getDescription());
		o.put("start_date", pro.getStart_date());
		o.put("end_date", pro.getEnd_date());
		o.put("point", pro.getPoint());
		o.put("stock", pro.getStock());
		long today = System.currentTimeMillis();
		if(today > pro.getStart_date().getTime()){
			o.put("sale", 1);
		}
		else{
			o.put("sale", 0);
		}
		try {
			this.dc.update(new BasicDBObject("id", pro.getId()), o);
			msg.setFail(0);
			msg.setMessage("更新商品信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("更新商品信息失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 更新商品上下架信息
	 * @param proid
	 * @param sale
	 * @return
	 */
	public ReturnMessage updateSale(String proid, int sale){
		ReturnMessage msg = new ReturnMessage();
		DBObject o = this.dc.findOne(new BasicDBObject("id", proid));
		o.put("sale", sale);
		long today = System.currentTimeMillis();
		Date d = new Date(today);
		if(sale == 0){				//手动下架，把结束日期改为当前日期
			o.put("end_date", d);
		}
		else{						//手动上架，把开始日期改为当前日期
			o.put("start_date", d);
		}
		try {
			this.dc.update(new BasicDBObject("id", proid), o);
			msg.setFail(0);
			msg.setMessage("更新上下架信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("更新上下架信息失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 根据商品id查询商品
	 * @param productid
	 * @return
	 */
	public Product queryProduct(String productid){
		Product pro = new Product();
		DBObject o = this.dc.findOne(new BasicDBObject("id", productid));
		pro.setId((String)o.get("id"));
		pro.setName((String)o.get("name"));
		pro.setPrice((Double)o.get("price"));
		pro.setDescription((String)o.get("description"));
		pro.setCover((String)o.get("cover"));
		pro.setStart_date((Date)o.get("start_date"));
		pro.setEnd_date((Date)o.get("end_date"));
		pro.setPoint((Integer)o.get("point"));
		pro.setStock((Integer)o.get("stock"));
		pro.setSale((Integer)o.get("sale"));
		GraphicDAO gd = new GraphicDAO();
		pro.setGraphics(gd.queryGraphic(productid));
		return pro;
	}
	
	/**
	 * 查询某个商家的所有商品
	 * @param companyid
	 * @return
	 */
	public ArrayList<Product> queryAllPro(String companyid){
		ArrayList<Product> pros = new ArrayList<Product>();
		DBCursor cur = this.dc.find(new BasicDBObject("companyid", companyid));
		while(cur != null && cur.hasNext()){
			DBObject o = cur.next();
			String proid = (String)o.get("id");
			Product pro = this.queryProduct(proid);
			pros.add(pro);
		}
		return pros;
	}
	
	public static void main(String[] args){
		ProDAO pd = new ProDAO();
		
//		Calendar c1 = Calendar.getInstance();
//		c1.set(Calendar.YEAR, 2013);
//		c1.set(Calendar.MONTH, 8);	//month从0开始
//		c1.set(Calendar.DAY_OF_MONTH, 25);
//		Date sd = c1.getTime();
//		Calendar c2 = c1;
//		c2.set(Calendar.DAY_OF_MONTH, 30);
//		Date ed = c2.getTime();
//		ArrayList<Graphic> gras = new ArrayList<Graphic>();
//		Graphic g1 = new Graphic("/img/1/1.jpg","欧美新潮流");
//		Graphic g2 = new Graphic("/img/1/2.jpg","多色可选");
//		gras.add(g1);
//		gras.add(g2);
//		Product p =new Product("长靴",229,"2013新款","/cover/1.jpg",sd,ed,229,100,gras);
//		ReturnMessage rm = pd.addProduct(p, "57f29c5a6da59a269c7abe06790ac9b9e196a934");
//		System.out.println(rm.getMessage());
		
//		Product p2 =p;
//		p2.setId("3f1e3f7c05b4f8c0e6c4cbe301983d58fc652772");
//		p2.setStart_date(sd);
//		ReturnMessage rm = pd.updateProduct(p2);
//		System.out.println(rm.getMessage());
		
//		ReturnMessage rm = pd.updateSale("3f1e3f7c05b4f8c0e6c4cbe301983d58fc652772", 1);
//		System.out.println(rm.getMessage());
		
//		Product p = pd.queryProduct("3f1e3f7c05b4f8c0e6c4cbe301983d58fc652772");
//		System.out.println(p.getEnd_date());
		
//		ArrayList<Product> pros = pd.queryAllPro("57f29c5a6da59a269c7abe06790ac9b9e196a934");
//		for(int i=0; i<pros.size(); i++){
//			System.out.println(pros.get(i).getName());
//		}
		
//		ReturnMessage rm = pd.deleteProduct("5a3ae5f606de6684f8daca247b30abfff47723db");
//		System.out.println(rm.getMessage());
	}

}
