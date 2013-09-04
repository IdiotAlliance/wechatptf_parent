package com.dt.wechatptf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="error")
public class ErrorController extends BaseController{
	
	/***
	 * Controller for 403 error page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public ModelAndView fot(HttpServletRequest request,
			HttpServletResponse response){
		return new ModelAndView("403");
	}
	
	/***
	 * Controller for 404 error page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/404", method=RequestMethod.GET)
	public ModelAndView fof(HttpServletRequest request,
			HttpServletResponse response){
		
		return new ModelAndView("404");
	}
	
}
