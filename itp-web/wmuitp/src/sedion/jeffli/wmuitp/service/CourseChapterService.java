package sedion.jeffli.wmuitp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sedion.jeffli.wmuitp.entity.CourseChapter;
import sedion.jeffli.wmuitp.util.Page;


public interface CourseChapterService
{

	/**
	 * 通过xls添加进度
	 * @param xlsFile xls文件
	 */
	int addCourseChapterByXls(MultipartFile xlsFile);
	
	/**
	 * 删除课程章节
	 * @param courseChapterIdStr 课程章节Id
	 */
	int deleteCourseChapter(String courseChapterIdStr);
	
	/**
	 * 保存修改课程章节
	 * @param courseID  课程id
	 * @param courseChapter	课程章节实体
	 */
	int saveOrUpdateCourseChapter(String courseID,CourseChapter courseChapter);
	
	/**
	 * 通过课程章节Id获得课程
	 * @param courseChapterID 课程章节Id
	 */
	CourseChapter getCourseChapterById(String courseChapterID);

	/**
	 * 获得所有课程章节
	 * @return
	 */
	List<CourseChapter> getCourseChapters();
	
	/**
	 * 获得对于老师所有课程章节
	 * @return
	 */
	List<CourseChapter> getCourseChaptersByTeaSession(String courseName);

	/**
	 * 获得根据基本课程名搜索后的内容 <br> 
	 * 如果基本课程为null 这返回所有   
	 * @param courseName 基本课程名称
	 * @return
	 */
	List<CourseChapter> getCourseChaptersBySearch(String courseName);

	/**
	 * 获取	基础课程章节
	 * @param page			分页
	 * @param pageParams	分页参数
	 * @return
	 */
	List<CourseChapter> getCourseChaptersPages(Page<CourseChapter> page, int[] pageParams);
	
	/**
	 * 获取	基础课程章节
	 * @param page			分页
	 * @param pageParams	分页参数
	 * @return
	 */
	List<CourseChapter> getCourseChaptersPagesSearchByCourseName(Page<CourseChapter> page, int[] pageParams,String courseName);

}
