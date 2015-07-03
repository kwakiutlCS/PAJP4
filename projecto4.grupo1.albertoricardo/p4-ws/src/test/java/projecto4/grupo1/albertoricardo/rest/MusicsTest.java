package projecto4.grupo1.albertoricardo.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
@RunWith(MockitoJUnitRunner.class)
public class MusicsTest {
	
	@Before
	public void setUp() {
		RestAssured.basePath = "/p4-ws/rest";
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	public void listMusicsTest() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/musics");
	}
	
	@Test
	public void listMusicsByUser() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/musics/user/9");
	}
	
	@Test
	public void listMusicsByUserNotFound() {
		RestAssured.expect().statusCode(404).contentType(ContentType.TEXT).when().get("/musics/user/999");
	}
	
	@Test
	public void countMusics() {
		RestAssured.expect().statusCode(200).contentType(ContentType.TEXT).when().get("/musics/total");
	}
	
	@Test
	public void showMusic() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/musics/1");
	}
	
	@Test
	public void showMusicNotFound() {
		RestAssured.expect().statusCode(404).when().get("/musics/1002");
	}

	@Test
	public void deleteMusicNotFound() {
		RestAssured.expect().statusCode(404).when().delete("/musics/user/1002");
	}

}
