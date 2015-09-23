package sedion.jeffli.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;


/**
 * 字符串操作工具类
 * @author WWF
 * @version 1.0
 */
public class StringUtil 
{
	/**
	 * 将字符串切割并剔除掉空的字符串
	 * @param value 需要切割的字符串
	 * @param regularExpression 分隔符
	 * @return 字符串数组
	 */
	public static String[] splitAndTrim(String value,String regularExpression)
	{
		try 
		{
			String[] values = value.split(regularExpression);
			int num=0;
			
			for (int i = 0; i < values.length; i++) 
			{
				if (!values[i].trim().equals("")) 
				{
					num++;
				}
			}
			
			String[] resultValues = new String[num];
			
			for (int i = 0; i < resultValues.length; i++)
			{
				resultValues[i] = values[i];
			}
			
			return resultValues;
		}
		catch (Exception e) 
		{
			Log.e("字符串切割成数组错误", e.toString());
			return null;
		}
		
	}
	
	/**
	 * 将List集合转化为数组 
	 * @param list 字符串集合
	 * @return 字符串数组
	 */
	public static String[] List2Array(List<String> list)
	{
		try 
		{
			String[] strings = new String[list.size()];
			
			for (int i = 0; i < list.size(); i++)
			{
				strings[i]  = list.get(i);
			}
			
			return strings;
		} 
		catch (Exception e) 
		{
			Log.e("list转数组错误", e.toString());
			return null;
		}
	}
	/**
	  * 验证手机号码、电话号码是否有效
	  *  手机号前面加86的情况也考虑
	  * 新联通  
	　　  *（中国联通+中国网通）手机号码开头数字 130、131、132、145、155、156、185、186
	　　  * 新移动
	　　  * （中国移动+中国铁通）手机号码开头数字 134、135、136、137、138、139、147、150、151、152、157、158、159、182、183、187、188
	　　  * 新电信
	　　   * （中国电信 +中国卫通）手机号码开头数字 133、153、189、180
	  * 座机：
	  *3/4位区号（数字）+ “-” + 7/8位（数字）+ “-”+数字位数不限
	  *说明：“-”+数字位数不限；这段可有可无
	  */
	public static String checkPhone(String phone) 
	{
		if (null != phone) 
		{
			String reisphoto = phone.replace("，", ",").replace(";", ",")
					.replace("；", ",").replace("　", ",").replace(" ", ",")
					.replace("/", ",").replace("\\", ",");
			String[] photo1 = reisphoto.split(",");
			String[] photo2 = new String[photo1.length];
			boolean isfirst;
			
			if (null != photo1 && photo1.length > 0) 
			{
				for (int i = 0; i < photo1.length; i++) 
				{
					isfirst = false;
					
					if (photo1[i]
							.matches("(^[0-9]{3,4}-[0-9]{3,8}$)|^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|2|3|5|6|7|8|9])\\d{8}$")) 
					{
						photo2[i] = photo1[i];
						isfirst = true;
					}
					
					// 第二规则 “-”+数字位数不限 和手机号前面加86的情况也考虑
					if (!isfirst)
					{
						if (photo1[i]
								.matches("(^[0-9]{3,4}-[0-9]{3,8}-[0-9]{0,100}$)|^((\\+86)|(86))?(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|2|3|5|6|7|8|9])\\d{8}$")) 
						{
							photo2[i] = photo1[i];
						}
					}
				}
				// 如果两个电话 只用一个
				if (photo2.length > 0) 
				{
					return photo2[0];
				}
			}
		}
		return null;
	}
	
	/**
	 * 判断给定字符串是否空白串。
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input)
	{
		if (input == null || input.trim().equals(""))
		{
			return true;
		}
		
		for (int i = 0; i < input.length(); i++) 
		{
			char c = input.charAt(i);
			
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') 
			{
				return false;
			}
		}
		return true;
	}
	
    /**
     * 验证是不是手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNum(String mobiles)
    {     
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");     
        Matcher m = p.matcher(mobiles);     
        return m.matches();     
    }

}
