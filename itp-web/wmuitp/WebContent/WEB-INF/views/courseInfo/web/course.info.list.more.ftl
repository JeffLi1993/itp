<!DOCTYPE html>
<html>
	<head>
	    <script type="text/javascript">
	       $(function()
	        {
	        	if($("#allStuSum").val() != null)
	        	{
		       		var nowStu =  $("#nowStuSum").val()/$("#allStuSum").val()*100;
		       		
		            var items = [],items1 = [],items2 = [],items3 = [];
					
					items.push({strCaption : '已到',data : nowStu    ,itemStyle:'#00B0F0'});
		            items.push({strCaption : '缺勤',data : 100-nowStu,itemStyle:'#00B050'});
		           
		            charts = new OpenCharts.Chart.PieChart("canvas");
		            charts.strTitle = "考勤情况总汇表";
		            charts.addItems(items,"考勤情况总汇表");
		
		            charts.render();
	            }
	        });
	    </script>
	</head>
	<body>
		<div class="pageContent" >
		<#if allStuSum?exists>
			<input type="hidden" value=${allStuSum} name="allStuSum" id="allStuSum" />
			<input type="hidden" value=${nowStuSum} name="nowStuSum" id="nowStuSum" />
			<h1>全班总共${allStuSum}人，考勤人数${nowStuSum}人</h1>
			<div style="left:0px;top:0px;">
	   			<canvas id="canvas" height="300px" width="400px"  />
			</div>
		<#else>
			<h2>当前没有一人考勤！</h2>
		</#if>
		</div>
	</body>
</html>
