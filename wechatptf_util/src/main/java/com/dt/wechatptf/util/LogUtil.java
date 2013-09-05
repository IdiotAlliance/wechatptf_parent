package com.dt.wechatptf.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

/***
 * 本系统采用slf4j + logback作为日志系统，该类是日志系统服务类，使用该类的中的方法
 * 可创建<code>Logger</code>实例。<br>
 * 
 * Logback中的logger分为7个level,{all,trace,debug,info,warn,error,off},其
 * 相互关系为：all<trace<debug<info<warn<error<off。若一个logger设定的level为
 * p,则只能打印出level大于或等于p的日志，例如L的level是warn，则对L的指令中，只有
 * {warn,error}是有效的，但调用其余指令不会报错。<br>
 * 
 * @see org.slf4j.LoggerFactory
 * @see ch.qos.logback.classic.Level
 * 
 * @author lvxiang
 *
 */
public class LogUtil {
	
	private static final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	
	/***
	 * 为指定的类创建一个logger, 默认的level为debug
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(final Class<?> clazz){
		return getLogger(clazz, Level.DEBUG);
	}
	
	/**
	 * 为指定的类创建一个logger,并指定logger的类型
	 * @param clazz
	 * @param level
	 * @return
	 */
	public static Logger getLogger(final Class<?> clazz, final Level level){
		Logger logger = LoggerFactory.getLogger(clazz);
		((ch.qos.logback.classic.Logger) logger).setLevel(level);
		return logger;
	}
	
	public static void printLogbackStatus(){
		StatusPrinter.print(lc);
	}

}
