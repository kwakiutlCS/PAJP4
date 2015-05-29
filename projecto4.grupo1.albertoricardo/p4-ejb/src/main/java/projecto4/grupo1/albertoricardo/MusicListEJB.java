package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import projecto4.grupo1.albertoricardo.MusicEntity;




@Stateless
public class MusicListEJB implements MusicListEJBLocal {
	
    @PersistenceContext(name="Playlist")
    private EntityManager em;

 
//    The method merge creates or updates an entity,  
//    we cannot remove not-attached entities 
//    - we have to find them first. 
//    This is the "Seek And Destroy" pattern 
    
  
	@Override
	public List<MusicEntity> listMusics() {
		Query q = em.createQuery("select m from MusicEntity m");
		List<MusicEntity> lme = new ArrayList<MusicEntity>();
		lme = q.getResultList();
		return lme;
	}
}