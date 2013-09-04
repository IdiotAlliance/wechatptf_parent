package com.dt.wechatptf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WechatXMLUtil {

	public static final int MSG_TYPE_UNKNOWN = -1;
	public static final int MSG_TYPE_TEXT  = 0x00;
	public static final int MSG_TYPE_IMG   = 0x01;
	public static final int MSG_TYPE_GEO   = 0x02;
	public static final int MSG_TYPE_LINK  = 0x03;
	public static final int MSG_TYPE_EVENT = 0x04;
	public static final int MSG_TYPE_MUSIC = 0x05;
	public static final int MSG_TYPE_VIDEO = 0x06;
	public static final int MSG_TYPE_NEWS  = 0x07;
	public static final int MSG_TYPE_VOICE = 0x08;
	
	public static final String[] MSG_TYPES = {
		"text","image","location","link","event","music","video","news","voice"
	};
	
	// wechat tags in their original format
	public static final String KEY_FROM_USER = "FromUserName";
	public static final String KEY_TO_USER   = "ToUserName";
	public static final String KEY_CREATE_TIME = "CreateTime";
	public static final String KEY_MSG_TYPE    = "MsgType";
	public static final String KEY_CONTENT     = "Content";
	public static final String KEY_MSG_ID      = "MsgId";
	public static final String KEY_PIC_URL     = "PicUrl";
	public static final String KEY_LOCATION_X  = "Location_X";
	public static final String KEY_LOCATION_Y  = "Location_Y";
	public static final String KEY_SCALE       = "Scale";
	public static final String KEY_LABEL       = "Label";
	public static final String KEY_TITLE       = "Title";
	public static final String KEY_DESC        = "Description";
	public static final String KEY_URL  	   = "Url";
	public static final String KEY_EVENT       = "Event";
	public static final String KEY_EVENT_KEY   = "EventKey";
	public static final String KEY_MUSIC       = "Music";
	public static final String KEY_MUSIC_URL   = "MusicUrl";
	public static final String KEY_HQMUSIC_URL = "HQMusicUrl";
	public static final String KEY_ARTICLE_COUNT = "ArticleCount";
	public static final String KEY_ARTICLES    = "Articles";
	public static final String KEY_ITEM        = "item";
	
	// jsoup auto transform tags to lower case
	public static final String KEY_FROM_USER_LOWER   = "fromusername";
	public static final String KEY_TO_USER_LOWER     = "tousername";
	public static final String KEY_CREATE_TIME_LOWER = "createtime";
	public static final String KEY_MSG_TYPE_LOWER    = "msgtype";
	public static final String KEY_CONTENT_LOWER     = "content";
	public static final String KEY_MSG_ID_LOWER      = "msgid";
	public static final String KEY_PIC_URL_LOWER     = "picurl";
	public static final String KEY_LOCATION_X_LOWER  = "location_x";
	public static final String KEY_LOCATION_Y_LOWER  = "location_y";
	public static final String KEY_SCALE_LOWER       = "scale";
	public static final String KEY_LABEL_LOWER       = "label";
	public static final String KEY_TITLE_LOWER       = "title";
	public static final String KEY_DESC_LOWER        = "description";
	public static final String KEY_URL_LOWER  	     = "url";
	public static final String KEY_EVENT_LOWER       = "event";
	public static final String KEY_EVENT_KEY_LOWER   = "eventkey";
	public static final String KEY_MUSIC_LOWER       = "music";
	public static final String KEY_MUSIC_URL_LOWER   = "musicurl";
	public static final String KEY_HQMUSIC_URL_LOWER = "hqmusicurl";
	public static final String KEY_ARTICLE_COUNT_LOWER = "articlecount";
	public static final String KEY_ARTICLES_LOWER    = "articles";
	public static final String KEY_ITEM_LOWER        = "item";
	
	/***
	 * Object representation of wechat msg
	 * @author lvxiang
	 *
	 */
	public static class WechatMsg{
		private int type = MSG_TYPE_UNKNOWN;
		private Map<String, String> values;
		
		public WechatMsg(){
			this.values = new HashMap<String, String>();
		}
		
		public String valueOf(String key){
			return values.get(key);
		}
		
		public void put(String key, String value){
			this.values.put(key, value);
		}
		
		public int getType(){
			return type;
		}
		
		public void setType(int type){
			this.type = type;
		}
	}
	
	/***
	 * parse a wechat message in xml. the result is an {@link WechatMsg} object.
	 * @param xmlMsg message to be parsed
	 * @return an {@link WechatMsg} object
	 */
	public static WechatMsg parseMsg(String xmlMsg){
		if(xmlMsg == null)
			throw new NullPointerException();
		WechatMsg wm = new WechatMsg();
		Document doc = Jsoup.parse(xmlMsg);
		for(Element elem: doc.body().children().first().children()){
			String nodeName = elem.nodeName().trim();
			String value    = null;
			if(elem.childNodes().size() > 0)
				value    = elem.childNode(0).toString().trim();
			System.out.println(nodeName + ":" + value);
			if(nodeName == null)
				throw new NullPointerException("node name is null!");
			if(nodeName.equals(KEY_MSG_TYPE_LOWER)){
				wm.setType(getType(value));
			}
			wm.put(nodeName, value);
		}
		return wm;
	}
	
	public static class WechatMsgBuilder{
		
		public static MsgBuilder createTextMsgBuilder(){
			return new TextMsgBuilder();
		}
		
		public static MsgBuilder createMusicMsgBuilder(){
			return new MusicMsgBuilder();
		}
		
		public static MsgBuilder createVoiceMsgBuilder(){
			return new VoiceMsgBuilder();
		}
		
		public static MsgBuilder createVideoMsgBuilder(){
			return new VideoMsgBuilder();
		}
		
		public static MsgBuilder createNewsMsgBuilder(){
			return new NewsMsgBuilder();
		}
		
		public static MsgBuilder createNewsItemBuilder(){
			return new NewsItemBuilder();
		}
		
		private static class BaseBuilder implements MsgBuilder{
			
			protected Map<String, String> values = new HashMap<String, String>(); 
			protected Map<String, List<String>> items = new HashMap<String, List<String>>();
			
			public MsgBuilder append(String key, String value) {
				values.put(key, value);
				return this;
			}
			
			public MsgBuilder appendItem(String key, String value) {
				if(!items.containsKey(key))
					items.put(key, new ArrayList<String>());
				items.get(key).add(value);
				return this;
			}
			
			public String build() {
				String result = "<xml>" + 
								"<" + KEY_TO_USER + "><![CDATA[" + values.get(KEY_TO_USER) + "]]></" + KEY_TO_USER + ">" +
								"<" + KEY_FROM_USER + "><![CDATA[" + values.get(KEY_FROM_USER) + "]]></" + KEY_FROM_USER + ">" + 
								"<" + KEY_CREATE_TIME + ">" + values.get(KEY_CREATE_TIME) + "</" + KEY_CREATE_TIME + ">";
				return result;
			}
		}
		
		private static class TextMsgBuilder extends BaseBuilder{
			@Override
			public String build() {
				String result = super.build();
				result += "<" + KEY_MSG_TYPE + "><![CDATA[" + MSG_TYPES[MSG_TYPE_TEXT] + "]]></" + KEY_MSG_TYPE + ">" +
						  "<" + KEY_CONTENT + "><![CDATA[" + values.get(KEY_CONTENT) + "]]></" + KEY_CONTENT + ">" + 
						  "</xml>";
				return result;
			}
		}
		
		private static class MusicMsgBuilder extends BaseBuilder{
			@Override
			public String build() {
				String result = super.build();
				result += "<" + KEY_MSG_TYPE + "><![CDATA[" + MSG_TYPES[MSG_TYPE_MUSIC] + "]]></" + KEY_MSG_TYPE + ">" +
				          "<Music>" +
						  "<" + KEY_TITLE + "><![CDATA[" + values.get(KEY_TITLE) + "]]></" + KEY_TITLE + ">" +
				          "<" + KEY_DESC + "><![CDATA[" + values.get(KEY_DESC) + "]]></" + KEY_DESC + ">" +
				          "<" + KEY_MUSIC_URL + "><!CDATA[" + values.get(KEY_MUSIC_URL) + "]]></" + KEY_MUSIC_URL + ">" +
				          "<" + KEY_HQMUSIC_URL + "><![CDATA[" + values.get(KEY_HQMUSIC_URL) + "]]></" + KEY_HQMUSIC_URL + ">" +
				          "</Music>" +
				          "</xml>"; 
			    return result;
			}
		}
		
		private static class NewsMsgBuilder extends BaseBuilder{
			@Override
			public String build() {
				String result = super.build();
				result += "<" + KEY_MSG_TYPE + "><![CDATA[" + MSG_TYPES[MSG_TYPE_NEWS] + "]]></" + KEY_MSG_TYPE + ">" +
						  "<" + KEY_ARTICLE_COUNT + ">" + items.get(KEY_ITEM).size() + "</" + KEY_ARTICLE_COUNT + ">" +
						  "<" + KEY_ARTICLES + ">";
				for(String value: items.get(KEY_ITEM)){
					result += "<" + KEY_ITEM + ">" + value + "</" + KEY_ITEM + ">";
				}
				result += "</" + KEY_ARTICLES + ">" +
						  "</xml>";
				return result;
			}
		}
		
		private static class VideoMsgBuilder extends BaseBuilder{
			@Override
			public String build() {
				// TODO Auto-generated method stub
				return null;
			}
		}
		
		private static class VoiceMsgBuilder extends BaseBuilder{
			@Override
			public String build() {
				// TODO Auto-generated method stub
				return null;
			}
		}
		
		private static class NewsItemBuilder extends BaseBuilder{
			@Override
			public String build() {
				String result = "<" + KEY_TITLE + "><![CDATA[" + values.get(KEY_TITLE) + "]]></" + KEY_TITLE + ">" +
							    "<" + KEY_DESC + "><![CDATA[" + values.get(KEY_DESC) + "]]></" + KEY_DESC + ">" +
							    "<" + KEY_PIC_URL + "><![CDATA[" + values.get(KEY_PIC_URL) + "]]></" + KEY_PIC_URL + ">" +
							    "<" + KEY_URL + "><![CDATA[" + values.get(KEY_URL) + "]]></" + KEY_URL + ">";
				return result;
			}
		}
	}
	
	public interface MsgBuilder{
		/**
		 * append a simple element to the current message
		 * @param key
		 * @param value
		 * @return
		 */
		public MsgBuilder append(String key, String value);
		
		/**
		 * append complex item to the current message
		 * @param key
		 * @param value
		 * @return
		 */
		public MsgBuilder appendItem(String key, String value);
		
		/**
		 * build the messsage into a single xml string
		 * @return
		 */
		public String build();
	}
	
	private static int getType(String type){
		int t = 0;
		for(String str: MSG_TYPES){
			if(str.equals(type))
				return t;
			t ++;
		}
		return MSG_TYPE_UNKNOWN;
	}
	
	public static void main(String[] args){
		
	}
}
