package sedion.jeffli.wmuitp.constant;

public class ExamStudentResultWebConstant 
{
	
	
	public static final String PAPERINFO_BASE 			    = "/paperInfo";
	public static final String WEB_BASE					    = PAPERINFO_BASE + "/web";
	public static final String WEB_APP_BASE					= PAPERINFO_BASE + "/webApp";
	
	/**
	 * views about PaperInfo
	 */
	public static final String PAPERINFO_LIST_VIEW 			= WEB_BASE + "/paperInfo.list";
	public static final String PAPERINFO_DETAIL_VIEW		= WEB_BASE + "/paperInfo.detail";
	public static final String PAPERINFO_ADD_EDIT_VIEW		= WEB_BASE + "/paperInfo.addEdit";
	
	
	public static String getPaperinfoListView()
	{
		return PAPERINFO_LIST_VIEW;
	}
	
	public static String getPaperinfoAddEditView()
	{
		return PAPERINFO_ADD_EDIT_VIEW;
	}
	
	public static String getPaperinfoDetailView()
	{
		return PAPERINFO_DETAIL_VIEW;
	}
	
	/**
	 * app view about course-info
	 */
	public static final String PAPERINFO_LIST_APP_VIEW 					= WEB_APP_BASE + "/paper.info.list";
	public static final String PAPERSUBJECT_RELATION_LIST_APP_VIEW 		= WEB_APP_BASE + "/paper.subject.relation.list";
	
	public static String getPaperinfoListAppView() 
	{
		return PAPERINFO_LIST_APP_VIEW;
	}

	public static String getPapersubjectRelationListAppView() 
	{
		return PAPERSUBJECT_RELATION_LIST_APP_VIEW;
	}
	
	

}
