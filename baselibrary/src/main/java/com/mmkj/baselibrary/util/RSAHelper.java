package com.mmkj.baselibrary.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by Administrator on 2016/7/6.
 */
public class RSAHelper {
	/**
	 * 加密算法RSA
	 */
	private final static String RSA = "RSA";
	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "rsa";
	private static String RSA_JAVA = "RSA/None/PKCS1Padding";
	public final static String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+nbQvrMMOTIoK6nrZHvIIdt/7lk+UVz2FYusG35R600NbEsmmUvPP+5XorPb/J2JbaF7FUaqJPk9l6NBupOioecO+2EBFWHDWtt0O6NNRrP31p4FngbeW8Q0Eiy/gsCWUmZxioC+nBgPm9iIajiBzd58lsQOqNOdylg4hIR89DwIDAQAB";
	private static final String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL6dtC+sww5Migrqetke8gh23/uWT5RXPYVi6wbflHrTQ1sSyaZS88/7leis9v8nYltoXsVRqok+T2Xo0G6k6Kh5w77YQEVYcNa23Q7o01Gs/fWngWeBt5bxDQSLL+CwJZSZnGKgL6cGA+b2IhqOIHN3nyWxA6o053KWDiEhHz0PAgMBAAECgYB7E8Dgd6CKfwky+yvPPZfxty/wdqFzmtDya2hty7dHh1maWIszhaQ/yqaE0gm5vmhXtRBcP9rpOv5xQt4yUvCVmgC7IxRzuIJbMqOZpc9+5iNu8wI3i+uvVfXrm4QF7DUJL/V5vpRWDzTftmeGUzW+TfKIVsy7TR9D0y+BJsJQIQJBAOw31XB/TMKzU/NPwomi6suZ5F5KMIx59+V7sXFxuCDLGNJ6eihQ063STQpIj8MUGGPGr/j829v8+smCFldfdhsCQQDOlDqww6oT8vfIneWjl6d7SBx5rdJ4pTJuPj2H2ZddgpKXZWmkPROMApCugp2H3yrTqkAWCGP1M3IxugSailQdAkEAiP54kp3BbsmBzoJbWGdbtGizwNtV/KJf2n86EYjaOGqRIsBVSDxYB18HvwcGGST2YCdCtywudg7pgyQmwNVkMQJAa87b2kgiyIpyWdZdc8S/eQ0nHzFht+iuqu38eptJ7VkpW61nNnlRbC4ih5BO2gvTc+dTzqvOTrbsj5r+sBAk2QJADlf1YiD4E+0KtIEth0KAgZgVa+yXAwqk90SOmXklocuIET7LrbpMcsNgrAjShQVPTLrLnBrFBSRmNY8dlGmwhw==";
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;
	/**
	 * 用公钥加密 <br>
	 * 每次加密的字节数，不能超过密钥的长度值减去11
	 *
	 * @param data      需加密数据的byte数据
	 * @param publicKey 公钥
	 * @return 加密后的byte型数据
	 */
	public static byte[] encryptData(byte[] data, PublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_JAVA);
			// 编码前设定编码方式及密钥
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			// 传入编码数据并返回编码结果
			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 从字符串中加载公钥
	 *
	 * @param publicKeyStr 公钥数据字符串
	 * @throws Exception 加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Utils.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 获取加密后的字符串
	 *
	 * @param data 加密前字符串
	 * @return 加密后的结果
	 */
	public static String getRSAData(String data) {
		String text = null;
		try {
			PublicKey publicKey = loadPublicKey(public_key);
			byte[] bytes = encryptData(data.getBytes(), publicKey);
			text = Base64Utils.encode(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 *
	 * @param data      源数据
	 * @param publicKey 公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) {
		try {
			byte[] keyBytes = Base64Utils.decode(publicKey);
			//byte[] keyBytes = publicKey.getBytes();
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(RSA_JAVA);
			cipher.init(Cipher.ENCRYPT_MODE, publicK);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 *
	 * @param encryptedData 已加密数据
	 * @param privateKey    私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) {
		try {
			byte[] keyBytes = Base64Utils.decode(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privateK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 获取加密后的字符串
	 *
	 * @param data 加密前字符串
	 * @return 加密后的结果
	 */
	public static String getRSADataubsection(String data) {
		String text = null;
		try {
			byte[] bytes = encryptByPublicKey(data.getBytes("UTF-8"),public_key);
			text = Base64Utils.encode(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * 获取解密后的字符串
	 *
	 * @param data 解密前字符串
	 * @return解密后的结果
	 */
	public static String getRSADataDecrypt(String data) {
		String text = null;
		try {
			byte[] bytes = decryptByPrivateKey(data.getBytes("UTF-8"),private_key);
			text=new String(bytes,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
}
