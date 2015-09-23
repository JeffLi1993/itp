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
	    
	    <link rel="stylesheet"  href="/wmuitp/data/css/mobiscroll.custom-2.5.0.min.css">
	    <script src="/wmuitp/data/js/mobiscroll.custom-2.5.0.min.js"></script>
	    <script type="text/javascript">
	    	 $(document).ready(function()
			 {
			 	alert("aa");
			 });
			 $(document).on("pageinit","#Lqqbody",function(){
				alert("xx");
				divload('Lqqbody','${ctx}/studentCourseRelation/getStudentCourseRelationsByUserLoginApp?ulName='+${ulName}+'$ulPassword='+${ulPassword};
			});
	    </script>
	</head>
	<body id="Lqqbody">
		112
	</body>
</html>    
