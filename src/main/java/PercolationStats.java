import edu.princeton.cs.algs4.*;
import java.util.*;

public class PercolationStats {
  private double _mean;
  private double _stddev;
  private double _confidenceLo;
  private double _confidenceHi;

  // perform T independent experiments on an N-by-N grid
  public PercolationStats(int N, int T) {
    if (N <= 0)
      throw new IllegalArgumentException("Invalid value for N. N>0 must be true.");
    if (T <= 0)
      throw new IllegalArgumentException("Invalid value for T. T>0 must be true.");
  
    int matrixSize = N * N;
    double[] xfs = new double[T];
  
    for (int t = 0; t < T; t++) {
      Percolation p = new Percolation(N);
      int openCnt = 0;
      while (!p.percolates()) {
        int i, j;
        do {
          i = StdRandom.uniform(1, N+1);
          j = StdRandom.uniform(1, N+1);
        } while (p.isOpen(i, j));
        openCnt++;
        p.open(i, j);
      }
      xfs[t] = ((double) openCnt) / ((double)matrixSize);
    }
    
    this._mean = StdStats.mean(xfs);
    this._stddev = StdStats.stddev(xfs);
    double half = 1.96 * _stddev / Math.sqrt(T);
    this._confidenceLo = _mean - half;
    this._confidenceHi = _mean + half;
  }

  // sample mean of percolation threshold
  public double mean() {
    return _mean;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return _stddev;
  }

  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return _confidenceLo;
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return _confidenceHi;
  }

  private void info(double[] xfs) {
    System.out.printf("xfs:\n");
    for (double xf: xfs) {
      System.out.printf("(%f) ", xf);
    }
    System.out.println();
  }

  // test client (described below)
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(N, T);
    System.out.printf("mean                    = %.16f\n", ps.mean());
    System.out.printf("stddev                  = %.17f\n", ps.stddev());
    System.out.printf("95%% confidence interval = %.16f, %.16f\n", ps.confidenceLo(), ps.confidenceHi());
  }
}
