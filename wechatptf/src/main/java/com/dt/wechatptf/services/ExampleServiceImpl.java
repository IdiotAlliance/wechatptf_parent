package com.dt.wechatptf.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/***
 * 这是一个service示例，该类实现了{@link ExampleService}接口，并把其中的
 * {@link ExampleService#hello(String)} 方法定义为一个service接口，接口
 * 的定义是通过标注(annotation)实现的。
 * @author lvxiang
 *
 */
@Path("example") // 服务的url前缀
public class ExampleServiceImpl extends BaseService implements ExampleService {

	@GET
	@Path("/hello/{name}") // 先对于前缀的路径，大括号中是url参数
	public String hello(
			@PathParam("name") String name // 通过参数标注的方式获得url中的参数，注意名称的对应关系
	) {
		// TODO Auto-generated method stub
		return "Hello " + name;
	}

	/***
	 * 如果post请求是通过表格提交的key-value的形式，可以直接使用annotation来标记和获取参数。
	 */
	@POST
	@Path("/register")
	public String post(@FormParam("username") String username, 
					   @FormParam("password") String password) {
		
		return "your username is :" + username +"\n" +
			   "your password is :" + password;
	}

}

/***
 * 服务类写完后就需要到src/main/webapp/WEB-INF下的cxf-bean中把当前的类声明为一个bean，
 * 纳入spring的管理范围。声明方法请参考文件中已有的写法。注意在改动该文件前务必通知其他人，避免
 * 出现代码冲突。
 * 定义完bean之后尝试访问http://localhost:8080/wechatptf/services/example/hello/lv
 * 浏览器中应现实Hello lv
 */