package sedion.jeffli.wmuitp.base;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;



public class GenericHibernateDAOImpl<T> extends QueryBase implements GenericHibernateDAO<T>
{

	private Class<T> persistentClass;

	/*protected GenericHibernateDAO(Class<T> persistentClass) 
	{
		this.persistentClass = persistentClass;
	}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericHibernateDAOImpl() 
	{
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.persistentClass = (Class)pt.getActualTypeArguments()[0];
	}
	
	protected Class<T> getPersistentClass() 
	{
		return persistentClass;
	}
	
	/**
	 * find entity of database by entity-id
	 * --------------------------------------------------------
	 * @param(int) 		id
	 * @return  		T -> entity
	 */
	@SuppressWarnings("unchecked")
	public T findById(String id) 
	{
		return (T) getCurrentSession().get(persistentClass, Integer.valueOf(id));
	}
	
	/**
	 * find entity of database by entity-id
	 * --------------------------------------------------------
	 * @param(int) 		id
	 * @return  		T -> entity
	 */
	@SuppressWarnings("unchecked")
	public T findById(Integer id) 
	{
		return (T) getCurrentSession().get(persistentClass, id);
	}
	
	/**
	 * find entity of database by entity-id
	 * --------------------------------------------------------
	 * @param(long) 	id
	 * @return  		T -> entity
	 */
	@SuppressWarnings("unchecked")
	public T findById(Long id) 
	{
		return (T) getCurrentSession().get(persistentClass, id);
	}

	/**
	 * find unique-entity of database by hql
	 * --------------------------------------------------------
	 * @param(String) 	hql
	 * @return  	  	T -> entity
	 */
	@SuppressWarnings("unchecked")
	public T findByHQL(final String hql) 
	{
		return (T) getCurrentSession().createQuery(hql).uniqueResult();
	}

	/**
	 * save entity into database, make entity-data persistence
	 * --------------------------------------------------------
	 * @param(T) 		entity
	 * @return			T -> entity
	 */
	public T turnPersitent(T entity) 
	{
		getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * update entity into database, make entity-data persistence
	 * --------------------------------------------------------
	 * @param(T) 		entity
	 * @return			null
	 */
	public void updateEntity(T entity) 
	{
		getCurrentSession().saveOrUpdate(entity);
	}
	
	/**
	 * delete entity of database, make entity-data transient
	 * --------------------------------------------------------
	 * @param(T) 		entity
	 * @return			null
	 */
	public void turnTransient(T entity)
	{
		getCurrentSession().delete(entity);
	}

	/**
	 * save entity-s into database,
	 * make entity-s persistence by sql
	 * --------------------------------------------------------
	 * @param(String) 	hql
	 * @return			null
	 */
	public void turnTransients(final String sql) 
	{
		getCurrentSession().createSQLQuery(sql);
	}
	
	/**
	 * get entity of database by sql
	 * --------------------------------------------------------
	 * @param(String) 	hql
	 * @return			Object
	 */
	@SuppressWarnings("unchecked")
	public T getUniqueResultByHQL(String hql) 
	{
		Query query = createQuery(hql);
		try {
			return (T)query.uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * get entity of database by sql through objects
	 * --------------------------------------------------------
	 * @param(String) 	hql
	 * @param(Object)	objects
	 * @return			Object
	 */
	@SuppressWarnings("unchecked")
	public T getUniqueResultByHQL(String hql, Object... objects) 
	{
		Query query = createQuery(hql);
		if (objects != null) 
		{
			for (int i = 0; i < objects.length; i++) 
			{
				query.setParameter(i, objects[i]);
			}
		}
		try 
		{
			return (T) query.uniqueResult();
		} 
		catch (NoResultException e) 
		{
			return null;
		}
	}

	/**
	 * find entity-s of database by hql
	 * --------------------------------------------------------
	 * @param(String) 	hql			
	 * @param(String) 	offset		当前标识	
	 * @param(String) 	pageSize	分页大小
	 * @return			null/List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql, int offset, int pageSize) 
	{
		if (hql == null) 
		{
			return new ArrayList<T>();
		}
		Query query = createQuery(hql);
		if (!(offset == 0 && pageSize == 0)) 
		{
			query.setFirstResult(offset).setMaxResults(pageSize);
		}
		if (query.list() != null)
			return query.list();
		else
			return null;

	}

	/**
	 * find entity-s of database by hql
	 * through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql, Object[] values) 
	{
		if (hql == null) 
		{
			return new ArrayList<T>();
		}
		Query query = createQuery(hql);
		setQueryParams(query, values);
		if (query.list() != null)
			return query.list();
		else
			return null;
	}

	/**
	 * 	find entity-s of database by hql,
	 * through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql, List<Object> values) 
	{
		if (hql == null) 
		{
			return new ArrayList<T>();
		}
		Query query = createQuery(hql);
		setQueryParams(query, values);
		if (query.list() != null)
			return query.list();
		else
			return null;
	}

	/**
	 * find entity-s of database by hql,
	 * through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql, int offset, int pageSize, List<Object> values) 
	{
		if (hql == null) 
		{
			return new ArrayList<T>();
		}
		Query query = createQuery(hql);
		setQueryParams(query, values);
		if (!(offset == 0 && pageSize == 0)) 
		{
			query.setFirstResult(offset).setMaxResults(pageSize);
		}
		return query.list();
	}

	/**
	 * find entity-s of database by hql,
	 * through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql, int offset, int pageSize, Object[] parameters, Object[] values) 
	{
		if (hql == null) 
		{
			return new ArrayList<T>();
		}
		Query query = createQuery(hql);
		setQueryParams(query, parameters, values);
		if (!(offset == 0 && pageSize == 0)) 
		{
			query.setFirstResult(offset).setMaxResults(pageSize);
		}
		return query.list();
	}

	/**
	 * find entity-s of database by hql,
	 * through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql, int offset, int pageSize, List<String> parameters, List<String> values) 
	{
		if (hql == null) 
		{
			return new ArrayList<T>();
		}
		Query query = createQuery(hql);
		setQueryParams(query, parameters, values);
		if (!(offset == 0 && pageSize == 0)) 
		{
			query.setFirstResult(offset).setMaxResults(pageSize);
		}
		return query.list();
	}

	/**
	 * find entity-s of database by hql,
	 * through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql, int offset, int pageSize, Object[] values) 
	{
		if (hql == null) 
		{
			return new ArrayList<T>();
		}
		Query query = createQuery(hql);
		setQueryParams(query, values);
		if (!(offset == 0 && pageSize == 0))
		{
			query.setFirstResult(offset).setMaxResults(pageSize);
		}
		return query.list();
	}

	/**
	 * find entity-counts of database by hql,
	 * --------------------------------------------------------
	 */
	public long getCount(String hql)
	{
		String generatedCountHql = "select count(*) " + hql;
		Query countQuery = createQuery(generatedCountHql);
		long totalCount = ((Long) countQuery.uniqueResult()).intValue();
		return totalCount;
	}

	public long getCount(String hql, List<Object> values) 
	{
		String generatedCountHql = "select count(*) " + hql;
		Query countQuery = createQuery(generatedCountHql);
		setQueryParams(countQuery, values);
		long totalCount = ((Long) countQuery.uniqueResult()).intValue();
		return totalCount;
	}

	public long getCount(String hql, Object[] values) 
	{
		String generatedCountHql = "select count(*) " + hql;
		Query countQuery = createQuery(generatedCountHql);
		setQueryParams(countQuery, values);
		long totalCount = ((Long) countQuery.uniqueResult()).intValue();
		return totalCount;
	}

	public void updateObj(String hql, Object[] values) 
	{
		Query query = createQuery(hql);
		setQueryParams(query, values);
		query.executeUpdate();
	}


	@SuppressWarnings("unchecked")
	public List<T> getListByHQL(String hql) 
	{
		return (List<T>) createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListByHQLLimitNum(String hql,int limitNum, Object... objects) 
	{
		Query query=createQuery(hql);
		query.setMaxResults(limitNum);
		if (objects != null) 
		{
			for (int i = 0; i < objects.length; i++) 
			{
				query.setParameter(i, objects[i]);
			}
		}
		try 
		{
			return (List<T>)query.list();
		} 
		catch (NoResultException e) 
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListByHQL(String hql, Object... objects) 
	{
		Query query = createQuery(hql);
		
		if (objects != null) 
		{
			for (int i = 0; i < objects.length; i++) 
			{
				query.setParameter(i, objects[i]);
			}
		}
		try 
		{
			return query.list();
		} 
		catch (NoResultException e) 
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() 
	{
		String hql = "from " + persistentClass.getSimpleName();
		return (List<T>) createQuery(hql).list();
	}


	
	/**
	 * -----------------------------------------------------------------------
	 * Composition where statement
	 * @param orderby
	 */
	protected static void setQueryParams(Query query, Object[] values) 
	{
		if (values != null && values.length > 0) 
		{
			for (int i = 0; i < values.length; i++) 
			{
				query.setParameter(i, values[i]);
			}
		}
	}

	protected static void setQueryParams(Query query, List<Object> values) 
	{
		if (values != null && values.size() > 0) 
		{
			for (int i = 0; i < values.size(); i++) 
			{
				query.setParameter(i, values.get(i));
			}
		}
	}

	public static void setQueryParams(Query query, Object[] queryParams, Object[] values) 
	{
		if (queryParams != null && values != null && queryParams.length > 0 && values.length > 0 && (queryParams.length == values.length)) 
		{
			for (int i = 0; i < queryParams.length; i++) 
			{
				query.setParameter(String.valueOf(queryParams[i]), values[i]);
			}
		}
	}

	protected static void setQueryParams(Query query, List<String> queryParams, List<String> values) 
	{
		if (queryParams != null && values != null && queryParams.size() > 0 && values.size() > 0 && (queryParams.size() == values.size())) 
		{
			for (int i = 0; i < queryParams.size(); i++) 
			{
				query.setParameter(String.valueOf(queryParams.get(i)), values.get(i));
			}
		}
	}
	
	/**
	 * end
	 * -----------------------------------------------------------------------
	 */

}
