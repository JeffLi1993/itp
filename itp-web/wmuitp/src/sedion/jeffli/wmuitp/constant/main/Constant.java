package sedion.jeffli.wmuitp.constant.main;

import java.io.File;

public class Constant {
	public static final String WEB_IP = "192.168.1.104";
	public static final String WEB_IP_PORT = WEB_IP + ":8080/wmuitp";

	public static final String QR_CODE = WEB_IP + ":8080";

	public static final String IMG_REAL_PATH = "E:" + File.separator
			+ "wmuitpImg";// File.separator在window系统就等于'/'
	public static final String CACHE_PATH = "E:" + File.separator + "cache"
			+ File.separator;// File.separator在window系统就等于'/'
	public static final String IMG_ACCESS_PATH = "/wmuitphtml";
	/**
	 * 删除对象列中的分隔符
	 */
	public static final String LINE = "-";
	public static final String COMMA = ",";

	/**
	 * 
	 */
	public static final String INIT_PASSWORD = "e10adc3949ba59abbe56e057f20f883e";//初始密码123456,该值为经过MD5加密后的值

	/**
	 * 返回String状态
	 */
	public static final int RESULT_SUCCESS = 1;
	public static final int RESULT_FAIL = 0;

	public static final int RESULT_EXIST = -1;

	public static String getWebIpPort() {
		return WEB_IP_PORT;
	}

	public static String getQrCode() {
		return QR_CODE;
	}

	public static String getImgRealPath() {
		return IMG_REAL_PATH;
	}

	public static String getImgAccessPath() {
		return IMG_ACCESS_PATH;
	}

	public static String getCachePath() {
		return CACHE_PATH;
	}

}
