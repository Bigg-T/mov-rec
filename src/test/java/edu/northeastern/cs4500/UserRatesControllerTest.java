package edu.northeastern.cs4500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  UserRatesObject uro;

  @Before
  public void setUp() throws Exception {

    String rand = RandomStringUtils.randomAlphanumeric(20);
    first_name = "user1" + rand;
    last_name = "user1" + rand;
    email = "user1" + rand;
    password = "user1" + rand;
    username = "user1" + rand;
    user = new UserObject(first_name, last_name, email, password, username);
    userRepository.save(user);

    //setup movie
    //we have some random movie_id
    movie_id = 3;
    rate = 3;
    user_id = user.getId();
    uro = new UserRatesObject(movie_id, user_id, rate);
    userRatesRepository.save(uro);
  }

  @After
  public void tearDown() throws Exception {
    userRepository.delete(user);
    userRatesRepository.delete(uro);
  }

  @Test //adding user rating
  public void testAddUserRates() throws Exception {
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    HttpEntity<HashMap> response =
        restTemplate
            .exchange(
                createURLWithPort(
                    "/api/movie/addUserRates/?movie_id="
                        + movie_id + "&user_id=" + user_id + "&rate=" + rate),
                HttpMethod.GET, entity, HashMap.class);
    @SuppressWarnings("unchecked")
    HashMap<String, Object> body = response.getBody();
    String statusOK = (String) body.get("status");
    Assert.assertEquals(true, statusOK.equals("OK"));
    uro.setId((Integer) body.get("userRatingId"));
    userRatesRepository.delete(uro);
    userRepository.delete(user);
  }


  @Test // Testing Null
  public void testAddUserRatesNullValues() throws Exception {
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    HttpEntity<HashMap> response = restTemplate
        .exchange(
            createURLWithPort(
                "/api/movie/addUserRates/?movie_id="
                    + "&user_id=" + "&rate="),
            HttpMethod.GET, entity, HashMap.class);

    @SuppressWarnings("unchecked")
    HashMap<String, Object> body = response.getBody();
    String badreq = (String) body.get("status");
    Assert.assertEquals(true, badreq.equals("BAD_REQUEST"));
  }

  @Test // Non existing users
  public void testAddUserRatesUserDoesntExist() throws Exception {
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    HttpEntity<HashMap> response = restTemplate.exchange(
        createURLWithPort(
            "/api/movie/addUserRates/?movie_id=3"
                + "&user_id=-101" + "&rate=3"),
        HttpMethod.GET, entity, HashMap.class);
    @SuppressWarnings("unchecked")
    HashMap<String, Object> body = response.getBody();
    String badreq = (String) body.get("message");
    Assert.assertEquals(true, badreq.equals("User Does Not Exist"));
  }

  @Test
  public void testGetTopMovies() throws Exception {
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    HttpEntity<HashMap> response = restTemplate.exchange(
        createURLWithPort(
            "/api/movie/addUserRates/?movie_id="
                + movie_id + "&user_id=" + user_id + "&rate=" + rate),
        HttpMethod.GET, entity, HashMap.class);

    @SuppressWarnings("unchecked")
    HashMap<String, Object> body = response.getBody();
    String statusOK = (String) body.get("status");
    Assert.assertEquals(true, statusOK.equals("OK"));
    uro.setId((Integer) body.get("userRatingId"));

    HttpEntity<HashMap> responseTopMovies = restTemplate.exchange(
        createURLWithPort("/api/movie/getUserRates/?user_id=" + user_id),
        HttpMethod.GET, entity, HashMap.class);

    @SuppressWarnings("unchecked")
    HashMap<String, Object> responseBody = responseTopMovies.getBody();
    System.out.println("TOPMOVE: " + responseBody.get("status").toString());
    @SuppressWarnings("unchecked")
    Object URO = responseBody.get("topMovies");
    System.out.println("KEY" + responseBody.keySet().toString());
//    System.out.println("TOPMOVE: " +URO.toString());
//    List<Integer> firstObj = URO.get(0);
//    System.out.println(firstObj);
//    Assert.assertEquals(true, firstObj.get(0) == movie_id);
//    Assert.assertEquals(true, firstObj.get(1) == rate);

    userRatesRepository.delete(uro);
    userRepository.delete(user);
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
