package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.ExamStudent;
import sedion.jeffli.wmuitp.entity.PaperInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.courseInfo.SubjectDetail;
import sedion.jeffli.wmuitp.util.Page;

public interface PaperInfoService
{
	int getExamStatistical(String startDate,String endDate,String courseTeacherRelationID);

	/**
	 * 获得每个题目对的各个班级的正确率
	 * @param paperInfoId 考卷id
	 * @param subjectInfoId 题目id
	 * @return
	 */
	List<SubjectDetail> getPaperInfoStatisticDetail(String paperInfoId,String subjectInfoId);

	/**
	 *  保存或者修改试卷
	 * @param paperInfo 考卷实体
	 * @param courseID  课程id
	 * @param siId	题目ids
	 * @return
	 */
	int saveOrUpdatePaperInfo(PaperInfo paperInfo,
								String  courseInfoID,
								String  siIdStr);
	
	/**
	 * 获得全部考试卷
	 * @return
	 */
	List<PaperInfo> getPaperInfos();

	/**
	 * 获取考试卷
	 * @param paperInfoID
	 * @return
	 */
	PaperInfo getPaperInfoByPaperInfoId(String paperInfoId);

	/**
	 * 删除考试卷 批量
	 * @param paperInfoIDStr
	 * @return
	 */
	int deletePaperInfos(String paperInfoIDStr);

	/**
	 * 获取考试卷集合
	 * @param page			分页对象
	 * @param pageParams	分页参数
	 * @return
	 */
	List<PaperInfo> getPaperInfosPages(Page<PaperInfo> page, int[] pageParams);
	
	/**
	 * 获取考试卷集合
	 * @param page			分页对象
	 * @param pageParams	分页参数
	 * @return
	 */
	List<PaperInfo> getPaperInfosPagesTea(Page<PaperInfo> page, int[] pageParams);
	


	/**
	 * =======================================APP webView Service====================================
	 */
	
	/**
	 * 获取考试卷集合
	 * @param courseInfoId	课程ID
	 * @return
	 */
	List<PaperInfo> getPaperInfosByCourseInfoID(String courseInfoId);
	
	/**
	 * 获取全部考试卷集合 老师
	 */
	List<PaperInfo> getPaperInfosByTeaSession();
	
	/**
	 * 转换考试卷是否开启
	 * @param paperInfoId 考试卷id
	 */
	int turnPaperInfoSign(String paperInfoId);
	
	/**
	 * 获得测试统计
	 * @param paperInfoId 考试卷id
	 */
	List<SubjectDetail> getPaperInfoStatistic(String paperInfoId);

	/**
	 * 获得考生
	 * @param courseInfoId
	 * @param userLogin
	 * @return
	 */
	List<ExamStudent> getExamStudentsByCourseInfoID(String courseInfoId,UserLogin userLogin);
	/**
	 * 获得考生
	 * @param courseInfoId
	 * @param userLogin
	 * @return
	 */
	List<ExamStudent> getExamStudentsByCourseID(String courseId,
			UserLogin studentLoginFromHttpSession);
}
