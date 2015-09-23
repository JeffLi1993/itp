package sedion.jeffli.constant;

public class MainLeftFragConstant 
{
	public static final String NET_WORK_UNAVAILABLE			= "网络不可用";
	public static final String QR_CODE_SUCCESS 				= "二维码扫描签到成功";
	public static final String QR_CODE_FAILE				= "二维码扫描签到失败";
	public static final String QR_CODE_IS_NOT_GOOD			= "二维码无法识别";
	public static final String COURSE_IS_NOT_RUNNING		= "课程未开启，请开启后签到";
	
	public static String getNetWorkUnavailable() {
		return NET_WORK_UNAVAILABLE;
	}

	public static String getQrCodeSuccess() {
		return QR_CODE_SUCCESS;
	}

	public static String getQrCodeFaile() {
		return QR_CODE_FAILE;
	}

	public static String getQrCodeIsNotGood() {
		return QR_CODE_IS_NOT_GOOD;
	}

	public static String getCourseIsNotRunning() {
		return COURSE_IS_NOT_RUNNING;
	}
	
}
