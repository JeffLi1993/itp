package sedion.jeffli.wmuitp.util.session;

import javax.servlet.http.HttpSession;

import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;

public class AdminUtil
{
	public static final String ADMIN 		  = "admin";
	public static final String ADMIN_ID 	  = "adminID";
	public static final String TEACHER_NAME   = "teacherName";
	public static final String ADMIN_PASS_ENCRYPT = "teacherPassEncrypt";
	
	public static void saveAdminToSession(HttpSession session,UserLogin userLogin)
	{
		session.setAttribute(ADMIN, userLogin);
		System.out.println("存admin");
	}
	
	public static void saveAdminIDToSession(HttpSession session,UserLogin userLogin)
	{
		session.setAttribute(ADMIN_ID, userLogin.getUlId().toString());
		System.out.println("存adminId");
	}
	
	public static void saveTeacherIDToSession(HttpSession session,TeacherInfo teacherInfo)
	{
		session.setAttribute(TEACHER_NAME, teacherInfo.getTiName());
		System.out.println("存teacherName");
	}
	
	public static void saveAdminPassEncrypt(HttpSession session,String teacherPassEncrypt)
	{
		session.setAttribute(ADMIN_PASS_ENCRYPT, teacherPassEncrypt);
		System.out.println("存teacherPassEncrypt");
	}
	
	public static UserLogin getUserLoginFromHttpSession(HttpSession session)
	{
		Object attribute = session.getAttribute(ADMIN);
		return attribute == null ? null : (UserLogin)attribute;
	}
	
	public static String getUserLoginIDFromHttpSession(HttpSession session)
	{
		Object attribute = session.getAttribute(ADMIN_ID);
		return attribute == null ? null : (String)attribute;
	}
	
}
