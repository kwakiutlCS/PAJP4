package projecto4.grupo1.albertoricardo.playlist;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.PlaylistEJB;
import projecto4.grupo1.albertoricardo.PlaylistEntity;
import projecto4.grupo1.albertoricardo.UserEntity;
import projecto4.grupo1.albertoricardo.user.UserLogged;

@Named
@ViewScoped
public class ListPlaylist implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ListPlaylist.class);
	@EJB
	private PlaylistEJB plistejb;

	@Inject
	private UserLogged userlog;

	private List<PlaylistEntity> playlists;

	private PlaylistEntity selectedPlaylist;

	public List<PlaylistEntity> ownPlaylists(UserEntity u) {
		try {
			playlists = plistejb.getOwnPlaylists(u.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return playlists;
	}

	public List<PlaylistEntity> getPlaylists() {
		if (playlists == null) {
			try {
				playlists = plistejb.getOwnPlaylists(userlog.getUser().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return playlists;
	}

	public void addToSelectedPlaylist(MusicEntity music) {
		boolean duplicate = false;
		System.out.println(music.getTitle());
		int aux = music.getId();
		for (MusicEntity m:selectedPlaylist.getMusics()) {
			if (m.getId() == aux) duplicate = true;
			FacesMessage msg = new FacesMessage("Erro","Música já existe na Playlist seleccionada.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		if (!duplicate) {
			try {
				selectedPlaylist.getMusics().add(music);
				plistejb.update(selectedPlaylist);
				FacesMessage msg = new FacesMessage("Playlists","Música "+music.getTitle()+" adicionada com successo à playlist seleccionada.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage("Erro",e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

	public void removeMusicFromPlaylist(PlaylistEntity playlist, MusicEntity music) {
		System.out.println("Entrou aqui");
		Iterator<MusicEntity> it = playlist.getMusics().iterator();
		while(it.hasNext()) {
			MusicEntity m = it.next();
			if (m.getId() == music.getId()) it.remove();
		}
		log.debug("Delete from playlist");
		plistejb.update(playlist);
		FacesMessage msg = new FacesMessage("Playlist","Música "+music.getTitle()+" removida com sucesso da playlist "+playlist.getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		log.warn("Música "+music.getTitle()+" removida da playlist "+playlist.getName()+" do utilizador "+playlist.getUserOwner().getName());
	}

	public void deletePlaylist(PlaylistEntity p) {
		plistejb.removePlaylistFromUser(p);
		refresh();
		playlists = getPlaylists();
	}

	public void onRowEdit(RowEditEvent event) {
		plistejb.update((PlaylistEntity) event.getObject());
		FacesMessage msg = new FacesMessage("Playlist","Playlist "+((PlaylistEntity) event.getObject()).getName()+" editado com successo.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	private void refresh() {
		try {
			playlists = plistejb.getOwnPlaylists(userlog.getUser().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPlaylists(List<PlaylistEntity> playlists) {
		this.playlists = playlists;
	}

	public PlaylistEntity getSelectedPlaylist() {
		return selectedPlaylist;
	}

	public void setSelectedPlaylist(PlaylistEntity selectedPlaylist) {
		this.selectedPlaylist = selectedPlaylist;
	}



}
