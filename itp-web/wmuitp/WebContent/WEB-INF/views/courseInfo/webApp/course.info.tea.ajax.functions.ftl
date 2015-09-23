<div class="xmyTab xmyTeachTab" >
	<ul>
		<li class="xtabChooes"><a href="#" onclick="divload('contentYwl','${ctx}/courseInfo/getCourseInfoByCIIdTeaAppAjax?courseInfoId=${courseInfo.ciId}')">课程详情</a></li>
		<li><a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoByCIIdTeaApp?courseInfoId=${courseInfo.ciId}')">课堂测试</a></li>
		<li><a href="#" onclick="divload('contentYwl','${ctx}/discussionTopic/getDiscussionTopicByCIIdAppTea?courseInfoId=${courseInfo.ciId}')">议题</a></li>
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
									<em class="index_time"></em><span>${(courseInfo.ciDateTime)?substring(11,16)}</span>
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
					<p>今日打烊，暂时无课</p>
				</div>
			</#if>
	   </div>
	</div>
</div>