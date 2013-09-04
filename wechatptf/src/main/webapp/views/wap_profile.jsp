<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>会员注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	<style type="text/css">
		#subhidbtn{
			display: none;
		}
	</style>
</head>
<body>
	
	<!--  
	<script type="text/javascript">
        function submit(){
            var name=document.getElementById("name").value;
            var gender=0;
            if(document.getElementById("male").checked){
            	gender=0;
            }
            else if(document.getElementById("female").checked){
            	gender=1;
            }
            var birth_year=parseInt(document.getElementById("year").value);
            var birth_month=parseInt(document.getElementById("month").value);
            var birth_day=parseInt(document.getElementById("day").value);
            var address=document.getElementById("address").value;
            var mail=document.getElementById("mail").value;
            var phone=document.getElementById("phone").value;
            $.ajax({
                type:"POST",
                url:"http://localhost:8080/wechatptf/wap/1/2/bind",
                data:"name="+name+"&gender="+gender+"&birth_year="+birth_year+"&birth_month="+birth_month
                +"&birth_day="+birth_day+"&address="+address+"&mail="+mail+"&phone="+phone
            }).done(function(msg){
                
            });
        }
	</script>
	-->
<div data-role="page">
	<div data-role="header" data-theme="a">
		<h1 data-role="ui-title" role="heading" arial-level="1">清秋梧桐</h1>
		<a href="/wechatptf/wap/${cid}/${wxid}/index" data-role="button" data-icon="home" 
		data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" 
		data-theme="c" class="ui-btn ui-btn-left ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c">
			<span class="ui-btn-inner ui-btn-corner-all">
				<span class="ui-btn-text">首页</span>
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
	<form method="post" action="/wechatptf/wap/1/2/bind">
		<ul data-role="listview" data-inset="true" class="ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c ui-corner-top">
	        	<label for="name" class="ui-input-text">昵称：</label>
	        	<input type="text" name="name" id="name" value="" maxlength="255" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
			</li>
			
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c">
				<fieldset data-role="controlgroup" id="gender_set" class="ui-corner-all ui-controlgroup ui-controlgroup-vertical">
					<div role="heading" class="ui-controlgroup-label">性别：</div>
					<div class="ui-controlgroup-controls">
						<div class="ui-radio">
							<input type="radio" name="gender" id="male" value="0" checked="checked">
							<label for="male" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="radio-off" data-theme="c" class="ui-btn ui-btn-icon-left ui-corner-top ui-btn-up-c ui-radio-on">
								<span class="ui-btn-inner ui-corner-top">
									<span class="ui-btn-text">男</span>
								</span>
							</label>
						</div>
			         	<div class="ui-radio">
			         		<input type="radio" name="gender" id="female" value="1">
			         		<label for="female" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="radio-off" data-theme="c" class="ui-btn ui-btn-icon-left ui-radio-off ui-corner-bottom ui-controlgroup-last ui-btn-up-c">
			         			<span class="ui-btn-inner ui-corner-bottom ui-controlgroup-last">
			         				<span class="ui-btn-text">女</span>
			         			</span>
			         		</label>
			         	</div>
			        </div>
				</fieldset>
			</li>
					
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c">
				<label class="select ui-select">出生日期：</label>
				<div class="ui-controlgroup-controls">
					<span class="ui-select">
						<select name="year" id="year">
						</select>
						<label class="ui-input-text">年</label>
					</span>
					<span class="ui-select">
						<select name="month" id="month">
						</select>
						<label class="ui-input-text">月</label>
					</span>
					<span class="ui-select">
						<select name="day" id="day" onfocus="setDay()">
						</select>
						<label class="ui-input-text">日</label>
					</span>
				</div>
			</li>
			
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c">
	        	<label for="address" class="ui-input-text">地址：</label>
				<textarea cols="40" rows="8" name="address" id="address" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset"></textarea>
			</li>
			
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c ui-corner-top">
	        	<label for="mail" class="ui-input-text">邮箱：</label>
	        	<input type="text" name="mail" id="mail" value="" maxlength="64" onblur="checkMail()" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
			</li>
			
			<li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c ui-corner-top">
	        	<label for="phone" class="ui-input-text">电话：</label>
	        	<input type="text" name="phone" id="phone" value="" maxlength="20" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
			</li>
		</ul>
		<input type="submit" id="subhidbtn" disabled="disabled" class="hidden">
	</form>
	</div>
	
	<div data-role="footer">
		<h4>2012-2013 D&T Software, No Rights Reserved</h4>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var today = new Date();
			var year = today.getFullYear();
			var ys = document.getElementById("year");
			for(var i=0; i<120; i++){
				ys.options[i] = new Option(year-i,year-i);
			}
		
			var ms = document.getElementById("month");
			for(var i=0; i<12; i++){
				ms.options[i] = new Option(i+1,i+1);
			}
		});
		function checkMail(){
			var mail = document.getElementById("mail").value;
			var reMail =/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
			var s=new RegExp(reMail);

			if(! s.test(mail)){
				alert("邮箱不合法，请重新填写！");
				document.getElementById('mail').value="";
			}
		}
		
		function setDay(){
			var ds = document.getElementById("day");
			var year_slt = document.getElementById("year").value;
			var month_slt = document.getElementById("month").value;
			var day = 31;
			if(year_slt != null & month_slt != null){
				var y = parseInt(year_slt);
				var m = parseInt(month_slt);
				var d= new Date(y,m,0);
				day = d.getDate();
				//document.write(day);
			}
			for(var i=0; i<day; i++){
				ds.options[i] = new Option(i+1,i+1);
			}
		}
		
		function submit(){
	 		document.getElementById("subhidbtn").removeAttribute("disabled");
	 		$("#subhidbtn").click();
	 		document.getElementById("subhidbtn").setAttribute("disabled", "disabled");
		}
	</script>
</div>

</body>
</html>