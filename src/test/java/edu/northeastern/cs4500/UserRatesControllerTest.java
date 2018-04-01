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
import edu.northeastern.cs4500.DB.movie.UserRatesRepository;
import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.DB.user.UserRepository;

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

  UserRepository userRepository; 
  
  
  
  @Test
  public void testAddUserRates() throws Exception {
	
	List<UserRatesObject> intiallist = userRatesRepository.findAll();
    Integer initialSize = intiallist.size();
    
    String first_name = "user1";
    String last_name = "user1";
    String email = "user1";
    String password = "user1";
    String username = "user1";

    UserObject user = new UserObject(first_name, last_name, email, password, username);

    userRepository.save(user);

    //we have some random movie_id
    Integer movie_id = 3;
    Integer rate = 5;
    Integer user_id = user.getId();
    
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    HttpEntity<HashMap> response = restTemplate.exchange(
            createURLWithPort("/api/movie/addUserRates/?movie_id=" + movie_id + "&user_id=" + user.getId() + "&rate=" + rate),
            HttpMethod.GET, entity, HashMap.class);
    HashMap<String, Object> body = response.getBody();
    boolean statusOK = (boolean) body.get("True");
    Assert.assertEquals((boolean)body.get("True"), HttpStatus.OK);

    userRepository.delete(user);
  }

  @Test
  public void testGetUserRates() throws Exception {

    List<UserRatesObject> responseList = new ArrayList<UserRatesObject>();

    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<HashMap> response = restTemplate.exchange(
            createURLWithPort("/api/movie/getUserRates/"),
            HttpMethod.GET, entity, HashMap.class);
    HashMap<String, Object> context = response.getBody();
    
    @SuppressWarnings("unchecked")
    List<UserRatesObject> userRatesRepository = (List<UserRatesObject>) context.get("UserRates");
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
