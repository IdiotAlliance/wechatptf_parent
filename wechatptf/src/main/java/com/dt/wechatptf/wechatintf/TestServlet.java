package com.dt.wechatptf.wechatintf;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// print headers
		System.out.println("HEADERS:");
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()){
			String header = headers.nextElement();
			String value  = request.getHeader(header);
			System.out.println("HEADER-" + header + ":" + value);
		}
		
		// print cookies
		System.out.println("COOKIES:");
		if(request.getCookies() != null){
			for(Cookie cookie: request.getCookies()){
				if(cookie != null){
					String name = cookie.getName();
					String value = cookie.getValue();
					System.out.println("COOKIE-" + name + ":" + value);
				}
			}
		}
		
		// print attributes
		System.out.println("ATTRIBUTES:");
		Enumeration<String> attributes = request.getAttributeNames();
		while(attributes.hasMoreElements()){
			String attr = attributes.nextElement();
			Object val  = request.getAttribute(attr);
			String value = val==null?"null":val.toString();
			System.out.println("ATTRIBUTE-" + attr + ":" + value);
		}
		
		// print parameters
		System.out.println("PARAMETERS:");
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()){
			String pname = params.nextElement();
			String value = request.getParameter(pname);
			System.out.println("PARAM-" + pname + ":" + value);
		}
	}

}
