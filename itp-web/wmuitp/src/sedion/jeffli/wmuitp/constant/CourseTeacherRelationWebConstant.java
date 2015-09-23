package sedion.jeffli.wmuitp.constant;

public class CourseTeacherRelationWebConstant 
{
	/**
	 * view about CourseTeacherRelation
	 */
	public static final String TEACHER_COURSE_LIST_VIEW = CourseConstantWeb.WEB_BASE + "/course.teacher.list";
	public static final String TEACHER_COURSE_LIST_VIEW_TEA = CourseConstantWeb.WEB_BASE + "/course.teacher.list.tea";
	public static final String TEACHER_COURSE_ADD_VIEW 	= CourseConstantWeb.WEB_BASE + "/course.teacher.add";
	public static final String TEACHER_COURSE_ADD_VIEW_TEA 	= CourseConstantWeb.WEB_BASE + "/course.teacher.add.tea";
	public static final String TEACHER_COURSE_LOOK_UP_VIEW 	= CourseConstantWeb.WEB_BASE + "/course.teacher.lookup";
	
	public static String getTeacherCourseListView()
	{
		return TEACHER_COURSE_LIST_VIEW;
	}

	public static String getTeacherCourseAddView()
	{
		return TEACHER_COURSE_ADD_VIEW;
	}

	public static String getTeacherCourseLookUpView() {
		return TEACHER_COURSE_LOOK_UP_VIEW;
	}
	public static String getTeacherCourseAddViewTea() {
		return TEACHER_COURSE_ADD_VIEW_TEA;
	}
	public static String getTeacherCourseListViewTea() {
		return TEACHER_COURSE_LIST_VIEW_TEA;
	}
}
