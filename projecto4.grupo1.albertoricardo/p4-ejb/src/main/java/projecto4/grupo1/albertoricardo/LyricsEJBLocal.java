package projecto4.grupo1.albertoricardo;

public interface LyricsEJBLocal {

	LyricsEntity create(LyricsEntity lyrics);
	LyricsEntity update(LyricsEntity lyrics);
    void remove(LyricsEntity lyrics);
    LyricsEntity find(Object id);
}
