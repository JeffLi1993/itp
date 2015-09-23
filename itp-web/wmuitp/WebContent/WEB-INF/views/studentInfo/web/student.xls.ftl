<div class="pageFormContent" layoutH="20">
	<form id="addStudentFormXls" method="POST" action="${ctx}/studentInfo/studentInfoAddXls"  enctype="multipart/form-data">
		<p>
		    <label>班级名字：</label>
		    <a class="btnLook" style="float:right;" href="${ctx}/classInfo/showClassInfoLookupSignle"  lookupGroup=""   width="650" rel="showClassInfoLookup">查找带回</a>
		    <input type="text" 	name="className" readonly="readonly"/>
		    <input type="hidden" 	name="classInfoId"  />
		</p>
		<p>
			<label>xls文件：</label>
			<input type="file" id="xlsFile" name= "xlsFile"   />
		</p>
		<p>
			<label>样例下载：</label>
			<a href="/wmuitphtml/demo/学生样例一.xls">样例一</a>
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
