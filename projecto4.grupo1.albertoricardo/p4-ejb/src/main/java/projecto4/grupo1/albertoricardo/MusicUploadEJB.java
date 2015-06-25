package projecto4.grupo1.albertoricardo;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projecto4.grupo1.albertoricardo.MusicEntity;
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
			le.setLyrics(soapSearch.getLyric(artist, title));
		} catch(Exception e) {
			le.setLyrics(restSearch.getLyric(artist, title));
		}
		
		try {
			musicejb.create(me);
			lyricsejb.create(le);
			FacesMessage msg = new FacesMessage("Música","Upload realizado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			log.error("Erro a guardar nova música",e);
			FacesMessage msg = new FacesMessage("Música","Erro ao fazer upload.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}