package sedion.jeffli.wmuitp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.ExamStudentDAO;
import sedion.jeffli.wmuitp.dao.ExamStudentResultDAO;
import sedion.jeffli.wmuitp.dao.PaperInfoDAO;
import sedion.jeffli.wmuitp.dao.SubjectInforDAO;
import sedion.jeffli.wmuitp.dao.impl.ExamStudentDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.ExamStudentResultDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.PaperInfoDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.SubjectInforDAOImpl;
import sedion.jeffli.wmuitp.entity.ExamStudent;
import sedion.jeffli.wmuitp.entity.ExamStudentResult;
import sedion.jeffli.wmuitp.entity.PaperInfo;
import sedion.jeffli.wmuitp.entity.SubjectInfor;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.service.ExamStudentResultService;


//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class ExamStudentResultServiceImpl implements ExamStudentResultService 
{
	private static final String All_EXAM_STUDENT_RESULT	 		 = "FROM ExamStudentResult AS esr ";
	private static final String All_EXAM_STUDENT	 		 	 = "FROM ExamStudent AS es ";
	
	@Autowired
	private ExamStudentDAO 		 	examStudentDAO;
	@Autowired
	private PaperInfoDAO 	 		paperInfoDAO;
	@Autowired
	private SubjectInforDAO 	 	subjectInforDAO;
	@Autowired
	private ExamStudentResultDAO 	examStudentResultDAO;
	
	@Override
	public int showExamStatistical() {
		
		return 0;
	}
	
	@Override
	public List<ExamStudentResult> getExamStudentResultByExamStudent(ExamStudent examStudent)
	{
		StringBuilder hql= new StringBuilder(All_EXAM_STUDENT_RESULT);
		hql.append(CommonConstant.ONE_EQUALS_ONE);
		hql.append(" AND esr.examStudent=?");
		return examStudentResultDAO.getListByHQL(hql.toString(),examStudent);
	}
	//test
	public int saveExamStudentResultsByNewThread(String examStudentResultStr,String UesrId,String paperInfoId)
	{
//		System.out.println("paperInfoId====="+paperInfoId);
//		System.out.println("UesrId====="+UesrId);
//		System.out.println("examStudentResultStr====="+examStudentResultStr);
		PaperInfo paperInfo=paperInfoDAO.findById(Integer.valueOf(paperInfoId));
		//判断考试是否关闭  关闭提交失败
		if(!paperInfo.getPiSign().equals("T"))
		{
			return Constant.RESULT_FAIL;
		}
		//考生已经提交过试卷 提交失败
		StringBuilder hqles= new StringBuilder(All_EXAM_STUDENT);
		hqles.append(CommonConstant.ONE_EQUALS_ONE);
		hqles.append(" AND es.paperInfo.piId=");
		hqles.append(paperInfoId);
		hqles.append(" AND es.studentInfo.userLogin.ulId=");
		hqles.append(UesrId);
		ExamStudent esReault=examStudentDAO.findByHQL(hqles.toString());
		if(esReault==null||esReault.getEsSign().equals("F"))
		{
			return Constant.RESULT_FAIL;
		}
		
		StringBuilder examStuHql = new StringBuilder(All_EXAM_STUDENT + CommonConstant.ONE_EQUALS_ONE);
		examStuHql.append(" AND es.studentInfo.userLogin.ulId="+UesrId);
		examStuHql.append(" AND es.paperInfo.piId="+paperInfoId);
		ExamStudent examStudent = (ExamStudent)examStudentDAO.getUniqueResultByHQL(examStuHql.toString());//获取该考生

		
		String[] examStudentResultsString = examStudentResultStr.split("-");
		
		for (String examStudentResult : examStudentResultsString)
		{
			String[] examStuR = examStudentResult.split(Constant.COMMA);
			
			ExamStudentResult examStudentResultEntity = new ExamStudentResult();
			SubjectInfor subjectInfor = subjectInforDAO.findById(Integer.valueOf(examStuR[0]));
			examStudentResultEntity.setExamStudent(examStudent);
			examStudentResultEntity.setSubjectInfor(subjectInfor);
			examStudentResultEntity.setEsrContent(examStuR[1]);
			
			if(subjectInfor.getCsAnswer().equals(examStuR[1]))
				examStudentResultEntity.setEsrScore(CommonConstant.TRUE);
			else
				examStudentResultEntity.setEsrScore(CommonConstant.FALSE);
			examStudentResultDAO.updateEntity(examStudentResultEntity);//提交考试卷
		}
		if (examStudent.getEsSign().equals(CommonConstant.TRUE))
		{
			examStudent.setEsSign(CommonConstant.FALSE);
			examStudentDAO.updateEntity(examStudent);//证明考过考试
		}
		
		return Constant.RESULT_SUCCESS;
	}
	@Override
	public int saveExamStudentResults(String examStudentResultStr,UserLogin studentLogin,String paperInfoId)
	{
		System.out.println("paperInfoId====="+paperInfoId);
		PaperInfo paperInfo=paperInfoDAO.findById(Integer.valueOf(paperInfoId));
		//判断考试是否关闭  关闭提交失败
		if(!paperInfo.getPiSign().equals("T"))
		{
			return Constant.RESULT_FAIL;
		}
		//考生已经提交过试卷 提交失败
		StringBuilder hqles= new StringBuilder(All_EXAM_STUDENT);
		hqles.append(CommonConstant.ONE_EQUALS_ONE);
		hqles.append(" AND es.paperInfo.piId=");
		hqles.append(paperInfoId);
		hqles.append(" AND es.studentInfo.userLogin.ulId=");
		hqles.append(studentLogin.getUlId());
		ExamStudent esReault=examStudentDAO.findByHQL(hqles.toString());
		if(esReault==null||esReault.getEsSign().equals("F"))
		{
			return Constant.RESULT_FAIL;
		}
		
		StringBuilder examStuHql = new StringBuilder(All_EXAM_STUDENT + CommonConstant.ONE_EQUALS_ONE);
		
		if (studentLogin != null && studentLogin.getUlId() != 0)
		{
			examStuHql.append(" AND es.studentInfo.userLogin.ulId="+studentLogin.getUlId());
		}
		if (StringUtils.isNotEmpty(paperInfoId))
		{
			examStuHql.append(" AND es.paperInfo.piId="+paperInfoId);
		}
		ExamStudent examStudent = (ExamStudent)examStudentDAO.getUniqueResultByHQL(examStuHql.toString());//获取该考生

		
		String[] examStudentResultsString = examStudentResultStr.split("-");
		
		for (String examStudentResult : examStudentResultsString)
		{
			String[] examStuR = examStudentResult.split(Constant.COMMA);
			
			ExamStudentResult examStudentResultEntity = new ExamStudentResult();
			
			SubjectInfor subjectInfor = subjectInforDAO.findById(Integer.valueOf(examStuR[0]));
			
			examStudentResultEntity.setExamStudent(examStudent);
			examStudentResultEntity.setSubjectInfor(subjectInfor);
			examStudentResultEntity.setEsrContent(examStuR[1]);
			
			if(subjectInfor.getCsAnswer().equals(examStuR[1]))
				examStudentResultEntity.setEsrScore(CommonConstant.TRUE);
			else
				examStudentResultEntity.setEsrScore(CommonConstant.FALSE);

			examStudentResultDAO.updateEntity(examStudentResultEntity);//提交考试卷
		}
		
		if (examStudent.getEsSign().equals(CommonConstant.TRUE))
		{
			examStudent.setEsSign(CommonConstant.FALSE);
			examStudentDAO.updateEntity(examStudent);//证明考过考试
		}
		
		return Constant.RESULT_SUCCESS;
	}

	@Override
	public ExamStudent getExamStudentByUserLogin(UserLogin studentLogin,String paperInfoId)
	{
		StringBuilder examStuHql = new StringBuilder(All_EXAM_STUDENT + CommonConstant.ONE_EQUALS_ONE);
		
		if (studentLogin != null && studentLogin.getUlId() != 0)
		{
			examStuHql.append(" AND es.studentInfo.userLogin.ulId="+studentLogin.getUlId());
		}
		if (StringUtils.isNotEmpty(paperInfoId))
		{
			examStuHql.append(" AND es.paperInfo.piId="+paperInfoId);
		}
		
		return (ExamStudent)examStudentDAO.getUniqueResultByHQL(examStuHql.toString());
	}

}
