package projecto4.grupo1.albertoricardo;

import projecto4.grupo1.albertoricardo.dto.LyricDTO;
import projecto4.grupo1.albertoricardo.dto.MusicDTO;

public interface LyricsEJBLocal {

	LyricsEntity create(LyricsEntity lyrics);
	LyricsEntity update(LyricsEntity lyrics);
    void remove(LyricsEntity lyrics);
    LyricsEntity find(Object id);
	LyricDTO findCustomed(int musicid, int ownerid);
	LyricDTO findOriginal(int musicid);
	void editedLyrics(LyricDTO lyric);
}
