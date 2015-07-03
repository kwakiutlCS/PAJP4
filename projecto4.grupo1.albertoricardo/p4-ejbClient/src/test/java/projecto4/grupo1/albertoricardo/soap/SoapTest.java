package projecto4.grupo1.albertoricardo.soap;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import soap.LyricSearch;

import com.chartlyrics.api.Apiv1;
import com.chartlyrics.api.Apiv1Soap;
@RunWith(MockitoJUnitRunner.class)
public class SoapTest {
	
	@Mock
	Apiv1 api;
	@Mock
	Apiv1Soap soap;
	
	@InjectMocks
	LyricSearch soapsearch;

	@Test
	public void foundLyric() {
		
		try {
			String result = soapsearch.getLyric("u2", "one");
			assertNotNull(result);
		} catch (Exception e) {
			
		}
		
		
	}

}
