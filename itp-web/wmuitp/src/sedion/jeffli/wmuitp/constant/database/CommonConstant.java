package sedion.jeffli.wmuitp.constant.database;


public final class CommonConstant 
{

	private CommonConstant() {}
	
	/**
	 * u know
	 */
	public static final String ONE_EQUALS_ONE				 = " WHERE 1=1 ";
	public static final String ONE_EQUALS_ZERO				 = " WHERE 1=0 ";
	
	/**
	 * data base constant
	 */
	public static final String FALSE   						 = "F"; 
	public static final String TRUE   						 = "T"; 
	
	/**
	 * permission type
	 */
	public static final String ADMIN_PER   					 = "adt"; 
	public static final String TEACHER_PER 					 = "tea";
	public static final String STUDENT_PER 					 = "stu";

	/**
	 * attachment path
	 */
	public static final String ATTACHMENT_PATH 	    		 = "/attachment/";
	public static final String ATTACHMENT_REAL_PATH 		 = "F:/attachment/";
	
	/**
	 * the configuration file path
	 * and  
	 * the beans
	 */
	public static final String APPLICATIONCONTEXT_XML_PATH 	 = "applicationContext.xml";
	
	public static final String SCHEDULER_FACTORY_BEAN_OF_AXP = "schedulerFactoryBean";						
	
}
