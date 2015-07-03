package projecto4.grupo1.albertoricardo.rest;

import javax.inject.Inject;


import static org.junit.Assert.*;

import org.mockito.Mockito;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import org.junit.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import com.chartlyrics.api.Apiv1Soap;
import com.chartlyrics.api.GetLyricResult;

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
	public void notNull() {
		
		String result = lrcRest.getLyric("u2", "one");
		assertNotNull(result);
	}
	
	@Test
	public void notFound() {
		String result = lrcRest.getLyric("Tony Carreira", "Depois de ti");
		assertNull(result);
	}
	
	@Test
	public void should_return_null_when_no_lyrics_found() {
		String result = lrcRest.getLyric("asdf", "assddfgh");
		Assert.assertThat(result, is(equalTo(null)));

	}

	@Test
	public void should_return_string_when_lyrics_found() {
		String result = lrcRest.getLyric("The Doors", "Soul kitchen");
		Assert.assertThat(result.length(), is(greaterThan(5)));
	}
}
