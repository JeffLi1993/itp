package sedion.jeffli.wmuitp.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.CourseDAO;
import sedion.jeffli.wmuitp.dao.CourseTeacherRelationDAO;
import sedion.jeffli.wmuitp.dao.TeacherInfoDAO;
import sedion.jeffli.wmuitp.dao.impl.CourseDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.CourseTeacherRelationDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.TeacherInfoDAOImpl;
import sedion.jeffli.wmuitp.entity.Course;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.CourseTeacherRelationService;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;



//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class CourseTeacherRelationServiceImpl implements CourseTeacherRelationService 
{
	
	private static final String ALL_COURSES_TEACHER_RELATIONS = "FROM CourseTeacherRelation AS ctr ";
	
	@Autowired
	private CourseDAO 				 courseDAO;
	@Autowired
	private TeacherInfoDAO			 teacherInfoDAO;
	@Autowired
	private CourseTeacherRelationDAO courseTeacherRelationDAO;
	@Autowired 
	public HttpSession session; 
	
	@Override
	public List<CourseTeacherRelation> getCourseTeacherRelationSearchLess(String courseName,String teacherName)
	{
		courseName = StringUtils.trimToNull(courseName);
		teacherName = StringUtils.trimToNull(teacherName);
		StringBuilder hql = new StringBuilder(ALL_COURSES_TEACHER_RELATIONS).append(CommonConstant.ONE_EQUALS_ONE);
		if(courseName!=null)
			hql.append(" AND ctr.course.CName LIKE '%").append(courseName).append("%' ");
		if(teacherName!=null)
			hql.append(" AND ctr.teacherInfo.tiName LIKE '%").append(teacherName).append("%' ");
		return courseTeacherRelationDAO.getListByHQL(hql.toString());
	}
	
	@Override
	public List<CourseTeacherRelation> getCourseTeacherRelationSearchLessTea(String courseName,String teacherName)
	{
		courseName = StringUtils.trimToNull(courseName);
		teacherName = StringUtils.trimToNull(teacherName);
		StringBuilder hql = new StringBuilder(ALL_COURSES_TEACHER_RELATIONS).append(CommonConstant.ONE_EQUALS_ONE);
		if(courseName!=null)
			hql.append(" AND ctr.course.CName LIKE '%").append(courseName).append("%' ");
		if(teacherName!=null)
			hql.append(" AND ctr.teacherInfo.tiName LIKE '%").append(teacherName).append("%' ");
		hql.append(" AND ctr.teacherInfo.userLogin=?");
		UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
		return courseTeacherRelationDAO.getListByHQL(hql.toString(),ul);
	}
	
	@Override
	public List<CourseTeacherRelation> getCourseTeacherRelationPages(Page<CourseTeacherRelation> page,int[] pageParams)
	{
		List<CourseTeacherRelation> results = new ArrayList<>();

		StringBuilder resultsHQL = new StringBuilder(ALL_COURSES_TEACHER_RELATIONS);//StringBuilder在进行字符串处理时，不生成新的对象，在内存使用上要优于String类。
		try
		{
			results = courseTeacherRelationDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(courseTeacherRelationDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException("Error! ClassInfoServiceImpl.getClassInfosPages(Page<ClassInfo> page,int[] pageParams) ",e);
		}

		return results;
	}
	
	@Override
	public List<CourseTeacherRelation> getCourseTeacherRelationPagesTea(Page<CourseTeacherRelation> page,int[] pageParams)
	{
		List<CourseTeacherRelation> results = new ArrayList<>();
		StringBuilder resultsHQL = new StringBuilder(ALL_COURSES_TEACHER_RELATIONS);//StringBuilder在进行字符串处理时，不生成新的对象，在内存使用上要优于String类。
		try
		{
			UserLogin userLogin = AdminUtil.getUserLoginFromHttpSession(session);
			resultsHQL.append(CommonConstant.ONE_EQUALS_ONE).append(" AND ctr.teacherInfo.userLogin.ulId=").append(userLogin.getUlId());
			results = courseTeacherRelationDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(courseTeacherRelationDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException("Error! ClassInfoServiceImpl.getClassInfosPages(Page<ClassInfo> page,int[] pageParams) ",e);
		}

		return results;
	}
	
	@Override
	public List<CourseTeacherRelation> getCourseTeacherRelations()
	{
		return courseTeacherRelationDAO.getListByHQL(ALL_COURSES_TEACHER_RELATIONS);
	}
	
	@Override
	public List<CourseTeacherRelation> getCourseTeacherRelationsByTea(UserLogin userLogin)
	{
		StringBuilder hql = new StringBuilder(ALL_COURSES_TEACHER_RELATIONS);
		hql.append(CommonConstant.ONE_EQUALS_ONE);
		hql.append(" AND ctr.teacherInfo.userLogin=?");
		return courseTeacherRelationDAO.getListByHQL(hql.toString(),userLogin);
	}
	
	@Override
	public CourseTeacherRelation getCourseTeacherRelationById(String courseTeacherRelationID)
	{
		return courseTeacherRelationDAO.findById(Integer.valueOf(courseTeacherRelationID));
	}

	@Override
	public int saveOrUpdateCourseTeacherRelation(String courseID,
												 String teacherInfoID, 
												 CourseTeacherRelation courseTeacherRelation)
	{
		Course 		course 		= null;
		TeacherInfo teacherInfo = null;
		
		if (courseID != null && !courseID.equals(""))
		{
			course = courseDAO.findById(Integer.valueOf(courseID));
		}	
		else 
			return Constant.RESULT_FAIL;
		
		if (teacherInfoID != null && !teacherInfoID.equals(""))
		{
			teacherInfo = teacherInfoDAO.findById(Integer.valueOf(teacherInfoID));
		}
		else 
			return Constant.RESULT_FAIL;
		
		courseTeacherRelation.setCourse(course);
		courseTeacherRelation.setTeacherInfo(teacherInfo);
		
		courseTeacherRelationDAO.updateEntity(courseTeacherRelation);
		
		return Constant.RESULT_SUCCESS;
	}

	@Override
	public int deleteCourseTeacherRelations(String courseTeacherRelationIdStr)
	{
		String[] subjectInforIdStrs;
		
		if(courseTeacherRelationIdStr != null && !courseTeacherRelationIdStr.equals(""))
		{
			try
			{
				subjectInforIdStrs = courseTeacherRelationIdStr.split(Constant.LINE);
				for (String subjectInforId : subjectInforIdStrs)
				{
					CourseTeacherRelation courseTeacherRelation = courseTeacherRelationDAO.findById(Integer.valueOf(subjectInforId));
					courseTeacherRelationDAO.turnTransient(courseTeacherRelation);//删除
				}
				
			} 
			catch (Exception e)
			{
				throw new EntityException("Error! delete the set of entities. ",e);
			}
			
			return Constant.RESULT_SUCCESS;
			
		}	
		
		return Constant.RESULT_FAIL;
	}

	@Override
	public List<CourseTeacherRelation> getCourseTeacherRelationsByTearcherId(String teacherId)
	{
		StringBuilder hql = new StringBuilder(ALL_COURSES_TEACHER_RELATIONS);
		hql.append(CommonConstant.ONE_EQUALS_ONE);
		
		if(teacherId!=null && !teacherId.equals("") )
		{
			hql.append(" AND ctr.teacherInfo.tiId=");
			hql.append(teacherId);
		}
		
		return courseTeacherRelationDAO.getListByHQL(hql.toString());
	}
	
	
	
}
