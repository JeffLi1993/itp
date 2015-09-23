<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" 		href="${ctx}/paperInfo/showPaperInfoAdd" target="dialog" width="450" height="450" title="新增测试试卷" rel="paperInfoAdd"><span>新增测试试卷</span></a></li>
			<li><a class="edit" 	href="${ctx}/paperInfo/showPaperInfoAdd?paperInfoID={paperInfo}" target="dialog" width="450" height="450" title="修改测试试卷" mask = "true" rel="paperInfoEdit"><span>修改测试试卷</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('paperInfo', '${ctx}/paperInfo/paperInfoDelete', '${ctx}/paperInfo/paperInfos', 'paperInfos');return false;" title="支持批量删除" rel="paperInfoDelete"><span>删除</span></a></li>
			<li><a class="add" 		href="${ctx}/paperInfo/showExamStatistical" target="dialog" width="450" height="220" title="导出测试情况" rel="showExamStatistical"><span>导出测试情况</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="130" rel="jbsxBox">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="paperInfo" /></th>
				<th width="30">ID</th>
				<th >测试卷名称	</th>
				<th >所属课程	</th>
				<th >所属老师	</th>
				<th >创建时间	</th>
			</tr>
		</thead>
		<tbody>
			<#if page.getResult()?exists>
				<#list page.getResult() as paperInfo>
				<tr target="paperInfo" rel="${paperInfo.piId}">
					<td><input type="checkbox" name="paperInfo" value="${paperInfo.piId}"/></td>
					<td>${paperInfo.piId}</td>
					<td>
						<a href="${ctx}/paperInfo/paperInfoDetail?paperInfoId=${paperInfo.piId}" target="dialog" width="450" height="350" title="试卷详情" mask = "true" rel="paperInfoDetail">
							${paperInfo.piName}
						</a>
					</td>
					<td>
						${paperInfo.courseInfo.courseTeacherRelation.course.CName}
						(${paperInfo.courseInfo.ciDateTime})
					</td>
					<td>${paperInfo.teacherInfo.tiName}</td>
					<td>${paperInfo.piDateTime}</td>
				</tr>	
				</#list>
			</#if>
		</tbody>
	</table>
	<form id="pagerForm" method="post" action="${ctx}/subjectInfor/subjectInfors">
		<input type="hidden" name="pageNum" 	value=${page.getPageNo()} />
		<input type="hidden" name="numPerPage" 	value=${page.getPageSize()} />
	</form>
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
