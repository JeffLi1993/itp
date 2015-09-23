<!DOCTYPE html>
<html>
  <head>
    <title>jq mobile</title>
 `   <meta charset="utf-8">
	<meta name="viewport" content="width=device,inital-scale=1">
	<link  rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css"/>
    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script type="text/javascript">
		$(document).on("mobileinit",function(){
		$.extend( $.mobile,{
			pageLoadErrorMessage:' Error!'
			});
		});
	</script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	<style>
	img {max-width:100%;}
	</style>
  </head>
  
  <body>
    <div data-role="page">
    	<div data-role="header"><h1>${teacherInfo.tiName}</h1></div>
        	<div data-role="content">
        		<div class="ui-grid-a" >
        			<div class="ui-block-a" >
            			<img alt="教师照片" src="/wmuitphtml/teacherImg/${teacherInfo.tiAddress}">
            		</div>
            		<div class="ui-block-b" >
            			<p>教师名字：${teacherInfo.tiName}</p>
            			<p>教师年龄：${teacherInfo.tiAge}</p>
            		</div>
            	</div>
            	<p>教师职称：${teacherInfo.tiJob}</p>
            	<p>教师标识：${teacherInfo.tiSign}</p>
    			<p>教师技能详情：${teacherInfo.tiSkills}</p>
            </div>
    </div>
  </body>
</html>
