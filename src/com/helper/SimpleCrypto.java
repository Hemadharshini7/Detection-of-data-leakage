package com.helper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class SimpleCrypto {

	public static String encrypt(String seed, String cleartext) throws Exception {
		byte[] rawKey = getRawKey(seed.getBytes());
		byte[] result = encrypt(rawKey, cleartext.getBytes());
		return toHex(result);
	}
	
	public static String decrypt(String seed, String encrypted) throws Exception {
		byte[] rawKey = getRawKey(seed.getBytes());
		byte[] enc = toByte(encrypted);
		byte[] result = decrypt(rawKey, enc);
		return new String(result);
	}

	private static byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(seed);
	    kgen.init(128, sr); // 192 and 256 bits may not be available
	    SecretKey skey = kgen.generateKey();
	    byte[] raw = skey.getEncoded();
	    return raw;
	}

	
	private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	    byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	    byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}
public static void encryptFile(String seed, String inputFileName,String outputFileName) throws Exception {

    	byte[] rawKey = getRawKey(seed.getBytes());
    SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
		Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
         CipherInputStream   cipherIn=new CipherInputStream(new FileInputStream(new File(inputFileName)), cipher);
        FileOutputStream fos=new FileOutputStream(new File(outputFileName));
        int i;
        while((i=cipherIn.read())!=-1){
        fos.write(i);
        }
        fos.flush();
        fos.close();

}


public static void decryptFile(String seed, String inputFileName,String outputFileName) throws Exception {

    	byte[] rawKey = getRawKey(seed.getBytes());
    SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
		Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
         CipherInputStream   cipherIn=new CipherInputStream(new FileInputStream(new File(inputFileName)), cipher);
        FileOutputStream fos=new FileOutputStream(new File(outputFileName));
        int i;
        while((i=cipherIn.read())!=-1){
        fos.write(i);
        }
        fos.flush();
        fos.close();

}


	public static String toHex(String txt) {
		return toHex(txt.getBytes());
	}
	public static String fromHex(String hex) {
		return new String(toByte(hex));
	}
	
	public static byte[] toByte(String hexString) {
		int len = hexString.length()/2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
		return result;
	}

	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2*buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}
	private final static String HEX = "0123456789ABCDEF";
	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
	}
	public static void main(String[] args) {   
		try {
		//	System.out.println(new SimpleCrypto().encrypt("seed", "xzmAJSASJFbnvmzbxc&*Q$@^^$(*"));
//		System.out.println(new SimpleCrypto().encrypt("seed", "name=rajesh&password=rajesh"));
//		System.out.println(new SimpleCrypto().decrypt("seed", "7981B379310008184B22ABB8B649BFB5811C56CAEB324F9A60D6297F7069A8A1"));
//    System.out.println(System.getProperty("java.io.tmpdir"));
//                    encryptFile("Rajesh ", "F:\\write\\Picture 3.jpg", "F:\\write\\Picture 3_0.jpg");
                    decryptFile("rajesh ", "//192.168.0.108/a/datadb/1334345154961/Dos.dmp", "//192.168.0.108/a/datadb/1334345154961/Dos_o.dmp");


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}