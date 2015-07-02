package projecto4.grupo1.albertoricardo;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.dto.PListDTO;
import pt.uc.dei.aor.paj.proj4.group1.business.ws.model.AllPlaylists;
import pt.uc.dei.aor.paj.proj4.group1.business.ws.model.ListPlaylists;

@Local
public interface PlaylistEJBLocal {

    void addPlaylist(String name, Date insertDate, UserEntity userlogged);

    List<PListDTO> getPlaylistDozer();

    List<PlaylistEntity> getPlaylists();

    int getPlaylistCount();

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
