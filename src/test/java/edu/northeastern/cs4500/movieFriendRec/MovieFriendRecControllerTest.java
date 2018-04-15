package edu.northeastern.cs4500.movieFriendRec;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.JPARepositories.UserRepository;

public class MovieFriendRecControllerTest {
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


	@Before
	public void setUp() {
		String rand = RandomStringUtils.randomAlphanumeric(20);
		
		first_name = "test-first" + rand;
		last_name = "test-last" + rand;
		email = "test-email-3" + rand;
		password = "test-password" + rand;
		username = "test-username-3" + rand;
		String about_me = "Test About me" + rand;
		String prof_pic = "Profile Picture" + rand;
	    
	    if (userRepo.getUserByUsername(username).size() > 0) {
			UserObject user_exists = userRepo.getUserByUsername(username).get(0);
	    		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	    		if (user_exists.getFriends().size() > 0) {
	    			ResponseEntity<HashMap> response = restTemplate.exchange(
							createURLWithPort("/api/user/remove_friend/?userId=" + user_exists.getId() + "&" + "friendId=" + userRepo.getUserByUsername(username + "_2").get(0).getId()),
							HttpMethod.POST, entity, HashMap.class);
	    		}
				try {
					userRepo.delete(user_exists.getId());
				} catch(Exception e) {
				}
	    }

		if (userRepo.getUserByUsername(username + "_2").size() > 0) {
			UserObject user_exists_2 = userRepo.getUserByUsername(username + "_2").get(0);
		    	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
				ResponseEntity<HashMap> response = restTemplate.exchange(
						createURLWithPort("/api/user/remove_friend/?userId=" + user_exists_2.getId() + "&" + "friendId=" + userRepo.getUserByUsername(username).get(0).getId()),
						HttpMethod.POST, entity, HashMap.class);
		    	try {
					userRepo.delete(user_exists_2);
		    	} catch(Exception e) {
		    	}
		}

		test_user_1 = new UserObject(first_name, last_name, email, password, username);
		test_user_1.setAbout_me(about_me);
		test_user_1.setProf_pic(prof_pic);
		test_user_1.setLogged(true);

		test_user_2 = new UserObject(first_name + "_2", last_name + "_2", email + "_2", password + "_2", username +"_2");
		test_user_2.setAbout_me(about_me + "_2");
		test_user_2.setProf_pic(prof_pic + "_2");
		userRepo.save(test_user_1);
		userRepo.save(test_user_2);
	}

	@After
	public void destroy() {
    	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/remove_friend/?userId=" + test_user_1.getId() + "&" + "friendId=" + test_user_2.getId()),
				HttpMethod.POST, entity, HashMap.class);
		try {
			userRepo.delete(test_user_1);
		} catch (Exception e) {
		}

    	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response2 = restTemplate.exchange(
				createURLWithPort("/api/user/remove_friend/?userId=" + test_user_2.getId() + "&" + "friendId=" + test_user_1.getId()),
				HttpMethod.POST, entity2, HashMap.class);
		try {
			userRepo.delete(test_user_2);
		} catch(Exception e) {
		}
	}
	
	
	
    /**
     * Friend Recommends Movie To Another Friend
     */
    @Test
    public void testRecommendMovie() throws Exception {

    }
    
    /**
     * User Deletes Friend Recommended movie
     */
    @Test
    public void testDeleteRec() throws Exception {

    }
    
    /**
     * Gets all the recommendations a user has from friends
     */
    @Test
    public void testGetRecs() throws Exception {

    }
    
    //-------------------BAD REQUETS-------------------
    
    /**
     * Sends a bad user ID
     */
    @Test
    public void testRecommendMovieBad() throws Exception {

    }
    
    /**
     * Sends a bad movie Id
     */
    @Test
    public void testRecommendMovieBad2() throws Exception {

    }
    
    /**
     * Sends a bad friend Id
     */
    @Test
    public void testRecommendMovieBad3() throws Exception {

    }
    
    /**
     * Sends a bad user Id
     */
    @Test
    public void testDeleteRecBad() throws Exception {

    }
    
    /**
     * Sends a bad movieId
     */
    @Test
    public void testDeleteRecBad2() throws Exception {

    }
    
    
    /**
     * Sends a bad User Id
     */
    @Test
    public void testGetRecsBad() throws Exception {

    }
    
    
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
    
}
