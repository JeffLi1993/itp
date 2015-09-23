<div class="pageHeader">
	<form id="pagerForm" method="post" action="${ctx}/courseChapter/courseChapters">
		<input type="hidden" name="pageNum" 	value=${page.getPageNo()} />
		<input type="hidden" name="numPerPage" 	value=${page.getPageSize()} />
		<p>
			<lable>课程名称：</lable>
			<input name="courseName"  type="text" size="30" value="${(courseName)?default("")}"/>
		</p>
		<div class="buttonActive">
			<div class="buttonContent">
				<button  onclick="navTab.reload('${ctx}/courseChapter/courseChapters?pageNo=1', {},'courseChapters');return false;">
				检索
				</button>
			</div>
			<a class="btnLook" style="float:right;" href="${ctx}/course/courseLookup" lookupGroup="" width="650">查找带回</a>
		</div>
	</form>
</div>
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" 		href="${ctx}/courseChapter/showcourseChapterAdd" target="dialog" width="450" height="200" title="新增课程进度" mask = "true" rel="courseChapterAdd"><span>新增课程进度</span></a></li>
			<li><a class="edit" 	href="${ctx}/courseChapter/showcourseChapterAdd?courseChapterID={courseChapter}" target="dialog" width="450" height="200"  title="修改课程进度" mask = "true" rel="courseChapterEdit"><span>修改课程进度</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('courseChapter', '${ctx}/courseChapter/courseChapterDelete', '${ctx}/courseChapter/courseChapters', 'courseChapters');return false;" title="支持批量删除" rel="courseChapterDelete"><span>删除</span></a></li>
			<li><a class="add" 		href="${ctx}/courseChapter/showCourseChapterAddByXls" target="dialog" width="450" height="150" title="新增课程进度xls" mask = "true" rel="courseChapterAdd"><span>新增课程进度xls</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="135" >
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="employ" /></th>
				<th width="30">ID</th>
				<th width="70%">课程进度名称	</th>
				<th >所属课程	</th>
				<th >备注	</th>
			</tr>
		</thead>
		<tbody>
				
			<#if page.getResult()?exists>
				<#list page.getResult() as courseChapter>
			
				<tr target="courseChapter" rel="${courseChapter.ccId}" >
					<td><input type="checkbox" name="courseChapter" value="${courseChapter.ccId}"/></td>
					<td>${courseChapter.ccId}</td>
					<td>${courseChapter.ccName}</td>
					<td>${courseChapter.course.CName}</td>
					<td>${courseChapter.ccSign}</td>
				</tr>	
				</#list>
			</#if>
		</tbody>
	</table>
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
