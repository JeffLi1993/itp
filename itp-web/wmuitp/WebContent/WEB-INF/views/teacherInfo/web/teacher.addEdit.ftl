
<div class="pageFormContent" >
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "formDatasAdd('teacherInfoAddForm');return false;"><span>保存</span></a></li>
		</ul>
	</div>
	<form id="teacherInfoAddForm" method="POST" class="pageForm required-validate" action="${ctx}/teacherInfo/teacherInfoAdd"  enctype="multipart/form-data" >
		<p style="width:340px;float:left">
			<label style="width:70px">用户名：</label>
			<input type="text" size="30" value = "${(teacherInfo.userLogin.ulName)?default("")}"  name = "ulName" class="required"/>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:300px">密码：123456(初始密码)</label>
			<!--input type="text" size="30" value = "${(teacherInfo.userLogin.ulPassword)?default("")}"  name = "ulPassword" class="required"/-->
		</p>
	
		<p style="width:340px;float:left">
			<label style="width:70px">教师姓名：</label>
			<input type="text" size="30" value = "${(teacherInfo.tiName)?default("")}"  name = "tiName" class="required"/>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">教师职称：</label>
			<input type="text" size="30" value = "${(teacherInfo.tiJob)?default("")}"  name = "tiJob"  />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">教师年龄：</label>
			<input type="text" size="30" value = "${(teacherInfo.tiAge)?default("")}"  name = "tiAge" />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">技能详情：</label>
			<input type="text" size="30" value = "${(teacherInfo.tiSkills)?default("")}"  name = "tiSkills" />
		</p>
		<p>
			<label style="width:70px">专业名称：</label>
			<select class="" name="professionInfoID">
				<#if teacherInfo.professionInfo.piProfession == "" >
				    <option value="">选择专业名称</option>
				<#else>
				    <option value="${teacherInfo.professionInfo.piId}">${teacherInfo.professionInfo.piProfession}</option>
				</#if>
				<#if professionInfos?exists>
					<#list professionInfos as professionInfo>
						<option value="${professionInfo.piId}">${professionInfo.piProfession}</option>
					</#list>
				</#if>
			</select>
		</p>
		<p>
			<label style="width:70px">头像图片：</label>
			<input type="file" name="file" /> 
		</p>
		<p style="width:340px;float:left">
			<input type="hidden" size="30" value = "${(teacherInfo.tiId)?default("")}"  name = "tiId" />
		</p>
		<p style="width:340px;float:left">
			<input type="hidden" size="30" value = "${(teacherInfo.userLogin.ulId)?default("")}"  name = "ulId" />
		</p>
		
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick = "formDatasAdd('teacherInfoAddForm');return false;">保存</button>
						</div>	
					</div>
				</li>
				
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
		
	</form>
</div>
