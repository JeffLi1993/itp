package sedion.jeffli.wmuitp.web.course;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.CourseConstant;
import sedion.jeffli.wmuitp.constant.CourseConstantWeb;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.entity.Course;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.util.DateUtil;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;
import sedion.jeffli.wmuitp.util.session.ClientUtil;
import sedion.jeffli.wmuitp.util.zxing.ZxingEncoderHelper;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;

@RequestMapping(value = "/course")
@Controller
public class CourseController extends MainBase 
{

	/**
	 * ---------------------------基础课程----------------------------
	 */
	@RequestMapping(value = "/courses")
	public ModelAndView courses() //获取全部基础课程
	{	
		ModelAndView mav = new ModelAndView(CourseConstantWeb.getCourseListView());
		
		try 
		{
			Page<Course> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			courseService.getCoursePages(page, pageParams);
			mav.addObject(ConstantWeb.getPage(), page);
		}
		catch (Exception e) 
		{
			throw new EntityException(" CourseController courses() Error!!", e);
		}
		
		return mav;
	}
	
	
	@RequestMapping(value="/showCourseAdd")
	public ModelAndView showCourseAdd(String CId)//显示添加基础课程页面
	{
		ModelAndView mav = new ModelAndView(CourseConstantWeb.getCourseListAddView());
		
		if(StringUtils.isNotEmpty(CId))
		{
			Course course = courseService.getCourseById(CId);
			
			mav.addObject(CourseConstant.getCourseObject(), course);
		}
		
		return mav;
	}
	
	
	@RequestMapping(value="/courseAdd")
	public @ResponseBody  String coureAdd(Course course)//添加基础课程
	{
		
		int sign = courseService.saveOrUpdateCourse(course);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
								   CourseConstant.getCourseUrl(),
								   CourseConstant.getCourseRef());

		return failResponse(EntityConstant.getSaveEntityFailure());
	}
	
	
	@RequestMapping(value="/courseDelete")
	public @ResponseBody String courseDelete(String ojbIdStr)//删除基础课程
	{
		int sign = courseService.deleteCourse(ojbIdStr);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
								   CourseConstant.getCourseUrl() ,
								   CourseConstant.getCourseRef());
		
		return failResponse(EntityConstant.getSaveEntityFailure());
	}
	
	@RequestMapping(value="/courseLookup")
	public ModelAndView courseLookup(String CName)//查找带回
	{
		ModelAndView mav = new ModelAndView(CourseConstantWeb.getCourseLookUpView());
		List<Course> courses = courseService.getCourseByCourseName(CName);
		mav.addObject("courses", courses);
		return mav;
	}
	
	
	
	/**
	 * *************************************************************
	 * 某堂课详细	开始
	 * *************************************************************
	 * @return
	 */
	//获取教师要上的某堂课详细
	@RequestMapping(value = "/courseinfos")
	public ModelAndView courseInfos() 
	{	
		
		List<CourseInfo> courseInfos = courseService.
				getCourseInfosByTeacherID("");
		
		ModelAndView mav = new ModelAndView(CourseConstantWeb.COURSE_INFO_LIST_VIEW);
		mav.addObject("courseInfos",courseInfos);
		
		return mav;
	}
	
	//显示添加课详情
	@RequestMapping(value = "/showcourseInfoAdd")
	public ModelAndView showcourseInfoAdd() 
	{	
		List<CourseTeacherRelation> courseTeacherRelations = courseService.
				getCourseTeacherRelationsByOBJ("");
		
		ModelAndView mav = new ModelAndView(CourseConstantWeb.COURSE_INFO_ADD_VIEW);
		mav.addObject("courseTeacherRelations",courseTeacherRelations);
		
		return mav;
	}
	
	//添加课详情
	@RequestMapping(value = "/courseInfoAdd")
	public @ResponseBody String courseInfoAdd(String courseTeacherRelationID,CourseInfo courseInfo) 
	{
		System.out.println("courseTeacherRelationID"+courseTeacherRelationID);
		System.out.println("getCiDateTime"+courseInfo.getCiDateTime());
		
		courseService.saveCourseInfo(courseInfo,courseTeacherRelationID);
		
		//生成二维码 png
		//String logoRealPathDir = ZxingEncoderHelper.getImgPath(request);
		String picStr  =  DateUtil.getFormateDateSimple()+".png";
		String pathStr =  CourseConstant.QR_CODE_PATH+"\\"+picStr;
		
		ZxingEncoderHelper.encode("http://"+Constant.WEB_IP+
				":8080/wmuitp/webservice/saveStudentCourseRelation?courseInfoID="+courseInfo.getCiId(),
				350, 350,pathStr);
		
		courseInfo.setCiQrcode(CourseConstant.ALL_QR_CODE_PATH+picStr);
		
		courseService.updateCourseInfo(courseInfo);
		
		//AJAX
		return successResponse("保存成功","../course/courseinfos" , "teachercourses");
	}
	
	/**
	 * ************************************************************
	 * 某堂课详细	结束
	 * ************************************************************
	 */
	
	/**
	 * --------------------------课详情和学生--------------------------
	 */
	
	//添加 课详情和学生
	//http://192.168.1.114:8080/wmuitp/course/studentCourseRelationAdd
	//  XXX  ???
	public String studentCourseRelationAdd(String studentInfoID,String courseInfoID) 
	{
		System.out.println("studentInfoID:"+studentInfoID);
		System.out.println("courseInfoID:"+courseInfoID);
		
//		CourseInfo courseInfo 	= courseService.findCourseInfoById(Integer.valueOf(courseInfoID));
//		StudentInfo studentInfo = studentInfoService.findStudentInfoById(studentInfoID);
		
		courseService.saveStudentCourseRelationBystudentInfoIDAndcourseInfoID(studentInfoID,courseInfoID);
		
		return null;
	}
		
	
	//课上详情和资料
	@RequestMapping(value = "/courseinfobyid")
	public ModelAndView courseInfoByID(String ciId) 
	{	
		System.out.println("ciId:"+ciId);
		
		//CourseInfo courseInfo = courseService.findCourseInfoById(Integer.valueOf(ciId));
				
		ModelAndView mav = new ModelAndView(CourseConstantWeb.COURSE_INFO_LIST_MORE_VIEW);
		
		List<StudentCourseRelation> studentCourseRelations = courseService.
				getStuStudentCourseRelationsByCIID(ciId);
		if (studentCourseRelations != null && studentCourseRelations.size() > 0)
		{
			String stuSumString = studentCourseRelations.get(0).
					getStudentInfo().getClassInfo().getCiStudentSum();
			System.out.println("stuSumString:"+stuSumString);
			
			mav.addObject("allStuSum",stuSumString);
			mav.addObject("nowStuSum",studentCourseRelations.size());
		}
		else 
		{
			mav.addObject("allStuSum",null);
		}
		
		
		return mav;
	}
		
	
	//获取某堂课详细
	@RequestMapping(value = "/courseqrcode")
	public ModelAndView courseqrcode() 
	{	
		List<CourseInfo> courseInfos = courseService.getCourseInfos();

		ModelAndView mav = new ModelAndView(CourseConstantWeb.COURSE_INFO_QRCODE_VIEW);
		mav.addObject("courseInfos",courseInfos);
		
		return mav;
	}
	
	//根据学生ID 获取学生课程大类
	@RequestMapping(value = "/getCoursesByStu")
	public ModelAndView getCoursesByStu() 
	{	
		List<Course> courses = courseService.getCoursesByStu(ClientUtil.getStudentLoginFromHttpSession(session));
		ModelAndView mav = new ModelAndView(CourseConstantWeb.COURSE_LIST_STU_VIEW);
		mav.addObject("courses",courses);
		
		return mav;
	}
}
