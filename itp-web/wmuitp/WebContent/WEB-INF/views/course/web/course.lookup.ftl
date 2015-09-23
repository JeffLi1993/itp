<div class="pageHeader">
	<form rel="pagerFormcourse" method="post" action="${ctx}/course/courseLookup" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>基本课程:</label>
				<input class="textInput" name="CName" value="" type="text">
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
				<th orderfield="courseID" width="150">基本课程</th>
				<th orderfield="courseName">课程描述</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<#if courses?exists>
				<#list courses as course>
					<tr>
						<td>${course.CName}</td>
						<td>${course.CDescription}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({courseID:'${course.CId}', courseName:'${course.CName}'})" title="查找带回">选择</a>
						</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>
</div>