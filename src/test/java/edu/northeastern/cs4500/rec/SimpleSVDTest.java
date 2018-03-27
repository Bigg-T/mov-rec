package edu.northeastern.cs4500.rec;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by t on 3/26/18.
 */
public class SimpleSVDTest {

  @Test(expected = IllegalArgumentException.class)  // must be a probability
  public void constructor() throws Exception {
    new SimpleSVD(new double[][]{},100);
  }

  @Test(expected = IllegalArgumentException.class)  // must be a probability
  public void constructor2() throws Exception {
    new SimpleSVD(new double[][]{{1,3}},-100);
  }

  @Test(expected = IllegalArgumentException.class)  // must be a edge
  public void constructor3() throws Exception {
    new SimpleSVD(new double[][]{{1,3}},1);
  }

  @Test(expected = IllegalArgumentException.class) // must be a edge
  public void constructor4() throws Exception {
    new SimpleSVD(new double[][]{{1,3}},0);
  }

  @Test(expected = IllegalArgumentException.class) // No element
  public void constructor5() throws Exception {
    new SimpleSVD(new double[][]{},.5);
  }

  @Test(expected = IllegalArgumentException.class) // null
  public void constructor6() throws Exception {
    new SimpleSVD(null,.5);
  }

  @Test
  public void compute1() throws Exception {
    double[][] test3 = {
        {5.0, 6.0, 0.0, 0.0},
        {6.2, 0.0, 0.0, 0.0},
        {0.0, 0.0, 5.0, 6.0},
        {0.0, 0.0, 0.0, 6.0}};//[1,1]
    SimpleSVD a = new SimpleSVD(test3, .65);
    double[][] predict = a.compute();
    assertEquals(2.81,predict[1][1] ,.1);
    assertEquals(2.36,predict[3][2] ,.1);
  }

}