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
  }

  // open site (row i, column j) if it is not open already
  public void open(int i, int j) {
    checkIndex(i, j);
    if (isOpen(i, j)) return;
    status[i-1][j-1] = OPEN;
    int p = index(i, j);
    if (i == 1)
      uf.union(p, vTop);
    else if (i == N)
      uf.union(p, vBottom);
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
    return uf.connected(vTop, index(i, j));
  }

  // does the system percolate?
  public boolean percolates() {
    return uf.connected(vTop, vBottom);
  }

  private int index(int i, int j) {
    return (i-1) * N + (j-1) + 1;
  }
  private void union(int p, int i, int j) {
    if (!isValid(i)) return;
    if (!isValid(j)) return;
    if (!isOpen(i, j)) return;
    int q = index(i, j);
    uf.union(p, q);
  }
  private void checkIndex(int i, int j) {
    if (!isValid(i))
      throw new IndexOutOfBoundsException("Invalid value " + i + " for row. Row's range is [1, " + N + "].");
    if (!isValid(j))
      throw new IndexOutOfBoundsException("Invalid value " + i + " for column. Column's range is [1, " + N + "].");
  }
  // a must be in [1, N].
  private boolean isValid(int a) {
    return 1 <= a && a <= N;
  }
  // test client (optional)
  public static void main(String[] args) {
    Percolation p22 = new Percolation(2);
    p22.open(1, 1);
    p22.open(2, 1);
    if (!p22.percolates())
      throw new IllegalStateException("percolation: expect yes, actual no");

    Percolation p33 = new Percolation(3);
    p33.open(1, 1);
    p33.open(2, 2);
    p33.open(3, 3);
    if (p33.percolates())
      throw new IllegalStateException("percolate: expect no, actual yes");
  }
}
