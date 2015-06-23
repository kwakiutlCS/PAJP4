package projecto4.grupo1.albertoricardo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lyrics")
public class LyricsEntity {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String lyrics;
	@ManyToOne
	private UserEntity user;
	@ManyToOne
	private MusicEntity music;
}
