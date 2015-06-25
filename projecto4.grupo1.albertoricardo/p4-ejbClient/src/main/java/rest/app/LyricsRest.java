package rest.app;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import rest.entity.LyricsResult;

/**
 * Session Bean implementation class LyricsRest
 */
@Stateless
@LocalBean
public class LyricsRest {
	
	private final static String urlTarget = "http://lyrics.wikia.com/api.php?";
	
    public LyricsRest() {
    }
    
    public String getLyric(String author, String songname) {
    	System.out.println("start rest lyrics for "+author+" and "+songname);
		
    	try {
				ResteasyClient reClient = new ResteasyClientBuilder().build();
				ResteasyWebTarget tgt = reClient
						.target(urlTarget + "artist=" + author + "&song=" + songname + "&fmt=xml");
				Response response = tgt.request(MediaType.APPLICATION_XML)
						.get();
				String lyrics = response.readEntity(LyricsResult.class).getLyric();
				System.out.println(lyrics);
				if ("Not found".equals(lyrics)) return null;
				else return lyrics;
			} catch (Exception e) {
				
			}
    	System.out.println("failed");
    	return null;
    }

    
    
}
