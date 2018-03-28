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
    new SimpleSVD(new double[][]{{1,3}},1.01);
  }

  @Test(expected = IllegalArgumentException.class) // must be a edge
  public void constructor4() throws Exception {
    new SimpleSVD(new double[][]{{1,3}},-0.01);
  }

  @Test(expected = IllegalArgumentException.class) // No element
  public void constructor5() throws Exception {
    new SimpleSVD(new double[][]{},.5);
  }

  @Test(expected = Exception.class) // null
  public void constructor6() throws Exception {
    new SimpleSVD(null,.5);
  }

  @Test(expected = Exception.class) // null
  public void constructor7() throws Exception {
    new SimpleSVD(new double[][]{null,null},.5);
  }

  @Test //edge case for about to construct
  public void constructor8() throws Exception {
    new SimpleSVD(new double[][]{{1}},1.0);
    new SimpleSVD(new double[][]{{1}},0.0);
  }

  @Test
  public void compute1() throws Exception {
    double[][] test3 = {
        {5.0, 6.0, 0.0, 0.0},
        {6.2, 0.0, 0.0, 0.0},
        {0.0, 0.0, 5.0, 6.0},
        {0.0, 0.0, 0.0, 6.0}};//[1,1]
    ICFAlgo a = new SimpleSVD(test3, .65);
    double[][] predict = a.compute();
    assertEquals(2.81,predict[1][1] ,.1);
    assertEquals(2.36,predict[3][2] ,.1);
  }

  @Test // picking all 100% mode doesn't change score
  public void compute2() throws Exception {
    double[][] test3 = {{5.0}};//
    ICFAlgo a = new SimpleSVD(test3, 1.0);
    double[][] predict = a.compute();
    assertEquals(5.0,predict[0][0],.1);
  }

  @Test //same as being small modes
  public void compute3() throws Exception {
    double[][] test3 = {{5.0, 0.0},{0.0, 4.0}};//
    ICFAlgo a = new SimpleSVD(test3, 0.0);
    double[][] predict = a.compute();
    assertEquals(0.0,predict[0][1],.1);
  }


}