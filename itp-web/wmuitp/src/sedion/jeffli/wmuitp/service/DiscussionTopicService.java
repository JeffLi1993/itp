package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.*;
import sedion.jeffli.wmuitp.util.Page;

public interface DiscussionTopicService {


	/**
	 * 转换议题是否开启
	 * @param DiscussionTopicId 议题id
	 */
	int turnDiscussionTopicSign(String DiscussionTopicId);
	
	List<DiscussionTopic> getDiscussionTopicsPagesSearch(Page<DiscussionTopic> page, int[] pageParams,String discussionTopicName,String courseInfoName);
	
	/**
	 * 获得全部议题
	 * @param page 分页
	 * @param pageParams  分页参数
	 */
	List<DiscussionTopic> getDiscussionTopicsPages(Page<DiscussionTopic> page, int[] pageParams);

	/**
	 * 获得全部议题 老师端
	 * @param page 分页
	 * @param pageParams  分页参数
	 */
	List<DiscussionTopic> getDiscussionTopicsPagesSearchTea(Page<DiscussionTopic> page, int[] pageParams,String discussionTopicName,String courseInfoName);

	/**
	 * 增加议题
	 * @param discussionTopic  议题
	 * @param courseInfoId    课程详情id
	 */
	int addDiscussionTopics(DiscussionTopic discussionTopic,String courseInfoId);
	
	
	
	/**
	 * 根据议题id获得议题
	 * @param discussionTopicId  议题id
	 */
	DiscussionTopic getDiscussionTopicServiceById(String discussionTopicId);
	/**
	 * 根据议题id获得议题(浏览+1)
	 * @param discussionTopicId  议题id
	 */
	DiscussionTopic addGetDiscussionTopicServiceById(String discussionTopicId);
	/**
	 * 根据议题id获得议题(浏览+1,人数+1)
	 * @param discussionTopicId  议题id
	 */
	DiscussionTopic addGetDiscussionTopicServiceByIdStu(String discussionTopicId);
	
	/**
	 * 删除议题
	 * @param discussionTopicIdStr 议题id字符串
	 */
	int deleteDiscussionTopic(String discussionTopicIdStr);
	
	/**
	 * 获得议题 通过对应的课程id
	 * @param courseInfoId 课程id
	 */
	List<DiscussionTopic> getDiscussionTopicByCIId(String courseInfoId);
	/**
	 * 获得议题 通过对应的课id
	 * @param courseInfoId 课程id
	 */
	List<DiscussionTopic> getDiscussionTopicByCId(String courseId);
	/**
	 * 添加学生回复
	 * @param discussionTopicId 议题id
	 * @param discussionTopicText 学生回复内容
	 */
	int addDiscussionTopicAppStu(String discussionTopicId,String discussionTopicText);
	
	/**
	 * 添加老师回复
	 * @param discussionTopicId 议题id
	 * @param discussionTopicText 老师回复内容
	 * @return
	 */
	int addDiscussionTopicAppTea(String discussionTopicId,String discussionTopicText);
	
	/**
	 * 获得学生置顶的议题
	 * @param discussionTopicId 议题id
	 */
	List<DiscussionStudentReply> findDiscussionStudentReplyByDiscussionTopicIdTopFine(String discussionTopicId);
	
	/**
	 * 学生获得自己对该议题的全部回复
	 * @param discussionTopicId 议题id
	 */
	List<DiscussionStudentReply> findDiscussionStudentReplyBySessionSelfDiscussionTopicId(String discussionTopicId);

	/**
	 * 获得全部学生对该议题的的回复
	 * @param discussionTopicId
	 */
	List<DiscussionStudentReply> findAllDiscussionStudentReplyByDiscussion(String discussionTopicId);
	
	/**
	 * 获得全部学生对该议题的的回复(按置顶排序)
	 * @param discussionTopicId
	 */
	List<DiscussionStudentReply> findAllDiscussionStudentReplyByDiscussionOrderByTop(String discussionTopicId);
	
	/**
	 * 获得老师对
	 * @param discussionTopicId
	 */
	List<DiscussionTeacherReply> findDiscussionTeacherReplyByDiscussionTopicId(String discussionTopicId);
	DiscussionStudentReply findDiscussionStudentReplyByDiscussionStudentReplyId(String DiscussionStudentReplyId);
	int turnDiscussionStudentReplyTop(String discussionStudentReplyId);
	int getTopDivReload (String discussionStudentReplyId);
	/**
	 * 获得议题 通过老师Session
	 */
	List<DiscussionTopic> getDiscussionTopicByTeaSession();
}
