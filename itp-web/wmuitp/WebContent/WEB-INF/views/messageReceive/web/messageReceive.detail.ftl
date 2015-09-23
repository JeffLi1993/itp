<div class="pageContent" sytle="height: 811px;">
<table class="table" width="99%" layoutH="130" >
	<tbody>
		<#if messageReceive?exists>
			<tr>
				<td>
					<div>收件标题:${messageReceive.messageSender.msTopic}</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>发件人:${messageReceive.messageSender.userLogin.ulName}</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						收件内容:${messageReceive.messageSender.msContent}
					</div>
				</td>
			</tr>
		</#if>
	</tbody>
</table>
</div>
