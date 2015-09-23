package sedion.jeffli.wmuitp.service.util;

import java.io.File;

import sedion.jeffli.wmuitp.constant.CourseInfoConstant;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.entity.CourseInfo;
import sedion.jeffli.wmuitp.util.DateUtil;
import sedion.jeffli.wmuitp.util.zxing.ZxingEncoderHelper;

public class CourseInfoUtil
{
	/**
	 * 获取	二维码储存地址
	 * @param courseInfo	课详情
	 * @return
	 */
	public static String getCourseInfoQrcode(CourseInfo courseInfo)
	{
		String picStr  =  DateUtil.getFormateDateSimple()+".png";
		String pathStr =  CourseInfoConstant.getQrCodePath()+"/"+picStr;
		
		if (!new File(CourseInfoConstant.getQrCodePath()).isDirectory())//生成路径
		{
			new File(CourseInfoConstant.getQrCodePath()).mkdir();
		}
		
		ZxingEncoderHelper.encode("http://"+Constant.WEB_IP+":8080/wmuitp/webservice/turnSCRPresent?courseInfoID="+courseInfo.getCiId(),350, 350,pathStr);
		
		return CourseInfoConstant.getAccessQrCodePath()+picStr;
	}
}
