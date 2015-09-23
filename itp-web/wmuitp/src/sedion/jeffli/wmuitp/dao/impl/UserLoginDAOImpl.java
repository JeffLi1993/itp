package sedion.jeffli.wmuitp.dao.impl;

import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.dao.UserLoginDAO;
import sedion.jeffli.wmuitp.entity.UserLogin;

@Repository
public class UserLoginDAOImpl extends GenericHibernateDAOImpl<UserLogin> implements UserLoginDAO
{
	private static final String ALL_USER_LOGIN_ONE_EQUALS_ONE = "FROM UserLogin AS ul "+CommonConstant.ONE_EQUALS_ONE;
	
	@Override
	public UserLogin getUserLoginByName(String UserName)
	{
		StringBuilder hql = new StringBuilder(ALL_USER_LOGIN_ONE_EQUALS_ONE)
						.append(" AND ul.ulName=?");
		return (UserLogin) getUniqueResultByHQL(hql.toString(),UserName);
	}
	
}