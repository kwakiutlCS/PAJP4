package projecto4.grupo1.albertoricardo;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projecto4.grupo1.albertoricardo.PlaylistEntity;




@Stateless
public class PlaylistEJB implements PlaylistEJBLocal {

	@PersistenceContext(name="Playlist")
	private EntityManager em;


	@Override
	public PlaylistEntity create(PlaylistEntity playlist) {
		return em.merge(playlist);
	}
	@Override
	public PlaylistEntity update(PlaylistEntity playlist) {
		return em.merge(playlist);
	}
	@Override
	public void remove(PlaylistEntity playlist) {
		em.remove(em.merge(playlist));

	}
	@Override
	public PlaylistEntity find(Object id) {
		return em.find(projecto4.grupo1.albertoricardo.PlaylistEntity.class, id);
	}
	
	@Override
	public void addPlaylist(String name, Date insertDate) {
		PlaylistEntity pl = new PlaylistEntity();
		pl.setName(name);
		pl.setInsertDate(insertDate);;
		em.persist(pl);
	}    

	@Override
	public List<PlaylistEntity> getPlaylists() {        
		return em.createQuery("From Playlists").getResultList();
	}
	@Override
	public void addPlaylist(String name, Date insertDate, UserEntity userlogged) {
		// TODO Auto-generated method stub
		
	}



}