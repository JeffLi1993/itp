package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.ExamStudent;
import sedion.jeffli.wmuitp.entity.ExamStudentResult;
import sedion.jeffli.wmuitp.entity.UserLogin;


public interface ExamStudentResultService
{
	int showExamStatistical();
	
	/**
	 * 通过examStudent获得ExamStudentResult
	 * @return
	 */
	List<ExamStudentResult> getExamStudentResultByExamStudent(ExamStudent examStudent);
	
	/**
	 * 保存
	 * @param examStudentResultStr
	 * @param studentLogin
	 */
	int saveExamStudentResults(String examStudentResultStr,UserLogin studentLogin,String paperInfoId);

	/**
	 * 根据useLlogin 获取ExamStu
	 * @return
	 */
	ExamStudent getExamStudentByUserLogin(UserLogin studentLogin,String paperInfoId);

	/**
	 * 通过新线程保存
	 * @param examStudentResultStr
	 * @param UesrId
	 * @param paperInfoId
	 * @return
	 */
	int saveExamStudentResultsByNewThread(String examStudentResultStr,String UesrId,String paperInfoId);
}
