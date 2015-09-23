package test.quartz;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzLoanAutoRelease {

    private static Logger _log = LoggerFactory.getLogger(QuartzLoanAutoRelease.class);
    /**
     * 借款初审结束后设定计划发布时间，到达指定时间后将其发布
     * */
    public void run(int autoReleaseLong, int id, int autoSubLong, String username, String bidConfigName) 
        throws Exception {

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        System.out.println("------- Scheduling Jobs ------------------");
        //设置工作详情
        JobDetail job = newJob(JobLoanAutoRelease.class) 
            .withIdentity("loanReleasejob_"+id, "loanRelease"+id) 		// put triggers in group named after the cluster node instance just to distinguish (in logging) what was scheduled from where
            .requestRecovery() 											// ask scheduler to re-execute this job if it was in progress when the scheduler went down...
            .build();
        //设置存储参数
        job.getJobDataMap().put("loanId", id);
        job.getJobDataMap().put("username", username);
        job.getJobDataMap().put("autoSubLong", autoSubLong);
        job.getJobDataMap().put("bidConfigName", bidConfigName);
        //设置触发器
        SimpleTrigger trigger = newTrigger()
            .withIdentity("loanRelease"+id, "loanRelease"+id)
            .startAt(futureDate(autoReleaseLong, IntervalUnit.SECOND))
            .withSchedule(simpleSchedule()
            		.withRepeatCount(0)
                    .withIntervalInSeconds(0))
            .build();
        
        sched.scheduleJob(job, trigger);
      
        sched.start();
        System.out.println("------- Started Scheduler ----------------");
    }
}

