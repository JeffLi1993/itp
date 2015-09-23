package sedion.jeffli.wmuitp.dao.impl;

import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.dao.TeacherInfoDAO;
import sedion.jeffli.wmuitp.entity.TeacherInfo;

@Repository
public class TeacherInfoDAOImpl extends GenericHibernateDAOImpl<TeacherInfo>
		implements TeacherInfoDAO 
		{

	private static final String All_TEACHERINFOS = "FROM TeacherInfo AS ti ";

	@Override
	public TeacherInfo getTeacherInfoByUserLoginId(String UserLoginId) 
	{
		StringBuilder hql = new StringBuilder(All_TEACHERINFOS)
				.append(CommonConstant.ONE_EQUALS_ONE)
				.append(" AND ti.userLogin.ulId=").append(UserLoginId);
		return getUniqueResultByHQL(hql.toString());
	}
}
