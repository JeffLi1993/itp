package sedion.jeffli.wmuitp.constant;

public class CourseInfoConstantWeb 
{
	public static final String COURSE_INFO_BASE 			= "/courseInfo";
	public static final String WEB_BASE					    = COURSE_INFO_BASE + "/web";
	public static final String WEB_APP_BASE					= COURSE_INFO_BASE + "/webApp";
	
	/**
	 * view about course-info
	 */
	public static final String COURSE_INFO_LIST_VIEW 		= WEB_BASE + "/course.info.list";
	public static final String COURSE_INFO_LIST_VIEW_TEA 		= WEB_BASE + "/course.info.list.tea";
	public static final String COURSE_INFO_ADD_VIEW 		= WEB_BASE + "/course.info.add";
	public static final String COURSE_INFO_LOOKUP_VIEW 		= WEB_BASE + "/course.info.lookup";
	public static final String COURSE_INFO_LOOKUP_TEA_VIEW 		= WEB_BASE + "/course.info.lookup.tea";
	public static final String COURSE_INFO_ADD_VIEW_TEA 		= WEB_BASE + "/course.info.add.tea";
	public static final String COURSE_INFO_LIST_MORE_VIEW 	= WEB_BASE + "/course.info.list.more";
	
	public static String getCourseInfoLookupTeaView() {
		return COURSE_INFO_LOOKUP_TEA_VIEW;
	}
	public static String getCourseInfoListView()
	{
		return COURSE_INFO_LIST_VIEW;
	}
	public static String getCourseInfoLookupView() {
		return COURSE_INFO_LOOKUP_VIEW;
	}
	public static String getCourseInfoListViewTea() 
	{
		return COURSE_INFO_LIST_VIEW_TEA;
	}
	public static String getCourseInfoAddView()
	{
		return COURSE_INFO_ADD_VIEW;
	}
	
	public static String getCourseInfoListMoreView()
	{
		return COURSE_INFO_LIST_MORE_VIEW;
	}
	public static String getCourseInfoAddViewTea() 
	{
		return COURSE_INFO_ADD_VIEW_TEA;
	}
	
	/**
	 * app view about course-info
	 */
	public static final String COURSE_INFO_LIST_APP_VIEW   		  = WEB_APP_BASE + "/course.info.list";
	public static final String COURSE_INFO_LIST_APP_AJAX_VIEW     = WEB_APP_BASE + "/course.info.list.ajax";
	public static final String COURSE_INFO_TEA_LIST_APP_AJAX_VIEW = WEB_APP_BASE + "/course.info.tea.list.ajax";
	public static final String COURSE_INFO_INDEX_VIEW     		  = WEB_APP_BASE + "/course.info.index";
	public static final String COURSE_INFO_LIST_TEA_APP_VIEW   	  = WEB_APP_BASE + "/course.info.tea.list";
	public static final String COURSE_INFO_FUCTIONS_APP_VIEW   	  = WEB_APP_BASE + "/course.info.functions";
	public static final String COURSE_INFO_FUCTIONS_TEA_APP_VIEW  = WEB_APP_BASE + "/course.info.tea.functions";
	public static final String COURSE_INFO_FUCTIONS_TAJAX_APP_VIEW= WEB_APP_BASE + "/course.info.tea.ajax.functions";
	public static final String COURSE_AND_CLASS_INFO_APP_VIEW     = WEB_APP_BASE + "/couresAndClassInfo.Detail";
	public static final String COURSE_ABSENT_DETAIL_APP_VIEW      = WEB_APP_BASE + "/course.absent.detail";
	public static final String TIPS    							  = WEB_APP_BASE + "/tips";

	public static String getCourseAbsentDetailAppView() {
		return COURSE_ABSENT_DETAIL_APP_VIEW;
	}

	public static String getCourseInfoListAppView() 
	{
		return COURSE_INFO_LIST_APP_VIEW;
	}
	
	public static String getCourseInfoListAppAjaxView() 
	{
		return COURSE_INFO_LIST_APP_AJAX_VIEW;
	}

	public static String getCourseInfoTeaListAppAjaxView() {
		return COURSE_INFO_TEA_LIST_APP_AJAX_VIEW;
	}

	public static String getCourseInfoIndexView() {
		return COURSE_INFO_INDEX_VIEW;
	}

	public static String getCourseInfoFuctionsAppView()
	{
		return COURSE_INFO_FUCTIONS_APP_VIEW;
	}

	public static String getCourseInfoFuctionsTajaxAppView() {
		return COURSE_INFO_FUCTIONS_TAJAX_APP_VIEW;
	}

	public static String getCourseInfoListTeaAppView()
	{
		return COURSE_INFO_LIST_TEA_APP_VIEW;
	}

	public static String getCourseInfoFuctionsTeaAppView()
	{
		return COURSE_INFO_FUCTIONS_TEA_APP_VIEW;
	}

	public static String getCourseAndClassInfoAppView()
	{
		return COURSE_AND_CLASS_INFO_APP_VIEW;
	}

	public static String getTips()
	{
		return TIPS;
	}
	
}
