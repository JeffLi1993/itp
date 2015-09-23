package sedion.jeffli.wmuitp.web.exam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.PaperInfoConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.util.session.ClientUtil;

/**
 * 
 * @author Jeff Li
 * @version 1.0
 */
@RequestMapping(value = "/examStudentResult")
@Controller
public class ExamStudentResultController extends MainBase {

	@RequestMapping(value = "/saveExamStudentResults")
	public synchronized @ResponseBody String saveExamStudentResults(
			String examStudentResultStr, String paperInfoId) {
		System.out.println("examStudentResultStr:" + examStudentResultStr);
		System.out.println("userLogin："
				+ ClientUtil.getStudentLoginFromHttpSession(session).getUlId());
		int sign = examStudentResultService
				.saveExamStudentResults(examStudentResultStr,
						ClientUtil.getStudentLoginFromHttpSession(session),
						paperInfoId);
		System.out.println("sign==========" + sign);
		// TODO 考试问题
		// final String esrStr = examStudentResultStr;
		// final String UesrId =
		// ClientUtil.getStudentLoginFromHttpSession(session).getUlId().toString();
		// final String piId = paperInfoId;
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// examStudentResultService.saveExamStudentResultsByNewThread(esrStr,UesrId,piId);
		// }
		// }).start();
		if (sign == Constant.RESULT_SUCCESS)
			return successResponse("提交成功", "", "");

		return failResponse("提交失败，教师已经关闭考试");
	}

	
	
	@RequestMapping(value = "/test")
	public int Test(String examStudentResultStr, String UesrId,
			String paperInfoId) {
		int sign = examStudentResultService.saveExamStudentResultsByNewThread(
				examStudentResultStr, UesrId, paperInfoId);
		System.out.println("sign==" + sign);
		return sign;
	}
	//
	// @RequestMapping(value = "/subjectInforDetail")
	// public ModelAndView subjectInforDetail(String subjectInforID)
	// {
	// SubjectInfor subjectInfor =
	// subjectInforService.getSubjectInforBySubjectInforID(subjectInforID);
	//
	// ModelAndView mav = new
	// ModelAndView(SubjectInforWebConstant.getSubjectInforDetailView());
	// mav.addObject("subjectInfor", subjectInfor);
	//
	// return mav;
	// }
	//
	// @RequestMapping(value = "/showSubjectInforAdd")
	// public ModelAndView showSubjectInforAdd(String subjectInforID)
	// {
	// System.out.println("subjectInforID:"+subjectInforID);
	//
	// ModelAndView mav = new
	// ModelAndView(SubjectInforWebConstant.getSubjectInforAddEditView());
	//
	// List<TeacherInfo> teacherInfos = teacherInfoService.getTeacherInfos();
	// List<CourseChapter> courseChapters =
	// courseChapterService.getCourseChapters();
	//
	// mav.addObject("teacherInfos", teacherInfos);
	// mav.addObject("courseChapters", courseChapters);
	//
	// if(subjectInforID != null && !subjectInforID.equals(""))
	// {
	// SubjectInfor subjectInfor =
	// subjectInforService.getSubjectInforBySubjectInforID(subjectInforID);
	// mav.addObject("subjectInfor", subjectInfor);
	// }
	// return mav;
	// }
	//
	// @RequestMapping(value = "/subjectInforAdd")
	// public @ResponseBody String subjectInforAdd(String courseChapterID,String
	// teacherInfoID,SubjectInfor subjectInfor)
	// {
	// System.out.println("courseChapterID:"+courseChapterID);
	// System.out.println("getSiId:"+subjectInfor.getSiId());
	//
	// int sign =
	// subjectInforService.saveOrUpdateSubjectInfor(subjectInfor,courseChapterID,teacherInfoID);
	//
	// if(sign == 1)
	// return successResponse("保存成功","../subjectInfor/subjectInfors" ,
	// "subjectInfors");
	//
	// return failResponse("保存失败");
	// }
	//
	// @RequestMapping(value = "/subjectInforDelete")
	// public @ResponseBody String subjectInforDelete(String ojbIdStr)
	// {
	// int sign = subjectInforService.deleteSubjectInfors(ojbIdStr);
	//
	// if(sign == 1)
	// return successResponse("删除成功","../subjectInfor/subjectInfors" ,
	// "subjectInfors");
	//
	// return failResponse("删除失败");
	// }
	//
	// /**
	// * ===============================该类主要逻辑=================================
	// */
	//
	// @RequestMapping(value = "/subjectInforsForLookUp")
	// public ModelAndView subjectInforsForLookUp()
	// {
	// ModelAndView mav = new
	// ModelAndView(SubjectInforWebConstant.getSubjectInforListForLuView());
	//
	// try
	// {
	//
	// Page<SubjectInfor> page = new Page<>(10);//lookup界面较小,分页数据10条
	// int[] pageParams = PageUtil.init(page,request);//分页初始化
	// subjectInforService.getSubjectInforsPages(page, pageParams);
	//
	// mav.addObject(MainWebConstant.getPage(), page);
	//
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	//
	// return mav;
	// }
}
