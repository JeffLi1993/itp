package sedion.jeffli.wmuitp.dao.impl;

import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.dao.CourseTeacherRelationDAO;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;

@Repository
public class CourseTeacherRelationDAOImpl extends GenericHibernateDAOImpl<CourseTeacherRelation> implements CourseTeacherRelationDAO
{}
