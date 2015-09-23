<div class="pageContent" sytle="height: 811px;">
<table class="table" width="99%" layoutH="130" >
	<tbody>
		<#if messageSender?exists>
			<tr>
				<td>
					<div>发件标题:${messageSender.msTopic}</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						收件内容:${messageSender.msContent}
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						收件时间:${messageSender.msSendTime}
					</div>
				</td>
			</tr>
		</#if>
	</tbody>
</table>
</div>
