package sedion.jeffli.wmuitp.base;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class QueryBase 
{

	//@Autowired 自动装配
	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() 
	{
		return sessionFactory.getCurrentSession();
	}

	public Query createQuery(String hql) 
	{
		Query query = getCurrentSession().createQuery(hql);
		return query;
	}

	public Query createQuery(String hql, Object... objects)
	{
		Query query = getCurrentSession().createQuery(hql);
		if (objects != null) 
		{
			for (int i = 0; i < objects.length; i++) 
			{
				query.setParameter(i, objects[i]);
			}
		}
		return query;
	}

	public Query createQuery(String hql, int offset, int pageSize) 
	{
		Query query = getCurrentSession().createQuery(hql);
		if (!(offset == 0 && pageSize == 0)) 
		{
			query.setFirstResult(offset).setMaxResults(pageSize);
		}
		return query;
	}

	public Query createQuery(String hql, int offset, int pageSize, Object... objects) 
	{
		Query query = getCurrentSession().createQuery(hql);
		if (objects != null) 
		{
			for (int i = 0; i < objects.length; i++) 
			{
				query.setParameter(i, objects[i]);
			}
		}
		if (!(offset == 0 && pageSize == 0)) 
		{
			query.setFirstResult(offset).setMaxResults(pageSize);
		}
		return query;
	}
}
