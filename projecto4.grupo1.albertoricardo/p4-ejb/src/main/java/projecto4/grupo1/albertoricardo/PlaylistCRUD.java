package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PlaylistCRUD {

	@PersistenceContext(name="Playlist")
	private EntityManager em;
	
	
	public PlaylistEntity create(PlaylistEntity playlist) {

		return em.merge(playlist);
	}

	
	public PlaylistEntity update(PlaylistEntity playlist) {

		return em.merge(playlist);
	}

	public void remove(PlaylistEntity playlist) {

		em.remove(em.merge(playlist));
	}


	public PlaylistEntity find(Object id) {

		return em.find(PlaylistEntity.class,id);
	}

	
}
