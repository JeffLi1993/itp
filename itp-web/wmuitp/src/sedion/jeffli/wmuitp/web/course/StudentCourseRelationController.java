package sedion.jeffli.wmuitp.web.course;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.CourseInfoConstantWeb;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.database.MD5;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.courseInfo.StudentPresent;
import sedion.jeffli.wmuitp.util.DateUtil;
import sedion.jeffli.wmuitp.util.session.AdminUtil;
import sedion.jeffli.wmuitp.util.session.ClientUtil;

/**
 * 关于课程和学生的一系列操作
 * @author Li
 *
 */
@RequestMapping(value = "/studentCourseRelation")
@Controller
public class StudentCourseRelationController extends MainBase 
{
	
	/**
	 * =======================================APP webView Controllers====================================
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/getAppIndex")
	public ModelAndView getAppIndex(String ulName,String ulPassword) //作为首页
	{	
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoIndexView());
		mav.addObject("ulName", ulName);
		mav.addObject("ulPassword",ulPassword);
		System.out.println("ulName"+ulName+";ulPassword="+ulPassword);
		return mav;
	}
	
	@RequestMapping(value = "/getStudentCourseRelationsByUserLoginApp")
	public ModelAndView getStudentCourseRelationsByUserLoginApp(String ulName,String ulPassword) throws ParseException//根据用户信息,获取相关在线课程
	{	
		System.out.println("ulPassword:"+ulPassword);
		//学生老师客户端登录检验
		UserLogin userLogin = userLoginService.findUserLoginByULName(ulName);
		//ulPassword = MD5.EncryptionByMD5(ulPassword);//MD5加密
	 	if(!userLogin.getUlPassword().equals(ulPassword))//如果账号和密码对不上，将userLogin变为null，跳转到500
	 		userLogin = null;
	 	System.out.println(userLogin);
		ModelAndView mav = null;
		if (userLogin.getUlSign().equals(CommonConstant.STUDENT_PER))
		{
			ClientUtil.saveStudentLoginToSession(session, userLogin);//存session
			ClientUtil.saveStudentIDToSession(session, studentInfoService.
					findStudentInfoByULID(userLogin.getUlId().toString()));
			
			List<StudentCourseRelation> studentCourseRelations = studentCourseRelationServcie.getStudentCourseRelationsByStudentLogin(userLogin,null);
			mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoListAppView());
			mav.addObject("DataStrs",DateUtil.getDate().toString());
//			System.out.println("DataStrs==*="+DateUtil.getDate().toString());
			if(studentCourseRelations!=null&&studentCourseRelations.size()!=0)
				mav.addObject("studentCourseRelations",studentCourseRelations);
		}
		else
		{
			AdminUtil.saveAdminToSession(session, userLogin);//存session
			AdminUtil.saveTeacherIDToSession(session, teacherInfoService.
					getTeacherInfoIDByOBJ(userLogin.getUlId().toString()));
			List<CourseInfo> courseInfos = courseInfoService.getCourseInfosByTeacherLogin(userLogin);//TODO 1
			mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoFuctionsTeaAppView());
//			mav.addObject("DataStrs",DateUtil.getDate().toString());
			if(courseInfos!=null&&courseInfos.size()!=0)
			mav.addObject("courseInfos",courseInfos);
		}
		
		return mav;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getStudentCourseRelationsChangeDate")
	public ModelAndView getStudentCourseRelationsChangeDate(String ulName,String ulPassword,String DataStr,RedirectAttributes attr) throws ParseException//根据用户信息,获取相关在线课程
	{	
		if(DataStr==null&&DataStr=="")
		{
			attr.addAttribute("kindName", "登录");
			attr.addAttribute("desription", "对不起，登录超时！");
			return new ModelAndView("redirect:/tips/tip?");        
		}
		//保存日期                                                            
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");              
		List<String> DateArray=new ArrayList<String>();
		DateArray.add(DataStr.substring(0,7));
		DateArray.add(DataStr.substring(8,10));
		DateArray.add(sdf.parse(DataStr).toString().substring(0,3));
		DateArray.add(sdf.format(DateUtil.getYesterdayDate(sdf.parse(DataStr))).substring(8, 10));
		DateArray.add(DateUtil.getYesterdayDate(sdf.parse(DataStr)).toString().substring(0,3));
		DateArray.add(sdf.format(DateUtil.getTomorrowDate(sdf.parse(DataStr))).substring(8, 10));
		DateArray.add(DateUtil.getTomorrowDate(sdf.parse(DataStr)).toString().substring(0,3));
		session.setAttribute("DateArray", DateArray);
		
		//从session中获得UserLogin
		UserLogin userLogin=AdminUtil.getUserLoginFromHttpSession(session);
		if(userLogin==null)
			userLogin=ClientUtil.getStudentLoginFromHttpSession(session);
		ulName = userLogin.getUlName();
		ulPassword = userLogin.getUlPassword();
	
		//学生老师客户端登录检验
		ModelAndView mav = null;
		if (userLogin.getUlSign().equals(CommonConstant.STUDENT_PER))
		{
			List<StudentCourseRelation> studentCourseRelations = studentCourseRelationServcie.getStudentCourseRelationsByStudentLogin(userLogin,DataStr);
			System.out.println("CourseInfoConstantWeb.getCourseInfoListAppAjaxView()");
			mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoListAppAjaxView());
			mav.addObject("DataStrs",DataStr);
			
			if(studentCourseRelations!=null&&studentCourseRelations.size()!=0)
				mav.addObject("studentCourseRelations",studentCourseRelations);
			
		}
		/*else
		{
			List<CourseInfo> courseInfos = courseInfoService.getCourseInfosByTeacherLogin(userLogin,DataStr);// 老师端
			mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoTeaListAppAjaxView());
			mav.addObject("DataStrs",DataStr);
			
			if(courseInfos!=null&&courseInfos.size()!=0)
				mav.addObject("courseInfos",courseInfos);
		}*/
		
		return mav;
	}
	
	@RequestMapping(value = "/getStudentCourseRelationsBySession")
	public ModelAndView getStudentCourseRelationsBySession(String UlId,String UserType)//根据session，跳转到首页
	{	
		System.out.println("UlId="+UlId);
		System.out.println("UserType="+UserType);
		ModelAndView mav = null;
		if(UserType.equals("stu")) {
			UserLogin userLogin =  userLoginService.findUserLoginById(Integer.valueOf(UlId));
			List<StudentCourseRelation> studentCourseRelations = studentCourseRelationServcie.getStudentCourseRelationsByStudentLogin(userLogin,null);
			
			mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoListAppView());
			mav.addObject("studentCourseRelations",studentCourseRelations);
		}
		else
		{
			UserLogin userLogin =  userLoginService.findUserLoginById(Integer.valueOf(UlId));
			List<CourseInfo> courseInfos = courseInfoService.getCourseInfosByTeacherLogin(userLogin); // TODO 3
			
			mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoFuctionsTeaAppView());
			mav.addObject("courseInfos",courseInfos);
		}
		
	
		return mav;
	}
	
	@RequestMapping(value = "/getAttendanceApp")
	public ModelAndView getAttendanceApp(String courseInfoId)
	{	
		List<StudentPresent> couresAndClassInfos = studentCourseRelationServcie.getAttendance(courseInfoId);
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseAndClassInfoAppView());
		System.out.println(couresAndClassInfos);
		mav.addObject("courseInfoId",courseInfoId);
		mav.addObject("couresAndClassInfos", couresAndClassInfos);
		
		return mav;
	}
	@RequestMapping(value = "/getAbsentDetailApp")
	public ModelAndView getAbsentDetailApp(String courseInfoId,String classId)
	{	
		System.out.println("courseInfoId="+courseInfoId);
		System.out.println("classId="+classId);
		List<StudentInfo> studentInfos = studentCourseRelationServcie.getAbsentDetail(courseInfoId,classId);
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseAbsentDetailAppView());
		mav.addObject("studentInfos", studentInfos);
		mav.addObject("courseInfoId", courseInfoId);
		return mav;
	}
}
