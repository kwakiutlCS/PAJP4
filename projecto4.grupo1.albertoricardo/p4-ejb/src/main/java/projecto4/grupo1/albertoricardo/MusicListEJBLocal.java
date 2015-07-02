package projecto4.grupo1.albertoricardo;

import java.util.List;

import javax.ejb.Local;

import pt.uc.dei.aor.paj.proj4.group1.business.ws.model.ListMusicEntities;
import pt.uc.dei.aor.paj.proj4.group1.business.ws.model.MusicDetail;

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