package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.courseInfo.CourseInfoDetail;
import sedion.jeffli.wmuitp.util.Page;

public interface CourseInfoService
{
	/**
	 * 获得今天和今天以后的课程
	 */
	List<CourseInfo> getCourseInfoAfterToday();
	
	public List<CourseInfo> getCourseInfosPages(Page<CourseInfo> page, int[] pageParams);
	
	/**
	 * 获取课详情 
	 * @param page	分页对象
	 * @param pageParams 分页参数
	 */
	public List<CourseInfo> getCourseInfosPagesCourseInfoDetail(Page<CourseInfoDetail> page, int[] pageParams);
	/**
	 * 获取课详情 
	 * @param page	分页对象
	 * @param pageParams 分页参数
	 */
	public List<CourseInfo> getCourseInfosPagesCourseInfoDetailByTeaSession(Page<CourseInfoDetail> page, int[] pageParams);
	
	/**
	 * 获取课详情 
	 * @param page	分页对象
	 * @param pageParams 分页参数
	 * @param userLogin 教师用户
	 */
	public List<CourseInfo> getCourseInfosPagesTea(Page<CourseInfoDetail> page, int[] pageParams,UserLogin userLogin);
	
	/**
	 * 获取	课详情 	List
	 * @return
	 */
	public List<CourseInfo> getCourseInfos();
	
	/**
	 * 获取	课详情
	 * @param cIId			课详情ID
	 * @return
	 */
	public CourseInfo getCourseInfoById(String CIId);
	
	/**
	 * 更新      课详情
	 * @param courseInfo  	课详情
	 */
	public void updateCourseInfo(CourseInfo  courseInfo);
	
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
	 * @param classInfoIds				班级ID集合
	 */
	public int saveCourseInfo(CourseInfo courseInfo,String courseTeacherRelationID,String classInfoIds);

	/**
	 * 删除	课详情
	 * @param courseInfoStr	课详情ID集合
	 * @return
	 */
	public int deleteCourseInfos(String courseInfoStr);

	/**
	 * ======================================APP webView Service ===============================
	 */
	
	/**
	 * 获取课详情列表
	 * @param ulName
	 * @param ulPassword
	 * @return
	 */
	public List<CourseInfo> getCourseInfosByUserLogin(String ulName,String ulPassword);

	/**
	 * 开启课详情
	 * @param courseInfoId	课详情ID
	 */
	public int turnCIStateTureByCourseInfoId(int courseInfoId);

	/**
	 * 获取课详情
	 * @param classNameFrom	上课地点
	 * @return
	 */
	public CourseInfo getCourseInfoByCIPlace(String ciPlace);

	/**
	 * 获取课详情
	 * @param userLogin  登录信息
	 * @return
	 */
	public List<CourseInfo> getCourseInfosByTeacherLogin(UserLogin userLogin);
}
