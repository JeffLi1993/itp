package sedion.jeffli.wmuitp.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.MessageReceiveDAO;
import sedion.jeffli.wmuitp.dao.MessageSenderDAO;
import sedion.jeffli.wmuitp.dao.UserLoginDAO;
import sedion.jeffli.wmuitp.entity.MessageReceive;
import sedion.jeffli.wmuitp.entity.MessageSender;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.MessageSenderService;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;


//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class MessageSenderServiceImpl implements MessageSenderService {
	
	private static final String ALL_MESSAGE_SENDER			= " FROM MessageSender AS ms ";
	
	@Autowired 
	public HttpSession session; 
	@Autowired
	private MessageSenderDAO			     messageSenderDAO;
	@Autowired
	private MessageReceiveDAO			     messageReceiveDAO;
	@Autowired
	private UserLoginDAO			         userLoginDAO;

	
	@Override
	public MessageSender findMessageSenderById(String messageSenderId)
	{
		String hql = ALL_MESSAGE_SENDER 
				+ CommonConstant.ONE_EQUALS_ONE
				+ " AND ms.msDelete =? "
				+ " AND ms.msId=?";
		
		return messageSenderDAO.getUniqueResultByHQL(hql,CommonConstant.FALSE,Integer.valueOf(messageSenderId));
	}
	
	@Override
	public int saveOrUpdateMessageSender(MessageSender messageSender,String ulIdStr)
	{
		//获取发件人(自己)
		UserLogin userLoginSender = AdminUtil.getUserLoginFromHttpSession(session);
		
		messageSender.setUserLogin(userLoginSender);
//		System.out.println(new Date(new GregorianCalendar().getTimeInMillis()).toString());
		messageSender.setMsSendTime(new Date(new GregorianCalendar().getTimeInMillis()).toString());
		messageSender.setMsDelete(CommonConstant.FALSE);
		
		messageSenderDAO.updateEntity(messageSender);
		
		String[] ulIds=ulIdStr.split(",");
		
		for(String ulId :ulIds){
			//获取收件人
			UserLogin userLoginReceiver=userLoginDAO.findById(Integer.valueOf(ulId));
					
			MessageReceive messageReceive=new MessageReceive();
			messageReceive.setUserLogin(userLoginReceiver);
			messageReceive.setMrDelete(CommonConstant.FALSE);
			messageReceive.setMrRead(CommonConstant.FALSE);
			messageReceive.setMessageSender(messageSender);
			
			messageReceiveDAO.updateEntity(messageReceive);
		}
		return Constant.RESULT_SUCCESS;
	}
	
	@Override
	public List<MessageSender> getMessageSenderPages(Page<MessageSender> page,int[] pageParams)
	{
		List<MessageSender> results = new ArrayList<>();

		String hql = ALL_MESSAGE_SENDER 
				+ CommonConstant.ONE_EQUALS_ONE
				+ " AND ms.userLogin.ulId='"+AdminUtil.getUserLoginIDFromHttpSession(session)+"'";
		
//		System.out.println("用户ID="+AdminUtil.getUserLoginIDFromHttpSession(session));
		try
		{
			results = messageSenderDAO.findByPage(hql, pageParams[0], pageParams[1]);
			
			page.setTotalCount(messageSenderDAO.getCount(hql));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException("Error! getMessageSenderPages. ",e);
		}
		
		

		return results;
	}
}
