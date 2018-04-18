package edu.northeastern.cs4500;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
import edu.northeastern.cs4500.DB.user.UserService;
import edu.northeastern.cs4500.JPARepositories.UserRepository;

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
	
    String first_name;
	String last_name;
	String email;
	String password;
	String username;

	String t_first_name;
	String t_last_name;
	String t_email;
	String t_password;
	String t_username;
	
	UserObject user;
	
	UserObject test_user_1;
	UserObject test_user_2;
	Integer api_created_user;
	
	@Before
	public void setUp() {
		String rand = RandomStringUtils.randomAlphanumeric(20);
		first_name = "test-first" +  rand;
	    last_name = "test-last" +  rand;
	    email = "test-email-3" +  rand;
	    password = "test-password" +  rand;
	    username = "test-username-3" +  rand;
	    
	    String rand2 = RandomStringUtils.randomAlphanumeric(20);
	    t_first_name = "test-first" +  rand2;
	    t_last_name = "test-last" +  rand2;
	    t_email = "test-email-3" +  rand2;
	    t_password = "test-password" +  rand2;
	    t_username = "test-username-3" +  rand2;
		
	    test_user_1 = new UserObject(first_name, last_name, email, password, username);
		test_user_2 = new UserObject(t_first_name, t_last_name, t_email, t_password, t_username);
		userRepo.save(test_user_1);
		api_created_user = 0;
	}
	
	@After
	public void destroy() {
		test_user_1.setFriends(null);
		try {
			userRepo.delete(test_user_1);
		} catch (Exception e) {
		}
		if (api_created_user != 0) {
			userRepo.delete(api_created_user);
		}
	}

	
	
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
				createURLWithPort("/api/user/add_User/?fname=afafsdg&lname=sdgsdgsdg&email=sdgsdgbsr&pw=password&username=fdbr4bre"),
				HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		boolean isSuccess = (boolean) body.get("isSuccess");
		Assert.assertEquals(response.getStatusCodeValue(), 200);
		Assert.assertEquals((boolean)body.get("isSuccess"), true);
		
		if (isSuccess) {
			api_created_user = (Integer)body.get("id");
		}
	}
	
	@Test
	public void testAddExitingUser() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);		
		ResponseEntity<HashMap> response2 = restTemplate.exchange(
				createURLWithPort("/api/user/add_User/?fname="+test_user_1.getFirst_name() +
						"&lname="+ test_user_1.getLast_name() +
						"&email="+test_user_1.getEmail() +
						"&pw="+test_user_1.getPassword() +
						"&username=" + test_user_1.getUsername()),
				HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body2 = response2.getBody();
		Assert.assertEquals((boolean)body2.get("isSuccess"), false);
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
			
			//Tests for existing User's username and password
			ResponseEntity<HashMap> response2 = restTemplate.exchange(
					createURLWithPort("/api/user/validate_login/?username=" + username + "&pw=" + password),
					HttpMethod.GET, entity, HashMap.class);
			HashMap<String,Object> body2 = response2.getBody();
			Assert.assertEquals((boolean)body2.get("isSuccess"), true);
			
			//Tests for wrong password
			ResponseEntity<HashMap> response3 = restTemplate.exchange(
					createURLWithPort("/api/user/validate_login/?username=" + username + "&pw=wrong-password"),
					HttpMethod.GET, entity, HashMap.class);
			HashMap<String,Object> body3 = response3.getBody();
			Assert.assertEquals((boolean)body3.get("isSuccess"), false);
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		Integer user_id = test_user_1.getId();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/delete/user/?user_id=" + user_id),
				HttpMethod.POST, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		boolean isSuccess = (boolean) body.get("isSuccess");
		Assert.assertEquals((boolean)body.get("isSuccess"), true);		
	}
	
	@Test
	public void testDeleteUserBad() throws Exception {
		Integer user_id = 0;
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/delete/user/?user_id=" + user_id),
				HttpMethod.POST, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		boolean isSuccess = (boolean) body.get("isSuccess");
		Assert.assertEquals((boolean)body.get("isSuccess"), false);		
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
