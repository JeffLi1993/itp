/**
 * Common js
 */
// 显示加载器
function showLoader() {
	// 显示加载器.for jQuery Mobile 1.2.0
	$.mobile.loading('show', {
		text : '加载中...', // 加载器中显示的文字
		textVisible : true, // 是否显示文字
		theme : 'c', // 加载器主题样式a-e
		textonly : false, // 是否只显示文字
		html : "" // 要显示的html内容，如图片等
	});
}

// 隐藏加载器.for jQuery Mobile 1.2.0
function hideLoader() {
	// 隐藏加载器
	$.mobile.loading('hide');
}
/**
 * AJAX 课堂测试提交 *********************************************************
 * 
 * @param {int}
 *            form ->FORM的ID标志
 */
function formExamStudentResultsAdd(form, examStudentResultSize, paperInfoId) {
	var idArray = new Array();
	var examStudentResultStr;
	for ( var i = 1; i <= examStudentResultSize; i++) {
		$("input[name=examStudentResult_" + i + "]:checked").each(
				function(j, obj) {
					idArray[i - 1] = $(obj).val();
				});
	}

	examStudentResultStr = idArray.join("-");

	$.ajax({
				url : "../examStudentResult/saveExamStudentResults",
				data : {
					sendTime : (new Date()).getTime(),
					examStudentResultStr : examStudentResultStr,
					paperInfoId : paperInfoId
				},
				type : "post",
				async : false,
				dataType : "json",
				success : function(data) {

					if (data.success) {
						var pop = document.getElementById("popdiv");
						pop.innerHTML = " <lable style=' font-size:22px;'>"
								+ data.msg
								+ ",页面将在</lable><lable style=' font-size:22px;' id='jumpTo'></lable><lable style=' font-size:22px;'>秒后跳转</lable>";
						$("#popdiv").popup();// id为弹出层的id
						$("#popdiv").popup("open");

						var ajaxUrlId = document.getElementById("ajaxUrlId");
						countDown(5, ajaxUrlId.value);

					} else {
						var pop = document.getElementById("popdiv");
						pop.innerHTML = "<h3>" + data.reason + "</h3>";
						$("#popdiv").popup();// id为弹出层的id
						$("#popdiv").popup("open");
					}
				}
			});

}

/**
 * AJAX 议题提交测试提交 学生 *********************************************************
 */
function formStudentDiscussionTopicAdd(discussionTopicId, rel) {
	var discussionTopicText = $("#discussionTopicText").val();
	discussionTopicText = discussionTopicText.trim();
	if (discussionTopicText != "") {
		$.ajax({
			url : "../discussionTopic/addDiscussionTopicStu",
			data : {
				sendTime : (new Date()).getTime(),
				discussionTopicId : discussionTopicId,
				discussionTopicText : $("#discussionTopicText").val()
			},
			type : "post",
			async : false,
			dataType : "json",
			success : function(data) {

				if (data.success) {
					var pop = document.getElementById("popdiv");
					pop.innerHTML = "<h2>" + data.msg + "</h2>";
					$("#popdiv").popup();// id为弹出层的id
					$("#popdiv").popup("open");
					divload(rel, data.url + "?discussionTopicId="
							+ discussionTopicId);
				} else {
					var pop = document.getElementById("popdiv");
					pop.innerHTML = "<h3>" + data.reason + "</h3>";
					$("#popdiv").popup();// id为弹出层的id
					$("#popdiv").popup("open");
				}
			}
		});
	} else {
		var pop = document.getElementById("popdiv");
		pop.innerHTML = "<h2>请填写内容后再回复</h2>";
		$("#popdiv").popup();// id为弹出层的id
		$("#popdiv").popup("open");
	}
}
/**
 * AJAX 议题提交测试提交 老师 *********************************************************
 */
function formTeacherDiscussionTopicAdd(discussionTopicId, rel) {
	var discussionTopicText = $("#discussionTopicText").val().trim();
	if (discussionTopicText != "") {
		$.ajax({
			url : "../discussionTopic/addDiscussionTopicTea",
			data : {
				sendTime : (new Date()).getTime(),
				discussionTopicId : discussionTopicId,
				discussionTopicText : $("#discussionTopicText").val()
			},
			type : "post",
			async : false,
			dataType : "json",
			success : function(data) {

				if (data.success) {
					var pop = document.getElementById("popdiv");
					pop.innerHTML = "<h2>" + data.msg + "</h2>";
					$("#popdiv").popup();// id为弹出层的id
					$("#popdiv").popup("open");
					divload(rel, data.url + "?discussionTopicId="
							+ discussionTopicId);
				} else {
					var pop = document.getElementById("popdiv");
					pop.innerHTML = "<h3>" + data.reason + "</h3>";
					$("#popdiv").popup();// id为弹出层的id
					$("#popdiv").popup("open");
				}
			}
		});
	} else {
		var pop = document.getElementById("popdiv");
		pop.innerHTML = "<h2>请填写信息后再回复</h2>";
		$("#popdiv").popup();// id为弹出层的id
		$("#popdiv").popup("open");
	}
}
/**
 * AJAX 课堂测试开启关闭 *********************************************************
 */
function changePaperSign(signValue, paperInfoId) {
	// alert(signValue+paperInfoId);
	$.ajax({
		url : "../paperInfo/turnPaperInfoSign",
		data : {
			sendTime : (new Date()).getTime(),
			paperInfoId : paperInfoId
		},
		type : "post",
		async : false,
		dataType : "json",
		success : function(data) {

			if (data.success) {
				var pop = document.getElementById("popdiv");
				pop.innerHTML = "<h1>" + data.msg + "</h1>";
				$("#popdiv").popup();// id为弹出层的id
				$("#popdiv").popup("open");
			} else {
				var pop = document.getElementById("popdiv");
				pop.innerHTML = "<h1>" + data.reason + "</h1>";
				$("#popdiv").popup();// id为弹出层的id
				$("#popdiv").popup("open");
			}
		}
	});
}

/**
 * AJAX 议题开启关闭 *********************************************************
 */
function changeDiscussionSign(signValue, discussionTopicId) {
	// alert(signValue+discussionTopicId);
	$.ajax({
		url : "../discussionTopic/turDiscussionTopicSign",
		data : {
			sendTime : (new Date()).getTime(),
			discussionTopicId : discussionTopicId
		},
		type : "post",
		async : false,
		dataType : "json",
		success : function(data) {

			if (data.success) {
				var pop = document.getElementById("popdiv");
				pop.innerHTML = "<h1>" + data.msg + "</h1>";
				$("#popdiv").popup();// id为弹出层的id
				$("#popdiv").popup("open");
			} else {
				var pop = document.getElementById("popdiv");
				pop.innerHTML = "<h1>" + data.reason + "</h1>";
				$("#popdiv").popup();// id为弹出层的id
				$("#popdiv").popup("open");
			}
		}
	});
}
/**/

function countDown(secs, surl) {
	// alert(surl);
	var jumpTo = document.getElementById('jumpTo');
	jumpTo.innerHTML = secs;
	if (--secs > 0) {
		setTimeout("countDown(" + secs + ",'" + surl + "')", 1000);
	} else {
		divload("contentYwl", surl);
	}
}
/**
 * div加载页面
 */
function divload(divId, tagController) {
	// 预加载
	showLoader();
	$("#" + divId).load(tagController, {
		sendTime : (new Date()).getTime()
	}, (function() {
		// 预加载结束
		hideLoader();
		$('#' + divId).trigger('create');
	}));
}
/**
 * 验证学生考试卷是否全部选择
 */
function validateExam(examStudentResultSize, paperInfoId) {
	var idArray = new Array();
	var x = 0;
	for ( var i = 1; i <= examStudentResultSize; i++) {
		$("input[name=examStudentResult_" + i + "]:checked").each(
				function(j, obj) {
					idArray[x] = $(obj).val();
					x++;
				});
	}
	if (idArray.length >= examStudentResultSize) {
		formExamStudentResultsAdd(
				'../examStudentResult/saveExamStudentResults',
				examStudentResultSize, paperInfoId);
	} else {
		var pop = document.getElementById("popdiv");
		pop.innerHTML = "<h2>请将试卷填写完毕后再提交</h2>";
		$("#popdiv").popup();// id为弹出层的id
		$("#popdiv").popup("open");
	}
}
/**
 * 
 */
function changeTop(divId, discussionStudentReplyId) {
	$.ajax({
		url : "../discussionTopic/trunStudentReplyTop",
		data : {
			sendTime : (new Date()).getTime(),
			discussionStudentReplyId : discussionStudentReplyId
		},
		type : "post",
		async : false,
		dataType : "json",
		success : function(data) {

			if (data.success) {
				divload(divId,
						"../discussionTopic/showDiscussionTopicStuDivReload?discussionStudentReplyId="
								+ discussionStudentReplyId);
				var pop = document.getElementById("popdiv");
				pop.innerHTML = "<h1>" + data.msg + "</h1>";
				$("#popdiv").popup();// id为弹出层的id
				$("#popdiv").popup("open");
			} else {
				var pop = document.getElementById("popdiv");
				pop.innerHTML = "<h1>" + data.reason + "</h1>";
				$("#popdiv").popup();// id为弹出层的id
				$("#popdiv").popup("open");
			}
		}
	});
}
/**
 * 
 */
