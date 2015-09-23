package sedion.wq.itp.webservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

import sedion.jeffli.entity.Results;
import android.util.Log;

public class WebService extends WebServiceHelper
{	
	
	/**
	 * 用户登陆，登陆成功，返回用户的编号，
	 * 用户名或密码错误，返回0.
	 * 登录成功，返回1.
	 * 网络连接失败，返回1
	 * @param userName	用户名
	 * @param password	密码
	 * @return
	 */
	public static Results login(String userName,String password)
	{
		try 
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("username", userName));
			params.add(new BasicNameValuePair("password", password));
			
			JSONObject result =  post(URL, LOGIN, params);
			
			//System.out.println("result:.."+result);
			Results results =  JSON.parseObject(result.toString(), Results.class); 

			//System.out.println("result:..."+results);
			if (result!=null) 
			{
				return results;
			}
		} 
		catch (Exception e) 
		{
			Log.e("login failure", e.toString());
		}
		
		return null;
	}
	
	
	
}
