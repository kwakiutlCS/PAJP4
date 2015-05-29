package projecto4.grupo1.albertoricardo.music;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.MusicListEJBLocal;

@Named
@SessionScoped
public class ListMusic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MusicListEJBLocal mlejb;

	private List<MusicEntity> musics;

	public ListMusic() {
		musics = new ArrayList<MusicEntity>();
	}



	public List<MusicEntity> getMusics() {
		try {
			musics = mlejb.listMusics();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return musics;
	}



}