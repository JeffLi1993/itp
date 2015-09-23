<div class="pageHeader">
	<form  method="post" action="${ctx}/courseChapter/showCourseChapterLookUp" onsubmit="return dwzSearch(this, 'dialog');">
				<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>基本课程:</label>
				<input class="textInput"  name="courseName"  type="text" value="${(courseName)?default("")}">
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
				<th orderfield="courseTeacherRelationID" width="150">课程名称</th>
				<th orderfield="courseTeacherRelationName">进度名称</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<#if courseChapters?exists>
				<#list courseChapters as courseChapter>
					<tr>
						<td>${courseChapter.course.CName}</td>
						<td>${courseChapter.ccName}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({courseChapterID:'${courseChapter.ccId}', courseChapterName:'${courseChapter.course.CName}(${courseChapter.ccName})'})" title="查找带回">选择</a>
						</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>
</div>