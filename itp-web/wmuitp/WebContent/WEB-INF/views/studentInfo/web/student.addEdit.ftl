<div class="pageFormContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "formDatasAdd('studentInfoAddForm');return false;"><span>保存</span></a></li>
		</ul>
	</div>
	<form id="studentInfoAddForm" method="POST" class="pageForm required-validate" action="${ctx}/studentInfo/studentInfoAdd" style="float:left">

		<p style="width:340px;float:left">
			<label style="width:70px">用户名：</label>
			<input type="text" size="30" value = "${(studentInfo.userLogin.ulName)?default("")}"  name = "ulName" class="required"/>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:300px">密码：123456(初始密码)</label>
			<!--input type="text" size="30" value = "${(studentInfo.userLogin.ulPassword)?default("")}"  name = "ulPassword" class="required"/-->
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">班级编号：</label>
			<select class="" name="classInfoId">
				<#if studentInfo.classInfo.ciName == "" >
				    <option value="">选择班级</option>
				<#else>
				    <option value="${studentInfo.classInfo.ciId}">${studentInfo.classInfo.ciName}</option>
				</#if>
				<#if classInfos?exists>
					<#list classInfos as classInfo>
						<option value="${classInfo.ciId}">${classInfo.ciName}</option>
					</#list>
				</#if>
			</select>
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">学生学号：</label>
			<input type="text" size="30" value = "${(studentInfo.siNum)?default("")}"  name = "siNum" class="required"/>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">真实姓名：</label>
			<input type="text" size="30" value = "${(studentInfo.siRealName)?default("")}"  name = "siRealName"  />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">学生性别：</label>
			<input type="text" size="30" value = "${(studentInfo.siSex)?default("")}"  name = "siSex" />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">学生信息：</label>
			<input type="text" size="30" value = "${(studentInfo.siInformation)?default("")}"  name = "siInformation" />
		</p>
		
		<p style="width:340px;float:left">
			<input type="hidden" size="30" value = "${(studentInfo.siId)?default("")}"  name = "siId" />
		</p>
		<p style="width:340px;float:left">
			<input type="hidden" size="30" value = "${(studentInfo.userLogin.ulId)?default("")}"  name = "ulId" />
		</p>
		
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick = "formDatasAdd('studentInfoAddForm');return false;">保存</button>
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
