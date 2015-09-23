<div class="pageHeader">
	<form id="pagerForm" method="post" action="${ctx}/subjectInfor/subjectInfors">
		<div class="searchBar">
			<table>
				<tr>
					<input type="hidden" name="pageNum" 	value=${page.getPageNo()} />
					<input type="hidden" name="numPerPage" 	value=${page.getPageSize()} />
					<td>
						<lable>试题名称：</lable>
						<input name="subjectInfoName"  type="text" size="30" alt="输入多个请用逗号隔开" value="${(subjectInfoName)?default("")}"/>
					</td>
					<td>
						<lable>教师名称：</lable>
						<input name="teacherInfoName"  type="text" size="30" alt="输入多个请用逗号隔开" value="${(teacherInfoName)?default("")}"/>
					</td>
					<td>
						<lable>课程名称：</lable>
						<input name="courseName"  type="text" size="30" alt="输入多个请用逗号隔开" value="${(courseName)?default("")}"/>
					</td>
					<td>
						<lable>进度名称：</lable>
						<input name="courserChapterName"  type="text" size="30" alt="输入多个请用逗号隔开" value="${(courserChapterName)?default("")}"/>
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button  onclick="navTab.reload('${ctx}/subjectInfor/subjectInfors?pageNo=1', {},'subjectInfors');return false;">
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
			<li><a class="add" 		href="${ctx}/subjectInfor/showSubjectInforAdd" target="dialog" width="380" height="450" title="新增测试题" mask = "true" rel="subjectInforAdd"><span>新增测试题</span></a></li>
			<li><a class="edit" 	href="${ctx}/subjectInfor/showSubjectInforAdd?subjectInforID={subjectInfor}" target="dialog" width="380" height="450" title="修改测试题" mask = "true" rel="subjectInforEdit"><span>修改测试题</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('subjectInfor', '${ctx}/subjectInfor/subjectInforDelete', '${ctx}/subjectInfor/subjectInfors', 'subjectInfors');return false;" title="支持批量删除" rel="subjectInforDelete"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="135" >
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="subjectInfor" /></th>
				<th width="30">ID</th>
				<th width="70%">测试题目名称	</th>
				<th >所属进度及课程	</th>
				<th >所属老师	</th>
			</tr>
		</thead>
		<tbody>
				
			<#if page.getResult()?exists>
				<#list page.getResult() as subjectInfor>
			
				<tr target="subjectInfor" rel="${subjectInfor.siId}" >
					<td><input type="checkbox" name="subjectInfor" value="${subjectInfor.siId}"/></td>
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
