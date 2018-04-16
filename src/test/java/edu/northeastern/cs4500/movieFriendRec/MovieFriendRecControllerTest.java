package edu.northeastern.cs4500.movieFriendRec;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import edu.northeastern.cs4500.Cs4500Spring2018NguyenApplication;
import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.DB.user.UserProfile;
import edu.northeastern.cs4500.DB.user.UserService;
import edu.northeastern.cs4500.JPARepositories.MovieRatingRepository;
import edu.northeastern.cs4500.JPARepositories.UserRepository;
import edu.northeastern.cs4500.models.MovieRatingsObject;
import edu.northeastern.cs4500.user.friendRec.MovieFriendRecObject;
import edu.northeastern.cs4500.user.friendRec.MovieFriendRecRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

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
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)public class MovieFriendRecControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Autowired
	UserRepository userRepo;
	@Autowired
	MovieRatingRepository movieRepo;
	@Autowired
	MovieFriendRecRepository MFCRepo;
	
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
	UserObject test_user_3;

	MovieRatingsObject movie;
	
	MovieFriendRecObject rec;

	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	private void createUsers() {
		String rand = RandomStringUtils.randomAlphanumeric(20);
		first_name = "test-first" + rand;
		last_name = "test-last" + rand;
		email = "test-email-3" + rand;
		password = "test-password" + rand;
		username = "test-username-3" + rand;
		test_user_1 = new UserObject(first_name, last_name, email, password, username);
		test_user_2 = new UserObject(first_name + "_2", last_name + "_2", email + "_2", password + "_2", username +"_2");
		test_user_3 = new UserObject(first_name + "_3", last_name + "_3", email + "_3", password + "_3", username +"_3");
		userRepo.save(test_user_1);
		userRepo.save(test_user_2);
		userRepo.save(test_user_3);
		test_user_1.getFriends().add(test_user_2);
		test_user_1.getFriends().add(test_user_3);
		userRepo.save(test_user_1);
	
	}
	
	private void deleteUsers() {
    	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/remove_friend/?userId=" + test_user_1.getId() + "&" + "friendId=" + test_user_2.getId()),
				HttpMethod.GET, entity, HashMap.class);
		try {
			userRepo.delete(test_user_1);
		} catch (Exception e) {
		}

    	HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response2 = restTemplate.exchange(
				createURLWithPort("/api/user/remove_friend/?userId=" + test_user_2.getId() + "&" + "friendId=" + test_user_1.getId()),
				HttpMethod.GET, entity2, HashMap.class);
		try {
			userRepo.delete(test_user_2);
		} catch(Exception e) {
		}
	}
	
	private void createMovie() {
		String movieTitle = "movie";
		String movieGenre = "genre";
		
		movie = new MovieRatingsObject(movieTitle, movieGenre, 1);
		movieRepo.save(movie);
	}
	
	private void deleteMovie() {
		//movieRepo.delete(movie.getmovieId());
		movieRepo.delete(movie);
	}
	
	@Before
	public void setUp() {
		createUsers();
		createMovie();
		rec = new MovieFriendRecObject(test_user_1.getId(), test_user_2.getId(), movie.getTmdbId());
		MFCRepo.save(rec);
	}

	@After
	public void un_setUp() {
		deleteUsers();
		deleteMovie();
	}
	

  /**
  * Friend Recommends Movie To Another Friend
  */
	@Test
	public void testRecommendMovie() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
		createURLWithPort("/api/user/recommend/?userId=" + test_user_1.getId() + "&friendId=" + test_user_2.getId() + "&movieId=" + movie.getTmdbId()),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), true);
		Integer recId = (Integer) body.get("recId");
		MFCRepo.delete(recId);
	}
	
	/**
	* User Deletes Friend Recommended movie
	*/
	@Test
	public void testDeleteRec() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer userId = rec.getUser_id();
		Integer movieId = rec.getMovie_id();
		ResponseEntity<HashMap> response = restTemplate.exchange(
		createURLWithPort("/api/user/recommend/delete/?userId=" + userId + "&movieId=" + movieId),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		System.out.println("------------HEAFSDFSDSDFSDFSDF----------");
		System.out.println(body);
		Assert.assertEquals(body.get("isSuccess"), true);		
	}
// 
	/**
	 * Gets all the recommendations a user has from friends
	 */
	@Test
	public void testGetRecs() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer userId = rec.getUser_id();
		ResponseEntity<HashMap> response = restTemplate.exchange(
		createURLWithPort("/api/user/recommend/getAll/?userId=" + userId),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), true);			
	}
//	
	/**
	 * Gets all the recommendations a user has from friends
	 */
	@Test
	public void testAllFriendsToRec() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer userId = rec.getUser_id();
		Integer movieId = rec.getMovie_id();
		ResponseEntity<HashMap> response = restTemplate.exchange(
		createURLWithPort("/api/user/recommend/get_friends/?userId=" + userId + "&movieId=" + movieId),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), true);	
		List<HashMap<String, Object>> friends = (List<HashMap<String, Object>>)body.get("friends");
		Assert.assertEquals(friends.size(), 1);
	}
	
	/**
	 * Gets all the recommendations a user has from friends
	 */
	@Test
	public void testAllFriendsToRec2() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer userId = rec.getUser_id();
		Integer movieId = rec.getMovie_id();
		ResponseEntity<HashMap> response = restTemplate.exchange(
		createURLWithPort("/api/user/recommend/get_friends/?userId=" + userId + "&movieId=" + movieId),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), true);			
	}
//	
//
//////-------------------BAD REQUETS-------------------
////	
	/**
	 * Sends a bad user ID
	 */
	@Test
	public void testRecommendMovieBad() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/recommend/?userId=" + 0 + "&friendId=" + test_user_2.getId() + "&movieId=" + movie.getmovieId()),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), false);
	}
	
	/**
	 * Sends a bad movie Id
	 */
	@Test
	public void testRecommendMovieBad2() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/recommend/?userId=" + test_user_1.getId() + "&friendId=" + 0 + "&movieId=" + movie.getmovieId()),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), false);
	}
//	
	/**
	 * Sends a bad friend Id
	 */
	@Test
	public void testRecommendMovieBad3() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/recommend/?userId=" + test_user_1.getId() + "&friendId=" + test_user_2.getId() + "&movieId=" + 0),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), false);
	}
//	
	/**
	 * Sends a bad user Id
	 */
	@Test
	public void testDeleteRecBad() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer userId = 0;
		Integer movieId = rec.getMovie_id();
		Integer recId = rec.getId();
		ResponseEntity<HashMap> response = restTemplate.exchange(
		createURLWithPort("/api/user/recommend/delete/?userId=" + userId + "&movieId=" + movieId),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), false);
	}
	
	/**
	 * Sends a bad movieId
	 */
	@Test
	public void testDeleteRecBad2() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer userId = rec.getUser_id();
		Integer movieId = 0;
		Integer recId = rec.getId();
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/user/recommend/delete/?userId=" + userId + "&movieId=" + movieId),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), false);
	}
	
	
	/**
	 * Sends a bad User Id
	 */
	@Test
	public void testGetRecsBad() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer userId = 0;
		ResponseEntity<HashMap> response = restTemplate.exchange(
		createURLWithPort("/api/user/recommend/getAll/?userId=" + userId),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), false);
	}
	
	@Test
	public void testBadAllFriendsToRec() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer userId = 0;
		Integer movieId = rec.getMovie_id();
		ResponseEntity<HashMap> response = restTemplate.exchange(
		createURLWithPort("/api/user/recommend/get_friends/?userId=" + userId + "&movieId=" + movieId),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), false);			
	}
	
	@Test
	public void testBadAllFriendsToRec2() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer userId = rec.getUser_id();
		Integer movieId = 0;
		ResponseEntity<HashMap> response = restTemplate.exchange(
		createURLWithPort("/api/user/recommend/get_friends/?userId=" + userId + "&movieId=" + movieId),
		HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> body = response.getBody();
		Assert.assertEquals(body.get("isSuccess"), false);			
	}
}

