<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>课程详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet"  href="/wmuitp/js/webApp/jqMobile/css/jquery.mobile-1.3.2.css">
    <script src="/wmuitp/js/webApp/exam/exam.js"></script>
	<script src="/wmuitp/js/common/jquery.form.js"></script>
    <script src="/wmuitp/js/webApp/jqMobile/js/jquery.js"></script>
    <script src="/wmuitp/js/webApp/jqMobile/js/jquery.mobile-1.3.2.js"></script>
    <link rel="stylesheet"  href="/wmuitp/js/webApp/app/css/common.css">
    <link rel="stylesheet"  href="/wmuitp/js/webApp/app/css/stu.css">
    <link rel="stylesheet"  href="/wmuitp/js/webApp/app/css/teach.css">
</head>
<body>
<div data-role="page">
    <div data-role="header" data-theme="d">    
        <h1>课堂详细信息</h1>
    </div>
        <div data-role="content"> 
        	<header>
				<div class="teachHeadBack headBack" onclick="divload('contentYwl','${ctx}/courseInfo/getCourseInfoByCIIdTeaAppAjax')"><span>主页</span></div>
				<h1>课程信息</h1>
			</header>
			<div id="contentYwl">
				<div class="xmyTab xmyTeachTab" >
					<ul>
						<li class="xtabChooes"><a href="#" onclick="divload('contentYwl','${ctx}/courseInfo/getCourseInfoByCIIdTeaAppAjax')">课程详情</a></li>
						<li><a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoByCIIdTeaApp')">课堂测试</a></li>
						<li><a href="#" onclick="divload('contentYwl','${ctx}/discussionTopic/getDiscussionTopicByCIIdAppTea')">议题</a></li>
					</ul>	
				</div>
					<div class="xmyContent" id="AjaxTeaContent">
						<div class="xmyindex">
				            <#if courseInfos?exists>
								<#list courseInfos as courseInfo>
									<div class="xclassList">
										<div class="xclassList_imgBox">
											<a href="${ctx}/studentCourseRelation/getAttendanceApp?courseInfoId=${courseInfo.ciId}"  > 
												<img src="/wmuitp/js/webApp/app/image/replaceImg1.jpg" />
											</a>	
												<p>${courseInfo.courseTeacherRelation.course.CName}</p>
										</div>
										<div class="xclassList_detail">
											<ul>
												<li>
													<em class="index_person"></em><span>${courseInfo.courseTeacherRelation.teacherInfo.tiName}</span>
												</li>
												<li>
													<em class="index_time"></em><span>${(courseInfo.ciDateTime)?substring(0,16)}</span>
												</li>
												<li>
													<#if (courseInfo.ciState) == "T" >
														<em class="index_flag1"></em><span>开启</span>
													<#else>
														<em class="index_flag2"></em><span>关闭</span>
													</#if>
												</li>
											</ul>				
										</div>
									</div>
								</#list>
							<#else>
								<div class="xclassTestPoint">
									<em></em>
									<h2>温馨提示</h2>
									<p>目前处于无课状态!</p>
								</div>
							</#if>
			    	   </div>
					</div>
				</div>
			</div>
        </div>
    </div>  
</body>
</html>
