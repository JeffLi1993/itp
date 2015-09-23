package sedion.jeffli.wmuitp.web.course;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.CourseChapterConstant;
import sedion.jeffli.wmuitp.constant.CourseChapterConstantWeb;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.CourseChapter;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;
import sedion.jeffli.wmuitp.util.session.AdminUtil;
@RequestMapping(value = "/courseChapter")
@Controller
public class CourseChapterController extends MainBase 
{

	@RequestMapping(value = "/courseChapters")
	public ModelAndView subjectInfos(String courseName)
	{	
		ModelAndView mav = new ModelAndView(CourseChapterConstantWeb.getCourseChapterListView());
			
		Page<CourseChapter> page = new Page<>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page,request);//分页初始化
		courseChapterService.getCourseChaptersPagesSearchByCourseName(page, pageParams, courseName);
		
		mav.addObject("courseName", courseName);
		mav.addObject(ConstantWeb.getPage(), page);
		
		return mav;
	}
	
	@RequestMapping(value = "/showcourseChapterAdd")
	public ModelAndView showcourseChapterAdd(String courseChapterID)
	{	
		ModelAndView mav = new ModelAndView(CourseChapterConstantWeb.getCourseChapterAddEditView());
		if(StringUtils.isNotEmpty(courseChapterID))
		{
			CourseChapter courseChapter  =  courseChapterService.getCourseChapterById(courseChapterID);
			mav.addObject(CourseChapterConstant.getCourseChapterObject(), courseChapter);
		}
		
		return mav;
	}

	@RequestMapping(value = "/courseChapterAdd")
	public @ResponseBody String courseChapterAdd(String courseID,CourseChapter courseChapter) 
	{
		int sign = courseChapterService.saveOrUpdateCourseChapter(courseID,courseChapter);
		
		if(sign == 1)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
					CourseChapterConstant.getCourseChapterUrl(), 
					CourseChapterConstant.getCourseChapterRef());
		
		return failResponse(EntityConstant.getSaveEntityFailure());
	}
	
	@RequestMapping(value = "/courseChapterDelete")
	public @ResponseBody String courseChapterDelete(String ojbIdStr) 
	{
		int sign = courseChapterService.deleteCourseChapter(ojbIdStr);
		
		if(sign == 1)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),
					CourseChapterConstant.getCourseChapterUrl(),
					CourseChapterConstant.getCourseChapterRef());
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	}
	
	@RequestMapping(value = "/showCourseChapterAddByXls")//显示添加xls界面
	public ModelAndView showCourseChapterAddByXls()
	{	
		ModelAndView mav = new ModelAndView(CourseChapterConstantWeb.getCourseChapterAddXls());
		return mav;
	}
	
	@RequestMapping(value = "/courseChapterAddXls")//根据xls添加学生详情
	public @ResponseBody String courseChapterAddXls(@RequestParam(value = "xlsFile", required = false)MultipartFile file)
	{	
		UserLogin ul=AdminUtil.getUserLoginFromHttpSession(session);
		int count=courseChapterService.addCourseChapterByXls(file);
		if(count == 0)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),
					CourseChapterConstant.getCourseChapterUrl(),
					CourseChapterConstant.getCourseChapterRef());
		else if(count ==-1)
			return failResponse("文件格式不对或者xls内容格式不对");
		else
		{
			return successXlsResponse(EntityConstant.getSaveEntitySuccess()+"，但有"+count+"项保存失败",
								"/cache/"+ul.getUlName() + ".xls",
								CourseChapterConstant.getCourseChapterRef(),
								CourseChapterConstant.getCourseChapterUrl(),
								"T");
		}
	}
	@RequestMapping(value = "/showCourseChapterLookUpTea")//查找带回
	public ModelAndView showCourseChapterLookUpTea(String courseName)
	{	
		ModelAndView mav = new ModelAndView(CourseChapterConstantWeb.getCourseChapterLookUpTeaView());
		List<CourseChapter> courseChapters = courseChapterService.getCourseChaptersByTeaSession(courseName);
		mav.addObject("courseChapters", courseChapters);
		mav.addObject("courseName", courseName);
		return mav;
	}
	
	@RequestMapping(value = "/showCourseChapterLookUp")//查找带回
	public ModelAndView showCourseChapterLookUp(String courseName)
	{	
		ModelAndView mav = new ModelAndView(CourseChapterConstantWeb.getCourseChapterLookUpView());
		List<CourseChapter> courseChapters = courseChapterService.getCourseChaptersBySearch(courseName);
		mav.addObject("courseChapters", courseChapters);
		mav.addObject("courseName", courseName);
		return mav;
	}
}
