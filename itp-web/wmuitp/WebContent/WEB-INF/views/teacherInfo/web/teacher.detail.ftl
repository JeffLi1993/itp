
<div class="pageFormContent" >
		<p style="width:340px;float:left">
			<label style="width:70px">用户名：</label>
			<lable>${(teacherInfo.userLogin.ulName)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">密码：</label>
			<lable>${(teacherInfo.userLogin.ulPassword)?default("")}</lable>
		</p>
	
		<p style="width:340px;float:left">
			<label style="width:70px">学院名称：</label>
			<lable>${(teacherInfo.professionInfo.piCollege)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">专业名称：</label>
			<lable>${(teacherInfo.professionInfo.piProfession)?default("")}</lable>
		</p>

		<p style="width:340px;float:left">
			<label style="width:70px">教师姓名：</label>
			<lable>${(teacherInfo.tiName)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">教师职称：</label>
			<lable>${(teacherInfo.tiJob)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">教师年龄：</label>
			<lable>${(teacherInfo.tiAge)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">教师标识：</label>
			<lable>${(teacherInfo.tiSign)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">教师技能详情：</label>
			<lable>${(teacherInfo.tiSkills)?default("")}</lable>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">教师照片：</label>
			<#if teacherInfo.tiAddress == "" >
				<img src="/wmuitphtml/teacherImg/default.jpg" alt="照片加载中" ></img>
			<#else>
				<img src="/wmuitphtml/teacherImg/${(teacherInfo.tiAddress)?default("")}" alt="照片加载中" ></img>
			</#if>
		</p>
</div>
