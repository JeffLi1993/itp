<div class="pageHeader">
	<form rel="pagerFormcourse" method="post" action="${ctx}/teacherInfo/teacherInfoLookup" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>教师姓名:</label>
				<input class="textInput" name="teacherInfoName" value="${(teacherInfoName)?default("")}" type="text">
			</li>	  
			<li>
				<label>专业名称:</label>
				<input class="textInput" name="professionName" value="${(professionName)?default("")}" type="text">
			</li>	  
			<li>
				<label>学院名称:</label>
				<input class="textInput" name="collegeName" value="${(collegeName)?default("")}" type="text">
			</li>	  
			<li>
				<label>教师职称:</label>
				<input class="jobName" name="jobName" value="${(jobName)?default("")}" type="text">
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
				<th orderfield="teacherInfoID" >教师名字</th>
				<th orderfield="#">教师职称</th>
				<th orderfield="#">教师专业</th>
				<th orderfield="#">教师学院</th>
				<th orderfield="#">教师年龄</th>
				<th orderfield="#">教师技能</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<#if teacherInfos?exists>
				<#list teacherInfos as teacherInfo>
					<tr>
						<td>${teacherInfo.tiName}</td>
						<td>${teacherInfo.tiJob}</td>
						<td>${teacherInfo.professionInfo.piProfession}</td>
						<td>${teacherInfo.professionInfo.piCollege}</td>
						<td>${teacherInfo.tiAge}</td>
						<td>${teacherInfo.tiSkills}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({teacherInfoID:'${teacherInfo.tiId}', teacherInfoName:'${teacherInfo.tiName}'})" title="查找带回">选择</a>
						</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>
</div>