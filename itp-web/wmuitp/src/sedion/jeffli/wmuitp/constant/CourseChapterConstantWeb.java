package sedion.jeffli.wmuitp.constant;

public class CourseChapterConstantWeb 
{
	public static final String COURSE_CHAPTER_BASE 			  = "/courseChapter";
	public static final String WEB_BASE					      = COURSE_CHAPTER_BASE + "/web";
	public static final String WEB_APP_BASE					  = COURSE_CHAPTER_BASE + "/webApp";
	
	/*
	 * views about CourseChapter
	 */
	public static final String COURSE_CHAPTER_LIST_VIEW 	  = WEB_BASE + "/courseChapter.list";
	public static final String COURSE_CHAPTER_LOOK_UP_TEA_VIEW 	  = WEB_BASE + "/courseChapter.lookup.tea";
	public static final String COURSE_CHAPTER_LOOK_UP_VIEW 	  = WEB_BASE + "/courseChapter.lookup";
	public static final String COURSE_CHAPTER_ADD_EDIT_VIEW   = WEB_BASE + "/courseChapter.addEdit";
	public static final String COURSE_CHAPTER_XLS_VIEW   	  = WEB_BASE + "/courseChapter.xls";
	
	/*
	 * 为了 项目大的时候,编译的java文件不用全部编译,用getXXX();
	 * 博客:http://www.ibm.com/developerworks/cn/java/l-java-interface/index.html
	 */
	public static String getCourseChapterLookUpTeaView() {
		return COURSE_CHAPTER_LOOK_UP_TEA_VIEW;
	}
	public static String getCourseChapterListView()
	{
		return COURSE_CHAPTER_LIST_VIEW;
	}
	public static String getCourseChapterAddEditView()
	{
		return COURSE_CHAPTER_ADD_EDIT_VIEW;
	}
	public static String getCourseChapterAddXls()
	{
		return COURSE_CHAPTER_XLS_VIEW;
	}
	public static String getCourseChapterLookUpView() {
		return COURSE_CHAPTER_LOOK_UP_VIEW;
	}
}
