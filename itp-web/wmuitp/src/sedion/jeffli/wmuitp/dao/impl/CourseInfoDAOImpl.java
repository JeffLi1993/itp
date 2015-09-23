package sedion.jeffli.wmuitp.dao.impl;

import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.dao.CourseInfoDAO;
import sedion.jeffli.wmuitp.entity.CourseInfo;

@Repository
public class CourseInfoDAOImpl extends GenericHibernateDAOImpl<CourseInfo> implements CourseInfoDAO
{}