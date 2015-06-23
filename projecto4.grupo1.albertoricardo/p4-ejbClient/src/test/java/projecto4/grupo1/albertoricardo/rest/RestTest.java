package projecto4.grupo1.albertoricardo.rest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import rest.app.LyricsRest;

@RunWith(MockitoJUnitRunner.class)
public class RestTest {
	
	@InjectMocks
	LyricsRest lrcRest;
	
	@Test
	public void test() {
		
		lrcRest.getLyric("u2", "one");
	}

}
