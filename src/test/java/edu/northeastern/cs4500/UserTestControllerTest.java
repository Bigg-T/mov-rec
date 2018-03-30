package edu.northeastern.cs4500;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;

import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.DB.user.UserRepository;
import edu.northeastern.cs4500.DB.user.UserService;

/**
 * Tests for the User's Controller and any helper methods
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTestControllerTest {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	UserRepository userRepo;
	
	@Test
	public void testAllUsers() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/user/all_users"),
				HttpMethod.GET, entity, String.class);
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}

	@Test
	public void testAddUserSuccess() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/add_User/?fname=Jeanpaul&lname=Torre&email=torre.j+100@husky.neu.edu&pw=password&username=jeanpaulrt100"),
				HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		boolean isSuccess = (boolean) body.get("isSuccess");
		Assert.assertEquals(response.getStatusCodeValue(), 200);
		Assert.assertEquals((boolean)body.get("isSuccess"), true);
		
		if (isSuccess) {
			userRepo.delete((Integer)body.get("id"));
		}
	}
	
	@Test
	public void testAddExitingUser() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/add_User/?fname=Jeanpaul&lname=Torre&email=torre.j+100@husky.neu.edu&pw=password&username=jeanpaulrt100"),
				HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		boolean isSuccess = (boolean) body.get("isSuccess");
		Assert.assertEquals(response.getStatusCodeValue(), 200);
		Assert.assertEquals((boolean)body.get("isSuccess"), true);
		
		ResponseEntity<HashMap> response2 = restTemplate.exchange(
				createURLWithPort("/api/user/add_User/?fname=Jeanpaul&lname=Torre&email=torre.j+100@husky.neu.edu&pw=password&username=jeanpaulrt100"),
				HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body2 = response2.getBody();
		Assert.assertEquals((boolean)body2.get("isSuccess"), false);
		
		if (isSuccess) {
			userRepo.delete((Integer)body.get("id"));
		}
	}
	
	@Test
	public void testValidateUser() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		//Tests for wrong username and password
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/validate_login/?username=townhall-test&pw=password"),
				HttpMethod.GET, entity, HashMap.class);
		HashMap<String,Object> body = response.getBody();
		Assert.assertEquals((boolean)body.get("isSuccess"), false);
		
		UserObject testUser = new UserObject("test-fname", "test-lname", "test-email", "test-password", "test-username");
		try {
			userRepo.save(testUser);
			
			//Tests for existing User's username and password
			ResponseEntity<HashMap> response2 = restTemplate.exchange(
					createURLWithPort("/api/user/validate_login/?username=test-username&pw=test-password"),
					HttpMethod.GET, entity, HashMap.class);
			HashMap<String,Object> body2 = response2.getBody();
			Assert.assertEquals((boolean)body2.get("isSuccess"), true);
			
			//Tests for wrong password
			ResponseEntity<HashMap> response3 = restTemplate.exchange(
					createURLWithPort("/api/user/validate_login/?username=test-username&pw=wrong-password"),
					HttpMethod.GET, entity, HashMap.class);
			HashMap<String,Object> body3 = response3.getBody();
			Assert.assertEquals((boolean)body3.get("isSuccess"), false);
			
			userRepo.delete(testUser);
		} catch(Exception e) {
			userRepo.delete(testUser);
		}
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
