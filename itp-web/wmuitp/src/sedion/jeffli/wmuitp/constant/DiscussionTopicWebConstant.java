package sedion.jeffli.wmuitp.constant;

public class DiscussionTopicWebConstant 
{
	public static final String DISCUSSION_TOPIC_BASE 		= "/discussionTop";
	public static final String WEB_BASE						= DISCUSSION_TOPIC_BASE + "/web";
	public static final String WEB_APP_BASE					= DISCUSSION_TOPIC_BASE + "/webApp";
	
	/**
	 * view about DiscussionTopic
	 */
	public static final String DISCUSSION_TOPIC_LIST_VIEW 	= WEB_BASE + "/discussionTop.list";
	public static final String DISCUSSION_TOPIC_LIST_TEA_VIEW 	= WEB_BASE + "/discussionTop.list.tea";
	public static final String DISCUSSION_TOPIC_ADD_VIEW 	= WEB_BASE + "/discussionTop.add";
	public static final String DISCUSSION_TOPIC_ADD_TEA_VIEW 	= WEB_BASE + "/discussionTop.add.tea";
	public static final String DISCUSSION_TOPIC_DETAIL_VIEW = WEB_BASE + "/discussionTop.detail";
	
	public static String getDiscussionTopicListView()
	{
		return DISCUSSION_TOPIC_LIST_VIEW;
	}
	public static String getDiscussionTopicAddView()
	{
		return DISCUSSION_TOPIC_ADD_VIEW;
	}
	public static String getDiscussionTopicAddTeaView() 
	{
		return DISCUSSION_TOPIC_ADD_TEA_VIEW;
	}
	public static String getDiscussionTopicDetailView() 
	{
		return DISCUSSION_TOPIC_DETAIL_VIEW;
	}
	public static String getDiscussionTopicListTeaView()
	{
		return DISCUSSION_TOPIC_LIST_TEA_VIEW;
	}
	/**
	 * app view about DiscussionTopic
	 */
	public static final String DISCUSSION_TOPIC_TOP_DIV_VIEW = WEB_APP_BASE + "/discussionTop.top.div";
	public static final String DISCUSSION_TOPIC_STU_VIEW = WEB_APP_BASE + "/discussionTop.stu";
	public static final String DISCUSSION_TOPIC_BEFORE_STU_VIEW = WEB_APP_BASE + "/discussionTop.stu.before.list";
	public static final String DISCUSSION_TOPIC_TEA_VIEW = WEB_APP_BASE + "/discussionTop.tea";
	public static final String DISCUSSION_TOPIC_STU_LIST_VIEW = WEB_APP_BASE + "/discussionTop.stu.list";
	public static final String DISCUSSION_TOPIC_TEA_LIST_VIEW = WEB_APP_BASE + "/discussionTop.tea.list";
	
	public static String getDiscussionTopicStuView() 
	{
		return DISCUSSION_TOPIC_STU_VIEW;
	}
	public static String getDiscussionTopicStuListView()
	{
		return DISCUSSION_TOPIC_STU_LIST_VIEW;
	}
	public static String getDiscussionTopicTeaListView() 
	{
		return DISCUSSION_TOPIC_TEA_LIST_VIEW;
	}
	public static String getDiscussionTopicTeaView() {
		return DISCUSSION_TOPIC_TEA_VIEW;
	}
	public static String getDiscussionTopicTopDivView() {
		return DISCUSSION_TOPIC_TOP_DIV_VIEW;
	}
}
