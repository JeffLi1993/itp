<#if discussionStudentReply.dsrTop=="T">
	<a  onclick="changeTop('stu${discussionStudentReply.dsrId}','${discussionStudentReply.dsrId}')" href="#"><em class="noDingIcon"></em>取消置顶</a>
<#else>
	<a onclick="changeTop('stu${discussionStudentReply.dsrId}','${discussionStudentReply.dsrId}')"  href="#"><em class="dingIcon"></em>置顶</a>
</#if>