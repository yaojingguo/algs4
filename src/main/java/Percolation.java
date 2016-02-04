import edu.princeton.cs.algs4.*;
  
public class Percolation {
  private static int BLOCKED = 0;
  private static int OPEN = 1;

  private int N;
  private int ufSize;
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
    this.ufSize = N * N + 2;
    this.uf = new WeightedQuickUnionUF(ufSize);
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
    if (isOpen(i, j)) return;
    status[i-1][j-1] = OPEN;
    int p = index(i, j);
    union(p, i, j-1);
    union(p, i-1, j);
    union(p, i, j+1);
    union(p, i+1, j);
  }

  // is site (row i, column j) open?
  public boolean isOpen(int i, int j) {
    checkIndex(i, j);
    return status[i-1][j-1] == OPEN;
  }

  // is site (row i, column j) full?
  public boolean isFull(int i, int j) {
    checkIndex(i, j);
    throw new UnsupportedOperationException();
  }

  // does the system percolate?
  public boolean percolates() {
    throw new UnsupportedOperationException();
  }

  private int index(int i, int j) {
    return i * N + j;
  }
  private void union(int p, int i, int j) {
    if (isValid(i)) return;
    if (isValid(j)) return;
    if (isOpen(i, j)) return;
    int q = index(i, j);
    uf.union(p, q);
  }
  private void checkIndex(int i, int j) {
    if (!isValid(i))
      throw new IndexOutOfBoundsException("Invalid value " + i + " for row. Row's range is [1, " + "N");
    if (!isValid(j))
      throw new IndexOutOfBoundsException("Invalid value " + i + " for column. Column's range is [1, " + "N");
  }
  private boolean isValid(int a) {
    return 1 <= a || a <= N;
  }
  // test client (optional)
  public static void main(String[] args) {
  }
}
