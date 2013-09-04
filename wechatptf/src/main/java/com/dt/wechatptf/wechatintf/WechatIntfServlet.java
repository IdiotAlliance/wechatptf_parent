package com.dt.wechatptf.wechatintf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dt.wechatptf.util.Encoder;
import com.dt.wechatptf.util.WechatXMLUtil;
import com.dt.wechatptf.util.WechatXMLUtil.WechatMsg;

/**
 * Servlet implementation class WechatIntfServlet
 */
@WebServlet("/WechatIntfServlet")
public class WechatIntfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public WechatIntfServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function handles callbacks from wechat
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String token = "ryouji";
		
		String userId    = request.getContextPath();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce     = request.getParameter("nonce");
		String echostr   = request.getParameter("echostr");
		System.out.println(userId);
		System.out.println("signature:" + signature);
		System.out.println("timestamp:" + timestamp);
		System.out.println("nonce:" + nonce);
		System.out.println("echostr:" + echostr);
		
		String local_sig = "";
		if(token != null && timestamp != null && nonce != null){
			
			List<String> listToSort = new ArrayList<String>();
			listToSort.add(token);
			listToSort.add(timestamp);
			listToSort.add(nonce);
			Collections.sort(listToSort);
			
			// compare sha1 codes
			if(signature != null && signature.equals(Encoder.encodeSHA1(local_sig))){
				response.getWriter().write(echostr);
				response.flushBuffer();
				return;
			}
		}
		response.getWriter().write(echostr + "1");
		response.getWriter().flush();
		return;
		
	}

	/**
	 * This interface is for messages post from wechat
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("received post request");
		
		// get input from wechat
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String input = "";
		String temp  = null;
		while((temp = reader.readLine()) != null)
			input += temp;
		System.out.println(input);
		
		// parse xml string
		response.setCharacterEncoding("utf-8");
		WechatMsg msg     = WechatXMLUtil.parseMsg(input);
		response.getWriter().write(WechatMessageHandler.handleMsg("ryouji", msg));
		response.flushBuffer();
	}
	
}
