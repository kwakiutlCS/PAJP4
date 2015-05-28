package projecto4.grupo1.albertoricardo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.user.LoginChoose;
import projecto4.grupo1.albertoricardo.user.UserLogged;
import projecto4.grupo1.albertoricardo.user.UserLogin;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginTest {
	
	@Mock
	LoginChoose lChoose;
	@Mock
	ExternalContext ext;
	@Mock
	HttpServletRequest req;
	@Mock
	HttpSession session;
	@Mock
	UserEJBLocal userEJB;
	@Spy
	UserLogged uLogged;
	
	@InjectMocks
	private UserLogin userLogin;
	
	@Before
	public void setUp() {
	}
	
	@Ignore
	@Test
	public void doLoginFailTest() {
		userLogin.setEmail("ricardo");
		userLogin.setPassword("123");
		Mockito.when(userEJB.verifyLogin("ricardo", "123")).thenReturn(false);
		String d = userLogin.doLogin();
		assertEquals("", "", d);
	}
	
	@Ignore
	@Test
	public void doLoginSuccessTest() {
		FacesContext context = ContextMocker.mockFacesContext();
		Mockito.when(context.getExternalContext()).thenReturn(ext);
		Map<String, Object> sessionMap = new HashMap<String, Object>();
		Mockito.when(ext.getSessionMap()).thenReturn(sessionMap);
		Mockito.when(ext.getRequest()).thenReturn(req);
		Mockito.when(req.getSession()).thenReturn(session);
		userLogin.setEmail("ricardo");
		userLogin.setPassword("123");
		Mockito.when(userEJB.verifyLogin(userLogin.getEmail(), userLogin.getPassword())).thenReturn(true);
		String d = userLogin.doLogin();
		assertEquals("", "/Authorized/Layout/layout.xhtml?faces-redirect=true", d);
	}

}
