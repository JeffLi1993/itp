<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8">
    <title>课程详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div data-role="page" id="page">
        <div data-role="header" data-theme="d" >    
             <a data-role="button"  href="${ctx}/studentCourseRelation/getStudentCourseRelationsBySession?UlId=${stuId.ulId}&UserType=${stuId.ulSign}"  >主页</a>
            <h1>课堂详细信息</h1>
        </div>
        
        <div data-role="content" >
	        <header>
				<div class="stuHeadBack headBack">            
					<a href="#"  data-rel="back" >
						<span>主页</span>
					</a>
				</div>
				<h1>课程信息</h1>
			</header>
			<div id="contentYwl">
				<div class="xmyTab">
					<ul>
						<li class="xtabChooes"><a href="#" id="courseInfoButton" >课程详情</a></li>
						<li><a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoByCIIdApp?courseInfoId=${courseInfo.ciId}')">课堂测试</a></li>
						<li><a href="#"  onclick="divload('contentYwl','${ctx}/discussionTopic/getDiscussionTopicByCIIdApp?courseInfoId=${courseInfo.ciId}')">议题</a></li>
					</ul>	
				</div>
        		<div class="xmyContent">
					<div class="xclassList">
						<div class="xclassList_imgBox">
							<img src="/wmuitp/js/webApp/app/image/replaceImg1.jpg" />
							<p>${(courseInfo.courseTeacherRelation.course.CName)?default("")}</p>
						</div>
					</div>
					<div class="xclassList xclassListDetailMsg">
						<hr/>
						<h3><em class="iconListImg detail_title"></em>课程信息</h3>
						<ul>
							<li>
								<em class="iconListImg detail_position"></em><span>${(courseInfo.ciPlace)?default("")}</span>
							</li>
							<li>
								<em class="iconListImg detail_keshu"></em><span>${(courseInfo.ciPeriod)?default("")}节课</span>
							</li>
							<li>
								<em class="iconListImg detail_person"></em><span>${courseInfo.courseTeacherRelation.teacherInfo.tiName}</span>
							</li>
							<li>
								<em class="iconListImg detail_flag"></em><span>已签到</span>
							</li>
							<li>
								<em class="iconListImg detail_clock"></em><span>${courseInfo.ciDateTime}</span>
							</li>
							<li>
								<em class="iconListImg detail_clock"></em><span>${courseInfo.ciOver}</span>
							</li>	
						</ul>
					</div>
					<div class="xclassList xclassListDetailMsg">
						<hr/>
						<h3>
							<em class="iconListImg detail_intro"></em>
							课程介绍
						</h3>
						<p>
							${(courseInfo.ciDesription)?default("")}
						</p>
						</ul>
					</div>
					<div class="xclassList xclassListDetailMsg">
						<hr/>
						<h3>
							<em class="iconListImg detail_teacher"></em>
							老师信息
						</h3>
						<p>
						${courseInfo.courseTeacherRelation.ctrSign}
						</p>
						</ul>
					</div>
				</div>
			</div>
        </div>
    </div>  
</body>
</html>
