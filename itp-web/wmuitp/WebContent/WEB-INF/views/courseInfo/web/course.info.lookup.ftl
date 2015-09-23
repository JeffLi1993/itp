<div class="pageHeader">
	<form rel="pagerFormCourseInfoLookUp" method="post" action="${ctx}/courseInfo/courseInfoLookUp" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<input type="hidden" name="pageNum" value="1" />
			<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
			<input type="hidden" name="orderField" value="${param.orderField}" />
			<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
			<li>
				<label>用户名:</label>
				<input class="textInput" name="UserName" value="" type="text">
			</li>	  
			<li>
				<label>用户Id:</label>
				<input class="textInput" name="UserId" value="" type="text">
			</li> 
			<li>
				<label>用户类型:</label>
				<input class="textInput" name="UserType" value="" type="text">
			</li> 
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="#">课程详情Id</th>
				<th orderfield="#">课程名称</th>	
				<th orderfield="#">上课教师</th>
				<th orderfield="#">上课时间</th>
				<th width="80">查找带回</th>	
			</tr>
		</thead>
		<tbody>
			<#if page.getResult()?exists>
				<#list page.getResult() as courseInfo>
					<tr>
						<td>${courseInfo.ciId}</td>
						<td>${courseInfo.courseTeacherRelation.course.CName}</td>
						<td>${courseInfo.courseTeacherRelation.teacherInfo.tiName}</td>
						<td>${courseInfo.ciDateTime}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({courseInfoId:'${courseInfo.ciId}', courseName:'${courseInfo.courseTeacherRelation.course.CName}'})" title="查找带回">选择</a>
						</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="20" 	<#if numPerPage == 20>	selected</#if>>20	</option>
					<option value="50" 	<#if numPerPage == 50>	selected</#if>>50	</option>
					<option value="100" <#if numPerPage == 100>	selected</#if>>100	</option>
					<option value="200" <#if numPerPage == 200>	selected</#if>>200	</option>
			</select>
			<span>条，共${page.getTotalCount()}条</span>
		</div>
		<div class="pagination" totalCount=${page.getTotalCount()} targetType="dialog" numPerPage=${page.getPageSize()} pageNumShown="10" currentPage=${page.getPageNo()}></div>
	</div>
</div>