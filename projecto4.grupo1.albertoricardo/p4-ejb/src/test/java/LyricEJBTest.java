import static org.junit.Assert.*;


import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.LyricsEJB;
import projecto4.grupo1.albertoricardo.LyricsEntity;
import projecto4.grupo1.albertoricardo.dto.LyricDTO;

@RunWith(MockitoJUnitRunner.class)
public class LyricEJBTest {
	
	@Mock
	LyricDTO dto;
	@Mock
	EntityManager em;
	@Mock
	LyricsEntity le;
	@InjectMocks
	LyricsEJB ejb;

	@Test
	public void editedLyricsUpdate() {
		ejb.editedLyrics(dto);
		Mockito.verify(em).merge(Mockito.any(LyricsEntity.class));
	}

}
