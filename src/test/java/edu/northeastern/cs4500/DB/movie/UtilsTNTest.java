package edu.northeastern.cs4500.DB.movie;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by t on 4/8/18.
 */
public class UtilsTNTest {

  double[][] rateEx = {{1.0,4.5}, {2,5}};

  double[][] rateEx2 = {{1.0,6.5, 3}, {1.5,5,4}, {0, 9, 2}};
  @Test //basic test
  public void getMinMax() throws Exception {
    double[] expect = {1.0,5};
    Assert.assertArrayEquals(expect, UtilsTN.getMinMax(rateEx), .01);
  }

  @Test //basic test
  public void getMinMax2() throws Exception {
    double[] expect = {0,9};
    Assert.assertArrayEquals(expect, UtilsTN.getMinMax(rateEx2), .01);
  }

  @Test(expected = Exception.class)
  public void getMinMaxErr1() throws Exception {
    UtilsTN.getMinMax(null);
  }

}