package edu.northeastern.cs4500;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.DB.user.UserProfile;
import edu.northeastern.cs4500.DB.user.UserRepository;
import edu.northeastern.cs4500.DB.user.UserService;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for the User's Controller and any helper methods
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserProfileTests {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	UserRepository userRepo;
	
    /**
     * Test that the User Profile pages gets all the data it needs from the REST API for the specific user
     */
    @Test
    public void testGetUserProfileData() throws Exception {
    //	String first_name, String last_name, String email, String password, String username
    	    String first_name = "test-first";
    	    String last_name = "test-last";
    	    String email = "test-email-2";
    	    String password = "test-password";
    	    String username = "test-username-2";
    	    String about_me = "Test About me";
    	    String prof_pic = "Profile Picture";
    		UserObject testUser = new UserObject(first_name, last_name, email, password, username);
    		testUser.setAbout_me(about_me);
    		testUser.setProf_pic(prof_pic);
    		userRepo.save(testUser);
    		int test_user_id = testUser.getId();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/profile/?id=" + test_user_id),
				HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> context = response.getBody();
		Assert.assertEquals(response.getStatusCodeValue(), 200);
		Assert.assertEquals((String)context.get("last_name"), last_name);
		Assert.assertEquals((String)context.get("first_name"), first_name);
		Assert.assertEquals((String)context.get("profile_picture"), prof_pic);
		Assert.assertEquals((String)context.get("about_me"), about_me);
		Assert.assertEquals((boolean)context.get("isSuccess"), true);
		userRepo.delete(testUser);
    }
    
    /**
     * Tests that a User can add another user
     * @throws Exception
     */
    @Test
    public void testAddNewFriend() throws Exception {
	    
        String first_name = "user3";
        String last_name = "user3";
        String email = "user3";
        String password = "user3";
        String username = "user3";
        
        String first_name_2 = "friend3";
        String last_name_2 = "friend3";
        String email_2 = "friend3";
        String password_2 = "friend3";
        String username_2 = "friend3";
    	
    	
		UserObject user = new UserObject(first_name, last_name, email, password, username);
		UserObject friend = new UserObject(first_name_2, last_name_2, email_2, password_2, username_2);
		
		userRepo.save(user);
		userRepo.save(friend);
		
		int user_id = user.getId();
		int friend_id = friend.getId();
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/add_friend/?userId=" + user_id + "&" + "friendId=" + friend_id),
				HttpMethod.POST, entity, HashMap.class);
		HashMap<String, Object> context = response.getBody();
		Assert.assertEquals((boolean)context.get("isSuccess"), true);
		userRepo.delete(user);
		userRepo.delete(friend); 
    }
    
    /**
     * Tests that a User cannot add a friend that they already had
     * @throws Exception
     */
    @Test
    public void testAddExisitingFriend() throws Exception {
        String first_name = "user3";
        String last_name = "user3";
        String email = "user3";
        String password = "user3";
        String username = "user3";
        
        String first_name_2 = "friend3";
        String last_name_2 = "friend3";
        String email_2 = "friend3";
        String password_2 = "friend3";
        String username_2 = "friend3";    
    	
		UserObject user = new UserObject(first_name, last_name, email, password, username);
		UserObject friend = new UserObject(first_name_2, last_name_2, email_2, password_2, username_2);
		
		userRepo.save(user);
		userRepo.save(friend);
		
		int user_id = user.getId();
		int friend_id = friend.getId();
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/add_friend/?userId=" + user_id + "&" + "friendId=" + friend_id),
				HttpMethod.POST, entity, HashMap.class);
		HashMap<String, Object> context = response.getBody();
		Assert.assertEquals((boolean)context.get("isSuccess"), true);
		
		HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response2 = restTemplate.exchange(
				createURLWithPort("/api/user/add_friend/?userId=" + user_id + "&" + "friendId=" + friend_id),
				HttpMethod.POST, entity2, HashMap.class);
		HashMap<String, Object> context2 = response2.getBody();
		
		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
		Assert.assertEquals((String)context2.get("message"), "Already Friends");

		userRepo.delete(user);
		userRepo.delete(friend); 
    }
    
    /**
     * Tests that a User can remove a friend
     * @throws Exception
     */
    @Test
    public void testRemoveFriend() throws Exception {
        String first_name = "user3";
        String last_name = "user3";
        String email = "user3";
        String password = "user3";
        String username = "user3";
        
        String first_name_2 = "friend3";
        String last_name_2 = "friend3";
        String email_2 = "friend3";
        String password_2 = "friend3";
        String username_2 = "friend3";   
    	
		UserObject user = new UserObject(first_name, last_name, email, password, username);
		UserObject friend = new UserObject(first_name_2, last_name_2, email_2, password_2, username_2);
		
		userRepo.save(user);
		userRepo.save(friend);
		
		int user_id = user.getId();
		int friend_id = friend.getId();
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/add_friend/?userId=" + user_id + "&" + "friendId=" + friend_id),
				HttpMethod.POST, entity, HashMap.class);
		HashMap<String, Object> context = response.getBody();
		Assert.assertEquals((boolean)context.get("isSuccess"), true);
		
		
		HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response2 = restTemplate.exchange(
				createURLWithPort("/api/user/remove_friend/?userId=" + user_id + "&" + "friendId=" + friend_id),
				HttpMethod.POST, entity2, HashMap.class);
		HashMap<String, Object> context2 = response2.getBody();
		Assert.assertEquals((boolean)context2.get("isSuccess"), true);
    	
		userRepo.delete(user);
		userRepo.delete(friend); 
    	
    }
    
    /**
     * Tests that a User can't remove a friend that it's not friends with
     * @throws Exception
     */
    @Test
    public void testRemoveWrongFriend() throws Exception {
        String first_name = "user3";
        String last_name = "user3";
        String email = "user3";
        String password = "user3";
        String username = "user3";
        
        String first_name_2 = "friend3";
        String last_name_2 = "friend3";
        String email_2 = "friend3";
        String password_2 = "friend3";
        String username_2 = "friend3";
	
    	    UserObject user = new UserObject(first_name, last_name, email, password, username);
		UserObject friend = new UserObject(first_name_2, last_name_2, email_2, password_2, username_2);
		
		userRepo.save(user);
		userRepo.save(friend);
		
		int user_id = user.getId();
		int friend_id = friend.getId();
		
		HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response2 = restTemplate.exchange(
				createURLWithPort("/api/user/remove_friend/?userId=" + user_id + "&" + "friendId=" + friend_id),
				HttpMethod.POST, entity2, HashMap.class);
		HashMap<String, Object> context2 = response2.getBody();
		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
		Assert.assertEquals((String)context2.get("message"), "User's are not friends");
		//User's are not friends
    	
		userRepo.delete(user);
		userRepo.delete(friend);
    }
   
    @Test 
    public void testBadUsers() throws Exception{
    	
        String first_name = "user3";
        String last_name = "user3";
        String email = "user3";
        String password = "user3";
        String username = "user3";
	    UserObject user = new UserObject(first_name, last_name, email, password, username);

		userRepo.save(user);
		int real_id = user.getId();
		int fake_id = 0;
		
	    //tests for fake friend id
		HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response2 = restTemplate.exchange(
				createURLWithPort("/api/user/remove_friend/?userId=" + real_id + "&" + "friendId=" + fake_id),
				HttpMethod.POST, entity2, HashMap.class);
		HashMap<String, Object> context2 = response2.getBody();
		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
		Assert.assertEquals((String)context2.get("message"), "Friend does not exist");

		
//		//tests for fake user id
		HttpEntity<String> entity3 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response3 = restTemplate.exchange(
				createURLWithPort("/api/user/remove_friend/?userId=" + fake_id + "&" + "friendId=" + real_id),
				HttpMethod.POST, entity2, HashMap.class);
		HashMap<String, Object> context3 = response3.getBody();
		Assert.assertEquals((boolean)context3.get("isSuccess"), false);
		Assert.assertEquals((String)context3.get("message"), "User does not exist");
//		
//	    //tests for fake friend id
		HttpEntity<String> entity4 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response4 = restTemplate.exchange(
				createURLWithPort("/api/user/add_friend/?userId=" + real_id + "&" + "friendId=" + fake_id),
				HttpMethod.POST, entity4, HashMap.class);
		HashMap<String, Object> context4 = response4.getBody();
		Assert.assertEquals((boolean)context4.get("isSuccess"), false);
		Assert.assertEquals((String)context4.get("message"), "Friend Requested does not exist");

//		//tests for fake user id
		HttpEntity<String> entity5 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response5 = restTemplate.exchange(
				createURLWithPort("/api/user/add_friend/?userId=" + fake_id + "&" + "friendId=" + real_id),
				HttpMethod.POST, entity5, HashMap.class);
		HashMap<String, Object> context5 = response5.getBody();
		Assert.assertEquals((boolean)context5.get("isSuccess"), false);
		Assert.assertEquals((String)context5.get("message"), "User does not exist");
		
		userRepo.delete(user);
    }
    
    @Test 
    public void testSameUser() throws Exception{
        String first_name = "user3";
        String last_name = "user3";
        String email = "user3";
        String password = "user3";
        String username = "user3";
        
        UserObject user = new UserObject(first_name, last_name, email, password, username);
        userRepo.save(user);
    	
		int user_id = user.getId();
		int friend_id = user.getId();
		
		HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response2 = restTemplate.exchange(
				createURLWithPort("/api/user/remove_friend/?userId=" + user_id + "&" + "friendId=" + friend_id),
				HttpMethod.POST, entity2, HashMap.class);
		HashMap<String, Object> context2 = response2.getBody();
		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
		Assert.assertEquals((String)context2.get("message"), "User cannot remove itself");
		
		HttpEntity<String> entity3 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response3 = restTemplate.exchange(
				createURLWithPort("/api/user/add_friend/?userId=" + user_id + "&" + "friendId=" + friend_id),
				HttpMethod.POST, entity3, HashMap.class);
		HashMap<String, Object> context3 = response3.getBody();
		Assert.assertEquals((boolean)context3.get("isSuccess"), false);
		Assert.assertEquals((String)context3.get("message"), "User cannot add itself");
		
		userRepo.delete(user);
    }
    
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
	

