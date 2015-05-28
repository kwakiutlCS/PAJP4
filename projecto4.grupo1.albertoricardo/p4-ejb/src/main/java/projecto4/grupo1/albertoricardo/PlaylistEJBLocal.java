package projecto4.grupo1.albertoricardo;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.PlaylistEntity;



@Local
public interface PlaylistEJBLocal {

	
    PlaylistEntity create(PlaylistEntity playlist);
    PlaylistEntity update(PlaylistEntity playlist);
    void remove(PlaylistEntity playlist);
    PlaylistEntity find(Object id);
    void addPlaylist(String name, Date insertDate);
    List<PlaylistEntity> getPlaylists();
    
    
}