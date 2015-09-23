<div class="pageContent">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>收件箱</span></a></li>
					<li><a href="${ctx}/messageSender/messageSenders" 	class="j-ajax"  ><span>发件箱</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:550px;">
			<div>
				<div class="panelBar">
					<ul class="toolBar">
						<li><a class="delete"   href="#" onclick="objDelete('messageReceive', '${ctx}/messageReceive/messageReceiveDelete', '${ctx}/messageReceive/messageReceives', 'messageReceives');return false;" title="支持批量删除" rel="messageReceiveDelete"><span>删除收件</span></a></li>
					</ul>
				</div>
				<table class="table" width="99%" layoutH="130" rel="jbsxBox">
						<thead>
							<tr>
								<th width="30"><input type="checkbox" class="checkboxCtrl" group="messageReceive" /></th>
								<th width="60">发件人	</th>
								<th width="240">标题	    </th>	
								<th width="120">发送时间	</th>
								<th width="60">是否看过</th>
							</tr>
						</thead>
						<tbody>
							<#if page.getResult()?exists>
								<#list page.getResult() as messageReceive>
									<tr target="messageReceive" rel="${messageReceive.mrId}">
										<td><input type="checkbox" name="messageReceive" value="${messageReceive.mrId}"/></td>
										<td>${messageReceive.messageSender.userLogin.ulName}</td>
										<td>
											<a href="${ctx}/messageReceive/showMessageReceiveDetail?messageReceiveId=${messageReceive.mrId}" target="dialog" width="450" height="350" title="收件详情" mask = "true" rel="MessageReceiveDetail">
												${messageReceive.messageSender.msTopic}
											</a>		
										</td>
										<td>${messageReceive.messageSender.msSendTime}</td>
										<td>${messageReceive.mrRead}</td>
									</tr>	
								</#list>
							</#if>
						</tbody>
					</table>
					<form id="pagerForm" method="post" action="${ctx}/messageReceive/messageReceives">
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
			</div>
			<div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>