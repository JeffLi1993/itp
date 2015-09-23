package test.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;


public class QuartzTest
{

    public void run(String date, int id)throws Exception 
    {

    	
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();//可以通过SchedulerFactory创建一个Scheduler实例
        
        //设置工作详情
        JobDetail job = newJob(JobTest.class) 
            .withIdentity("job_"+id, "test"+id) 		// (String name, String group)把触发器在集群节点实例命名的组只是为了区分（伐木）从什么地方定问调度重新执行此作业，如果它是正在进行时调度下去...
            .requestRecovery() 							
            .build();
       
        job.getJobDataMap().put("testId", id);		//设置存储参数(不需要可删除)
        
       
        Date startDate = FormatDate.stringToDateAll(date);//Date转String
        //设置触发器
        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
		        .withIdentity("test"+id, "test"+id)//withIdentity("trigger", "group")
		        .startAt(startDate)
		        .build();
        
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        System.out.println("------- Start Scheduler ----------------");
    }
    
    public static void main(String[] args) throws Exception 
    {
    	QuartzTest quartzOverdue = new QuartzTest();
		quartzOverdue.run("2014-07-02 10:00:00",6666);
	}
}

