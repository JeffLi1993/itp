<div class="xmyTab">
	<ul>
		<li><a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoBeforeByCIdApp?courseId=${courseId}')">课堂测试</a></li>
		<li class="xtabChooes"><a href="#">议题</a></li>
	</ul>	
</div>
<div data-role="content"> 
	<div class="xmyContent">
		<div class="editor">
			<a><em></em>帖子详情</a>
		</div>
		<div class="editor">
			共有<em>${discussionTopics?size}</em>贴
		</div>
		<#if discussionTopics?exists>
				<#list discussionTopics as discussionTopic>
					<!--第一个帖子Begin-->
					<div class="chatBox" onclick="divload('contentYwl','${ctx}/discussionTopic/showDiscussionTopicApp?discussionTopicId=${discussionTopic.dtId}')">
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
							<a  href="#"  ><em></em>${(discussionTopic.dtReply)?default("0")}</a>
						</div>
					</div>
					<!--第一个帖子end-->
			</#list>
		</#if>
	</div>
</div>
