<div class="pageHeader">
	<form id="pagerForm" method="post" action="${ctx}/studentInfo/studentInfos">
		<div class="searchBar">
			<table>
				<tr>
					<input type="hidden" name="pageNum" 		value=${page.getPageNo()} />
					<input type="hidden" name="numPerPage" 		value=${page.getPageSize()} />
					<td>
						<lable>学院名称：</lable>
						<input name="collegeName"  type="text" size="30" alt="输入学院名称" value="${(collegeName)?default("")}"/>
					</td>
					<td>
						<lable>专业名称：</lable>
						<input name="professionName"  type="text" size="30" alt="输入专业名称" value="${(professionName)?default("")}"/>
					</td>
					<td>
						<lable>学生学号：</lable>
						<input name="studentNum" target="studentNumSearch" type="text" size="30" alt="输入学生学号" value="${(studentNum)?default("")}"/>
					</td>
					<td>
						<lable>学生姓名：</lable>
						<input name="studentName"  type="text" size="30" alt="输入学生姓名" value="${(studentName)?default("")}"/>
					</td>
							
					<td>
						&nbsp;
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button  onclick="navTab.reload('${ctx}/studentInfo/studentInfos?pageNo=1', {},'studentInfos');return false;">
								检索
								</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" 		href="${ctx}/studentInfo/showStudentInfoInfoAdd" target="dialog" width="450" height="450" title="新增学生" mask = "true" rel="showStudentInfoInfoAdd"><span>新增学生</span></a></li>
			<li><a class="edit" 	href="${ctx}/studentInfo/showStudentInfoInfoAdd?studentId={studentInfo}" target="dialog" width="450" height="450" title="修改学生" mask = "true" rel="showStudentInfoInfoEdit"><span>修改学生</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('studentInfo', '${ctx}/studentInfo/studentInfoDelete', '${ctx}/studentInfo/studentInfos', 'studentInfos');return false;" title="支持批量删除" rel="studentInfoDelete"><span>删除</span></a></li>
			<li><a class="edit" 	href="${ctx}/studentInfo/studentInfoDetail?studentId={studentInfo}" target="dialog" width="450" height="450" title="查看学生详情" mask = "true" rel="studentInfoDetail"><span>查看学生详情</span></a></li>
			<li><a class="add" 		href="${ctx}/studentInfo/showStudentAddByXls" target="dialog" width="450" height="150" title="新增学生xls" mask = "true" rel="showStudentAddByXls"><span>新增学生xls</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="135" >
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="studentInfo" /></th>
				<th width="60">学生编号</th>
				<th >学生学号	</th>
				<th >学生真实姓名	</th>
				<th >学生性别	</th>
				<th >学院	</th>
				<th >专业	</th>
				<th >班级	</th>
				<th >学生信息详情	</th>
				<th >重置密码	</th>
			</tr>
		</thead>
		<tbody>
				
			<#if page.getResult()?exists>
				<#list page.getResult() as studentInfo>
			
				<tr target="studentInfo" rel="${studentInfo.siId}" >
					<td><input type="checkbox" name="studentInfo" value="${studentInfo.siId}"/></td>
					<td>${studentInfo.siId}</td>
					<td>${studentInfo.siNum}</td>
					<td>${studentInfo.siRealName}</td>
					<td>${studentInfo.siSex}</td>
					<td>${studentInfo.classInfo.ciCollege}</td>
					<td>${studentInfo.classInfo.ciProfession}</td>
					<td>${studentInfo.classInfo.ciName}</td>
					<td>${studentInfo.siInformation}</td>
					<td>
						<a href="#" onclick="initPassword(${studentInfo.siId},'${ctx}/studentInfo/initStudentPassword')">
							重置密码
						</a>
					</td>
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
