package soap;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceException;

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
	public LyricSearch() {
	}

	public String getLyric(String author, String songname) {
		Apiv1 api = new Apiv1();
		Apiv1Soap soap = api.getApiv1Soap();
		while (true) {
			try {
				GetLyricResult result = soap.searchLyricDirect(author, songname);
				String lyrics = result.getLyric();
				if ("".equals(lyrics)) return null;
	
				else return lyrics;
			} catch (WebServiceException wse) {
				
			}
		}


	}






}
