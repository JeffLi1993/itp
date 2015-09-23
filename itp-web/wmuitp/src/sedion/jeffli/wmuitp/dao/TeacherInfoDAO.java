package sedion.jeffli.wmuitp.dao;

import sedion.jeffli.wmuitp.base.GenericHibernateDAO;
import sedion.jeffli.wmuitp.entity.TeacherInfo;

public interface TeacherInfoDAO extends GenericHibernateDAO<TeacherInfo>
{
	
	/**
	 * 通过用户Id获得老师实体
	 * @param UserLoginId 用户Id
	 */
	TeacherInfo getTeacherInfoByUserLoginId(String UserLoginId);
}
