package sedion.jeffli.wmuitp.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.ClassInfoDAO;
import sedion.jeffli.wmuitp.dao.CourseInfoDAO;
import sedion.jeffli.wmuitp.dao.StudentCourseRelationDAO;
import sedion.jeffli.wmuitp.dao.StudentInfoDAO;
import sedion.jeffli.wmuitp.dao.UserLoginDAO;
import sedion.jeffli.wmuitp.entity.ClassInfo;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.courseInfo.StudentPresent;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.StudentCourseRelationServcie;
import sedion.jeffli.wmuitp.util.DateUtil;

//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class StudentCourseRelationServcieImpl implements
		StudentCourseRelationServcie
{
	private static final String ALL_STUDENT_INFOS = "FROM StudentInfo AS si ";
	private static final String All_STUDENT_COURSE_RELATION = "FROM StudentCourseRelation AS scr ";
	private static final String All_CLASS_INFOS = "FROM ClassInfo AS ci ";

	@Autowired
	private UserLoginDAO userLoginDAO;
	@Autowired
	private CourseInfoDAO courseInfoDAO;
	@Autowired
	private StudentInfoDAO studentInfoDAO;
	@Autowired
	private StudentCourseRelationDAO studentCourseRelationDAO;
	@Autowired
	private ClassInfoDAO classInfoDAO;

	@Override
	public List<StudentInfo> getAbsentDetail(String courseInfoId, String classId) 
	{
		StringBuilder hql = new StringBuilder(All_STUDENT_COURSE_RELATION);
		hql.append(CommonConstant.ONE_EQUALS_ONE);
		hql.append(" AND scr.courseInfo.ciId=");
		hql.append(courseInfoId);
		hql.append(" AND scr.studentInfo.classInfo.ciId=");
		hql.append(classId);
		hql.append(" AND scr.scrPresent='F' ");
		List<StudentCourseRelation> studentAbsent = studentCourseRelationDAO
				.getListByHQL(hql.toString());
		List<StudentInfo> StudentInfo = new ArrayList<>();
		for (int i = 0; i < studentAbsent.size(); i++)
			// 转存
			StudentInfo.add(studentAbsent.get(i).getStudentInfo());
		return StudentInfo;
	}

	@Override
	public List<StudentPresent> getAttendance(String courseInfoId)
	{
		
		List<StudentCourseRelation> studentCourseRelations = studentCourseRelationDAO
				.getStudentCourseRelationByCourseInfoId(courseInfoId);
		// 获得班级
		StringBuilder hql2 = new StringBuilder(All_CLASS_INFOS).append(" WHERE 1=0 ");
		for (StudentCourseRelation studentCourseRelation : studentCourseRelations) 
		{
			hql2.append(" OR ci.ciId=");
			hql2.append(studentCourseRelation.getStudentInfo().getClassInfo()
					.getCiId());
		}
		
		List<ClassInfo> classInfos = classInfoDAO.getListByHQL(hql2.toString());
		List<StudentPresent> courseMessage = new ArrayList<>();
		
		for (ClassInfo classInfo : classInfos)
		{
			StudentPresent classinfo1 = new StudentPresent();
			classinfo1.setClassStudentNumber(classInfo.getCiStudentSum());
			classinfo1.setClassName(classInfo.getCiName());
			// 已到人数
			StringBuilder hql = new StringBuilder(All_STUDENT_COURSE_RELATION);
			hql.append(CommonConstant.ONE_EQUALS_ONE);
			hql.append(" AND scr.courseInfo.ciId=");
			hql.append(courseInfoId);
			hql.append(" AND scr.studentInfo.classInfo.ciId=");
			hql.append(classInfo.getCiId());
			hql.append(" AND scr.scrPresent='T' ");
			List<StudentCourseRelation> studentPresent = studentCourseRelationDAO
					.getListByHQL(hql.toString());
			// 未到人数
			hql = new StringBuilder(All_STUDENT_COURSE_RELATION);
			hql.append(CommonConstant.ONE_EQUALS_ONE);
			hql.append(" AND scr.courseInfo.ciId=");
			hql.append(courseInfoId);
			hql.append(" AND scr.studentInfo.classInfo.ciId=");
			hql.append(classInfo.getCiId());
			hql.append(" AND scr.scrPresent='F' ");
			List<StudentCourseRelation> studentAbsent = studentCourseRelationDAO.getListByHQL(hql.toString());
			classinfo1.setClassStudentNumberAbsent(String.valueOf(studentAbsent.size()));
			classinfo1.setClassId(classInfo.getCiId().toString());

			try {
				if (studentPresent.size() != 0)
				{
					classinfo1.setClassStudentNumberAttend(String.valueOf(studentPresent.size()));
					Double attendRateOriginal = 100.0* Double.valueOf(classinfo1.getClassStudentNumberAttend())
							/ Double.valueOf(classinfo1.getClassStudentNumber());
					DecimalFormat df = new DecimalFormat("#0.0");
					String attendRateNow = df.format(attendRateOriginal);
					classinfo1.setAttendRate(attendRateNow);
				} 
				else
				{
					classinfo1.setAttendRate("0");
					classinfo1.setClassStudentNumberAttend("0");
				}
			} catch (Exception e) {
				System.out.println("报错了");
				e.printStackTrace();
			}
			courseMessage.add(classinfo1);
		}
		return courseMessage;
	}

	@Override
	public int saveStudentCourseRelationBySIAndCI(StudentInfo studentInfo,
			CourseInfo courseInfo)
	{
		StudentCourseRelation studentCourseRelation = new StudentCourseRelation();

		studentCourseRelation.setStudentInfo(studentInfo);
		studentCourseRelation.setCourseInfo(courseInfo);

		StudentCourseRelation scr = studentCourseRelationDAO
				.turnPersitent(studentCourseRelation);

		if (scr != null)
			return Constant.RESULT_SUCCESS;
		else
			return Constant.RESULT_FAIL;
	}

	@Override
	public List<StudentCourseRelation> getStudentCourseRelationsByStudentLogin(
			UserLogin stuUserLogin, String DataStr) 
	{// 根据学生信息获取学生该上的课
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentInfo studentInfo = new StudentInfo();
		StringBuilder stuHql = new StringBuilder(ALL_STUDENT_INFOS).append(CommonConstant.ONE_EQUALS_ONE);

		try 
		{

			if (stuUserLogin != null && !"".equals(stuUserLogin.getUlName()))
			{

				stuHql.append(" AND si.userLogin.ulId=").append(stuUserLogin.getUlId());
				studentInfo = studentInfoDAO.getUniqueResultByHQL(stuHql.toString());
			}
		} 
		catch (Exception e) 
		{
			throw new EntityException("findStudentInfoByULID error ", e);
		}

		List<StudentCourseRelation> results = new ArrayList<>();

		StringBuilder stuCourseRelationHql = new StringBuilder(
				All_STUDENT_COURSE_RELATION ).append( CommonConstant.ONE_EQUALS_ONE);

		try
		{
			if (studentInfo != null && studentInfo.getSiId() != 0) 
				stuCourseRelationHql.append(" AND scr.studentInfo.siId=?");

			if (DataStr == null || DataStr == "") 
			{//获取某天的课程  由于data直接=在hql语句中无效
				stuCourseRelationHql.append(" AND scr.courseInfo.ciDateTime>='").append( DateUtil.getDate() ).append("' ");
				stuCourseRelationHql.append(" AND scr.courseInfo.ciDateTime<'").append(sdf.format(DateUtil.getTomorrowDate(DateUtil.getDate()))).append("' order by  scr.courseInfo.ciDateTime ASC");
			}
			else 
			{
				stuCourseRelationHql.append(" AND scr.courseInfo.ciDateTime>='").append((DataStr)).append("' ");
				stuCourseRelationHql.append(" AND scr.courseInfo.ciDateTime<'").append((sdf.format(DateUtil.getTomorrowDate(sdf.parse(DataStr))))).append( "' order by  scr.courseInfo.ciDateTime ASC");
			}
			
			List<StudentCourseRelation> scr = studentCourseRelationDAO
					.getListByHQL(stuCourseRelationHql.toString(),
							studentInfo.getSiId());
			results = scr == null || scr.size() == 0 ? null : scr;
		} 
		catch (Exception e) 
		{
			throw new EntityException("StudentCourseRelationServcieImpl.getStudentCourseRelationsByStudentLogin(...) error ", e);
		}

		return results;
	}

	@Override
	public int turnSCRPresent(String courseInfoID, String userLoginID) 
	{
		CourseInfo courseInfo = new CourseInfo();
		StudentInfo studentInfo = new StudentInfo();
		StudentCourseRelation studentCourseRelation;

		if (StringUtils.isNotEmpty(courseInfoID))
			courseInfo = courseInfoDAO.findById(Integer.valueOf(courseInfoID));

		if (!courseInfo.getCiState().equals(CommonConstant.TRUE))// 判断课是否开启
			return Constant.RESULT_EXIST;
		if (StringUtils.isNotEmpty(userLoginID)) {
			StringBuilder stuHql = new StringBuilder(ALL_STUDENT_INFOS).append(CommonConstant.ONE_EQUALS_ONE);

			try 
			{
				stuHql.append(" AND si.userLogin.ulId=").append(userLoginID);
				studentInfo = studentInfoDAO.getUniqueResultByHQL(stuHql.toString());
			} 
			catch (Exception e)
			{
				throw new EntityException("turnSCRPresent error ", e);
			}
		}

		try
		{
			StringBuilder stuCourseRelationHql = new StringBuilder(
					All_STUDENT_COURSE_RELATION + CommonConstant.ONE_EQUALS_ONE);

			stuCourseRelationHql.append(" AND scr.courseInfo.ciId=? ");
			stuCourseRelationHql.append(" AND scr.studentInfo.siId=? ");

			studentCourseRelation = studentCourseRelationDAO
					.getUniqueResultByHQL(stuCourseRelationHql.toString(),
							courseInfo.getCiId(), studentInfo.getSiId());

			if (studentCourseRelation.getScrPresent() != CommonConstant.TRUE) 
			{
				studentCourseRelation.setScrPresent(CommonConstant.TRUE);
			}
			studentCourseRelationDAO.updateEntity(studentCourseRelation);// 签到成功

			return Constant.RESULT_SUCCESS;
		} 
		catch (Exception e)
		{
			throw new EntityException(" StudentCourseRelationServcieImpl.turnSCRPresent(...) Error!!", e);
		}
	}

}
