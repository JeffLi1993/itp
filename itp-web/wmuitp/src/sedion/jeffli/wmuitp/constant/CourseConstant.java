package sedion.jeffli.wmuitp.constant;

import sedion.jeffli.wmuitp.constant.main.Constant;

public class CourseConstant 
{
	public static final String QR_CODE_PATH  		= Constant.getImgRealPath()+"/qrcode";		//二维码图片存储地址
	public static final String ALL_QR_CODE_PATH  	= "/qrcode/";	        					//二维码图片全路径地址

	public static final String COURSE_URL			= "../course/courses";
	public static final String COURSE_REF			= "courses";
	public static final String COURSE_OBJECT		= "course";
	public static final String COURSE_OBJECT_LIST   = "courses";
	
	
	public static String getCourseUrl()
	{
		return COURSE_URL;
	}
	public static String getCourseRef()
	{
		return COURSE_REF;
	}
	public static String getCourseObject()
	{
		return COURSE_OBJECT;
	}
	public static String getCourseObjectList()
	{
		return COURSE_OBJECT_LIST;
	}
	
}
