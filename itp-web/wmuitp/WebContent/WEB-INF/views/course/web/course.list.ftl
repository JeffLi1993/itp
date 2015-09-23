<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/course/showCourseAdd" target="dialog" width="450" height="160" title="添加课程" mask = "true" rel="courseAdd"><span>添加课程</span></a></li>
			<li><a class="edit" href="${ctx}/course/showCourseAdd?CId={course}" target="dialog" width="450" height="160" title="修改课程" mask = "true" rel="courseEdit"><span>修改课程</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('course', '${ctx}/course/courseDelete', '${ctx}/course/courses', 'courses');return false;" title="支持批量删除" rel="courseDelete"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="130" rel="jbsxBox">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="employ" /></th>
				<th width="30">ID</th>
				<th >课程名字</th>
				<th width="80%">课程描述</th>
			</tr>
		</thead>
		<tbody>
			<#if  page.getResult()?exists>
				<#list  page.getResult() as course>
				<tr target="course" rel="${course.CId}" >
					<td><input type="checkbox" name="course" value="${course.CId}"/></td>
					<td>${course.CId}</td>
					<td>${course.CName}</td>
					<td>${course.CDescription}</td>
				</tr>	
				</#list>
			</#if>
		</tbody>
	</table>
	<form id="pagerForm" method="post" action="${ctx}/course/courses">
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
</body>
</html>
