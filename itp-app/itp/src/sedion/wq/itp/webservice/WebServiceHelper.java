package sedion.wq.itp.webservice;

public class WebServiceHelper extends HttpClient
{
		/**错误 **/
		public static final int 		RESULT_NET_FAIL 	  	= -4;
		/**成功**/
		public static final int 		SUCCESS    		= 1	;
		/**失败**/
		public static final int 		FAILURE    		= 0	;
		/**其他失败**/
		public static final int 		OTHER_FAILURE 	= -1;
		/**连接超时**/
		protected static final int 		TIME_OUT   		= -2;
		/**补充的数据**/
		protected static final String 	EXTRA   	= "extra";
		/** 请求回应 **/
		protected static final String	RESULTS 	= "results";
		
		/** IP地址或域名 **/
		public 	  static String IP 					= "http://192.168.232.36:8080";
		/** 项目名 **/
		public static final String   PROJECT 		= "/wmuitp/";
		/** Webservice提供地址 **/
		protected static String URL 				= IP + PROJECT + "webservice/";
		
		
		/********************** 下面是方法名 **************************/				
		/** 用户登陆 		**/
		protected static final String LOGIN 					   = "login";
		/** 保存 学生课程关系  **/
		protected static final String SAVE_STUDENT_COURSE_RELATION = "turnSCRPresent";
		
}
