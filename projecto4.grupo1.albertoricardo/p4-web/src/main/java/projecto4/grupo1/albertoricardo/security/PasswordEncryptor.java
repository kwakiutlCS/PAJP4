package projecto4.grupo1.albertoricardo.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.syndication.io.impl.Base64;

public class PasswordEncryptor {
	
	
	
	public PasswordEncryptor() {
	}

	public String encrypt(String password) {
		String securedPassword = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			
			byte byteData[] = md.digest();
			byte[] data2 = Base64.encode(byteData);
			securedPassword = new String(data2);
			return securedPassword;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return securedPassword;
	}
	
	public boolean check(String passwordUnsecured, String passwordEncrypted) {
		boolean pwMatch = false;
		
		String pw1 = encrypt(passwordUnsecured);
		
		if (pw1.equals(passwordEncrypted)) pwMatch = true;
		else pwMatch = false;
		
		return pwMatch;
	}

}
