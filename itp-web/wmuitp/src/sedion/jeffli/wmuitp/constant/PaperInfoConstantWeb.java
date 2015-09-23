package sedion.jeffli.wmuitp.constant;

public class PaperInfoConstantWeb 
{
	public static final String PAPERINFO_BASE 			    = "/paperInfo";
	public static final String WEB_BASE					    = PAPERINFO_BASE + "/web";
	public static final String WEB_APP_BASE					= PAPERINFO_BASE + "/webApp";
	
	/**
	 * views about PaperInfo
	 */
	public static final String PAPERINFO_LIST_VIEW 			= WEB_BASE + "/paperInfo.list";
	public static final String PAPERINFO_LIST_TEA_VIEW 			= WEB_BASE + "/paperInfo.list.tea";
	public static final String PAPERINFO_DETAIL_VIEW		= WEB_BASE + "/paperInfo.detail";
	public static final String PAPERINFO_ADD_EDIT_VIEW		= WEB_BASE + "/paperInfo.addEdit";
	public static final String PAPERINFO_ADD_EDIT_TEA_VIEW		= WEB_BASE + "/paperInfo.addEdit.tea";
	
	
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
	public static String getPaperinfoListTeaView()
	{
		return PAPERINFO_LIST_TEA_VIEW;
	}
	public static String getPaperinfoAddEditTeaView()
	{
		return PAPERINFO_ADD_EDIT_TEA_VIEW;
	}
	/**
	 * app view about course-info
	 */
	public static final String PAPERINFO_LIST_APP_VIEW 					= WEB_APP_BASE + "/paper.info.list";
	public static final String PAPERINFO_LIST_BEFORE_APP_VIEW 			= WEB_APP_BASE + "/paper.info.before.list";
	public static final String PAPERSUBJECT_RELATION_LIST_APP_VIEW 		= WEB_APP_BASE + "/paper.subject.relation.list";
	
	public static final String PAPERINFO_LIST_TEA_APP_VIEW 				= WEB_APP_BASE + "/paper.info.tea.list";
	public static final String PAPERSUBJECT_RELATION_LIST_TEA_APP_VIEW 	= WEB_APP_BASE + "/paper.subject.relation.tea.list";
	public static final String PAPERINFO_STATISTIC_DETAIL_APP_VIEW 		= WEB_APP_BASE + "/paper.statistic.detail";
	public static final String PAPERINFO_STATISTIC_APP_VIEW 			= WEB_APP_BASE + "/paper.statistic";
	
	public static String getPaperinfoListAppView() 
	{
		return PAPERINFO_LIST_APP_VIEW;
	}

	public static String getPapersubjectRelationListAppView() 
	{
		return PAPERSUBJECT_RELATION_LIST_APP_VIEW;
	}

	public static String getPaperinfoListTeaAppView()
	{
		return PAPERINFO_LIST_TEA_APP_VIEW;
	}

	public static String getPapersubjectRelationListTeaAppView()
	{
		return PAPERSUBJECT_RELATION_LIST_TEA_APP_VIEW;
	}
	
	public static String getPaperinfoStatisticView() 
	{
		return PAPERINFO_STATISTIC_APP_VIEW;
	}

	public static String getPaperinfoStatisticDetailAppView() {
		return PAPERINFO_STATISTIC_DETAIL_APP_VIEW;
	}

	public static String getPaperinfoListBeforeAppView()
	{
		return PAPERINFO_LIST_BEFORE_APP_VIEW;
	}
	
}
