<div id="subjectInfoLookUpId">
	<div class="pageHeader">
		<form  method="post" action="${ctx}/subjectInfor/subjectInforsForLookUp" onsubmit="return dwzSearch(this, 'dialog');">
					<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>所属课程:</label>
					<input class="textInput" alt="请用逗号隔开" name="courseName" " type="text" value="${(courseName)?default("")}">
				</li>
				<li>
					<label>进度名称:</label>
					<input class="textInput" alt="请用逗号隔开" name="courseChapterName"  type="text" value="${(courseChapterName)?default("")}">
				</li>	  
				<li>
					<label>所属老师:</label>
					<input class="textInput" alt="请用逗号隔开" name="teacherName"  type="text" value="${(teacherName)?default("")}">
				</li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="button" multLookup="subjectInfor" warn="请选择试题">选择带回</button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
	
		<table class="table" layoutH="118" targetType="dialog" >
			<thead>
				<tr>
					<th width="30"><input type="checkbox" class="checkboxCtrl" group="subjectInfor" /></th>
					<th orderfield="csTitle">试题编号</th>
					<th orderfield="siId"width="550">题目名称</th>
					<th orderfield="leader"width="200">所属进度</th>
					<th orderfield="creator" width="100">所属老师</th>
				</tr>
			</thead>
			<tbody>
				<#if subjectInfors?exists>
					<#list subjectInfors as subjectInfor>
						<tr target="subjectInfor" rel="${subjectInfor.siId}" >
							<td><input type="checkbox" name="subjectInfor" value="{siId:'${subjectInfor.siId}', csTitle:'${subjectInfor.csTitle}'}"/></td>
							<td>${subjectInfor.siId}</td>
							<td>
								<a href="${ctx}/subjectInfor/subjectInforDetail?subjectInforID=${subjectInfor.siId}" target="dialog" width="450" height="350" title="题目详情" mask = "true" rel="subjectInforDetail">
								${subjectInfor.csTitle}
								</a>
							</td>
							<td>
								${subjectInfor.courseChapter.ccName}
								(${subjectInfor.courseChapter.course.CName})
							</td>
							<td>${subjectInfor.teacherInfo.tiName}</td>
						</tr>	
					</#list>
				</#if>
			</tbody>
		</table>
	</div>
</div>