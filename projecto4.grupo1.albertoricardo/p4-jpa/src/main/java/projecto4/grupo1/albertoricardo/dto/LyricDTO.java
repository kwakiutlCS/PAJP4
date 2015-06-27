package projecto4.grupo1.albertoricardo.dto;
import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.UserEntity;

public class LyricDTO {
	private int id;
	private String lyrics;
	private UserEntity user;
	private MusicEntity music;
	
	public LyricDTO() {
		// TODO Auto-generated constructor stub
	}

	public LyricDTO(int id, String lyrics, UserEntity user, MusicEntity music) {
		super();
		this.id = id;
		this.lyrics = lyrics;
		this.user = user;
		this.music = music;
	}
	
	public LyricDTO(String lyrics, UserEntity user, MusicEntity music) {
		super();
		this.lyrics = lyrics;
		this.user = user;
		this.music = music;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public MusicEntity getMusic() {
		return music;
	}

	public void setMusic(MusicEntity music) {
		this.music = music;
	}
	
	

}
