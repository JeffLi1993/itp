package sedion.jeffli.wmuitp.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import sedion.jeffli.wmuitp.exception.QuartzException;

public class ScheduleStartListener implements ServletContextListener
{
	@Override
	public void contextDestroyed(ServletContextEvent sce) 
	{
		
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) 
	{
		try 
		{
			recovery();
		} 
		catch (Exception e)
		{
			throw new QuartzException(" ScheduleStartListener contextInitialized ERROR!!",e);
		}
	}
	
	
	public void recovery() 
	{
		
        Scheduler scheduler = null;
        
        try {
        	
        	SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        	scheduler = schedulerFactory.getScheduler();//可以通过SchedulerFactory创建一个Scheduler实例
        	List<String> triggerGroups = scheduler.getTriggerGroupNames();//获取调度器中所有的触发器组
        	System.out.println("调度器中所有的触发器组 size():"+triggerGroups.size());
        	
        	if(triggerGroups != null && triggerGroups.size() != 0)//重新恢复在triggerGroups组中所有的触发器
        	{
        		for (int i = 0; i < triggerGroups.size(); i++) 
        		{
            			TriggerKey triggerKey = TriggerKey.triggerKey(triggerGroups.get(i), triggerGroups.get(i));
                		System.out.println("triggerKey:"+triggerKey);
                		
            			Trigger tg = scheduler.getTrigger(triggerKey);//获取trigger
            			System.out.println(triggerKey+" -> 执行时间 :"+tg.getNextFireTime());
            			
        				scheduler.rescheduleJob(triggerKey, tg);//按新的trigger重新设置job执行
            	}
        	}
        	
        	scheduler.start();
        	
        } 
        catch (Exception e) 
        {
        	throw new QuartzException("ScheduleStartListener  recovery() Error!", e);
        }
    }
}
