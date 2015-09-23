<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" 		href="${ctx}/courseInfo/showCourseInfoAddTea" target="dialog" width="450" height="450" title="新增课详情" mask = "true" rel="courseInfoAdd"><span>新增课详情</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="130" >
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="courseInfo" /></th>
				<th width="180">上课时间</th>
				<th >节数</th>
				<th >上课班级</th>
				<th >上课地点</th>
				<th >课程名字</th>
				<th >结束时间</th>
				<th >描述</th>
			</tr>
		</thead>
		<tbody>
			<#if page.getResult()?exists>
				<#list page.getResult() as courseInfoDetail>
					<tr target="courseInfo" rel="${courseInfoDetail.courseInfo.ciId}" >
						<td><input type="checkbox" name="courseInfo" value="${courseInfoDetail.courseInfo.ciId}"/></td>
						<td>${courseInfoDetail.courseInfo.ciDateTime}</td>
						<td>${courseInfoDetail.courseInfo.ciPeriod}</td>
						<td>${courseInfoDetail.classNames}</td>
						<td>${courseInfoDetail.courseInfo.ciPlace}</td>
						<td>${courseInfoDetail.courseInfo.courseTeacherRelation.course.CName}</td>
						<td>${courseInfoDetail.courseInfo.ciOver}</td>
						<td>${courseInfoDetail.courseInfo.ciDesription}</td>
					</tr>	
				</#list>
			</#if>
		</tbody>
	</table>
	<form id="pagerForm" method="post" action="${ctx}/courseInfo/courseInfosTea">
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
