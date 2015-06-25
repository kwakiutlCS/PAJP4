package projecto4.grupo1.albertoricardo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import projecto4.grupo1.albertoricardo.LyricsEntity;

@Entity
@Table (name="musics")
@XmlRootElement
public class MusicEntity {
	

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,unique=true)
	private int id;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String artist;
	@Column(nullable=false)
	private String album;
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dateRecord;
	@Column(nullable=false)
	private String pathFile;
	@ManyToMany(mappedBy="musics")
	List<PlaylistEntity> playlists;
	@ManyToOne
	private UserEntity userOwner;
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="music")
	private List<LyricsEntity> lyrics;
	
	
//	static Logger logger = LoggerFactory.getLogger(MusicEntity.class);
	
	
	public MusicEntity() {

	}

	public MusicEntity(int id, String title, String artist, String album, Date date,
			String pathFile) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.dateRecord=date;
		this.pathFile = pathFile;
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

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public Date getDateRecord() {
		return dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	public UserEntity getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserEntity userOwner) {
		this.userOwner = userOwner;
	}
	
	public List<PlaylistEntity> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlaylistEntity> playlists) {
		this.playlists = playlists;
	}

	public List<LyricsEntity> getLyrics() {
		return lyrics;
	}

	public void setLyrics(List<LyricsEntity> lyrics) {
		this.lyrics = lyrics;
	}

}