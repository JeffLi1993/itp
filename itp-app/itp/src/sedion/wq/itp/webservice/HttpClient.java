package sedion.wq.itp.webservice;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class HttpClient 
{
	
	/**连接超时**/
	private static final String TIME_OUT = "{\"results\":-2}";
	
	/** User-Agent 用户识别 **/
	private static final String User_Agent = "...";

	/**
	 * 使用post请求获取数据
	 * @param uriAPI 网址
	 * @param params 请求参数
	 * @return
	 * @throws JSONException 
	 */
	private static JSONObject post(String uriAPI, List<NameValuePair> params)
	{
		org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
		JSONObject result = null;
		
		try 
		{
			// 使用post方式
			HttpPost httpRequest = new HttpPost(uriAPI);
			
			if (params !=null && params.size()>0) 
			{
				//设置请求参数和编码格式
				httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}
			
			//设置请求用户识别
			//httpRequest.addHeader("User-Agent", User_Agent);
			//设置请求超时时间为5s
			httpRequest.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,5000);
			//读取超时时间
			httpRequest.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
			
			//发送请求并获取响应
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			
			
			//根据不同的请求结果代码进行处理数据
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) 
			{
				//判断请求成功并返回数据
				//解析返回的结果，将返回的信息整成字符串
				String strResult = EntityUtils.toString(httpResponse.getEntity());
				//转化成json对象，便于操作，json对象是类似于hashmap
				result = new JSONObject(strResult);
				
				System.out.println("JSONObject -> result:"+result);
				
				//如果返回的数据时空
				if (result.optString("results").equals("null")) 
				{
					result = null;							
				}
				
			}
			else
			{
				//请求失败
				Log.e("请求失败", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
				result = null;
			}
		}
		catch(ConnectTimeoutException cException)
		{
			try 
			{
				result = new JSONObject(TIME_OUT);
			} 
			catch (Exception e) 
			{
				Log.e("webservice json 转化错误", e.toString());
			}
		}
		catch (Exception e)
		{
			Log.e("post Error", e.toString());
			e.printStackTrace();
			result = null;
		}
		finally
		{
			try 
			{
				httpClient.getConnectionManager().shutdown();
			} 
			catch (Exception e2)
			{
				Log.e("关闭连接失败", e2.toString());
			}
		}
		return result;
	}
	
	/**
	 * 使用post请求获取数据
	 * @param uriAPI 网址
	 * @param params 请求参数
	 * @return
	 */
	public static JSONObject post(String uri, String method, List<NameValuePair> params) 
	{
		return post(uri + method, params);
	}
	
	/**
	 * 使用get请求获取数据
	 * @param uriAPI  网址
	 * @return
	 */
	public static JSONObject get(String uriAPI,HttpParams params)
	{
		try 
		{
			//实例化get请求
			HttpGet httpRequest  = new HttpGet(uriAPI);
			if (params !=null)
			{
				//设置参数
				httpRequest.setParams(params);
			}
			//执行
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200)
			{
	            String strResult = EntityUtils.toString(httpResponse.getEntity());
	            JSONObject result = new JSONObject(strResult);
	            System.out.println("result:"+result);
	            
	            return result;
            } 
			else
            {
				return null;
            }
		} 
		catch (Exception e) 
		{
			Log.e("get Error", e.toString());
			return null;
		}
	}
	
	
	/** post 访问
	 * @param url
	 * @param reqEntity
	 * @return
	 */
	public static JSONObject post(String url ,MultipartEntity reqEntity )
	{
		JSONObject result  = null;
		HttpParams parms = new BasicHttpParams();
		parms.setParameter("charset", HTTP.UTF_8);
		org.apache.http.client.HttpClient client = new DefaultHttpClient(parms);
		try 
		{
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Charsert", HTTP.UTF_8);
			if (reqEntity != null) 
			{
				//添加参数
				httpPost.setEntity(reqEntity);
			}
			HttpResponse response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				HttpEntity entity = response.getEntity();
				String respnseString = EntityUtils.toString(entity);
				result = new JSONObject(respnseString);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
		finally
		{
			try 
			{
				//关闭连接
				client.getConnectionManager().shutdown();
			} 
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return result;
	}	
}
