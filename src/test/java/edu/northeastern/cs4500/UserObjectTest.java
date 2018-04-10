package edu.northeastern.cs4500;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import edu.northeastern.cs4500.JPARepositories.UserRepository;
import edu.northeastern.cs4500.DB.user.UserService;

/**
 * Tests for the User's Controller and any helper methods
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserObjectTest {

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
		String first_name = "test-first";
	    String last_name = "test-last";
	    String email = "test-email-3";
	    String password = "test-password";
	    String username = "test-username-3";
		test_user_1 = new UserObject(first_name, last_name, email, password, username);
		test_user_2 = new UserObject(first_name + "_2", last_name, email + "_2", password, username);
		userRepo.save(test_user_1);
	}
	
	@After
	public void destroy() {
		test_user_1.setFriends(null);
		userRepo.delete(test_user_1);
	}

	@Test
	public void testIsAllow_location() throws Exception{
		Assert.assertEquals(test_user_1.isAllow_location(), false);
        test_user_1.setAllow_location(true);
		Assert.assertEquals(test_user_1.isAllow_location(), true);
	}
	
	@Test
	public void tesGetAbout_me() throws Exception{
		Assert.assertEquals(test_user_1.getAbout_me(), null);
        test_user_1.setAbout_me("about me");
		Assert.assertEquals(test_user_1.getAbout_me(), "about me");
	}
	
	@Test
	public void testGetProf_Pic() throws Exception {
		Assert.assertEquals(test_user_1.getProf_pic(), null);
        test_user_1.setProf_pic("Prof Pic");
		Assert.assertEquals(test_user_1.getProf_pic(), "Prof Pic");
	}
	
	@Test
	public void testIs_admin() throws Exception{
		Assert.assertEquals(test_user_1.isIs_admin(), false);
        test_user_1.setIs_admin(true);
		Assert.assertEquals(test_user_1.isIs_admin(), true);
	}
	
	@Test
	public void TestIs_active() throws Exception{
		Assert.assertEquals(test_user_1.isIs_active(), false);
        test_user_1.setIs_active(true);
		Assert.assertEquals(test_user_1.isIs_active(), true);
	}
	
	@Test
	public void TestGetGender() throws Exception{
		Assert.assertEquals(test_user_1.getGender(), null);
        test_user_1.setGender("M");;
		Assert.assertEquals(test_user_1.getGender(), "M");
	}

	@Test
	public void testGetDob() throws Exception{
		Assert.assertEquals(test_user_1.getDob(), null);
		Date test = new Date();
        test_user_1.setDob(test);
       	Assert.assertEquals(test_user_1.getDob(), test);
	}
	
	@Test
	public void testGetState() throws Exception {
		Assert.assertEquals(test_user_1.getState(), null);
		test_user_1.setState("MA");
		Assert.assertEquals(test_user_1.getState(), "MA");
	}
	
	@Test
	public void TestGetCountry() throws Exception {
		Assert.assertEquals(test_user_1.getCountry(), null);
		test_user_1.setCountry("USA");
		Assert.assertEquals(test_user_1.getCountry(), "USA");
	}
	
	
	@Test
	public void TestGetUsername() throws Exception {
		Assert.assertEquals(test_user_1.getUsername(), "test-username-3");
		test_user_1.setUsername("blahblahblah");
		Assert.assertEquals(test_user_1.getUsername(), "blahblahblah");
	}

	
	@Test
	public void testGetPassword() {
		Assert.assertEquals(test_user_1.getPassword(), "test-password");
		test_user_1.setPassword("shouldProbablyHash");
		Assert.assertEquals(test_user_1.getPassword(), "shouldProbablyHash");
	}

	@Test
	public void testGetEmail() {
		Assert.assertEquals(test_user_1.getEmail(), "test-email-3");
		test_user_1.setEmail("hello@jeanapul.com");
		Assert.assertEquals(test_user_1.getEmail(), "hello@jeanapul.com");
	}

	@Test
	public void testGetLast_name() {
		Assert.assertEquals(test_user_1.getLast_name(), "test-last");
		test_user_1.setLast_name("another fake name lmao");
		Assert.assertEquals(test_user_1.getLast_name(), "another fake name lmao");
	}

	@Test
	public void testGetFirstName() {
		Assert.assertEquals(test_user_1.getFirst_name(), "test-first");
		test_user_1.setFirst_name("another fake name lmao");
		Assert.assertEquals(test_user_1.getFirst_name(), "another fake name lmao");	
	}

    @Test
	public void testGetFriends() {
		Assert.assertEquals(test_user_1.getFriends(), null);
		Collection<UserObject> friends = new ArrayList<UserObject>();
		friends.add(test_user_2);
		test_user_1.setFriends(friends);
		Assert.assertEquals(test_user_1.getFriends().size(), 1);
	}

    @Test
    public void testIsLogged() {
		Assert.assertEquals(test_user_1.isLogged(), false);
		test_user_1.setLogged(true);
		Assert.assertEquals(test_user_1.isLogged(), true);		
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
