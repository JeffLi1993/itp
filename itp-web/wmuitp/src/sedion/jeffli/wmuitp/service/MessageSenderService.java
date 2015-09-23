package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.MessageSender;
import sedion.jeffli.wmuitp.util.Page;

public interface MessageSenderService {

	/**
	 * 获取站内信发件
	 * @param page			分页类
	 * @param pageParams	分页参数
	 */
	public List<MessageSender> getMessageSenderPages(Page<MessageSender> page,int[] pageParams);
	
	/**
	 * 保存站内信发件
	 * @param messageSender  发件实体
	 * @param ulName  收件人Id
	 */
	int saveOrUpdateMessageSender(MessageSender messageSender,String ulIdStr);
	
	/**
	 * 通过id查找发件
	 * @param messageSenderId 发件id
	 */
	MessageSender findMessageSenderById(String messageSenderId);

}
