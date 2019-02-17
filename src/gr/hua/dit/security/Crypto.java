package gr.hua.dit.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

//Υλοποίηση από https://www.javainterviewpoint.com/aes-256-encryption-and-decryption/

@Component
public class Crypto {

	private String secretKey;
	
	
	
	/**
	 * 
	 * @param secretKey Το κλειδί με βάση το οποίο  θα πραγματοποιείται η κρυπτογράφηση
	 */
	public Crypto(String secretKey) {
		super();
		this.secretKey = secretKey;
	}
	/**
	 * 
	 * @param text Το προς κρυπτογράφηση κείμενο
	 * @return κρυπτογραφημένο το αρχικό μήνυμα
	 * 
	 */
	public String encrypt(String text) {

		 byte[] encrypted= null;
		try {
			encrypted = encrypt(text, secretKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
         return Base64.getEncoder().encodeToString(encrypted)+"==";
	}
	/**
	 * 
	 * @param text Το προς αποκρυπτογράφηση κείμενο
	 * @return Αποκρυπτογραφημένο το κείμενο
	 * 
	 */
	public String decrypt(String text)  {

		try {
			
			if(this.isEncrypted(text)) {
				
				text=text.substring(0, text.length()-2);
			}
			
			return decrypt(org.apache.commons.codec.binary.Base64.decodeBase64(text.getBytes()), secretKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	private static String decrypt(byte[] encryptedIvTextBytes, String key) throws Exception {
        int ivSize = 16;
        int keySize = 16;

        // Extract IV.
        byte[] iv = new byte[ivSize];
        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Extract encrypted part.
        int encryptedSize = encryptedIvTextBytes.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

        // Hash key.
        byte[] keyBytes = new byte[keySize];
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(key.getBytes());
        System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Decrypt.
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

        return new String(decrypted);
    }
	
	
	private static byte[] encrypt(String plainText, String key) throws Exception {
        byte[] clean = plainText.getBytes();

        // Generating IV.
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Hashing key.
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(key.getBytes("UTF-8"));
        byte[] keyBytes = new byte[16];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(clean);

        // Combine IV and encrypted part.
        byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
        System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);

        return encryptedIVAndText;
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

		Pattern pattern = Pattern.compile(".+=$");

		return pattern.matcher(text).matches();

	}
}