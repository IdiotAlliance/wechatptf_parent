<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.dt.wechatptf.services.MailServiceImpl"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mail Verify</title>
</head>
<body>

<%
	MailServiceImpl msi = new MailServiceImpl();
	String account_md5 = request.getParameter("account_md5");
	String random_md5 = request.getParameter("random_md5");
	out.println(msi.verify(account_md5,random_md5));
%>

</body>
</html>