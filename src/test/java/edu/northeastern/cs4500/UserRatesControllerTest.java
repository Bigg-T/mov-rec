package edu.northeastern.cs4500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import edu.northeastern.cs4500.DB.movie.UserRatesObject;
import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.JPARepositories.UserRatesRepository;
import edu.northeastern.cs4500.JPARepositories.UserRepository;

/**
 * Tests for the User's Controller and any helper methods
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRatesControllerTest {

  @LocalServerPort
  private int port;

  TestRestTemplate restTemplate = new TestRestTemplate();

  HttpHeaders headers = new HttpHeaders();

  @Autowired
  UserRatesRepository userRatesRepository;

  @Autowired
  UserRepository userRepository; 
  
  @Test
  public void testAddUserRates() throws Exception {
    String first_name = "user1";
    String last_name = "user1";
    String email = "user1";
    String password = "user1";
    String username = "user1";

    UserObject user = new UserObject(first_name, last_name, email, password, username);
    user.setLogged(true);
    userRepository.save(user);

    //we have some random movie_id
    Integer movie_id = 3;
    Integer rate = 3;
    Integer user_id = user.getId();
    
    UserRatesObject uro = new UserRatesObject(movie_id, user_id);
    uro.setRate(rate);
    
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    
    HttpEntity<HashMap> response = restTemplate.exchange(
            createURLWithPort("/api/movie/addUserRates/?movie_id=" + movie_id + "&user_id=" + user_id + "&rate=" + rate),
            HttpMethod.GET, entity, HashMap.class);
   
    @SuppressWarnings("unchecked")
	HashMap<String, Object> body = response.getBody();
    String statusOK = (String) body.get("status");
    Assert.assertEquals(true, statusOK.equals("OK"));
    uro.setId( (Integer) body.get("userRatingId"));
    userRatesRepository.delete(uro);
    userRepository.delete(user);
  }

  @Test
  public void testAddUserRatesNullInput() throws Exception {
    String first_name = "user1";
    String last_name = "user1";
    String email = "user1";
    String password = "user1";
    String username = "user1";

    UserObject user = new UserObject(first_name, last_name, email, password, username);
    user.setLogged(true);
    userRepository.save(user);

    //we have some random movie_id
    Integer movie_id = null;
    Integer rate = null;
    Integer user_id = null;
    
    UserRatesObject uro = new UserRatesObject();
    uro.setRate(rate);
    
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    
    HttpEntity<HashMap> response = restTemplate.exchange(
            createURLWithPort("/api/movie/addUserRates/?movie_id=" + movie_id + "&user_id=" + user_id + "&rate=" + rate),
            HttpMethod.GET, entity, HashMap.class);
   
    @SuppressWarnings("unchecked")
	HashMap<String, Object> body = response.getBody();
    String message = (String) body.get("message");
    Assert.assertEquals(true, message.equals("Incorrect Input"));
    
    uro.setId( (Integer) body.get("userRatingId"));
    userRatesRepository.delete(uro);
    userRepository.delete(user);
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
