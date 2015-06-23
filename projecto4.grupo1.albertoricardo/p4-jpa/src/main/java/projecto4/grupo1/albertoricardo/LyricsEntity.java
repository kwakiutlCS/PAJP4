package projecto4.grupo1.albertoricardo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LyricsEntity {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String lyrics;
}
