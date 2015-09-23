<div class="pageHeader">
	<form id="pagerForm" method="post" action="${ctx}/discussionTopic/discussionTopics">
		<div class="searchBar">
			<table>
				<tr>
					<input type="hidden" name="pageNum" 		value=${page.getPageNo()} />
					<input type="hidden" name="numPerPage" 		value=${page.getPageSize()} />
					<td>
						<lable>议题题目：</lable>
						<input name="discussionTopicName"  type="text" size="30" alt="输入议题名称" value="${(discussionTopicName)?default('')}" />
					</td>
					<td>
						<lable>课程名称：</lable>
						<input name="courseInfoName"  type="text" size="30" alt="输入课程名称" value="${(courseInfoName)?default('')}" />
					</td>
							
					<td>
						&nbsp;
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button  onclick="navTab.reload('${ctx}/discussionTopic/discussionTopics?pageNo=1', {},'discussionTopics');return false;">
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
			<li><a class="add" 		href="${ctx}/discussionTopic/showDiscussionTopicAdd" target="dialog" width="450" height="285" title="新增议题" mask = "true" rel="showDiscussionTopicAdd"><span>新增议题</span></a></li>
			<li><a class="edit" 	href="${ctx}/discussionTopic/showDiscussionTopicAdd?discussionTopicId={discussionTopic}" target="dialog" width="450" height="285" title="修改议题" mask = "true" rel="showDiscussionTopicEdit"><span>修改议题</span></a></li>
			<li><a class="delete"   href="#" onclick="objDelete('discussionTopic', '${ctx}/discussionTopic/discussionTopicDelete', '${ctx}/discussionTopic/discussionTopics', 'discussionTopics');return false;" title="支持批量删除" rel="discussionTopicDelete"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="135" >
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="discussionTopic" /></th>
				<th width="60">Id</th>
				<th >议题名称</th>
				<th >议题时间</th>
				<th >进度名称</th>
				<th >浏览次数</th>
				<th >浏览人数</th>
				<th >回复次数</th>
				<th >议题状态</th>
			</tr>
		</thead>
		<tbody>
				
			<#if page.getResult()?exists>
				<#list page.getResult() as discussionTopic>
			
				<tr target="discussionTopic" rel="${discussionTopic.dtId}" >
					<td><input type="checkbox" name="discussionTopic" value="${discussionTopic.dtId}"/></td>
					<td>${discussionTopic.dtId}</td>
					<td>
						<a href="${ctx}/discussionTopic/discussionTopicDetail?discussionTopicId=${discussionTopic.dtId}" target="dialog" height="600" width="450" >
							${discussionTopic.dtName}
						</a>
					</td>
					<td>${discussionTopic.dtDateTime}</td>
					<td>${discussionTopic.courseInfo.courseTeacherRelation.course.CName}</td>
					<td>${discussionTopic.dtScan}</td>
					<td>${discussionTopic.studentInfos?size}</td>
					<td>${discussionTopic.dtReply}</td>
					<td>${discussionTopic.dtSign}</td>
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
