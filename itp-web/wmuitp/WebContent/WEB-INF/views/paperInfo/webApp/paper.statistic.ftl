<div class="xmyTab">
	<ul>
		<li ><a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperSubjectRelationsByPIIdTeaApp?paperInfoId=${paperInfoId}')" >测试答案</a></li>
		<li class="xtabChooes"><a href="#">测试情况</a></li>
	</ul>	
</div>
<div data-role="content"> 
	<div class="xmyContent">
			<h2>以下为${(paperInfo.piDateTime)}发布的考试的考试结果</h2>
		<div class="stuExamDetail xclassList">
		<!--一个班级的分析结果Begin-->
		<#if answerDetails?exists>
			<div class="xmyClassA">
				<h3>汇总结果</h3>
				<div class="xmyClassAName">
					<#list answerDetails as answerDetail>
						<div class="testResult">
							<div class="testOneQuestion">
								<div class="testOneQuestion_title">
										<a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoStatisticDetail?paperInfoId=${paperInfoId}&subjectInfoId=${answerDetail.subjectInfor.siId}')"  >第${answerDetail_index+1}题：${(answerDetail.subjectInfor.csTitle)?default("")}</a>
								</div>
								<!--选项A Begin-->
								<div class="testAnswer">
									<p <#if answerDetail.subjectInfor.csAnswer == "A"> style="color:red" </#if> >选项A：${(answerDetail.subjectInfor.csA)?default("")}</p>
									<div class="testAnswerBox">
										<div class="testAnswerExp">${(answerDetail.arate)?default("")}%</div>
										<div class="testAnswerShow" style="width: ${(answerDetail.arate)?default("")}%;"></div>
									</div>
								</div>
								<!--选项A End-->
								<!--选项B Begin-->
								<div class="testAnswer">
									<p <#if answerDetail.subjectInfor.csAnswer == "B"> style="color:red" </#if> >选项B：${(answerDetail.subjectInfor.csB)?default("")}</p>
									<div class="testAnswerBox">
										<div class="testAnswerExp">${(answerDetail.brate)?default("")}%</div>
										<div class="testAnswerShow" style="width: ${(answerDetail.brate)?default("")}%;"></div>
									</div>
								</div>
								<!--选项B End-->
								<#if answerDetail.subjectInfor.csC !="">
									<!--选项	C Begin-->
									<div class="testAnswer">
										<p <#if answerDetail.subjectInfor.csAnswer == "C"> style="color:red" </#if> >选项C：${(answerDetail.subjectInfor.csC)?default("")}</p>
										<div class="testAnswerBox">
											<div class="testAnswerExp">${(answerDetail.crate)?default("")}%</div>
											<div class="testAnswerShow" style="width: ${(answerDetail.crate)?default("")}%;"></div>
										</div>
									</div>
									<!--选项C End-->
			            		</#if>
			            		<#if answerDetail.subjectInfor.csD !="">
				            		<!--选项D Begin-->
									<div class="testAnswer">
										<p <#if answerDetail.subjectInfor.csAnswer == "D"> style="color:red" </#if> >选项D：${(answerDetail.subjectInfor.csD)?default("")}</p>
										<div class="testAnswerBox">
											<div class="testAnswerExp">${(answerDetail.drate)?default("")}%</div>
											<div class="testAnswerShow" style="width: ${(answerDetail.drate)?default("")}%;"></div>
										</div>
									</div>
									<!--选项D End-->
			            		</#if>
				            	<#if answerDetail.subjectInfor.csE !="">
					            	<!--选项E Begin-->
									<div class="testAnswer">
										<p <#if answerDetail.subjectInfor.csAnswer == "E"> style="color:red" </#if> >选项E：${(answerDetail.subjectInfor.csE)?default("")}</p>
										<div class="testAnswerBox">
											<div class="testAnswerExp">${(answerDetail.erate)?default("")}%</div>
											<div class="testAnswerShow" style="width: ${(answerDetail.erate)?default("")}%;"></div>
										</div>
									</div>
									<!--选项E End-->
	       						</#if>
							</div>
						</div>
					</#list>
				</div>
			</div>
		<#else>
			<h3>温馨提示：</h3>
			<h3>暂时没人完成考试！</h3>
		</#if>
		</div>
	</div>
</div>