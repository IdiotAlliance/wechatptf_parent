package com.dt.wechatptf.mongodao;

import com.dt.wechatptf.entity.Company;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ComDAO extends BaseDAO{

	final MemComDAO mcd = new MemComDAO();
	
	public ComDAO() {
		super("company");
	}
	
	/**
	 * 添加商家
	 * @param company
	 * @return
	 */
	public ReturnMessage addCompany(Company company){
		ReturnMessage msg = new ReturnMessage();
		try {
			String companyid = UUIDUtil.genUUID();
			this.dc.save(new BasicDBObject("id", companyid)
				.append("account", company.getAccount())
				.append("password", company.getPassword())
				.append("name", company.getName())
				.append("address", company.getAddress())
				.append("weiid", company.getWeiid())
				.append("director", company.getDirector())
				.append("phone", company.getPhone())
				.append("description", company.getDescription()));
			msg.setFail(0);
			msg.setMessage("添加商家成功！");
		}catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("添加商家失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 删除商家
	 * @param account
	 * @return
	 */
	public ReturnMessage deleteCompany(String account){
		ReturnMessage msg = new ReturnMessage();
		DBObject o = this.dc.findOne(new BasicDBObject("account", account));
		String companyid = (String)o.get("id");
		try {
			this.dc.remove(new BasicDBObject("id", companyid));
			
			msg = mcd.deleteMemInCom(companyid);
			if(msg.getFail() == 0){
				msg.setMessage("删除商家成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("删除商家失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 更新商家信息
	 * @param company
	 * @return
	 */
	public ReturnMessage updateCompany(Company company){
		ReturnMessage msg = new ReturnMessage();
		DBObject o = this.dc.findOne(new BasicDBObject("account", company.getAccount()));
		o.put("password", company.getPassword());
		o.put("name", company.getName());
		o.put("address", company.getAddress());
		o.put("weiid", company.getWeiid());
		o.put("director", company.getDirector());
		o.put("phone", company.getPhone());
		o.put("description", company.getDescription());
		try {
			this.dc.update(new BasicDBObject("account", company.getAccount()), o);
			msg.setFail(0);
			msg.setMessage("更新商家信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFail(1);
			msg.setMessage("更新商家信息失败，未知错误！");
		}
		return msg;
	}
	
	/**
	 * 查询商家信息
	 * @param account
	 * @return
	 */
	public Company queryCompany(String account){
		Company company = new Company();
		DBObject o = this.dc.findOne(new BasicDBObject("account", account));
		company.setId((String)o.get("id"));
		company.setAccount((String)o.get("account"));
		company.setPassword((String)o.get("password"));
		company.setName((String)o.get("name"));
		company.setAddress((String)o.get("address"));
		company.setWeiid((String)o.get("weiid"));
		company.setDirector((String)o.get("director"));
		company.setPhone((String)o.get("phone"));
		company.setDescription((String)o.get("description"));
		return company;
	}
	
	/**
	 * 根据account查询companyid
	 * @param account
	 * @return
	 */
	public String queryCompanyid(String account){
		String companyid = null;
		DBObject o = this.dc.findOne(new BasicDBObject("account", account));
		companyid = (String)o.get("id");
		return companyid;
	}
	
	public static void main(String[] args){
		ComDAO cd = new ComDAO();
		
		Company c1 = new Company("google_acc","google_pass","google","USA","google_wei","Larry","123456789","We do the best");
		ReturnMessage rm = cd.addCompany(c1);
		System.out.println(rm.getMessage());
		
//		Company c2 = c1;
//		c2.setDescription("Google Company");
//		ReturnMessage rm = cd.updateCompany(c2);
//		System.out.println(rm.getMessage());
		
//		Company c = cd.queryCompany("google_acc");
//		if(c != null){
//			System.out.println(c.getDescription());
//		}
		
//		ReturnMessage rm = cd.deleteCompany("google_acc");
//		System.out.println(rm.getMessage());
	}

}
