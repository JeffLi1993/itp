<!DOCTYPE html>
<html>
<head>
</head>

<body>
	<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" 		href="${ctx}/courseInfo/showCourseInfoAdd" target="dialog" width="587" height="520" title="新增课详情" mask = "true" rel="courseInfoAdd"><span>新增课详情</span></a></li>
				<li><a class="edit" 	href="${ctx}/courseInfo/showCourseInfoAdd?CIId={courseInfo}" target="dialog" width="450" height="450" title="修改课详情" mask = "true" rel="staffEdit"><span>修改课详情</span></a></li>
				<li><a class="delete"   href="#" onclick="objDelete('courseInfo', '${ctx}/courseInfo/courseInfoDelete', '${ctx}/courseInfo/courseinfos', 'courseInfos');return false;" title="支持批量删除" rel="courseInfoDelete"><span>删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="99%" layoutH="130" >
			<thead>
				<tr>
					<th width="30"><input type="checkbox" class="checkboxCtrl" group="courseInfo" /></th>
					<th width="30">ID</th>
					<th >上课时间</th>
					<th >课程名字</th>
					<th >上课地点</th>
					<th >节数</th>
					<th >描述</th>
				</tr>
			</thead>
			<tbody>
				<#if courseInfos?exists>
					<#list courseInfos as courseInfo>
						<tr target="courseInfo" rel="${courseInfo.ciId}" >
							<td><input type="checkbox" name="courseInfo" value="${courseInfo.ciId}"/></td>
							<td>${courseInfo.ciId}</td>
							<td>${courseInfo.ciDateTime}</td>
							<td>
								${courseInfo.courseTeacherRelation.course.CName}
								<a href="${ctx}/course/courseinfobyid?ciId=${courseInfo.ciId}"  target="navTab" 	rel= "courseInfoMore">
									<font style="color:blue"><详情></font>  
								</a>
							</td>
							<td>${courseInfo.ciPlace}</td>
							<td>${courseInfo.ciPeriod}</td>
							<td>${courseInfo.ciDesription}</td>
						</tr>	
					</#list>
				</#if>
			</tbody>
		</table>
	</div>
</body>
</html>
