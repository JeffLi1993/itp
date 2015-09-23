<div class="xmyindex">
	<#if studentCourseRelations?exists>
		<#list studentCourseRelations as studentCourseRelation>
		<!--课程信息1Begin-->
		<div class="xclassList">
			<div class="xclassList_imgBox">
				<a href="${ctx}/courseInfo/getCourseInfoByCIIdApp?courseInfoId=${studentCourseRelation.courseInfo.ciId}"  > 
					<img src="/wmuitp/js/webApp/app/image/replaceImg1.jpg" />
				</a>	
					<p>${studentCourseRelation.courseInfo.courseTeacherRelation.course.CName}</p>
			</div>
			<div class="xclassList_detail">
				<ul>
					<li>
						<em class="index_person"></em><span>${studentCourseRelation.courseInfo.courseTeacherRelation.teacherInfo.tiName}</span>
					</li>
					<li>
						<em class="index_time"></em><span>${(studentCourseRelation.courseInfo.ciDateTime)?substring(11,16)}</span>
					</li>
					<li>
						<#if (studentCourseRelation.scrPresent) == "T" >
							<em class="index_flag1"></em><span>已签到</span>
						<#else>
							<em class="index_flag2"></em><span>未签到</span>
						</#if>
					</li>
				</ul>				
			</div>
		</div>
		<!--课程信息1End-->
		</#list>
	<#else>
		<div class="xmyContent">
			<h2>今日暂时无课！</h2>
			<div class="xclassTestPoint">
				<em></em>
			</div>		
		</div>
	</#if>
</div>