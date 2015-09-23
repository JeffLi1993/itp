package sedion.jeffli.wmuitp.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.dao.StudentCourseRelationDAO;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;

@Repository
public class StudentCourseRelationDAOImpl extends
		GenericHibernateDAOImpl<StudentCourseRelation> implements
		StudentCourseRelationDAO 
{
	private static final String All_STUDENT_COURSE_RELATION = "FROM StudentCourseRelation AS scr ";

	@Override
	public List<StudentCourseRelation> getStudentCourseRelationByCourseInfoId(
			String courseInfoId) 
{
		StringBuilder hql = new StringBuilder(All_STUDENT_COURSE_RELATION)
				.append(CommonConstant.ONE_EQUALS_ONE)
				.append(" AND scr.courseInfo.ciId=").append(courseInfoId);
		return getListByHQL(hql.toString());
	}

}