package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import projecto4.grupo1.albertoricardo.dto.LyricDTO;
import projecto4.grupo1.albertoricardo.dto.MusicDTO;




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

	@Override
	public LyricDTO findCustomed(int musicid, int ownerid) {
		try {
			TypedQuery<LyricsEntity> q = em.createQuery("select l from LyricsEntity l where l.user.id = :u and l.music.id = :m",LyricsEntity.class)
					.setParameter("u", ownerid)
					.setParameter("m", musicid);
			LyricsEntity le = q.getSingleResult();
			LyricDTO ldto = new LyricDTO();
			List<String> dozermapping = new ArrayList<>();
			dozermapping.add("META-INF/dtomapping.xml");
			Mapper mapper = new DozerBeanMapper(dozermapping);
			mapper.map(le, ldto);
			ldto.setMusic(le.getMusic());
			return ldto;
		} catch (Exception e) {
			return findOriginal(musicid);
		}
	}

	@Override
	public LyricDTO findOriginal(int musicid) {
		TypedQuery<LyricsEntity> q = em.createQuery("select l from LyricsEntity l where l.user.id is null and l.music.id = :m",LyricsEntity.class)
				.setParameter("m", musicid);
		LyricsEntity le = q.getSingleResult();
		LyricDTO ldto = new LyricDTO();
		List<String> dozermapping = new ArrayList<>();
		dozermapping.add("META-INF/dtomapping.xml");
		Mapper mapper = new DozerBeanMapper(dozermapping);
		mapper.map(le, ldto);
		ldto.setMusic(le.getMusic());
		return ldto;
	}
	
	@Override
	public void editedLyrics(LyricDTO ldto) {
		LyricsEntity le = new LyricsEntity();
		Mapper mapper = new DozerBeanMapper();
		mapper.map(ldto, le);
		update(le);
	}

}