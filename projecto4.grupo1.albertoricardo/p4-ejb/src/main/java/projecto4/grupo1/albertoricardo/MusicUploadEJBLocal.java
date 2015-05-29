package projecto4.grupo1.albertoricardo;


import java.util.Date;

import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.MusicEntity;



@Local
public interface MusicUploadEJBLocal {

	void uploadMusicDB(String title, String artist, String album,
			Date dateReleased, String path);
	
}