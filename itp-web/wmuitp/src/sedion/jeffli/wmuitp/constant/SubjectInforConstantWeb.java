package sedion.jeffli.wmuitp.constant;

public class SubjectInforConstantWeb 
{
	public static final String SUBJECT_INFOR_BASE 			  = "/subjectInfor";
	public static final String WEB_BASE					      = SUBJECT_INFOR_BASE + "/web";
	public static final String WEB_APP_BASE					  = SUBJECT_INFOR_BASE + "/webApp";
	
	/*
	 * views about SubjectInfor
	 */
	public static final String SUBJECT_INFOR_LIST_VIEW 		  = WEB_BASE + "/subjectInfor.list";
	public static final String SUBJECT_INFOR_LIST_TEA_VIEW 		  = WEB_BASE + "/subjectInfor.list.tea";
	public static final String SUBJECT_INFOR_DETAIL_VIEW	  = WEB_BASE + "/subjectInfor.detail";
	public static final String SUBJECT_INFOR_ADD_EDIT_VIEW    = WEB_BASE + "/subjectInfor.addEdit";
	public static final String SUBJECT_INFOR_ADD_EDIT_TEA_VIEW    = WEB_BASE + "/subjectInfor.addEdit.tea";
	public static final String SUBJECT_INFOR_ADD_XLS_VIEW     = WEB_BASE + "/subjectInfor.xls";
	
	public static final String SUBJECT_INFOR_LIST_FOR_LU_VIEW = WEB_BASE + "/subjectInfor.list.lookup";
	
	/*
	 * 为了 项目大的时候,编译的java文件不用全部编译,用getXXX();
	 * 博客:http://www.ibm.com/developerworks/cn/java/l-java-interface/index.html
	 */
	public static String getSubjectInforAddEditTeaView() 
	{
		return SUBJECT_INFOR_ADD_EDIT_TEA_VIEW;
	}
	public static String getSubjectInforListTeaView() 
	{
		return SUBJECT_INFOR_LIST_TEA_VIEW;
	}
	public static String getSubjectInforListView()
	{
		return SUBJECT_INFOR_LIST_VIEW;
	}
	
	public static String getSubjectInforDetailView()
	{
		return SUBJECT_INFOR_DETAIL_VIEW;
	}
	
	public static String getSubjectInforAddEditView()
	{
		return SUBJECT_INFOR_ADD_EDIT_VIEW;
	}

	public static String getSubjectInforListForLuView()
	{
		return SUBJECT_INFOR_LIST_FOR_LU_VIEW;
	}
	public static String getSubjectInforAddXlsView()
	{
		return SUBJECT_INFOR_ADD_XLS_VIEW;
	}
	
}
