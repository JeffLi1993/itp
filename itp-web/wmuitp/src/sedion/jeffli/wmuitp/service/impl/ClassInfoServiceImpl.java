package sedion.jeffli.wmuitp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.ClassInfoDAO;
import sedion.jeffli.wmuitp.dao.StudentCourseRelationDAO;
import sedion.jeffli.wmuitp.dao.impl.ClassInfoDAOImpl;
import sedion.jeffli.wmuitp.dao.impl.StudentCourseRelationDAOImpl;
import sedion.jeffli.wmuitp.entity.ClassInfo;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.ClassInfoService;
import sedion.jeffli.wmuitp.util.Page;


//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class ClassInfoServiceImpl implements ClassInfoService 
{

	private static final String ALL_CLASS_INFO						= "FROM ClassInfo AS cl";
	private static final String ALL_STUDENT_COURSE_RELATION			= "FROM StudentCourseRelation AS scr";
	
    @Autowired
	private ClassInfoDAO				classInfoDAO;
    @Autowired
	private StudentCourseRelationDAO	studentCourseRelationDAO;
	
	@Override
	public List<ClassInfo> findCommonClassInfo(UserLogin userLogin)
	{
		StringBuilder hql = new StringBuilder(ALL_STUDENT_COURSE_RELATION);
		hql.append(CommonConstant.ONE_EQUALS_ONE);
		hql.append(" AND scr.courseInfo.courseTeacherRelation.teacherInfo.userLogin=?");
		List<StudentCourseRelation> scrs = studentCourseRelationDAO.getListByHQLLimitNum(hql.toString(),1000,userLogin);
		
		hql = new StringBuilder(ALL_CLASS_INFO);
		hql.append(CommonConstant.ONE_EQUALS_ZERO);
		for(int i=0;i<scrs.size();i++)
		{
			hql.append(" OR cl.ciId=");
			hql.append(scrs.get(i).getStudentInfo().getClassInfo().getCiId());
		}
		return classInfoDAO.getListByHQL(hql.toString()).size()==0? null : classInfoDAO.getListByHQL(hql.toString());
	}

	@Override
	public List<ClassInfo> findAllClassInfo()
	{
		return classInfoDAO.findAll();
	}
	
	@Override
	public int deleteClassInfo(String classInfoIdStr)
	{
		String[] ClassInfoIdStrs;
		
		if(classInfoIdStr != null && !classInfoIdStr.equals(""))
		{
			ClassInfoIdStrs = classInfoIdStr.split(Constant.LINE);
			for (String classInfoId : ClassInfoIdStrs)
			{
				ClassInfo classInfo = classInfoDAO.findById(Integer.valueOf(classInfoId));
				classInfoDAO.turnTransient(classInfo);//删除
			}
			
			return Constant.RESULT_SUCCESS;
		}	
		return Constant.RESULT_FAIL;
	}
	
	@Override
	public int saveOrUpdateClassInfo(ClassInfo classInfo)
	{
		classInfoDAO.updateEntity(classInfo);
		return Constant.RESULT_SUCCESS;
	}
	
	@Override
	public ClassInfo getClassInfoByClassInfoID(String classInfoId)
	{
		return classInfoDAO.findById(Integer.valueOf(classInfoId));
		
	}
	
	@Override
	public List<ClassInfo> getClassInfosPages(Page<ClassInfo> page,int[] pageParams)
	{
		List<ClassInfo> results = new ArrayList<>();

		StringBuilder resultsHQL = new StringBuilder(ALL_CLASS_INFO);//StringBuilder在进行字符串处理时，不生成新的对象，在内存使用上要优于String类。
		
		try
		{
			results = classInfoDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(classInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException("Error! ClassInfoServiceImpl.getClassInfosPages(Page<ClassInfo> page,int[] pageParams) ",e);
		}

		return results;
	}
	
	@Override
	public List<ClassInfo> getClassInfosPagesBySearch(Page<ClassInfo> page,int[] pageParams,String className,String professionName,String collegeName)
	{
		List<ClassInfo> results = new ArrayList<>();
		StringBuffer resultsHQL = new StringBuffer(ALL_CLASS_INFO);
		
		className=StringUtils.trimToEmpty(className);
		String[] classNames=className.split(",");
		if(classNames.length==1)
			classNames=classNames[0].split("，");
		
		professionName=StringUtils.trimToEmpty(professionName);
		String[] professionNames=professionName.split(",");
		if(professionNames.length==1)
			professionNames=professionNames[0].split("，");
		
		collegeName=StringUtils.trimToEmpty(collegeName);
		String[] collegeNames=collegeName.split(",");
		if(collegeNames.length==1)
			collegeNames=collegeNames[0].split("，");
			
		if((!(classNames.length==1&&classNames[0].equals("")))||(!(professionNames.length==1&&professionNames[0].equals("")))||!(collegeNames.length==1&&collegeNames[0].equals("")))
			resultsHQL.append(CommonConstant.ONE_EQUALS_ZERO);

		if(!(classNames.length==1&&classNames[0].equals("")))
		{
			for(int i=0;i<classNames.length;i++)
			{
				resultsHQL.append(" OR cl.ciName LIKE '%");
				resultsHQL.append(classNames[i].trim());
				resultsHQL.append("%' ");
			}
		}
		if(!(professionNames.length==1&&professionNames[0].equals("")))
		{
			for(int i=0;i<professionNames.length;i++)
			{
				resultsHQL.append(" OR cl.ciProfession LIKE '%");
				resultsHQL.append(professionNames[i].trim());
				resultsHQL.append("%' ");
			}
		}
		if(!(collegeNames.length==1&&collegeNames[0].equals("")))
		{
			for(int i=0;i<collegeNames.length;i++)
			{
				resultsHQL.append(" OR cl.ciCollege LIKE '%");
				resultsHQL.append(collegeNames[i].trim());
				resultsHQL.append("%' ");
			}
		}
		try
		{
			results = classInfoDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(classInfoDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		} 
		catch (Exception e)
		{
			throw new EntityException("Error! ClassInfoServiceImpl.getClassInfosPages(Page<ClassInfo> page,int[] pageParams) ",e);
		}

		return results;
	}
	
	@Override
	public List<ClassInfo> getClassInfosBySearch(String className,String professionName,String collegeName)
	{
		StringBuffer resultsHQL = new StringBuffer(ALL_CLASS_INFO);
		
		className=StringUtils.trimToEmpty(className);
		String[] classNames=className.split(",");
		if(classNames.length==1)
			classNames=classNames[0].split("，");
		
		professionName=StringUtils.trimToEmpty(professionName);
		String[] professionNames=professionName.split(",");
		if(professionNames.length==1)
			professionNames=professionNames[0].split("，");
		
		collegeName=StringUtils.trimToEmpty(collegeName);
		String[] collegeNames=collegeName.split(",");
		if(collegeNames.length==1)
			collegeNames=collegeNames[0].split("，");
			
		if((!(classNames.length==1&&classNames[0].equals("")))||(!(professionNames.length==1&&professionNames[0].equals("")))||!(collegeNames.length==1&&collegeNames[0].equals("")))
			resultsHQL.append(CommonConstant.ONE_EQUALS_ZERO);

		if(!(classNames.length==1&&classNames[0].equals("")))
		{
			for(int i=0;i<classNames.length;i++)
			{
				resultsHQL.append(" OR cl.ciName LIKE '%");
				resultsHQL.append(classNames[i].trim());
				resultsHQL.append("%' ");
			}
		}
		if(!(professionNames.length==1&&professionNames[0].equals("")))
		{
			for(int i=0;i<professionNames.length;i++)
			{
				resultsHQL.append(" OR cl.ciProfession LIKE '%");
				resultsHQL.append(professionNames[i].trim());
				resultsHQL.append("%' ");
			}
		}
		if(!(collegeNames.length==1&&collegeNames[0].equals("")))
		{
			for(int i=0;i<collegeNames.length;i++)
			{
				resultsHQL.append(" OR cl.ciCollege LIKE '%");
				resultsHQL.append(collegeNames[i].trim());
				resultsHQL.append("%' ");
			}
		}
		return classInfoDAO.getListByHQL(resultsHQL.toString());
	}
}
