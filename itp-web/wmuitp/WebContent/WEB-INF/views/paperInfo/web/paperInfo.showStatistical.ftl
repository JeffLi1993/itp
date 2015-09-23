<div class="pageFormContent" layoutH="20" style="width:600px;">
	<div class="panelBar" style="width: 430px">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "getExamStatisticalAddress()"><span>导出</span></a></li>
		</ul>
	</div>
	<form id="examStatisticalForm" method="POST" class="pageForm required-validate" action="${ctx}/paperInfo/examStatisticalAddress" style="float:left">

	<table cellpadding="0" cellspacing="0" border="0" width="430" class="tableShowCont">
		<tr>
			<td width="80">
				<label class="labelShowBox">开始时间：</label>
			</td>
			<td>
				<input type="text" value="" name="startDate" id="startDate" class="date textInput readonly valid focus"   dateFmt="yyyy-MM-dd" />
				<a class="inputDateButton" href="#" >选择</a>
			</td>
		</tr>
		
		<tr>
			<td width="80">
				<label class="labelShowBox">结束时间：</label>
			</td>
			<td>
				<input type="text" value="" name="endDate" id="endDate" class="date textInput readonly valid focus"  dateFmt="yyyy-MM-dd" />
				<a class="inputDateButton" href="#" >选择</a>
			</td>
		</tr>
		
		<tr>
			<td>
				<a class="btnLook" style="float:right;" href="${ctx}/courseTeacherRelation/showCourseTeacherRelationLookUp" lookupGroup="" >查找带回</a>
				<label class="labelShowBox" style="width:80px;">所属课程：</label> 
			</td>
			<td>
				<input  name="courseTeacherRelationName" type="text" readonly="readonly" class="required"  >
				<input  name="courseTeacherRelationID" id="courseTeacherRelationID" type="hidden"   />
			</td>
		</tr>
		<tr>
			<td>
				<h2 id="examStatistical"></h2>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<div class="formBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="button"  onclick = "getExamStatisticalAddress()" >保存</button>
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
		<tr>
	</table>	
	</form>
</div>
