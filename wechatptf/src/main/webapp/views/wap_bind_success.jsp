<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>绑定成功</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	
	<style>
		
	</style>
</head>
<body>

<div data-role="page">
	<div data-role="header" data-theme="a">
		<h1 data-role="ui-title" role="heading" arial-level="1">清秋梧桐</h1>
	</div>
	
	<div data-role="content" role="main" class="content-primary" style="text-align:center">
		<c:choose>
			<c:when test="${fail==0}">
				<div>
					恭喜你！绑定成功！
				</div>		
			</c:when>
			<c:otherwise>
				<div>抱歉，绑定失败，错误原因是：</div>
				<div>${msg}</div>
			</c:otherwise>
		</c:choose>
		<a href="/wechatptf/wap/123/2345/index" data-role="button" data-icon="home" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="c" class="ui-btn ui-btn-left ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c">
			<span class="ui-btn-inner ui-btn-corner-all">
				<span class="ui-btn-text">点击返回首页</span>
				<span class="ui-icon ui-icon-home ui-icon-shadow">&nbsp;</span>
			</span>
		</a>
	</div>
	
	
	<div data-role="footer" style="float:bottom">
		<h4>2012-2013 D&T Software, No Rights Reserved</h4>
	</div>
</div>

</body>
</html>