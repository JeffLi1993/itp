package test.quartz;


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

import sedion.jeffli.wmuitp.exception.QuartzException;


public class JobTest implements Job{
    
    public JobTest() {}    
  
    public void execute(JobExecutionContext context)throws JobExecutionException
    {  
    	
    	JobDataMap data = context.getJobDetail().getJobDataMap();
    	System.out.println("data.testId : "+data.getInt("testId")); //不需要可删除
    	
    	try
    	{
    		System.out.println("添入需要加入任务的具体操作");
    	}
    	catch (Exception e) 
    	{
			throw new QuartzException("JobTest execute() ERROR !!", e);
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
