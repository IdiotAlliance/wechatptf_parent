package com.dt.wechatptf.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dt.wechatptf.dao.MemberDAO;
import com.dt.wechatptf.entity.Activity;
import com.dt.wechatptf.entity.Member;
import com.dt.wechatptf.mongodao.ActDAO;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.TokenUtil;

@Controller
@RequestMapping(value="wap/{companyid}/{wxid}")
public class WapController extends BaseController{
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView index(@PathVariable("companyid") String cid, 
			@PathVariable("wxid") String wid){
		return new ModelAndView("wap_index");
	}
	
	@RequestMapping(value="/userindex", method=RequestMethod.GET)
	public ModelAndView userIndex(){
		return null;
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public ModelAndView profile(){
		Map<String, String> ds = new HashMap<String, String>();
		ds.put("cid", "123");
		ds.put("wxid", "2345");
		ModelAndView mv = new ModelAndView("wap_profile");
		mv.addAllObjects(ds);
		return mv;
	}
	
	@RequestMapping(value="/bind", method=RequestMethod.POST)
	public ModelAndView bind(HttpServletRequest request, @PathVariable("companyid") int cid, @PathVariable("wxid") String wid, 
			String name, String gender, String year, String month, String day, String address, 
			String mail, String phone){
		
		String uuid = (String) request.getSession().getAttribute(TokenUtil.KEY_UUID);
		
		int gen = Integer.parseInt(gender);
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(day);
		
		Member mem = new Member();
		mem.setWeiid(wid);
		mem.setName(name);
		mem.setGender(gen);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, y);
		c.set(Calendar.MONTH, m-1);	//month从0开始
		c.set(Calendar.DAY_OF_MONTH, d);
		long birthday = c.getTimeInMillis();
		mem.setBirthday(new Date(birthday));
		mem.setAddress(address);
		mem.setMail(mail);
		mem.setPhone(phone);
		
		MemberDAO md = new MemberDAO();
		ReturnMessage rm = md.addMember(mem, cid);
		
		Map<String, String> ds = new HashMap<String, String>();
		ds.put("fail", rm.getFail()+"");
		ds.put("msg", rm.getMessage());
		this.putValue(uuid, ds);
		
		ModelAndView mv = new ModelAndView("redirect:http://localhost:8080/wechatptf/wap/1/2/bindSuccess");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/bindSuccess", method=RequestMethod.GET)
	public ModelAndView bindSuccess(HttpServletRequest request){
		String uuid = (String) request.getSession().getAttribute(TokenUtil.KEY_UUID);
		ModelAndView mv = new ModelAndView("wap_bind_success");
		mv.addAllObjects((Map<String, String>)this.getValue(uuid));
		return mv;
	}

	@RequestMapping(value="/product", method=RequestMethod.GET)
	public ModelAndView product(){
		return null;
	}
	
	@RequestMapping(value="order", method=RequestMethod.GET)
	public ModelAndView order(){
		return null;
	}
	
	@RequestMapping(value="pay", method=RequestMethod.GET)
	public ModelAndView pay(){
		return null;
	}
	
	@RequestMapping(value="/{type}/{goodsid}/{time}/activity", method=RequestMethod.GET)
	public ModelAndView activity(@PathVariable("companyid") String cid, 
			@PathVariable("wxid") String wid, @PathVariable("type") String type, 
			@PathVariable("goodsid") String goodsid, @PathVariable("time") String time){
		Map<String, String> ds = new HashMap<String, String>();
		ds.put("cid", cid);
		ds.put("wxid", wid);
		ds.put("type", type);
		ds.put("goodsid", goodsid);
		ds.put("time", time);
		ModelAndView mv = new ModelAndView("wap_activity");
		mv.addAllObjects(ds);
		return mv;
	}
	
	@RequestMapping(value="/{type}/{goodsid}/{time}/addActivity", method=RequestMethod.POST)
	public ModelAndView addActivity(HttpServletRequest request, @PathVariable("companyid") String cid, 
			@PathVariable("wxid") String wid, @PathVariable("type") String type, 
			@PathVariable("goodsid") String goodsid, @PathVariable("time") String time, 
			String brief, String detail, String name, String password){
		
		String uuid = (String) request.getSession().getAttribute(TokenUtil.KEY_UUID);
		
		int tp = Integer.parseInt(type);
		
		Activity act = new Activity(wid,cid,tp,goodsid,time,brief,detail,name,password);
		ActDAO ad = new ActDAO();
		ReturnMessage rm = ad.addActivity(act);
		
		Map<String, String> ds = new HashMap<String, String>();
		ds.put("fail", rm.getFail()+"");
		ds.put("msg", rm.getMessage());
		this.putValue(uuid, ds);
		
		ModelAndView mv = new ModelAndView("redirect:http://localhost:8080/wechatptf/wap/"+cid+"/"+wid+"/"+"/"+type+"/"+goodsid+"/"+time+"/activityAdded");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{type}/{goodsid}/{time}/activityAdded", method=RequestMethod.GET)
	public ModelAndView activityAdded(HttpServletRequest request){
		String uuid = (String) request.getSession().getAttribute(TokenUtil.KEY_UUID);
		ModelAndView mv = new ModelAndView("wap_activityAdded");
		mv.addAllObjects((Map<String, String>)this.getValue(uuid));
		return mv;
	}
}
