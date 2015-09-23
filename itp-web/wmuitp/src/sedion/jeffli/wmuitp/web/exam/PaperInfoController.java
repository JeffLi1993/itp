package sedion.jeffli.wmuitp.web.exam;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.PaperInfoConstant;
import sedion.jeffli.wmuitp.constant.PaperInfoConstantWeb;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.ExamStudent;
import sedion.jeffli.wmuitp.entity.ExamStudentResult;
import sedion.jeffli.wmuitp.entity.PaperInfo;
import sedion.jeffli.wmuitp.entity.PaperSubjectRelation;
import sedion.jeffli.wmuitp.entity.SubjectInfor;
import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.courseInfo.SubjectDetail;
import sedion.jeffli.wmuitp.util.FormatDate;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;
import sedion.jeffli.wmuitp.util.session.AdminUtil;
import sedion.jeffli.wmuitp.util.session.ClientUtil;

/**
 * 
 * @author Jeff Li
 * @version 1.0
 */
@RequestMapping(value = "/paperInfo")
@Controller
public class PaperInfoController extends MainBase 
{

	@RequestMapping(value = "/paperInfos")
	public ModelAndView paperInfos()
	{	
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoListView());
			
		Page<PaperInfo> page = new Page<>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page,request);//分页初始化
		paperInfoService.getPaperInfosPages(page, pageParams);
		
		mav.addObject(ConstantWeb.getPage(), page);
		
		return mav;
	}
	@RequestMapping(value = "/paperInfosTea")
	public ModelAndView paperInfosTea()
	{	
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoListTeaView());
			
		Page<PaperInfo> page = new Page<>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page,request);//分页初始化
		paperInfoService.getPaperInfosPagesTea(page, pageParams);
		
		mav.addObject(ConstantWeb.getPage(), page);
		
		return mav;
	}
	
	@RequestMapping(value = "/paperInfoDetail")
	public ModelAndView paperInfoDetail(String paperInfoId) 
	{
		System.out.println("paperInfoId::"+paperInfoId);
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoDetailView());
		PaperInfo paperInfo=paperInfoService.getPaperInfoByPaperInfoId(paperInfoId);
		mav.addObject("paperInfo", paperInfo);
		return mav;
	}
	
	@RequestMapping(value = "/paperInfoAdd")
	public @ResponseBody String paperInfoAdd(PaperInfo paperInfo,String courseInfoId,String siId) 
	{
		System.out.println("paperInfoId:1:"+paperInfo.getPiId()+courseInfoId);
		int sign = paperInfoService.saveOrUpdatePaperInfo(paperInfo,courseInfoId,siId);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
					PaperInfoConstant.getPaperInfoUrl() , 
					PaperInfoConstant.getPaperInfoRef());
		
		return failResponse(EntityConstant.getSaveEntityFailure());
	}
	@RequestMapping(value = "/paperInfoAddTea")
	public @ResponseBody String paperInfoAddTea(PaperInfo paperInfo,String courseInfoId,String siId) 
	{
		System.out.println("paperInfoId:2:"+paperInfo.getPiId());
		int sign = paperInfoService.saveOrUpdatePaperInfo(paperInfo,courseInfoId,siId);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),"../paperInfo/paperInfosTea" , 
					PaperInfoConstant.getPaperInfoRef());
		
		return failResponse(EntityConstant.getSaveEntityFailure());
	}
	
	
	@RequestMapping(value = "/showPaperInfoAdd")
	public ModelAndView showPaperInfoAdd(String paperInfoID)
	{	
		System.out.println("paperInfoID:"+paperInfoID);
		
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoAddEditView());
		
		List<CourseInfo>      courseInfos 	= courseInfoService.getCourseInfoAfterToday();
		List<TeacherInfo> 	  teacherInfos 	= teacherInfoService.getTeacherInfos();
		List<PaperSubjectRelation>   psr    = null;
		
		if(StringUtils.isNotEmpty(paperInfoID)){//修改
			
			psr = paperSubjectRelationService.getPaperSubjectRelationsByPaperInfoID(paperInfoID);
		
			StringBuilder paperSubjectRelationIds =new StringBuilder();
			StringBuilder paperSubjectRelationNames =new StringBuilder();
			
			for(int i=0;i<psr.size();i++)
			{
				paperSubjectRelationIds.append(psr.get(i).getSubjectInfor().getSiId());
				paperSubjectRelationNames.append(psr.get(i).getSubjectInfor().getCsTitle());
				if(i < psr.size()-1)
				{
					paperSubjectRelationIds.append(Constant.COMMA);
					paperSubjectRelationNames.append(Constant.COMMA);
				}
			}
			
			mav.addObject("paperSubjectRelationIds", paperSubjectRelationIds.toString());
			mav.addObject("paperSubjectRelationNames", paperSubjectRelationNames.toString());
			
			PaperInfo paperInfo = paperInfoService.getPaperInfoByPaperInfoId(paperInfoID);
			mav.addObject(PaperInfoConstant.getPaperInfoObject(), paperInfo);
		}
		
		mav.addObject("courseInfos", courseInfos);
		mav.addObject("teacherInfos", teacherInfos);

		return mav;
	}
	@RequestMapping(value = "/showPaperInfoAddTea")
	public ModelAndView showPaperInfoAddTea(String paperInfoId)
	{	
		System.out.println("showPaperInfoAdd");
		System.out.println("paperInfoID:"+paperInfoId);
		
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoAddEditTeaView());
		
		List<CourseInfo>      courseInfos 	= courseInfoService.getCourseInfoAfterToday();
		List<PaperSubjectRelation>   psr    = null;
		
		if(StringUtils.isNotEmpty(paperInfoId)){//修改
			
			psr = paperSubjectRelationService.getPaperSubjectRelationsByPaperInfoID(paperInfoId);
		
			StringBuilder paperSubjectRelationIds =new StringBuilder();
			StringBuilder paperSubjectRelationNames =new StringBuilder();
			
			for(int i=0;i<psr.size();i++)
			{
				paperSubjectRelationIds.append(psr.get(i).getSubjectInfor().getSiId());
				paperSubjectRelationNames.append(psr.get(i).getSubjectInfor().getCsTitle());
				if(i < psr.size()-1)
				{
					paperSubjectRelationIds.append(Constant.COMMA);
					paperSubjectRelationNames.append(Constant.COMMA);
				}
			}
			
			mav.addObject("paperSubjectRelationIds", paperSubjectRelationIds.toString());
			mav.addObject("paperSubjectRelationNames", paperSubjectRelationNames.toString());
			
			PaperInfo paperInfo = paperInfoService.getPaperInfoByPaperInfoId(paperInfoId);
			mav.addObject(PaperInfoConstant.getPaperInfoObject(), paperInfo);
		}
		
		mav.addObject("courseInfos", courseInfos);

		return mav;
	}
	
	@RequestMapping(value = "/showExamStatistical")
	public ModelAndView showExamStatistical() 
	{
		ModelAndView mav =new ModelAndView("/paperInfo/web/paperInfo.showStatistical");
		return mav;
	}
	@RequestMapping(value = "/showExamStatisticalTea")
	public ModelAndView showExamStatisticalTea() 
	{
		ModelAndView mav =new ModelAndView("/paperInfo/web/paperInfo.showStatistical.tea");
		return mav;
	}
	
	@RequestMapping(value = "/examStatisticalAddress")
	public @ResponseBody String showExamStatistical(String startDate,String endDate,String courseTeacherRelationID) 
	{
		UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
		System.out.println(startDate+endDate+"!"+courseTeacherRelationID);
		int sign =paperInfoService.getExamStatistical(startDate, endDate, courseTeacherRelationID);
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse("点击下载","/cache/"+ul.getUlName()+".xls","");
		
		return failResponse("导出失败");
	}
	
	
	/**
	 * =======================================APP webView Controllers====================================
	 */
	@RequestMapping(value = "/getPaperInfoByCIIdApp")
	public ModelAndView getPaperInfoByCIIdApp(String courseInfoId,RedirectAttributes attr)//根据课程详情ID,获取在线测试详情
	{	
		List<ExamStudent> examStudents= paperInfoService.getExamStudentsByCourseInfoID(courseInfoId,ClientUtil.getStudentLoginFromHttpSession(session));
		
		if (examStudents==null||examStudents.size()==0)//判断有没有课堂练习
		{
			System.out.println("...");
			attr.addAttribute("kindName", "测试");
			attr.addAttribute("desription", "对不起，该课堂未存在测试卷！");
			attr.addAttribute("courseInfoId",courseInfoId);
			return new ModelAndView("redirect:/tips/tip");
		}
		else
		{
			ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoListAppView());
			
			mav.addObject("examStudents",examStudents);
			mav.addObject("courseInfoId",courseInfoId);
			
			return mav;
		}
		
	}
	
	@RequestMapping(value = "/getPaperInfoBeforeByCIdApp")
	public ModelAndView getPaperInfoBeforeByCIdApp(String courseId,RedirectAttributes attr)//根据课ID,获取在线测试详情
	{	
		List<ExamStudent> examStudents= paperInfoService.getExamStudentsByCourseID(courseId,ClientUtil.getStudentLoginFromHttpSession(session));
		
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoListBeforeAppView());
		
		mav.addObject("examStudents",examStudents);
		mav.addObject("courseId",courseId);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/getPaperInfoByCIIdTeaApp")
	public ModelAndView getPaperInfoByCIIdTeaApp()//根据课程详情ID,获取在线测试详情
	{	
		List<PaperInfo> paperInfos = paperInfoService.getPaperInfosByTeaSession();
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoListTeaAppView());
		System.out.println(paperInfos.size());
		mav.addObject("paperInfos",paperInfos);
		return mav;
	}
	
	@RequestMapping(value = "/getPaperSubjectRelationsByPIIdApp")
	public ModelAndView getPaperSubjectRelationsByPIIdApp(String paperInfoId,RedirectAttributes attr) throws ParseException//根据课程详情ID,获取在线测试详情
	{	
		PaperInfo paperInfo = paperInfoService.getPaperInfoByPaperInfoId(paperInfoId);
		List<PaperSubjectRelation> paperSubjectRelations = paperSubjectRelationService.getPaperSubjectRelationsByPaperInfoID(paperInfoId);
		ExamStudent examStudent= examStudentResultService.getExamStudentByUserLogin(ClientUtil.
				getStudentLoginFromHttpSession(session),paperInfoId);
		List<ExamStudentResult> examStudentResults = examStudentResultService.getExamStudentResultByExamStudent(examStudent);
		String esSign = examStudent.getEsSign();
		
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPapersubjectRelationListAppView());
		mav.addObject("paperSubjectRelations",paperSubjectRelations);
		mav.addObject("courseInfoId", paperSubjectRelations.get(0).getPaperInfo().getCourseInfo().getCiId());
		mav.addObject("examStudentResults", examStudentResults);
		mav.addObject("paperInfoId",paperInfoId);
		mav.addObject("esSign",esSign);
		//时间问题：
		String dateSign = CommonConstant.FALSE;
		if (FormatDate.dateCompare(FormatDate.stringToDate(examStudent.getPaperInfo().getCourseInfo().getCiOver()),new Date()))
		{
			dateSign = CommonConstant.TRUE;
		}
		mav.addObject("dateSign",dateSign);
		System.out.println("if="+FormatDate.dateCompare(FormatDate.stringToDate(examStudent.getPaperInfo().getCourseInfo().getCiOver()),new Date()));
		System.out.println("examStudent.getPaperInfo().getCourseInfo().getCiOver()="+examStudent.getPaperInfo().getCourseInfo().getCiOver());
		System.out.println("new Date()="+new Date());
		System.out.println("esSign="+esSign);
		System.out.println("dateSign:"+dateSign);
		
		if (dateSign.equals(CommonConstant.TRUE))
			return mav;
		if (esSign.equals(CommonConstant.FALSE))
			return mav;
		if (paperInfo.getPiSign().equals(CommonConstant.TRUE))
			return mav;
		else
		{
			attr.addAttribute("kindName", "测试");
			attr.addAttribute("desription", "对不起，该测试卷未开启或关闭！");
			return new ModelAndView("redirect:/tips/tip");
		}
	}
	@RequestMapping(value = "/getPaperSubjectRelationsByPIIdTeaApp")
	public ModelAndView getPaperSubjectRelationsByPIIdTeaApp(String paperInfoId,RedirectAttributes attr)
	{	
		List<PaperSubjectRelation> paperSubjectRelations = paperSubjectRelationService.getPaperSubjectRelationsByPaperInfoID(paperInfoId);
		PaperInfo paperInfo = paperInfoService.getPaperInfoByPaperInfoId(paperInfoId);
		
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPapersubjectRelationListTeaAppView());
		if(paperSubjectRelations==null||paperSubjectRelations.size()==0)
		{
			attr.addAttribute("kindName", "测试");
			attr.addAttribute("desription", "试卷没有题目，请添加后在开启！");
			return new ModelAndView("redirect:/tips/tip");
		}
		mav.addObject("paperSubjectRelations",paperSubjectRelations);
		mav.addObject("paperInfoId",paperInfoId);
		mav.addObject("paperInfo",paperInfo);

		return mav;
	}
	
	@RequestMapping(value = "/turnPaperInfoSign")
	public @ResponseBody String turnPaperInfoSign(String paperInfoId)//转换开关
	{	
		int sign=paperInfoService.turnPaperInfoSign(paperInfoId);
		System.out.println("sign"+sign);
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse("操作成功","","");
		
		return failResponse("操作失败");
	}
	
	@RequestMapping(value = "/getPaperInfoStatistic")
	public ModelAndView  getPaperInfoStatistic(String paperInfoId,RedirectAttributes attr)//根据课程详情ID,获取在线测试详情
	{	
		List<SubjectDetail> answerDetails=paperInfoService.getPaperInfoStatistic(paperInfoId);
//		if(answerDetails==null||answerDetails.size()==0)
//		{
//			attr.addAttribute("kindName", "测试统计");
//			attr.addAttribute("desription", "对不起，暂时还没人完成测试卷！");
//			return new ModelAndView("redirect:/tips/tip");
//		}
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoStatisticView());
		PaperInfo paperInfo = paperInfoService.getPaperInfoByPaperInfoId(paperInfoId);
		mav.addObject("answerDetails",answerDetails);
		mav.addObject("paperInfoId",paperInfoId);
		mav.addObject("paperInfo",paperInfo);
	
		return mav;
	}
	
	@RequestMapping(value = "/getPaperInfoStatisticDetail")
	public ModelAndView  getPaperInfoStatisticDetail(String paperInfoId,String subjectInfoId)//根据考试卷I和题目Id,获取测试详情
	{	
		ModelAndView mav = new ModelAndView(PaperInfoConstantWeb.getPaperinfoStatisticDetailAppView());
		List<SubjectDetail> Details = paperInfoService.getPaperInfoStatisticDetail(paperInfoId, subjectInfoId);
		SubjectInfor subjectinfor = subjectInforService.getSubjectInforBySubjectInforID(subjectInfoId);
		mav.addObject("paperInfoId", paperInfoId);
		mav.addObject("subjectinfor", subjectinfor);
		mav.addObject("Details", Details);
		return mav;
	}
	
}
