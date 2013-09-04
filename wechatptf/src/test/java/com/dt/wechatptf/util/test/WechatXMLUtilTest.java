package com.dt.wechatptf.util.test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import com.dt.wechatptf.util.WechatXMLUtil;
import com.dt.wechatptf.util.WechatXMLUtil.WechatMsg;

public class WechatXMLUtilTest {
//
//	@Test
//	public void testGetType(){
//		for(Method method : WechatMsg.class.getMethods()){
//			if(method.getName().equals("getType")){
//				method.setAccessible(true);
//				try {
//					int type1 = (Integer) method.invoke(null, "image");
//					int type2 = (Integer) method.invoke(null, "link");
//					int type3 = (Integer) method.invoke(null, "unknown");
//					assertEquals(type1, WechatXMLUtil.MSG_TYPE_IMG);
//					assertEquals(type2, WechatXMLUtil.MSG_TYPE_LINK);
//					assertEquals(type3, WechatXMLUtil.MSG_TYPE_UNKNOWN);
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	
	@Test
	public void testParseXml1(){
		
		WechatMsg wm = WechatXMLUtil.parseMsg("<xml>" + 
 "<ToUserName><![CDATA[toUser]]></ToUserName>" +
 "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
 "<CreateTime>1348831860</CreateTime>" +
 "<MsgType><![CDATA[image]]></MsgType>" +
 "<PicUrl><![CDATA[this is a url]]></PicUrl>" +
 "<MsgId>1234567890123456</MsgId>" +
 "</xml>");
		assertEquals(wm.valueOf(WechatXMLUtil.KEY_MSG_TYPE_LOWER), "image");
		assertEquals(wm.getType(), WechatXMLUtil.MSG_TYPE_IMG);
		assertEquals(wm.valueOf(WechatXMLUtil.KEY_FROM_USER_LOWER), "fromUser");
		
	}
	
	@Test
	public void testParseXML2(){
		WechatXMLUtil.parseMsg("<xml><ToUserName><![CDATA[gh_2f57adf933f5]]></ToUserName><FromUserName><![CDATA[oz7OTjuZ_z6iwu03GOetYvpIMVpA]]></FromUserName><CreateTime>1377006695</CreateTime><MsgType><![CDATA[location]]></MsgType><Location_X>32.178417</Location_X><Location_Y>120.049957</Location_Y><Scale>20</Scale><Label><![CDATA[]]></Label><MsgId>5914198721398046747</MsgId></xml>");
	}
	
	@Test
	public void testParseXML3(){
		WechatXMLUtil.parseMsg("<xml><ToUserName><![CDATA[gh_2f57adf933f5]]></ToUserName><FromUserName><![CDATA[oz7OTjuZ_z6iwu03GOetYvpIMVpA]]></FromUserName><CreateTime>1377006772</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[unsubscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>");
	}
	
	@Test
	public void testBuildNewsItem(){
		String item = WechatXMLUtil.WechatMsgBuilder
			.createNewsItemBuilder()
			.append(WechatXMLUtil.KEY_TITLE, "test")
			.append(WechatXMLUtil.KEY_DESC, "测试消息")
			.append(WechatXMLUtil.KEY_URL, "http://www.baidu.com")
			.append(WechatXMLUtil.KEY_PIC_URL, "http://img1.gtimg.com/nice_mb/4b/07/27269.jpg")
			.build();
		System.out.println(item);
	}
	
	@Test
	public void testBuildNewsList(){
		String item1 = WechatXMLUtil.WechatMsgBuilder
				.createNewsItemBuilder()
				.append(WechatXMLUtil.KEY_TITLE, "test1")
				.append(WechatXMLUtil.KEY_DESC, "测试消息1")
				.append(WechatXMLUtil.KEY_URL, "http://www.baidu.com")
				.append(WechatXMLUtil.KEY_PIC_URL, "http://img1.gtimg.com/nice_mb/4b/07/27269.jpg")
				.build();
		String item2 = WechatXMLUtil.WechatMsgBuilder
				.createNewsItemBuilder()
				.append(WechatXMLUtil.KEY_TITLE, "test2")
				.append(WechatXMLUtil.KEY_DESC, "测试消息2")
				.append(WechatXMLUtil.KEY_URL, "http://www.baidu.com")
				.append(WechatXMLUtil.KEY_PIC_URL, "http://img1.gtimg.com/nice_mb/4b/07/27269.jpg")
				.build();
		String msg = WechatXMLUtil.WechatMsgBuilder
				.createNewsMsgBuilder()
				.append(WechatXMLUtil.KEY_FROM_USER, "123123123")
				.append(WechatXMLUtil.KEY_TO_USER, "321321321")
				.append(WechatXMLUtil.KEY_MSG_TYPE, WechatXMLUtil.MSG_TYPES[WechatXMLUtil.MSG_TYPE_NEWS])
				.append(WechatXMLUtil.KEY_CREATE_TIME, System.currentTimeMillis() + "")
				.appendItem(WechatXMLUtil.KEY_ITEM, item1)
				.appendItem(WechatXMLUtil.KEY_ITEM, item2)
				.build();
		System.out.println(msg);
	}
	
	@Test
	public void testBuildMusicMsg(){
		String msg = WechatXMLUtil.WechatMsgBuilder
				.createMusicMsgBuilder()
				.append(WechatXMLUtil.KEY_FROM_USER, "123123123")
				.append(WechatXMLUtil.KEY_TO_USER, "321321321")
				.append(WechatXMLUtil.KEY_MSG_TYPE, WechatXMLUtil.MSG_TYPES[WechatXMLUtil.MSG_TYPE_TEXT])
				.append(WechatXMLUtil.KEY_CREATE_TIME, System.currentTimeMillis() + "")
				.append(WechatXMLUtil.KEY_TITLE, "夜夜夜夜")
				.append(WechatXMLUtil.KEY_MUSIC_URL, "http://music.baidu.com/song/23152631")
				.append(WechatXMLUtil.KEY_HQMUSIC_URL, "http://music.baidu.com/song/23152631")
				.build();
		System.out.println(msg);
	}
	
	@Test
	public void testBuildTestMsg(){
		String msg = WechatXMLUtil.WechatMsgBuilder
				.createTextMsgBuilder()
				.append(WechatXMLUtil.KEY_FROM_USER, "123123123")
				.append(WechatXMLUtil.KEY_TO_USER, "321321321")
				.append(WechatXMLUtil.KEY_MSG_TYPE, WechatXMLUtil.MSG_TYPES[WechatXMLUtil.MSG_TYPE_TEXT])
				.append(WechatXMLUtil.KEY_CREATE_TIME, System.currentTimeMillis() + "")
				.append(WechatXMLUtil.KEY_CONTENT, "测试测试测试测试！")
				.build();
		System.out.println(msg);
	}
	
}
