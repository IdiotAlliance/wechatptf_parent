package com.dt.wechatptf.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dt.wechatptf.util.TokenUtil;

/***
 * 给请求session添加uuid属性
 * @author lvxiang
 *
 */
public class SessionFilter extends OncePerRequestFilter{
	
	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest arg0,
			HttpServletResponse arg1, FilterChain arg2)
			throws ServletException, IOException {
		if(arg0.getSession().getAttribute(TokenUtil.KEY_UUID) == null){
			String uuid = TokenUtil.genUUID();
			arg0.getSession().setAttribute(TokenUtil.KEY_UUID, uuid);
			logger.debug("bind uuid {} to session {}", uuid, arg0.getSession().getId());
		}
		arg2.doFilter(arg0, arg1);
	}

}
