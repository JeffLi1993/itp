package sedion.jeffli.wmuitp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import sedion.jeffli.wmuitp.util.DbConn;

public class DbConnListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		ServletContext servletContext = servletContextEvent.getServletContext();
		String className = servletContext.getInitParameter("className");
		String connUrl = servletContext.getInitParameter("connUrl");
		String uname = servletContext.getInitParameter("uname");
		String psw = servletContext.getInitParameter("psw");
		DbConn.getconn(className, connUrl, uname, psw);					//Web容器完全启动前调用
	}
	
}
