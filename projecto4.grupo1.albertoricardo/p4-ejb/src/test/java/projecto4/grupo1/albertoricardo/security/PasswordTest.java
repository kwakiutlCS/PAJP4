package projecto4.grupo1.albertoricardo.security;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PasswordTest {
	
	@Mock
	MessageDigest md;
	@InjectMocks
	PasswordEncryptor pe;

	@Test
	public void rightPassword() {
		String password = "qwerty1234";
		String expectedResult = "F/gHVGRNM6xoWwhCpAIimtu0P8kxL3vfNrokI3ofH/s=";
		String result = pe.encrypt(password);
		assertEquals(expectedResult, result);
	}

}
