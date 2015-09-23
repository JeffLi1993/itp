package sedion.jeffli.wmuitp.web.member;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.ProfessionInfoConstant;
import sedion.jeffli.wmuitp.constant.StudentInfoConstant;
import sedion.jeffli.wmuitp.constant.StudentInfoConstantWeb;
import sedion.jeffli.wmuitp.constant.TeacherInfoConstant;
import sedion.jeffli.wmuitp.constant.TeacherInfoConstantWeb;
import sedion.jeffli.wmuitp.entity.ClassInfo;
import sedion.jeffli.wmuitp.entity.ProfessionInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

@RequestMapping(value = "/teacherInfo")
@Controller
public class TeacherInfoController extends MainBase 
{
	@RequestMapping(value = "/showTeacherAddByXls")//显示添加xls界面
	public ModelAndView showTeacherAddByXls()
	{	
		ModelAndView mav = new ModelAndView(TeacherInfoConstantWeb.getTeacherInfoXlsWeb());
		return mav;
	}
	@RequestMapping(value = "/teacherAddXls")//根据xls添加学生详情
	public @ResponseBody String teacherAddXls(@RequestParam(value = "xlsFile", required = false)MultipartFile file)
	{	
		UserLogin ul=AdminUtil.getUserLoginFromHttpSession(session);
		int count = teacherInfoService.addTeacherByXls(file);
		if(count == 0)
			return successResponse(EntityConstant.getSaveEntitySuccess(),TeacherInfoConstant.getTeacherInfoUrl(),TeacherInfoConstant.getTeacherInfoRef());
		else if(count ==-1)
			return failResponse("文件格式不对或者xls内容格式不对");
		else
		{
			return successXlsResponse(EntityConstant.getSaveEntitySuccess()+"，但有"+count+"项保存失败",
								"/cache/"+ul.getUlName() + ".xls",TeacherInfoConstant.getTeacherInfoRef(),
								TeacherInfoConstant.getTeacherInfoUrl(),"T");
		}
	}
	
	@RequestMapping(value = "/initTeacherInfoPassword")//添加学生
	public @ResponseBody String initTeacherInfoPassword(String entityId)
	{
		int sign =	teacherInfoService.initTeacherInfoPassword(entityId);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse("重置成功",
								TeacherInfoConstant.getTeacherInfoUrl(),
								TeacherInfoConstant.getTeacherInfoRef());
		return failResponse("重置失败");
		
	}
	
	@RequestMapping(value = "/showTeacherInfoAdd")//显示添加教师界面
	public ModelAndView showTeacherInfoAdd(String teacherInfoId)
	{	
		System.out.println("teacherInfoId:"+teacherInfoId);
		
		ModelAndView mav = new ModelAndView(TeacherInfoConstantWeb.getTeacherInfoAddEditView());
				   		     	     
		List<ProfessionInfo>  professionInfo   =  professionInfoService.findAllProfessionInfo();
		
		mav.addObject(ProfessionInfoConstant.getProfessionInfoObjectList(), professionInfo);
		
		if(teacherInfoId != null && !teacherInfoId.equals(""))
		{
			TeacherInfo teacherInfo  =  teacherInfoService.getTeacherInfoByTeacherInfoID(teacherInfoId);
			
			mav.addObject(TeacherInfoConstant.getTeacherInfoObject(), teacherInfo);
		}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/teacherInfoAdd")//教师添加
	public @ResponseBody String teacherInfoAdd(TeacherInfo teacherInfo,String professionInfoID,UserLogin userLogin,@RequestParam(value = "file", required = false)MultipartFile file) 
	{
		System.out.println("teacherInfoAdd正在运行");
		int	sign = teacherInfoService.saveOrUpdateTeacherInfo(teacherInfo,userLogin,professionInfoID,file);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
						TeacherInfoConstant.getTeacherInfoUrl(),
						TeacherInfoConstant.getTeacherInfoRef());
		else if(sign == Constant.RESULT_EXIST)
			return failResponse(EntityConstant.SAVE_ENTITY_FAILURE+"用户名已存在");
		
		return failResponse(EntityConstant.getSaveEntityFailure());
		
	}
	
	@RequestMapping(value = "/teacherInfotest")//????????????????????????????????????
	public @ResponseBody String teacherInfotest() 
	{
		System.out.println("teacherInfotest");
		
		return failResponse(EntityConstant.getSaveEntityFailure());
		
	}
	
	@RequestMapping(value = "/teacherInfoDelete")//教师删除
	public @ResponseBody String teacherInfoDelete(String ojbIdStr) 
	{
		
		int sign = teacherInfoService.deleteTeacherInfo(ojbIdStr);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),
								TeacherInfoConstant.getTeacherInfoUrl(),
								TeacherInfoConstant.getTeacherInfoRef());
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	}
	
	@RequestMapping(value = "/teacherInfoWebDetail")//显示教师手机端详情
	public ModelAndView teacherInfoWebDetail(String teacherInfoId)
	{	
		ModelAndView mav = new ModelAndView(TeacherInfoConstantWeb.getTeacherInfoWebDetail());
		TeacherInfo teacherInfo  =  teacherInfoService.getTeacherInfoByTeacherInfoID(teacherInfoId);
		mav.addObject(TeacherInfoConstant.getTeacherInfoObject(), teacherInfo);
		
		return mav;
	}
	
	@RequestMapping(value = "/teacherInfoDetail")//显示教师管理员界面详情
	public ModelAndView teacherInfoDetail(String teacherInfoId)
	{	
		ModelAndView mav = new ModelAndView(TeacherInfoConstantWeb.getTeacherInfoDetail());
		TeacherInfo teacherInfo  =  teacherInfoService.getTeacherInfoByTeacherInfoID(teacherInfoId);
		mav.addObject(TeacherInfoConstant.getTeacherInfoObject(), teacherInfo);
		
		return mav;
	}
	
	@RequestMapping(value = "/teacherInfoLookup")//显示教师查找带回  
	public ModelAndView teacherInfoLookup(String teacherInfoName,String professionName,String collegeName,String jobName)
	{	
		ModelAndView mav = new ModelAndView(TeacherInfoConstantWeb.getTeacherLookUpView());
		List<TeacherInfo> teacherInfos  =  teacherInfoService.getLessTeacherInfoForSearch(collegeName, professionName, teacherInfoName, jobName);
		mav.addObject("teacherInfos", teacherInfos);
		mav.addObject("teacherInfoName", teacherInfoName);
		mav.addObject("professionName", professionName);
		mav.addObject("collegeName", collegeName);
		mav.addObject("jobName", jobName);
		
		return mav;
	}
	
	@RequestMapping(value = "/teacherInfos")//显示所有教师或者搜索教师
	public ModelAndView teacherInfos(String collegeName,
										String professionName,
										String teacherInfoName,
										String jobName,
										String pageNo)
	{	
		ModelAndView mav = new ModelAndView(TeacherInfoConstantWeb.getTeacherInfoListView());
		
		try 
		{
			
			Page<TeacherInfo> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			//判断前台是否传来page参数
			if(pageNo!=null)
				pageParams = PageUtil.init(page,pageNo);//分页初始化
			teacherInfoService.getTeacherInfosPagesForSearch(page,pageParams,
									collegeName,professionName,teacherInfoName,jobName);
			
			mav.addObject(ConstantWeb.getPage(), page);
			mav.addObject("professionName",professionName);
			mav.addObject("collegeName",collegeName);
			mav.addObject("teacherInfoName",teacherInfoName);
			mav.addObject("jobName",jobName);
		} catch (Exception e) 
		{
			throw new EntityException(" TeacherInfoController teacherInfoSearch(...) Error!!", e);
		}
		
		return mav;
	}
	
}
