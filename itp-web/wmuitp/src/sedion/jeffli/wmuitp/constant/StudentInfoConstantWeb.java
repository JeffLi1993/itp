package sedion.jeffli.wmuitp.constant;

public class StudentInfoConstantWeb {
	public static final String STUDENT_INFO_BASE					= "/studentInfo";
	public static final String WEB_BASE					    		= STUDENT_INFO_BASE + "/web";
	public static final String WEB_APP_BASE							= STUDENT_INFO_BASE + "/webApp";
	
	/**
	 * views about teacher
	 */
	public static final String STUDENT_INFO_LIST_VIEW	 		= WEB_BASE + "/student.list";
	public static final String STUDENT_INFO_ADD_EDIT_VIEW		= WEB_BASE + "/student.addEdit";
	public static final String STUDENT_INFO_DETAIL_VIEW			= WEB_BASE + "/student.detail";
	public static final String STUDENT_INFO_ADD_XLS 			= WEB_BASE + "/student.xls";
	public static final String STUDENT_INFO_ERROR_DOWNLOAD 		= WEB_BASE + "/xls.showerror";
	
	public static String getStudentInfoListView()
	{
		return STUDENT_INFO_LIST_VIEW;
	}
	public static String getStudentInfoAddEditView()
	{
		return STUDENT_INFO_ADD_EDIT_VIEW;
	}
	public static String getStudentInfoDetailView()
	{
		return STUDENT_INFO_DETAIL_VIEW;
	}
	public static String getStudentInfoAddXls()
	{
		return STUDENT_INFO_ADD_XLS;
	}
	public static String getStudentInfoAddXlsError()
	{
		return STUDENT_INFO_ERROR_DOWNLOAD;
	}
}
