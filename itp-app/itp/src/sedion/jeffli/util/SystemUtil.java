package sedion.jeffli.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 检查系统参数工具
 * @author WQ
 * @version 1.0
 */

public class SystemUtil
{	
	/**
	 * 检查系统的网络或wifi是否连接
	 * @param context 上下文
	 * @return 是否连通
	 */
	public static boolean isNetworkConnected(Context context) 
	{
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = cm.getActiveNetworkInfo();
		
		if (network != null&&network.isConnectedOrConnecting())
		{
			return network.isAvailable();
		}	
		return false;
	}
	
	/**
	 * 获取当前网络类型
	 * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
	 */
	@SuppressLint("DefaultLocale")
	public static int getNetworkType(Context context) 
	{
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		
		if (networkInfo == null) 
		{
			return netType;
		}		
		
		int nType = networkInfo.getType();
		
		if (nType == ConnectivityManager.TYPE_MOBILE) 
		{
			String extraInfo = networkInfo.getExtraInfo();
			
			if(!StringUtil.isEmpty(extraInfo))
			{
				if (extraInfo.toLowerCase().equals("cmnet")) 
				{
					netType = 3;
				} 
				else 
				{
					netType = 2;
				}
			}
		} 
		else if (nType == ConnectivityManager.TYPE_WIFI) 
		{
			netType = 1;
		}
		
		return netType;
	}
}
