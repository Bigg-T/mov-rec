package edu.northeastern.cs4500.DB.movie;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by t on 4/8/18.
 */
public class UserRatesObjectTest {

  UserRatesObject ratesObject1;
  UserRatesObject ratesObject2;
  UserRatesObject ratesObject3;
//  Random r = new Random();
//  int roMovieId1 = r.nextInt(10000) + 5235424;
//  int roMovieId2 = r.nextInt(10000) + 5235424;
  @Before
  public void setUp() throws Exception {
    ratesObject1 = new UserRatesObject(10,5,4.0);
    ratesObject2 = new UserRatesObject(10, 5, 4.0, true);
    ratesObject3 = new UserRatesObject(10, 5, 4.0, false);
  }

  @Test
  public void getId() throws Exception {
    ratesObject1.setId(100);
    Assert.assertEquals(100, ratesObject1.getId());
  }

  @Test
  public void setId() throws Exception {
    ratesObject2.setId(100);
    Assert.assertEquals(100, ratesObject2.getId());
  }

  @Test
  public void getMovie_id() throws Exception {
    Assert.assertEquals(10, ratesObject1.getMovie_id());
  }

  @Test
  public void setMovie_id() throws Exception {
    ratesObject1.setMovie_id(0);
    Assert.assertEquals(0, ratesObject1.getMovie_id());
  }

  @Test
  public void getUser_id() throws Exception {
    Assert.assertEquals(5, ratesObject1.getUser_id());
  }

  @Test
  public void setUser_id() throws Exception {
    ratesObject1.setUser_id(50);
    Assert.assertEquals(50,ratesObject1.getUser_id());
  }

  @Test
  public void getRate() throws Exception {
    Assert.assertEquals(4.0,ratesObject1.getRate(),.01);
  }

  @Test
  public void setRate() throws Exception {
    ratesObject1.setRate(6.5);
    Assert.assertEquals(6.5, ratesObject1.getRate(), .01);
  }

  @Test
  public void setIs_watched() throws Exception {
    ratesObject1.setIs_watched(false);
    Assert.assertEquals(false, ratesObject1.getIs_watched());
  }

  @Test
  public void setIs_watched2() throws Exception {
    ratesObject2.setIs_watched(false);
    Assert.assertEquals(false, ratesObject2.getIs_watched());
  }

  @Test
  public void getIs_watched() throws Exception {
    Assert.assertEquals(false, ratesObject3.getIs_watched());
  }
  @Test
  public void getIs_watched2() throws Exception {
    Assert.assertEquals(true, ratesObject2.getIs_watched());
  }

}