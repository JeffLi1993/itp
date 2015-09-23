/**
 * AJAX批量删除，刷新返回相应内容
 * *********************************************************
 * @param {String} checkboxName			->CheckBox的名字
 * @param {String} batchAction			->进行批量操作的Action名
 * @param {String} backAction		    ->返回刷新的Action名
 * @param {String} rel					->刷新的页面题ID标识
 * */
function objDelete(checkboxName, batchAction, backAction, rel)
{
	var idArray=new Array();
	var ojbIdStr;
	
	if($("input[name="+checkboxName+"]:checked").size()==0)
	{
		alertMsg.warn("请至少选择一条需要删除的数据！");
		return false;
	}
	else
	{
		$("input[name="+checkboxName+"]:checked").
			each(
				function(i,obj)
				{
					idArray[i]=$(obj).val();
				});
		ojbIdStr=idArray.join("-");
	}
	
	
	alert("ojbIdStr:"+ojbIdStr);
	
	if (confirm("删除的数据将不能恢复,确定继续？")) {
		$.ajax({
			url:	batchAction,
			data:
			{
				sendTime:(new Date()).getTime(),
				ojbIdStr:ojbIdStr
			},
			type:		 "post",
			async:		  false,
			dataType:	 "json",
			success:	function(data){
				
				if(data.success)
				{
					alertMsg.correct(data.msg);
					navTab.reload(data.url, {}, data.rel);
				}
				else
				{
					alertMsg.error(data.reason);
				}
			}
		});
	}else{
		return false;
	}
}

/**
 * 
 */
function initPassword(entityId,url)
{
	if (confirm("确定初始化密码？")) {
		$.ajax({
			url: url,
			data:
			{
				sendTime:(new Date()).getTime(),
				entityId:entityId
			},
			type:		 "post",
			async:		  false,
			dataType:	 "json",
			success:	function(data){
				
				if(data.success)
				{
					alertMsg.correct(data.msg);
					navTab.reload(data.url, {}, data.rel);
				}
				else
				{
					alertMsg.correct(data.reason);
				}
			}
		});
	}

}


/**
 * AJAX-FORM 提交
 * *********************************************************
 * @param {int} form				->FORM的ID标志
 * */
function formDatasAdd(form){
	var $form=$("#"+form);
	//alert(form);
	if(!$form.valid())
	{
		return false;
	}
	
	var options = { 
	  dataType: 'json',
	  success: dataResponse
	}; 
	
	$("#"+form+"").ajaxSubmit(options);
}

/**
 * AJAX-返回 
 * *********************************************************
 * @param {json} data				->返回的json的Data
 * */
function dataResponse(data){
	if(data.success == true)
	{
//		var $box = $("#"+data.rel+"");
		alertMsg.correct(data.msg);
		$.pdialog.closeCurrent();
		
		navTab.reload(data.url, {}, data.rel);
	}
	else
	{
		alertMsg.error(data.reason);
	}
}
/**
 * 单击tr选择复选框
 * *********************************************************
 * @param {obj} row				    ->row对象
 * */
$(function()
{
	pickRow();
});
function pickrow(row)
{ 
	var x = row.getElementsByTagName("input"); 
	
	for(var j=0; j < x.length; j++)
	{ 
		if(x[j].type == "checkbox")
		{ 
			x[j].checked = !x[j].checked; 
		} 
	} 
} 
function pickRow() 
{
	$("div.gridTbody tr").live("click",function()
	{
		$(this).find("input[type='checkbox']").click();//点击行时触发复选框的click事件
	});
	
	$("div.gridTbody input[type='checkbox']").live("click",function(e)
	{
		e.stopPropagation();//阻止事件冒泡
	});
}

/**
* AJAX-FORM-XLS 提交
* *********************************************************
* @param {int} form				->FORM的ID标志
* */
function formXlsAdd(form){
	var $form=$("#"+form);
	//alert(form);
	if(!$form.valid())
	{
		return false;
	}
	
	var options = { 
		type:		 "post",
		async:		  false,
		dataType:	 "json",
	    success:      xlsResponse
	}; 
	
	$("#"+form+"").ajaxSubmit(options);
}
/**
 * AJAX-XLS-返回 
 * *********************************************************
 * @param {json} data				->返回的json的Data
 * */
function xlsResponse(data){
	if(data.success == true)
	{
		if(data.sign=="T")
			{
				$.pdialog.closeCurrent();	
				$.pdialog.open("../dialog/dialogs?msg="+data.msg+"&urlD="+data.urlD, "", "导入失败内容",{width:400,height:200,mask:true,mixable:true,minable:true,resizable:true,drawable:true,fresh:true});
				navTab.reload(data.url, {}, data.rel);
			}else
			{
				alertMsg.correct(data.msg);
				$.pdialog.closeCurrent();
				navTab.reload(data.url, {}, data.rel);
			}
	}
	else
	{
		alertMsg.error(data.reason);
	}
}
/**
 * 
 */
function getExamStatisticalAddress() {
	$.ajax({
		url : "../paperInfo/examStatisticalAddress",
		data : {
			sendTime 				: (new Date()).getTime(),
			startDate				:$("#startDate").val(),
			endDate					:$("#endDate").val(),
			courseTeacherRelationID :$("#courseTeacherRelationID").val()
		},
		type : "post",
		async : false,
		dataType : "json",
		success : function(data) {

			if (data.success) {
				alertMsg.correct(data.msg);
				var $h2 =$("#examStatistical");
				$h2.html("<a href='"+data.url+"' style='color:red'>点击下载导出文件</a>");
			} else {
				alertMsg.error(data.reason);
			}
		}
	});
}
