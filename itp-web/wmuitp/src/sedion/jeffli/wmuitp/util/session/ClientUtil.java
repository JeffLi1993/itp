package sedion.jeffli.wmuitp.util.session;

import javax.servlet.http.HttpSession;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;

public class ClientUtil 
{
	public static final String STUDENT_ID 	= "stuId";
	public static final String STUDENT_NAME = "stuName";
	
	public static void saveStudentLoginToSession(HttpSession session,UserLogin userLogin)
	{
		session.setAttribute(STUDENT_ID, userLogin);
		System.out.println("添加stuId");
	}
	
	
	public static UserLogin getStudentLoginFromHttpSession(HttpSession session)
	{
		Object attribute = session.getAttribute(STUDENT_ID);
		return attribute == null ? null : (UserLogin)attribute;
	}
	public static void saveStudentIDToSession(HttpSession session,StudentInfo studentInfo)
	{
		session.setAttribute(STUDENT_NAME, studentInfo.getSiRealName());
		System.out.println("添加studentInfo");
	}
}
