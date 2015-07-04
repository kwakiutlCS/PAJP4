package projecto4.grupo1.albertoricardo;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.business.ws.model.AllPlaylists;
import projecto4.grupo1.albertoricardo.business.ws.model.ListPlaylists;
import projecto4.grupo1.albertoricardo.dto.PListDTO;
import projecto4.grupo1.albertoricardo.dto.PlaylistDTO;

@Local
public interface PlaylistEJBLocal {

	void addPlaylist(String name, Date insertDate, UserEntity userlogged);

	List<PListDTO> getPlaylistDozer();

	List<PlaylistEntity> getPlaylists();

	List<PlaylistEntity> getOwnPlaylists(int id);

	boolean findName(String name);

	boolean removePlaylistsOfUser(UserEntity u);

	boolean removePlaylistFromUser(PlaylistEntity p);

	void updateName(int id, String name);

	void update(PlaylistEntity playlist);

	AllPlaylists findToDTO(Object id);

	ListPlaylists getAllPlaylists();

	ListPlaylists getPlaylistsFromUser(Object id);

	boolean updateFromDTO(AllPlaylists ap);

	boolean createPlaylistFromDTO(AllPlaylists ap);

}
