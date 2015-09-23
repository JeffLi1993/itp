package sedion.jeffli.wmuitp.constant;

public class TeacherInfoConstantWeb {
	public static final String TEACHER_INFO_BASE					= "/teacherInfo";
	public static final String WEB_BASE					    		= TEACHER_INFO_BASE + "/web";
	public static final String WEB_APP_BASE							= TEACHER_INFO_BASE + "/webApp";
	
	/**
	 * views about teacher
	 */
	public static final String TEACHER_INFO_LIST_VIEW	 		= WEB_BASE + "/teacher.list";
	public static final String TEACHER_INFO_ADD_EDIT_VIEW		= WEB_BASE + "/teacher.addEdit";
	public static final String TEACHER_INFO_DETAIL				= WEB_BASE + "/teacher.detail";
	public static final String TEACHER_LOOK_UP_VIEW				= WEB_BASE + "/teacher.lookup";
	public static final String TEACHER_INFO_WEB_DETAIL			= WEB_APP_BASE + "/teacher.detail";
	public static final String TEACHER_INFO_XLS_WEB				= WEB_BASE + "/teacher.xls";
	
	
	public static String getTeacherLookUpView() {
		return TEACHER_LOOK_UP_VIEW;
	}
	public static String getTeacherInfoListView()
	{
		return TEACHER_INFO_LIST_VIEW;
	}
	public static String getTeacherInfoAddEditView()
	{
		return TEACHER_INFO_ADD_EDIT_VIEW;
	}
	public static String getTeacherInfoWebDetail()
	{
		return TEACHER_INFO_WEB_DETAIL;
	}
	public static String getTeacherInfoDetail()
	{
		return TEACHER_INFO_DETAIL;
	}
	public static String getTeacherInfoXlsWeb() {
		return TEACHER_INFO_XLS_WEB;
	}
	
}
