package yao;

import java.util.*;

import edu.princeton.cs.algs4.*;

public class TwoSumFast {

  public static int count(int[] a) { 
    // Count pairs that sum to 0.
    Arrays.sort(a);
    int N = a.length;
    int cnt = 0;
    for (int i = 0; i < N; i++)
      if (BinarySearch.rank(-a[i], a) > i)
        cnt++;
    return cnt;
  }

  public static int count2(int[] a) {
    Arrays.sort(a);
    int cnt = 0;
    int l = 0;
    int r = a.length - 1;
    while (l < r) {
      int left = a[l];
      int right = a[r];
      if (left * right >= 0) break;
      left = -left;
      if (left == right) {
        cnt++; 
        l++;
        r--;
      } else if (left < right) {
        r--;
      } else {
        l++;
      }
    }
    return cnt;
  }

  public static void main(String[] args) {
    int[] a = In.readInts(args[0]);
    StdOut.println(count2(a));
  }
}
