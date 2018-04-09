package edu.northeastern.cs4500.DB.movie;

import java.util.Objects;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by t on 4/8/18.
 */
public class UtilsTN {

  public static double[] getMinMax(double[][] m) {
    Objects.requireNonNull(m, "Matrix can't not be null");
    double max = NumberUtils.max(m[0]);
    double min = NumberUtils.min(m[0]);
    int rowLen = m.length;
    for (int i = 1; i < rowLen; i++) {
      double tempMax = NumberUtils.max(m[i]);
      double tempMin = NumberUtils.min(m[i]);
      if (min > tempMin) {
        min = tempMin;
      }
      if (max < tempMax) {
        max = tempMax;
      }
    }
    double[] minMax = {min, max};
    return minMax;
  }
}
