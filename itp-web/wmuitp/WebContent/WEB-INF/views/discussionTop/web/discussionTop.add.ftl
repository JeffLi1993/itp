<div class="pageFormContent" layoutH="0">
	<div class="panelBar" style="width:430px;">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "formDatasAdd('discussionTopicAddForm');return false;"><span>保存</span></a></li>
		</ul>
	</div>
	<form id="discussionTopicAddForm" method="POST" class="pageForm required-validate" action="${ctx}/discussionTopic/discussionTopicAdd" style="float:left">
		<input type="hidden" size="30" value = "${(discussionTopic.dtId)?default("")}"  name = "dtId" />
		<table cellpadding="0" cellspacing="0" border="0" width="430" class="tableShowCont">
			<tr>
				<td>
					<label style="width:90px">所属基本课程：</label> 
					<a class="btnLook" style="float:right;" href="${ctx}/courseInfo/courseInfoLookUp" lookupGroup="" width="650">查找带回</a>
				</td>
				<td>
					<input  name="courseName" type="text" readonly="readonly" class="required" value="${(discussionTopic.courseInfo.courseTeacherRelation.course.CName)?default('')}">
					<input  name="courseInfoId" type="hidden" value="${(discussionTopic.courseInfo.courseTeacherRelation.course.CId)?default('')}"/>
				</td>
			</tr>
			<tr>
				<td><label style="width:90px">议题题目：</label></td>
				<td>
					<input  name="dtName" type="text"  class="required" value="${(discussionTopic.dtName)?default('')}">
				</td>
			</tr>
			<tr>
				<td><label style="width:70px">议题内容：</label></td>
				<td><textarea  cols="40" rows="3"  name="dtContent">${(discussionTopic.dtContent)?default("")}</textarea><td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="formBar">
						<ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit" onclick = "formDatasAdd('discussionTopicAddForm');return false;">保存</button>
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
