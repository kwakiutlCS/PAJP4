package projecto4.grupo1.albertoricardo.business.ws.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class MusicDetail {
	private int id;
	private String title;
	private String artist;
	private String album;
	private Date dateRecord;
	private int userOwnerID;
	
	public MusicDetail() {
	}

	public MusicDetail(int id, String title, String artist, String album,
			Date dateRecord, int userOwnerID) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.dateRecord = dateRecord;
		this.userOwnerID = userOwnerID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Date getDateRecord() {
		return dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	public int getUserOwnerID() {
		return userOwnerID;
	}

	public void setUserOwnerID(int userOwnerID) {
		this.userOwnerID = userOwnerID;
	}
	
	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return "Id: "+id+", Nome: "+title+", Artista: "+artist+", Album: "+album+", Data: "+df.format(dateRecord)+", submetida pelo utilizador com id "+userOwnerID;

	}

}
