package com.dt.wechatptf.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created with IntelliJ IDEA.
 * User: lvxiang
 * Date: 13-6-2
 * Time: 下午8:44
 * To change this template use File | Settings | File Templates.
 */
public class Encoder {

    private static MessageDigest SHA256_ENCODER; // the sha-256 encoder
    private static MessageDigest MD5_ENCODER; // the md5 encoder
    private static MessageDigest SHA1_ENCODER; // the sha-1 encoder
    private static BASE64Encoder BASE64_ENCODER = new BASE64Encoder();
    private static BASE64Decoder BASE64_DECODER = new BASE64Decoder();

    private final static String DES = "DES";
    
    static {
        try {
            SHA256_ENCODER = MessageDigest.getInstance("SHA-256");
            SHA1_ENCODER   = MessageDigest.getInstance("SHA-1");
            MD5_ENCODER    = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * User SHA-256 to encode a string, The string must not be null, otherwise
     * {@link NullPointerException} would be thrown. Returned value would be a
     * string of hexdecimal numbers transformed from byte array.
     * @param strToEncode
     * @return
     */
    public static String encodeSHA256(String strToEncode){

        if(strToEncode == null)
            throw new NullPointerException("String to be encoded must not be null.");
        SHA256_ENCODER.update(strToEncode.getBytes());
        return bytesToHex(SHA256_ENCODER.digest());
    }

    /***
     * User SHA-1 to encode a string, The string must not be null, otherwise
     * {@link NullPointerException} would be thrown. Returned value would be a
     * @param strToEncode
     * @return
     */
    public static String encodeSHA1(String strToEncode){
        if(strToEncode == null)
            throw new NullPointerException("String to be encoded must not be null.");
        SHA1_ENCODER.update(strToEncode.getBytes());
        return bytesToHex(SHA1_ENCODER.digest());
    }

    /**
     * User MD5 to encode a string, The string must not be null, otherwise
     * {@link NullPointerException} would be thrown. Returned value would be a
     * @param strToEncode
     * @return
     */
    public static String encodeMD5(String strToEncode){
        if(strToEncode == null)
            throw new NullPointerException("String to be encoded must not be null.");
        MD5_ENCODER.update(strToEncode.getBytes());
        return bytesToHex(MD5_ENCODER.digest());
    }

    public static String encodeBASE64(String strToEncode){
        if(strToEncode == null)
            throw new NullPointerException("String to be encoded must not be null");
        return BASE64_ENCODER.encode(strToEncode.getBytes());
    }

    public static String decodeBASE64(String strToDecode) throws IOException {
        if(strToDecode == null)
            throw new NullPointerException("String to be decoded must not be null");
        return new String(BASE64_DECODER.decodeBuffer(strToDecode), "UTF-8");
    }
    
	/**
	 * Description 根据键值进行加密
	 * @param data 
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static String encryptDES(String data, String key) throws Exception {
		byte[] bt = encrypt(data.getBytes(), key.getBytes());
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}

	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decryptDES(String data, String key) throws IOException,
			Exception {
		if (data == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = decoder.decodeBuffer(data);
		byte[] bt = decrypt(buf,key.getBytes());
		return new String(bt);
	}

	/**
	 * Description 根据键值进行加密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}
	
	
	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}

    /**
     * transform a byte array into its corresponding hex-form string.
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes){
        if(bytes == null)
            throw new NullPointerException();
        String result = "";
        for(byte b: bytes){
            String tmp = Integer.toHexString(b & 0xFF);
            if(tmp.length() == 1)
                result += "0";
            result += tmp;
        }
        return result;
    }
}
