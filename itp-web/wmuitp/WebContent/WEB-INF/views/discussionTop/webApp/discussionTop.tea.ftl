<!--发送框-->
<div class="reply">
	<input type="text" class="reply_text" name="discussionTopicText" id="discussionTopicText"/>
	<div class="reply_send  reply_sendChange" onclick="formTeacherDiscussionTopicAdd('${discussionTopic.dtId}','contentYwl')">
		<span>发送</span>
	</div>
</div>

<div id="popdiv" data-role="popup" data-inline="true" data-transition="pop" data-position-to="window" data-theme="b" style="max-width:400px;" class="ui-corner-all"><h1>我是弹出层</h1></div>

<div class="xmyContent2 replyResult">	
	<div class="chatBox">
		<h3>
			${discussionTopic.dtName}
		</h3>			
		<div class="chatMsg">
			<span>${discussionTopic.courseInfo.courseTeacherRelation.teacherInfo.tiName}<em>${(discussionTopic.dtDateTime)?substring(0,10)}</em></span>
		</div>
		<p>
			${discussionTopic.dtContent}
		</p>
		<!-- 按钮开始 -->
			 开启该议题：
 	    	 	<label for="switch"><h2></h2></label>
	    		<select name="switch" id="switchPaperSignId" data-role="slider" onchange="changeDiscussionSign(this.value,${discussionTopic.dtId})" >
		    		<option value="on"
		    			<#if discussionTopic.dtSign == 'F'>
		      			 selected </#if>>
		      			 关闭
		      		</option>
		     		<option value="off"
		     			<#if discussionTopic.dtSign != 'F'>
		     			selected</#if>>
		     			开启
		     		</option>
	    		</select>
		<!-- 按钮结束 -->
	</div>
	
	<!--回复1Begin-->
	<#if discussionStudentReplys?exists>
		<#list discussionStudentReplys as discussionStudentReply>
			<div class="chatBox replyBox">
				<div class="chatMsg">
					<em class="iconListImg detail_person"></em>
					<span>${(discussionStudentReply.studentInfo.siRealName)?default("")}同学<em>${(discussionStudentReply.dsrDataTime)?default("")}</em></span>
					<p>${(discussionStudentReply.dsrContent)?default("")}</p>
					<div class="teaChatList" id="stu${discussionStudentReply.dsrId}">
						<#if discussionStudentReply.dsrTop=="T">
							<a  onclick="changeTop('stu${discussionStudentReply.dsrId}','${discussionStudentReply.dsrId}')" href="#"><em class="noDingIcon"></em>取消置顶</a>
						<#else>
							<a onclick="changeTop('stu${discussionStudentReply.dsrId}','${discussionStudentReply.dsrId}')"  href="#"><em class="dingIcon"></em>置顶</a>
						</#if>
					</div>
				</div>
			</div>
		</#list>
	</#if>
	<!--回复1End-->

	<!--回复2Begin-->
	<#if discussionTeacherReplys?exists>
		<#list discussionTeacherReplys as discussionTeacherReply>
			<div class="chatBox replyBox">
				<div class="chatMsg">
					<em class="iconListImg teaPerson"></em>
					<span>自己<em>${(discussionTeacherReply.dtrDataTime)?default("")}</em></span>
					<p>${(discussionTeacherReply.dtrContent)?default("")}</p>
				</div>
			</div>
		</#list>
	</#if>
	<!--回复2End-->
</div>
