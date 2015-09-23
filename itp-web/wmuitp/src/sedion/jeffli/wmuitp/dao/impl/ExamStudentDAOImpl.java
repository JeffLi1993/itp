package sedion.jeffli.wmuitp.dao.impl;


import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.dao.ExamStudentDAO;
import sedion.jeffli.wmuitp.entity.ExamStudent;

@Repository
public class ExamStudentDAOImpl extends GenericHibernateDAOImpl<ExamStudent> implements ExamStudentDAO
{}