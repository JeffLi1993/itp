package sedion.jeffli.wmuitp.security.crypto;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * RSA加密解密工具
 * @author Jeff Li
 * @version 1.0
 */
public abstract class RSAUtil
{

	// 非对称加密密钥算法
	public static final String KEY_ALGORITHM = "RSA";

	//公钥
	private static final String PUBLIC_KEY = "jeff";//可修改

	//私钥
	private static final String PRIVATE_KEY = "li";//可修改

	//RSA密钥长度默认1024位， 密钥长度必须是64的倍数， 范围在512至65536位之间。
	private static final int KEY_SIZE = 1024;

	/**
	 * 私钥解密
	 * @param 	data  	待解密数据
	 * @param 	key		私钥
	 */
	public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception 
	{
		
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);// 取得私钥
		KeyFactory 			keyFactory 	 = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey 			privateKey   = keyFactory.generatePrivate(pkcs8KeySpec);// 生成私钥

		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);// 对数据解密

		return cipher.doFinal(data);
	}

	/**
	 * 公钥解密
	 * @param data		待解密数据
	 * @param key		公钥
	 */
	public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception 
	{

		X509EncodedKeySpec 	x509KeySpec = new X509EncodedKeySpec(key);// 取得公钥
		KeyFactory 			keyFactory 	= KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey 			publicKey 	= keyFactory.generatePublic(x509KeySpec);// 生成公钥

		
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);// 对数据解密

		return cipher.doFinal(data);
	}

	/**
	 * 私钥加密
	 * @param data 	待加密数据
	 * @param key	私钥
	 */
	public static byte[] encryptByPrivateKey(byte[] data, byte[] key)  throws Exception 
	{

		
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);// 取得私钥
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);// 生成私钥

		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);// 对数据加密

		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥加密
	 * @param data 待加密数据
	 * @param key  公钥
	 */
	public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception 
	{
		
		X509EncodedKeySpec 	x509KeySpec = new X509EncodedKeySpec(key);// 取得公钥
		KeyFactory 			keyFactory  = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey 			publicKey   = keyFactory.generatePublic(x509KeySpec);// 生成公钥

		
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);// 对数据加密

		return cipher.doFinal(data);
	}


	/**
	 * 获取私钥
	 * @param keyMap 密钥Map
	 */
	public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception 
	{

		Key key = (Key) keyMap.get(PRIVATE_KEY);

		return key.getEncoded();
	}

	/**
	 * 获得公钥
	 * @param keyMap 密钥Map
	 */
	public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception 
	{

		Key key = (Key) keyMap.get(PUBLIC_KEY);

		return key.getEncoded();
	}

	/**
	 * 初始化密钥
	 * 
	 * @return Map 密钥Map
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {

		
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);// 实例化密钥对生成器
		keyPairGen.initialize(KEY_SIZE);// 初始化密钥对生成器
		
		KeyPair 	  keyPair 	 = keyPairGen.generateKeyPair();// 生成密钥对
		RSAPublicKey  publicKey  = (RSAPublicKey) keyPair.getPublic();// 公钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();// 私钥
		
		Map<String, Object> keyMap = new HashMap<String, Object>(2);// 封装密钥
		keyMap.put(PUBLIC_KEY , publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);

		return keyMap;
	}
}
