package sedion.jeffli.wmuitp.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.CourseDAO;
import sedion.jeffli.wmuitp.dao.CourseInfoDAO;
import sedion.jeffli.wmuitp.dao.CourseTeacherRelationDAO;
import sedion.jeffli.wmuitp.dao.StudentCourseRelationDAO;
import sedion.jeffli.wmuitp.dao.StudentInfoDAO;
import sedion.jeffli.wmuitp.dao.impl.CourseDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.CourseInfoDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.CourseTeacherRelationDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.StudentCourseRelationDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.StudentInfoDAOImpl;
import sedion.jeffli.wmuitp.entity.Course;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.CourseService;
import sedion.jeffli.wmuitp.util.DateUtil;
import sedion.jeffli.wmuitp.util.Page;



//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class CourseServiceImpl implements CourseService 
{
	
	private static final String ALL_COURSES					  = "FROM Course As c";
	private static final String ALL_STUDENT_INFOS			  = "FROM StudentInfo AS si ";
	private static final String ALL_COURSES_INFOS             = "FROM CourseInfo AS ci ";
	private static final String ALL_STUDENT_COURSE_RELATIONS  = "FROM StudentCourseRelation AS scr";
	private static final String ALL_COURSES_TEACHER_RELATIONS = "FROM CourseTeacherRelation AS ctr ";

	
	@Autowired
	private CourseDAO 			     courseDAO;
	@Autowired
	private CourseInfoDAO            courseInfoDAO;
	@Autowired
	private StudentInfoDAO 			 studentInfoDAO;
	@Autowired
	private CourseTeacherRelationDAO courseTeacherRelationDAO;
	@Autowired
	private StudentCourseRelationDAO studentCourseRelationDAO;
	
	@Override
	public List<Course> getCourseByCourseName(String CName)
	{
		CName=StringUtils.trimToEmpty(CName);
		StringBuilder resultsHQL = new StringBuilder(ALL_COURSES);
		if(!CName.equals("")){
		resultsHQL.append(CommonConstant.ONE_EQUALS_ONE);
		resultsHQL.append(" AND c.CName LIKE '%");
		resultsHQL.append(CName);
		resultsHQL.append("%' ");
		}
		return courseDAO.getListByHQL(resultsHQL.toString());
	}
	
	@Override
	public List<Course> getCoursePages(Page<Course> page,int[] pageParams)
	{
		List<Course> results = new ArrayList<>();

		StringBuilder resultsHQL = new StringBuilder(ALL_COURSES);//StringBuilder在进行字符串处理时，不生成新的对象，在内存使用上要优于String类。
		
		try
		{
			results = courseDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(courseDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException("Error! ClassInfoServiceImpl.getCoursePages(Page<ClassInfo> page,int[] pageParams) ",e);
		}

		return results;
	}
	
	@Override
	public List<Course> getCourses() 
	{
		return courseDAO.getListByHQL(ALL_COURSES);
	}
	
	@Override
	public Course getCourseById(String CId)
	{		
		return courseDAO.findById(Integer.valueOf(CId));
	}
	
	@Override
	public int saveOrUpdateCourse(Course course)
	{
		
		try
		{
			 courseDAO.turnPersitent(course);
		}
		catch (Exception e)
		{
			throw new EntityException("CourseServiceImpl saveOrUpdateCourse(Course course) Error!",e);
		}
		
		return Constant.RESULT_SUCCESS;
		
	}

	@Override
	public int deleteCourse(String CIdStr) {
		
		String[] CIdStrs;
		
		if(StringUtils.isNotEmpty(CIdStr))
		{
			CIdStrs = CIdStr.split(Constant.LINE);
			
			for (String CId : CIdStrs)
			{
				try
				{
					Course  course= courseDAO.findById(Integer.valueOf(CId));
					courseDAO.turnTransient(course);
				} 
				catch (Exception e)
				{
					throw new EntityException("CourseServiceImpl deleteCourse(String CIdStr) ERROR !!!",e);
				}
				
			}
			
			return Constant.RESULT_SUCCESS;
		}
		
		return Constant.RESULT_FAIL;
	}
	
	public Course findCourseById(long courseId) 
	{
		return courseDAO.findById(courseId);
	}


	@Override
	public List<CourseTeacherRelation> getCourseTeacherRelations()
	{
		return courseTeacherRelationDAO.
				getListByHQL(ALL_COURSES_TEACHER_RELATIONS);
	}
	
	@Override
	public List<CourseTeacherRelation> getCourseTeacherRelationsByOBJ(String teacherID)
	{	
		String hql = ALL_COURSES_TEACHER_RELATIONS+
				CommonConstant.ONE_EQUALS_ONE;
		if(teacherID!=null && !teacherID.equals("") )
		{
			hql += " AND ctr.teacherInfo.tiId="+teacherID;
		}
		
		return courseTeacherRelationDAO.getListByHQL(hql);
	}
	
	@Override
	public List<CourseInfo> getCourseInfosByTeacherID(String teacherID)
	{
		String hql = ALL_COURSES_INFOS+
				CommonConstant.ONE_EQUALS_ONE;
		if(teacherID!=null && !teacherID.equals("") )
		{
			hql += " AND ci.courseTeacherRelation.teacherInfo.tiId="+teacherID;
		}
		//大于当前时间
		hql += " AND ci.ciDateTime>='"+DateUtil.getDate()+"' order by ci.ciDateTime";
		
		System.out.println(hql);
		return courseInfoDAO.getListByHQL(hql);
	}
	
	@Override
	public CourseInfo findCourseInfoById(Integer courseID) 
	{
		return courseInfoDAO.findById(courseID);
	}
	
	@Override
	public List<CourseInfo> getCourseInfos()
	{
		return courseInfoDAO.getListByHQL(ALL_COURSES_INFOS);
	}
	
	@Override
	public CourseTeacherRelation getCourseTeacherRelationsByCTRID(
			String courseTeacherRelationID)
	{
		return courseTeacherRelationDAO.
				findById(Integer.valueOf(courseTeacherRelationID));
	}
	
	@Override
	public CourseInfo saveCourseInfo(CourseInfo courseInfo,String courseTeacherRelationID)
	{
		//保存 课程和教师关系
		courseInfo.setCourseTeacherRelation(courseTeacherRelationDAO.
				findById(Integer.valueOf(courseTeacherRelationID)));
		
		courseInfoDAO.turnPersitent(courseInfo);
		return courseInfo;
		
	}
	
	@Override
	public void saveStudentCourseRelation(
			StudentCourseRelation studentCourseRelation)
	{
		studentCourseRelationDAO.turnPersitent(studentCourseRelation);
	}

	@Override
	public List<StudentCourseRelation> getStuStudentCourseRelationsByCIID(
			String ciId)
	{
		String hql = ALL_STUDENT_COURSE_RELATIONS+
				CommonConstant.ONE_EQUALS_ONE;
		if(ciId != null && !ciId.equals("") )
		{
			hql += " AND scr.courseInfo.ciId="+ciId;
		}
		
		return studentCourseRelationDAO.getListByHQL(hql);
	}

	@Override
	public void updateCourseInfo(CourseInfo courseInfo)
	{
		courseInfoDAO.updateEntity(courseInfo);
	}

	@Override
	public void saveStudentCourseRelationBystudentInfoIDAndcourseInfoID(
			String studentInfoID, String courseInfoID)
	{

		StudentInfo studentInfo =  studentInfoDAO.findById(Integer.valueOf(studentInfoID));
		CourseInfo  courseInfo  =  courseInfoDAO.findById(Integer.valueOf(courseInfoID));
		
		StudentCourseRelation studentCourseRelation = new StudentCourseRelation();
		studentCourseRelation.setCourseInfo(courseInfo);
		studentCourseRelation.setStudentInfo(studentInfo);
		
		studentCourseRelationDAO.turnPersitent(studentCourseRelation);
	}

	@Override
	public List<CourseInfo> getCourseInfosByDateTime(String dateTimeStr)
	{
		String hql = ALL_COURSES_INFOS+
				CommonConstant.ONE_EQUALS_ONE;
		if (dateTimeStr != null && !dateTimeStr.equals(""))
		{
			hql += " AND ci.ciDateTime='"+DateUtil.getDate()+"' ORDER BY ci.ciDateTime";
		}
		
		System.out.println("hql:"+hql);
		return courseInfoDAO.getListByHQL(hql);
	}

	@Override
	public List<Course> getCoursesByStu(UserLogin stuUserLogin)
	{
		//获取学生信息
		StudentInfo studentInfo = null;
		StringBuilder stuHql = new StringBuilder(ALL_STUDENT_INFOS).append(CommonConstant.ONE_EQUALS_ONE);

		try 
		{
			if (stuUserLogin != null && StringUtils.isNotEmpty(stuUserLogin.getUlName()))
			{
				stuHql.append(" AND si.userLogin.ulId=").append(stuUserLogin.getUlId());
				studentInfo = studentInfoDAO.getUniqueResultByHQL(stuHql.toString());
			}
		} 
		catch (Exception e) 
		{
			throw new EntityException("getCoursesByStu error ", e);
		}
		
		
		List<Course> courses = null;
		StringBuilder courseHql = new StringBuilder(ALL_COURSES).append(CommonConstant.ONE_EQUALS_ZERO);
		
		try
		{
			if (studentInfo!=null)
			{
				//获取学生和课关系表
				for (Iterator iterator = studentInfo.getStudentCourseRelations().iterator(); iterator.hasNext();)
				{
					StudentCourseRelation sCourseRelation = (StudentCourseRelation) iterator.next();
					courseHql.append(" OR c.CId=").append(sCourseRelation.getCourseInfo().getCourseTeacherRelation().getCourse().getCId());
				}
			}
			System.out.println("courseHql.toString():"+courseHql.toString());
			courses = courseDAO.getListByHQL(courseHql.toString());
			
		} catch (Exception e)
		{
			throw new EntityException("getCoursesByStu error ", e);
		}
		
		return courses;
	}
	
}
