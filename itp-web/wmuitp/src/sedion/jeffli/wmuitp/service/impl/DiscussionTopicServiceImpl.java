package sedion.jeffli.wmuitp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.dao.CourseInfoDAO;
import sedion.jeffli.wmuitp.dao.DiscussionStudentReplyDAO;
import sedion.jeffli.wmuitp.dao.DiscussionTeacherReplyDAO;
import sedion.jeffli.wmuitp.dao.DiscussionTopicDAO;
import sedion.jeffli.wmuitp.dao.StudentInfoDAO;
import sedion.jeffli.wmuitp.dao.TeacherInfoDAO;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.entity.DiscussionStudentReply;
import sedion.jeffli.wmuitp.entity.DiscussionTeacherReply;
import sedion.jeffli.wmuitp.entity.DiscussionTopic;
import sedion.jeffli.wmuitp.entity.StudentCourseRelation;
import sedion.jeffli.wmuitp.entity.StudentInfo;
import sedion.jeffli.wmuitp.entity.TeacherInfo;
import sedion.jeffli.wmuitp.entity.UserLogin;
import sedion.jeffli.wmuitp.exception.EntityException;
import sedion.jeffli.wmuitp.service.DiscussionTopicService;
import sedion.jeffli.wmuitp.util.Page;
import sedion.jeffli.wmuitp.util.session.AdminUtil;
import sedion.jeffli.wmuitp.util.session.ClientUtil;


//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class DiscussionTopicServiceImpl implements DiscussionTopicService 
{
	private static final String ALL_DISCUSSION_TOPIC				= "FROM DiscussionTopic AS dt";
	private static final String ALL_COURSE_INFO 					= "FROM CourseInfo AS ci";
	private static final String ALL_STUDENT_INFO 					= "FROM StudentInfo AS si";
	private static final String ALL_TEACHER_INFO 					= "FROM TeacherInfo AS ti";
	private static final String ALL_DISCUSSION_STUDENT_REPLY 		= "FROM DiscussionStudentReply AS dsr";
	private static final String ALL_DISCUSSION_TEACHER_REPLY 		= "FROM DiscussionTeacherReply AS dtr";

	@Autowired
	private DiscussionTopicDAO discussionTopicDAO;
	@Autowired
	private DiscussionStudentReplyDAO discussionStudentReplyDAO;
	@Autowired
	private DiscussionTeacherReplyDAO discussionTeacherReplyDAO;
	@Autowired
	private CourseInfoDAO courseInfoDAO;
	@Autowired
	private StudentInfoDAO studentInfoDAO;
	@Autowired
	private TeacherInfoDAO teacherInfoDAO;
	@Autowired
	private HttpSession session;
	
	@Override
	public int turnDiscussionTopicSign(String DiscussionTopicId)
	{
		DiscussionTopic discussionTopic = discussionTopicDAO.findById(DiscussionTopicId);

		if (StringUtils.isNotBlank(discussionTopic.getDtSign()))
		{
			switch (discussionTopic.getDtSign()) 
			{
			case "T":
				discussionTopic.setDtSign("F");
				break;
			case "F":
				discussionTopic.setDtSign("T");
				break;
			default:
				discussionTopic.setDtSign("F");
				break;
			}
		} 
		else
		{
			discussionTopic.setDtReply("F");
		}

		try 
		{
			discussionTopicDAO.updateEntity(discussionTopic);
		}
		catch (Exception e)
		{
			throw new EntityException(
					"DiscussionTopicServiceImpl.turnDiscussionTopicSign(...)  error ", e);
		}
		return Constant.RESULT_SUCCESS;
	}
	
	@Override
	public int getTopDivReload (String discussionStudentReplyId)
	{
		DiscussionStudentReply dsr = discussionStudentReplyDAO.findById(Integer.valueOf(discussionStudentReplyId));
		if(dsr.getDsrTop().equals("T"))
			return Constant.RESULT_SUCCESS;
		else
			return Constant.RESULT_FAIL;
	}
	@Override
	public 	int turnDiscussionStudentReplyTop(String discussionStudentReplyId)
	{
		DiscussionStudentReply dsr = discussionStudentReplyDAO.findById(Integer.valueOf(discussionStudentReplyId));
		if(dsr.getDsrTop().equals("T"))
			dsr.setDsrTop("F");
		else
			dsr.setDsrTop("T");
		try 
		{
			discussionStudentReplyDAO.updateEntity(dsr);
		} catch (Exception e) 
		{
			System.out.println("DiscussionTopicServiceImpl.trunDiscussionStudentReplyTop   ERROR!!!");
			return Constant.RESULT_FAIL;
		}
		return Constant.RESULT_SUCCESS;
	}
	
	@Override
	public List<DiscussionStudentReply> findAllDiscussionStudentReplyByDiscussionOrderByTop(String discussionTopicId)
	{
		StringBuilder hql = new StringBuilder(ALL_DISCUSSION_STUDENT_REPLY)
						.append(CommonConstant.ONE_EQUALS_ONE)
						.append(" AND dsr.discussionTopic.dtId=")
						.append(discussionTopicId)
						.append(" ORDER BY dsr.dsrTop DESC,dsr.dsrId ASC");
		return discussionStudentReplyDAO.getListByHQL(hql.toString());
	}
	
	/*@Override
	public 	List<DiscussionTeacherReply> findDiscussionTeacherReplyBySessionSelfDiscussionTopicId(String discussionTopicId)
	{
		UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
		StringBuilder hql = new StringBuilder(ALL_TEACHER_INFO).append(CommonConstant.ONE_EQUALS_ONE).append(" AND ti.userLogin=?");
		TeacherInfo ti = (TeacherInfo) teacherInfoDAO.getUniqueResultByHQL(hql.toString(), ul);
		hql = new StringBuilder(ALL_DISCUSSION_TEACHER_REPLY)
		.append(CommonConstant.ONE_EQUALS_ONE)
		.append(" AND dtr.discussionTopic.dtId=")
		.append(discussionTopicId)
		.append(" AND dtr.teacherInfo=?");
		return discussionTeacherReplyDAO.getListByHQL(hql.toString(),ti);
	}*/
	
	@Override
	public 	DiscussionStudentReply findDiscussionStudentReplyByDiscussionStudentReplyId(String DiscussionStudentReplyId)
	{
		return discussionStudentReplyDAO.findById(Integer.valueOf(DiscussionStudentReplyId));
	}
	
	@Override
	public List<DiscussionTeacherReply> findDiscussionTeacherReplyByDiscussionTopicId(String discussionTopicId)
	{
		StringBuilder hql = new StringBuilder(ALL_DISCUSSION_TEACHER_REPLY)
		.append(CommonConstant.ONE_EQUALS_ONE)
		.append(" AND dtr.discussionTopic.dtId=")
		.append(discussionTopicId);
		return discussionTeacherReplyDAO.getListByHQL(hql.toString());
	}
	
	@Override
	public 	List<DiscussionStudentReply> findDiscussionStudentReplyBySessionSelfDiscussionTopicId(String discussionTopicId)
	{
		UserLogin ul = ClientUtil.getStudentLoginFromHttpSession(session);
		StringBuilder hql = new StringBuilder(ALL_STUDENT_INFO).append(CommonConstant.ONE_EQUALS_ONE).append(" AND si.userLogin=?");
		StudentInfo si =  (StudentInfo) studentInfoDAO.getUniqueResultByHQL(hql.toString(), ul);
		hql = new StringBuilder(ALL_DISCUSSION_STUDENT_REPLY).append(CommonConstant.ONE_EQUALS_ONE).append(" AND dsr.studentInfo=? ").append(" AND dsr.discussionTopic.dtId=").append(discussionTopicId).append(" ORDER BY dsr.dsrId ASC");
		return discussionStudentReplyDAO.getListByHQL(hql.toString(), si);
	}
	public List<DiscussionStudentReply> findAllDiscussionStudentReplyByDiscussion(String discussionTopicId)
	{
		StringBuilder hql = new StringBuilder(ALL_DISCUSSION_STUDENT_REPLY).append(CommonConstant.ONE_EQUALS_ONE).append(" AND dsr.discussionTopic.dtId=").append(discussionTopicId);
		return discussionStudentReplyDAO.getListByHQL(hql.toString());
	}
	@Override
	public List<DiscussionStudentReply> findDiscussionStudentReplyByDiscussionTopicIdTopFine(String discussionTopicId)
	{
		StringBuilder hql = new StringBuilder(ALL_DISCUSSION_STUDENT_REPLY).append(CommonConstant.ONE_EQUALS_ONE).append(" AND dsr.discussionTopic.dtId=").append(discussionTopicId).append(" AND dsr.dsrTop='T'");
		return discussionStudentReplyDAO.getListByHQL(hql.toString());
	}
	
	@Override
	public 	int addDiscussionTopicAppStu(String discussionTopicId,String discussionTopicText)
	{
		discussionTopicText = StringUtils.trimToEmpty(discussionTopicText);
		DiscussionTopic discussionTopic=discussionTopicDAO.findById(discussionTopicId);
		UserLogin ul = ClientUtil.getStudentLoginFromHttpSession(session);
		StringBuilder hql = new StringBuilder(ALL_STUDENT_INFO).append(CommonConstant.ONE_EQUALS_ONE).append(" AND si.userLogin=?");
		StudentInfo si =  (StudentInfo) studentInfoDAO.getUniqueResultByHQL(hql.toString(), ul);
		//判断回答该议题的学生是否为上这趟课的学生  不然有漏洞
		HashSet<StudentInfo> studentSet = new HashSet<>();
		Iterator<StudentCourseRelation> iterator = discussionTopic.getCourseInfo().getStudentCourseRelations().iterator();
		while(iterator.hasNext())
			studentSet.add(iterator.next().getStudentInfo());
		if(!studentSet.contains(si))
			return Constant.RESULT_FAIL;
		System.out.println("判断回答该议题的学生是否为上这趟课的学生"+studentSet.contains(si));
		//判断议题是否关闭
		if(discussionTopic.getDtSign().equals("F"))
			return Constant.RESULT_FAIL;
		//判断重复提交
		hql = new StringBuilder(ALL_DISCUSSION_STUDENT_REPLY).append(CommonConstant.ONE_EQUALS_ONE).append(" AND dsr.studentInfo=? ").append(" AND dsr.discussionTopic.dtId=").append(discussionTopicId).append(" ORDER BY dsr.dsrId DESC");
		DiscussionStudentReply dsr = discussionStudentReplyDAO.getListByHQL(hql.toString(), si)==null||discussionStudentReplyDAO.getListByHQL(hql.toString(), si).size()==0?null:discussionStudentReplyDAO.getListByHQL(hql.toString(), si).get(0);
		//判断这条信息是否与上一条相等
		if(dsr!=null&&dsr.getDsrContent().equals(discussionTopicText))
			return Constant.RESULT_EXIST;
		
		dsr = new DiscussionStudentReply();
		dsr.setDiscussionTopic(discussionTopic);
		dsr.setDsrContent(discussionTopicText);
		dsr.setDsrDataTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		dsr.setDsrFine("F");
		dsr.setDsrTop("F");
		dsr.setStudentInfo(si);
		try 
		{
			discussionStudentReplyDAO.updateEntity(dsr);
			//回复成功
			if(discussionTopic.getDtReply()==null||discussionTopic.getDtReply().equals(""))
				discussionTopic.setDtReply("1");
			else
				discussionTopic.setDtReply(String.valueOf(Integer.valueOf(discussionTopic.getDtReply())+1));
			discussionTopicDAO.updateEntity(discussionTopic); 
		} catch (Exception e) 
		{
			System.out.println("DiscussionTopicServiceImpl.addDiscussionTopicAppStu ERROR!!!!");
			return Constant.RESULT_FAIL;
		}
		return Constant.RESULT_SUCCESS;
	} 
	
	@Override
	public 	int addDiscussionTopicAppTea(String discussionTopicId,String discussionTopicText)
	{
		discussionTopicText = StringUtils.trimToEmpty(discussionTopicText);
		DiscussionTopic discussionTopic=discussionTopicDAO.findById(discussionTopicId);
		UserLogin ul = AdminUtil.getUserLoginFromHttpSession(session);
		StringBuilder hql = new StringBuilder(ALL_TEACHER_INFO).append(CommonConstant.ONE_EQUALS_ONE).append(" AND ti.userLogin=?");
		TeacherInfo ti =  (TeacherInfo) teacherInfoDAO.getUniqueResultByHQL(hql.toString(), ul);
		//判断重复提交
		hql = new StringBuilder(ALL_DISCUSSION_TEACHER_REPLY).append(CommonConstant.ONE_EQUALS_ONE).append(" AND dtr.teacherInfo=? ").append(" AND dtr.discussionTopic.dtId=").append(discussionTopicId).append(" ORDER BY dtr.dtrId DESC");
		DiscussionTeacherReply dtr = discussionTeacherReplyDAO.getListByHQL(hql.toString(), ti)==null||discussionTeacherReplyDAO.getListByHQL(hql.toString(), ti).size()==0?null:discussionTeacherReplyDAO.getListByHQL(hql.toString(), ti).get(0);
		//判断这条信息是否与上一条相等
		if(dtr!=null&&dtr.getDtrContent().equals(discussionTopicText))
			return Constant.RESULT_EXIST;
		dtr = new  DiscussionTeacherReply();
		dtr.setDiscussionTopic(discussionTopic);
		dtr.setDtrContent(discussionTopicText);
		dtr.setDtrDataTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		dtr.setTeacherInfo(ti);
		try 
		{
			discussionTeacherReplyDAO.updateEntity(dtr);
			if(discussionTopic.getDtReply()==null||discussionTopic.getDtReply().equals(""))
				discussionTopic.setDtReply("1");
			else
				discussionTopic.setDtReply(String.valueOf(Integer.valueOf(discussionTopic.getDtReply())+1));
			discussionTopicDAO.updateEntity(discussionTopic); 
		} catch (Exception e) 
		{
			System.out.println("DiscussionTopicServiceImpl.addDiscussionTopicAppTea ERROR!!!!");
			return Constant.RESULT_FAIL;
		}
		return Constant.RESULT_SUCCESS;
	} 
	
	@Override
	public List<DiscussionTopic> getDiscussionTopicsPages(
			Page<DiscussionTopic> page, int[] pageParams)
	{
		List<DiscussionTopic> results = new ArrayList<>();

		StringBuilder resultsHQL = new StringBuilder(ALL_DISCUSSION_TOPIC);//StringBuilder在进行字符串处理时，不生成新的对象，在内存使用上要优于String类。
		try
		{
			results = discussionTopicDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(discussionTopicDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		}
		catch (Exception e)
		{
			throw new EntityException("getDiscussionTopicsPages Error ",e);
		}
		return results;
	}
	
	@Override
	public List<DiscussionTopic> getDiscussionTopicsPagesSearch(
			Page<DiscussionTopic> page, int[] pageParams,String discussionTopicName,String courseInfoName)
	{
		List<DiscussionTopic> results = new ArrayList<>();

		discussionTopicName = StringUtils.trimToEmpty(discussionTopicName);
		courseInfoName = StringUtils.trimToEmpty(courseInfoName);
		StringBuilder resultsHQL = new StringBuilder(ALL_DISCUSSION_TOPIC).append(CommonConstant.ONE_EQUALS_ONE);
		//搜索
		if(!discussionTopicName.equals(""))
			resultsHQL.append(" AND dt.dtName LIKE '%").append(discussionTopicName).append("%' ");
		if(!courseInfoName.equals(""))
			resultsHQL.append(" AND dt.courseInfo.courseTeacherRelation.course.CName LIKE '%").append(courseInfoName).append("%' ");
		try
		{
			results = discussionTopicDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(discussionTopicDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		}
		catch (Exception e)
		{
			throw new EntityException("getDiscussionTopicsPages Error ",e);
		}
		return results;
	}
	
	@Override
	public List<DiscussionTopic> getDiscussionTopicsPagesSearchTea(
			Page<DiscussionTopic> page, int[] pageParams,String discussionTopicName,String courseInfoName)
	{
		discussionTopicName = StringUtils.trimToEmpty(discussionTopicName);
		courseInfoName = StringUtils.trimToEmpty(courseInfoName);
		List<DiscussionTopic> results = new ArrayList<>();

		StringBuilder resultsHQL = new StringBuilder(ALL_DISCUSSION_TOPIC).append(CommonConstant.ONE_EQUALS_ONE)
								.append(" AND dt.courseInfo.courseTeacherRelation.teacherInfo.userLogin.ulId=").append(AdminUtil.getUserLoginIDFromHttpSession(session));//StringBuilder在进行字符串处理时，不生成新的对象，在内存使用上要优于String类。
		//搜索
		if(!discussionTopicName.equals(""))
			resultsHQL.append(" AND dt.dtName LIKE '%").append(discussionTopicName).append("%' ");
		if(!courseInfoName.equals(""))
			resultsHQL.append(" AND dt.courseInfo.courseTeacherRelation.course.CName LIKE '%").append(courseInfoName).append("%' ");
		try
		{
			resultsHQL.append(" ORDER BY dt.dtDateTime DESC");
			results = discussionTopicDAO.findByPage(resultsHQL.toString(), pageParams[0], pageParams[1]);
			
			page.setTotalCount(discussionTopicDAO.getCount(resultsHQL.toString()));
			page.setResult(results);
			
		}
		catch (Exception e)
		{
			throw new EntityException("getDiscussionTopicsPages Error ",e);
		}
		return results;
	}
	
	@Override
	public int addDiscussionTopics(DiscussionTopic discussionTopic,String courseInfoId)
	{
		try
		{
			StringBuilder hql = new StringBuilder(ALL_COURSE_INFO).append(CommonConstant.ONE_EQUALS_ONE).append(" AND ci.ciId=").append(courseInfoId);
			CourseInfo courseInfo = (CourseInfo) courseInfoDAO.getUniqueResultByHQL(hql.toString());
			discussionTopic.setCourseInfo(courseInfo);
			discussionTopic.setDtReply("0");
			discussionTopic.setDtScan("0");
			discussionTopic.setDtSign("F");
			discussionTopic.setDtDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			discussionTopicDAO.updateEntity(discussionTopic);
		}
		catch (Exception e)
		{
			throw new EntityException(" DiscussionTopicServiceImpl.addDiscussionTopics Error ",e);
		}
		return Constant.RESULT_SUCCESS;
	}
	
	@Override
	public 	DiscussionTopic addGetDiscussionTopicServiceById(String discussionTopicId)
	{
		DiscussionTopic discussionTopic=discussionTopicDAO.findById(discussionTopicId);
//		if(discussionTopic.getDtScan()==null||discussionTopic.getDtScan().equals(""))
//			discussionTopic.setDtScan("1");
//		else
//			discussionTopic.setDtScan(String.valueOf(Integer.valueOf(discussionTopic.getDtScan())+1));
//		discussionTopicDAO.updateEntity(discussionTopic); 
		return discussionTopic;
	}
	
	@Override
	public 	DiscussionTopic addGetDiscussionTopicServiceByIdStu(String discussionTopicId)
	{
		DiscussionTopic discussionTopic=discussionTopicDAO.findById(discussionTopicId);
		UserLogin userlogin = ClientUtil.getStudentLoginFromHttpSession(session);
		StringBuilder hql = new StringBuilder();
		hql.append(ALL_STUDENT_INFO).append(CommonConstant.ONE_EQUALS_ONE).append(" AND si.userLogin=?");
		StudentInfo student = studentInfoDAO.getUniqueResultByHQL(hql.toString(), userlogin);
		discussionTopic.addStudentInfo(student);
		if(discussionTopic.getDtScan()==null||discussionTopic.getDtScan().equals(""))
			discussionTopic.setDtScan("1");
		else
			discussionTopic.setDtScan(String.valueOf(Integer.valueOf(discussionTopic.getDtScan())+1));
		discussionTopicDAO.updateEntity(discussionTopic); 
		return discussionTopic;
	}
	
	@Override
	public 	DiscussionTopic getDiscussionTopicServiceById(String discussionTopicId)
	{
		return discussionTopicDAO.findById(Integer.valueOf(discussionTopicId));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public 	int deleteDiscussionTopic(String discussionTopicIdStr)
	{
		try {
			String[] ids = discussionTopicIdStr.split(Constant.LINE);
			for(String id : ids)
			{
				StringBuilder hql = new StringBuilder(ALL_DISCUSSION_TOPIC).append(CommonConstant.ONE_EQUALS_ONE).append(" AND dt.dtId=").append(id);
				DiscussionTopic discussionTopic = (DiscussionTopic) discussionTopicDAO.getUniqueResultByHQL(hql.toString());
				// 删除对应的回复
				Iterator<DiscussionStudentReply> iterator1 = discussionTopic.getDiscussionStudentReplies().iterator();
				while(iterator1.hasNext())
				{
					discussionStudentReplyDAO.turnTransient(iterator1.next());
				}
				Iterator<DiscussionTeacherReply> iterator2 = discussionTopic.getDiscussionTeacherReplies().iterator();
				while(iterator2.hasNext())
				{
					discussionTeacherReplyDAO.turnTransient(iterator2.next());
				}
				discussionTopicDAO.turnTransient(discussionTopic);
			}
		}
		catch (Exception e)
		{
			return Constant.RESULT_SUCCESS;
//			throw new EntityException(" DiscussionTopicServiceImpl.deleteDiscussionTopic Error ",e);
		}
		return Constant.RESULT_SUCCESS;
	}
	public List<DiscussionTopic> getDiscussionTopicByCIId(String courseInfoId)
	{
		StringBuilder hql = new StringBuilder(ALL_DISCUSSION_TOPIC).append(CommonConstant.ONE_EQUALS_ONE).append(" AND dt.courseInfo.ciId=").append(courseInfoId); 
		return discussionTopicDAO.getListByHQL(hql.toString()).size()==0 ? null : discussionTopicDAO.getListByHQL(hql.toString());
	}
	public List<DiscussionTopic> getDiscussionTopicByCId(String courseId)
	{
		StringBuilder hql = new StringBuilder(ALL_DISCUSSION_TOPIC).append(CommonConstant.ONE_EQUALS_ONE).append(" AND dt.courseInfo.courseTeacherRelation.course.CId=").append(courseId); 
		return discussionTopicDAO.getListByHQL(hql.toString());
	}
	public List<DiscussionTopic> getDiscussionTopicByTeaSession()
	{
		UserLogin userLogin = AdminUtil.getUserLoginFromHttpSession(session);
		StringBuilder hql = new StringBuilder(ALL_DISCUSSION_TOPIC).append(CommonConstant.ONE_EQUALS_ONE)
				.append(" AND dt.courseInfo.courseTeacherRelation.teacherInfo.userLogin.ulId=").append(userLogin.getUlId())
				.append(" ORDER BY dt.courseInfo.ciDateTime DESC "); 
		return discussionTopicDAO.getListByHQL(hql.toString());
	}
}
