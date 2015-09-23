package sedion.jeffli.wmuitp.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import sedion.jeffli.wmuitp.service.ClassInfoService;
import sedion.jeffli.wmuitp.service.CourseChapterService;
import sedion.jeffli.wmuitp.service.CourseInfoService;
import sedion.jeffli.wmuitp.service.CourseService;
import sedion.jeffli.wmuitp.service.CourseTeacherRelationService;
import sedion.jeffli.wmuitp.service.DiscussionTopicService;
import sedion.jeffli.wmuitp.service.ExamStudentResultService;
import sedion.jeffli.wmuitp.service.MessageReceiveService;
import sedion.jeffli.wmuitp.service.MessageSenderService;
import sedion.jeffli.wmuitp.service.PaperInfoService;
import sedion.jeffli.wmuitp.service.PaperSubjectRelationService;
import sedion.jeffli.wmuitp.service.ProfessionInfoService;
import sedion.jeffli.wmuitp.service.StudentCourseRelationServcie;
import sedion.jeffli.wmuitp.service.StudentInfoService;
import sedion.jeffli.wmuitp.service.SubjectInforService;
import sedion.jeffli.wmuitp.service.TeacherInfoService;
import sedion.jeffli.wmuitp.service.UserLoginService;




public class MainBase
{

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected HttpServletRequest  request;				//请求
	protected HttpServletResponse response;				//响应
	@Autowired
	protected HttpSession     	  session;			    //会话


	/**
	 * 注册Service层
	 **/
	@Autowired
	protected CourseService 	 					courseService; 						//课程
	@Autowired
	protected UserLoginService   					userLoginService;					//用户
	@Autowired
	protected PaperInfoService 						paperInfoService;					//考试卷
	@Autowired
	protected ClassInfoService						classInfoService;					//班级详情
	@Autowired
	protected CourseInfoService						courseInfoService;					//课程详情
	@Autowired
	protected TeacherInfoService 					teacherInfoService; 				//老师详情
	@Autowired
	protected StudentInfoService					studentInfoService;					//学生详情
	@Autowired
	protected SubjectInforService					subjectInforService;				//考题详情
	@Autowired
	protected MessageSenderService                  messageSenderService;               //站内信发件
	@Autowired
	protected CourseChapterService 					courseChapterService;				//课程章节
	@Autowired
	protected MessageReceiveService                 messageReceiveService;              //站内信收件
	@Autowired
	protected ProfessionInfoService 				professionInfoService; 				//专业详情
	@Autowired
	protected ExamStudentResultService				examStudentResultService;			//考生答案
	@Autowired
	protected PaperSubjectRelationService	        paperSubjectRelationService;		//考试卷和考题关系
	@Autowired
	protected CourseTeacherRelationService 			courseTeacherRelationService;		//课程和老师关系
	@Autowired
	protected StudentCourseRelationServcie			studentCourseRelationServcie;		//学生和课程关系
	@Autowired
	protected DiscussionTopicService				discussionTopicService;				//议题

	
	
	
	/**
	 * AJAX response-json object
	 * */

	/**
	 * ajax 失败
	 * @param reason	失败原因
	 * @return
	 */
	protected String failResponse(String reason) {
		
		String response = "{\"success\": false, \"reason\":\"" + reason + "\"}";
		
		return response;
	}

	/**
	 * 
	 * @param msg		成功提示
	 * @param url		跳转URL
	 * @param rel		URL对应区域ID
	 * @return
	 */
	protected String successResponse(String msg, String url, String rel) {
		
		String response = "{\"success\": true,\"msg\":\"" + msg
				+ "\",\"url\":\"" + url + "\",\"rel\":\"" + rel + "\"}";
		
		return response;
	}
	
	

	/**
	 *AJAX response Chinese information to alert
	 **/
	protected String successResponse(String msg) 
	{
		String response = "{\"success\": true,\"msg\":\"" + msg + "\"}";
		return response;
	}

	protected String cartResponse(String cartJson, String totalPrice, String businessId, String shopName) 
	{
		String response = "{\"success\": true,\"cart\":" + cartJson + ",\"businessId\":\"" + businessId + "\",\"shopName\":\"" + shopName
				+ "\",\"totalPrice\":\"" + totalPrice + "\"}";
		return response;
	}

	/**
	 * response some data structure,don't need the " symbol
	 * 
	 * @param msg
	 * @return
	 */
	protected String jsonDataResponse(String msg) 
	{
		String response = "{\"success\": true,\"msg\":" + msg + "}";
		return response;
	}
	
	/**
	 * 拼接JsonObjects
	 * @param JsonObject1	JSON对象1
	 * @param JsonObject2	JSON对象2
	 * @return
	 */
	protected String joinJsonObjects(JSONObject JsonObject1,
			JSONObject JsonObject2)
	{
		JSONObject allJsonObject = new JSONObject();
		
		allJsonObject.putAll(JsonObject1);
	    allJsonObject.putAll(JsonObject2);
	    
		return allJsonObject.toString();
	}
	/**
	 * 
	 * @param msg		导入提示
	 * @param urlD		下载URL
	 * @param url		跳转URL
	 * @param rel		URL对应区域ID
	 * @param sign      标识
	 * @return
	 */
	protected String successXlsResponse(String msg, String urlD,String rel,String url,String sign) {
		
		String response = "{\"success\": true,\"msg\":\"" + msg
				+ "\",\"urlD\":\"" + urlD + "\",\"rel\":\"" + rel 
				+"\",\"url\":\"" + url +"\",\"sign\":\""+ sign +"\"}";
		return response;
	}

}
