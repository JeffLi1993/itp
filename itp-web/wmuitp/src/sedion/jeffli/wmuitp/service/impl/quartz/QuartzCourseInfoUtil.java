package sedion.jeffli.wmuitp.service.impl.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import sedion.jeffli.wmuitp.util.FormatDate;


public class QuartzCourseInfoUtil
{

	 public void run(String date, int id) throws SchedulerException, ParseException
	    {

	    	
	        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	        Scheduler scheduler = schedulerFactory.getScheduler();//可以通过SchedulerFactory创建一个Scheduler实例
	        
	        //设置工作详情
	        JobDetail job = newJob(JobCourseInfo.class) 
	            .withIdentity("job_"+id, "courseInfo"+id) 		// (String name, String group)把触发器在集群节点实例命名的组只是为了区分（伐木）从什么地方定问调度重新执行此作业，如果它是正在进行时调度下去...
	            .requestRecovery() 							
	            .build();
	       
	        job.getJobDataMap().put(JobCourseInfo.COUSER_INFO, id);		//设置存储参数(不需要可删除)
	        
	       
	        Date startDate = FormatDate.stringToDateAll(date);//Date转String
	        //设置触发器
	        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
			        .withIdentity("courseInfo"+id, "courseInfo"+id)//withIdentity("trigger", "group")
			        .startAt(startDate)
			        .build();
	        
	        scheduler.scheduleJob(job, trigger);
	        scheduler.start();
	        System.out.println("------- QuartzCourseInfoUtil Scheduler 安排成功 ----------------");
	    }
	 
	 	public void stop(String date, int id) throws SchedulerException, ParseException
	    {

	    	
	        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	        Scheduler scheduler = schedulerFactory.getScheduler();//可以通过SchedulerFactory创建一个Scheduler实例
	        
	        //设置工作详情
	        JobDetail job = newJob(JobCourseInfoStop.class) 
	            .withIdentity("job__"+id, "courseInfoid"+id) 		// (String name, String group)把触发器在集群节点实例命名的组只是为了区分（伐木）从什么地方定问调度重新执行此作业，如果它是正在进行时调度下去...
	            .requestRecovery() 							
	            .build();
	       
	        job.getJobDataMap().put(JobCourseInfoStop.COUSER_INFO, id);		//设置存储参数(不需要可删除)
	        
	       
	        Date startDate = FormatDate.stringToDateAll(date);//Date转String
	        //设置触发器
	        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
			        .withIdentity("courseInfoid"+id, "courseInfoid"+id)//withIdentity("trigger", "group")
			        .startAt(startDate)
			        .build();
	        
	        scheduler.scheduleJob(job, trigger);
	        scheduler.start();
	        System.out.println("------- QuartzCourseInfoUtil Scheduler 安排成功 ----------------");
	    }

}

