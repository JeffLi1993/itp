package sedion.jeffli.wmuitp.base;

import org.springframework.stereotype.Repository;

//首先使用 @Repository 将 DAO 类声明为 Bean 
@Repository
public class CommonDAO extends GenericHibernateDAOImpl<String> {}
