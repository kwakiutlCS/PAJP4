package projecto4.grupo1.albertoricardo.playlist;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.PlaylistEJB;
import projecto4.grupo1.albertoricardo.user.UserLogged;



@RunWith(MockitoJUnitRunner.class)
public class PlaylistTest {
	
	
	
	@Mock
	private PlaylistEJB playlistejb;
	
	@Mock
	private UserLogged userlogged;
		
	@InjectMocks
	private Playlist pl;
	


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

//	@Test
//	public void verifyPlaylistNameTest() {
//
//		List<PlaylistEntity> plE = new ArrayList<PlaylistEntity>();
//		List<MusicEntity> musicas  =new ArrayList<MusicEntity>();
//		PlaylistEntity pl1 = new PlaylistEntity(50,"minhaplay", musicas);
//		plE.add(pl1);
//		UserEntity eu = new UserEntity();
//		
//	
//		Mockito.when(playlistejb.getOwnPlaylists(10)).thenReturn(plE);
//		pl.setName("minhaplay");
//		
//		Assert.assertEquals(pl.verifyPlaylistName(),true);
//		
//	}
	
	
	
	
	
	
	

}
