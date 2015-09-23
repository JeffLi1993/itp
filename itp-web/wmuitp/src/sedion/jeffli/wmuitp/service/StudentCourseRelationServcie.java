package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.courseInfo.StudentPresent;


public interface StudentCourseRelationServcie
{
	/**
	 * 获取未到学生详情
	 * @param courseInfoId 课程id
	 * @param classId 班级id
	 * @return
	 */
	List<StudentInfo> getAbsentDetail(String courseInfoId,String classId);
	
	/**
	 * 获得考勤率
	 * @param courseInfoId	
	 */
	List<StudentPresent> getAttendance(String courseInfoId);
	
	/**
	 * 保存学生关系表 
	 * @param studentInfo	 学生详情
	 * @param courseInfo	 课程详情
	 */
	int saveStudentCourseRelationBySIAndCI(StudentInfo studentInfo,
			CourseInfo courseInfo);

	/**
	 * 获取学生课程关系表
	 * @param stuUserLogin	学生登录信息
	 * @return
	 */
	List<StudentCourseRelation> getStudentCourseRelationsByStudentLogin(
			UserLogin stuUserLogin,
			String DataStr);

	/**
	 * App 学生签到成功
	 * @param courseInfoID 
	 * @param userLoginID
	 * @return
	 */
	int turnSCRPresent(String courseInfoID, String userLoginID);

	
}
