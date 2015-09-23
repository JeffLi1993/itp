package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.MessageReceive;
import sedion.jeffli.wmuitp.util.Page;



public interface MessageReceiveService {

	
	/**
	 * 获取站内信收件
	 * @param page			分页类
	 * @param pageParams	分页参数
	 */
	public List<MessageReceive> getMessageReceivePages(Page<MessageReceive> page,int[] pageParams);
	
	/**
	 * 通过收件id读收件
	 * @param messageReceiveId 收件Id
	 */
	MessageReceive readMessageReceiveById(String messageReceiveId);
	
	/**
	 * 删除收件(将收件的是否删除改为是)
	 * @param messageReceiveIdStr 收件ids
	 */
	int deleteMessageReceive(String messageReceiveIdStr);
}
