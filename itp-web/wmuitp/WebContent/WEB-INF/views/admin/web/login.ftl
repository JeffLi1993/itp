<!DOCTYPE html>
<html>
<head>
<title>管理员登陆</title>

<script src="/wmuitp/js/validation/validation.js" type="text/javascript"></script>

<script type = "text/javascript">
	function check(){
		var loginName = document.getElementById("loginName").value;
		var password = document.getElementById("password").value;
		var VerificationCode=document.getElementById("textid").value;
		if(loginName == ""|| password == ""){
			var messagenode= document.getElementById("message");
			messagenode.innerHTML="请将登录信息填写完整";
			return false;
		}
		if(VerificationCode!=validation)
		{
			var messagenode= document.getElementById("message");
			messagenode.innerHTML="验证码不正确";
			return false;
		}
	}
</script>
</head>

<body>
	<p>管理员登陆</p>
	<form name="login" action="${ctx}/admin/login" method="post" onsubmit = "return check();" >
		账号：<input type="text" name="loginName" id="loginName" value="${(loginName)?default("")}"/><br/>
		密码：<input type="password" name="password" id="password" /><br/>
        <label>验证码: </label>
        <img src="" id="imgid" onclick="changeimg()" alt="正在加载中"  />
        <input type="text" id="textid" style=" width:125px;"/>
		<input type="submit" value="登陆" />
	</form>
	<font color="red" id="message">${(message)?default("")}</font>
</body>
</html>
