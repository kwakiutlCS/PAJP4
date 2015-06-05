package projecto4.grupo1.albertoricardo.user;

import static org.junit.Assert.*;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.validators.PasswordValidator;

@RunWith(MockitoJUnitRunner.class)
public class PasswordValidatorTest {
	@Mock
	UIComponent component;
	@Mock
	UIInput input;
	@InjectMocks
	PasswordValidator pv;
	
	@Before
	public void setUp() {
	}
	
	
	@Test(expected=ValidatorException.class)
	public void confirmDifFromPassword() throws ExceptionInInitializerError {
		Mockito.when(component.getAttributes().get("confirmPassword")).thenReturn(input);
		Mockito.when(input.getSubmittedValue()).thenReturn("123");
		String password = "456";
		pv.validate(null, component, password);
	}

}
