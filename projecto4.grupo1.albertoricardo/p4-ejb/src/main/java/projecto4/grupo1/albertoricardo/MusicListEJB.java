package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import projecto4.grupo1.albertoricardo.MusicEntity;




@Stateless
public class MusicListEJB implements MusicListEJBLocal {

	@PersistenceContext(name="Playlist")
	private EntityManager em;

	@EJB
	private MusicEJBLocal crud;

	//    The method merge creates or updates an entity,  
	//    we cannot remove not-attached entities 
	//    - we have to find them first. 
	//    This is the "Seek And Destroy" pattern 


	@SuppressWarnings("unchecked")
	@Override
	public List<MusicEntity> listMusics() {
		Query q = em.createQuery("select m from MusicEntity m");
		List<MusicEntity> lme = new ArrayList<MusicEntity>();
		lme = q.getResultList();
		return lme;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MusicEntity> listOwnMusics(UserEntity user) {
		List<MusicEntity> me = new ArrayList<>();
		Query q = em.createQuery("SELECT m FROM MusicEntity m where m.userOwner.id = :u")
				.setParameter("u", user.getId());
		try {
			me = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return me;
	}
	
	@Override
	public boolean update(MusicEntity music) {
		boolean success = false;
		try {
			crud.update(music);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
}