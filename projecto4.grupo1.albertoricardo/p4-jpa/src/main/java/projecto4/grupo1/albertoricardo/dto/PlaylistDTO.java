package projecto4.grupo1.albertoricardo.dto;

import java.util.Date;
import java.util.List;

public class PlaylistDTO {
	
	private int id;
	private String name;
	private Date insertDate;
	List<MusicDTO>musics;
	
	public PlaylistDTO() {
		// TODO Auto-generated constructor stub
	}

	public PlaylistDTO(int id, String name, Date insertDate,
			List<MusicDTO> musics) {
		super();
		this.id = id;
		this.name = name;
		this.insertDate = insertDate;
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

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public List<MusicDTO> getMusics() {
		return musics;
	}

	public void setMusics(List<MusicDTO> musics) {
		this.musics = musics;
	}
	
	

}
