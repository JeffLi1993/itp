<div class="pageFormContent" layoutH="20">
	<form id="addCourseForm" method="POST" class="pageForm required-validate" action="${ctx}/course/courseAdd">
		
		<input type="hidden" name="CId" value="${(course.CId)?default("")}"/>
		
		<p>
			<label>课程名字：</label>
			<input type="text" size="30" name= "CName" value="${(course.CName)?default("")}" class="required"/>
		</p>
		
		<p>
			<label>课程描述：</label>
			<input type="text" size="30" name = "CDescription" value="${(course.CDescription)?default("")}" class="required"/>
		</p>
		
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick = "formDatasAdd('addCourseForm');return false;">保存</button>
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
