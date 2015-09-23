<div class="xmyTab">
	<ul>
		<li class="xtabChooes"><a href="#"  >测试答案</a></li>
		<li ><a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoStatistic?paperInfoId=${paperInfoId}')">测试情况</a></li>
	</ul>	
</div>
<div data-role="content"> 
	<div class="xmyContent">
		<div class="stuExam">
			
			<h2>${paperSubjectRelations[0].paperInfo.piName}——${paperSubjectRelations[0].paperInfo.teacherInfo.tiName}</h2>
		</div>
		<div class="stuExamDetail xclassList">
			<#if paperSubjectRelations?exists>
				<div data-role="fieldcontain">
					 开启该测试卷：
	 	    	 	<label for="switch"><h2></h2></label>
		    		<select name="switch" id="switchPaperSignId" data-role="slider" onchange="changePaperSign(this.value,${paperInfoId})" >
			    		<option value="on"
			    			<#if paperInfo.piSign == "F">
			      			 selected </#if>>
			      			 关闭
			      		</option>
			     		<option value="off"
			     			<#if paperInfo.piSign != "F">
			     			selected</#if>>
			     			开启
			     		</option>
		    		</select>
	 			<div id="popdiv" data-role="popup" data-inline="true" data-transition="pop" data-position-to="window" data-theme="c" style="max-width:400px;" class="ui-corner-all"><h1>我是弹出层</h1></div>
			  	</div>
				<#list paperSubjectRelations as paperSubjectRelation>
       				<p>第${paperSubjectRelation_index+1}题：${(paperSubjectRelation.subjectInfor.csTitle)?default("")}</p>
       				<p>解释：${(paperSubjectRelation.subjectInfor.csAnswerExplain)}</p>
					<ul data-role="listview" data-inset="true">
						<li><span <#if paperSubjectRelation.subjectInfor.csAnswer == "A"> style="color:red" </#if> >A：${(paperSubjectRelation.subjectInfor.csA)?default("")}</span></li>
						<li><span <#if paperSubjectRelation.subjectInfor.csAnswer == "B"> style="color:red" </#if>>B：${(paperSubjectRelation.subjectInfor.csB)?default("")}</span></li>
						<#if paperSubjectRelation.subjectInfor.csC !="">
							<li><span <#if paperSubjectRelation.subjectInfor.csAnswer == "C"> style="color:red" </#if>>C：${(paperSubjectRelation.subjectInfor.csC)?default("")}</span></li>
						</#if>
						<#if paperSubjectRelation.subjectInfor.csD !="">
							<li><span <#if paperSubjectRelation.subjectInfor.csAnswer == "D"> style="color:red" </#if>>D：${(paperSubjectRelation.subjectInfor.csD)?default("")}</span></li>
						</#if>
						<#if paperSubjectRelation.subjectInfor.csE !="">
							<li><span <#if paperSubjectRelation.subjectInfor.csAnswer == "E"> style="color:red" </#if>>E：${(paperSubjectRelation.subjectInfor.csE)?default("")}</span></li>
						</#if>
					</ul>  
				</#list>
			</#if>
		</div>
	</div>	
</div>
