package sedion.jeffli.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class UIHelper {
	/**
	 * 弹出Toast消息
	 * @param msg
	 */
	public static void ToastMessage(Context cont,String msg)
	{
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void ToastMessage(Context cont,int msg)
	{
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void ToastMessage(Context cont,String msg,int time)
	{
		Toast.makeText(cont, msg, time).show();
	}
	
	/**
	 * 返回原activity
	 * @param context
	 */
	public static void FinishActivity(Activity context)
	{
		context.finish();		
	}
}