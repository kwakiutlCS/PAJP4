
package projecto4.grupo1.albertoricardo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Stateless
public class PlaylistEJB {

	@PersistenceContext(name="Playlist")
	private EntityManager em;

	@EJB
	private PlaylistCRUD pl_crud;

	private static Logger log = LoggerFactory.getLogger(PlaylistEJB.class);


	public void addPlaylist(String name, Date insertDate, UserEntity userlogged) {
		PlaylistEntity pl = new PlaylistEntity();
		pl.setName(name);
		pl.setInsertDate(insertDate);
		pl.setUserOwner(userlogged);
		em.persist(pl);;
	}    


	@SuppressWarnings("unchecked")
	public List<PlaylistEntity> getPlaylists() { 
		List<PlaylistEntity> pe = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM PlaylistEntity p");
			pe = (ArrayList<PlaylistEntity>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pe;
	}

	@SuppressWarnings("unchecked")
	public List<PlaylistEntity> getOwnPlaylists(int id) { 
		List<PlaylistEntity> pe = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM PlaylistEntity p WHERE p.userOwner.id = :id")
					.setParameter("id", id);
			pe = (ArrayList<PlaylistEntity>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pe;
	}


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

	public boolean removePlaylistsOfUser(UserEntity u) {
		boolean success = false;
		try {
			int complete = em.createQuery("DELETE FROM PlaylistEntity p where p.userOwner.id = :i")
					.setParameter("i", u.getId())
					.executeUpdate();
			if (complete > 0) success = true;
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage("Erro",e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		return success;

	}

	public boolean removePlaylistFromUser(PlaylistEntity p) {
		boolean success = false;
		try {
			p.getMusics().clear();
			pl_crud.remove(p);
			success = true;
			FacesMessage msg = new FacesMessage("Playlist","Playlist "+p.getName()+" removida com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch(Exception e) {
			log.error("Erro ao tentar remover playlist",e);
			FacesMessage msg = new FacesMessage("Erro","Playlist "+p.getName()+" não foi eliminada");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		return success;
	}

	public void updateName(int id, String name){

		pl_crud.find(id).setName(name);

	}

	public void update(PlaylistEntity playlist) {

		pl_crud.update(playlist);
	}

}
