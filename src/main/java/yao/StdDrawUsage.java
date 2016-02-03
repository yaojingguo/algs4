package yao;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class StdDrawUsage {

  static void one() {
    int N = 100;
    StdDraw.setXscale(0, N);
    StdDraw.setYscale(0, N*N);
    for (int i = 1; i <= N; i++) {
      StdDraw.point(i, i);
      StdDraw.point(i, i * i);
      StdDraw.point(i, i * Math.log(i));
    }
  }

  static void two() {
    int N = 50;
    double[] a = new double[N];
    for (int i = 0; i < N; i++)
      a[i] = StdRandom.random();
    Arrays.sort(a);
    for (int i = 0; i < N; i++) {
      double x = 1.0*i/N + 0.5/N;
      double y = a[i]/2.0;
      double rw = 0.5/N;
      double rh = a[i]/2.0;
      StdDraw.filledRectangle(x, y, rw, rh);
    }
  }

  static void three() {
  }

  public static void main(String[] args) {
    // one();  
    two();
  }
}
