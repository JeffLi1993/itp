package sedion.jeffli.wmuitp.web.discussion;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.DiscussionTopicWebConstant;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.DiscussionStudentReply;
import sedion.jeffli.wmuitp.entity.DiscussionTeacherReply;
import sedion.jeffli.wmuitp.entity.DiscussionTopic;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;

@RequestMapping(value = "/discussionTopic")
@Controller
public class DiscussionTopicController extends MainBase 
{
	@RequestMapping(value = "/discussionTopics")
	public ModelAndView discussionTopics(String discussionTopicName,String courseInfoName)
	{	
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicListView());
		
		Page<DiscussionTopic> page = new Page<>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page,request);//分页初始化
		discussionTopicService.getDiscussionTopicsPagesSearch(page, pageParams, discussionTopicName, courseInfoName);
		mav.addObject("discussionTopicName", discussionTopicName);
		mav.addObject("courseInfoName", courseInfoName);
		mav.addObject(ConstantWeb.getPage(), page);
		
		return mav;
	}
	
	@RequestMapping(value = "/discussionTopicsTea")
	public ModelAndView discussionTopicsTea(String discussionTopicName,String courseInfoName)
	{	
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicListTeaView());
		
		Page<DiscussionTopic> page = new Page<>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page,request);//分页初始化
		discussionTopicService.getDiscussionTopicsPagesSearchTea(page, pageParams, discussionTopicName, courseInfoName);
		mav.addObject("discussionTopicName", discussionTopicName);
		mav.addObject("courseInfoName", courseInfoName);
		mav.addObject(ConstantWeb.getPage(), page);
		
		return mav;
	}

	@RequestMapping(value = "/showDiscussionTopicAdd")//显示议题添加页面
	public ModelAndView showDiscussionTopicAdd(String discussionTopicId)
	{	
		System.out.println("discussionTopic:"+discussionTopicId);
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicAddView());
		
		if(discussionTopicId != null && !discussionTopicId.equals(""))
		{
			DiscussionTopic discussionTopic  =  discussionTopicService.getDiscussionTopicServiceById(discussionTopicId);
			mav.addObject("discussionTopic",discussionTopic);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/showDiscussionTopicAddTea")//显示议题添加页面
	public ModelAndView showDiscussionTopicAddTea(String discussionTopicId)
	{	
		System.out.println("discussionTopic:"+discussionTopicId);
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicAddTeaView());
		
		if(discussionTopicId != null && !discussionTopicId.equals(""))
		{
			DiscussionTopic discussionTopic  =  discussionTopicService.getDiscussionTopicServiceById(discussionTopicId);
			mav.addObject("discussionTopic",discussionTopic);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/discussionTopicAdd")//添加议题
	public @ResponseBody String discussionTopicAdd(DiscussionTopic discussionTopic,String courseInfoId)
	{
		int	sign =	discussionTopicService.addDiscussionTopics(discussionTopic, courseInfoId);
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),"../discussionTopic/discussionTopics","discussionTopics");
		
		return failResponse(EntityConstant.getSaveEntityFailure());
		
	}
	@RequestMapping(value = "/discussionTopicAddTea")//添加议题
	public @ResponseBody String discussionTopicAddTea(DiscussionTopic discussionTopic,String courseInfoId)
	{
		int	sign =	discussionTopicService.addDiscussionTopics(discussionTopic, courseInfoId);
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),"../discussionTopic/discussionTopicsTea","discussionTopics");
		
		return failResponse(EntityConstant.getSaveEntityFailure());
		
	}
	
	@RequestMapping(value = "/discussionTopicDelete")//删除议题
	public @ResponseBody String discussionTopicDelete(String ojbIdStr)
	{
		int sign = discussionTopicService.deleteDiscussionTopic(ojbIdStr);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),"../discussionTopic/discussionTopics","discussionTopics");
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	}
	
	@RequestMapping(value = "/discussionTopicDeleteTea")//删除议题
	public @ResponseBody String discussionTopicDeleteTea(String ojbIdStr)
	{
		int sign = discussionTopicService.deleteDiscussionTopic(ojbIdStr);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),"../discussionTopic/discussionTopicsTea","discussionTopics");
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
	}

	@RequestMapping(value = "/discussionTopicDetail")//显示议题详情
	public ModelAndView discussionTopicDetail(String discussionTopicId)
	{	
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicDetailView());
		DiscussionTopic discussionTopic  =  discussionTopicService.getDiscussionTopicServiceById(discussionTopicId);
		mav.addObject("discussionTopic", discussionTopic);
		return mav;
	}
	/********************手机端***********************/
	
	@RequestMapping(value = "/getDiscussionTopicByCIIdApp")//显示全部议题
	public ModelAndView getDiscussionTopicByCIIdApp(String courseInfoId,RedirectAttributes attr)
	{	
		System.out.println("courseInfoId="+courseInfoId);
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicStuListView());
		List<DiscussionTopic> discussionTopics  =  discussionTopicService.getDiscussionTopicByCIId(courseInfoId);
		if (discussionTopics!=null)
		{
			mav.addObject("discussionTopics", discussionTopics);
			mav.addObject("courseInfoId", courseInfoId);
			return mav;
		}
		else 
		{
			attr.addAttribute("kindName", "温馨提示");
			attr.addAttribute("desription", "还没有任何人发过帖子哦。");
			return new ModelAndView("redirect:/tips/tip?");
		}
	}
	
	@RequestMapping(value = "/getDiscussionTopicByCIdApp")//显示全部议题
	public ModelAndView getDiscussionTopicByCIdApp(String courseId,RedirectAttributes attr)
	{	
		System.out.println("courseId="+courseId);
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.DISCUSSION_TOPIC_BEFORE_STU_VIEW);
		List<DiscussionTopic> discussionTopics  =  discussionTopicService.getDiscussionTopicByCId(courseId);
		mav.addObject("discussionTopics", discussionTopics);
		mav.addObject("courseId", courseId);
		return mav;
	}
	
	@RequestMapping(value = "/getDiscussionTopicByCIIdAppTea")//显示全部议题 老师 TODO
	public ModelAndView getDiscussionTopicByCIIdAppTea()
	{	
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicTeaListView());
		List<DiscussionTopic> discussionTopics  =  discussionTopicService.getDiscussionTopicByTeaSession();
		mav.addObject("discussionTopics", discussionTopics);
		return mav;
	}
	
	@RequestMapping(value = "/showDiscussionTopicApp")//显示议题详情学生
	public ModelAndView showDiscussionTopicApp(String discussionTopicId)
	{	
		System.out.println("discussionTopicId="+discussionTopicId);
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicStuView());
		DiscussionTopic discussionTopic  =  discussionTopicService.addGetDiscussionTopicServiceByIdStu(discussionTopicId);
		mav.addObject("discussionTopic", discussionTopic);
		
		//置顶的回复
		List<DiscussionStudentReply> discussionStudentReplys = discussionTopicService.findDiscussionStudentReplyByDiscussionTopicIdTopFine(discussionTopicId);
		mav.addObject("discussionStudentReplys", discussionStudentReplys);
		//自己的回复
		List<DiscussionStudentReply> discussionStudentReplySelfs = discussionTopicService.findDiscussionStudentReplyBySessionSelfDiscussionTopicId(discussionTopicId);
		mav.addObject("discussionStudentReplySelfs", discussionStudentReplySelfs);
		//老师的回复
		List<DiscussionTeacherReply> discussionTeacherReplys = discussionTopicService.findDiscussionTeacherReplyByDiscussionTopicId(discussionTopicId);
		mav.addObject("discussionTeacherReplys", discussionTeacherReplys);
		return mav;
	}
	
	@RequestMapping(value = "/showDiscussionTopicAppTea")//显示议题详情老师
	public ModelAndView showDiscussionTopicAppTea(String discussionTopicId)
	{	
		System.out.println("discussionTopicId="+discussionTopicId);
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicTeaView());
		DiscussionTopic discussionTopic  =  discussionTopicService.addGetDiscussionTopicServiceById(discussionTopicId);
		mav.addObject("discussionTopic", discussionTopic);
		//全部学生的回复
		List<DiscussionStudentReply> discussionStudentReplys  =  discussionTopicService.findAllDiscussionStudentReplyByDiscussionOrderByTop(discussionTopicId);
		mav.addObject("discussionStudentReplys", discussionStudentReplys);
		//老师自己的回复
		List<DiscussionTeacherReply> discussionTeacherReplys = discussionTopicService.findDiscussionTeacherReplyByDiscussionTopicId(discussionTopicId);
		mav.addObject("discussionTeacherReplys", discussionTeacherReplys);
		return mav;
	}
	
	@RequestMapping(value = "/addDiscussionTopicStu")//学生回复
	public @ResponseBody String addDiscussionTopicStu(String discussionTopicId,String discussionTopicText)
	{
//		System.out.println("discussionTopicId="+discussionTopicId+"discussionTopicText+"+discussionTopicText);
		int sign = discussionTopicService.addDiscussionTopicAppStu(discussionTopicId, discussionTopicText);
//		System.out.println("sign="+sign);
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse("发表成功","../discussionTopic/showDiscussionTopicApp","");
		else if(sign == Constant.RESULT_EXIST)
			return failResponse("发表失败,请勿重复提交");
		
		return failResponse("发表失败");
	}
	
	@RequestMapping(value = "/addDiscussionTopicTea")//老师回复
	public @ResponseBody String addDiscussionTopicTea(String discussionTopicId,String discussionTopicText)
	{
//		System.out.println("discussionTopicId="+discussionTopicId+"discussionTopicText="+discussionTopicText);
		int sign = discussionTopicService.addDiscussionTopicAppTea(discussionTopicId, discussionTopicText);
//		System.out.println("sign="+sign);
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse("发表成功","../discussionTopic/showDiscussionTopicAppTea","");
		else if(sign == Constant.RESULT_EXIST)
			return failResponse("发表失败,请勿重复提交");
		
		return failResponse("发表失败");
	}
	
	@RequestMapping(value = "/trunStudentReplyTop")//置顶
	public @ResponseBody String trunStudentReplyTop(String discussionStudentReplyId)
	{
		System.out.println("discussionStudentReply="+discussionStudentReplyId);
		int sign = discussionTopicService.turnDiscussionStudentReplyTop(discussionStudentReplyId);
		System.out.println("sign="+sign);
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse("操作成功","../discussionTopic/showDiscussionTopicAppTea","");
		
		return failResponse("操作失败");
	}
	
	@RequestMapping(value = "/showDiscussionTopicStuDivReload")//显示议题详情老师
	public ModelAndView showDiscussionTopicStuDivReload(String discussionStudentReplyId)
	{	
		System.out.println("discussionStudentReplyId="+discussionStudentReplyId);
		ModelAndView mav = new ModelAndView(DiscussionTopicWebConstant.getDiscussionTopicTopDivView());
		DiscussionStudentReply discussionStudentReply = discussionTopicService.findDiscussionStudentReplyByDiscussionStudentReplyId(discussionStudentReplyId);
		mav.addObject("discussionStudentReply", discussionStudentReply);
		return mav;
	}
	
	@RequestMapping(value = "/turDiscussionTopicSign")
	public @ResponseBody String turDiscussionTopicSign(String discussionTopicId)//转换议题开关
	{	
		int sign=discussionTopicService.turnDiscussionTopicSign(discussionTopicId);
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse("操作成功","","");
		return failResponse("操作失败");
	}
}
