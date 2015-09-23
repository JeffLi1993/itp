<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/courseTeacherRelation/showCourseTeacherRelationAddTea" target="dialog"  width="450" height="300" title="新增课程和老师" mask = "true" rel="staffAdd"><span>新增课程和老师</span></a></li>
			<li><a class="edit" href="${ctx}/courseTeacherRelation/showCourseTeacherRelationAddTea?CTRId={teacherCourse}" target="dialog"  width="450" height="300" title="修改课程和老师" mask = "true" rel="staffEdit"><span>修改课程和老师</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('teacherCourse', '${ctx}/courseTeacherRelation/courseTeacherRelationDeleteTea', '${ctx}/courseTeacherRelation/courseTeacherRelationsTea', 'courseTeacherRelations');return false;" title="支持批量删除" rel="courseTeacherRelationDelete"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="130" rel="jbsxBox">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="teacherCourse" /></th>
				<th width="30">ID</th>
				<th >课程名字</th>
				<th >授课老师</th>
				<th >课程备注</thd>
			</tr>
		</thead>
		<tbody>
			<#if page.getResult()?exists>
				<#list page.getResult() as teacherCourse>
					<tr target="teacherCourse" rel="${teacherCourse.ctrId}" >
						<td><input type="checkbox" name="teacherCourse" value="${teacherCourse.ctrId}"/></td>
						<td>${teacherCourse.ctrId}</td>
						<td>${teacherCourse.course.CName}</td>
						<td>${teacherCourse.teacherInfo.tiName}</td>
						<td>${teacherCourse.ctrSign}</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>
	<form id="pagerForm" method="post" action="${ctx}/courseTeacherRelation/courseTeacherRelationsTea">
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
