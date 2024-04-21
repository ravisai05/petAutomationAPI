package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.User;
import api.endpoints.UserEndPoints;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User payload;
	public Logger logger;
	@BeforeClass
	public void setupData(){
		
		faker=new Faker();
		payload=new User();
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstname(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password(5,10));
		payload.setPhone(faker.phoneNumber().cellPhone());
		logger=LogManager.getLogger(this.getClass());
		
			
	}
	
	@Test(priority = 1)
	public void testPost()
	{
		logger.info("****************Creating User**********************");
		Response response=UserEndPoints.createUser(payload);
		System.out.println(payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********************Created User********************");
		
	}
	@Test(priority = 2)
	public void getUsername() {
		logger.info("********************Reading user info****************");
		Response resp=UserEndPoints.getUser(this.payload.getUsername());
		resp.then().log().all();
		Assert.assertEquals(resp.getStatusCode(),200);
		logger.info("********************user info Displayed****************");
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("********************Updating user info****************");
		payload.setUsername(faker.name().username());
		payload.setFirstname(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		
		Response resp=UserEndPoints.UpdateUser(this.payload.getUsername(),payload);
		resp.then().log().all();
		Assert.assertEquals(resp.getStatusCode(),200);
		logger.info("********************user info Updated****************");
	}
	
	@Test(priority = 4)
	public void deleteUser() {
		logger.info("********************Deleting user info****************");
		Response response=UserEndPoints.deleteUser(this.payload.getUsername());
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("********************deleted  user info****************");
	}
}
