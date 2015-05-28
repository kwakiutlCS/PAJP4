package projecto4.grupo1.albertoricardo;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.user.UserRegister;

@RunWith(MockitoJUnitRunner.class)
public class UserRegisterTest extends TestCase {
	
	@Mock
	UserEJBLocal userejb;
	@InjectMocks
	UserRegister uRegister;
	
	@Ignore
	@Test
	public void addNewUserSuccessTest() {
		uRegister.setEmail("username@mail.com");
		uRegister.setEmailConfirm("username@mail.com");
		uRegister.setPassword("123");
		uRegister.setPasswordConfirm("123");
		uRegister.setName("Username Created");
		String d = uRegister.addNewUser();
		assertEquals("login.xhtml?faces-redirect=true", d);
		
	}
	
	@Ignore
	@Test
	public void addNewUserFailTest() {
		uRegister.setEmail("username@mail.com");
		uRegister.setEmailConfirm("anotherUsername@mail.com");
		uRegister.setPassword("123");
		uRegister.setPasswordConfirm("123");
		uRegister.setName("Username Created");
		uRegister.addNewUser();
		String d = uRegister.getResult();
		assertEquals("E-mails não correspondem.", d);
		
	}

}
