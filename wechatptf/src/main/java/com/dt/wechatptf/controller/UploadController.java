package com.dt.wechatptf.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Level;

import com.dt.wechatptf.util.LogUtil;
import com.dt.wechatptf.util.PropertiesUtil;
import com.dt.wechatptf.util.ReturnMessage;
import com.dt.wechatptf.util.TokenUtil;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="upload/{companyId}")
public class UploadController extends BaseController{
	
	private static final String KEY_UPLOAD_BASE = "UPLOAD_BASE";
	private static final String[] MIME_TYPE_IMAGE = {
		"jpeg", "png", "gif"
	};
	
	// 上传的文件最大4M
	private static final int MAX_SIZE = 4 * 1024 * 1024; 
	private static final PropertiesUtil pu = new PropertiesUtil("system.properties");
	
	private final Gson gson = new Gson();
	private final Logger logger = LogUtil.getLogger(UploadController.class, Level.DEBUG);
	
	
	@RequestMapping(value="/img", method=RequestMethod.POST)
	public String uploadImage(
			@PathVariable("companyId") String cid,
			@RequestParam("img") MultipartFile file){
		ReturnMessage rm = new ReturnMessage();
		String type = file.getContentType();
		for(String mime: MIME_TYPE_IMAGE){
			if(type.contains(mime)){
				
				byte[] data = null;
				try {
					data = file.getBytes();
				} catch (IOException e) {
					logger.warn("Exception while receiving uploaded image:", e.getCause());
					rm.setFail(1);
					rm.setMessage(e.getMessage());
					return gson.toJson(rm);
				}
				
				// 检测文件大小是否超标
				if(data.length > MAX_SIZE){
					rm.setFail(1);
					rm.setMessage("上传的文件不得超过4M");
					return gson.toJson(rm);
				}
				
				// 将文件存储到指定的路径
				String fileName = TokenUtil.genUUID() + "_" + 
								  System.currentTimeMillis();
				String suffix   = "." + mime;
				String dirPath = pu.getStringProperty(KEY_UPLOAD_BASE) + "/" + cid;
				File   dir     = new File(dirPath);
				if(!dir.exists())
					dir.mkdir();
				String filePath = dirPath + "/" + fileName + suffix;
				FileOutputStream fos = null;
				try {
					 // 创建原始大小的图片
					 fos = new FileOutputStream(new File(filePath));
					 fos.write(data);
					 fos.flush();
					 
					 // TODO 创建thumbnail图片文件
					 

					 // TODO 将路径存入数据库	
					 
					 rm.setFail(0);
					 rm.setMessage("url/to/the/image");
					 return gson.toJson(rm);
				} catch (Exception e) {
					logger.warn("Exception while creating image on server:", e.getCause());
				} finally{
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		rm.setFail(1);
		rm.setMessage("请上传正确格式的文件");
		return gson.toJson(rm);
	}
	
}
