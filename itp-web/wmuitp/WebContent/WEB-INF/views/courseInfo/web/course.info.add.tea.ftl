<script>
function validate()
{
	var ciDateTime=document.getElementById("ciDateTime").value;
	if(ciDateTime =="")
	{
		alert("请先选择上课时间");
	}
}
function getClassOverTime(){

	var ciDateTime=document.getElementById("ciDateTime").value;
	ciDateTime=ciDateTime.replace('-', '/');
	ciDateTime=ciDateTime.replace('-', '/');
	var date1=new Date(ciDateTime);
	var ciPeriod=document.getElementById("ciPeriod").value;
	var claaOverTime=document.getElementById("claaOverTime");
	if(ciDateTime ==""||ciPeriod =="")
	{
		return ;
	}
	//                               签到时间
	var date2=new Date(date1 -1 +1 +ciPeriod*45*60*1000);
	claaOverTime.value=((date2.getYear()+1900)+"-"+(date2.getMonth()+1)+"-"+date2.getDate()+' '+date2.getHours()+":"+date2.getMinutes()+":"+date2.getSeconds()).replace(/\b(\w)\b/g, '0$1');
}
	/*$(document).ready(function(){
	  if(confirm("你是否延用上次的班级"))
		{
			alert("T");
		} 
		else
		{
			alert("F");
		}
	});*/
</script>
<div class="pageFormContent" layoutH="20" style="height: 480px; width: 576px; overflow: auto;">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="#" onclick = "getClassOverTime();formDatasAdd('courseInfoAddForm');return false;"><span>保存</span></a></li>
		</ul>
	</div>
	<form id="courseInfoAddForm" method="POST" class="pageForm required-validate" action="${ctx}/courseInfo/courseInfoAddTea" style="float:left">
		<input type="hidden" size="30" value = "${(courseInfo.ciId)?default("")}"  name = "ciId" />
		
		<table cellpadding="0" cellspacing="0" border="0" width="430" class="tableShowCont">
			<tr>
				<td width="80">
					<label style="width:70px">上课时间：</label>
				</td>
				<td>	
					<input type="text" value="${(courseInfo.ciDateTime)?default("")}" name="ciDateTime" id="ciDateTime" class="date textInput readonly valid focus" onchange="getClassOverTime()" dateFmt="yyyy-MM-dd HH:mm:ss" />
					<a class="inputDateButton" href="#" >选择</a>
				</td>
			</tr>
			<tr>
				<td>
					<label style="width:70px">节数：</label>
				</td>
				<td>
					<select name = "ciPeriod" id = "ciPeriod" onchange="getClassOverTime()" onfocus="validate()">
						<option >请选择课节数</option>
						<option value="1"<#if courseInfo.ciPeriod == 1 > selected="selected" </#if>>1</option>
					    <option value="2"<#if courseInfo.ciPeriod == 2 > selected="selected" </#if>>2</option>
					    <option value="3"<#if courseInfo.ciPeriod == 3 > selected="selected" </#if>>3</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<lable  class="labelShowBox">结束时间：</lable>
				</td>
				<td>
					<input readonly="readonly"id="claaOverTime" name="ciOver"  ></input>	
				</td>
			</tr>
			<tr>
				<td>
					<a class="btnLook" style="float:right;" href="${ctx}/courseTeacherRelation/showCourseTeacherRelationLookUpTea" lookupGroup="" >查找带回</a>
					<label class="labelShowBox" style="width:70px">所属课程：</label> 
				</td>
				<td>
					<input  name="courseTeacherRelationName" type="text" readonly="readonly" class="required"  >
					<input  name="courseTeacherRelationID" type="hidden"   />
				</td>
			</tr>
			<tr>
				<td>	
					<label class="labelShowBox">上课地点：</label>
				</td>
				<td>	
					<input type="text" size="30" value = "${(courseInfo.ciPlace)?default("")}"  name = "ciPlace" class="required"/>
				</td>
			</tr>
			<tr>
				<td>
					<label style="width:70px">描述：</label>
				</td>
				<td>
					<input type="text" size="30" value = "${(courseInfo.ciDesription)?default("")}"  name = "ciDesription" class="required"/>
				</td>
			</tr>
			<tr>
				<td valign="top">	
					<a class="btnLook" style="float:right;" href="${ctx}/classInfo/showClassInfoLookup" lookupGroup=""  width="950" >查找带回</a>
					<label class="labelShowBox" style="width:80px;">班级名称：</label>
				</td>
				<td>
					<textarea name="className" readonly="readonly" class="required" cols="40" rows="5"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="readonly" name="classInfoIds"  readonly="readonly" type="hidden"  class="required"/>
				</td>
			<tr>
			<tr>
				<td colspan="2">
					<div class="formBar" style=" width: 422px;">
						<ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit" onclick = "getClassOverTime();formDatasAdd('courseInfoAddForm');return false;">保存</button>
									</div>	
								</div>
							</li>
							
							<li>
								<div class="button">
									<div class="buttonContent">
										<button type="button" class="close">取消</button>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</td>
			<tr>
	</table>
	</form>
</div>
