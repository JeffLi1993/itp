<div class="xmyTab">
	<ul>
		<li class="xtabChooes"><a href="#"  >课堂测试</a></li>
		<li><a href="#" onclick="divload('contentYwl','${ctx}/discussionTopic/getDiscussionTopicByCIdApp?courseId=${courseId}')">议题</a></li>
	</ul>	
</div>
<div data-role="content"> 
	<div class="xmyContent">
		<div class="xclassTest">
			<p>
				<span>共有测试卷<em>${examStudents?size}</em>份</span>
			</p>
		</div>
		<ul class="xclassTestList">
			<#if examStudents?exists>
				<#list examStudents as examStudent>
				<li>
					<a href="#" onclick="divload('contentYwl','${ctx}/paperInfo/getPaperSubjectRelationsByPIIdApp?paperInfoId=${examStudent.paperInfo.piId}')">
						<div class="xclassTestList_date fl">
							<span>${(examStudent.paperInfo.courseInfo.ciDateTime)?substring(0,4)}<br/><em>${(examStudent.paperInfo.courseInfo.ciDateTime)?substring(5,10)}</em></span>
						</div>
						<div class="classTestList_msg fr">
							<span>${examStudent.paperInfo.courseInfo.courseTeacherRelation.course.CName}</span>
							<span>${examStudent.paperInfo.teacherInfo.tiName}</span>
							<#if (examStudent.esSign) == "T" >
								<em class="noSubmit">未提交</em>
							<#else>
								<em class="haveSubmit">已提交</em>
							</#if>
						</div>
					</a>
				</li>
				</#list>
			<#else>
				本课程暂时无测试卷！
			</#if>
		</ul>
	</div>
</div>
