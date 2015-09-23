<div class="pageHeader" >
	<form rel="pagerFormClass"   id="pagerForm" method="post" action="${ctx}/classInfo/showClassInfoLookup" onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">
			<ul class="searchContent" >
				<input type="hidden" name="pageNum" 	value=${page.getPageNo()} />
				<input type="hidden" name="numPerPage" 	value=${page.getPageSize()} />
				<li>
					<label>所属专业:</label>
					<input class="textInput" name="professionName" alt="请用逗号分开" value="${(professionName)?default("")}" type="text">
				</li> 
				<li>
					<label>所属学院:</label>
					<input class="textInput" name="collegeName" alt="请用逗号分开" value="${(collegeName)?default("")}" type="text">
				</li> 
				<li>
					<label>班级名称:</label>
					<input class="textInput" name="className" alt="请用逗号分开" value="${(className)?default("")}" type="text">
				</li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				</ul>
			</div>
			
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="pageContent" >
		<div>
			<table class="table" layoutH="118"  targetType="dialog" width="100%">
				<thead>
					<tr>
						<th orderfield="classInfoIds">班级ID</th>
						<th orderfield="className">班级名称</th>
						<th orderfield="ciStudentSum">班级人数</th>
						<th orderfield="ciProfession">专业</th>
						<th orderfield="ciCollege">所属院校</th>
						<th width="30"><input type="checkbox" class="commonClassInfo" group="classInfoIds" onclick="checkAll(0)" /></th>
					</tr>
				</thead>
				<tbody>
					<#if page.getResult()?exists>
						<#list page.getResult() as commonClassInfo>
							<tr>
								<td>${commonClassInfo.ciId}</td>
								<td>${commonClassInfo.ciName}</td>
								<td>${commonClassInfo.ciStudentSum}</td>
								<td>${commonClassInfo.ciProfession}</td>
								<td>${commonClassInfo.ciCollege}</td>
								<td>
									<a class="btnSelect" href="javascript:$.bringBack({classInfoId:'${commonClassInfo.ciId}', className:'${commonClassInfo.ciName}'})" title="查找带回">选择</a>
								</td>
							</tr>
						</#list>
					</#if>
				</tbody>
			</table>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
	

</div>