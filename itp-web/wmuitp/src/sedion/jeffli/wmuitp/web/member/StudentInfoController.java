package sedion.jeffli.wmuitp.web.member;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.StudentInfoConstant;
import sedion.jeffli.wmuitp.constant.StudentInfoConstantWeb;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.ClassInfo;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

@RequestMapping(value = "/studentInfo")
@Controller
public class StudentInfoController extends MainBase 
{
	
	@RequestMapping(value = "/initStudentPassword")//添加学生
	public @ResponseBody String initStudentPassword(String entityId)
	{
		int sign =	studentInfoService.initStudentInfoPassword(entityId);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse("重置成功",
									StudentInfoConstant.getStudentInfoUrl(),
									StudentInfoConstant.getStudentInfoRef());
		return failResponse("重置失败");
		
	}
	
	@RequestMapping(value = "/showStudentInfoInfoAdd")//显示学生添加页面
	public ModelAndView showStudentInfoInfoAdd(String studentId)
	{	
		System.out.println("studentId:"+studentId);
		
		ModelAndView mav = new ModelAndView(StudentInfoConstantWeb.getStudentInfoAddEditView());
		
		List<ClassInfo> classInfo  =  classInfoService.findAllClassInfo();
		
		
		
		mav.addObject("classInfos", classInfo);
		
		if(studentId != null && !studentId.equals(""))
		{
			StudentInfo studentInfo  =  studentInfoService.findStudentInfoById(studentId);
			
			mav.addObject(StudentInfoConstant.getStudentInfoObject(), studentInfo);
		}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/studentInfoAdd")//添加学生
	public @ResponseBody String studentInfoAdd(StudentInfo studentInfo,UserLogin userLogin,String classInfoId)
	{
		 int	sign =	studentInfoService.saveOrUpdateStudentInfo(studentInfo,userLogin,classInfoId);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
									StudentInfoConstant.getStudentInfoUrl(),
									StudentInfoConstant.getStudentInfoRef());
		else if(sign == Constant.RESULT_EXIST)
			return failResponse(EntityConstant.getSaveEntitySuccess()+"用户名已传在");
		
		return failResponse(EntityConstant.getSaveEntityFailure());
		
	}
	
	
	@RequestMapping(value = "/studentInfoDelete")//删除学生
	public @ResponseBody String studentInfoDelete(String ojbIdStr)
	{
		int sign = studentInfoService.deleteStudentInfo(ojbIdStr);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),
									StudentInfoConstant.getStudentInfoUrl(),
									StudentInfoConstant.getStudentInfoRef());
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	}

	@RequestMapping(value = "/studentInfoDetail")//显示学生详情
	public ModelAndView studentInfoDetail(String studentId)
	{	
		ModelAndView mav = new ModelAndView(StudentInfoConstantWeb.getStudentInfoDetailView());
		StudentInfo studentInfo  =  studentInfoService.findStudentInfoById(studentId);
		mav.addObject(StudentInfoConstant.getStudentInfoObject(), studentInfo);
		
		return mav;
	}
	@RequestMapping(value = "/studentInfos")//显示所有学生和学生搜索
	public ModelAndView studentInfos(String collegeName,
									String professionName,
									String studentNum,
									String studentName,
									String pageNo)
	{	
		System.out.println("学生查看"+studentNum);
		ModelAndView mav = new ModelAndView(StudentInfoConstantWeb.getStudentInfoListView());
		
		try
		{
			Page<StudentInfo> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			//判断前台是否传来page参数
			if(pageNo!=null)
				pageParams = PageUtil.init(page,pageNo);//分页初始化
			studentInfoService.getStudentInfoPagesForSearch(page,pageParams,collegeName,professionName,studentNum,studentName);
			mav.addObject(ConstantWeb.getPage(), page);
			mav.addObject("professionName",professionName);
			mav.addObject("collegeName",collegeName);
			mav.addObject("studentNum",studentNum);
			mav.addObject("studentName",studentName);
		} 
		catch (Exception e) 
		{
			throw new EntityException(" ProfessionInfoController studentInfoSearch(...) Error!!", e);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/showStudentAddByXls")//显示添加xls界面
	public ModelAndView showStudentAddByXls()
	{	
		ModelAndView mav = new ModelAndView(StudentInfoConstantWeb.getStudentInfoAddXls());
		List<ClassInfo> classInfos=classInfoService.findAllClassInfo();
		mav.addObject("classInfos",classInfos);
		return mav;
	}
	
	@RequestMapping(value = "/studentInfoAddXls")//根据xls添加学生详情
	public @ResponseBody String studentInfoAddXls(String classInfoId,@RequestParam(value = "xlsFile", required = false)MultipartFile file)
	{	
		System.out.println("classInfoId==="+classInfoId);
		UserLogin ul=AdminUtil.getUserLoginFromHttpSession(session);
		int count=studentInfoService.addStudentByXls(classInfoId,file);
		if(count == 0)
			return successResponse(EntityConstant.getSaveEntitySuccess(),StudentInfoConstant.getStudentInfoUrl(),StudentInfoConstant.getStudentInfoRef());
		else if(count ==-1)
			return failResponse("文件格式不对或者xls内容格式不对");
		else
		{
			return successXlsResponse(EntityConstant.getSaveEntitySuccess()+"，但有"+count+"项保存失败",
								"/cache/"+ul.getUlName() + ".xls",
								StudentInfoConstant.getStudentInfoRef(),
								StudentInfoConstant.getStudentInfoUrl(),
								"T");
		}
	}
	
}
