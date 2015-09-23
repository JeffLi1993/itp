package sedion.jeffli.constant;

public class LoginActivityConstant 
{
	public static final String USER_NAME_OR_USER_PASS_NO_EMPTY 	= "登录名或密码不能为空!";
	public static final String NET_WORK_UNAVAILABLE				= "网络不可用";
	public static final String USER_NAME_OR_USER_PASS_IS_FAIL 	= "用户名或密码错误";
	public static final String LOGIN_SUCCESS 					= "登录成功";
	public static final String ITP_GOES_ERROR 					= "软件出现错误,请通知管理员";
	
	public static String getUserNameOrUserPassNoEmpty() {
		return USER_NAME_OR_USER_PASS_NO_EMPTY;
	}
	public static String getNetWorkUnavailable() {
		return NET_WORK_UNAVAILABLE;
	}
	public static String getUserNameOrUserPassIsFail() {
		return USER_NAME_OR_USER_PASS_IS_FAIL;
	}
	public static String getLoginSuccess() {
		return LOGIN_SUCCESS;
	}
	public static String getItpGoesError() {
		return ITP_GOES_ERROR;
	}
	
	
}
