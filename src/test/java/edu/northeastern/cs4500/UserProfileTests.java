package edu.northeastern.cs4500;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)public class UserProfileTests {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	UserRepository userRepo;
	
	UserObject test_user_1;
	UserObject test_user_2;
	
	
	@Before
	public void setUp() {
	    String l_first_name = "34grdgeg34";
	    String l_last_name = "35gr4gweg4e";
	    String l_email = "g4eg4egh5ehe5he";
	    String l_password = "ehe5heh4ehd4";
	    String l_username = "e5ge4hd43g";
	    test_user_1 = new UserObject(l_first_name, l_last_name, l_email, l_password, l_username);
		test_user_1.setLogged(true);
		userRepo.save(test_user_1);
		
		test_user_2 = new UserObject(l_first_name + "_2", l_last_name + "_2", l_email + "_2", l_password + "_2", l_username +"_2");
		userRepo.save(test_user_2);
	}
	
	@After
	public void un_setUp() {
		userRepo.delete(test_user_1);
		userRepo.delete(test_user_2);
	}
	
    /**
     * Test that the User Profile pages gets all the data it needs from the REST API for the specific user
     */
    @Test
    public void testGetUserProfileData() throws Exception {
    //	String first_name, String last_name, String email, String password, String username
	    String l_first_name = "test-first";
	    String l_last_name = "test-last";
	    String l_email = "test-email-2";
	    String l_password = "test-password";
	    String l_username = "test-username-2";
	    String l_about_me = "Test About me";
	    String l_prof_pic = "Profile Picture";
    		UserObject ltestUser = new UserObject(l_first_name, l_last_name, l_email, l_password, l_username);
		ltestUser.setLogged(true);
		
    	    String first_name = "test-first";
    	    String last_name = "test-last";
    	    String email = "test-email-3";
    	    String password = "test-password";
    	    String username = "test-username-3";
    	    String about_me = "Test About me";
    	    String prof_pic = "Profile Picture";
    		UserObject testUser = new UserObject(first_name, last_name, email, password, username);
    		testUser.setAbout_me(about_me);
    		testUser.setProf_pic(prof_pic);
    		try {
    	  		//testUser.setLogged(true);
        		userRepo.save(testUser);
        		userRepo.save(ltestUser);
        		int test_user_id = testUser.getId();
        		int ltest_user_id = ltestUser.getId();
	    		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	    		ResponseEntity<HashMap> response = restTemplate.exchange(
	    				createURLWithPort("/api/user/profile/?id=" + test_user_id + "&user_request=" + ltest_user_id),
	    				HttpMethod.GET, entity, HashMap.class);
	    		HashMap<String, Object> context = response.getBody();
	    		Assert.assertEquals(response.getStatusCodeValue(), 200);
	    		Assert.assertEquals((String)context.get("last_name"), last_name);
	    		Assert.assertEquals((String)context.get("first_name"), first_name);
	    		Assert.assertEquals((String)context.get("profile_picture"), prof_pic);
	    		Assert.assertEquals((String)context.get("about_me"), about_me);
	    		Assert.assertEquals((boolean)context.get("isSuccess"), true);
	    		userRepo.delete(testUser);
	    		userRepo.delete(ltestUser);
    		} catch(Exception e) {
    			userRepo.delete(testUser);
	    		userRepo.delete(ltestUser);
    		}
  
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
		try {
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
		} catch(Exception e) {
			userRepo.delete(user);
			userRepo.delete(friend);
		}
 
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
		try {
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
		} catch(Exception e) {
			userRepo.delete(user);
			userRepo.delete(friend); 
		}
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
		try {
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
		} catch(Exception e) {
			userRepo.delete(user);
			userRepo.delete(friend);
		}
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
		try {
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
		} catch(Exception e) {
			userRepo.delete(user);
			userRepo.delete(friend);
		}
    }
   
    @Test 
    public void testBadUsers() throws Exception{
    	
        String first_name = "user3";
        String last_name = "user3";
        String email = "user3";
        String password = "user3";
        String username = "user3";
	    UserObject user = new UserObject(first_name, last_name, email, password, username);
	    try {
	    	userRepo.save(user);
			int real_id = user.getId();
			int fake_id = 0;
			
			Integer null_id = null;
			Integer user_request_null = null;
			
		    //tests for fake friend id
			HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
			ResponseEntity<HashMap> response2 = restTemplate.exchange(
					createURLWithPort("/api/user/remove_friend/?userId=" + real_id + "&" + "friendId=" + fake_id),
					HttpMethod.POST, entity2, HashMap.class);
			HashMap<String, Object> context2 = response2.getBody();
			Assert.assertEquals((boolean)context2.get("isSuccess"), false);
			Assert.assertEquals((String)context2.get("message"), "Friend does not exist");
			
//			//tests for fake user id
			HttpEntity<String> entity3 = new HttpEntity<String>(null, headers);
			ResponseEntity<HashMap> response3 = restTemplate.exchange(
					createURLWithPort("/api/user/remove_friend/?userId=" + fake_id + "&" + "friendId=" + real_id),
					HttpMethod.POST, entity2, HashMap.class);
			HashMap<String, Object> context3 = response3.getBody();
			Assert.assertEquals((boolean)context3.get("isSuccess"), false);
			Assert.assertEquals((String)context3.get("message"), "User does not exist");
//			
//		    //tests for fake friend id
			HttpEntity<String> entity4 = new HttpEntity<String>(null, headers);
			ResponseEntity<HashMap> response4 = restTemplate.exchange(
					createURLWithPort("/api/user/add_friend/?userId=" + real_id + "&" + "friendId=" + fake_id),
					HttpMethod.POST, entity4, HashMap.class);
			HashMap<String, Object> context4 = response4.getBody();
			Assert.assertEquals((boolean)context4.get("isSuccess"), false);
			Assert.assertEquals((String)context4.get("message"), "Friend Requested does not exist");

//			//tests for fake user id
			HttpEntity<String> entity5 = new HttpEntity<String>(null, headers);
			ResponseEntity<HashMap> response5 = restTemplate.exchange(
					createURLWithPort("/api/user/add_friend/?userId=" + fake_id + "&" + "friendId=" + real_id),
					HttpMethod.POST, entity5, HashMap.class);
			HashMap<String, Object> context5 = response5.getBody();
			Assert.assertEquals((boolean)context5.get("isSuccess"), false);
			Assert.assertEquals((String)context5.get("message"), "User does not exist");
			
			
//			//tests for null inputs
			HttpEntity<String> entity6 = new HttpEntity<String>(null, headers);
			ResponseEntity<HashMap> response6 = restTemplate.exchange(
					createURLWithPort("/api/user/add_friend/?userId=" + null_id + "&" + "friendId=" + user_request_null),
					HttpMethod.POST, entity6, HashMap.class);
			HashMap<String, Object> context6 = response6.getBody();
			Assert.assertEquals((boolean)context6.get("isSuccess"), false);
			Assert.assertEquals((String)context6.get("message"), "Incorrect Input");
			
			
//			tests for logged out user
			HttpEntity<String> entity7 = new HttpEntity<String>(null, headers);
			ResponseEntity<HashMap> response7 = restTemplate.exchange(
					createURLWithPort("/api/user/add_friend/?userId=" + real_id + "&" + "friendId=" + real_id),
					HttpMethod.POST, entity7, HashMap.class);
			HashMap<String, Object> context7 = response7.getBody();
			Assert.assertEquals((boolean)context7.get("isSuccess"), false);
			Assert.assertEquals((String)context7.get("message"), "User Not Logged In");
			
			userRepo.delete(user);
	    } catch (Exception e) {
			userRepo.delete(user);
	    }
    }
    
    @Test 
    public void testSameUser() throws Exception{
        String first_name = "user3";
        String last_name = "user3";
        String email = "user3";
        String password = "user3";
        String username = "user3";
        
        UserObject user = new UserObject(first_name, last_name, email, password, username);
        try {
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
        } catch(Exception e) {
    			userRepo.delete(user);
        }  
    }
    
    
    	@Test
    	public void testFriendsProfile() {
    		
    		HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
    		ResponseEntity<HashMap> response2 = restTemplate.exchange(
    				createURLWithPort("/api/user/profile/?id=" + test_user_2.getId() + "&user_request=" + test_user_1.getId()),
    				HttpMethod.GET, entity2, HashMap.class);
    		HashMap<String, Object> context2 = response2.getBody();
    		Assert.assertEquals((boolean)context2.get("isSuccess"), true);
    		Assert.assertEquals((boolean)context2.get("isFriend"), false);
    		userRepo.delete(test_user_1);
    		userRepo.delete(test_user_2);
    	}
    	
    	@Test
    	public void testNotLoggedInProfile() {
    		HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
    		ResponseEntity<HashMap> response2 = restTemplate.exchange(
    				createURLWithPort("/api/user/profile/?id=" + test_user_1.getId() + "&user_request=" + test_user_2.getId()),
    				HttpMethod.GET, entity2, HashMap.class);
    		HashMap<String, Object> context2 = response2.getBody();
    		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
    		Assert.assertEquals((String)context2.get("message"), "User Not Logged In");
    	}
    	

    	@Test
    	public void testEditProfile() {
    		//profile edit input: 
    		//Integer user_request, String about_me, String first_name, String last_name
    		Integer user_request = test_user_1.getId();
    		String about_me = "testing about me";
    		String first_name = "edit first namee";
    		String last_name = "edit last name";
       	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
    		ResponseEntity<HashMap> response2 = restTemplate.exchange(
    				createURLWithPort("/api/user/profile/edit/?user_request=" + user_request + "&about_me=" + about_me
    						+ "&first_name=" + first_name + "&last_name=" + last_name),
    				HttpMethod.POST, entity2, HashMap.class);
    		HashMap<String, Object> context2 = response2.getBody();
    		Assert.assertEquals((boolean)context2.get("isSuccess"), true);
    		Assert.assertEquals((String)context2.get("message"), "Succesfully Updated Profile");
    	}
    	
    	@Test
    	public void testEditProfileNotLoggedIn() {
    		Integer user_request = test_user_2.getId();
    		String about_me = "testing about me";
    		String first_name = "edit first namee";
    		String last_name = "edit last name";
       	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
    		ResponseEntity<HashMap> response2 = restTemplate.exchange(
    				createURLWithPort("/api/user/profile/edit/?user_request=" + user_request + "&about_me=" + about_me
    						+ "&first_name=" + first_name + "&last_name=" + last_name),
    				HttpMethod.POST, entity2, HashMap.class);
    		HashMap<String, Object> context2 = response2.getBody();
    		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
    		Assert.assertEquals((String)context2.get("message"), "User Not Logged In");
    	}
    	
    	@Test
    	public void testEditProfileUserDoesNotExist() {
    		Integer user_request = 0;
    		String about_me = "testing about me";
    		String first_name = "edit first namee";
    		String last_name = "edit last name";
       	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
    		ResponseEntity<HashMap> response2 = restTemplate.exchange(
    				createURLWithPort("/api/user/profile/edit/?user_request=" + user_request + "&about_me=" + about_me
    						+ "&first_name=" + first_name + "&last_name=" + last_name),
    				HttpMethod.POST, entity2, HashMap.class);
    		HashMap<String, Object> context2 = response2.getBody();
    		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
    		Assert.assertEquals((String)context2.get("message"), "User Does Not Exist");
    	}
    	
    	@Test
    	public void testGetUserEditData() {
    		test_user_1.setAbout_me("Test About Me");
    		userRepo.save(test_user_1);
    		Integer user_request = test_user_1.getId();
       	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
    		ResponseEntity<HashMap> response2 = restTemplate.exchange(
    				createURLWithPort("/api/user/profile/edit/?user_request=" + user_request),
    				HttpMethod.GET, entity2, HashMap.class);
    		HashMap<String, Object> context2 = response2.getBody();
    		Assert.assertEquals((boolean)context2.get("isSuccess"), true);
    		Assert.assertEquals((String)context2.get("about_me"), test_user_1.getAbout_me());
    		Assert.assertEquals((String)context2.get("first_name"), test_user_1.getFirst_name());
    		Assert.assertEquals((String)context2.get("last_name"), test_user_1.getLast_name());
    	}
    	
    	@Test
    	public void testGetUserEditNotLoggedIn() {
    		Integer user_request = test_user_2.getId();
       	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
    		ResponseEntity<HashMap> response2 = restTemplate.exchange(
    				createURLWithPort("/api/user/profile/edit/?user_request=" + user_request),
    				HttpMethod.GET, entity2, HashMap.class);
    		HashMap<String, Object> context2 = response2.getBody();
    		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
    		Assert.assertEquals((String)context2.get("message"), "User Not Logged In");
    	}
    	
    	@Test
    	public void testGetUserEditUserDoesNotExist() {
    		Integer user_request = 0;
           	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
        		ResponseEntity<HashMap> response2 = restTemplate.exchange(
        				createURLWithPort("/api/user/profile/edit/?user_request=" + user_request),
        				HttpMethod.GET, entity2, HashMap.class);
        		HashMap<String, Object> context2 = response2.getBody();
        		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
        		Assert.assertEquals((String)context2.get("message"), "User Does Not Exist");
    	}
    	
    	@Test
    	public void testLogOut() {
    		Integer user_request = test_user_1.getId();
       	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
    		ResponseEntity<HashMap> response2 = restTemplate.exchange(
    				createURLWithPort("/api/user/validate_logout/?user_request=" + user_request),
    				HttpMethod.GET, entity2, HashMap.class);
    		HashMap<String, Object> context2 = response2.getBody();
    		
    		Assert.assertEquals((boolean)context2.get("isSuccess"), true);
    		Assert.assertEquals((boolean)context2.get("isLogged"), false);
    	}
    	
    	
    	@Test
    	public void testLogOutNotLoggedIn() {
    		Integer user_request = test_user_2.getId();
           	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
        		ResponseEntity<HashMap> response2 = restTemplate.exchange(
        				createURLWithPort("/api/user/validate_logout/?user_request=" + user_request),
        				HttpMethod.GET, entity2, HashMap.class);
        		HashMap<String, Object> context2 = response2.getBody();
        		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
        		Assert.assertEquals((String)context2.get("message"), "User not logged in");
    	}
    	
    	@Test
    	public void testLogOutUserNotExist() {
		Integer user_request = 0;
       	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
    		ResponseEntity<HashMap> response2 = restTemplate.exchange(
    				createURLWithPort("/api/user/validate_logout/?user_request=" + user_request),
    				HttpMethod.GET, entity2, HashMap.class);
    		HashMap<String, Object> context2 = response2.getBody();
    		Assert.assertEquals((boolean)context2.get("isSuccess"), false);
    		Assert.assertEquals((String)context2.get("message"), "User does not exist");
    	}
    	
    	
    	private String createURLWithPort(String uri) {
    		return "http://localhost:" + port + uri;
    	}
}
	

