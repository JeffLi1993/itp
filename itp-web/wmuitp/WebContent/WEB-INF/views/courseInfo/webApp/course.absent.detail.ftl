<header>
	<div class="stuHeadBack headBack">            
		<a href="#" onclick="divload('contentYwl1','${ctx}/studentCourseRelation/getAttendanceApp?courseInfoId=${courseInfoId}')"  >
			<span>返回</span>
		</a>
	</div>
</header>
<div class="xmyContent">
	<h3>2014-07-07缺勤学生</h3>
	<div class="noLogin">
		<ul>
			<#if studentInfos?exists>
				<li class="f16">
					<span>姓名</span>
					<span>班级</span>
					<span>学号</span>
				</li>
				<#list studentInfos as studentInfo>
					<li>
						<span>${(studentInfo.siRealName)?default("")}</span>
						<span>${studentInfo.classInfo.ciCollege}${studentInfo.classInfo.ciProfession}${studentInfo.classInfo.ciName}</span>
						<span>${(studentInfo.siNum)?default("")}</span>
					</li>
				</#list>
			</#if>
		</ul>
	</div>
</div>
