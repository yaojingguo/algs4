package yao;

import edu.princeton.cs.algs4.*;
  
public class Percolation {
  private int N;
  private int size;
  // Virtual top
  private int vTop;
  // Virtual bottom
  private int vBottom;
  private WeightedQuickUnionUF uf;
  private int[][] status;

  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {
    if (N <= 0) 
      throw new IllegalArgumentException("Invalid value " + N + " for N. N must be greater than 0.");
    this.status = new int[N][N];
    this.size = N * N + 2;
    uf = new WeightedQuickUnionUF(size);
    this.N = N;
    this.vTop = 0;
    this.vBottom = N * N + 1;
    // Connect virtual top to top row
    for (int i = 1; i < N; i++)
      uf.union(vTop, i);
    // Connect virtual bottom to bottom row
    for (int i = 1; i < N; i++)
      uf.union(vBottom - i, vBottom);
  }   

  // open site (row i, column j) if it is not open already
  public void open(int i, int j) {
    checkIndex(i, j);
    int self = index(i, j);
    int left = index(i, j - 1);
    int above = index(i - 1, i);
    int right = index(i, j + 1);
    int below = index(i + 1, j);
    union(self, left);
    union(self, top);
    union(self, right);
    union(self, below);
  }
  // is site (row i, column j) open?
  public boolean isOpen(int i, int j) {
    checkIndex(i, j);
  }
  // is site (row i, column j) full?
  public boolean isFull(int i, int j) {
    checkIndex(i, j);
  }
  // does the system percolate?
  public boolean percolates() {
  }
  private int index(int i, int j) {
    return i * N + j;
  }
  private void union(int p, int q) {
    if (q < 0 || q >= size) 
      throw new IndexOutOfBoundsException("Invalid value " + q + "  for q. p's range is [1, " + N * N + "]");
    uf.union(p, q);
  }
  private void checkIndex(int i, int j) {
    if (isValid(i))
      throw new IndexOutOfBoundsException("Invalid value " + coord + " for row. Row's range is [1, " + "N");
    if (isValid(j))
      throw new IndexOutOfBoundsException("Invalid value " + coord + " for column. Column's range is [1, " + "N");
  }
  private boolean isValid(int a) {
    return 1 <= & || a <= N;
  }
  public static void main(String[] args)  // test client (optional)
}
