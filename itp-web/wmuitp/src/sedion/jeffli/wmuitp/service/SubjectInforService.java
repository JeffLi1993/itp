package sedion.jeffli.wmuitp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sedion.jeffli.wmuitp.entity.SubjectInfor;
import sedion.jeffli.wmuitp.util.Page;


public interface SubjectInforService
{
	/**
	 * 批量添加
	 * @param courseChaptersid  进度id
	 * @param file xls文件
	 */
	int addSubjectInforByXls(String courseChaptersid,MultipartFile file);
	
	/**
	 * 获得所有题目
	 */
	List<SubjectInfor> getSubjectInfors();

	/**
	 * 通过Id获得题目
	 * @param subjectInforID
	 */
	SubjectInfor getSubjectInforBySubjectInforID(String subjectInforID);

	/**
	 * 保存或更新
	 * @param subjectInfor
	 * @param courseChapterID
	 * @param teacherInfoID
	 * @return
	 */
	int saveOrUpdateSubjectInfor(SubjectInfor subjectInfor,
							     String 		courseChapterID,
							     String 		teacherInfoID);

	/**
	 * 删除 支持批量删除
	 * @param subjectInforIdStr 题目ids
	 * @return
	 */
	int deleteSubjectInfors(String subjectInforIdStr);

	/**
	 * 获取 试题 带分页 带搜索
	 * @param page			分页
	 * @param pageParams	分页参数
	 */
	List<SubjectInfor> getSubjectInforsPages(Page<SubjectInfor> page, int[] pageParams,String subjectInfoName,String teacherInfoName,String courseName,String courserChapterName);
	/**
	 * 获取 试题 带分页 带搜索(老师端)
	 * @param page			分页
	 * @param pageParams	分页参数
	 */
	List<SubjectInfor> getSubjectInforsPagesTea(Page<SubjectInfor> page, int[] pageParams,String subjectInfoName,String teacherInfoName,String courseName,String courserChapterName);
	
	/**
	 * 获得全部题目或者按照指定内容搜索
	 * @param chapterName 进度名字
	 * @param teacherName 老师名字
	 * @return
	 */
	List<SubjectInfor> getSubjectInforsByChapterTeacherAndCourseMore(String chapterName,String teacherName,String courseName);

}
