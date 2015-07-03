package soap;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chartlyrics.api.*;
/**
 * Session Bean implementation class LyricSearch
 */
@Stateless
@LocalBean
public class LyricSearch {
	/**
	 * Default constructor. 
	 */
	private static Logger log = LoggerFactory.getLogger(LyricSearch.class);
	public LyricSearch() {
	}

	public String getLyric(String author, String songname) throws Exception {
		Apiv1 api = new Apiv1();
		Apiv1Soap soap = api.getApiv1Soap();
		int count = 0;
		
		while (true) {
			try {
				GetLyricResult result = soap.searchLyricDirect(author, songname);
				String lyrics = result.getLyric();
				if ("".equals(lyrics)) return null;

				else return lyrics;
			} catch (WebServiceException wse) {
				count++;
				if (count > 5) { 
					log.warn("After 50 tries, it has failed once again.");
					throw new Exception();
				}
			}
		}


	}






}
