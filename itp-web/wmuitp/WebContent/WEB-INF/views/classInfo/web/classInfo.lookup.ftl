<script>
function checkAll(num)
{
	if(num==0)
	{
		var clicknode=$("input.commonClassInfo");
		for(var x=0;x<clicknode.length;x++)
		{
			clicknode[x].checked=clicknode[0].checked;
		}
	}else
	{
		var clicknode=$("input.allClassInfo");
		for(var x=0;x<clicknode.length;x++)
		{
			clicknode[x].checked=clicknode[0].checked;
		}
	}
}
</script>

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
				<li><div class="button"><div class="buttonContent"><button type="button" multLookup="classInfoIds" warn="请选择班级">选择带回</button></div></div></li>
			</ul>
		</div>
		
	</div>
	</form>
</div>
<div class="pageContent">


	<div class="pageContent" >
	<div class="tabs" currentIndex="${(tabsNum)?default("1")}" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>常用的班级</span></a></li>
					<li><a href="javascript:;"><span>全部班级</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:256px;">
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
						<#if commonClassInfos?exists>
							<#list commonClassInfos as commonClassInfo>
								<tr>
									<td>${commonClassInfo.ciId}</td>
									<td>${commonClassInfo.ciName}</td>
									<td>${commonClassInfo.ciStudentSum}</td>
									<td>${commonClassInfo.ciProfession}</td>
									<td>${commonClassInfo.ciCollege}</td>
									<td><input class="commonClassInfo" type="checkbox" name="classInfoIds" value="{classInfoIds:'${commonClassInfo.ciId}', className:'${commonClassInfo.ciName}'}"/></td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
			</div>
			<div>
        		<table class="table" layoutH="118" targetType="dialog" width="100%">
					<thead>
						<tr>
							<th orderfield="classInfoIds">班级ID</th>
							<th orderfield="className">班级名称</th>
							<th orderfield="ciStudentSum">班级人数</th>
							<th orderfield="ciProfession">专业</th>
							<th orderfield="ciCollege">所属院校</th>
							<th width="30"><input type="checkbox" class="allClassInfo" group="classInfoIds" onclick="checkAll(1)" /></th>
						</tr>
					</thead>
					<tbody>
						<#if page.getResult()?exists>
							<#list page.getResult() as classInfo>
								<tr>
									<td>${classInfo.ciId}</td>
									<td>${classInfo.ciName}</td>
									<td>${classInfo.ciStudentSum}</td>
									<td>${classInfo.ciProfession}</td>
									<td>${classInfo.ciCollege}</td>
									<td><input class="allClassInfo" type="checkbox" name="classInfoIds" value="{classInfoIds:'${classInfo.ciId}', className:'${classInfo.ciName}'}"/></td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
				<div class="panelBar">
					<div class="pages">
						<span>显示</span>
						<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
							<option value="20" 	<#if numPerPage == 20>	selected</#if>>20	</option>
							<option value="50" 	<#if numPerPage == 50>	selected</#if>>50	</option>
							<option value="100" <#if numPerPage == 100>	selected</#if>>100	</option>
							<option value="200" <#if numPerPage == 200>	selected</#if>>200	</option>
						</select>
						<span>条，共${page.getTotalCount()}条</span>
					</div>
					<div class="pagination" targetType="dialog" totalCount=${page.getTotalCount()} numPerPage=${page.getPageSize()} pageNumShown="10" currentPage=${page.getPageNo()}></div>
				</div>
            </div>

		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
	

</div>