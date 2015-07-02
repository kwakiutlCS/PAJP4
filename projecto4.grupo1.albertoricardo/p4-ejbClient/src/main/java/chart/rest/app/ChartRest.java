package chart.rest.app;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rest.entity.GetLyricResult;
import rest.entity.LyricsResult;

/**
 * Session Bean implementation class LyricsRest
 */
@Stateless
@LocalBean
public class ChartRest {

	private final static String urlTarget = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?";
	private static Logger log = LoggerFactory.getLogger(ChartRest.class);

	public ChartRest() {
	}

	public String getLyric(String author, String songname) {
		try {
			ResteasyClient reClient = new ResteasyClientBuilder().build();
			ResteasyWebTarget tgt = reClient
					.target(urlTarget + "artist=" + author + "&song=" + songname);
			Response response = tgt.request(MediaType.APPLICATION_XML)
					.get();
			String lyrics = response.readEntity(GetLyricResult.class).getLyric();
			if ("".equals(lyrics)) return null;
			else return lyrics;
		} catch (Exception e) {
			log.error("Error on REST (ChartLyrics)");
			return null;
		}
	}

	//	public static void main(String[] args) {
	//		ChartRest r = new ChartRest();
	//		System.out.println(r.getLyric("U2", "Where the streets have no name"));
	//	}
}
