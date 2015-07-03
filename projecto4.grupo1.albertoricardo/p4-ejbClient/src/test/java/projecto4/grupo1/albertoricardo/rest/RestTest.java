package projecto4.grupo1.albertoricardo.rest;

import javax.inject.Inject;

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
	
	@InjectMocks
	LyricsRest lrcRest;
	
	
	@Test
	public void should_return_null_when_no_lyrics_found() {
		String result = lrcRest.getLyric("asdf", "assddfgh");
		Assert.assertThat(result, is(equalTo(null)));
	}

	@Test
	public void should_return_string_when_lyrics_found() {
		String result = lrcRest.getLyric("u2", "one");
		Assert.assertThat(result.length(), is(greaterThan(5)));
	}
}
