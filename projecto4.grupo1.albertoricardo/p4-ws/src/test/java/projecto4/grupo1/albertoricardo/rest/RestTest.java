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
public class RestTest {
	
	@Before
	public void setUp() {
		RestAssured.basePath = "/p4-ws/rest";
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	public void listUsersTest() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/users");
	}
	
	@Test
	public void nUsersTest() {
		RestAssured.expect().statusCode(200).contentType(ContentType.TEXT).when().get("/users/total");
	}
	
	@Test
	public void userDetailsTest() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/users/get/6");
	}
	
	@Test
	public void usersDetailsFailTest() {
		RestAssured.expect().statusCode(404).contentType(ContentType.TEXT).when().get("/users/get/150");
	}
	
	@Test
	public void loggedTotalTest() {
		RestAssured.expect().statusCode(200).contentType(ContentType.TEXT).when().get("/users/logged/total");
	}
	
	@Test
	public void listLoggedTest() {
		RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when().get("/users/logged");
	}
	
	@Test
	public void createTest() {
		RestAssured.given().contentType("application/x-www-form-urlencoded").
		formParam("username", "ab@ab.pt").
		formParam("name", "123").
		formParam("password", "ab").
		expect().
		statusCode(200).
		when().post("/users");
	}

}
