package sedion.jeffli.wmuitp.dao.impl;

import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.dao.StudentInfoDAO;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;

@Repository
public class StudentInfoDAOImpl extends GenericHibernateDAOImpl<StudentInfo> implements StudentInfoDAO
{
	private static final String ALL_STUDENT_INFOS = "FROM StudentInfo AS si WHERE 1=1 ";
	
	@Override 
	public StudentInfo getStudentInfoByUserLogin(UserLogin  userLogin)
	{
		StringBuilder hql = new StringBuilder(ALL_STUDENT_INFOS);
		hql.append(" AND si.userLogin=?");
		return getUniqueResultByHQL(hql.toString(),userLogin);
	}
}