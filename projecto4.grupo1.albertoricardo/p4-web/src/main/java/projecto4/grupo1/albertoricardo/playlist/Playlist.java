package projecto4.grupo1.albertoricardo.playlist;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.PlaylistEJBLocal;
import projecto4.grupo1.albertoricardo.PlaylistEntity;
import projecto4.grupo1.albertoricardo.user.UserLogged;



@Named
@SessionScoped
public class Playlist implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(Playlist.class);
	

	@EJB
	private PlaylistEJBLocal playlistejb;
	@Inject
	private UserLogged userlogged;
	
	
	private String name;
	private Date insertDate;
	private int id;
	
	
	public Playlist(){
		
	}
	
	public Playlist(String name, Date insertDate){
		this.name=name;
		this.insertDate=insertDate;
	}
	
	
	
	
	
	public boolean verifyPlaylistName(){
		boolean found=false;
		List<PlaylistEntity> pl = playlistejb.getOwnPlaylists(userlogged.getUser().getId());
		for(PlaylistEntity p: pl)
		 if(p.getName().equalsIgnoreCase(name))
			 found=true;
		
		return found;
	}
			 
		
	public String insertPlaylist(){
		if(verifyPlaylistName()==false){
		Calendar now = Calendar.getInstance();
		insertDate = now.getTime();
		playlistejb.addPlaylist(name, insertDate, userlogged.getUser());
		log.info("inseriu");
		}
		else 
			log.info("nome duplicado");
		
		return "createPlaylist";
		
	}

	 
	public PlaylistEJBLocal getPlaylistejb() {
		return playlistejb;
	}


	public void setPlaylistejb(PlaylistEJBLocal playlistejb) {
		this.playlistejb = playlistejb;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	

	
	
	
	
	
	
	

}
