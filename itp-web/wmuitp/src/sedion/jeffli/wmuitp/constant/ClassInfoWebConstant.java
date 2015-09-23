package sedion.jeffli.wmuitp.constant;

public class ClassInfoWebConstant {
	public static final String CLASS_INFO_BASE				    	= "/classInfo";
	public static final String WEB_BASE					    		= CLASS_INFO_BASE + "/web";
	public static final String WEB_APP_BASE							= CLASS_INFO_BASE + "/webApp";
	
	/**
	 * views about classInfo
	 */
	public static final String CLASS_INFO_LIST_VIEW	 		= WEB_BASE + "/classInfo.list";
	public static final String CLASS_INFO_ADD_EDIT_VIEW		= WEB_BASE + "/classInfo.addEdit";
	public static final String CLASS_INFO_LOOKUP_VIEW		= WEB_BASE + "/classInfo.lookup";
	public static final String CLASS_INFO_LOOKUP_SIGNLE_VIEW		= WEB_BASE + "/classInfo.lookup.signle";
	public static String getClassInfoListView()
	{
		return CLASS_INFO_LIST_VIEW;
	}
	public static String getClassInfoAddEditView()
	{
		return CLASS_INFO_ADD_EDIT_VIEW;
	}
	public static String getClassInfoLookupView() {
		return CLASS_INFO_LOOKUP_VIEW;
	}
	public static String getClassInfoLookupSignleView() {
		return CLASS_INFO_LOOKUP_SIGNLE_VIEW;
	}
}
