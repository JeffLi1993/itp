<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" 		href="${ctx}/classInfo/showClassInfoAdd" target="dialog" width="380" height="300" title="新增班级" mask = "true" rel="showClassInfoAdd"><span>新增班级</span></a></li>
			<li><a class="edit" 	href="${ctx}/classInfo/showClassInfoAdd?classInfoId={classInfo}" target="dialog" width="450" height="300" title="修改班级" mask = "true" rel="classInfoEdit"><span>修改班级</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('classInfo', '${ctx}/classInfo/classInfoDelete', '${ctx}/classInfo/classInfos', 'classInfos');return false;" title="支持批量删除" rel="classInfoDelete"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="130" rel="jbsxBox">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="classInfo" /></th>
				<th width="30">ID</th>
				<th >班级姓名	</th>
				<th >班级人数	</th>
				<th >专业名称	</th>
				<th >所属学院	</th>
				<th >详情备注	</th>
			</tr>
		</thead>
		<tbody>
			<#if page.getResult()?exists>
				<#list page.getResult() as classInfo>
				<tr target="classInfo" rel="${classInfo.ciId}">
					<td><input type="checkbox" name="classInfo" value="${classInfo.ciId}"/></td>
					<td>${classInfo.ciId}</td>
					<td>${classInfo.ciName}</td>
					<td>${classInfo.ciStudentSum}</td>
					<td>${classInfo.ciProfession}</td>
					<td>${classInfo.ciCollege}</td>
					<td>${classInfo.ciSign}</td>
				</tr>	
				</#list>
			</#if>
		</tbody>
	</table>
	<form id="pagerForm" method="post" action="${ctx}/classInfo/classInfos">
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
