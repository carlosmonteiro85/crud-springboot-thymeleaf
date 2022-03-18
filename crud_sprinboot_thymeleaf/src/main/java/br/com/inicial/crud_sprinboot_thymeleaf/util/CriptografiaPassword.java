package br.com.inicial.crud_sprinboot_thymeleaf.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;


public class CriptografiaPassword {

	public static final String SHA_512 = "SHA-512";
	public static final String SHA_256 = "SHA-256";
	public static final String MD5 = "MD5";
	/*
	 * Esse método criptografa a senha em SHA512
	 */
	public static String encryptSHA512(String value) {
		return encrypt(value, SHA_512);
	}
	/*
	 * Esse método criptografa a senha em MD5
	 */
	public static String encryptMD5(String value) {
		return encrypt(value, MD5);
	}
	/*
	 * Esse método criptografa a senha
	 */
	public static String criptografiaBase64Encoder(String valor) {
		return new Base64().encodeToString(valor.getBytes());
	}
	/*
	 * Esse método descriptografa a senha
	 */
	public static String descriptografiaBase64Decoder(String valorCriptografado) {
		return new String(new Base64().decode(valorCriptografado));
	}
	private static String encrypt(String value, String format) {
		try {
			MessageDigest md = MessageDigest.getInstance(format);
			md.update(value.getBytes(StandardCharsets.UTF_8));
			byte[] hash = md.digest();
			StringBuffer hashString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				if ((CryptographyValues.BYTE_ONE.getValue() & hash[i]) < CryptographyValues.BYTE_TWO.getValue()) {
					hashString.append("0")
							.append(Integer.toHexString(CryptographyValues.BYTE_THREE.getValue() & hash[i]));
				} else {
					hashString.append(Integer.toHexString(CryptographyValues.BYTE_THREE.getValue() & hash[i]));
				}
			}
			return hashString.toString();
		} catch (NoSuchAlgorithmException nsae) {
			throw new RuntimeException();
		}
	}
	private static byte[] encryptToByte(String value, String format) {
		try {
			MessageDigest md = MessageDigest.getInstance(format);
			md.update(value.getBytes(StandardCharsets.UTF_8));
			return md.digest();
		} catch (NoSuchAlgorithmException nsae) {
			throw new RuntimeException();
		}
	}
	public static byte[] encryptSHA256(String value) {
		return encryptToByte(value, SHA_256);
	}
}