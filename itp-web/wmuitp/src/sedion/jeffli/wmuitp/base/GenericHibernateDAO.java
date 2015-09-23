package sedion.jeffli.wmuitp.base;

import java.util.List;

public interface GenericHibernateDAO<T> {


	/**
	 * find entity of database by entity-id
	 * --------------------------------------------------------
	 * 
	 * @param(int) id
	 * @return T -> entity
	 */
	T findById(String id);

	/**
	 * find entity of database by entity-id
	 * --------------------------------------------------------
	 * 
	 * @param(int) id
	 * @return T -> entity
	 */
	T findById(Integer id);

	/**
	 * find entity of database by entity-id
	 * --------------------------------------------------------
	 * 
	 * @param(long) id
	 * @return T -> entity
	 */
	T findById(Long id);

	/**
	 * find unique-entity of database by hql
	 * --------------------------------------------------------
	 * 
	 * @param(String) hql
	 * @return T -> entity
	 */
	T findByHQL(final String hql);

	/**
	 * save entity into database, make entity-data persistence
	 * --------------------------------------------------------
	 * 
	 * @param(T) entity
	 * @return T -> entity
	 */
	T turnPersitent(T entity);

	/**
	 * update entity into database, make entity-data persistence
	 * --------------------------------------------------------
	 * 
	 * @param(T) entity
	 * @return null
	 */
	void updateEntity(T entity);

	/**
	 * delete entity of database, make entity-data transient
	 * --------------------------------------------------------
	 * 
	 * @param(T) entity
	 * @return null
	 */
	void turnTransient(T entity);

	/**
	 * save entity-s into database, make entity-s persistence by sql
	 * --------------------------------------------------------
	 * 
	 * @param(String) hql
	 * @return null
	 */
	void turnTransients(final String sql);

	/**
	 * get entity of database by sql
	 * --------------------------------------------------------
	 * 
	 * @param(String) hql
	 * @return Object
	 */
	T getUniqueResultByHQL(String hql);

	/**
	 * get entity of database by sql through objects
	 * --------------------------------------------------------
	 * 
	 * @param(String) hql
	 * @param(Object) objects
	 * @return Object
	 */
	T getUniqueResultByHQL(String hql, Object... objects);

	/**
	 * find entity-s of database by hql
	 * --------------------------------------------------------
	 * 
	 * @param(String) hql
	 * @param(String) offset 当前标识
	 * @param(String) pageSize 分页大小
	 * @return null/List<T>
	 */
	List<T> findByPage(String hql, int offset, int pageSize);

	/**
	 * find entity-s of database by hql through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	List<T> findByPage(String hql, Object[] values);

	/**
	 * find entity-s of database by hql, through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	List<T> findByPage(String hql, List<Object> values);

	/**
	 * find entity-s of database by hql, through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	List<T> findByPage(String hql, int offset, int pageSize, List<Object> values);

	/**
	 * find entity-s of database by hql, through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	List<T> findByPage(String hql, int offset, int pageSize,
			Object[] parameters, Object[] values);

	/**
	 * find entity-s of database by hql, through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	List<T> findByPage(String hql, int offset, int pageSize,
			List<String> parameters, List<String> values);

	/**
	 * find entity-s of database by hql, through setQueryParams(...)
	 * --------------------------------------------------------
	 */
	List<T> findByPage(String hql, int offset, int pageSize, Object[] values);

	/**
	 * find entity-counts of database by hql,
	 * --------------------------------------------------------
	 */
	long getCount(String hql);

	long getCount(String hql, List<Object> values);

	long getCount(String hql, Object[] values);

	void updateObj(String hql, Object[] values);

	List<T> getListByHQL(String hql);

	List<T> getListByHQLLimitNum(String hql, int limitNum, Object... objects);

	List<T> getListByHQL(String hql, Object... objects);

	List<T> findAll();

	/**
	 * -----------------------------------------------------------------------
	 * Composition where statement
	 * 
	 * @param orderby
	 */
//	protected static void setQueryParams(Query query, Object[] values);
//
//	protected static void setQueryParams(Query query, List<Object> values);
//
//	protected static void setQueryParams(Query query, Object[] queryParams, Object[] values);
//
//	protected static void setQueryParams(Query query, List<String> queryParams,
//			List<String> values);


	/**
	 * end
	 * -----------------------------------------------------------------------
	 */

}
