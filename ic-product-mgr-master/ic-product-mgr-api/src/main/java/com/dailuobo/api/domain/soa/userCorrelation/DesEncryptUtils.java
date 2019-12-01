package com.dailuobo.api.domain.soa.userCorrelation;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * DesEncryptUtils<br/>
 * 描述: DES方式加密
 * 
 * @author huangwei
 * @since 2016-1-5<br/>
 */
public class DesEncryptUtils {
	
	/** The Constant DES. */
	private final static String DES = "DES";

	/**
	 * 根据键值进行加密
	 */
	public static String encrypt(String data, String key) throws Exception {
		byte[] bt = encrypt(data.getBytes(), key.getBytes());
		String strs = new String(Base64.encodeBase64(bt));
		return strs;
	}

	/**
	 * 根据键值进行解密
	 */
	public static String decrypt(String data, String key) throws IOException,
			Exception {
		if (data == null)
			return null;
		byte[] buf = Base64.decodeBase64(data);
		byte[] bt = decrypt(buf, key.getBytes());
		return new String(bt);
	}

	/**
	 * 根据键值进行加密
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		Cipher cipher = cipherInit(data, key, Cipher.ENCRYPT_MODE);
		return cipher.doFinal(data);
	}

	/**
	 * 根据键值进行解密
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		Cipher cipher = cipherInit(data, key, Cipher.DECRYPT_MODE);
		return cipher.doFinal(data);
	}

	private static Cipher cipherInit(byte[] data, byte[] key, int cipherValue)
			throws Exception {
		/** 生成一个可信任的随机数源 **/
		SecureRandom sr = new SecureRandom();
		/** 从原始密钥数据创建DESKeySpec对象 **/
		DESKeySpec dks = new DESKeySpec(key);
		/** 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象 **/
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		/** Cipher对象实际完成加密或解密操作 **/
		Cipher cipher = Cipher.getInstance(DES);
		/** 用密钥初始化Cipher对象 **/
		cipher.init(cipherValue, securekey, sr);
		return cipher;
	}
}
