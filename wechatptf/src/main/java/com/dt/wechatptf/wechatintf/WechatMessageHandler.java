package com.dt.wechatptf.wechatintf;

import com.dt.wechatptf.util.WechatXMLUtil;
import com.dt.wechatptf.util.WechatXMLUtil.WechatMsg;

public class WechatMessageHandler {

	public static String handleMsg(String userId, WechatMsg msg){
		switch(msg.getType()){
		case WechatXMLUtil.MSG_TYPE_TEXT:{
			return new TextMessageHandler(userId, msg).handleMsg();
		}
		case WechatXMLUtil.MSG_TYPE_GEO:{
			return new GeoMessageHandler(userId, msg).handleMsg();
		}
		case WechatXMLUtil.MSG_TYPE_EVENT:{
			return new EventMessageHandler(userId, msg).handleMsg();
		}
		}
		return null;
	}
	
	private static abstract class MessageHandler{
		protected WechatMsg wm;
		protected String fromUser;
		protected String toUser;
		protected long createTime;
		
		public MessageHandler(String userId, WechatMsg wm){
			this.wm = wm;
			this.fromUser   = wm.valueOf(WechatXMLUtil.KEY_FROM_USER_LOWER);
			this.toUser     = wm.valueOf(WechatXMLUtil.KEY_TO_USER_LOWER);
			this.createTime = Long.parseLong(wm.valueOf(WechatXMLUtil.KEY_CREATE_TIME_LOWER));
		}
		
		public abstract String handleMsg();
	}
	
	private static class TextMessageHandler extends MessageHandler{

		public TextMessageHandler(String userId, WechatMsg wm) {
			super(userId, wm);
		}

		@Override
		public String handleMsg() {
			return "<xml>" +
				   "<ToUserName><![CDATA[" + fromUser + "]]></ToUserName>" + 
				   "<FromUserName><![CDATA[" + toUser + "]]></FromUserName>" + 
				   "<CreateTime>" + System.currentTimeMillis()/1000 + "</CreateTime>" +
				   "<MsgType><![CDATA[news]]></MsgType>" +
				   "<ArticleCount>1</ArticleCount>" +
				   "<Articles>" + 
				   "<item>" + 
				   "<Title><![CDATA[测试标题]]></Title>" +  
				   "<Description><![CDATA[测试页面]]></Description>" + 
				   "<PicUrl><![CDATA[http://210.209.70.43/wechatptf/img/mishi.jpg]]></PicUrl>" + 
				   "<Url><![CDATA[http://210.209.70.43/wechatptf/test]]></Url>" +
				   "</item>" +
				   "</xml>";
		}
	}
	
	private static class EventMessageHandler extends MessageHandler{

		public EventMessageHandler(String userId, WechatMsg wm) {
			super(userId, wm);
			// TODO Auto-generated constructor stub
			
		}

		@Override
		public String handleMsg() {
			return "<xml>" +
					   "<ToUserName><![CDATA[" + fromUser + "]]></ToUserName>" + 
					   "<FromUserName><![CDATA[" + toUser + "]]></FromUserName>" + 
					   "<CreateTime>" + System.currentTimeMillis()/1000 + "</CreateTime>" +
					   "<MsgType><![CDATA[text]]></MsgType>" +
					   "<Content><![CDATA[我是ryouji,如果你收到这条消息就说明我成功啦！]]></Content>" +
					   "</xml>";
		}
	}
	
	private static class GeoMessageHandler extends MessageHandler{

		public GeoMessageHandler(String userId, WechatMsg wm) {
			super(userId, wm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String handleMsg() {
			return "<xml>" +
				   "<ToUserName><![CDATA[" + fromUser + "]]></ToUserName>" + 
				   "<FromUserName><![CDATA[" + toUser + "]]></FromUserName>" + 
				   "<CreateTime>" + System.currentTimeMillis()/1000 + "</CreateTime>" +
				   "<MsgType><![CDATA[text]]></MsgType>" +
				   "<Content><![CDATA[我是ryouji,如果你收到这条消息就说明我成功啦！]]></Content>" +
				   "</xml>";
		}
	}
	
}
