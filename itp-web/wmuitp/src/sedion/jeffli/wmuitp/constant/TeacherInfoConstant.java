package sedion.jeffli.wmuitp.constant;

import java.io.File;

import sedion.jeffli.wmuitp.constant.main.Constant;

public class TeacherInfoConstant {
	
	public static final String TEACHER_IMG_PATH  		= Constant.getImgRealPath()+File.separator+"teacherImg";		//二维码图片存储地址
	
	public static final String TEACHER_URL			= "../teacherInfo/teacherInfos";
	public static final String TEACHER_REF			= "teacherInfos";
	public static final String TEACHER_OBJECT		= "teacherInfo";
	public static final String TEACHER_OBJECT_LIST  = "teacherInfos";
	
	
	public static String getTeacherInfoUrl()
	{
		return TEACHER_URL;
	}
	public static String getTeacherInfoRef()
	{
		return TEACHER_REF;
	}
	public static String getTeacherInfoObject()
	{
		return TEACHER_OBJECT;
	}
	public static String getTeacherInfoObjectList()
	{
		return TEACHER_OBJECT_LIST;
	}
}
