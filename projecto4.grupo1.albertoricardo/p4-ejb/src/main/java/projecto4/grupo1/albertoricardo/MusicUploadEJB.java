package projecto4.grupo1.albertoricardo;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chart.rest.app.ChartRest;
import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.exceptions.UploadException;
import rest.app.LyricsRest;
import soap.LyricSearch;




@Stateless
public class MusicUploadEJB implements MusicUploadEJBLocal {
	
	@EJB
	MusicEJBLocal musicejb;
	
	@EJB
	LyricsEJBLocal lyricsejb;
	
	@Inject
	LyricSearch soapSearch;
	@Inject
	LyricsRest restSearch;
	@Inject
	ChartRest chartRest;
	
	private static Logger log = LoggerFactory.getLogger(MusicUploadEJB.class);


	//    The method merge creates or updates an entity,  
	//    we cannot remove not-attached entities 
	//    - we have to find them first. 
	//    This is the "Seek And Destroy" pattern 


	@Override
	public void uploadMusicDB(String title, String artist, String album, Date dateReleased, String path, UserEntity ue) {
		MusicEntity me = new MusicEntity();
		me.setTitle(title);
		me.setArtist(artist);
		me.setAlbum(album);
		me.setDateRecord(dateReleased);
		me.setPathFile(path);
		me.setUserOwner(ue);
		LyricsEntity le = new LyricsEntity();
		le.setMusic(me);
		
		try {
			le.setLyrics(chartRest.getLyric(artist, title));
		} catch(Exception e2) {
			e2.printStackTrace();
			try {
				le.setLyrics(restSearch.getLyric(artist, title));
			} catch(Exception e) {
				le.setLyrics(null);
				try {
					le.setLyrics(soapSearch.getLyric(artist, title));
				} catch (Exception e1) {
					log.error("Erro no SOAP");
				}

			}
		}
		
		try {
			musicejb.create(me);
			if (le.getLyrics() != null)
				lyricsejb.create(le);
		} catch (Exception e) {
			
			log.error("Erro a guardar nova música",e);
			throw new UploadException("Erro ao fazer upload de música");
		}
	}
}