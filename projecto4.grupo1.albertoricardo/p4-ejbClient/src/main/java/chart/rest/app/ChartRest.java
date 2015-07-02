package chart.rest.app;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.chartlyrics.api.GetLyricResult;

import rest.entity.LyricsResult;

/**
 * Session Bean implementation class LyricsRest
 */
@Stateless
@LocalBean
public class ChartRest {

	private final static String urlTarget = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?";

	public ChartRest() {
	}

	public String getLyric(String author, String songname) {
		System.out.println("start rest lyrics for "+author+" and "+songname);
		while(true) {
			try {
				ResteasyClient reClient = new ResteasyClientBuilder().build();
				ResteasyWebTarget tgt = reClient
						.target(urlTarget + "artist=" + author + "&song=" + songname);
				Response response = tgt.request(MediaType.APPLICATION_XML)
						.get();
				String lyrics = response.readEntity(GetLyricResult.class).getLyric();
				System.out.println(lyrics);
				if ("".equals(lyrics)) return null;
				else return lyrics;
			} catch (Exception e) {
				
			}
		}
	}

//	public static void main(String[] args) {
//		ChartRest r = new ChartRest();
//		System.out.println(r.getLyric("The Doors", "Light my fire"));
//	}
}
