package sedion.jeffli.wmuitp.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.MessageReceiveConstant;
import sedion.jeffli.wmuitp.constant.MessageReceiveConstantWeb;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.MessageReceive;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;

@RequestMapping(value = "/messageReceive")
@Controller
public class MessageReceiveController extends MainBase
{
	
	@RequestMapping(value = "/messageReceives")//显示收件
	public ModelAndView messageReceives()
	{	
		ModelAndView mav = new ModelAndView(MessageReceiveConstantWeb.getMessageReceiveListView());
		
		try {
			
			Page<MessageReceive> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			messageReceiveService.getMessageReceivePages(page, pageParams);
			mav.addObject(ConstantWeb.getPage(), page);
			
		} catch (Exception e) {
			throw new EntityException("MessageReceiveController messageReceives() erroe");
		}
		
		return mav;
	}


	
	@RequestMapping(value = "/showMessageReceiveDetail")//显示收件细节
	public ModelAndView showMessageReceiveDetail(String messageReceiveId)
	{	
		System.out.println("messageReceiveId:"+messageReceiveId);
		
		ModelAndView mav = new ModelAndView(MessageReceiveConstantWeb.getMessageReceiveDetail());
		
		MessageReceive messageReceive  =  messageReceiveService.readMessageReceiveById(messageReceiveId);
		
		mav.addObject(MessageReceiveConstant.getMessageReceiveObject(), messageReceive);
		
		return mav;
		
	}

	@RequestMapping(value = "/messageReceiveDelete")//删除收件
	public @ResponseBody String messageReceiveDelete(String ojbIdStr)
	{	
		System.out.println("ojbIdStr:"+ojbIdStr);
		int sign=messageReceiveService.deleteMessageReceive(ojbIdStr);
		
		if(sign==Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getDeleteEntitySuccess(),
								MessageReceiveConstant.getMessageReceiveUrl() , 
								MessageReceiveConstant.getMessageReceiveRef());
		
		return failResponse(EntityConstant.getDeleteEntityFailure());
		
	}
	
	
}
