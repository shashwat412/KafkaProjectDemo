package com.example.demo.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AES 
{
//	@Value("${IV-VECTOR}")
//	private static String initVector;
//	
//	@Value("${SECRET-KEY}")
//	private static String secretKey;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AES.class);
	
	private static final String initVector="encryptionIntVec";
	private static final String secretKey="aesEncryptionKey";
	public static String encrypt(String strToEncrypt)
    {
		LOGGER.info("String to Encrypt : {}",strToEncrypt);
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null; 
    }
	
	public static String decrypt(String encrypted) {
		
//		LOGGER.info("Initial Vector : {}",initVector);
//		LOGGER.info("Secret Key : {}",secretKey);
		
		LOGGER.info("String to Decrypt : {}",encrypted);
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
	 
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
	 
	        return new String(original);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	 
	    return null;
	}
  
	

}
