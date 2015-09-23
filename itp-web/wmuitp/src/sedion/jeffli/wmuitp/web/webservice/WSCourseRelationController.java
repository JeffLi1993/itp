package sedion.jeffli.wmuitp.web.webservice;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.CourseConstantWeb;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.util.DateUtil;

@RequestMapping(value = "/webservice")
@Controller
public class WSCourseRelationController extends MainBase 
{
	/**
	 * 签到
	 * @param courseInfoID
	 * @param userLoginID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/turnSCRPresent")
	public @ResponseBody String turnSCRPresent( String courseInfoID,  String  userLoginID) throws IOException 
	{

		Result 	result = new Result();
		
		int sign = 	studentCourseRelationServcie.turnSCRPresent(courseInfoID,userLoginID);
		
		if(sign == Constant.RESULT_SUCCESS)
			result.setResults(Constant.RESULT_SUCCESS);
		else if (sign == Constant.RESULT_EXIST) {
			result.setResults( Constant.RESULT_EXIST);
		}
		else
			result.setResults(Constant.RESULT_FAIL);
			
		JSONObject resultJsonObject = (JSONObject) JSON.toJSON(result);
		
		return resultJsonObject.toJSONString();
	}
	
	@RequestMapping(value = "/getCourseInfosByDateTime")
	public ModelAndView getCourseInfosByDateTime(String dateTimeStr) 
	{	
		if (dateTimeStr == null || dateTimeStr.equals(""))
			dateTimeStr = DateUtil.getFormateDateSimple();	//...
		
		List<CourseInfo> courseInfos = courseService.getCourseInfosByDateTime(dateTimeStr);
		
		ModelAndView mav = new ModelAndView(CourseConstantWeb.COURSE_BASE);
		mav.addObject("courseInfos",courseInfos);
		mav.addObject("dateTimeStr",dateTimeStr);
		
		return mav;
	}
	
	@RequestMapping(value = "/aa")
	public  @ResponseBody String helloWorld()  {
	
	  Result result = new Result(33);
	  JSONObject resultJsonObject = (JSONObject) JSON.toJSON(result);
	  
	  UserLogin login = new UserLogin();
	  login.setUlId(333);
	  login.setUlName("xx");
	  JSONObject loginJsonObject = (JSONObject) JSON.toJSON(login);
	  
	  return joinJsonObjects(resultJsonObject, loginJsonObject);
	}

}
