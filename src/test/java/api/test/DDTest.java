package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.User;
import api.endpoints.UserEndPoints;
import api.utilites.DataProviders;
import io.restassured.response.Response;

public class DDTest {

	@Test(priority = 1,dataProvider = "data",dataProviderClass =DataProviders.class )
	public void testPostUser(String UserId,String UserName,String FirstName,String LastName,String Email,String pswd,String ph) {
		
		User payload=new User();
		payload.setId(Integer.parseInt(UserId));
		payload.setUsername(UserName);
		payload.setFirstname(FirstName);
		payload.setLastName(LastName);
		payload.setEmail(Email);
		payload.setPassword(ph);
		payload.setPhone(ph);
		Response resp=UserEndPoints.createUser(payload);
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	@Test(priority = 2,dataProvider = "UserName",dataProviderClass = DataProviders.class)
	public void deleteUser(String user) {
		
		Response resp=UserEndPoints.deleteUser(user);
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
}
