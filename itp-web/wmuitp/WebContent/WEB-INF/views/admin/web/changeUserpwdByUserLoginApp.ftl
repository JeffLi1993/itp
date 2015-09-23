    <script src="/wmuitp/dwz/js/jquery-1.7.2.js"></script>
	<script langauge="javascript">
	
		jQuery(document).ready(function()
		{
			$("#submitChangeUserPwdButton").attr({"disabled":"disabled"});
		});
		
		//判断两次输入密码相等
		function checkPwd()
		{
			var ulPwd = $("#ulPassword").val();
			var secondPwd = $("#secondPassword").val();

			if( ulPwd != secondPwd )
			{ 
				$("#msgApp").html("&nbsp;&nbsp;&nbsp;&nbsp;两次输入的密码不致，请重新输入.");
				$("#submitChangeUserPwdButton").attr({"disabled":"disabled"});
			}
			else if(ulPwd == "" || secondPwd == "")
			{
				$("#msgApp").html("&nbsp;&nbsp;&nbsp;&nbsp;密码不能为空，请输入.");
				$("#submitChangeUserPwdButton").attr({"disabled":"disabled"});
			}
			else
			{
				$("#msgApp").html("");
				$("#submitChangeUserPwdButton").removeAttr("disabled");
			}
		}
		
		/*修改个人密码*/
		function changeUserpwdByUserLoginFormApp()
		{
			var userLoginId = $("#userLoginId").val();
			var ulPassword = $("#ulPassword").val();
		
			$.ajax({
				url  : "../admin/changeUserpwdByUserLoginApp",
				data :
				{
					sendTime 	:  (new Date()).getTime(),
					userLoginId :  userLoginId,
					ulPassword  :  ulPassword
				},
				type  : "post",
				async : false,
				dataType : "json",
				success  : function(data) 
				{
					if (data.success)
					{
						window.jsi.exitToast()
					} 
					else 
					{
						
					}
				}
			});
		}
	</script>
<table>
	<input id="userLoginId" name="userLoginId" type="hidden" value="${(userLoginId)?default('')}"/>
	<tr>
		<td>
			<label style="width:70px">新密码：</label>
		</td>
		<td>
			<input id="ulPassword"  onpropertychange="checkPwd();" onblur="checkPwd();"  name="ulPassword" type="password" >
		</td>
	</tr>
	<tr>
		<td>
			<label style="width:70px">再次输入：</label>
		</td>
		<td>
			<input id="secondPassword" onpropertychange="checkPwd();" onblur="checkPwd();" type="password" >
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div id="msgApp" style="color:red"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" id="submitChangeUserPwdButton" onclick = "changeUserpwdByUserLoginFormApp();">修改密码</button>
		</td>
	</tr>
</table>


