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
            <h1>过往基本课程</h1>
        </div>
        
        <div data-role="content" >
	        <header>
				<div class="stuHeadBack headBack">            
					<a href="#"  data-rel="back" >
						<span>主页</span>
					</a>
				</div>
				<h1>过往基本课程</h1>
			</header>
			<div id="contentYwl">
				<div class="xmyContent">
					<#if courses?exists>
						<#list  courses as course>
							<div onclick="divload('contentYwl','${ctx}/paperInfo/getPaperInfoBeforeByCIdApp?courseId=${course.CId}')" class="xclassList xclassListDetailMsg">
								<hr/>
								<h3>
									<em class="iconListImg detail_intro"></em>
									${course.CName}
								</h3>
								<p>
									${course.CDescription}
								</p>
								</ul>
							</div>
						</#list>
					</#if>
				</div>
			</div>
        </div>
    </div>  
</body>
</html>
