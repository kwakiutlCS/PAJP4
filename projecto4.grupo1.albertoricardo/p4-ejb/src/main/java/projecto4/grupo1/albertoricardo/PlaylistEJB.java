
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

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.dto.DozerHelper;
import projecto4.grupo1.albertoricardo.dto.PListDTO;



@Stateless
public class PlaylistEJB implements PlaylistEJBLocal {

	@PersistenceContext(name="Playlist")
	private EntityManager em;

	@EJB
	private PlaylistCRUD pl_crud;

	private static Logger log = LoggerFactory.getLogger(PlaylistEJB.class);

	@Override
	public void addPlaylist(String name, Date insertDate, UserEntity userlogged) {
		PlaylistEntity pl = new PlaylistEntity();
		pl.setName(name);
		pl.setInsertDate(insertDate);
		pl.setUserOwner(userlogged);
		em.persist(pl);
	}    
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PListDTO> getPlaylistDozer() { 
		List<PListDTO> pldto = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM PlaylistEntity p");
			ArrayList<PlaylistEntity> pe = (ArrayList<PlaylistEntity>) q.getResultList();
			Mapper mapper = new DozerBeanMapper();
			pldto = DozerHelper.map(mapper, pe, PListDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pldto;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PlaylistEntity> getPlaylists() { 
		List<PlaylistEntity> pe = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM PlaylistEntity p");
			pe = (ArrayList<PlaylistEntity>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("catch exception - getPlaylists method");
		}
		return pe;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlaylistEntity> getOwnPlaylists(int id) { 
		List<PlaylistEntity> pe = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM PlaylistEntity p WHERE p.userOwner.id = :id")
					.setParameter("id", id);
			pe = (ArrayList<PlaylistEntity>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("catch exception - getOwnPlaylists method");
		}
		return pe;
	}

	@Override
	public boolean findName(String name){
		boolean found=false;
		try {
			Query q = em.createQuery("select u from PlaylistEntity u where u.name like :e");
			q.setParameter("e", name);
			if(q.getSingleResult()!=null)
				found=true;
		} catch (NoResultException nre) {
			found = false;
			log.warn("catch exception - findName method");
		}
		return found;
	}

	@Override
	public boolean removePlaylistsOfUser(UserEntity u) {
		boolean success = false;
		try {
			Query q = em.createQuery("DELETE FROM PlaylistEntity p where p.userOwner.id = :i");
			q.setParameter("i", u.getId());
			int complete = q.executeUpdate();
			if (complete > 0) success = true;
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage("Erro",e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.warn("catch exception - removePlaylistsOfUser method");
		}

		return success;

	}

	@Override
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

	@Override
	public void updateName(int id, String name){

		pl_crud.find(id).setName(name);

	}

	@Override
	public void update(PlaylistEntity playlist) {
		pl_crud.update(playlist);
	}

}
