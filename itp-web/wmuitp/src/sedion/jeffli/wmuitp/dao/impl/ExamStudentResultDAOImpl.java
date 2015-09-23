package sedion.jeffli.wmuitp.dao.impl;

import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.dao.ExamStudentResultDAO;
import sedion.jeffli.wmuitp.entity.ExamStudentResult;

@Repository
public class ExamStudentResultDAOImpl extends GenericHibernateDAOImpl<ExamStudentResult> implements ExamStudentResultDAO
{}