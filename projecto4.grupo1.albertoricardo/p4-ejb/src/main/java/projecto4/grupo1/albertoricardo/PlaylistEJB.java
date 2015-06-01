package projecto4.grupo1.albertoricardo;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;




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

	public void addPlaylist(String name, Date insertDate, UserEntity userlogged) {
		PlaylistEntity pl = new PlaylistEntity();
		pl.setName(name);
		pl.setInsertDate(insertDate);
		pl.setUserOwner(userlogged);
		em.persist(pl);
	}    

	@SuppressWarnings("unchecked")
	public List<PlaylistEntity> getPlaylists() {        
		return em.createQuery("From Playlists").getResultList();
	}

	@Override
	public boolean findName(String name){
		boolean found=false;
		try {
			Query q = em.createQuery("select u from PlaylistEntity u where u.name like :e")
					.setParameter("e", name);
			if(q.getSingleResult()!=null)
			  found=true;
		} catch (NoResultException nre) {
			found = false;
		}
		return found;
	}


}
