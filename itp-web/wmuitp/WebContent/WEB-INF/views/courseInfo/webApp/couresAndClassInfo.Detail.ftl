<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>提示</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
	<body>
		<div data-role="content"> 
			<header>
				<div class="stuHeadBack headBack">            
					<a href="#" data-rel="back"  >
						<span>主页</span>
					</a>
				</div>
			</header>
			<div class="xmyContent" id="contentYwl1">
				<#if couresAndClassInfos?exists>
					<#list couresAndClassInfos as couresAndClassInfo>
						<div class="xmyClassA kaoQinBox">
							<h3>${couresAndClassInfo.className}</h3>
							<div class="xmyClassAName">
								<div class="testResult">
									<div class="testOneQuestion">
										<div class="testOneQuestion_title">
											<p><em class="iconListImg teaPerson"></em>应到人数：<span>${couresAndClassInfo.classStudentNumber}</span>人</p>
											<p><em class="iconListImg teaPersonGrey"></em>未到人数：<span>${couresAndClassInfo.classStudentNumberAbsent}</span>人</p> 
											<p><em class="iconListImg teaPersonGrey"></em>实到人数：<span>${couresAndClassInfo.classStudentNumberAttend}</span>人</p>
										</div>
										<!--出勤比例 Begin-->
										<div class="testAnswer">
											<p>出勤比例</p>
											<div class="testAnswerBox">
												<div class="testAnswerExp">${couresAndClassInfo.attendRate}%</div>
												<div class="testAnswerShow" style="width: ${couresAndClassInfo.attendRate}%;"></div>
											</div>
										</div>
										<!--出勤比例 End-->
									<!--查看缺勤详情Begin-->
									<#if couresAndClassInfo.attendRate=="100.0">
										<div class="lookForDetail allArrive">
											<a href="#"s >缺勤学生详情</a>
										</div>							
									<#else>
										<div class="lookForDetail">
											<a href="#" onclick="divload('contentYwl1','${ctx}/studentCourseRelation/getAbsentDetailApp?courseInfoId=${courseInfoId}&classId=${couresAndClassInfo.classId}')">缺勤学生详情</a>
										</div>	
									</#if>
									<!--查看缺勤详情End-->
									</div>
								</div>
							</div>
						</div>
					</#list>
			    </#if>
			</div>

		</div>
	</body>
</html>
