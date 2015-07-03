package projecto4.grupo1.albertoricardo.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import chart.rest.app.ChartRest;

@RunWith(MockitoJUnitRunner.class)
public class ChartLyricsTest {
	
	@Mock
	ResteasyClient client;
	@Mock
	ResteasyWebTarget target;
	@InjectMocks
	ChartRest rest;

	@Test
	public void rightSong() throws Exception {
		String result = rest.getLyric("U2", "One");
		String substr = result.substring(0,20);
		assertEquals("Is it getting better", substr);
		
	}
	
	@Test
	public void notFound() {
		String result = rest.getLyric("Tony Carreira", "Depois de ti");
		assertNull(result);
	}

	@Test
	public void should_return_null_when_no_lyrics_found() {
		String result = rest.getLyric("asdf", "assddfgh");
		Assert.assertThat(result, is(equalTo(null)));

	}

}
