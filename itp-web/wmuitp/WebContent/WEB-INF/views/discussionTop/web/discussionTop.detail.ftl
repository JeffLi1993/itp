
<div class="pageFormContent"  style="height: 600px;">
		<p style="width:340px;float:left">
			<label style="width:70px">议题名称：</label>
			<lable>${(discussionTopic.dtName)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">课程名称：</label>
			<lable>${(discussionTopic.courseInfo.courseTeacherRelation.course.CName)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">议题时间：</label>
			<lable>${(discussionTopic.ciDateTime)?default("")}</lable>
		</p>
	
		<p style="width:340px;float:left">
			<label style="width:70px">是否置顶：</label>
			<lable>${(discussionTopic.dtTop)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">是否加精：</label>
			<lable>${(discussionTopic.dtFine)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">浏览次数：</label>
			<lable>${(discussionTopic.dtScan)?default("0")}</lable>
		</p>

		<p style="width:340px;float:left">
			<label style="width:70px">回复次数：</label>
			<lable>${(discussionTopic.dtReply)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">议题备注：</label>
			<lable>${(discussionTopic.dtSign)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">议题标识：</label>
			<lable>${(discussionTopic.dtState)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">教师姓名：</label>
			<lable>${(discussionTopic.courseInfo.courseTeacherRelation.teacherInfo.tiName)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">教师专业：</label>
			<lable>${(discussionTopic.courseInfo.courseTeacherRelation.teacherInfo.professionInfo.piProfession)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">教师学院：</label>
			<lable>${(discussionTopic.courseInfo.courseTeacherRelation.teacherInfo.professionInfo.piCollege)?default("")}</lable>
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">议题内容：</label>
			<textarea rows="6" cols="60" readonly="readonly">${(discussionTopic.dtContent)?default("")}</textarea>
		</p>
</div>
