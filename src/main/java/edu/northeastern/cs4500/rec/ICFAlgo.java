package edu.northeastern.cs4500.rec;

/**
 * Created by t on 3/26/18.
 * Interface for doing prediction.
 */
public interface ICFAlgo {

  /**
   * The rows represent the users and columns represent the rating of each time.
   * INVARIANT: The order of the matrix of user and items will remain. Only the rating,
   *             will get mutated with predicted rating.
   *
   * @return The estimated prediction rating.
   */
  public double[][] compute();

}
