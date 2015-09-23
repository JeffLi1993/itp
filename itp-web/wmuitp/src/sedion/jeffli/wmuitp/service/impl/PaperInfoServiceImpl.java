package sedion.jeffli.wmuitp.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Alignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.ClassInfoDAO;
import sedion.jeffli.wmuitp.dao.CourseInfoDAO;
import sedion.jeffli.wmuitp.dao.CourseTeacherRelationDAO;
import sedion.jeffli.wmuitp.dao.ExamStudentDAO;
import sedion.jeffli.wmuitp.dao.ExamStudentResultDAO;
import sedion.jeffli.wmuitp.dao.PaperInfoDAO;
import sedion.jeffli.wmuitp.dao.PaperSubjectRelationDAO;
import sedion.jeffli.wmuitp.dao.StudentCourseRelationDAO;
import sedion.jeffli.wmuitp.dao.StudentInfoDAO;
import sedion.jeffli.wmuitp.dao.SubjectInforDAO;
import sedion.jeffli.wmuitp.dao.TeacherInfoDAO;
import sedion.jeffli.wmuitp.entity.ClassInfo;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.ExamStudent;
import sedion.jeffli.wmuitp.entity.ExamStudentResult;
import sedion.jeffli.wmuitp.entity.PaperInfo;
import sedion.jeffli.wmuitp.entity.PaperSubjectRelation;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.SubjectInfor;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.courseInfo.SubjectDetail;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.PaperInfoService;
import sedion.jeffli.wmuitp.util.DateUtil;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;

//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class PaperInfoServiceImpl implements PaperInfoService 
{

	private static final String All_PAPER_INFOS = "FROM PaperInfo AS pi ";
	private static final String All_STUDENT_INFO = "FROM StudentInfo AS si ";
	private static final String ALL_PAPER_SUBJECT_RELATION = "FROM PaperSubjectRelation AS psr ";
	private static final String All_STUDENT_COURSE_RELATION = "FROM StudentCourseRelation AS scr ";
	private static final String All_EXAM_STUDENT = "FROM ExamStudent AS es ";
	private static final String All_EXAM_STUDENT_RESULT = "FROM ExamStudentResult AS esr ";
	private static final String All_CLASS_INFO = "FROM ClassInfo AS ci ";
	private static final String All_COURSE_INFO = "FROM CourseInfo AS ci ";

	@Autowired
	private ClassInfoDAO classInfoDAO;
	@Autowired
	private StudentInfoDAO studentInfoDAO;
	@Autowired
	private PaperInfoDAO paperInfoDAO;
	@Autowired
	private CourseInfoDAO courseInfoDAO;
	@Autowired
	private TeacherInfoDAO teacherInfoDAO;
	@Autowired
	private ExamStudentDAO examStudentDAO;
	@Autowired
	private ExamStudentResultDAO examStudentResultDAO;
	@Autowired
	private SubjectInforDAO subjectInforDAO;
	@Autowired
	private PaperSubjectRelationDAO paperSubjectRelationDAO;
	@Autowired
	private StudentCourseRelationDAO studentCourseRelationDAO;
	@Autowired
	private CourseTeacherRelationDAO courseTeacherRelationDAO;
	@Autowired
	public HttpSession session;
	
	@Override
	public int getExamStatistical(String startDate,String endDate,String courseTeacherRelationID)
	{
		UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
		
		StringBuilder hql = new StringBuilder();
		hql.append(All_COURSE_INFO).append(CommonConstant.ONE_EQUALS_ONE);
		hql.append(" AND ci.courseTeacherRelation.ctrId=").append(courseTeacherRelationID);
		hql.append(" AND ci.ciDateTime>='").append(startDate).append("' ");
		hql.append(" AND ci.ciDateTime<='").append(endDate).append("' ");
		List<CourseInfo> courseInfos = courseInfoDAO.getListByHQL(hql.toString());//获得对应的所有课程
		if(courseInfos.size()==0)
			return Constant.RESULT_FAIL;
		List<PaperInfo> paperInfos =new ArrayList<>();
		List<StudentInfo> studentInfos =new ArrayList<>();
		Set<ClassInfo> classInfos=new HashSet(0);
		for(CourseInfo courseInfo:courseInfos)
		{
			paperInfos.addAll(courseInfo.getPaperInfos());
			Iterator<StudentCourseRelation> iterator=courseInfo.getStudentCourseRelations().iterator();
			while(iterator.hasNext())
				studentInfos.add(iterator.next().getStudentInfo());
		}
		for(StudentInfo studentInfo:studentInfos)
			classInfos.add(studentInfo.getClassInfo());
		/******************/
		OutputStream os = null;
		WritableWorkbook book = null;
		try {
			File f = new File(Constant.getCachePath(), ul.getUlName() + ".xls");
			if (f.exists())
				f.delete();
			// f.createTempFile(ul.getUlName(), ".xls");
			os = new FileOutputStream(Constant.getCachePath() + ul.getUlName() + ".xls");
			book = Workbook.createWorkbook(os);
			// 设置字体 
	        jxl.write.WritableFont wfont = new jxl.write.WritableFont(WritableFont.createFont("楷书"), 12); 
			WritableCellFormat wc = new WritableCellFormat(wfont);
			// 设置居中 
	        wc.setAlignment(Alignment.CENTRE); 
	        // 设置边框线 
	        wc.setBorder(Border.ALL, BorderLineStyle.THIN); 
			
			Iterator<ClassInfo> classInfoIterator  =classInfos.iterator();
			Integer classNum = 0;
			while(classInfoIterator.hasNext())
			{
				ClassInfo classInfo = classInfoIterator.next();
				WritableSheet ws = book.createSheet(classInfo.getCiName(), classNum);
				ws.setColumnView(0, 20);
				hql = new StringBuilder(All_STUDENT_INFO).append(CommonConstant.ONE_EQUALS_ONE)
						.append(" AND si.classInfo.ciId=").append(classInfo.getCiId());
				List<StudentInfo> studentInfoss = studentInfoDAO.getListByHQL(hql.toString());
				
				Label label3 = new Label(0, 1, "题目个数",wc);
				ws.addCell(label3);
				//写入第一列信息
				for(int lineNum=0;lineNum<studentInfoss.size();lineNum++)
				{
					StringBuilder content = new StringBuilder(studentInfoss.get(lineNum).getSiNum())
								.append("(").append(studentInfoss.get(lineNum).getSiRealName()).append(")");
					//					列 		行			内容
					Label label1 = new Label(0, lineNum+2, content.toString(),wc);
					ws.addCell(label1);
				}
				int colNum = 1;
				for(PaperInfo paperInfo : paperInfos)
				{
					ws.setColumnView(colNum,10);
					int errorNum =0;
					//写入考生答题情况
					for(int i=0;i<studentInfoss.size();i++)
					{
						hql = new StringBuilder(All_EXAM_STUDENT).append(CommonConstant.ONE_EQUALS_ONE)
								.append(" AND es.paperInfo.piId=").append(paperInfo.getPiId())
								.append(" AND es.studentInfo.siId=").append(studentInfoss.get(i).getSiId());
						ExamStudent examStudent =examStudentDAO.getUniqueResultByHQL(hql.toString());
						if(examStudent==null)
						{
							//空白信息
							Label label1 = new Label(colNum, i+2,"",wc);
							ws.addCell(label1);
							System.out.println("跳过"+studentInfoss.get(i).getSiRealName());
							errorNum++;
							continue ;
						}
						Integer rightNum = 0 ;
						Iterator<ExamStudentResult> iterator = examStudent.getExamStudentResults().iterator();
						
						while(iterator.hasNext())
						{
							if(iterator.next().getEsrScore().equals("T"))
								rightNum++;
						}
						Label label1 = new Label(colNum, i+2,rightNum.toString(),wc);
						ws.addCell(label1);
					}
					
					//如果errornum和学生人数不相同表面，该班级参加这场考试//写入首行 
					if(errorNum!=studentInfoss.size())
					{
						//写入 第一行 第二行
						Label lable2 =new Label(colNum, 0, paperInfo.getPiName(),wc);
						ws.addCell(lable2);
						Label lable4 =new Label(colNum, 1, String.valueOf(paperInfo.getPaperSubjectRelations().size()),wc);
						ws.addCell(lable4);
					}else{
						Label lable2 =new Label(colNum, 0, "",wc);
						ws.addCell(lable2);
						Label lable4 =new Label(colNum, 1, "",wc);
						ws.addCell(lable4);
						continue ;
					}
					colNum++;	
				}
				classNum++;
			}
			// 写入
			book.write();
		} catch (FileNotFoundException e) {
			System.out.println("打开文件失败");
			throw new EntityException(" PaperInfoServiceImpl getExamStatistical(...) Error!!", e);
		} catch (IOException e) {
			System.out.println("打开文件失败");
			throw new EntityException(" PaperInfoServiceImpl getExamStatistical(...) Error!!", e);
		} catch (RowsExceededException e) {
			System.out.println("添加单元格失败");
			throw new EntityException(" PaperInfoServiceImpl getExamStatistical(...) Error!!", e);
		} catch (WriteException e) {
			System.out.println("添加单元格失败");
			throw new EntityException(" PaperInfoServiceImpl getExamStatistical(...) Error!!", e);
		} finally {
			try {
				System.out.println("关闭流");
				if (book != null)
					book.close();
				if (os != null)
					os.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/******************/
		return Constant.RESULT_SUCCESS;
	}

	

	@Override
	public List<PaperInfo> getPaperInfosByTeaSession() 
	{

		List<PaperInfo> results = new ArrayList<>();
		UserLogin userLogin = AdminUtil.getUserLoginFromHttpSession(session);
		StringBuilder hql = new StringBuilder(All_PAPER_INFOS)
				.append(CommonConstant.ONE_EQUALS_ONE)
				.append(" AND pi.teacherInfo.userLogin.ulId=").append(userLogin.getUlId())
				.append(" ORDER BY pi.courseInfo.ciDateTime DESC");
		results = paperInfoDAO.getListByHQL(hql.toString());
		return results;
	}
	@Override
	public List<SubjectDetail> getPaperInfoStatisticDetail(String paperInfoId,
			String subjectInfoId) 
	{
		List<SubjectDetail> answerDetail = new ArrayList<>();
		// 获得全部考生
		StringBuilder hql = new StringBuilder(All_EXAM_STUDENT)
				.append(CommonConstant.ONE_EQUALS_ONE)
				.append(" AND es.paperInfo.piId=").append(paperInfoId);
		List<ExamStudent> examStudents = examStudentDAO.getListByHQL(hql
				.toString());
		// 获得全部班级
		hql = new StringBuilder(All_CLASS_INFO)
				.append(CommonConstant.ONE_EQUALS_ZERO);
		for (ExamStudent examStudent : examStudents) 
		{
			hql.append(" OR ci.ciId=");
			hql.append(examStudent.getStudentInfo().getClassInfo().getCiId());
		}
		List<ClassInfo> classInfos = classInfoDAO.getListByHQL(hql.toString());
		for (ClassInfo classInfo : classInfos) {
			SubjectDetail result = new SubjectDetail();
			hql = new StringBuilder(All_EXAM_STUDENT_RESULT);
			hql.append(CommonConstant.ONE_EQUALS_ONE);
			hql.append(" AND esr.subjectInfor.siId=");
			hql.append(subjectInfoId);
			hql.append(" AND esr.examStudent.studentInfo.classInfo.ciId=");
			hql.append(classInfo.getCiId());
			int allStuentNum = examStudentResultDAO
					.getListByHQL(hql.toString()).size();
			if (allStuentNum == 0)
				continue;
			hql.append(" AND esr.esrContent=?");
			int AStuentNum = examStudentResultDAO.getListByHQL(hql.toString(),
					"A").size();
			int BStuentNum = examStudentResultDAO.getListByHQL(hql.toString(),
					"B").size();
			int CStuentNum = examStudentResultDAO.getListByHQL(hql.toString(),
					"C").size();
			int DStuentNum = examStudentResultDAO.getListByHQL(hql.toString(),
					"D").size();
			int EStuentNum = examStudentResultDAO.getListByHQL(hql.toString(),
					"E").size();
			DecimalFormat df = new DecimalFormat("#0.0");
			if (AStuentNum == 0)
				result.setArate("0");
			else
				result.setArate(df.format(100.0 * AStuentNum / allStuentNum));
			if (BStuentNum == 0)
				result.setBrate("0");
			else
				result.setBrate(df.format(100.0 * BStuentNum / allStuentNum));
			if (CStuentNum == 0)
				result.setCrate("0");
			else
				result.setCrate(df.format(100.0 * CStuentNum / allStuentNum));
			if (DStuentNum == 0)
				result.setDrate("0");
			else
				result.setDrate(df.format(100.0 * DStuentNum / allStuentNum));
			if (EStuentNum == 0)
				result.setErate("0");
			else
				result.setErate(df.format(100.0 * EStuentNum / allStuentNum));
			result.setClassInfoName(classInfo.getCiName());
			result.setSubjectInfor(subjectInforDAO.findById(Integer
					.valueOf(subjectInfoId)));
			answerDetail.add(result);
		}
		return answerDetail;
	}

	@Override
	public List<SubjectDetail> getPaperInfoStatistic(String paperInfoId) 
	{
		List<SubjectDetail> answerDetail = new ArrayList<>();

		List<PaperSubjectRelation> paperSubjectRelations = paperSubjectRelationDAO
				.getPaperSubjectRelationByPaperInfoId(paperInfoId);
		System.out.println(paperSubjectRelations);
		for (int i = 0; i < paperSubjectRelations.size(); i++) 
		{
			SubjectDetail result = new SubjectDetail();
			StringBuilder hql = new StringBuilder(All_EXAM_STUDENT_RESULT).append(CommonConstant.ONE_EQUALS_ONE)
						.append(" AND esr.subjectInfor.siId=").append(paperSubjectRelations.get(i).getSubjectInfor().getSiId())
						.append(" AND esr.examStudent.paperInfo.piId=").append(paperInfoId);
			int allStuentNum = examStudentResultDAO.getListByHQL(hql.toString()).size();
			if (allStuentNum == 0)
				continue;
			hql.append(" AND esr.esrContent=?");
			int AStuentNum = examStudentResultDAO.getListByHQL(hql.toString(), "A").size();
			int BStuentNum = examStudentResultDAO.getListByHQL(hql.toString(), "B").size();
			int CStuentNum = examStudentResultDAO.getListByHQL(hql.toString(), "C").size();
			int DStuentNum = examStudentResultDAO.getListByHQL(hql.toString(), "D").size();
			int EStuentNum = examStudentResultDAO.getListByHQL(hql.toString(), "E").size();
			// 格式化数字
			DecimalFormat df = new DecimalFormat("#0.0");
			if (AStuentNum == 0)
				result.setArate("0");
			else
				result.setArate(df.format(100.0 * AStuentNum / allStuentNum));
			if (BStuentNum == 0)
				result.setBrate("0");
			else
				result.setBrate(df.format(100.0 * BStuentNum / allStuentNum));
			if (CStuentNum == 0)
				result.setCrate("0");
			else
				result.setCrate(df.format(100.0 * CStuentNum / allStuentNum));
			if (DStuentNum == 0)
				result.setDrate("0");
			else
				result.setDrate(df.format(100.0 * DStuentNum / allStuentNum));
			if (EStuentNum == 0)
				result.setErate("0");
			else
				result.setErate(df.format(100.0 * EStuentNum / allStuentNum));
			result.setSubjectInfor(paperSubjectRelations.get(i)
					.getSubjectInfor());
			answerDetail.add(result);
		}
		return answerDetail.size()==0?null:answerDetail;
	}

	@Override
	public int turnPaperInfoSign(String paperInfoId) 
	{
		PaperInfo paperInfo = paperInfoDAO.findById(paperInfoId);

		if (StringUtils.isNotBlank(paperInfo.getPiSign()))
		{
			switch (paperInfo.getPiSign()) 
			{
			case "T":
				paperInfo.setPiSign("F");
				break;
			case "F":
				paperInfo.setPiSign("T");
				break;
			default:
				paperInfo.setPiSign("F");
				break;
			}
		} 
		else
		{
			paperInfo.setPiSign("F");
		}

		try 
		{
			paperInfoDAO.updateEntity(paperInfo);
		}
		catch (Exception e)
		{
			throw new EntityException(
					"PaperInfoServiceImpl.turnPaperInfoSign(...)  error ", e);
		}
		return Constant.RESULT_SUCCESS;
	}

	@Override
	public int saveOrUpdatePaperInfo(PaperInfo paperInfo, String courseInfoID,
			String siIdStr)
	{
		siIdStr = siIdStr.trim();// 去除空格

		CourseInfo courseInfo = courseInfoDAO.findById(courseInfoID);
		System.out.println(courseInfo);
		paperInfo.setCourseInfo(courseInfo);
		paperInfo.setTeacherInfo(courseInfo.getCourseTeacherRelation()
				.getTeacherInfo());
		paperInfo.setPiSign(CommonConstant.FALSE);

		if (paperInfo.getPiId() != null && paperInfo.getPiId() > 0)// 注册考生表
		{
			try
			{
				paperInfoDAO.updateEntity(paperInfo);
			} 
			catch (Exception e)
			{
				throw new EntityException("saveOrUpdatePaperInfo Error!! ", e);
			}
		} 
		else
		{
			try
			{
				paperInfoDAO.updateEntity(paperInfo);
			} 
			catch (Exception e)
			{
				throw new EntityException("saveOrUpdatePaperInfo Error!! ", e);
			}

			List<StudentCourseRelation> studentCourseRelations = new ArrayList<>();

			StringBuilder stuCourseRelationHql = new StringBuilder(
					All_STUDENT_COURSE_RELATION)
					.append(CommonConstant.ONE_EQUALS_ONE);

			try 
			{
				stuCourseRelationHql.append(" AND scr.courseInfo.ciId=")
						.append(courseInfoID);

				studentCourseRelations = studentCourseRelationDAO
						.getListByHQL(stuCourseRelationHql.toString());
			} 
			catch (Exception e)
			{
				throw new EntityException("saveOrUpdatePaperInfo Error!!", e);
			}

			for (StudentCourseRelation studentCourseRelation : studentCourseRelations) 
			{
				System.out.println("studentCourseRelation.getStudentInfo()"
						+ studentCourseRelation.getStudentInfo().getSiNum());
				ExamStudent examStudent = new ExamStudent();

				examStudent.setPaperInfo(paperInfo);
				examStudent.setStudentInfo(studentCourseRelation
						.getStudentInfo());
				examStudent.setEsSign(CommonConstant.TRUE);

				try 
				{
					examStudentDAO.updateEntity(examStudent);// 注册考生表
				} 
				catch (Exception e)
				{
					throw new EntityException("saveOrUpdatePaperInfo Error!!",e);
				}
			}

		}

		try 
		{
			List<PaperSubjectRelation> paperSubjectRelationList = paperSubjectRelationDAO
					.getPaperSubjectRelationByPaperInfoId(paperInfo.getPiId()
							.toString());
			for (PaperSubjectRelation paperSubjectRelation : paperSubjectRelationList) 
			{
				paperSubjectRelationDAO.turnTransient(paperSubjectRelation);// 删除原来试卷和试题关系
			}
		} 
		catch (Exception e) 
		{
			throw new EntityException("saveOrUpdatePaperInfo Error!!", e);
		}

		if (StringUtils.isNotEmpty(siIdStr)) 
		{
			String[] siIds = siIdStr.split(Constant.COMMA);
			for (String siId : siIds)
			{
				StringBuilder hql = new StringBuilder(
						ALL_PAPER_SUBJECT_RELATION)
						.append(CommonConstant.ONE_EQUALS_ONE);

				if (StringUtils.isNotEmpty(siId))
				{
					hql.append(" AND psr.subjectInfor.siId=").append(siId);
				}
				hql.append(" AND psr.paperInfo.piId=").append(
						paperInfo.getPiId());

				PaperSubjectRelation paperSubjectRelation2 = paperSubjectRelationDAO
						.findByHQL(hql.toString());

				if (paperSubjectRelation2 == null) 
				{
					PaperSubjectRelation paperSubjectRelation = new PaperSubjectRelation();
					paperSubjectRelation.setPaperInfo(paperInfo);

					SubjectInfor subjectInfor = subjectInforDAO
							.findById(Integer.valueOf(siId));
					paperSubjectRelation.setSubjectInfor(subjectInfor);

					paperSubjectRelation.setPsrTime(DateUtil.getDateTime()
							.toString());
					paperSubjectRelationDAO.updateEntity(paperSubjectRelation);// 添加试卷和试题关系
				}
			}
		}

		return Constant.RESULT_SUCCESS;
	}

	@Override
	public List<PaperInfo> getPaperInfos()
	{
		return paperInfoDAO.getListByHQL(All_PAPER_INFOS);
	}

	@Override
	public PaperInfo getPaperInfoByPaperInfoId(String paperInfoId)
	{
		return paperInfoDAO.findById(Integer.valueOf(paperInfoId));
	}

	@Override
	public int deletePaperInfos(String paperInfoIDStr)
	{
		String[] paperInfoIDStrs;

		if (paperInfoIDStr != null && !paperInfoIDStr.equals(""))
		{
			paperInfoIDStrs = paperInfoIDStr.split(Constant.LINE);
			for (String paperInfoID : paperInfoIDStrs) 
			{
				try 
				{
					String hql = ALL_PAPER_SUBJECT_RELATION
							+ CommonConstant.ONE_EQUALS_ONE;
					if (paperInfoID != null && !paperInfoID.equals(""))
					{
						hql += " AND psr.paperInfo.piId=?";
					}

					List<PaperSubjectRelation> paperSubjectRelations = paperSubjectRelationDAO
							.getListByHQL(hql, Integer.valueOf(paperInfoID));
					if (paperSubjectRelations != null) 
					{
						for (PaperSubjectRelation paperSubjectRelation : paperSubjectRelations)
						{
							paperSubjectRelationDAO
									.turnTransient(paperSubjectRelation);// 删除考试和试题关系
						}
					}
				} 
				catch (Exception e)
				{
					throw new EntityException(
							"PaperInfoServiceImpl.deletePaperInfos(...)  error ",
							e);
				}

				try 
				{
					PaperInfo paperInfo = paperInfoDAO.findById(Integer
							.valueOf(paperInfoID));
					paperInfoDAO.turnTransient(paperInfo);// 删除考试对象
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}

			}

			return Constant.RESULT_SUCCESS;
		}

		return Constant.RESULT_FAIL;
	}

	@Override
	public List<PaperInfo> getPaperInfosPages(Page<PaperInfo> page,
			int[] pageParams) 
	{
		List<PaperInfo> results = new ArrayList<>();

		StringBuffer resultsHQL = new StringBuffer(All_PAPER_INFOS);

		try 
		{
			results = paperInfoDAO.findByPage(resultsHQL.toString(),
					pageParams[0], pageParams[1]);

			page.setTotalCount(paperInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);

		}
		catch (Exception e)
		{
			throw new EntityException(
					"PaperInfoServiceImpl.getPaperInfosPages(...)  error ", e);
		}

		return results;
	}

	@Override
	public List<PaperInfo> getPaperInfosPagesTea(Page<PaperInfo> page,
			int[] pageParams) 
	{
		List<PaperInfo> results = new ArrayList<>();

		StringBuffer resultsHQL = new StringBuffer(All_PAPER_INFOS)
				.append(CommonConstant.ONE_EQUALS_ONE);

		try
		{
			UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
			resultsHQL.append(" AND pi.courseInfo.courseTeacherRelation.teacherInfo.userLogin.ulId=").append(ul.getUlId());
			resultsHQL.append(" ORDER BY pi.piId DESC");
			
			results = paperInfoDAO.findByPage(resultsHQL.toString(),pageParams[0], pageParams[1]);
			page.setTotalCount(paperInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);

		} 
		catch (Exception e)
		{
			throw new EntityException(
					"PaperInfoServiceImpl.getPaperInfosPages(...)  error ", e);
		}

		return results;
	}

	@Override
	public List<PaperInfo> getPaperInfosByCourseInfoID(String courseInfoId) 
	{

		List<PaperInfo> results = new ArrayList<>();

		StringBuilder hql = new StringBuilder(All_PAPER_INFOS)
				.append(CommonConstant.ONE_EQUALS_ONE);

		try 
		{

			if (courseInfoId != null && !courseInfoId.equals("")) 
			{
				hql.append(" AND pi.courseInfo.ciId=").append(courseInfoId);
			}

			results = paperInfoDAO.getListByHQL(hql.toString());
		}
		catch (Exception e) 
		{
			throw new EntityException(
					"PaperInfoServiceImpl.getPaperInfosByCourseInfoID(...)  error ",e);
		}

		return results;
	}

	@Override
	public List<ExamStudent> getExamStudentsByCourseInfoID(String courseInfoId,
			UserLogin userLogin) 
	{
		List<ExamStudent> results = new ArrayList<>();
		StringBuilder hql = new StringBuilder(All_EXAM_STUDENT)
				.append(CommonConstant.ONE_EQUALS_ONE);
		try 
		{
			hql.append(" AND es.paperInfo.courseInfo.ciId=").append(
					courseInfoId);
			hql.append(" AND es.studentInfo.userLogin.ulId=").append(
					userLogin.getUlId());
			results = examStudentDAO.getListByHQL(hql.toString());
		}
		catch (Exception e)
		{
			throw new EntityException(
					"PaperInfoServiceImpl.getPaperInfosByCourseInfoID(...)  error ",e);
		}
		return results;
	}
	
	@Override
	public List<ExamStudent> getExamStudentsByCourseID(String courseId,
			UserLogin userLogin) 
	{
		List<ExamStudent> results = new ArrayList<>();
		StringBuilder hql = new StringBuilder(All_EXAM_STUDENT)
				.append(CommonConstant.ONE_EQUALS_ONE);
		try 
		{
			hql.append(" AND es.paperInfo.courseInfo.courseTeacherRelation.course.CId=").append(
					courseId);
			hql.append(" AND es.studentInfo.userLogin.ulId=").append(
					userLogin.getUlId());
			results = examStudentDAO.getListByHQL(hql.toString());
		}
		catch (Exception e)
		{
			throw new EntityException(
					"PaperInfoServiceImpl.getExamStudentsByCourseID(...)  error ",e);
		}
		
		return results;
	}
}
