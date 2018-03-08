package io.sunny.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodAES {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodAES.class);
	private static final String outside_iv = "2B65571F90153BA5";// 随意16位 16进制 身份证
	private static final String outside_code = "584806";

	public static void main(String []args){
		System.out.println("{\"mercId\":2,\"mercNm\":2,\"mercAddr\":\"测试\"}");
	   String str=MethodAES.outsideEncrypt("{\"mercId\":\"8880187310000001\",\"product\":[{\"barcode\":\"01235504\",\"goodsNum\":\"10\",\"cid\":\"800\"},{\"barcode\":\"02000002\",\"goodsNum\":\"10\",\"cid\":\"800\"}]}");
	   System.out.println(str);
	}
	
	public static String outsideEncrypt(String password) {
		return MethodAES.encrypt(password, outside_code, outside_iv);
	}

	public static String outsidDecrypt(String password) {
		return MethodAES.decrypt(password, outside_code, outside_iv);
	}


	/**
	 * AES加密字符串
	 * 
	 * @param password
	 *            需要被加密的字符串
	 * @param code
	 *            加密需要的密码
	 * @return 密文
	 */
	private static String encrypt(String password, String code, String iv) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(code.getBytes());
			kgen.init(128, random);// 利用用户密码作为随机数初始化出// 64位的key生产者
			// 加密没关系，SecureRandom是生成安全随机数序列，code.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有code就行
			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回// null。
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			byte[] bytepassword = password.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv.getBytes()));// 初始化为加密模式的密码器
			byte[] result = cipher.doFinal(bytepassword);// 加密
			return Base64.encodeBase64String(result);
		} catch (Exception e) {
			LOGGER.error("encrypt password:{},code:{},e:{}", password, code, e);
			return null;
		}
	}

	/**
	 * 解密AES加密过的字符串
	 * 
	 * @param password
	 *            AES加密过过的内容
	 * @param code
	 *            加密时的密码
	 * @return 明文
	 */
	private static String decrypt(String password, String code, String iv) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(code.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv.getBytes()));// 初始化为解密模式的密码器
			byte[] result = cipher.doFinal(Base64.decodeBase64(password));
			return new String(result, "UTF-8"); // 明文
		} catch (Exception e) {
			LOGGER.error("decrypt password:{},code:{},e:{}", password, code, e);
			return null;
		}
	}
}
