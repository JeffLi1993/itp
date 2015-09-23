package sedion.jeffli.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 
{
	public static String EncryptionByMD5(String message)
	{	
		StringBuffer buf = null;
		try 
 		{ 
 			MessageDigest md = MessageDigest.getInstance("MD5"); 
 			md.update(message.getBytes()); 
 			byte b[] = md.digest(); 
 	
 			int buffNum; 
 			buf = new StringBuffer(""); 
 			for (int offset = 0; offset < b.length; offset++)
 			{ 
 				buffNum = b[offset]; 
 				if(buffNum<0) buffNum+= 256; 
 				if(buffNum<16) 
 				buf.append("0"); 
 				buf.append(Integer.toHexString(buffNum)); 
 			} 
 		} 
 		catch (NoSuchAlgorithmException e) 
 		{ 
 			e.printStackTrace(); 
 		} 
		return buf.toString();//32位的加密 
	}
}
