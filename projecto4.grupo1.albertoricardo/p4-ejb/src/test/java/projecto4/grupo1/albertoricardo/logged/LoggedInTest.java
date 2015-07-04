package projecto4.grupo1.albertoricardo.logged;

import static org.junit.Assert.*;

import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.omg.CosNaming.IstringHelper;
import org.hamcrest.Matchers;
import org.hamcrest.generator.HamcrestFactoryWriter;
import org.hamcrest.MatcherAssert;

import projecto4.grupo1.albertoricardo.business.ws.model.UserDetail;

@RunWith(MockitoJUnitRunner.class)
public class LoggedInTest extends TestCase {
	
	@Mock
	UserDetail ud;
	@InjectMocks
	LoggedIn logged;

	@Test
	public void test() {
		int size0 = logged.getUsers().getListUsers().size();
		logged.add(ud);
		int size1 = logged.getUsers().getListUsers().size();
		int dif = size1-size0;
		assertEquals(1, dif);
	}

}
