package sedion.jeffli.wmuitp.dao;

import java.util.List;

import sedion.jeffli.wmuitp.base.GenericHibernateDAO;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;

public interface StudentCourseRelationDAO extends GenericHibernateDAO<StudentCourseRelation>
{
	/**
	 * 获得学生课程关系通过课程Id
	 * @param courseInfoId 课程Id
	 */
	List<StudentCourseRelation> getStudentCourseRelationByCourseInfoId(String courseInfoId);
}
