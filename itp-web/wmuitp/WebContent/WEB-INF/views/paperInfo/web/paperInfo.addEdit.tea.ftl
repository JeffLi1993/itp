<div class="pageFormContent" >
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "formDatasAdd('paperInfoAddForm');return false;"><span>保存</span></a></li>
		</ul>
	</div>
	<form id="paperInfoAddForm" method="POST" class="pageForm required-validate" action="${ctx}/paperInfo/paperInfoAddTea" style="float:left;">
		<input type="hidden" size="30" value = "${(paperInfo.piId)?default("")}"  name = "piId" />
		<table cellpadding="0" cellspacing="0" border="0" width="430" class="tableShowCont">
			<tr>
				<td>
					<label style="width:70px">所属课程：</label>
					<a class="btnLook" style="float:right;" href="${ctx}/courseInfo/courseInfoLookUpByTeaSession" lookupGroup="" width="930" >查找带回</a>
				</td>
				<td> 
					<input  name="courseName" type="text" readonly="readonly" class="required"  >
					<input  name="courseInfoId" type="hidden"   />
				</td>
			</tr>
			<tr>
				<td>
					<label style="width:70px">练习名称：</label>
				</td>
				<td> 
					<input type="text" size="30" value = "${(paperInfo.piName)?default("")}"  name = "piName" class="required"/>
				</td>
			</tr>
			<tr>
				<td> </td>
				<td>
					<label style="width:80px;">选择题库</abel>
					<a class="btnLook" href="${ctx}/subjectInfor/subjectInforsForLookUp" width="1000" lookupGroup=""rel="subjectInforsForLookUp"  >查找带回</a> </dt>
				</td>
			</tr>
			<tr valign="top">
				<td><label>题目列表::</label></td>
				<td><textarea name="csTitle" readonly="true" rows="5" cols="60"  style="width:249px">${paperSubjectRelationNames}</textarea></td>
			</tr>
			<tr valign="top">
				<td><label>题目编号列表：</label></td>
				<td><textarea name="siId" readonly="true" rows="5" cols="60"  style="width:249px">${paperSubjectRelationIds}</textarea></td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick = "formDatasAdd('paperInfoAddForm');return false;">保存</button>
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
