package edu.northeastern.cs4500;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
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
	String first_name;
	String last_name;
	String email;
	String password;
	String username;
	UserObject user;

	//we have some random movie_id
	Integer movie_id;
	Integer rate;
	Integer user_id;
	@Autowired
	UserRepository userRepo;
	
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
	    String t_first_name = first_name + RandomStringUtils.randomAlphanumeric(5);
	    String t_last_name = last_name + RandomStringUtils.randomAlphanumeric(5);
		String t_email = email + RandomStringUtils.randomAlphanumeric(5);
		String t_password = password + RandomStringUtils.randomAlphanumeric(5);
		String t_username = username + RandomStringUtils.randomAlphanumeric(5);
	    test_user_1 = new UserObject(first_name, last_name, email, password, username);
		test_user_2 = new UserObject(t_first_name, t_last_name, t_email, t_password, t_username);
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
		Assert.assertEquals(test_user_1.getUsername(), username);
		test_user_1.setUsername("blahblahblah");
		Assert.assertEquals(test_user_1.getUsername(), "blahblahblah");
	}

	@Test
	public void testGetPassword() {
		Assert.assertEquals(test_user_1.getPassword(), password);
		test_user_1.setPassword("shouldProbablyHash");
		Assert.assertEquals(test_user_1.getPassword(), "shouldProbablyHash");
	}

	@Test
	public void testGetEmail() {
		Assert.assertEquals(test_user_1.getEmail(), email);
		test_user_1.setEmail("hello@jeanapul.com");
		Assert.assertEquals(test_user_1.getEmail(), "hello@jeanapul.com");
	}

	@Test
	public void testGetLast_name() {
		Assert.assertEquals(test_user_1.getLast_name(), last_name);
		test_user_1.setLast_name("another fake name lmao");
		Assert.assertEquals(test_user_1.getLast_name(), "another fake name lmao");
	}

	@Test
	public void testGetFirstName() {
		Assert.assertEquals(test_user_1.getFirst_name(), first_name);
		test_user_1.setFirst_name("another fake name lmao");
		Assert.assertEquals(test_user_1.getFirst_name(), "another fake name lmao");	
	}

    @Test
	public void testGetFriends() {
		Assert.assertEquals(test_user_1.getFriends().size(), 0);
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
}
