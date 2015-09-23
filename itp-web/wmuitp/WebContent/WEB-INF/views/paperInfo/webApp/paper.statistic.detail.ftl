<header>
		<div class="teachHeadBack headBack" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoStatistic?paperInfoId=${paperInfoId}')" ><span>返回</span></div>
		<div class="headMore"></div>
		<h1>统计结果</h1>
</header>

<div class="xmyContent">
	<div class="xmyClassA">
		<h3>${(Detail.subjectInfor.csTitle)?default("")}</h3>
		<div class="xmyClassAName">
			<#if Details?exists>
				<#list Details as Detail>
					<!--班级分析 Begin-->
					<div class="testResult">
						<div class="testOneQuestion">
							<div class="testOneQuestion_title">
								<p>${(Detail.classInfoName)?default("")}</p>
							</div>
							<!--选项A Begin-->
							<div class="testAnswer">
								<p <#if Detail.subjectInfor.csAnswer == "A"> style="color:red" </#if> >选项A：${(Detail.subjectInfor.csA)?default("")}</p>
								<div class="testAnswerBox">
									<div class="testAnswerExp">${(Detail.arate)?default("")}%</div>
									<div class="testAnswerShow" style="width: ${(Detail.arate)?default("")}%;"></div>
								</div>
							</div>
							<!--选项A End-->
							<!--选项B Begin-->
							<div class="testAnswer">
								<p <#if Detail.subjectInfor.csAnswer == "B"> style="color:red" </#if> >选项B：${(Detail.subjectInfor.csB)?default("")}</p>
								<div class="testAnswerBox">
									<div class="testAnswerExp">${(Detail.brate)?default("0")}%</div>
									<div class="testAnswerShow" style="width: ${(Detail.brate)?default("0")}%;"></div>
								</div>
							</div>
							<!--选项B End-->
							<#if Detail.subjectInfor.csC !="">
								<!--选项C Begin-->
								<div class="testAnswer">
									<p <#if Detail.subjectInfor.csAnswer == "C"> style="color:red" </#if> >选项C：${(Detail.subjectInfor.csC)?default("")}</p>
									<div class="testAnswerBox">
										<div class="testAnswerExp">${(Detail.crate)?default("")}%</div>
										<div class="testAnswerShow" style="width: ${(Detail.crate)?default("")}%;"></div>
									</div>
								</div>
								<!--选项C End-->
							</#if>
							
							<#if Detail.subjectInfor.csD !="">
								<!--选项D Begin-->
								<div class="testAnswer">
									<p <#if Detail.subjectInfor.csAnswer == "D"> style="color:red" </#if> >选项D：${(Detail.subjectInfor.csD)?default("")}</p>
									<div class="testAnswerBox">
										<div class="testAnswerExp">${(Detail.drate)?default("0")}%</div>
										<div class="testAnswerShow" style="width: ${(Detail.drate)?default("0")}%;"></div>
									</div>
								</div>
								<!--选项D End-->
							</#if>
							<#if Detail.subjectInfor.csE !="">
								<!--选项E Begin-->
								<div class="testAnswer">
									<p <#if Detail.subjectInfor.csAnswer == "E"> style="color:red" </#if> >选项E：${(Detail.subjectInfor.csE)?default("")}</p>
									<div class="testAnswerBox">
										<div class="testAnswerExp">${(Detail.erate)?default("0")}%</div>
										<div class="testAnswerShow" style="width: ${(Detail.erate)?default("0")}%;"></div>
									</div>
								</div>
								<!--选项E End-->
							</#if>
						</div>
					</div>
					<!--班级的分析 end-->
				</#list>
			</#if>
		</div>
	</div>
</div>
