<input id="ajaxUrlId" type="hidden" value="${ctx}/courseInfo/getCourseInfoByCIIIdApp?courseInfoId=${paperSubjectRelations[0].paperInfo.courseInfo.ciId}"/>

<#if esSign == "T" && dateSign == "F">
	<div data-role="content"> 
		<div class="xmyContent">
			<div class="stuExam">
				<h2>${paperSubjectRelations[0].paperInfo.piName}——${paperSubjectRelations[0].paperInfo.teacherInfo.tiName}</h2>
			</div>
			<div class="stuExamDetail xclassList">
				<#if paperSubjectRelations?exists>
					<#list paperSubjectRelations as paperSubjectRelation>
						<fieldset data-role="controlgroup">  
							<h3>第${paperSubjectRelation_index+1}题：${(paperSubjectRelation.subjectInfor.csTitle)?default("")}</h3>
							<input type="radio" name="examStudentResult_${paperSubjectRelation_index+1}" id="map1" value="${paperSubjectRelation.subjectInfor.siId},A"  />  
							<label for="map1" data-theme="b">A：${(paperSubjectRelation.subjectInfor.csA)?default("")}</label>  
							<input type="radio" name="examStudentResult_${paperSubjectRelation_index+1}" id="map2" value="${paperSubjectRelation.subjectInfor.siId},B"  />  
							<label for="map2" data-theme="b">B：${(paperSubjectRelation.subjectInfor.csB)?default("")}</label>  
							<#if paperSubjectRelation.subjectInfor.csC !="">
								<input type="radio" name="examStudentResult_${paperSubjectRelation_index+1}" id="map3" value="${paperSubjectRelation.subjectInfor.siId},C"  />  
								<label for="map3" data-theme="b">C：${(paperSubjectRelation.subjectInfor.csC)?default("")}</label>
							</#if>
								<#if paperSubjectRelation.subjectInfor.csD !="">
								<input type="radio" name="examStudentResult_${paperSubjectRelation_index+1}" id="map4" value="${paperSubjectRelation.subjectInfor.siId},D"  />  
								<label for="map4" data-theme="b">D：${(paperSubjectRelation.subjectInfor.csD)?default("")}</label>  
							</#if>
							<#if paperSubjectRelation.subjectInfor.csE !="">
								<input type="radio" name="examStudentResult_${paperSubjectRelation_index+1}" id="map5" value="${paperSubjectRelation.subjectInfor.siId},E"  />  
								<label for="map5" data-theme="b">E：${(paperSubjectRelation.subjectInfor.csE)?default("")}</label>  
							</#if>
						</fieldset>
					</#list>
				</#if>
					<div id="popdiv" data-role="popup" data-inline="true" data-transition="pop" data-position-to="window" data-theme="b" style="max-width:400px;" class="ui-corner-all"><h1>我是弹出层</h1></div>
					<button  onclick = "validateExam('${paperSubjectRelations?size}','${paperInfoId}');return false;">提交试卷</button>
			</div>
		</div>
	</div>
<#else >
	<div data-role="content"> 
		<div class="xmyContent">
			<div class="xclassTestPoint">
				<em></em>
				<h2>温馨提示</h2>
				<p>测试卷已经提交或者已经超过答题时间</p>
			</div>
			<#if paperSubjectRelations?exists>
				<#if examStudentResults?exists>
					<#list paperSubjectRelations as paperSubjectRelation>
						<p>第${paperSubjectRelation_index+1}题：${(paperSubjectRelation.subjectInfor.csTitle)?default("")}<#if dateSign == "T">( ${(paperSubjectRelation.subjectInfor.csAnswer)} )</#if></p>
						<ul data-role="listview" data-inset="true">
							<li><span <#if examStudentResults[paperSubjectRelation_index].esrContent == "A"> style="color:red" </#if> >A：${(paperSubjectRelation.subjectInfor.csA)?default("")}</span></li>
							<li><span<#if examStudentResults[paperSubjectRelation_index].esrContent == "B"> style="color:red" </#if>>B：${(paperSubjectRelation.subjectInfor.csB)?default("")}</span></li>
							<#if paperSubjectRelation.subjectInfor.csC !="">
								<li><span <#if examStudentResults[paperSubjectRelation_index].esrContent == "C"> style="color:red" </#if>>C：${(paperSubjectRelation.subjectInfor.csC)?default("")}</span></li>
							</#if>
							<#if paperSubjectRelation.subjectInfor.csD !="">
								<li><span <#if examStudentResults[paperSubjectRelation_index].esrContent == "D"> style="color:red" </#if>>D：${(paperSubjectRelation.subjectInfor.csD)?default("")}</span></li>
							</#if>
							<#if paperSubjectRelation.subjectInfor.csE !="">
								<li><span <#if examStudentResults[paperSubjectRelation_index].esrContent == "E"> style="color:red" </#if>>E：${(paperSubjectRelation.subjectInfor.csE)?default("")}</span></li>
							</#if>
						</ul>
					</#list>
				</#if>
			</#if>
		</div>
	</div>
</#if>
