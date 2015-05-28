package projecto4.grupo1.albertoricardo.playlist;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import projecto4.grupo1.albertoricardo.ejb.PlaylistEJBLocal;



@Named
@SessionScoped
public class Playlist implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PlaylistEJBLocal playlistejb;
	
	private String name;
	private Date insertDate;
	private int id;
	
	public Playlist(){
		
	}
	
	public Playlist(String name, Date insertDate){
		this.name=name;
		this.insertDate=insertDate;
	}
	
	
	public String insertPlaylist(){
		Calendar now = Calendar.getInstance();
		insertDate = now.getTime();
		playlistejb.addPlaylist(name, insertDate);
		
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
