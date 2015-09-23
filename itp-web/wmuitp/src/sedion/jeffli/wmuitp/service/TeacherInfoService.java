package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;

import org.springframework.web.multipart.MultipartFile;

import sedion.jeffli.wmuitp.util.Page;


public interface TeacherInfoService
{
	int addTeacherByXls(MultipartFile file);
	
	/**
	 * 初始化教师账号密码
	 * @param teacherInfoId  教师密码
	 */
	int initTeacherInfoPassword(String teacherInfoId);
	
	/**
	 * 获得满足搜索条件的的老师(单个老师)
	 * @param collegeName    学院名称
	 * @param professionName 专业
	 * @param teacherName    教师姓名	
	 * @param jobName		   教师职称
	 * @return
	 */
	public List<TeacherInfo> getLessTeacherInfoForSearch(String collegeName,String professionName,String teacherName,String jobName);
	
	/**
	 * 获得满足搜索条件的的老师 
	 * @param page		   	   分页对象
	 * @param pageParams  	   分页参数
	 * @param collegeName    学院名称
	 * @param professionName 专业
	 * @param teacherName    教师姓名	
	 * @param jobName		   教师职称
	 * @return
	 */
	public List<TeacherInfo> getTeacherInfosPagesForSearch(Page<TeacherInfo> page, int[] pageParams,String collegeName,String professionName,String teacherName,String jobName);
	
	/**
	 *  查找老师
	 * @param teacherInfoId 老师id
	 */
	TeacherInfo getTeacherInfoByTeacherInfoID(String teacherInfoId);
	
	/**
	 *  查找老师
	 * @param userLogin 用户
	 */
	TeacherInfo getTeacherInfoByTeacherInfoUserLogin(UserLogin userLogin);
	
	/**
	 * 删除老师
	 * @param teacherInfoIDStr 要删除老师的id
	 */
	int deleteTeacherInfo(String teacherInfoIDStr);
	
	/**
	 * 增加修改老师
	 * @param teacherInfo 		老师实体
	 * @param userLogin  		 用户
	 * @param professionInfoID  对应的专业id
	 */
	int saveOrUpdateTeacherInfo(TeacherInfo teacherInfo,
								UserLogin userLogin,
								String professionInfoID,
								MultipartFile file);
	
	/**
	 * 获得所有老师
	 * @param page			分页对象
	 * @param pageParams	分页参数
	 */
	List<TeacherInfo> getTeacherInfosPages(Page<TeacherInfo> page, int[] pageParams);
	
	/**
	 * @param 	userLoginId	 登录ID
	 * @return	TeacherInfo 
	 */
	public TeacherInfo getTeacherInfoIDByOBJ(String userLoginId);

	/**
	 * @return
	 */
	List<TeacherInfo> getTeacherInfos();

}
