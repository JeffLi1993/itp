package sedion.jeffli.wmuitp.constant;

public class AdminWebConstant 
{
	
	public static final String ADMIN_BASE 	= "/admin";
	public static final String WEB_BASE 	= ADMIN_BASE + "/web";

	/**
	 * view
	 */
	public static final String ADMIN_LOGIN_VIEW = WEB_BASE + "/login";
	public static final String ADMIN_INDEX_VIEW = ADMIN_BASE + "/index/index";
	public static final String ADMIN_CHANGE_USERPWD_BY_USERLOGIN_VIEW = WEB_BASE + "/changeUserpwdByUserLogin";
	public static final String ADMIN_CHANGE_USERPWD_BY_USERLOGIN_APP_VIEW = WEB_BASE + "/changeUserpwdByUserLoginApp";
	
	
	public static String getAdminChangeUserpwdByUserloginAppView()
	{
		return ADMIN_CHANGE_USERPWD_BY_USERLOGIN_APP_VIEW;
	}


	public static String getAdminChangeUserpwdByUserloginView()
	{
		return ADMIN_CHANGE_USERPWD_BY_USERLOGIN_VIEW;
	}
}
