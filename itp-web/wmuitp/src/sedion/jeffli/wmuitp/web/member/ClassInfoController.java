package sedion.jeffli.wmuitp.web.member;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.ClassInfoConstant;
import sedion.jeffli.wmuitp.constant.ClassInfoWebConstant;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.ClassInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

@RequestMapping(value = "/classInfo")
@Controller
public class ClassInfoController extends MainBase 
{

	@RequestMapping(value = "/classInfos")//获得所有的班级详情
	public ModelAndView classInfos()
	{	
		ModelAndView mav = new ModelAndView(ClassInfoWebConstant.getClassInfoListView());
		
		try 
		{
			Page<ClassInfo> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			classInfoService.getClassInfosPages(page, pageParams);
			mav.addObject(ConstantWeb.getPage(), page);
		}
		catch (Exception e) 
		{
			throw new EntityException(" ClassInfoController ClassInfoController() Error!!", e);
		}
		return mav;
	}


	
	@RequestMapping(value = "/showClassInfoAdd")
	public ModelAndView showClassInfoAdd(String classInfoId)//显示添加页面
	{	
		System.out.println("classInfoId:"+classInfoId);
		
		ModelAndView mav = new ModelAndView(ClassInfoWebConstant.getClassInfoAddEditView());
		
		if(classInfoId != null && !classInfoId.equals(""))
		{
			ClassInfo classInfo  =  classInfoService.getClassInfoByClassInfoID(classInfoId);
			
			mav.addObject(ClassInfoConstant.getClassInfoObject(), classInfo);
		}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/classInfoAdd")
	public @ResponseBody String classInfoAdd(ClassInfo classInfo)//班级添加 
	{
		System.out.println("classInfo:"+classInfo.getCiName());

		int sign = classInfoService.saveOrUpdateClassInfo(classInfo);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
									ClassInfoConstant.getClassInfoUrl(),
								    ClassInfoConstant.getClassInfoRef());
		
		return failResponse(EntityConstant.getSaveEntityFailure());
		
	}
	
	
	@RequestMapping(value = "/classInfoDelete")
	public @ResponseBody String classInfoAdd(String ojbIdStr)//班级删除
	{
		int sign = classInfoService.deleteClassInfo(ojbIdStr);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),
									ClassInfoConstant.getClassInfoUrl(),
									ClassInfoConstant.getClassInfoRef());
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	}
	
	
	@RequestMapping(value = "/showClassInfoLookup")
	public ModelAndView showClassInfoLookup(String className,String professionName,String collegeName)//班级查找带回
	{	
		UserLogin userLogin=AdminUtil.getUserLoginFromHttpSession(session);
		ModelAndView mav = new ModelAndView(ClassInfoWebConstant.getClassInfoLookupView());
		mav.addObject("className", className);
		mav.addObject("professionName", professionName);
		mav.addObject("collegeName", collegeName);
		try
		{
			Page<ClassInfo> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			classInfoService.getClassInfosPagesBySearch(page, pageParams, className, professionName, collegeName);
			mav.addObject(ConstantWeb.getPage(), page);
			//获得常用班级
			List<ClassInfo> commonClassInfos= classInfoService.findCommonClassInfo(userLogin);
			mav.addObject("commonClassInfos",commonClassInfos);
			if(AdminUtil.getUserLoginFromHttpSession(session).getUlSign().equals("tea"))
			{
			//设置选择页面
			if(className==null&&professionName==null&&collegeName==null)
				mav.addObject("tabsNum", "0");
			}
		}
		catch (Exception e) 
		{
			throw new EntityException(" ClassInfoController showClassInfoLookup() Error!!", e);
		}
		
		return mav;
		
	}
	@RequestMapping(value = "/showClassInfoLookupSignle")
	public ModelAndView showClassInfoLookupSignle(String className,String professionName,String collegeName)//班级查找带回
	{	
		ModelAndView mav = new ModelAndView(ClassInfoWebConstant.getClassInfoLookupSignleView());
		mav.addObject("className", className);
		mav.addObject("professionName", professionName);
		mav.addObject("collegeName", collegeName);
		try
		{
			Page<ClassInfo> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			classInfoService.getClassInfosPagesBySearch(page, pageParams, className, professionName, collegeName);
			mav.addObject(ConstantWeb.getPage(), page);
		}
		catch (Exception e) 
		{
			throw new EntityException(" ClassInfoController showClassInfoLookup() Error!!", e);
		}
		
		return mav;
		
	}
}
