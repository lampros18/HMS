package gr.hua.dit.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

//Υλοποίηση από https://howtodoinjava.com/security/java-aes-encryption-example/

@Component
public class Crypto {

	private String secretKey;
	private Aes aes;
	/**
	 * 
	 * @param text Το προς κρυπτογράφηση κείμενο
	 * @return κρυπτογραφημένο το αρχικό μήνυμα
	 */
	public String encrypt(String text) {

		return this.aes.encrypt(text, secretKey);
	}
	/**
	 * 
	 * @param text Το προς αποκρυπτογράφηση κείμενο
	 * @return Αποκρυπτογραφημένο το κείμενο
	 */
	public String decrypt(String text) {

		return this.aes.decrypt(text, secretKey);
	}

	public Crypto(String secretKey) {
		super();
		this.secretKey = secretKey;
		this.aes = new Aes();

	}

	private class Aes {

		private SecretKeySpec secretKey;
		private byte[] key;

		public void setKey(String myKey) {
			MessageDigest sha = null;
			try {
				key = myKey.getBytes("UTF-8");
				sha = MessageDigest.getInstance("SHA-1");
				key = sha.digest(key);
				key = Arrays.copyOf(key, 16);
				secretKey = new SecretKeySpec(key, "AES");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		public String encrypt(String strToEncrypt, String secret) {
			if(strToEncrypt == null)
				return null;
			try {
				setKey(secret);
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")))+"==";
			} catch (Exception e) {
				System.out.println("Error while encrypting: " + e.toString());
			}
			return null;
		}

		public String decrypt(String strToDecrypt, String secret) {
			try {

				strToDecrypt=strToDecrypt.substring(0, strToDecrypt.length()-2);
				setKey(secret);
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));


			} catch (Exception e) {
				System.out.println("Error while decrypting: " + e.toString());
			}
			return null;
		}
	}

	/**
	 * Βασίζεται στο γεγονός ότι κάθε κρυπτογραφημμένο μήνυμα τελειώνει σε "="
	 * 
	 * 
	 * @param text Το κείμενο που θέλουμε να ελέγξουμε αν είναι κρυπτογραφημένο με
	 *             aes
	 * @return true αν είναι κρυπτογραφημένο false διαφορετικά
	 */
	public boolean isEncrypted(String text) {

		if(text == null)
			return false;
		
		Pattern pattern = Pattern.compile(".+=$");
		return pattern.matcher(text).matches();

	}
	
}