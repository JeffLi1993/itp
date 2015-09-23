package sedion.jeffli.wmuitp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.MessageReceiveDAO;
import sedion.jeffli.wmuitp.entity.MessageReceive;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.MessageReceiveService;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class MessageReceiveServiceImpl implements MessageReceiveService 
{
	private static final String ALL_MESSAGE_RECEIVE			= "FROM MessageReceive AS mr ";
	
	@Autowired 
	public HttpSession		session; 
	@Autowired
	MessageReceiveDAO		messageReceiveDAO; 
	
	@Override
	public int deleteMessageReceive(String messageReceiveIdStr)
	{
		String[] messageReceiveIdStrs;
		
		if(StringUtils.isNotEmpty(messageReceiveIdStr))
		{
			messageReceiveIdStrs = messageReceiveIdStr.split(Constant.LINE);
			for (String messageReceiveId : messageReceiveIdStrs)
			{
				try
				{
					MessageReceive messageReceive=messageReceiveDAO.findById(Integer.valueOf(messageReceiveId));
					messageReceive.setMrDelete(CommonConstant.TRUE);
					messageReceiveDAO.updateEntity(messageReceive);
				} 
				catch (Exception e)
				{
					throw new EntityException("deleteMessageReceive Error !", e);
				}
				
			}
			
			return Constant.RESULT_SUCCESS;
		}	
		return Constant.RESULT_FAIL;
	}
	
	@Override
	public MessageReceive readMessageReceiveById(String messageReceiveId)
	{
		StringBuilder hql = new StringBuilder(ALL_MESSAGE_RECEIVE+CommonConstant.ONE_EQUALS_ONE); 
		hql.append(" AND mr.mrId=?");
		
		MessageReceive messageReceive = null;
		try
		{
		    messageReceive = (MessageReceive) messageReceiveDAO.getUniqueResultByHQL(hql.toString(),
		    		Integer.valueOf(messageReceiveId));
			
			messageReceive.setMrRead(CommonConstant.TRUE);//将邮件设置已读
			messageReceiveDAO.updateEntity(messageReceive);
			
		} catch (Exception e)
		{
			throw new EntityException("readMessageReceiveById Error!", e);
		}
		
		return messageReceive;
				
	}
	
	@Override
	public List<MessageReceive> getMessageReceivePages(Page<MessageReceive> page,int[] pageParams)
	{
		List<MessageReceive> results = new ArrayList<>();

		String hql = ALL_MESSAGE_RECEIVE 
				+ CommonConstant.ONE_EQUALS_ONE
				+ " AND mr.mrDelete ='F' "
				+ " AND mr.userLogin.ulId='"+AdminUtil.getUserLoginIDFromHttpSession(session)+"' "
				+ " order by mr.mrRead asc ";
		
//		System.out.println("用户ID="+AdminUtil.getUserLoginIDFromHttpSession(session));
		
		try
		{
			results = messageReceiveDAO.findByPage(hql, pageParams[0], pageParams[1]);
			
			page.setTotalCount(messageReceiveDAO.getCount(hql));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException("Error! MessageReceiveServiceImpl getMessageReceivePages(). ",e);
		}
		
		return results;
	}
}
