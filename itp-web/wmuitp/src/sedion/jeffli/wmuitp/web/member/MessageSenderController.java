package sedion.jeffli.wmuitp.web.member;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.EntityConstant;
import sedion.jeffli.wmuitp.constant.MessageReceiveConstant;
import sedion.jeffli.wmuitp.constant.MessageSenderConstant;
import sedion.jeffli.wmuitp.constant.MessageSenderConstantWeb;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.constant.main.ConstantWeb;
import sedion.jeffli.wmuitp.entity.MessageSender;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.PageUtil;

@RequestMapping(value = "/messageSender")
@Controller
public class MessageSenderController extends MainBase
{
	
	
	@RequestMapping(value = "/messageSenders")//显示发件
	public ModelAndView messageSenders()
	{	
		ModelAndView mav = new ModelAndView(MessageSenderConstantWeb.getMessageSenderListView());
		
		try 
		{
			
			Page<MessageSender> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			messageSenderService.getMessageSenderPages(page, pageParams);
			mav.addObject(ConstantWeb.getPage(), page);
			
		} 
		catch (Exception e) 
		{
			throw new EntityException(" MessageSenderController messageSenders() Error!!", e);
		}
		
		return mav;
	}


	
	@RequestMapping(value = "/showMessageSenderAdd")
	public ModelAndView showMessageSenderAdd()//添加发件
	{	
		
		ModelAndView mav = new ModelAndView(MessageSenderConstantWeb.getMessageSenderAddView());
		
		return mav;
	}
	
	
	@RequestMapping(value = "/messageSenderAdd")//发送发件
	public @ResponseBody String messageSenderAdd(MessageSender messageSender,String senderIds) 
	{
		System.out.println(senderIds);
		int sign = messageSenderService.saveOrUpdateMessageSender(messageSender,senderIds);
		
		if(sign == Constant.RESULT_SUCCESS)
			return successResponse(EntityConstant.getSaveEntitySuccess(),
								MessageReceiveConstant.getMessageReceiveUrl() ,
								MessageReceiveConstant.getMessageReceiveRef());
		
		return failResponse(EntityConstant.getSendEntityFailure());
		
	}
	
	@RequestMapping(value = "/showMessageSenderDetail")
	public ModelAndView showMessageSenderDetail(String messageSenderId)//显示发件细节
	{	
		System.out.println("messageSenderId:"+messageSenderId);
		
		ModelAndView mav = new ModelAndView(MessageSenderConstantWeb.getMessageSenderDetail());

		MessageSender messageSender  =  messageSenderService.findMessageSenderById(messageSenderId);
		
		mav.addObject(MessageSenderConstant.getMessageSenderObject(), messageSender);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/showMessageSenderLookup")
	public ModelAndView showMessageSenderLookup(String UserName,String UserId,String UserType)//收件人查找带回
	{	
		ModelAndView mav = new ModelAndView(MessageSenderConstantWeb.getMessageSenderLookup());

		try 
		{
			
			Page<UserLogin> page = new Page<>(PageUtil.PAGE_SIZE);
			int[] pageParams = PageUtil.init(page,request);//分页初始化
			userLoginService.getUserLoginPages(page, pageParams,UserName,UserId,UserType);
			mav.addObject(ConstantWeb.getPage(), page);
			
		} 
		catch (Exception e) 
		{
			throw new EntityException(" MessageSenderController  showMessageSenderLookup() Error!!", e);
		}
		
		return mav;
		
	}
	
	
}
