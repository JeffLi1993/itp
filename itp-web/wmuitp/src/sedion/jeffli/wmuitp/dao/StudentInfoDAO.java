package sedion.jeffli.wmuitp.dao;

import sedion.jeffli.wmuitp.base.GenericHibernateDAO;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;

public interface StudentInfoDAO extends GenericHibernateDAO<StudentInfo>
{
	/**
	 * 通过 UserLogin 获得 StudentInfo
	 * @param userLogin
	 */
	StudentInfo getStudentInfoByUserLogin(UserLogin  userLogin);
}
