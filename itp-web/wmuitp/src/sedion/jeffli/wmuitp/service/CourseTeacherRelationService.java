package sedion.jeffli.wmuitp.service;


import java.util.List;

import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.util.Page;

public interface CourseTeacherRelationService
{
	/**
	 * 按照搜索条件返回课程和教师关系
	 * @param courseName 基础课程
	 * @param teacherName 教师
	 * @return
	 */
	List<CourseTeacherRelation> getCourseTeacherRelationSearchLess(String courseName,String teacherName);
	
	/**
	 * 按照搜索条件返回课程和教师关系
	 * @param courseName 基础课程
	 * @param teacherName 教师
	 * @return
	 */
	List<CourseTeacherRelation> getCourseTeacherRelationSearchLessTea(String courseName,String teacherName);
	
	/**
	 * 获得课程老师关系
	 * @param page			分页类
	 * @param pageParams	分页参数
	 * @return
	 */
	List<CourseTeacherRelation> getCourseTeacherRelationPages(Page<CourseTeacherRelation> page,int[] pageParams);
	
	/**
	 * 获得课程老师关系
	 * @param page			分页类
	 * @param pageParams	分页参数
	 * @return
	 */
	List<CourseTeacherRelation> getCourseTeacherRelationPagesTea(Page<CourseTeacherRelation> page,int[] pageParams);
	
	/**
	 * 获取	课程及授课老师	List
	 * @return
	 */
	public List<CourseTeacherRelation> getCourseTeacherRelations();
	
	/**
	 * 获取	课程及授课老师	List(教师权限) 
	 * @return
	 */
	public List<CourseTeacherRelation> getCourseTeacherRelationsByTea(UserLogin userLogin);
	
	/**
	 * 获取	课教师关系表
	 * @param courseTeacherRelationID	课教师关系ID
	 * @return
	 */
	public CourseTeacherRelation getCourseTeacherRelationById(String courseTeacherRelationID);

	/**
	 * 保存      课教师关系表
	 * @param courseID					课ID
	 * @param teacherInfoID				老师详情ID
	 * @param courseTeacherRelation 	课教师关系表
	 * @return
	 */
	public int saveOrUpdateCourseTeacherRelation(String courseID,String teacherInfoID, CourseTeacherRelation courseTeacherRelation);

	/**
	 * 删除	课教师关系表
	 * @param ojbIdStr					 课教师关系表ID str
	 * @return
	 */
	public int deleteCourseTeacherRelations(String ojbIdStr);

	/**
	 * 获取      课程老师关系表
	 * @param teacherId
	 * @return
	 */
	public List<CourseTeacherRelation> getCourseTeacherRelationsByTearcherId(String teacherId);
	
	
}
