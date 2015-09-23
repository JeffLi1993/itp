package sedion.wq.itp.webservice;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

import sedion.jeffli.entity.Results;
import android.util.Log;

public class CourseWebService extends WebServiceHelper
{	
	
	/**
	 * 保存 学生课程关系
	 * @param urlStr       url
	 * @param userLoginID  用户ID
	 * @return
	 */
	public static Results saveStudentCourseRelation(String urlStr,String  userLoginID)
	{
		try 
		{
			/**拼接url**/
			String urlString = urlStr+ "&userLoginID="+userLoginID;
			
			System.out.println("urlString："+urlString);
			
			JSONObject result =  post(urlString, null);
			
			System.out.println("result:"+result);
			Results results =  JSON.parseObject(result.toString(), Results.class); 
			
			return results;
		} 
		catch (Exception e) 
		{
			Log.e("save failure", e.toString());
		}
		return null;
	}
	
	
	
}
