<div class="pageHeader">
	<form rel="pagerFormcourse" method="post" action="${ctx}/courseTeacherRelation/showCourseTeacherRelationLookUp" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>教师名字:</label>
				<input class="textInput" name="teacherName" value="" type="text">
			</li>
			<li>
				<label>基本课程:</label>
				<input class="textInput" name="courseName" value="" type="text">
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
				<th orderfield="courseTeacherRelationID" width="150">教师名字</th>
				<th orderfield="courseTeacherRelationName">基本课程</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<#if courseTeacherRelations?exists>
				<#list courseTeacherRelations as courseTeacherRelation>
					<tr>
						<td>${courseTeacherRelation.teacherInfo.tiName}</td>
						<td>${courseTeacherRelation.course.CName}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({courseTeacherRelationID:'${courseTeacherRelation.ctrId}', courseTeacherRelationName:'${courseTeacherRelation.course.CName}(${courseTeacherRelation.teacherInfo.tiName})'})" title="查找带回">选择</a>
						</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>
</div>