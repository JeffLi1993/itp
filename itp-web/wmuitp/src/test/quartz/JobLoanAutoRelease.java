package test.quartz;


import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 借款初审结束后设定计划发布时间，到达指定时间后将其发布
 * */
public class JobLoanAutoRelease implements Job{
	//private static Logger _log = LoggerFactory.getLogger(JobLoanAutoRelease.class);
    
    public JobLoanAutoRelease() {}    
  
    public void execute(JobExecutionContext context)  
            throws JobExecutionException {  
    	
    	JobDataMap data = context.getJobDetail().getJobDataMap();
    	//_log.info("开始执行自动发布借款(借款编号为 = "+data.getInt("loanId")+"): " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));  
    	
    	/**
    	 * 待做的事情
    	 * */
    	try{
    		System.out.println("...");
    	}catch (Exception e) {
			e.printStackTrace();
			//_log.info("执行自动发布借款异常(借款编号为 = "+data.getInt("loanId")+"): " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())); 
		}
    }  
    
    
}



