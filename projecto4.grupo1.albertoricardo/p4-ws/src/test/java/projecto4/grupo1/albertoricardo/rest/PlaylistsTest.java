package projecto4.grupo1.albertoricardo.rest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.UserEJBLocal;
import projecto4.grupo1.albertoricardo.app.Users;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
@RunWith(MockitoJUnitRunner.class)
public class PlaylistsTest {
	
	@Before
	public void setUp() {
		RestAssured.basePath = "/p4-ws/rest";
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	public void listUsersTest() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/playlists");
	}
	
	@Test
	public void showExistingPlaylist() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/playlists/1");
	}
	
	@Test
	public void showPlaylistNull() {
		RestAssured.expect().statusCode(404).contentType(ContentType.TEXT).when().get("/playlists/10002");
	}
	
	@Test
	public void showExistingPlaylistFromUser() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/playlists/user/6");
	}

	@Test
	public void showPlaylistNullFromUser() {
		RestAssured.expect().statusCode(404).contentType(ContentType.TEXT).when().get("/playlists/user/10002");
	}
	
	@Test
	public void showPlaylistCount() {
		RestAssured.expect().statusCode(200).contentType(ContentType.TEXT).when().get("/playlists/total");
	}
	
	@Test
	public void showPlaylistSongs() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/playlists/1/musics");
	}	
	


}
