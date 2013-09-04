package com.dt.wechatptf.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

public abstract class BaseService {

	protected final HttpSession getSession(){
		final Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpSession session = ((HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST)).getSession();
		return session;
	}
	
	protected final String getSessionId(){
		return getSession().getId();
	}
	
	protected final HttpServletRequest getRequest(){
		final Message message = PhaseInterceptorChain.getCurrentMessage();
		return (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
	}

	protected final InputStream getInputStream(){
		try {
			return getRequest().getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected final HttpServletResponse getResponse(){
		final Message message = PhaseInterceptorChain.getCurrentMessage();
		return (HttpServletResponse)message.get(AbstractHTTPDestination.HTTP_RESPONSE);
	}
	
	/***
	 * 从<code>HttpServletResponse</code>中获取<code></code>
	 * @return
	 */
	protected final OutputStream getOutputStream(){
		try {
			return getResponse().getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected final void setContentType(String type){
		getResponse().setContentType(type);
	}
}
