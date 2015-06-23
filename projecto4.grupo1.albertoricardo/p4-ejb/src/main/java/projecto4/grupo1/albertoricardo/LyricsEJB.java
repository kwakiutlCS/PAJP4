package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;




@Stateless
public class LyricsEJB implements LyricsEJBLocal {
	
    @PersistenceContext(name="Playlist")
    private EntityManager em;

  
	@Override
	public LyricsEntity create(LyricsEntity lyrics) {
		em.persist(lyrics);
		return lyrics;
	}


	@Override
	public LyricsEntity update(LyricsEntity lyrics) {
		return em.merge(lyrics);
	}


	@Override
	public void remove(LyricsEntity lyrics) {
		em.remove(lyrics);
	}


	@Override
	public LyricsEntity find(Object id) {
		return em.find(projecto4.grupo1.albertoricardo.LyricsEntity.class, id);
	}
	
}