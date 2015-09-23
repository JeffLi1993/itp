package sedion.jeffli.wmuitp.web.exam;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.SubjectInforConstant;
import sedion.jeffli.wmuitp.constant.SubjectInforConstantWeb;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.CourseChapter;
import sedion.jeffli.wmuitp.entity.SubjectInfor;
import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;
import sedion.jeffli.wmuitp.util.session.AdminUtil;
/**
 * 
 * @author Jeff Li
 * @version 1.0
 */
@RequestMapping(value = "/subjectInfor")
@Controller
public class SubjectInforController extends MainBase 
{

	@RequestMapping(value = "/subjectInfors")
	public ModelAndView subjectInfos(String subjectInfoName,String teacherInfoName,String courseName,String courserChapterName,String pageNo)
	{	
		ModelAndView mav = new ModelAndView(SubjectInforConstantWeb.getSubjectInforListView());
		try
		{
			Page<SubjectInfor> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			//判断前台是否传来page参数
			if(pageNo!=null)
				pageParams = PageUtil.init(page,pageNo);//分页初始化
			subjectInforService.getSubjectInforsPages(page, pageParams, subjectInfoName, teacherInfoName, courseName, courserChapterName);
			mav.addObject(ConstantWeb.getPage(), page);
			mav.addObject("subjectInfoName", subjectInfoName);
			mav.addObject("teacherInfoName", teacherInfoName);
			mav.addObject("courseName", courseName);
			mav.addObject("courserChapterName", courserChapterName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return mav;
	}
	@RequestMapping(value = "/subjectInforsTea")
	public ModelAndView subjectInforsTea(String subjectInfoName,String teacherInfoName,String courseName,String courserChapterName,String pageNo)
	{	
		ModelAndView mav = new ModelAndView(SubjectInforConstantWeb.getSubjectInforListTeaView());
		try
		{
			Page<SubjectInfor> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			//判断前台是否传来page参数
			if(pageNo!=null)
				pageParams = PageUtil.init(page,pageNo);//分页初始化
			subjectInforService.getSubjectInforsPagesTea(page, pageParams, subjectInfoName, teacherInfoName, courseName, courserChapterName);
			mav.addObject(ConstantWeb.getPage(), page);
			mav.addObject("subjectInfoName", subjectInfoName);
			mav.addObject("teacherInfoName", teacherInfoName);
			mav.addObject("courseName", courseName);
			mav.addObject("courserChapterName", courserChapterName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/subjectInforDetail")
	public ModelAndView subjectInforDetail(String subjectInforID)
	{	
		SubjectInfor subjectInfor  =  subjectInforService.getSubjectInforBySubjectInforID(subjectInforID);
		
		ModelAndView mav = new ModelAndView(SubjectInforConstantWeb.getSubjectInforDetailView());
		mav.addObject(SubjectInforConstant.getSubjectInforObject(), subjectInfor);
		
		return mav;
	}
	
	@RequestMapping(value = "/showSubjectInforAdd")
	public ModelAndView showSubjectInforAdd(String subjectInforID)
	{	
		System.out.println("subjectInforID:"+subjectInforID);
		
		ModelAndView mav = new ModelAndView(SubjectInforConstantWeb.getSubjectInforAddEditView());
	
		List<TeacherInfo> 	teacherInfos   = teacherInfoService.getTeacherInfos();
		List<CourseChapter> courseChapters = courseChapterService.getCourseChapters();

		mav.addObject("teacherInfos", teacherInfos);
		mav.addObject("courseChapters", courseChapters);
		
		if(subjectInforID != null && !subjectInforID.equals(""))
		{
			SubjectInfor subjectInfor  =  subjectInforService.getSubjectInforBySubjectInforID(subjectInforID);
			mav.addObject(SubjectInforConstant.getSubjectInforObject(), subjectInfor);
		}
		return mav;
	}
	
	@RequestMapping(value = "/showSubjectInforAddTea")
	public ModelAndView showSubjectInforAddTea(String subjectInforID)
	{	
		System.out.println("subjectInforID:"+subjectInforID);
		
		ModelAndView mav = new ModelAndView(SubjectInforConstantWeb.getSubjectInforAddEditTeaView());
		UserLogin userLogin = AdminUtil.getUserLoginFromHttpSession(session);
		TeacherInfo 	teacherInfo   = teacherInfoService.getTeacherInfoByTeacherInfoUserLogin(userLogin);
		List<CourseChapter> courseChapters = courseChapterService.getCourseChapters();

		mav.addObject("teacherInfo", teacherInfo);
		mav.addObject("courseChapters", courseChapters);
		
		if(subjectInforID != null && !subjectInforID.equals(""))
		{
			SubjectInfor subjectInfor  =  subjectInforService.getSubjectInforBySubjectInforID(subjectInforID);
			mav.addObject(SubjectInforConstant.getSubjectInforObject(), subjectInfor);
		}
		return mav;
	}

	@RequestMapping(value = "/subjectInforAdd")
	public @ResponseBody String subjectInforAdd(String courseChapterID,String teacherInfoID,SubjectInfor subjectInfor) 
	{
		int sign = subjectInforService.saveOrUpdateSubjectInfor(subjectInfor,courseChapterID,teacherInfoID);
		
		if(sign == 1)
			return successResponse(EntityConstant.getSaveEntitySuccess(),SubjectInforConstant.getSubjectInforUrl() , SubjectInforConstant.getSubjectInforRef());
		
		return failResponse(EntityConstant.getSaveEntityFailure());
	}
	
	@RequestMapping(value = "/subjectInforAddTea")
	public @ResponseBody String subjectInforAddTea(String courseChapterID,String teacherInfoID,SubjectInfor subjectInfor) 
	{
		int sign = subjectInforService.saveOrUpdateSubjectInfor(subjectInfor,courseChapterID,teacherInfoID);
		
		if(sign == 1)
			return successResponse(EntityConstant.getSaveEntitySuccess(),"../subjectInfor/subjectInforsTea" , SubjectInforConstant.getSubjectInforRef());
		
		return failResponse(EntityConstant.getSaveEntityFailure());
	}
	
	@RequestMapping(value = "/subjectInforDelete")
	public @ResponseBody String subjectInforDelete(String ojbIdStr) 
	{
		int sign = subjectInforService.deleteSubjectInfors(ojbIdStr);
		
		if(sign == 1)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),SubjectInforConstant.getSubjectInforUrl() , SubjectInforConstant.getSubjectInforRef());
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	}
	@RequestMapping(value = "/subjectInforDeleteTea")
	public @ResponseBody String subjectInforDeleteTea(String ojbIdStr) 
	{
		int sign = subjectInforService.deleteSubjectInfors(ojbIdStr);
		
		if(sign == 1)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),"../subjectInfor/subjectInforsTea" , SubjectInforConstant.getSubjectInforRef());
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	}
	@RequestMapping(value = "/showSubjectInforAddByXls")//显示添加xls界面
	public ModelAndView showSubjectInforAddByXls()
	{	
		ModelAndView mav = new ModelAndView(SubjectInforConstantWeb.getSubjectInforAddXlsView());
		List<CourseChapter> courseChapters=courseChapterService.getCourseChapters();
		mav.addObject("courseChapters",courseChapters);
		return mav;
	}
	
	@RequestMapping(value = "/subjectInforAddXlsTea")//根据xls添加题目
	public @ResponseBody String subjectInforAddXlsTea(String courseChaptersid,@RequestParam(value = "xlsFile", required = false)MultipartFile file)
	{	
		System.out.println("courseChaptersid="+courseChaptersid);
		UserLogin ul=AdminUtil.getUserLoginFromHttpSession(session);
		int count=subjectInforService.addSubjectInforByXls(courseChaptersid,file);
		if(count == 0)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
					"../subjectInfor/subjectInforsTea",
					SubjectInforConstant.getSubjectInforRef());
		else if(count ==-1)
			return failResponse("文件格式不对或者xls内容格式不对");
		else
		{
			return successXlsResponse(EntityConstant.getSaveEntitySuccess()+"，但有"+count+"项保存失败",
								"/cache/"+ul.getUlName() + ".xls",
								SubjectInforConstant.getSubjectInforRef(),
								"../subjectInfor/subjectInforsTea",
								"T");
		}
	}
	/**
	 * ===============================该类主要逻辑=================================
	 */
	
	@RequestMapping(value = "/subjectInforsForLookUp")
	public ModelAndView subjectInforsForLookUp(String courseChapterName,String teacherName,String courseName)
	{	
		ModelAndView mav = new ModelAndView(SubjectInforConstantWeb.getSubjectInforListForLuView());
		List<SubjectInfor> subjectInfors= subjectInforService.getSubjectInforsByChapterTeacherAndCourseMore(courseChapterName,teacherName,courseName);
		mav.addObject("subjectInfors", subjectInfors);
		if(courseChapterName!=null)
			mav.addObject("courseChapterName", courseChapterName);
		if(teacherName!=null)
			mav.addObject("teacherName", teacherName);
		if(courseName!=null)
			mav.addObject("courseName", courseName);
		return mav;
	}
}
