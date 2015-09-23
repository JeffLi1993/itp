<div class="xmyTab xmyTeachTab">
	<ul>
		<li><a href="#" onclick="divload('contentYwl','${ctx}/courseInfo/getCourseInfoByCIIdTeaAppAjax')">课程详情</a></li>
		<li><a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoByCIIdTeaApp')">课堂测试</a></li>
		<li class="xtabChooes"><a href="#" onclick="divload('contentYwl','${ctx}/discussionTopic/getDiscussionTopicByCIIdAppTea')">议题</a></li>
	</ul>	
</div>
<div data-role="content"> 
	<div class="xmyContent">
		<div class="editor teaEditor">
			<a><em></em>帖子详情</a>
		</div>
		<div class="editor">
			共有<em>${discussionTopics?size}</em>贴
		</div>
		<#if discussionTopics?exists>
			<#list discussionTopics as discussionTopic>
				<!--第一个帖子Begin-->
				<div class="chatBox">
					<h3>
						${discussionTopic.dtName}
					</h3>
					<p>
						${discussionTopic.dtContent}
					</p>
					<div class="chatMsg">
						<span>${discussionTopic.courseInfo.courseTeacherRelation.teacherInfo.tiName}<em>${(discussionTopic.dtDateTime)?substring(0,10)}</em></span>
					</div>
					<div class="chatDiscuss">
						<a href="#" onclick="divload('contentYwl','${ctx}/discussionTopic/showDiscussionTopicAppTea?discussionTopicId=${discussionTopic.dtId}')" ><em></em>${(discussionTopic.dtReply)?default("0")}</a>
					</div>
				</div>
				<!--第一个帖子end-->
			</#list>
		<#else>
		
		</#if>
	</div>
</div>