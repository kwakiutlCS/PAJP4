package projecto4.grupo1.albertoricardo.security;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

 private static Logger log = LoggerFactory.getLogger(PasswordEncryptor.class); 

	public PasswordEncryptor() {
	}

	public String encrypt(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");

			byte[] digest = md.digest(password.getBytes());
			String base64 = Base64.encodeBase64String(digest);
			log.info("BASE64: "+base64+"\n"
					+ "ANTES: "+digest.toString());
			return base64;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public boolean check(String passwordUnsecured, String passwordEncrypted) {
		boolean pwMatch = false;

		String pw1 = encrypt(passwordUnsecured);

		if (pw1.equals(passwordEncrypted)) pwMatch = true;
		else pwMatch = false;

		return pwMatch;
	}

	public static  byte[] toSHA256(String password)  
			throws NoSuchAlgorithmException {  
		MessageDigest md = MessageDigest.getInstance("SHA-256");  
		md.update(password.getBytes());  
		return md.digest();  
	} 

}
