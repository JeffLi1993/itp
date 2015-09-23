<html>
	<head>
		<title>后台管理系统</title>
		
		<!-- 关于浏览器的一些配置   不缓存 -->
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Expires" CONTENT="0"> 
		<meta http-equiv="Cache-Control" CONTENT="no-cache"> 
		<meta http-equiv="Pragma" CONTENT="no-cache">

		<!-- 网站icon设置 -->
		<link rel="icon" href="" type="image/x-icon" /> 
		<link rel="shortcut icon" href="" type="image/x-icon" />
		
		<!-- DWZ框架CSS设置 -->
		<link href="/wmuitp/css/pageStructure/indexPage.css" rel="stylesheet" type="text/css" media="screen" />
		<link href="/wmuitp/dwz/themes/silver/style.css" rel="stylesheet" type="text/css" media="screen"/>
		<link href="/wmuitp/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
		<link href="/wmuitp/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
		<link href="/wmuitp/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
		
		<!-- 主页其他CSS设置 -->
		
		<!--[if IE]><link href="/wmuitp/css/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/><![endif]-->

		<!--[if lte IE 9]>
		<script src="/wmuitp/dwz/js/speedup.js" type="text/javascript"></script>
		<![endif]-->
		
		
		<!-- DWZ框架JS设置 -->
		<script src="/wmuitp/dwz/js/jquery-1.7.2.min.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/jquery.cookie.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/jquery.validate.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.core.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.util.date.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.drag.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.tree.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.accordion.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.ui.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.theme.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.navTab.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.tab.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.resize.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.dialog.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.stable.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.ajax.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.pagination.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.database.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.effects.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.panel.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.history.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.combox.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.print.js" type="text/javascript"></script>
		<script src="/wmuitp/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

		<!-- 网站其他JS设置  -->
		<!-- common-->
		<script src="/wmuitp/js/admin/common.js" type="text/javascript"></script>
		<script src="/wmuitp/js/common/jquery.form.js" type="text/javascript"></script>
		<!-- 图表相关 js css-->
		<script src="/wmuitp/js/jsChart/lib/OpenCharts.js" type="text/javascript"></script>
    	<link rel="stylesheet" href="/wmuitp/js/jsChart/theme/default/style.css" type="text/css">
				


		<script type="text/javascript">
			/**
			 *DWZ框架入口：初始化 dwz.frag.xml
			 *-------------------------------------------------------------------------------------------
			 */
			$(function(){
				DWZ.init("/wmuitp/dwz/dwz.frag.xml", {
					loginUrl:"", loginTitle:"登录",	// 弹出登录对话框
					statusCode:{ok:200, error:300, timeout:301}, //【可选】
					pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField",
						orderDirection:"orderDirection"}, //【可选】
					debug:false,	// 调试模式 【true|false】
					callback:function(){
						initEnv();
						$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
					}
				});
			});
			/*-------------------------------------------------------------------------------------------*/
			
			/**
			 *页面加载前 "正在加载" 模块
			 *-------------------------------------------------------------------------------------------
			 */
			function DivLoads(state)              
			{                  
				var DivRef1 = document.getElementById("Loader");                  
				var IfrRef1 = document.getElementById("Shimerx");                  
				if(state)                  
				{                      
					DivRef1.style.display = "block";                      
					IfrRef1.style.width = DivRef1.offsetWidth;                      
					IfrRef1.style.height = DivRef1.offsetHeight;                      
					IfrRef1.style.top = DivRef1.style.top;                      
					IfrRef1.style.left = DivRef1.style.left;                      
					IfrRef1.style.zIndex = DivRef1.style.zIndex - 1;                      
					IfrRef1.style.display = "block";                  
				}                  
				else                  
				{                      
					DivRef1.style.display = "none";                      
					IfrRef1.style.display = "none";                  
				}              
			}  
			/*-------------------------------------------------------------------------------------------*/
			
			function appClient(url)
			{
				alertMsg.correct('手机客户端已经成功打开！请查看新的窗口页面。')
				window.open(url,"手机客户端",'height=600,width=370,top=20,left=300,z-look=yes,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');
			}
		</script>
		
	</head>
	
	<body scroll="no">

		<!--定义系统运行等待层,包括div和iframe-->
		<DIV id=Loader style="Z-INDEX:9999; BACKGROUND:white ;LEFT:0px; WIDTH:100%; PADDING-TOP:25%; POSITION:absolute; TOP:0px; HEIGHT:905px; TEXT-ALIGN: center">         
			<IMG src="/wmuitp/dwz/images/loading.gif">  
		    <B>页面加载中，请稍候...</B>
		</DIV>
		<IFRAME id=Shimerx style="DISPLAY:none; FILTER:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0); LEFT:0px; POSITION:absolute; TOP:0px" src="javascript:false;" frameBorder=0 scrolling=no>
		</IFRAME>
		<SCRIPT>     
			DivLoads(true);					//显示系统运行等待层
		</SCRIPT>
		<SCRIPT>
			var delayTime=1;				//等待多少秒
			function counter()
			{   
				DivLoads(false);			//隐藏系统运行等待层
			}  
			setTimeout("counter()",delayTime*1000);
		</SCRIPT>

		<!--页眉 -->
		<div id="layout">
		
			<div id="header">
				<div class="headerNav">
					<a href="#">
						<img style = "height:40px; width:128px;padding:5px 0 0 40px;" src = "/wmuitp/images/ico/title_logo2.png">
					</a>
					<div class="top_username">
						欢迎您，
						<#if teacherName??>
							${teacherName}
						<#else>
							${admin.ulName}
						</#if>
					    <span style="font-weight:bold;">
						</span> 
					</div>
					<ul class="nav">
						<li><a onclick="appClient('${ctx}/studentCourseRelation/getStudentCourseRelationsByUserLoginApp?ulName=${admin.ulName}&ulPassword=${teacherPassEncrypt}');">客户端电脑版</a></li>
						<li><a href="${ctx}/messageReceive/messageReceives" target="navTab" rel= "message" title="站内信">站内信</a></li>
						<li><a href="${ctx}/admin/showChangeUserpwdByUserLogin?ulName=${admin.ulName}&ulPassword=${admin.ulPassword}" target="dialog" width="450" height="180" title="修改密码" mask = "true" rel="changeUserpwdByUserLogin">修改密码</a></li>
						<li><a href="${ctx}/admin"  title="安全退出">退出</a></li>
					</ul>
					<ul   style="display:none" class="themeList" id="themeList">
						<li theme="default"><div >蓝色</div></li>
						<li theme="green"><div>绿色</div></li>
						<li theme="purple"><div>紫色</div></li>
						<li theme="azure"><div >天蓝</div></li>
						<li theme="silver"><div class="selected">银色</div></li>
					</ul>
				</div>
			</div>
	
			<!-- 菜单栏  -->
			<div id="leftside">
			
				<div id="sidebar_s">
					<div class="collapse">
						<div class="toggleCollapse"><div></div></div>
					</div>
				</div>
				
				<div id="sidebar">
					<div class="toggleCollapse">
						<h2>主菜单</h2>
						<div id = "contract">收缩</div>
					</div>
					
					<div class="accordion" fillSpace="sidebar">
						<#if admin.ulSign=="tea">
						<!-- ========================= 课  管理模块 ========================== -->
						<div class="accordionHeader">
							<h2><span>Folder</span>课相关管理</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder" >
								
								<li>		<a href="${ctx}/courseInfo/courseInfosTea" 						target="navTab" 	rel= "teacherCourses"> 			课设置		 		</a></li>
								<li>		<a href="${ctx}/paperInfo/paperInfosTea" 					target="navTab" rel="paperInfos">	课堂练习管理	</a></li>
								<li>	 	<a href="${ctx}/discussionTopic/discussionTopicsTea" 					target="navTab" 	rel= "discussionTopic"> 			议题设置		 		</a></li>	
								<#if admin.ulSign=="adt">
								<li>
									<a>课程管理</a>
									<ul>
										<li><a href="${ctx}/courseTeacherRelation/courseTeacherRelationsTea" 	target="navTab" 	rel= "courseTeacherRelations"> 	课程和老师管理        	</a></li>
									</ul>
								</li>
								</#if>
							</ul>	
							
						</div>
						<!-- =========================  模块结束      ========================== -->
					
						<!-- ========================= 课堂练习管理模块 ======================= -->
						<div class="accordionHeader">
							<h2><span>Folder</span>课堂练习管理</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a href="${ctx}/subjectInfor/subjectInforsTea" 			target="navTab" rel="subjectInfors">试题管理		</a></li>
							</ul>
						</div>
						<!-- =========================  模块结束      ========================== -->
						</#if>
						<#if admin.ulSign=="adt">
						<!-- ========================= 课  管理模块 ========================== -->
						<div class="accordionHeader">
							<h2><span>Folder</span>课相关管理</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder" >
								<li>
									<a>课程管理</a>
									<ul>
										<li><a href="${ctx}/course/courses" 								target="navTab" 	rel= "courses">		     		基础课程管理  		</a></li>
										<li><a href="${ctx}/courseChapter/courseChapters" 					target="navTab" 	rel= "courseChapters">		        基本课程进度管理		</a></li>
										<li><a href="${ctx}/courseTeacherRelation/courseTeacherRelations" 	target="navTab" 	rel= "courseTeacherRelations"> 	课程和老师管理        	</a></li>
									</ul>
								</li>
								<li>		<a href="${ctx}/courseInfo/courseInfos" 						target="navTab" 	rel= "teacherCourses"> 			课设置		 		</a></li>
							<li>		<a href="${ctx}/discussionTopic/discussionTopics" 					target="navTab" 	rel= "discussionTopic"> 			议题设置		 		</a></li>
							</ul>	
							
						</div>
						<!-- =========================  模块结束      ========================== -->
					
						<!-- ========================= 课堂练习管理模块 ======================= -->
						<div class="accordionHeader">
							<h2><span>Folder</span>课堂练习管理</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a href="${ctx}/paperInfo/paperInfos" 					target="navTab" rel="paperInfos">	测试试卷管理	</a></li>
								<li><a href="${ctx}/subjectInfor/subjectInfors" 			target="navTab" rel="subjectInfors">试题管理		</a></li>
							</ul>
						</div>
						<!-- =========================  模块结束      ========================== -->
						
						<!-- ========================= 人员管理模块 ======================= -->
						<div class="accordionHeader">
							<h2><span>Folder</span>人员管理</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder" >
								<li>
									<a>人员管理</a>
									<ul>
										<li><a href="${ctx}/professionInfo/professionInfos" 		target="navTab" rel="professionInfos">	专业管理	</a></li>
										<li><a href="${ctx}/classInfo/classInfos" 	            	target="navTab" rel="classInfos">	            班级管理	</a></li>
										<li><a href="${ctx}/teacherInfo/teacherInfos" 	            	target="navTab" rel="teacherInfos"> 老师管理	</a></li>
										<li><a href="${ctx}/studentInfo/studentInfos" 	            	target="navTab" rel="studentInfos"> 学生管理	</a></li>
									</ul>
								</li>
								<!--<li>		<a href="${ctx}/course/courseinfos" 	target="navTab" 	rel= "teachercourses"> 课设置		 </a></li>-->
							</ul>	
						</div>
						<!-- =========================  模块结束      ========================== -->
						</#if>
					</div>
				</div>
				
			</div>
			
			<!-- 其他区域  -->
			<div id="container">
				<div id="navTab" class="tabsPage">
					<div class="tabsPageHeader">
						<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
							<ul class="navTab-tab">
								<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">工作台</span></span></a></li>
							</ul>
						</div>
						<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
						<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
						<div class="tabsMore">more</div>
					</div>
					<ul class="tabsMoreList">
						<li><a href="javascript:;">工作台</a></li>
					</ul>
					<div class="navTab-panel tabsPageContent layoutBox">
						<div class="pageContent" selector="h1" layoutH="10">
					
						<div class="linestyle">
							
						</div>	
	
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 页面底部信息 -->
		<div id="footer"></div>
	</body>
</html>