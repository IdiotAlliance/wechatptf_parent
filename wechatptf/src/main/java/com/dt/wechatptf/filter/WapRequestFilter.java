package com.dt.wechatptf.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 针对wap请求的过滤器，只有来自手机浏览器的wap请求才有效，否则将会被转到网页版
 * @author lvxiang
 *
 */
public class WapRequestFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest arg0,
			HttpServletResponse arg1, FilterChain arg2)
			throws ServletException, IOException {
//		if(!WebUtil.isFromMobileBrowser(arg0)){
//			arg1.sendRedirect("error/403");
//			return;
//		}
		arg2.doFilter(arg0, arg1);
	}

}
