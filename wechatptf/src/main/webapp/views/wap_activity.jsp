<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>发起活动</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	<style type="text/css">
		
	</style>
</head>
<body>

<div data-role="page">
	<div data-role="header" data-theme="a">
		<h1 data-role="ui-title" role="heading" arial-level="1">清秋梧桐</h1>
		<a href="/wechatptf/wap/${cid}/${wxid}/index" data-role="button" data-icon="home" 
		data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" 
		data-theme="c" class="ui-btn ui-btn-left ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c">
			<span class="ui-btn-inner ui-btn-corner-all">
				<span class="ui-btn-text">返回</span>
				<span class="ui-icon ui-icon-home ui-icon-shadow">&nbsp;</span>
			</span>
		</a>
		<a onclick="submit()" data-role="button" data-icon="check" data-corners="true" 
		data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="c" 
		class="ui-btn ui-btn-right ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c">
			<span class="ui-btn-inner ui-btn-corner-all">
				<span class="ui-btn-text">完成</span>
				<span class="ui-icon ui-icon-check ui-icon-shadow">&nbsp;</span>
			</span>
		</a>
	</div>
	
	<div class="content-primary">
	<form method="post" action="/wechatptf/wap/${cid}/${wxid}/${type}/${goodsid}/${time}/addActivity">
		<ul data-role="listview" data-inset="true" class="ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c ui-corner-top">
	        	<label for="brief" class="ui-input-text">主题：</label>
	        	<input type="text" name="brief" id="brief" value="" maxlength="255" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
			</li>
		
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c">
	        	<label for="detail" class="ui-input-text">详述：</label>
				<textarea cols="40" rows="8" name="detail" id="detail" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset"></textarea>
			</li>
			
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c ui-corner-top">
	        	<label for="name" class="ui-input-text">姓名：</label>
	        	<input type="text" name="name" id="name" value="" maxlength="255" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
			</li>
			
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c ui-corner-top">
	        	<label for="password" class="ui-input-text">密码：</label>
	        	<input type="text" name="password" id="password" value="" maxlength="255" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
			</li>
		</ul>
		<input type="submit" data-theme="b" class="ui-btn-inner ui-btn-corner-all" value="发起活动">
	</form>
	
	<div data-role="footer">
		<h4>2012-2013 D&T Software, No Rights Reserved</h4>
	</div>
	
	<script type="text/javascript">
		
		
	</script>

</div>

</body>
</html>