<div class="pageFormContent" layoutH="20">
	<form id="addTeacherFormXls" method="POST" action="${ctx}/teacherInfo/teacherAddXls"  enctype="multipart/form-data">
		<p>
			<label>xls文件：</label>
			<input type="file" id="xlsFile" name= "xlsFile"   />
		</p>
		<p>
			<label>样例下载：</label>
			<a href="/wmuitphtml/demo/老师样例一.xls">样例一</a>
		</p>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit"  onclick = "formXlsAdd('addTeacherFormXls');return false;" >保存</button>
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
