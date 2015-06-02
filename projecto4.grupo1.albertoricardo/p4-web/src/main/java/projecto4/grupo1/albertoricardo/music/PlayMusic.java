package projecto4.grupo1.albertoricardo.music;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javazoom.jl.player.Player;

@Named
@SessionScoped
public class PlayMusic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;


	public PlayMusic() {
	}


	public void play(String filename) {
		try {  
			InputStream buffer = new FileInputStream(filename);
			player = new Player(buffer);
			player.play();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}

	public void stop() {
		if (player != null) {
			player.close();
		}
	}

}