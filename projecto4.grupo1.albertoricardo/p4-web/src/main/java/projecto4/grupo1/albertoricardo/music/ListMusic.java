package projecto4.grupo1.albertoricardo.music;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.MusicListEJBLocal;
import projecto4.grupo1.albertoricardo.user.UserLogged;

@Named
@SessionScoped
public class ListMusic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MusicListEJBLocal mlejb;

	@Inject
	private UserLogged userlog;

	private List<MusicEntity> musics;



	public List<MusicEntity> getMusics() {
		if (musics == null) {
			try {
				musics = mlejb.listMusics();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return musics;

	}

	public List<MusicEntity> getOwnMusic() {
		if (musics == null) {
			try {
				musics = mlejb.listOwnMusics(userlog.getUser());
			} catch (Exception e) {

			}
		}

		return musics;
	}



	public void onRowEdit(RowEditEvent event) {
		MusicEntity newMusic = (MusicEntity) event.getObject();
		System.out.println(newMusic.getAlbum());
		boolean updated = mlejb.update(newMusic.getTitle(), newMusic.getArtist(), newMusic.getAlbum(), newMusic.getDateRecord(), newMusic.getId());
		System.out.println(updated);

		FacesMessage msg = new FacesMessage("Editado com successo.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}



}