<div class="panelBar">
	<ul class="toolBar">
		<li><a class="add" 		href="${ctx}/messageSender/showMessageSenderAdd" target="dialog" width="450" height="450" title="写信" mask = "true" rel="showMessageSenderAdd"><span>写信</span></a></li>
	</ul>
</div>
<table class="table" width="99%" layoutH="130" rel="jbsxBox">
	<thead>
		<tr>
			<th width="20" ><input type="checkbox" class="checkboxCtrl" group="messageSender" /></th>
			<th width="60" >信标题	</th>
			<th width="120" >发送时间	</th>
		</tr>
	</thead>
	<tbody>
		<#if page.getResult()?exists>
			<#list page.getResult() as messageSender>
			<tr target="messageSender" rel="${messageSender.msId}">
				<td><input type="checkbox" name="messageSender" value="${messageSender.msId}"/></td>
				<td>
					<a href="${ctx}/messageSender/showMessageSenderDetail?messageSenderId=${messageSender.msId}" target="dialog" width="450" height="350" title="发件详情" mask = "true" rel="MessageSenderIdDetail">
						${messageSender.msTopic}
					<a/>
				</td>
				<td>${messageSender.msSendTime}</td>
			</tr>	
			</#list>
		</#if>
	</tbody>
</table>
<form id="pagerForm" method="post" action="${ctx}/messageSender/messageSenders">
	<input type="hidden" name="pageNum" 	value=${page.getPageNo()} />
	<input type="hidden" name="numPerPage" 	value=${page.getPageSize()} />
</form>
<div class="panelBar">
	<div class="pages">
		<span>显示</span>
		<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			<option value="20" 	<#if numPerPage == 20>	selected</#if>>20	</option>
			<option value="50" 	<#if numPerPage == 50>	selected</#if>>50	</option>
			<option value="100" <#if numPerPage == 100>	selected</#if>>100	</option>
			<option value="200" <#if numPerPage == 200>	selected</#if>>200	</option>
		</select>
		<span>条，共${page.getTotalCount()}条</span>
	</div>
	<div class="pagination" totalCount=${page.getTotalCount()} numPerPage=${page.getPageSize()} pageNumShown="10" currentPage=${page.getPageNo()}></div>
</div>