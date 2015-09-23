package sedion.jeffli.wmuitp.constant;

public class ProfessionInfoConstantWeb {
	public static final String PROFESSION_INFO_BASE					= "/professionInfo";
	public static final String WEB_BASE					    		= PROFESSION_INFO_BASE + "/web";
	public static final String WEB_APP_BASE							= PROFESSION_INFO_BASE + "/webApp";
	
	/**
	 * views about professionInfo
	 */
	public static final String PROFESSION_INFO_LIST_VIEW	 		= WEB_BASE + "/professionInfo.list";
	public static final String PROFESSION_INFO_ADD_EDIT_VIEW		= WEB_BASE + "/professionInfo.addEdit";
	
	public static String getProfessionInfoListView()
	{
		return PROFESSION_INFO_LIST_VIEW;
	}
	public static String getProfessionInfoAddEditView()
	{
		return PROFESSION_INFO_ADD_EDIT_VIEW;
	}
	
	
}
