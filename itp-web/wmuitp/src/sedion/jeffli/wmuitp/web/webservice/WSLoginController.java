package sedion.jeffli.wmuitp.web.webservice;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sedion.jeffli.wmuitp.base.MainBase;
import sedion.jeffli.wmuitp.constant.main.Constant;
import sedion.jeffli.wmuitp.entity.UserLogin;

@RequestMapping(value = "/webservice")
@Controller
public class WSLoginController extends MainBase 
{
	
	@RequestMapping(value = "/login")
	public @ResponseBody String login( String username,  String  password) throws IOException 
	{
		//设置密码用MD5加密**********
 		try 
 		{ 
 			MessageDigest md = MessageDigest.getInstance("MD5"); 
 			md.update(password.getBytes()); 
 			byte b[] = md.digest(); 
 	
 			int buffNum; 
 			StringBuffer buf = new StringBuffer(""); 
 			for (int offset = 0; offset < b.length; offset++)
 			{ 
 				buffNum = b[offset]; 
 				if(buffNum<0) buffNum+= 256; 
 				if(buffNum<16) 
 				buf.append("0"); 
 				buf.append(Integer.toHexString(buffNum)); 
 			} 
 			password=buf.toString();//32位的加密 
 			System.out.println("密码="+buf);
 		} 
 		catch (NoSuchAlgorithmException e) 
 		{ 
 			e.printStackTrace(); 
 		} 
 		//********
		
		Result 	  result 		= new Result();
		UserLogin userLogin 	= null;
		
		int resultState = userLoginService.checkUserLoginByPhone(username,password);
		
		if(resultState == Constant.RESULT_SUCCESS)
		{
			userLogin = new UserLogin();
			
			result.setResults(Constant.RESULT_SUCCESS);
			userLogin.setUlId(userLoginService.findUserLoginByULName(username).getUlId());
			
			userLogin.setMessageReceives(null);
			userLogin.setMessageSenders(null);
			userLogin.setTeacherInfos(null);
			userLogin.setStudentInfos(null);
			
			System.out.println("... phone login success!!");
		}
		else if(resultState == Constant.RESULT_FAIL)
		{
			result.setResults(Constant.RESULT_FAIL);
			
			System.out.println("... phone login  fail!!");
		}
		
		/**----------------------JSON-----------------------------**/
		JSONObject resultJsonObject 		= (JSONObject) JSON.toJSON(result);
		
		if(userLogin != null)
		{
			JSONObject userLoginJsonObject	= (JSONObject) JSON.toJSON(userLogin);
			
			return joinJsonObjects(resultJsonObject, userLoginJsonObject); //JSON拼接返回 格式: "{\"results\":1,\"ulId\":6}";
		}
		
		else
			return resultJsonObject.toString();
		
	}
	
	
}
