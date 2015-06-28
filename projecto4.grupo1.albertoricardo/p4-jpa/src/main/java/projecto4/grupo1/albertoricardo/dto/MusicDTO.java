package projecto4.grupo1.albertoricardo.dto;

import java.util.Date;
import java.util.List;

public class MusicDTO {
	
	private int id;
	private String title;
	private String artist;
	private String album;
	private Date dateRecord;
	List<PlaylistDTO> playlists;
	
	public MusicDTO() {
	}

	public MusicDTO(int id, String title, String artist, String album,
			Date dateRecord, List<PlaylistDTO> playlists) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.dateRecord = dateRecord;
		this.playlists = playlists;
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

	public List<PlaylistDTO> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlaylistDTO> playlists) {
		this.playlists = playlists;
	}
	
	
	
	

}
