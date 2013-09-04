package com.dt.wechatptf.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;

import ch.qos.logback.classic.Level;

import com.dt.wechatptf.util.LogUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Path("/upload")
public class ResourcesServiceImpl extends BaseService implements
		ResourcesService {

	private static final String MIME_TYPE_JPEG = "image/jpeg;charset=utf-8";
	private static final String MIME_TYPE_PNG = "image/png;charset=utf-8";
	private static final String MIME_TYPE_GIF = "image/gif;charset=utf-8";

	private static final String IMAGE_TYPE_JPEG = ".jpg";
	private static final String IMAGE_TYPE_PNG = ".png";
	private static final String IMAGE_TYPE_GIF = ".gif";

	@SuppressWarnings("unused")
	private static final String BASE_IMAGE_PATH = "/home/lvxiang/";

	private final Logger logger = LogUtil.getLogger(ResourcesServiceImpl.class,
			Level.DEBUG);

	@SuppressWarnings("restriction")
	@Path("/img/{dir}/{img_name}")
	public void serveImage(@PathParam("dir") String dir,
			@PathParam("img_name") String name) {

		// 检测参数是否齐全
		HttpServletResponse response = getResponse();
		if(dir == null || name == null){
			try {
				response.sendRedirect("/wechatptf/error/404");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String lower_path = name.toLowerCase();
		try {
			if (lower_path.endsWith(IMAGE_TYPE_JPEG)) {
				response.setContentType(MIME_TYPE_JPEG);
				InputStream imageIn = new FileInputStream(new File(
						"/home/lvxiang/123.jpg"));
				// 得到输入的编码器，将文件流进行jpg格式编码
				JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
				// 得到编码后的图片对象
				BufferedImage image = decoder.decodeAsBufferedImage();
				// 得到输出的编码器
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response
						.getOutputStream());
				encoder.encode(image);// 对图片进行输出编码
				imageIn.close();// 关闭文件流
			} else if (lower_path.endsWith(IMAGE_TYPE_PNG)) {
				response.setContentType(MIME_TYPE_PNG);

			} else if (lower_path.endsWith(IMAGE_TYPE_GIF)) {
				response.setContentType(MIME_TYPE_GIF);

			} else{
				response.sendRedirect("/wechatptf/error/404");
			}
		} catch (Exception e) {
			logger.warn("Exception while serving images:", e.getCause());
		}
	}

}
