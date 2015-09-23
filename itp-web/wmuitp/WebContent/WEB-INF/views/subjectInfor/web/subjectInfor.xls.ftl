<div class="pageFormContent" layoutH="20">
	<form id="addStudentFormXls" method="POST" action="${ctx}/subjectInfor/subjectInforAddXlsTea"  enctype="multipart/form-data">
		<p>
		    <label>课程进度：</label>
		    <select name="courseChaptersid">
				<#if courseChapters?exists>
					<#list courseChapters as courseChapter>
					  <option value="${(courseChapter.ccId)?default("")}" >${(courseChapter.ccName)?default("")}</option>
					</#list>
				</#if>
			</select>
		</p>
		<p>
			<label>xls文件：</label>
			<input type="file" id="xlsFile" name= "xlsFile" accept="image/xls" />
		</p>
		<p>
			<label>样例下载：</label>
			<a href="/wmuitphtml/demo/试题样例一.xls">样例一</a>
		</p>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit"  onclick = "formXlsAdd('addStudentFormXls');return false;" >保存</button>
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
