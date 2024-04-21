package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;



public class UserEndPoints {
	
	public static ResourceBundle getUrl(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response createUser(User payload) {
		String post_url=getUrl().getString("post_url");
		Response response=given()
		.contentType(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_url);
		return response;
	}
	
	public static Response getUser(String user){
		String get_URL=getUrl().getString("get_URL");
		Response response=given()
		.pathParam("user", user)
		.when()
		.get(get_URL);
		return response;
	}
	public static Response UpdateUser(String userName, User payload){
		Response response=given()
		.contentType(ContentType.JSON)
		.pathParam("user", userName)
		.body(payload)
		.when()
		.put(Routes.user_update);
		return response;
	}
	
	 public static Response deleteUser(String user){
		Response response=given()
		.pathParam("user", user)
		.when()
		.delete(Routes.user_delete);
		return response;
	}
	
}
