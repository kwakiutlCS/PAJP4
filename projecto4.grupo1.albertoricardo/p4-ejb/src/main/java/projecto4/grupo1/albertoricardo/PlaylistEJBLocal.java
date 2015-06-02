package projecto4.grupo1.albertoricardo;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;



@Local
public interface PlaylistEJBLocal {

	
    PlaylistEntity create(PlaylistEntity playlist);
    PlaylistEntity update(PlaylistEntity playlist);
    void remove(PlaylistEntity playlist);
    PlaylistEntity find(Object id);
    void addPlaylist(String name, Date insertDate, UserEntity userlogged);
    boolean findName(String name);
    List<PlaylistEntity> getPlaylists();
	List<PlaylistEntity> getOwnPlaylists(int id);
    
    
}