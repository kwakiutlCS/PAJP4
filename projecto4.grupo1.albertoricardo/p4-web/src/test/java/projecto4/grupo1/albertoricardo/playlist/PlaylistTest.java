package projecto4.grupo1.albertoricardo.playlist;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.PlaylistEJB;
import projecto4.grupo1.albertoricardo.PlaylistEntity;
import projecto4.grupo1.albertoricardo.user.UserLogged;


@RunWith(MockitoJUnitRunner.class)
public class PlaylistTest {
	
	
	@Mock
	private PlaylistEJB pl_ejb;
	@Mock
	private PlaylistEntity plE;
	@Mock
	private UserLogged user;
	@InjectMocks
	private Playlist pl;
	
	@Before
	public void setUp() {
		List<PlaylistEntity> plEs = new ArrayList<PlaylistEntity>();
		
		
	}
	
//	@Test
//	public void verifyPlaylistNameTest(){
//		List<PlaylistEntity> plEs = new ArrayList<PlaylistEntity>();
//		List<MusicEntity> musics = new ArrayList<MusicEntity>();
//		PlaylistEntity e = new PlaylistEntity(1, "John", musics);
//		plEs.add(e);
//		pl.setName("John");
//		Mockito.when(pl_ejb.getOwnPlaylists(user.getUser().getId())).thenReturn(plEs);
//		Assert.assertEquals(pl.verifyPlaylistName(), true);	
//	}
//	
//	
//	@Test
//	public void insertPlaylistTest(){
//	
//		Mockito.when(pl.verifyPlaylistName()).thenReturn(false);
//		pl.setName("john");
//		pl.insertPlaylist();
//		Assert.assertEquals(pl_ejb.findName("john"), true);	
//	
//	}
//	
	
	
	
	
	
	

}
