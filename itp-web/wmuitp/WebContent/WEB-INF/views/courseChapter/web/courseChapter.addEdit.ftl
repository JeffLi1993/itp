<div class="pageFormContent" layoutH="20">
	
	<form id="courseChapterAddForm" method="POST" class="pageForm required-validate" action="${ctx}/courseChapter/courseChapterAdd" style="float:left">
		<input type="hidden" size="30" value = "${(courseChapter.ccId)?default("")}"  name = "ccId" />
		<table cellpadding="0" cellspacing="0" border="0" width="430" class="tableShowCont">
			<tr>
				<td width="80">
					<label style="width:80px;">所属课程：</label>
					<a class="btnLook" style="float:right;" href="${ctx}/course/courseLookup" lookupGroup="" width="650">查找带回</a>
				</td>
				<td>
					<div  class="select">
						<input  name="courseName" type="text" readonly="readonly" class="required" value="${(courseChapter.course.CName)?default("")}">
						<input  name="courseID" type="hidden" value="${(courseChapter.course.CId)?default("")}"/>
					</div>
				</td>
			</tr>
			<tr>
				<td><label>进度名称：</label></td>
				<td>
					<input type="text" size="30" value = "${(courseChapter.ccName)?default("")}"  name = "ccName" class="required"/>
				</td>
			</tr>
			<tr>
				<td><label>进度备注：</label></td>
				<td>
					<input type="text" size="30" value = "${(courseChapter.ccSign)?default("")}"  name = "ccSign" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="formBar">
						<ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit" onclick = "formDatasAdd('courseChapterAddForm');return false;">保存</button>
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
