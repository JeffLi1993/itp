package sedion.jeffli.wmuitp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.util.Page;

public interface StudentInfoService
{
	
	
	/**
	 * ---------------------------学生信息----------------------------
	 */
	
	/**
	 *	初始化密码 
	 * @param studentInfoId 学生Id
	 */
	int initStudentInfoPassword(String studentInfoId);
	
	/**
	 * 获得满足搜索条件的学生
	 * @param page		               分页对象
	 * @param pageParams	   分页参数
	 * @param collegeName    学院
	 * @param professionName 专业
	 * @param studentNum	   学号
	 * @param studentName	   姓名
	 * @return
	 */
	List<StudentInfo> getStudentInfoPagesForSearch(Page<StudentInfo> page, int[] pageParams,String collegeName,String professionName,String studentNum,String studentName);
	
	/**
	 * 删除学生
	 * @param studentInfoIdStr  学生id
	 */
	int deleteStudentInfo(String studentInfoIdStr);
	
	/**
	 * 增加修改学生
	 * @param studentInfo  学生实体
	 * @param userLoginId  用户id
	 * @param classInfoId  班级id
	 */
	int saveOrUpdateStudentInfo(StudentInfo studentInfo,UserLogin userLogin,String classInfoId);
	
	/**
	 * 获得所有学生
	 * @param page			分页对象
	 * @param pageParams	分页参数
	 */
	List<StudentInfo> getStudentInfoPages(Page<StudentInfo> page, int[] pageParams);
	
	/**
	 * 获取	学生信息 
	 * @param studentInfoID 学生编号ID
	 */
	StudentInfo findStudentInfoById(String studentInfoID);

	/**
	 * 获取   	学生信息
	 * @param userLoginID
	 */
	public StudentInfo findStudentInfoByULID(String userLoginID);
	
	/**
	 * 通过xls文件批量导入 学生
	 * @param classInfoId 对应的班级Id
	 * @param xlsFile xls文件
	 * @return
	 */
	int addStudentByXls(String classInfoId,MultipartFile xlsFile);
	
}
