package projecto4.grupo1.albertoricardo;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.ws.ListMusicEntities;
import projecto4.grupo1.albertoricardo.ws.MusicDetail;



@Local
public interface MusicListEJBLocal {

	List<MusicEntity> listMusics();

	List<MusicEntity> listOwnMusics(UserEntity user);

	boolean update(MusicEntity music);

	boolean removerUserOwnership(UserEntity user);

	boolean removerMusicUserOwnership(MusicEntity m, UserEntity user);

	ListMusicEntities getAllMusics();

	ListMusicEntities getAllMusicsFromUser(int id);
	
	MusicDetail find(int id);
	
	boolean removerUserOwnership(int id);

}