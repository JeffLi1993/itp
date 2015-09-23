package sedion.jeffli.wmuitp.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.CourseInfoDAO;
import sedion.jeffli.wmuitp.dao.CourseTeacherRelationDAO;
import sedion.jeffli.wmuitp.dao.StudentCourseRelationDAO;
import sedion.jeffli.wmuitp.dao.StudentInfoDAO;
import sedion.jeffli.wmuitp.dao.TeacherInfoDAO;
import sedion.jeffli.wmuitp.dao.impl.CourseInfoDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.CourseTeacherRelationDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.StudentCourseRelationDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.StudentInfoDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.TeacherInfoDAOImpl;
import sedion.jeffli.wmuitp.entity.ClassInfo;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.CourseTeacherRelation;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.entity.courseInfo.CourseInfoDetail;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.CourseInfoService;
import sedion.jeffli.wmuitp.service.impl.quartz.QuartzCourseInfoUtil;
import sedion.jeffli.wmuitp.service.util.CourseInfoUtil;
import sedion.jeffli.wmuitp.util.DateUtil;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;



//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class CourseInfoServiceImpl implements CourseInfoService 
{
	
	private static final String ALL_COURSES_INFOS             = "FROM CourseInfo AS ci ";
	private static final String ALL_STUDENT_INFOS             = "FROM StudentInfo AS si ";
	private static final String All_TEACHERINFOS	 		  = "FROM TeacherInfo AS ti ";

	@Autowired
	private CourseInfoDAO            courseInfoDAO;
	@Autowired
	private StudentInfoDAO           studentInfoDAO;
	@Autowired
	private TeacherInfoDAO			 teacherInfoDAO;
	@Autowired
	private CourseTeacherRelationDAO courseTeacherRelationDAO;
	@Autowired
	private StudentCourseRelationDAO studentCourseRelationDAO;
	@Autowired 
	public HttpSession session; 
	@Override
	public List<CourseInfo> getCourseInfoAfterToday()
	{
		StringBuilder hql=new StringBuilder(ALL_COURSES_INFOS);
		hql.append(CommonConstant.ONE_EQUALS_ONE);
		hql.append(" AND ci.ciDateTime>='");
		hql.append(DateUtil.getDate());
		hql.append("' order by  ci.ciDateTime ASC");
		return courseInfoDAO.getListByHQL(hql.toString());
	}
	
	@Override
	public List<CourseInfo> getCourseInfosPages(Page<CourseInfo> page, int[] pageParams)
	{
		List<CourseInfo> results = new ArrayList<>();

		StringBuilder resultsHQL = new StringBuilder(ALL_COURSES_INFOS).append(CommonConstant.ONE_EQUALS_ONE).append(" ORDER BY ci.ciDateTime DESC ");
		try
		{
			results = courseInfoDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(courseInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException(" getCourseInfosPages Error", e);
		}

		return results;
		
	}
	
	@Override
	public List<CourseInfo> getCourseInfosPagesCourseInfoDetailByTeaSession(Page<CourseInfoDetail> page, int[] pageParams)
	{
		List<CourseInfo> results = new ArrayList<>();
		List<CourseInfoDetail> resultsEdit = new ArrayList<>();
		UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
		StringBuilder resultsHQL = new StringBuilder(ALL_COURSES_INFOS).append(CommonConstant.ONE_EQUALS_ONE)
							.append(" AND ci.courseTeacherRelation.teacherInfo.userLogin.ulId=").append(ul.getUlId())
							.append(" AND ci.ciDateTime>='").append(DateUtil.getDate()).append("' ").append(" ORDER BY ci.ciId DESC ");
		System.out.println("resultsHQL="+resultsHQL);
		try
		{
			results = courseInfoDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			for(CourseInfo result : results)
			{
				CourseInfoDetail cid = new CourseInfoDetail();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date d = sdf.parse(result.getCiDateTime()); 
				SimpleDateFormat sdfWeek = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss(EEEE)"); 
				result.setCiDateTime(sdfWeek.format(d));
				cid.setCourseInfo(result);
				@SuppressWarnings("unchecked")
				Iterator<StudentCourseRelation> iterator1 = result.getStudentCourseRelations().iterator();
				String classInfoNames = "";
				
				Set<ClassInfo> classInfos = new HashSet<>();
				while(iterator1.hasNext())
				{
					classInfos.add(iterator1.next().getStudentInfo().getClassInfo());
				}
				
				Iterator<ClassInfo> iterator2 = classInfos.iterator();
				while(iterator2.hasNext())
				{
					classInfoNames += iterator2.next().getCiName();
					if(iterator2.hasNext())
						classInfoNames += ",";
				}
				cid.setClassNames(classInfoNames);
				resultsEdit.add(cid);
			}
			
			page.setTotalCount(courseInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(resultsEdit);
			
		} 
		catch (Exception e)
		{
			throw new EntityException(" getCourseInfosPages Error", e);
		}

		return results;
		
	}
	
	@Override
	public List<CourseInfo> getCourseInfosPagesCourseInfoDetail(Page<CourseInfoDetail> page, int[] pageParams)
	{
		List<CourseInfo> results = new ArrayList<>();
		List<CourseInfoDetail> resultsEdit = new ArrayList<>();
		StringBuilder resultsHQL = new StringBuilder(ALL_COURSES_INFOS).append(CommonConstant.ONE_EQUALS_ONE).append(" ORDER BY ci.ciId DESC ");
		try
		{
			results = courseInfoDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			for(CourseInfo result : results)
			{
				CourseInfoDetail cid = new CourseInfoDetail();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date d = sdf.parse(result.getCiDateTime()); 
				SimpleDateFormat sdfWeek = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss(EEEE)"); 
				result.setCiDateTime(sdfWeek.format(d));
				cid.setCourseInfo(result);
				@SuppressWarnings("unchecked")
				Iterator<StudentCourseRelation> iterator1 = result.getStudentCourseRelations().iterator();
				String classInfoNames = "";
				
				Set<ClassInfo> classInfos = new HashSet<>();
				while(iterator1.hasNext())
				{
					classInfos.add(iterator1.next().getStudentInfo().getClassInfo());
				}
				
				Iterator<ClassInfo> iterator2 = classInfos.iterator();
				while(iterator2.hasNext())
				{
					classInfoNames += iterator2.next().getCiName();
					if(iterator2.hasNext())
						classInfoNames += ",";
				}
				cid.setClassNames(classInfoNames);
				resultsEdit.add(cid);
			}
			
			page.setTotalCount(courseInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(resultsEdit);
			
		} 
		catch (Exception e)
		{
			throw new EntityException(" getCourseInfosPages Error", e);
		}

		return results;
		
	}
	
	@Override
	public List<CourseInfo> getCourseInfosPagesTea(Page<CourseInfoDetail> page, int[] pageParams,UserLogin userLogin)
	{
		List<CourseInfo> results = new ArrayList<>();
		List<CourseInfoDetail> resultsEdit = new ArrayList<>();
		StringBuilder resultsHQL = new StringBuilder(ALL_COURSES_INFOS)
				.append(CommonConstant.ONE_EQUALS_ONE)
				.append(" AND ci.courseTeacherRelation.teacherInfo.userLogin.ulId=")
				.append(userLogin.getUlId())
				.append(" ORDER BY ci.ciDateTime DESC ");
		
		try
		{
			results = courseInfoDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			for(CourseInfo result : results)
			{
				CourseInfoDetail cid = new CourseInfoDetail();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date d = sdf.parse(result.getCiDateTime()); 
				SimpleDateFormat sdfWeek = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss(EEEE)"); 
				result.setCiDateTime(sdfWeek.format(d));
				cid.setCourseInfo(result);
				@SuppressWarnings("unchecked")
				Iterator<StudentCourseRelation> iterator1 = result.getStudentCourseRelations().iterator();
				String classInfoNames = "";
				
				Set<ClassInfo> classInfos = new HashSet<>();
				while(iterator1.hasNext())
				{
					classInfos.add(iterator1.next().getStudentInfo().getClassInfo());
				}
				Iterator<ClassInfo> iterator2 = classInfos.iterator();
				while(iterator2.hasNext())
				{
					classInfoNames += iterator2.next().getCiName();
					if(iterator2.hasNext())
						classInfoNames += ",";
				}
				cid.setClassNames(classInfoNames);
				resultsEdit.add(cid);
			}
			
			page.setTotalCount(courseInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(resultsEdit);
			
		} 
		catch (Exception e)
		{
			throw new EntityException(" getCourseInfosPages Error", e);
		}

		return results;
		
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
	public int saveCourseInfo(CourseInfo courseInfo,String courseTeacherRelationID,String classInfoIds)
	{
		CourseTeacherRelation courseTeacherRelation = null;
		
		if (StringUtils.isNotEmpty(courseTeacherRelationID))
		{
			courseTeacherRelation = courseTeacherRelationDAO.findById(Integer.valueOf(courseTeacherRelationID));
		}
		else 
			return Constant.RESULT_FAIL;
		
		courseInfo.setCourseTeacherRelation(courseTeacherRelation);
		courseInfo.setCiState(CommonConstant.FALSE);
		
		if(courseInfo.getCiId() != null && courseInfo.getCiId() != 0)//更新用户数据操作
		{
			courseInfoDAO.updateEntity(courseInfo);
		}
		else//保存(初始化)数据
		{
			courseInfoDAO.updateEntity(courseInfo);
			
			if (courseInfo != null )
			{
				if (courseInfo.getCiId() != 0) 
				{
					
					String picAddress = null;
					
					try
					{
						picAddress = CourseInfoUtil.getCourseInfoQrcode(courseInfo);//保存二维码图片
					}
					catch (Exception e)
					{
						System.out.println("...getCourseInfoQrcode fail");
					}
					
					courseInfo.setCiQrcode(picAddress);
					
					courseInfoDAO.updateEntity(courseInfo);//更新二维码地址
					
					QuartzCourseInfoUtil quartzCourseInfoUtil = new QuartzCourseInfoUtil();
					try
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = sdf.parse(courseInfo.getCiDateTime());
						System.out.println(sdf.format(new Date(date.getTime()-1000*60*10)));
						quartzCourseInfoUtil.run(sdf.format(new Date(date.getTime()-1000*60*10)), courseInfo.getCiId());//开启任务调度
					} 
					catch (SchedulerException e) 
					{
						e.printStackTrace();
					} 
					catch (ParseException e) 
					{
						e.printStackTrace();
					}
					
					try
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = sdf.parse(courseInfo.getCiOver());
						System.out.println(sdf.format(new Date(date.getTime()-1000*60*10)));
						quartzCourseInfoUtil.stop(sdf.format(new Date(date.getTime()-1000*60*10)), courseInfo.getCiId());//开启任务调停止签到
					} 
					catch (SchedulerException e) 
					{
						e.printStackTrace();
					} 
					catch (ParseException e) 
					{
						e.printStackTrace();
					}
					
				}
				else
					return Constant.RESULT_FAIL;
				
				StringBuilder hql = new StringBuilder(ALL_STUDENT_INFOS + CommonConstant.ONE_EQUALS_ONE); 
				hql.append(" AND si.classInfo.ciId=?");
				
				if (StringUtils.isNotEmpty(classInfoIds))
				{
					String [] classinfoIdStr = classInfoIds.split(Constant.COMMA);
					Set<String> classinfoIdStrs = new HashSet<>();
					for (String classInfoId : classinfoIdStr) //用HashSet剔除重复的
						classinfoIdStrs.add(classInfoId);
					@SuppressWarnings("rawtypes")
					Iterator iterator = classinfoIdStrs.iterator();
					while(iterator.hasNext()) //保存班级
					{
						try 
						{
							//System.out.println(hql);
							List<StudentInfo> studentInfos = studentInfoDAO.getListByHQL(hql.toString(), Integer.valueOf((String)iterator.next()));//获取学生详情
							
							for (StudentInfo studentInfo : studentInfos) {
								//System.out.println(studentInfo.getSiRealName());
								
								StudentCourseRelation studentCourseRelation = new StudentCourseRelation();
								studentCourseRelation.setCourseInfo(courseInfo);
								studentCourseRelation.setStudentInfo(studentInfo);
								studentCourseRelation.setScrPresent(CommonConstant.FALSE);
								
								studentCourseRelationDAO.updateEntity(studentCourseRelation);//保存学生课程关系
							}
						} 
						catch (Exception e)
						{
							throw new EntityException(" CourseInfoServiceImpl saveCourseInfo() Error ", e);
						}
						
					}
				}
				else
					return Constant.RESULT_FAIL;
			}
			
		}
		
		return Constant.RESULT_SUCCESS;
		
	}
	

	
	@Override
	public void updateCourseInfo(CourseInfo courseInfo)
	{
		courseInfoDAO.updateEntity(courseInfo);
	}


	@Override
	public List<CourseInfo> getCourseInfosByDateTime(String dateTimeStr)
	{
		StringBuilder hql = new StringBuilder(ALL_COURSES_INFOS+CommonConstant.ONE_EQUALS_ONE);
		
		if (StringUtils.isNotEmpty(dateTimeStr))
		{
			hql.append(" AND ci.ciDateTime='"+DateUtil.getDate()+"' ORDER BY ci.ciDateTime");
		}
		
		System.out.println("hql:"+hql);
		return courseInfoDAO.getListByHQL(hql.toString());
	}

	@Override
	public CourseInfo getCourseInfoById(String CIId)
	{
		return courseInfoDAO.findById(Integer.valueOf(CIId));
	}

	@Override
	public int deleteCourseInfos(String courseInfoStr)
	{
		String[] courseInfoIdStrs;
		
		if(courseInfoStr != null && !courseInfoStr.equals(""))
		{
			try
			{
				System.out.println("courseInfoStr:"+courseInfoStr);
				courseInfoIdStrs = courseInfoStr.split("-");
				for (String courseInfoId : courseInfoIdStrs)
				{
					CourseInfo courseInfo = courseInfoDAO.findById(Integer.valueOf(courseInfoId));
					courseInfoDAO.turnTransient(courseInfo);
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
	public List<CourseInfo> getCourseInfosByUserLogin(String ulName,String ulPassword) {
		
		
		
		//获取课堂列表
		List<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
		
		try {
			
			String hql = ALL_COURSES_INFOS+
					CommonConstant.ONE_EQUALS_ONE;
			
			courseInfos = courseInfoDAO.getListByHQL(hql);
			
		} catch (Exception e) {
			
			throw new EntityException("getCourseInfosByUserLogin Error!! ", e);
			
		}
		 
		return courseInfos;
	}

	@Override
	public int turnCIStateTureByCourseInfoId(int courseInfoId)
	{
		try 
		{
			
			CourseInfo courseInfo = new CourseInfo();
			courseInfo = courseInfoDAO.findById(courseInfoId);
			
			courseInfo.setCiState(CommonConstant.TRUE);
			courseInfoDAO.updateEntity(courseInfo);
			System.out.println("courseInfoId:"+courseInfoId);
		}
		catch (Exception e) {
			throw new EntityException("turnCIStateTureByCourseInfoId Error!! ", e);
		}
		
		return Constant.RESULT_SUCCESS;
	}

	@Override
	public CourseInfo getCourseInfoByCIPlace(String ciPlace)
	{
		String hql = ALL_COURSES_INFOS+
				CommonConstant.ONE_EQUALS_ONE;
		
		if (ciPlace != null && !ciPlace.equals(""))
		{
			hql += " AND ci.ciPlace like '%"+ciPlace+"%' ";
		}
		hql += "AND ci.ciDateTime>='"+DateUtil.getDate()+"'";
		hql += "AND ci.ciState='T'";
		System.out.println("hql:"+hql);
		return (CourseInfo) courseInfoDAO.getUniqueResultByHQL(hql);
	}

	@Override
	public List<CourseInfo> getCourseInfosByTeacherLogin(UserLogin userLogin)
	{
		TeacherInfo teacherInfo = new TeacherInfo();
		StringBuilder teaHql =  new StringBuilder(All_TEACHERINFOS).append(CommonConstant.ONE_EQUALS_ONE);
		
		if (userLogin != null && !"".equals(userLogin.getUlName())){
			teaHql.append(" AND ti.userLogin.ulId=?");
			teacherInfo = teacherInfoDAO.getUniqueResultByHQL(teaHql.toString(),userLogin.getUlId());
		}else{
			return null;
		}
			
		
		List<CourseInfo> results = new ArrayList<>();
		StringBuilder hql = new StringBuilder(ALL_COURSES_INFOS).append(CommonConstant.ONE_EQUALS_ONE);
		
		try {
			
			if (teacherInfo != null && teacherInfo.getTiId() != 0){
				hql.append(" AND ci.courseTeacherRelation.teacherInfo.tiId=").append(teacherInfo.getTiId())
				.append("  ORDER BY ci.ciDateTime DESC ");
			}else{
				return null;
			}
			
			List<CourseInfo> courseInfos=courseInfoDAO.getListByHQL(hql.toString());
			results = courseInfos;
		}	
		catch (Exception e)
		{
			throw new EntityException("get the paperInfos error ", e);
		}
		if (results.size() == 0)
			results = null;
		
		return results;
	}
	
}
