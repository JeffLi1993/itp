<div class="xmyTab">
	<ul>
		<li class="xtabChooes"><a href="#" id="courseInfoButton"  >课程详情</a></li>
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
				<em class="iconListImg detail_clock"></em><span>${(courseInfo.ciOver)?substring(11,16)}</span>
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
