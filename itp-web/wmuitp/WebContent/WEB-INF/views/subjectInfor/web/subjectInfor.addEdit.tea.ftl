<div class="pageFormContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "formDatasAdd('subjectInforAddForm');return false;"><span>保存</span></a></li>
		</ul>
	</div>
	<form id="subjectInforAddForm" method="POST" class="pageForm required-validate" action="${ctx}/subjectInfor/subjectInforAddTea" style="width:350px;float:left">
		
		<p style="width:340px;float:left">
			<label style="width:70px">题目：</label>
			<input type="text" size="30" value = "${(subjectInfor.csTitle)?default("")}"  name = "csTitle" class="required"/>
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">所属进度：</label> 
			<a class="btnLook" href="${ctx}/courseChapter/showCourseChapterLookUpTea" lookupGroup="" >查找带回</a>
			<input  name="courseChapterName" type="text" readonly="readonly" class="required" value = "${(subjectInfor.courseChapter.course.CName)?default("")}${(subjectInfor.courseChapter.ccName)?default("")}" >
			<input  name="courseChapterID" type="hidden"  value = "${(subjectInfor.courseChapter.ccId)?default("")}" />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">所属老师：</label> 
			<input type="text" size="30" value = "${teacherInfo.tiName}"  readonly="readonly" />
			<input type="hidden" size="30" value = "${teacherInfo.tiId}"  name = "teacherInfoID" />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">A：</label>
			<input type="text" size="30" value = "${(subjectInfor.csA)?default("")}"  name = "csA" />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">B：</label>
			<input type="text" size="30" value = "${(subjectInfor.csB)?default("")}"  name = "csB" />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">C：</label>
			<input type="text" size="30" value = "${(subjectInfor.csC)?default("")}"  name = "csC" />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">D：</label>
			<input type="text" size="30" value = "${(subjectInfor.csD)?default("")}"  name = "csD" />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">E：</label>
			<input type="text" size="30" value = "${(subjectInfor.csE)?default("")}"  name = "csE" />
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">答案：</label>
			<select  name = "csAnswer">
			<#if subjectInfor.csAnswer?exists>
				<option value="${(subjectInfor.csAnswer)?default("")}">${(subjectInfor.csAnswer)?default("")}</option>
				<#else>
				<option >请选择答案</option>
			</#if>
			  <option value="A">A</option>
			  <option value="B">B</option>
			  <option value="C">C</option>
			  <option value="D">D</option>
			  <option value="E">E</option>
			</select>
		</p>
		<p style="width:340px;float:left">
			<label style="width:70px">答案解析：</label>
			<input type="text" size="30" value = "${(subjectInfor.csAnswerExplain)?default("")}"  name = "csAnswerExplain" class="required"/>
		</p>
		<input type="hidden" size="30" value = "${(subjectInfor.siId)?default("")}"  name = "siId" />
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick = "formDatasAdd('subjectInforAddForm');return false;">保存</button>
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
