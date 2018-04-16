package edu.northeastern.cs4500.movieFriendRec;
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

import edu.northeastern.cs4500.Cs4500Spring2018NguyenApplication;
import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.JPARepositories.MovieRatingRepository;
import edu.northeastern.cs4500.JPARepositories.UserRepository;
import edu.northeastern.cs4500.user.friendRec.MovieFriendRecObject;
import edu.northeastern.cs4500.user.friendRec.MovieFriendRecRepository;
import edu.northeastern.cs4500.DB.user.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieFriendRecObjectTest {
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	Integer movie_id;
	Integer friend_id;
	Integer user_id;
	Integer rec_id;
	MovieFriendRecObject movieRec;
	
	@Autowired
	MovieFriendRecRepository MFRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	MovieRatingRepository movieRepo;
	
	@Before
	public void setUp() {
		user_id = 3;
		friend_id = 2;
		movie_id = 1;
		
		movieRec = new MovieFriendRecObject(user_id, friend_id, movie_id);
		MFRepo.save(movieRec);
		rec_id = movieRec.getId();

		
	}
	
	@After
	public void destroy() {
		MFRepo.delete(movieRec);
	}

	@Test
	public void testGetId() throws Exception{
		Assert.assertEquals((Integer)movieRec.getId(), (Integer)rec_id);
		}
	
	@Test
	public void testGetMovie_id() throws Exception{
		Assert.assertEquals((Integer)movieRec.getMovie_id(), movie_id);
	}

	@Test
	public void testSetMovie_id() throws Exception {
		Assert.assertEquals((Integer)movieRec.getMovie_id(),movie_id);
		movieRec.setMovie_id(4);
		Assert.assertEquals((Integer)movieRec.getMovie_id(),(Integer)4);
	}

	@Test
	public void testGetUser_id() throws Exception{
		Assert.assertEquals((Integer)movieRec.getUser_id(),user_id);
	}

	@Test
	public void testSetUser_id() throws Exception{
		Assert.assertEquals((Integer)movieRec.getUser_id(),user_id);
		movieRec.setUser_id(5);
		Assert.assertEquals((Integer)movieRec.getUser_id(),(Integer)5);
	}

	@Test
	public void getFriend_id() throws Exception{
		Assert.assertEquals((Integer)movieRec.getFriend_id(),friend_id);
	}
	@Test
	public void setFriend_id() throws Exception{
		Assert.assertEquals((Integer)movieRec.getFriend_id(),friend_id);
		movieRec.setFriend_id(7);
		Assert.assertEquals((Integer)movieRec.getFriend_id(),(Integer)7);
	}
}



