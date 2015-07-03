package projecto4.grupo1.albertoricardo.rest;

import javax.inject.Inject;

import static org.junit.Assert.*;

import org.mockito.Mockito;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rest.app.LyricsRest;

@RunWith(MockitoJUnitRunner.class)
public class RestTest {
	
	@Mock
	ResteasyClient client;
	@Mock
	ResteasyWebTarget target;
	
	@InjectMocks
	LyricsRest lrcRest;
	
	@Test
	public void rightSong() {
		
		String result = lrcRest.getLyric("U2", "One");
		String substr = result.substring(0,20);
		assertEquals("Is it getting better", substr);
		
	}
	
	@Test
	public void notNull() {
		
		String result = lrcRest.getLyric("u2", "one");
		assertNotNull(result);
	}
	
	@Test
	public void notFound() {
		String result = lrcRest.getLyric("Tony Carreira", "Depois de ti");
		assertNull(result);
	}

}
