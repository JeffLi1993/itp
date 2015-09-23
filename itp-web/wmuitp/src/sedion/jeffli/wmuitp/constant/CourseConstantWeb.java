package sedion.jeffli.wmuitp.constant;

public class CourseConstantWeb 
{
	public static final String COURSE_BASE 			        = "/course";
	public static final String WEB_BASE					    = COURSE_BASE + "/web";
	public static final String WEB_APP_BASE					= COURSE_BASE + "/webApp";
	
	/**
	 * view about course
	 */
	public static final String COURSE_LIST_VIEW 			= WEB_BASE + "/course.list";
	public static final String COURSE_LIST_ADD_VIEW			= WEB_BASE + "/course.list.add";

	public static final String COURSE_LIST_STU_VIEW			= WEB_APP_BASE + "/course.list.stu";
	
	/**
	 * view about course-info
	 */
	public static final String TEACHER_COURSE_LIST_VIEW 	= WEB_BASE + "/t.course.list";
	public static final String COURSE_INFO_LIST_VIEW 		= WEB_BASE + "/course.info.list";
	public static final String COURSE_INFO_ADD_VIEW 		= WEB_BASE + "/course.info.add";
	public static final String COURSE_INFO_LIST_MORE_VIEW 	= WEB_BASE + "/course.info.list.more";
	public static final String COURSE_INFO_QRCODE_VIEW 		= WEB_BASE + "/course.info.qrcode";
	public static final String COURSE_LOOK_UP_VIEW 			= WEB_BASE + "/course.lookup";
	
	
	public static String getCourseLookUpView() {
		return COURSE_LOOK_UP_VIEW;
	}
	public static String getCourseListView(){
		return COURSE_LIST_VIEW;
	}
	public static String getCourseListAddView(){
		return COURSE_LIST_ADD_VIEW;
	}
}
