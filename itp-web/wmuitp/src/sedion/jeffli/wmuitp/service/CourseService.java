package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.Course;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.util.Page;

public interface CourseService
{
	/**
	 * ---------------------------基础课程----------------------------
	 */
	
	/**
	 * 通过课名字获得基本课
	 * @param CName 课名字
	 * @return
	 */
	List<Course> getCourseByCourseName(String CName);
	
	/**
	 * 获取课程
	 * @param page			分页类
	 * @param pageParams	分页参数
	 */
	List<Course> getCoursePages(Page<Course> page,int[] pageParams);
	
	/**
	 * 获取	课程 
	 * @param courseId 课程ID
	 * @return
	 */
	public Course findCourseById(long courseId);
	
	/**
	 * 获取	课程 	List
	 * @return
	 */
	public List<Course> getCourses();
	
	/**
	 * 根据CId查找课程
	 */
	public Course getCourseById(String CId);
	/**
	 * 添加、更新基础课程
	 */
	public int saveOrUpdateCourse(Course course);
	/**
	 * 删除基础课程
	 */
	public int deleteCourse(String CId);
	
	/**
	 * --------------------------课程及授课老师-------------------------
	 */
	
	/**
	 * 获取	课程及授课老师	List
	 * @return
	 */
	public List<CourseTeacherRelation> getCourseTeacherRelations();
	
	/**
	 * @param 	teacherID	教师ID
	 * @return
	 */
	public List<CourseTeacherRelation> getCourseTeacherRelationsByOBJ(String teacherID);
	
	
	/**
	 * --------------------------课详情和学生--------------------------
	 */
	
	/**
	 * 保存	课详情和学生关系
	 * @param studentCourseRelation
	 */
	public void saveStudentCourseRelation(StudentCourseRelation studentCourseRelation);
	
	/**
	 * 获取	学生和课关系	List
	 * @param ciId	课详情ID
	 * @return
	 */
	public List<StudentCourseRelation> getStuStudentCourseRelationsByCIID(
			String ciId);
	
	/**
	 * -----------------------------课详细----------------------------
	 */
	
	/**
	 * 获取	课详情 	List
	 * @return
	 */
	public List<CourseInfo> getCourseInfos();
	
	/**
	 * 获取	课详情 	list
	 * @param teacherID		教师ID
	 * @return
	 */
	public List<CourseInfo> getCourseInfosByTeacherID(String teacherID);
	
	/**
	 * 获取	课详情
	 * @param courseID		课ID
	 * @return
	 */
	public CourseInfo findCourseInfoById(Integer courseID);
	
	/**
	 * 获取	课详情
	 * @param dateTimeStr	时间
	 * @return
	 */
	public List<CourseInfo> getCourseInfosByDateTime(String dateTimeStr);
	
	/**
	 * 保存	课详情
	 * @param courseInfo				课详情
	 * @param courseTeacherRelationID 	课程和老师关系表ID
	 */
	public CourseInfo saveCourseInfo(CourseInfo courseInfo,String courseTeacherRelationID);
	
	/**
	 * 更新      课详情
	 * @param courseInfo  	课详情
	 */
	public void updateCourseInfo(CourseInfo  courseInfo);
	
	/**
	 * 获取	课教师关系表
	 * @param courseTeacherRelationID	课教师关系ID
	 * @return
	 */
	public CourseTeacherRelation getCourseTeacherRelationsByCTRID(
			String courseTeacherRelationID);

	public void saveStudentCourseRelationBystudentInfoIDAndcourseInfoID(
			String studentInfoID, String courseInfoID);

	/**
	 * 根据学生信息 获取学生课程大类
	 * @return
	 */
	List<Course> getCoursesByStu(UserLogin studentLoginFromHttpSession);

	

	
	

}
