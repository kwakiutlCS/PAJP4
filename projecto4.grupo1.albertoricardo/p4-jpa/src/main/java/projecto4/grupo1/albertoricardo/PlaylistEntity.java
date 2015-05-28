package projecto4.grupo1.albertoricardo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="playlists")
public class PlaylistEntity{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,unique=true)
	private int id;
	@Column(nullable=false,unique=true)
	private String name;
	@Column(nullable=false,unique=false)
	private Date insertDate;
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	@ManyToMany
	List<MusicEntity>musics;
	@ManyToOne
	private UserEntity userOwner;
	
	
	public PlaylistEntity() {
	
	}

	public PlaylistEntity(int id, String name, List<MusicEntity> musics) {
		this.id = id;
		this.name = name;
		this.musics = musics;
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


	public List<MusicEntity> getMusics() {
		return musics;
	}


	public void setListMusic(List<MusicEntity> musics) {
		this.musics = musics;
	}
	
	
	
	
	
	
	
	
}
	