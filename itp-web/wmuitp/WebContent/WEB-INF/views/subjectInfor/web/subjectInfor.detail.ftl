<div class="pageContent" sytle="height: 811px;">
<table class="table" width="99%" layoutH="130" >
	<tbody>
		<#if subjectInfor?exists>
			<tr><label style="width:70px">试题题目：</label></tr><br/>
			<tr>${(subjectInfor.csTitle)?default("")}</tr><br/>
			<tr><label style="width:70px">A：</label></tr>
			<tr>${(subjectInfor.csA)?default("")}</tr><br/>
			<tr><label style="width:70px">B：</label></tr>
			<tr>${(subjectInfor.csB)?default("")}</tr><br/>
			<tr><label style="width:70px">C：</label></tr>
			<tr>${(subjectInfor.csC)?default("")}</tr><br/>
			<tr><label style="width:70px">D：</label></tr>
			<tr>${(subjectInfor.csD)?default("")}</tr><br/>
			<tr><label style="width:70px">E：</label></tr>
			<tr>${(subjectInfor.csE)?default("")}</tr><br/>
			<tr><label style="width:70px">答案及解析：</label></tr><br/>
			<tr>${(subjectInfor.csAnswer)?default("")}:${(subjectInfor.csAnswerExplain)?default("")}</tr>
		</#if>
	</tbody>
</table>
</div>
