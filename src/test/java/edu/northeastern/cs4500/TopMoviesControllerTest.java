


package edu.northeastern.cs4500;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



import java.util.ArrayList;

import java.util.HashMap;

import java.util.LinkedHashMap;

import java.util.List;

import java.util.Map;



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

import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.test.context.junit4.SpringRunner;



import edu.northeastern.cs4500.DB.movie.TopMoviesObject;

import edu.northeastern.cs4500.DB.movie.UserRatesObject;

import edu.northeastern.cs4500.DB.user.UserObject;

import edu.northeastern.cs4500.JPARepositories.TopMoviesRepository;

import edu.northeastern.cs4500.JPARepositories.UserRatesRepository;

import edu.northeastern.cs4500.JPARepositories.UserRepository;



/**

 * Tests for the Top Movie's Controller 

 */



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopMoviesControllerTest {



@LocalServerPort

  private int port;



  TestRestTemplate restTemplate = new TestRestTemplate();



  HttpHeaders headers = new HttpHeaders();



  @Autowired

  TopMoviesRepository topMoviesRepository;

  

  @Autowired

  UserRepository userRepository;

  

  

  @Test
  public void testAddTopMovie() throws Exception {

    String first_name = "use64r13214";

    String last_name = "use456r13214";

    String email = "user1456432121451";

    String password = "us456er13214";

    String username = "us456er132121451";



    UserObject user = new UserObject(first_name, last_name, email, password, username);

    userRepository.save(user);

    user.setLogged(true);



    //we have some random movie_id

    Integer movie_id = 3;

    Integer rank = 3;

    Integer user_id = user.getId();

    String description= "asdf";

    

    TopMoviesObject uro = new TopMoviesObject(movie_id, user_id);

    uro.setRank(rank);



    

    HttpEntity<String> entity = new HttpEntity<String>(null, headers);


    HttpEntity<HashMap> response = restTemplate.exchange(

            createURLWithPort("/api/movie/addTopMovie/?movie_id=" + movie_id + "&user_id=" + user_id + "&rank=" +

            rank + "&description=" + description),

            HttpMethod.GET, entity, HashMap.class);

   
    @SuppressWarnings("unchecked")
    HashMap<String, Object> body = response.getBody();
    System.out.println("IS SUCCESS");
     boolean statusOK = (boolean)body.get("isSuccess");
     System.out.println(statusOK);
     System.out.println("BODY");
     System.out.println(body);
     Assert.assertEquals(true, statusOK);
   userRepository.delete(user);

  }
  



  @Test 
  public void testAddTopMoviesNullValues() throws Exception {

 

  HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    

HttpEntity<HashMap> response = restTemplate.exchange(

           createURLWithPort("/api/movie/addTopMovie/?movie_id=" + "&user_id=" + "&rank=" + "&description="),

            HttpMethod.GET, entity, HashMap.class);


   @SuppressWarnings("unchecked")

   HashMap<String, Object> body = response.getBody();

   String badreq = (String) body.get("status");

   boolean isSuccess = (boolean) body.get("isSuccess");

   System.out.println(badreq);



   Assert.assertEquals(isSuccess, false);

  }

  

  @Test
  public void testAddTopMoviesUserDoesntExist() throws Exception {

    

    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    

    HttpEntity<HashMap> response = restTemplate.exchange(

            createURLWithPort("/api/movie/addTopMovie/?movie_id=3" + "&user_id=-11" + "&rank=3" + "&description=asdf"),

            HttpMethod.GET, entity, HashMap.class);

   

    @SuppressWarnings("unchecked")

HashMap<String, Object> body = response.getBody();

    String badreq = (String) body.get("message");

    boolean isSuccess = (boolean) body.get("isSuccess");

    System.out.println(badreq);

    Assert.assertEquals(isSuccess, false);

  }

  @Test
  public void testAddTopMoviesUserNotLoggedIn() throws Exception {

    String first_name = "user1";

    String last_name = "user1";

    String email = "user1";

    String password = "user1";

    String username = "user1";



    UserObject user = new UserObject(first_name, last_name, email, password, username);

    user.setLogged(false);



    //we have some random movie_id

    Integer movie_id = 3;

    Integer rank = 3;

    Integer user_id = user.getId();

    String description= "asdf";

    

    TopMoviesObject uro = new TopMoviesObject(movie_id, user_id);

    topMoviesRepository.save(uro);

    

    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    

    HttpEntity<HashMap> response = restTemplate.exchange(

            createURLWithPort("/api/movie/addTopMovie/?movie_id=" + movie_id + "&user_id=" + user_id + "&rank=" + rank + "&description=" + description),

            HttpMethod.GET, entity, HashMap.class);

    

    @SuppressWarnings("unchecked")

HashMap<String, Object> body = response.getBody();

    String badreq = (String) body.get("message");

    boolean isSuccess = (boolean) body.get("isSuccess");

    System.out.println(badreq);

    Assert.assertEquals(isSuccess, false);

    topMoviesRepository.delete(uro);

  }

 

  

  @Test
  public void testGetTopMovies() throws Exception {


  String first_name = "usasdfer1";

    String last_name = "uasdfser1";

    String email = "amritavadhera";

    String password = "usaasdfsdfkjher11";

    String username = "amritavadhera";



    UserObject user = new UserObject(first_name, last_name, email, password, username);

    user.setLogged(true);

    userRepository.save(user);



    //we have some random movie_id

    Integer movie_id = 389;

    Integer rank = 3;

    Integer user_id = user.getId();

    String description= "asdf";

   

    TopMoviesObject uro = new TopMoviesObject(movie_id, user_id);

    uro.setRank(rank);

    topMoviesRepository.save(uro);

   

    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

   



    HttpEntity<HashMap> responseTopMovies = restTemplate.exchange(

            createURLWithPort("/api/movie/getTopMovies/?user_id=" + user_id + "&limit=" + 3),

            HttpMethod.GET, entity, HashMap.class);

   

    @SuppressWarnings("unchecked")

HashMap<String, Object> responseBody = responseTopMovies.getBody();

    String statusOK = (String) responseBody.get("status");

    Assert.assertEquals(true, statusOK.equals("OK"));

    boolean isSuccess = (boolean) responseBody.get("isSuccess");



    @SuppressWarnings("unchecked")

    List<Object> topMovies =(ArrayList<Object>) responseBody.get("movies");

    Map<Object, Object> mov = (LinkedHashMap<Object, Object>) topMovies.get(0);

    int movId = (Integer) mov.get("movie_id");

    int rank1 = (Integer) mov.get("rank");

   

    Assert.assertEquals(3, rank1);

    Assert.assertEquals(389, movId);

 

    topMoviesRepository.delete(uro);

    userRepository.delete(user);

  }
  

  @Test 
  public void testGetTopMoviesNullValues() throws Exception {

 

  HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    

HttpEntity<HashMap> response = restTemplate.exchange(

           createURLWithPort("/api/movie/getTopMovies/?user_id=" + "&limit="),

            HttpMethod.GET, entity, HashMap.class);


   @SuppressWarnings("unchecked")

   HashMap<String, Object> body = response.getBody();

   String badreq = (String) body.get("status");

   boolean isSuccess = (boolean) body.get("isSuccess");

   System.out.println(badreq);



   Assert.assertEquals(isSuccess, false);

  }

  


private String createURLWithPort(String uri) {

    return "http://localhost:" + port + uri;

  }



}