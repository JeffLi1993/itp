<!DOCTYPE html>
<html>
<head>
<title>登录</title>


</head>

<body>
	${proName}
	<form name="login" action="${proName}/shop/action/login" method="post">
		登录名:<input type="text" name="loginName" /><br/>
		密码:<input type="password" name="password" /><br/>
		<a href="javascript:document.login.submit()">登录</a>
	</form>
</body>
</html>