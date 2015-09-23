package sedion.jeffli.wmuitp.web.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.CourseInfoConstant;
import sedion.jeffli.wmuitp.constant.CourseInfoConstantWeb;
import sedion.jeffli.wmuitp.constant.CourseTeacherRelationConstant;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.courseInfo.CourseInfoDetail;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;
import sedion.jeffli.wmuitp.util.session.AdminUtil;
import sedion.jeffli.wmuitp.constant.database.ClassInfoConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
/**
 * 关于课程详情的一系列操作
 * @author Li
 */
@RequestMapping(value = "/courseInfo")
@Controller
public class CourseInfoController extends MainBase 
{
	
	@RequestMapping(value = "/courseInfos")
	public ModelAndView courseInfos() //获取教师要上的课详细
	{	
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoListView());
		
		Page<CourseInfoDetail> page = new Page<>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page,request);//分页初始化
		courseInfoService.getCourseInfosPagesCourseInfoDetail(page, pageParams);

		mav.addObject(ConstantWeb.getPage(), page);
		
		return mav;
	}
	@RequestMapping(value = "/courseInfoLookUpByTeaSession")
	public ModelAndView courseInfoLookUpByTeaSession() //查找带回
	{	
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoLookupTeaView());
		
		Page<CourseInfoDetail> page = new Page<>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page,request);//分页初始化
		courseInfoService.getCourseInfosPagesCourseInfoDetailByTeaSession(page, pageParams);
		
		mav.addObject(ConstantWeb.getPage(), page);
		
		return mav;
	}
	
	@RequestMapping(value = "/courseInfosTea")
	public ModelAndView courseInfosTea() //获取教师要上的课详细
	{	
		UserLogin userLogin=AdminUtil.getUserLoginFromHttpSession(session);
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoListViewTea());
		
		Page<CourseInfoDetail> page = new Page<>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page,request);//分页初始化
		courseInfoService.getCourseInfosPagesTea(page, pageParams,userLogin);
		
		mav.addObject(ConstantWeb.getPage(), page);
		
		return mav;
	}
	
	@RequestMapping(value = "/showCourseInfoAdd")
	public ModelAndView showCourseInfoAdd(String CIId) //显示课程详情添加页面
	{	
		List<CourseTeacherRelation> courseTeacherRelations = courseTeacherRelationService.getCourseTeacherRelations();
		
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoAddView());
		
		mav.addObject(CourseTeacherRelationConstant.getCourseTeacherRealtionList(),
				courseTeacherRelations);
		
		if(StringUtils.isNotEmpty(CIId))
		{
			CourseInfo courseInfo = courseInfoService.getCourseInfoById(CIId);
			mav.addObject(CourseInfoConstant.getCourseInfoObject(), courseInfo);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/showCourseInfoAddTea")
	public ModelAndView showCourseInfoAddTea(String CIId) //显示课程详情添加页面
	{	
		UserLogin userLogin=AdminUtil.getUserLoginFromHttpSession(session);
		List<CourseTeacherRelation> courseTeacherRelations = courseTeacherRelationService.getCourseTeacherRelationsByTea(userLogin);
		
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoAddViewTea());
		
		mav.addObject(CourseTeacherRelationConstant.getCourseTeacherRealtionList(),
				courseTeacherRelations);
		
		if(StringUtils.isNotEmpty(CIId))
		{
			CourseInfo courseInfo = courseInfoService.getCourseInfoById(CIId);
			mav.addObject(CourseInfoConstant.getCourseInfoObject(), courseInfo);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/courseInfoAddTea")
	public @ResponseBody String courseInfoAddTea(String 		courseTeacherRelationID,
											  String 		classInfoIds,
											  CourseInfo 	courseInfo) //课程详情添加
	{
		int sign = courseInfoService.saveCourseInfo(courseInfo,courseTeacherRelationID,classInfoIds);
		
		if( sign == Constant.RESULT_SUCCESS )
			return successResponse(EntityConstant.getSaveEntitySuccess(),
					"../courseInfo/courseInfosTea",
					CourseInfoConstant.getCourseInfoRef());
	
		return failResponse(EntityConstant.getSaveEntityFailure());
	}
	
	@RequestMapping(value = "/courseInfoAdd")
	public @ResponseBody String courseInfoAdd(String 		courseTeacherRelationID,
											  String 		classInfoIds,
											  CourseInfo 	courseInfo) //课程详情添加
	{
		System.out.println("getCiPeriod:"+courseInfo.getCiPeriod());
		int sign = courseInfoService.saveCourseInfo(courseInfo,courseTeacherRelationID,classInfoIds);
		
		if( sign == Constant.RESULT_SUCCESS )
			return successResponse(EntityConstant.getSaveEntitySuccess(),
					CourseInfoConstant.getCourseInfoUrl(),
					CourseInfoConstant.getCourseInfoRef());
	
		return failResponse(EntityConstant.getSaveEntityFailure());
	}
	
	@RequestMapping(value = "/courseInfoDelete")
	public @ResponseBody String courseInfoDelete(String ojbIdStr) 
	{
		int sign = courseInfoService.deleteCourseInfos(ojbIdStr);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),
					CourseInfoConstant.getCourseInfoUrl(),
					CourseInfoConstant.getCourseInfoRef());
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	
	}
	
	@RequestMapping(value = "/courseInfoLookUp")
	public ModelAndView courseInfoLookUp() //查找带回
	{	
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoLookupView());
		
		Page<CourseInfo> page = new Page<>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page,request);//分页初始化
		courseInfoService.getCourseInfosPages(page, pageParams);
		
		mav.addObject(ConstantWeb.getPage(), page);
		
		return mav;
	}
	/**
	 * =======================================C# webView Controllers====================================
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getCourseInfoQrcodeBySignIdAndIp")
	public @ResponseBody String getCourseInfoQrcodeBySignIdAndIp(String signId,String clientIp)//根据ip,获取该房间有木有上课
	{	
		System.out.println("clientIp:" + clientIp);
		System.out.println("signId :" + signId );
		
		CourseInfo courseInfo = courseInfoService.getCourseInfoByCIPlace(ClassInfoConstant.getClassNameFromIP(clientIp));//获取课详情,通过Ip判断班级
		
		if (courseInfo != null)
		{
			return imgResponse(String.valueOf(courseInfo.getCiId()),Constant.getQrCode()+courseInfo.getCiQrcode());
		}
		return imgResponse(signId,"");
	}
	
	/**
	 * 返回Json字符串
	 * @param signId
	 * @param imgUrl
	 * @return
	 */
	protected String imgResponse(String signId, String imgUrl) 
	{
		
		String imgResponse = "{\"signId\":\"" + signId + "\",\"imgUrl\":\"" + imgUrl + "\"}";
		System.out.println("imgResponse:"+imgResponse);
		return imgResponse;
	}
	
	/**
	 * =======================================APP webView Controllers====================================
	 */
	/*@RequestMapping(value = "/getCourseinfosByUserLoginApp")
	public ModelAndView getCourseInfosByUserLoginApp(String ulName,String ulPassword)//根据用户信息,获取相关在线课程
	{	
		System.out.println("ulName:"+ulName);
		System.out.println("ulPassword:"+ulPassword);
		//学生老师客户端登录检验
		
		
		UserLogin stuUserLogin = userLoginService.findUserLoginByULName(ulName);
		ClientUtil.saveStudentLoginToSession(session, stuUserLogin);//存session
		
		
		List<CourseInfo> courseInfos = courseInfoService.getCourseInfosByUserLogin(ulName,ulPassword);
		
		ModelAndView mav = new ModelAndView(CourseInfoWebConstant.getCourseInfoListAppView());
		mav.addObject("courseInfos",courseInfos);
		
		return mav;
	}*/
	
	@RequestMapping(value = "/getCourseInfoByCIIdApp")
	public ModelAndView getCourseInfoByCIIdApp(String courseInfoId,RedirectAttributes attr)//根据课程详情ID,获取在线课程详情
	{	
		CourseInfo courseInfo = courseInfoService.getCourseInfoById(courseInfoId);
//		if (courseInfo.getCiState().equals(CommonConstant.TRUE))
//		{
			ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoFuctionsAppView());
			mav.addObject("courseInfo",courseInfo);
			
			return mav;
//		}
//		else 
//		{
//			attr.addAttribute("kindName", "课堂");
//			attr.addAttribute("desription", "对不起，该课堂没有被开启或结束！");
//			return new ModelAndView("redirect:/tips/tip?");
//		}
	}
	@RequestMapping(value = "/getCourseInfoByCIIIdApp")
	public ModelAndView getCourseInfoByCIIIdApp(String courseInfoId)//根据课程详情ID,获取在线课程详情
	{	
		CourseInfo courseInfo = courseInfoService.getCourseInfoById(courseInfoId);
		ModelAndView mav = new ModelAndView("/courseInfo/webApp/course.info.show");
		mav.addObject("courseInfo",courseInfo);
		return mav;
		
	}
	/*@RequestMapping(value = "/getCourseInfoByCIIdTeaApp")
	public ModelAndView getCourseInfoByCIIdTeaApp(String courseInfoId)//根据课程详情ID,获取在线课程详情 老师
	{	
		System.out.println("courseInfoId="+courseInfoId);
		CourseInfo courseInfo = courseInfoService.getCourseInfoById(courseInfoId);
		
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoFuctionsTeaAppView());
		mav.addObject("courseInfo",courseInfo);
		
		return mav;
	}*/
	
	@RequestMapping(value = "/getCourseInfoByCIIdTeaAppAjax")
	public ModelAndView getCourseInfoByCIIdTeaAppAjax(String courseInfoId)//根据课程详情ID,获取在线课程详情Ajax
	{	
		ModelAndView mav = new ModelAndView(CourseInfoConstantWeb.getCourseInfoFuctionsTajaxAppView());
		UserLogin userLogin = AdminUtil.getUserLoginFromHttpSession(session);
		List<CourseInfo> courseInfos = courseInfoService.getCourseInfosByTeacherLogin(userLogin); // TODO 3
		mav.addObject("courseInfos",courseInfos);
		return mav;
	}
}
