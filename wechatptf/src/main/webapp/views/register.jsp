<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register Page</title>
</head>
<body>

    <center>
		<form method="post" action="http://localhost:8080/wechatptf/services/mail/register">
			Name:<input type="text" name="account" id="account" /><br /><br />
			Password:<input type="text" name="password" id="password" /><br /><br />
			Email:<input type="text" name="mail" id="mail" /><br /><br />
			<input type="submit" value="Register"/>
		</form>
	</center>

</body>
</html>