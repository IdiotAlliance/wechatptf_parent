package com.dt.wechatptf.services;

/***
 * 这是一个例子，请模仿该例子编写service。这个文件中定义了一个借口，需要另外写
 * 一个类实现这个接口。接口中的方法就是一个个单独的service。
 * @author lvxiang
 *
 */
public interface ExampleService {
	/***
	 * 这个方法从url中获取用户姓名，并对其打招呼
	 * @param name 从url中解析的用户名
	 * @return
	 */
	public String hello(String name);
	
	public String post(String username, String password);
}
