package sedion.jeffli.wmuitp.dao;

import sedion.jeffli.wmuitp.base.GenericHibernateDAO;
import sedion.jeffli.wmuitp.entity.UserLogin;

public interface UserLoginDAO extends GenericHibernateDAO<UserLogin>
{
	/**
	 * 通过名字获得UserLogin
	 * @param UserName
	 */
	UserLogin getUserLoginByName(String UserName);
}
