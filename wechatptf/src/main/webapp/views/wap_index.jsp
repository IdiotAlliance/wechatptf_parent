<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html>
<head> 
	<title>首页</title> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	
	<style type="text/css">
		.hot-product{
			height: 60px;
			text-align: center;
			border: 1px solid #a0a0a0;
			margin: 5px;
			background-color: #fff;
		}
		.hot-product:hover{
			cursor: pointer;
		}
		.hot-product img{
			max-width: 100%;
			max-height: 100%;
		}
	</style>
</head> 

<body> 
<div data-role="page">

	<!-- this is a common header, should be reused, but I don't know how -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<h1 data-role="ui-title" role="heading" arial-level="1">清秋梧桐</h1>
		<a href="/wechatptf/views/components/account_bind_dialog.html"
			 data-icon="gear" data-rel="dialog" data-transition="pop"
			class="ui-btn-right ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-a" 
			data-corners="true" data-shadow="true" data-iconshadow="true" 
			data-wrapperels="span" data-theme="a">
			<span class="ui-btn-inner ui-btn-corner-all">
				<span class="ui-btn-text">游客</span>
				<span class="ui-icon ui-icon-gear ui-icon-shadow">&nbsp;</span>
			</span>
		</a>
		<div id="common-header-navigation" data-role="navbar">
			<ul>
				<li><a href="#" class="ui-btn-active">首页</a></li>
				<li><a href="#">优惠信息</a></li>
				<li><a href="#">积分商城</a></li>
			</ul>
		</div>	
	</div><!-- /header -->
		
	<div data-role="content" role="main">	
			
		<!-- hot products -->
		<div class="content-primary">
			<h6>热门产品</h6>
			<div class="ui-grid-a">
				<div class="ui-block-a">
					<div class="hot-product">
						<img src="/wechatptf/img/app1.jpg" alt="" />
					</div>
					
				</div>
				<div class="ui-block-b">
					<div class="hot-product">
						<img src="/wechatptf/img/app2.jpg" alt="" />
					</div>
					
				</div>
				<div class="ui-block-a">
					<div class="hot-product">
						<img src="/wechatptf/img/app3.jpg" alt="" />
					</div>
					
				</div>
				<div class="ui-block-b">
					<div class="hot-product">
						<img src="/wechatptf/img/app4.jpg" alt="" />
					</div>
					
				</div>
				<div class="ui-block-a">
					<div class="hot-product">
						<img src="/wechatptf/img/app5.jpg" alt="" />
					</div>
					
				</div>
			</div>
			
		</div>
		
		<!-- products categories -->
		<div class="content-primary" data-theme="c">
			<h6>产品分类</h6>
			<ul data-role="listview" data-filter="true" class="ui-listview">
				<li data-corners="false" data-shadow="false" data-iconshadow="true" 
					data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" 
					class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c">
					<div class="ui-btn-inner ui-li">
						<div class="ui-btn-text">
							<a href="#/uploads/apidocs/jquery-mobile/docs/lists/lists-nested.html&amp;ui-page=2-8" class="ui-link-inherit">
								<img src="/wechatptf/img/thumbnail_mishi.jpg" class="ui-li-thumb">
								<h3 class="ui-li-heading">密室逃脱</h3>
								<p class="ui-li-desc">惊险刺激的密室逃脱！</p>
							</a>
						</div>
						<span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span>
					</div>
				</li>
				<li data-corners="false" data-shadow="false" data-iconshadow="true" 
					data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" 
					class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c">
					<div class="ui-btn-inner ui-li">
						<div class="ui-btn-text">
							<a href="#/uploads/apidocs/jquery-mobile/docs/lists/lists-nested.html&amp;ui-page=2-8" class="ui-link-inherit">
								<img src="/wechatptf/img/thumbnail_gaodian.jpg" class="ui-li-thumb">
								<h3 class="ui-li-heading">糕点DIY</h3>
								<p class="ui-li-desc">发挥你的想象力，制作最好玩又好吃的糕点！</p>
							</a>
						</div>
						<span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span>
					</div>
				</li>
				<li data-corners="false" data-shadow="false" data-iconshadow="true" 
					data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" 
					class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c">
					<div class="ui-btn-inner ui-li">
						<div class="ui-btn-text">
							<a href="#/uploads/apidocs/jquery-mobile/docs/lists/lists-nested.html&amp;ui-page=2-8" class="ui-link-inherit">
								<img src="/wechatptf/img/thumbnail_space.jpg" class="ui-li-thumb">
								<h3 class="ui-li-heading">自由空间</h3>
								<p class="ui-li-desc">给你一片自由的空间，让灵感激荡，让想象飞驰！</p>
							</a>
						</div>
						<span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span>
					</div>
				</li>
			</ul>
		</div>
	</div><!-- /content -->
	
	<div data-role="footer">
		<h4>2012-2013 D&T Software, No Rights Reserved</h4>
	</div>
</div><!-- /page -->

</body>
</html>