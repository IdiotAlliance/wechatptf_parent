package com.dt.wechatptf.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/***
 * 过滤非法的请求
 * @author lvxiang
 *
 */
public class IllegalRequestFilter extends OncePerRequestFilter{

	private static final String[] black_lists = {".jsp"};
	
	@Override
	protected void doFilterInternal(HttpServletRequest arg0,
			HttpServletResponse arg1, FilterChain arg2)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Filter high rate accessing IPs
		
		
		// TODO Filter malformed urls
		String url = arg0.getRequestURL().toString();
		for(String str: black_lists){
			if(url.endsWith(str)){
				arg1.sendRedirect("/wechatptf/error/404");
				return;
			}
		}
		
		arg2.doFilter(arg0, arg1);
	}

}
