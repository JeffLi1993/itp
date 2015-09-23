<script language="text/javascript">
	//判断两次输入密码相等
  	function checkPwd()
  	{
		var ulPwd = $("#ulPassword").val();
		var secondPwd = $("#secondPassword").val();
		
		if( ulPwd != secondPwd )
		{ 
			 $("#msg").html("&nbsp;&nbsp;&nbsp;&nbsp;两次输入的密码不致，请重新输入");
			 $("#buttonId").addClass("buttonDisabled");
			 return false;
		}
		else if(ulPwd == "" || secondPwd == "")
		{
			$("#msg").html("&nbsp;&nbsp;&nbsp;&nbsp;密码不能为空，请输入");
			$("#buttonId").addClass("buttonDisabled");
			return false;
		}
		else
		{
			$("#msg").html("");
			$("#buttonId").removeClass("buttonDisabled");
			$("#buttonId").addClass("buttonActive");
			return true;
		}
	}
	
	function submitChangeUserPwdForm()
  	{
  		if(checkPwd())
  		{
  			formDatasAdd('changeUserpwdByUserLoginForm');
  		}
	}
</script>

<div class="pageFormContent" layoutH="0">
	<form id="changeUserpwdByUserLoginForm" method="POST" class="pageForm required-validate" action="${ctx}/admin/changeUserpwdByUserLogin" style="float:left">
		<input  name="userLoginId" type="hidden" value="${(userLoginId)?default('')}"/>
		<table cellpadding="0" cellspacing="0" border="0" width="430" class="tableShowCont">
			<tr>
				<td>
					<label style="width:70px">新密码：</label>
				</td>
				<td>
					<input id="ulPassword" onpropertychange="checkPwd();" onblur="checkPwd();"  name="ulPassword" type="password" class="required" >
				</td>
			</tr>
			<tr>
				<td>
					<label style="width:70px">再次输入：</label>
				</td>
				<td>
					<input id="secondPassword" onpropertychange="checkPwd();" onblur="checkPwd();" type="password" class="required">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="msg" style="color:red"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="formBar">
						<ul>
							<li>
								<div id="buttonId" class="buttonDisabled">
									<div class="buttonContent">
										<button type="button" onclick = "submitChangeUserPwdForm();">修改密码</button>
									</div>	
								</div>
							</li>
							<li>
								<div class="button">
									<div class="buttonContent">
										<button type="button" class="close">取消</button>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>
