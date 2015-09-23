<div class="pageHeader">
	<form id="pagerForm" method="post" action="${ctx}/teacherInfo/teacherInfos">
		<div class="searchBar">
			<table>
				<tr>
					<input type="hidden" name="pageNum" 	value=${page.getPageNo()} />
					<input type="hidden" name="numPerPage" 	value=${page.getPageSize()} />
					<td>
						<lable>学院名称：</lable>
						<input name="collegeName"  type="text" size="30" alt="输入学院名称" value="${(collegeName)?default("")}"/>
					</td>
					<td>
						<lable>专业名称：</lable>
						<input name="professionName"  type="text" size="30" alt="输入专业名称" value="${(professionName)?default("")}"/>
					</td>
					<td>
						<lable>教师姓名：</lable>
						<input name="teacherInfoName"  type="text" size="30" alt="输入教师姓名" value="${(teacherInfoName)?default("")}"/>
					</td>
					<td>
						<lable>教师职称：</lable>
						<input name="jobName"  type="text" size="30" alt="输入教师职称" value="${(jobName)?default("")}"/>
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button  onclick="navTab.reload('${ctx}/teacherInfo/teacherInfos?pageNo=1', {},'teacherInfos');return false;">
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
			<li><a class="add" 		href="${ctx}/teacherInfo/showTeacherInfoAdd" target="dialog" width="450" height="450" title="新增老师" mask = "true" rel="showTeacherInfoAdd"><span>新增老师</span></a></li>
			<li><a class="edit" 	href="${ctx}/teacherInfo/showTeacherInfoAdd?teacherInfoId={teacherInfo}" target="dialog" width="450" height="450" title="修改老师" mask = "true" rel="teacherInfoEdit"><span>修改老师</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('teacherInfo', '${ctx}/teacherInfo/teacherInfoDelete', '${ctx}/teacherInfo/teacherInfos', 'teacherInfos');return false;" title="支持批量删除" rel="teacherInfoDelete"><span>删除</span></a></li>
			<li><a class="edit" 	href="${ctx}/teacherInfo/teacherInfoDetail?teacherInfoId={teacherInfo}" target="dialog" width="450" height="650" title="查看老师详情" mask = "true" rel="teacherInfoDetail"><span>查看老师详情</span></a></li>
			<li><a class="add" 		href="${ctx}/teacherInfo/showTeacherAddByXls" target="dialog" width="450" height="450" title="新增老师xls" mask = "true" rel="showTeacherAddByXls"><span>新增老师xls</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="130" rel="jbsxBox">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="teacherInfo" /></th>
				<th width="60">教师编号</th>
				<th >教师姓名	</th>
				<th >教师职称	</th>
				<th >教师学院	</th>
				<th >教师年龄	</th>
				<th >教师技能详情	</th>
				<th >重置密码	</th>
			</tr>
		</thead>
		<tbody>
			<#if page.getResult()?exists>
				<#list page.getResult() as teacherInfo>
				<tr target="teacherInfo" rel="${teacherInfo.tiId}">
					<td><input type="checkbox" name="teacherInfo" value="${teacherInfo.tiId}"/></td>
					<td>${teacherInfo.tiId}</td>
					<td>${teacherInfo.tiName}</td>
					<td>${teacherInfo.tiJob}</td>
					<td>${teacherInfo.professionInfo.piCollege}</td>
					<td>${teacherInfo.tiAge}</td>
					<td>${teacherInfo.tiSkills}</td>
					<td>
						<a href="#" onclick="initPassword(${teacherInfo.tiId},'${ctx}/teacherInfo/initTeacherInfoPassword')">
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
