<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" 		href="${ctx}/professionInfo/showProfessionInfoAdd" target="dialog" width="400" height="250" title="新增专业" mask = "true" rel="professionInfoAdd"><span>新增专业</span></a></li>
			<li><a class="edit" 	href="${ctx}/professionInfo/showProfessionInfoAdd?professionInfoId={professionInfo}" target="dialog" width="400" height="250" title="修改专业" mask = "true" rel="professionInfoEdit"><span>修改专业</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('professionInfo', '${ctx}/professionInfo/paperInfoDelete', '${ctx}/professionInfo/professionInfos', 'professionInfos');return false;" title="支持批量删除" rel="professionInfoDelete"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="130" rel="jbsxBox">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="professionInfo" /></th>
				<th width="30">ID</th>
				<th >专业名称	</th>
				<th >所属学院	</th>
				<th >详情备注	</th>
			</tr>
		</thead>
		<tbody>
			<#if page.getResult()?exists>
				<#list page.getResult() as professionInfo>
				<tr target="professionInfo" rel="${professionInfo.piId}">
					<td><input type="checkbox" name="professionInfo" value="${professionInfo.piId}"/></td>
					<td>${professionInfo.piId}</td>
					<td>${professionInfo.piProfession}</td>
					<td>${professionInfo.piCollege}</td>
					<td>${professionInfo.piSign}</td>
				</tr>	
				</#list>
			</#if>
		</tbody>
	</table>
	<form id="pagerForm" method="post" action="${ctx}/professionInfo/professionInfos">
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
