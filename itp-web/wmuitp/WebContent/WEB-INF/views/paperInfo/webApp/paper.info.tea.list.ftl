<div class="xmyTab xmyTeachTab">
	<ul>
		<li><a href="#" onclick="divload('contentYwl','${ctx}/courseInfo/getCourseInfoByCIIdTeaAppAjax')">课程详情</a></li>
		<li class="xtabChooes"><a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoByCIIdTeaApp')">课堂测试</a></li>
		<li><a href="#" onclick="divload('contentYwl','${ctx}/discussionTopic/getDiscussionTopicByCIIdAppTea')">议题</a></li>
	</ul>	
</div>
<div class="xmyContent">
	<div class="xclassTest">
		<p>
			<span>共有测试卷<em>${paperInfos?size}</em>份</span>
		</p>
	</div>
	<ul class="xclassTestList">
		<#if paperInfos?exists>
			<#list paperInfos as paperInfo>
				<li>
					<a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperSubjectRelationsByPIIdTeaApp?paperInfoId=${paperInfo.piId}')">
						<div class="xclassTestList_date fl">
							<span>${(paperInfo.courseInfo.ciDateTime)?substring(0,4)}<br/><em>${(paperInfo.courseInfo.ciDateTime)?substring(5,10)}</em></span>
						</div>
						<div class="classTestList_msg fr">
							<span>${paperInfo.courseInfo.courseTeacherRelation.course.CName}</span>
							<span>${paperInfo.teacherInfo.tiName}</span>
						</div>
					</a>
				</li>
			</#list>
		</#if>
	</ul>
</div>
