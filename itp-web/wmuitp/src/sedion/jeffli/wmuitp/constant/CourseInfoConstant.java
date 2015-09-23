package sedion.jeffli.wmuitp.constant;

import sedion.jeffli.wmuitp.constant.main.Constant;

public class CourseInfoConstant {
	
	public static final String QR_CODE_PATH  			= Constant.getImgRealPath()+"/qrcode";		//二维码图片存储地址
	public static final String ACCESS_QR_CODE_PATH  	= Constant.getImgAccessPath()+"/qrcode/";	//二维码图片访问地址
	
	public static final String COURSE_INFO_URL			  = "../courseInfo/courseInfos";
	public static final String COURSE_INFO_REF			  = "courseInfos";
	public static final String COURSE_INFO_OBJECT		  = "courseInfo";
	public static final String COURSE_INFO_OBJECT_LIST    = "courseInfos";
	
	public static String getCourseInfoUrl()
	{
		return COURSE_INFO_URL;
	}
	public static String getCourseInfoRef()
	{
		return COURSE_INFO_REF;
	}
	public static String getCourseInfoObject()
	{
		return COURSE_INFO_OBJECT;
	}
	public static String getCourseInfoObjectList()
	{
		return COURSE_INFO_OBJECT_LIST;
	}
	public static String getQrCodePath()
	{
		return QR_CODE_PATH;
	}
	public static String getAccessQrCodePath()
	{
		return ACCESS_QR_CODE_PATH;
	}
	
	
}
