<div id="popdiv" data-role="popup" data-inline="true" data-transition="pop" data-position-to="window" data-theme="b" style="max-width:400px;" class="ui-corner-all"><h1>我是弹出层</h1></div>
<div class="xmyContent2 replyResult">
	<div class="chatBox">
		<h3>
			${discussionTopic.dtName}
		</h3>			
		<div class="chatMsg">
			<span>${discussionTopic.courseInfo.courseTeacherRelation.teacherInfo.tiName}老师<em>${(discussionTopic.dtDateTime)?substring(0,10)}</em></span>
		</div>
		<p>
			${discussionTopic.dtContent}
		</p>
	</div>
	<!--回复1Begin-->
	<#if discussionTeacherReplys?exists>
		<#list discussionTeacherReplys as discussionTeacherReply>
			<div class="chatBox replyBox">
				<div class="chatMsg">
					<em class="iconListImg teaPerson"></em>
					<span>${(discussionTeacherReply.teacherInfo.tiName)?default("")}老师<em>${(discussionTeacherReply.dtrDataTime)?default("")}</em></span>
					<p>${(discussionTeacherReply.dtrContent)?default("")}</p>
				</div>
			</div>
		</#list>
	</#if>
	<!--回复1End-->
	<!--回复2Begin-->
	<#if discussionStudentReplys?exists>
		<#list discussionStudentReplys as discussionStudentReply>
			<div class="chatBox replyBox">
				<div class="chatMsg">
					<em class="iconListImg detail_person"></em>
					<span>${(discussionStudentReply.studentInfo.siRealName)?default("")}同学<em>${(discussionStudentReply.dsrDataTime)?default("")}</em></span>
					<p>${(discussionStudentReply.dsrContent)?default("")}</p>
				</div>
			</div>
		</#list>
	</#if>
	<!--回复2End-->
	<!--回复3Begin-->
	<#if discussionStudentReplySelfs?exists>
		<#list discussionStudentReplySelfs as discussionStudentReply>
			<div class="chatBox replyBox">
				<div class="chatMsg">
					<em class="iconListImg zhiding"></em>
					<span>自己<em>${(discussionStudentReply.dsrDataTime)?default("")}</em></span>
					<p>${(discussionStudentReply.dsrContent)?default("")}</p>
				</div>
			</div>
		</#list>
	</#if>
	<#if discussionTopic.dtSign=='T'>
		<!--回复3End-->
		<div class="reply">
			<input type="text" id="discussionTopicText" class="reply_text" placeholder="在这里输入您需要的内容"/>
			<div class="reply_send" onclick="formStudentDiscussionTopicAdd('${discussionTopic.dtId}','contentYwl','${ctx}/discussionTopic/showDiscussionTopicApp?discussionTopicId=${discussionTopic.dtId}')">
			<span>发送</span>
		</div>
	<#else>
		<div class="reply">
			<lable>教师未开启议题或者议题已经关闭</label>
			<div class="reply_send" >
			<span>发送</span>
		</div>
	</#if>
</div>
</div>
