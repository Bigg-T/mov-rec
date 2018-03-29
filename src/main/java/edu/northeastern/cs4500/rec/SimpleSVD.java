package edu.northeastern.cs4500.rec;

import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

/**
 * Created by t on 3/26/18.
 * Class INVARIANT: The order of the matrix of user and items will remain the same. Only the rating,
 *                  will get mutated with predicted rating.
 * The predict based on the given matrix and might over/under predict.
 */
public class SimpleSVD implements ICFAlgo {
  private Array2DRowRealMatrix userItem;
  private double modePercent = .75;

  /**
   * INVARIANT: The order of the matrix of user and items will remain the same. Only the rating,
   *             will get mutated with predicted rating.
   * @param userItem the matrix user and item. The rating of each user rated on the items.
   * @param modePercent mode (recommended to  be around .6t for testing or mostly 0 in the matrix
   */
  public SimpleSVD(double[][] userItem, double modePercent) {
    Objects.requireNonNull(userItem);
    if (modePercent > 1.000 || modePercent < 0.000) {
      throw new IllegalArgumentException("Percentage must be (0.000,1.000)");
    }
    if (userItem.length < 1) {
      throw new IllegalArgumentException("Must contain rating");
    }
    this.userItem = new Array2DRowRealMatrix(userItem);
    this.modePercent = modePercent;
  }

  @Override
  public double[][] compute() {
    return this.compute(this.modePercent);
  }

  /**
   * The prediction give some value.
   *
   * @param modePercent the mode
   * @return the predict given some Y (users) predict on X (items)
   */
  private double[][] compute(double modePercent) {
    if (this.userItem.getColumnDimension() <= 1 && this.userItem.getRowDimension() <= 1) {
      return this.userItem.getData();
    }
    SingularValueDecomposition svd = new SingularValueDecomposition(this.userItem);
    double[] diagValues = svd.getSingularValues();
    int numMode = this.determineMode(diagValues, modePercent);
    int numUser = this.userItem.getRowDimension() - 1; // index by 0
    int numItem = this.userItem.getColumnDimension() - 1; // index by 0

    RealMatrix right = svd.getU().getSubMatrix(0,numUser,0,numMode); // right or U
    // start row, end row, start col, end col
    RealMatrix diag = svd.getS().getSubMatrix(0,numMode,0,numMode); //diagonal matrix
    RealMatrix left = svd.getV().transpose().getSubMatrix(0,numMode, 0, numItem); // left
    return right.multiply(diag).multiply(left).getData();
  }

  /**
   * The number of column to use to perform prediction.
   * @param diagValues the diagonal values of svd
   * @param mp the mode percentage
   * @return the number of columns to use
   */
  private int determineMode(double[] diagValues, double mp) {
    double sum = Arrays.stream(diagValues).sum();
    double runningSum = 0.0;
    int mode;
    for (mode = 0; mode < diagValues.length; mode++) {
      runningSum += diagValues[mode];
      int isRunSumGreater = Double.compare(runningSum/sum, mp); //1 mean grreater, 0 mean ==
      if (isRunSumGreater >= 0) {
        break; // could of return, but final return would never reach
      }
    }
    return mode;
  }

}
