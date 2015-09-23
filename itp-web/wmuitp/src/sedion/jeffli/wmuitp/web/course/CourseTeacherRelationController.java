package sedion.jeffli.wmuitp.web.course;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.CourseTeacherRelationWebConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

/**
 * 关于课程和老师的一系列操作
 * @author Li
 *
 */
@RequestMapping(value = "/courseTeacherRelation")
@Controller
public class CourseTeacherRelationController extends MainBase 
{
	//获取所有课程及授课老师
	@RequestMapping(value = "/courseTeacherRelations")
	public ModelAndView courseTeacherRelations() 
	{	
		ModelAndView mav = new ModelAndView(CourseTeacherRelationWebConstant.getTeacherCourseListView());
		try 
		{
			Page<CourseTeacherRelation> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			courseTeacherRelationService.getCourseTeacherRelationPages(page, pageParams);
			mav.addObject(ConstantWeb.getPage(), page);
		}
		catch (Exception e) 
		{
			throw new EntityException(" ClassInfoController ClassInfoController() Error!!", e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/courseTeacherRelationsTea")
	public ModelAndView courseTeacherRelationsTea() 
	{	
		ModelAndView mav = new ModelAndView(CourseTeacherRelationWebConstant.getTeacherCourseListViewTea());
		try 
		{
			Page<CourseTeacherRelation> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			courseTeacherRelationService.getCourseTeacherRelationPagesTea(page, pageParams);
			mav.addObject(ConstantWeb.getPage(), page);
		}
		catch (Exception e) 
		{
			throw new EntityException(" ClassInfoController ClassInfoController() Error!!", e);
		}
		return mav;
	}
	
	//显示添加课程及授课老师
	@RequestMapping(value="/showCourseTeacherRelationAdd")
	public ModelAndView showCourseTeacherRelationAdd(String CTRId)
	{
		
		ModelAndView mav=new ModelAndView(CourseTeacherRelationWebConstant.getTeacherCourseAddView());
		//判断是否为修改
		if(StringUtils.trimToNull(CTRId)!=null)
		{
			CourseTeacherRelation courseTeacherRelation = courseTeacherRelationService.getCourseTeacherRelationById(CTRId);
			mav.addObject("courseTeacherRelation", courseTeacherRelation);
		}
		
		return mav;
	}
	
	//显示添加课程及授课老师
	@RequestMapping(value="/showCourseTeacherRelationAddTea")
	public ModelAndView showCourseTeacherRelationAddTea(String CTRId)
	{
		ModelAndView mav=new ModelAndView(CourseTeacherRelationWebConstant.getTeacherCourseAddViewTea());
		UserLogin userLogin = AdminUtil.getUserLoginFromHttpSession(session);
		TeacherInfo teacherInfo=teacherInfoService.getTeacherInfoByTeacherInfoUserLogin(userLogin);
		mav.addObject("teacherInfo", teacherInfo);
		//判断是否为修改
		if(StringUtils.trimToNull(CTRId)!=null)
		{
			CourseTeacherRelation courseTeacherRelation = courseTeacherRelationService.getCourseTeacherRelationById(CTRId);
			mav.addObject("courseTeacherRelation", courseTeacherRelation);
		}
		return mav;
	}
	
	@RequestMapping(value = "/courseTeacherRelationAdd")
	public @ResponseBody String courseTeacherRelationAdd(String courseID,String teacherInfoID,CourseTeacherRelation courseTeacherRelation) 
	{
		System.out.println("courseChapterID:" + courseID + " :: "+teacherInfoID);
		
		try
		{
			int sign = courseTeacherRelationService.saveOrUpdateCourseTeacherRelation(courseID,teacherInfoID,courseTeacherRelation);
			
			if( sign == Constant.RESULT_SUCCESS )
				return successResponse("保存成功","../courseTeacherRelation/courseTeacherRelations" , "courseTeacherRelations");
		}
		catch (Exception e)
		{
		      throw new EntityException("Error！ when save the entity",e);
		}
		
		return failResponse("保存失败");
		
	}
	
	@RequestMapping(value = "/courseTeacherRelationAddTea")
	public @ResponseBody String courseTeacherRelationAddTea(String courseID,String teacherInfoID,CourseTeacherRelation courseTeacherRelation) 
	{
		System.out.println("courseChapterID:" + courseID + " :: "+teacherInfoID);
		
		try
		{
			int sign = courseTeacherRelationService.saveOrUpdateCourseTeacherRelation(courseID,teacherInfoID,courseTeacherRelation);
			if( sign == Constant.RESULT_SUCCESS )
				return successResponse("保存成功","../courseTeacherRelation/courseTeacherRelationsTea" , "courseTeacherRelations");
		}
		catch (Exception e)
		{
		      throw new EntityException("Error！ when save the entity",e);
		}
		
		return failResponse("保存失败");
		
	}
	
	@RequestMapping(value = "/courseTeacherRelationDelete")
	public @ResponseBody String subjectInforDelete(String ojbIdStr) 
	{
		try
		{
			int sign = courseTeacherRelationService.deleteCourseTeacherRelations(ojbIdStr);
			if(sign ==  Constant.RESULT_SUCCESS)
				return successResponse("删除成功","../courseTeacherRelation/courseTeacherRelations" , "courseTeacherRelations");
		}
		catch (Exception e)
		{
		      throw new EntityException("Error！ when delete the entity",e);
		}
		
		return failResponse("删除失败");
	
	}
	
	@RequestMapping(value = "/courseTeacherRelationDeleteTea")
	public @ResponseBody String courseTeacherRelationDeleteTea(String ojbIdStr) 
	{
		try
		{
			int sign = courseTeacherRelationService.deleteCourseTeacherRelations(ojbIdStr);
			if(sign ==  Constant.RESULT_SUCCESS)
				return successResponse("删除成功","../courseTeacherRelation/courseTeacherRelationsTea" , "courseTeacherRelations");
		}
		catch (Exception e)
		{
		      throw new EntityException("Error！ when delete the entity",e);
		}
		
		return failResponse("删除失败");
	
	}
	
	@RequestMapping(value="/showCourseTeacherRelationLookUp")
	public ModelAndView showCourseTeacherRelationLookUp(String courseName,String teacherName)
	{
		ModelAndView mav=new ModelAndView(CourseTeacherRelationWebConstant.getTeacherCourseLookUpView());
		List<CourseTeacherRelation> courseTeacherRelations = courseTeacherRelationService.getCourseTeacherRelationSearchLess(courseName, teacherName);
		mav.addObject("courseTeacherRelations", courseTeacherRelations);
		return mav; 
	}
	
	@RequestMapping(value="/showCourseTeacherRelationLookUpTea")
	public ModelAndView showCourseTeacherRelationLookUpTea(String courseName,String teacherName)
	{
		ModelAndView mav=new ModelAndView(CourseTeacherRelationWebConstant.getTeacherCourseLookUpView());
		List<CourseTeacherRelation> courseTeacherRelations = courseTeacherRelationService.getCourseTeacherRelationSearchLessTea(courseName, teacherName);
		mav.addObject("courseTeacherRelations", courseTeacherRelations);
		return mav; 
	}
}
