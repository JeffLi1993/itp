<div class="pageFormContent" >
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "formDatasAdd('classInfoAddForm');return false;"><span>保存</span></a></li>
		</ul>
	</div>
	<form id="classInfoAddForm" method="POST" class="pageForm required-validate" action="${ctx}/classInfo/classInfoAdd" style="float:left">
		
		<p style="width:340px;float:left">
			<label style="width:70px">班级姓名：</label>
			<input type="text" size="30" value = "${(classInfo.ciName)?default("")}"  name = "ciName" class="required"/>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">班级人数：</label>
			<input type="text" size="30" value = "${(classInfo.ciStudentSum)?default("")}"  name = "ciStudentSum" class="digits" minlength="1"/>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">专业名称：</label>
			<input type="text" size="30" value = "${(classInfo.ciProfession)?default("")}"  name = "ciProfession" class="required"/>
		</p>
		
		<p style="width:340px;float:left">
			<label style="width:70px">所属学院：</label>
			<input type="text" size="30" value = "${(classInfo.ciCollege)?default("")}"  name = "ciCollege" class="required"/>
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">详情备注：</label>
			<input type="text" size="30" value = "${(classInfo.ciSign)?default("")}"  name = "ciSign" />
		</p>
		
		<p style="width:340px;float:left">
			<input type="hidden" size="30" value = "${(classInfo.ciId)?default("")}"  name = "ciId" />
		</p>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick = "formDatasAdd('classInfoAddForm');return false;">保存</button>
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
