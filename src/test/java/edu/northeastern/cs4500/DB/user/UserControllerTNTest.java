package edu.northeastern.cs4500.DB.user;

import static org.junit.Assert.*;

import edu.northeastern.cs4500.Cs4500Spring2018NguyenApplication;
import edu.northeastern.cs4500.JPARepositories.UserRepository;
import java.util.HashMap;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by t on 4/12/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTNTest {

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
  UserObject user;

  @Before
  public void setUp() throws Exception {

    String rand = RandomStringUtils.randomAlphanumeric(20);
    first_name = "user1" + rand;
    last_name = "user1" + rand;
    email = "user1" + rand;
    password = "user1" + rand;
    username = "user1" + rand;
    user = new UserObject(first_name, last_name, email, password, username);
    userRepo.save(user);
  }

  @After
  public void tearDown() throws Exception {
    userRepo.delete(user);
  }



  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  @Test // not an admin
  public void testNotAdmin() throws Exception {
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    HttpEntity<HashMap> response = restTemplate.exchange(
        createURLWithPort(
            "/api/user/admin/?id="+user.getId()),
        HttpMethod.GET, entity, HashMap.class);
    @SuppressWarnings("unchecked")
    HashMap<String, Object> body = response.getBody();
    String isAdmin = body.get("isAdmin").toString();
    Assert.assertEquals("false", isAdmin);
  }
  @Test
  public void isAdminUser1() throws Exception {
    user.setIs_admin(true);
    userRepo.save(user);
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    HttpEntity<HashMap> response = restTemplate.exchange(
        createURLWithPort(
            "/api/user/admin/?id="+user.getId()),
        HttpMethod.GET, entity, HashMap.class);
    @SuppressWarnings("unchecked")
    HashMap<String, Object> body = response.getBody();
    String isAdmin = body.get("isAdmin").toString();
    Assert.assertEquals("true", isAdmin);
  }


}