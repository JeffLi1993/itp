package sedion.jeffli.wmuitp.service.impl.quartz;


import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import sedion.jeffli.wmuitp.base.QueryBase;
import sedion.jeffli.wmuitp.exception.QuartzException;
import sedion.jeffli.wmuitp.service.CourseInfoService;
import sedion.jeffli.wmuitp.util.DbConn;


public class JobCourseInfoStop implements Job{
	
	public  static String COUSER_INFO	=	"courseInfo";
	
    public JobCourseInfoStop() {}    
  
    public void execute(JobExecutionContext context)throws JobExecutionException
    {  
    	
    	JobDataMap data = context.getJobDetail().getJobDataMap();
    	System.out.println("任务中的data.courseInfoId : "+data.getInt(COUSER_INFO)); //不需要可删除
    	try
    	{
    		System.out.println("停止签到启动.....");
    		//courseInfoService.turnCIStateTureByCourseInfoId(courseInfoId);
    		
    		DbConn.exceptQuery("update course_info set CI_State = 'F' where CI_ID='"+data.getInt(COUSER_INFO)+"'");
    		
    		System.out.println("停止签到启动Success.....");
    	}
    	catch (Exception e) 
    	{
			throw new QuartzException("JobCourseInfoStop execute() ERROR !!", e);
		}
    }  
    
    
    public static void removeJob(JobKey jobKey, TriggerKey tiKey)throws SchedulerException
    { 
    	
    	SchedulerFactory sf 	= new StdSchedulerFactory();
        Scheduler 		 sched 	= sf.getScheduler();  
        
        sched.pauseTrigger(tiKey);			//停止触发器  
        sched.unscheduleJob(tiKey);			//移除触发器  
        sched.deleteJob(jobKey);			//删除任务  
        
    } 
}
