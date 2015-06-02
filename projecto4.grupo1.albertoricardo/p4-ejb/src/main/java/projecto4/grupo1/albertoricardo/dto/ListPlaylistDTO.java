package projecto4.grupo1.albertoricardo.dto;

import java.util.Date;
import java.util.List;


public class ListPlaylistDTO {


	private List<PlaylistDTO> list_pl_dto;


	public void addPlaylistDTO(int id, String nome, int size, Date date, int id_user){
		
		PlaylistDTO e = new PlaylistDTO(id, nome, size, date, id_user);
		
		list_pl_dto.add(e);
		
	}
	


}








