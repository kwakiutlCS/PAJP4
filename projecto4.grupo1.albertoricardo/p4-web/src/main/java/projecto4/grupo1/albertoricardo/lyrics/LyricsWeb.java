package projecto4.grupo1.albertoricardo.lyrics;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.LyricsEJBLocal;
import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.dto.LyricDTO;
import projecto4.grupo1.albertoricardo.user.UserLogged;

@Named
@ViewScoped
public class LyricsWeb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(LyricsWeb.class);

	@EJB
	LyricsEJBLocal ejb;
	
	@Inject
	UserLogged user;

	private int musicid = 0;
	private boolean showing = false;
	private String customedLyrics = "";
	private MusicEntity musicett;
	private LyricDTO lyricdto;

	public LyricsWeb() {
	}

	public String getOriginalLyrics() {
		if (musicett == null) {
			return null;
		} else {
			try {
				String result = ejb.findOriginal(musicett.getId()).getLyrics();
				if (result.length() > 0) {
					return result;
				}
				else {
					return "Letra indisponível.";
				}
			} catch (Exception e) {
				return "Letra indisponível.";
			}
		}
	}

	public String getCustomLyrics() {
		String result = "";
		if (musicett == null) {
			return null;
		} else {
			try {
				LyricDTO ltemp = ejb.findCustomed(musicett.getId(), user.getUser().getId());
				if (ltemp.getUser() == null || ltemp.getUser().getId() < 1) {
					result = ejb.findOriginal(musicett.getId()).getLyrics();
				} else {
					lyricdto = ejb.findCustomed(musicett.getId(), user.getUser().getId());
					musicett = lyricdto.getMusic();
					result = lyricdto.getLyrics();
				}
				if (result.length() > 0) return result;
				else return "Indisponivel";
			} catch (Exception e) {
				return "Indisponivel";
			}
		}
	}
	
	public boolean available(MusicEntity m) {
		musicett = m;
		if ("Letra indisponível.".equals(getOriginalLyrics())) return false;
		else return true;
	}

	public int getMusicid() {
		return musicid;
	}

	public void setMusicid(int musicid) {
		this.musicid = musicid;
	}

	public void noShow() {
		showing = false;
	}

	public void show() {
		showing = true;
	}

	public boolean isShowing() {
		return showing;
	}

	public void setCustomedLyrics(String customedLyrics) {
		this.customedLyrics = customedLyrics;
	}

	public void saveNewLyric() {
		if (lyricdto == null) lyricdto = new LyricDTO(customedLyrics, user.getUser(), musicett);
		
		else lyricdto.setLyrics(customedLyrics);
		log.trace("saveNewLyric, musicett: "+musicett.getArtist());
		
		ejb.editedLyrics(lyricdto);
	}

	public String getCustomedLyrics() {
		customedLyrics = getCustomLyrics();
		return customedLyrics;
	}

	public void setMusicDTO(MusicEntity m) {
		musicett = m;
	}

	public void saveEditedLyrics() {

	}

	public MusicEntity getMusicett() {
		return musicett;
	}

	public void setMusicett(MusicEntity musicett) {
		this.musicett = musicett;
	}



}
