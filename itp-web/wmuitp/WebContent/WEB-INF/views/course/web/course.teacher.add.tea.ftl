<div class="pageFormContent" layoutH="0">
	<div class="panelBar" style="width:430px;">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "formDatasAdd('courseTeacherRelationAddForm');return false;"><span>保存</span></a></li>
		</ul>
	</div>
	<form id="courseTeacherRelationAddForm" method="POST" class="pageForm required-validate" action="${ctx}/courseTeacherRelation/courseTeacherRelationAddTea" style="float:left">
		<input type="hidden" size="30" value = "${(courseTeacherRelation.ctrId)?default("")}"  name = "ctrId" />
		<table cellpadding="0" cellspacing="0" border="0" width="430" class="tableShowCont">
			<tr>
				<td>		
					<label style="width:90px">所属基本课程：</label> 
					<a class="btnLook" style="float:right;"  href="${ctx}/course/courseLookup" lookupGroup="" width="650">查找带回</a>
				</td>
				<td>
					<input  name="courseName" type="text" readonly="readonly" class="required" value="${(courseTeacherRelation.course.CName)?default('')}">
					<input  name="courseID" type="hidden" value="${(courseTeacherRelation.course.CId)?default('')}"/>
				</td>
			</tr>
			<tr>
				<td>
					<label style="width:90px">所属老师：</label> 
				</td>
				<td>
					<input  name="teacherInfoName" type="text" readonly="readonly" class="required" value="${teacherInfo.tiName}" >
					<input  name="teacherInfoID" type="hidden" value="${teacherInfo.tiId}"  />
				</td>
			</tr>
			<tr>
				<td>
					<label style="width:70px">备注摘录：</label>
				</td>
				<td>
					<textarea  cols="40" rows="3"  name="ctrSign">${(courseTeacherRelation.ctrSign)?default("")}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="formBar">
						<ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit" onclick = "formDatasAdd('courseTeacherRelationAddForm');return false;">保存</button>
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
				</td>
			</tr>
		</table>
	</form>
</div>
