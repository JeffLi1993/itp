<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>课程表</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet"  href="/wmuitp/js/webApp/jqMobile/css/jquery.mobile-1.3.2.css">
    <script src="/wmuitp/js/webApp/exam/exam.js"></script>
	<script src="/wmuitp/js/common/jquery.form.js"></script>
    <script src="/wmuitp/js/webApp/jqMobile/js/jquery.js"></script>
    <script src="/wmuitp/js/webApp/jqMobile/js/jquery.mobile-1.3.2.js"></script>
    <link rel="stylesheet"  href="/wmuitp/js/webApp/app/css/common.css">
    <link rel="stylesheet"  href="/wmuitp/js/webApp/app/css/stu.css">
    
    <link rel="stylesheet"  href="/wmuitp/data/css/mobiscroll.custom-2.6.2.min.css">
    <script src="/wmuitp/data/js/mobiscroll.custom-2.6.2.min.js"></script>
	<script type="text/javascript">
		 $(document).ready(function()
		 {
			//初始化日期控件
			var opt = 
			{
				preset: 'date', //日期
				theme: 'android-ics light', //皮肤样式
				display: 'modal', //显示方式 
				mode: 'Scroller', //日期选择模式
				dateFormat: 'yy-mm-dd', // 日期格式
				setText: '确定', //确认按钮名称
				cancelText: '取消',//取消按钮名籍我
				dateOrder: 'yymmdd', //面板中日期排列格式
				dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
				endYear:2020 //结束年份
			};
			
			$("#DataStr").mobiscroll(opt);
			var date=new Date();
			var month= date.getMonth()+1;
			var day= date.getDate();
			if(month < 10)
				month=""+0+month;
			if(day < 10)
				day=""+0+day;
			$("#DataStr").val(date.getFullYear()+"-"+month+"-"+day);
			validationTime();
		});
		function clickit()
		{
			$("#DataStr").focus();
		}
		function validationTime()
		{
			var dataStrval=$("#DataStr").val();
			if(dataStrval!="")
			{
				var d=document.getElementById("DataStr");
				divload('AjaxContent','${ctx}/studentCourseRelation/getStudentCourseRelationsChangeDate?DataStr='+$("#DataStr").val());
				//今天
				var date =new Date($("#DataStr").val());
				document.getElementById("yearMonth").innerHTML=$("#DataStr").val().substring(0,7);
				document.getElementById("todayDate").innerHTML=date.toString().substring(8,10);
				document.getElementById("todayWeek").innerHTML=date.toString().substring(0,3);
				//明天
				date =new Date(date-1+1+1000*60*60*24);
				document.getElementById("tomorrowDate").innerHTML=date.toString().substring(8,10);
				document.getElementById("tomorrowWeek").innerHTML=date.toString().substring(0,3);
				//昨天
				date =new Date(date-1+1-1000*60*60*24*2);
				document.getElementById("yesterdayDate").innerHTML=date.toString().substring(8,10);
				document.getElementById("yesterdayWeek").innerHTML=date.toString().substring(0,3);
			}else
			{
				return false;
			} 
		}
	</script>
</head>
<body>
	<input type="text" data-role="datebox" style="color:white"  id="DataStr" name="DataStr" onchange="validationTime()" />
	<div data-role="page">
        <div data-role="content" > 
		    <header>
				<a href="${ctx}/course/getCoursesByStu"> 
					<div class="headMore" ></div>
				</a>				
				<h1>课程表</h1>
			</header>
			<div class="xdate" onclick="clickit()">
				<h2 id="yearMonth">${(DateArray[0])?default("")}</h2>
				<ul>
					<li >
						<span id="yesterdayDate">${(DateArray[3])?default("")}</span>
						<h3 id="yesterdayWeek">${(DateArray[4])?default("")}</h3>
					</li>
					<li class="xdateChooes">
						<span id="todayDate">${(DateArray[1])?default("")}</span>
						<h3 id="todayWeek">${(DateArray[2])?default("")}</h3>
					</li>
					<li>
						<span id="tomorrowDate">${(DateArray[5])?default("")}</span>
						<h3 id="tomorrowWeek">${(DateArray[6])?default("")}</h3>
					</li>
				</ul>
			</div>
			<div class="xmyContent" id="AjaxContent">
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
							<div class="xclassTestPoint">
								<em></em>
								<h2>温馨提示</h2>
								<p>今日打烊，暂时无课</p>
							</div>
						</#if>
					</div>
			</div>
	    </div>  
	</body>
</html>
