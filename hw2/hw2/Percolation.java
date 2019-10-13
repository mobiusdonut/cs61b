public class Percolation {
  private int sizeOfSide;
  private int virtualTop;
  private int virtualBottom;
  private int openSites;
  private WeightedQuickUnionUF uf;
  private WeightedQuickUnionUF antiBackwash;
  private boolean[] sites;

  public Percolation(int N) {
    if (N <= 0) {
        throw new java.lang.IllegalArgumentException();
    }
    sizeOfSide = N;
    virtualTop = N * N;
    virtualBottom = N * N + 1;
    uf = new WeightedQuickUnionUF(N * N + 2);
    antiBackwash = new WeightedQuickUnionUF(N * N + 1);
    sites = new boolean[N * N + 1];
    openSites = 0;
    for (int i = 0; i < sizeOfSide; i++) {
        antiBackwash.union(virtualTop, i);
    }

    //  connect bottom
    for (int j = sizeOfSide * (sizeOfSide - 1); j < sizeOfSide * sizeOfSide; j++) {
        uf.union(virtualBottom, j);
    }
  }

  private void validateIndex(int row, int col) {
    if (row < 0 || col < 0 || row > sizeOfSide - 1 || col > sizeOfSide - 1) {
        throw new java.lang.IndexOutOfBoundsException();
    }
  }

  private int xyTo1D(int row, int col) {
    return sizeOfSide * row + col;
  }

  private void connectEmpty(int row, int col) {
      for (int x : neighbours(row, col)) {
          if (x != -1 && sites[x]) {
              uf.union(xyTo1D(row, col), x);
              antiBackwash.union(xyTo1D(row, col), x);
              if (antiBackwash.connected(virtualTop,x)) {
                  uf.union(virtualTop, xyTo1D(row, col));
                  antiBackwash.union(virtualTop, xyTo1D(row, col));
              }
          }
      }
  }

  private int[] neighbours(int row, int col) {
    int[] neighbours = {-1, -1, -1, -1};

    // if only one row, add itself
    if (row == 0) {
        neighbours[0] = xyTo1D(row, col);
    }

    // add if there's top neighbour
    if (row > 0) {
        neighbours[0] = xyTo1D(row - 1, col);
    }

    // add if there's bottom neighbour
    if (row != sizeOfSide - 1) {
        neighbours[1] = xyTo1D(row + 1, col);
    }

    // add if there's left neighbour
    if (col > 0) {
        neighbours[2] = xyTo1D(row, col - 1);
    }

    // add if there's right neighbour
    if (col != sizeOfSide - 1) {
        neighbours[3] = xyTo1D(row, col + 1);
    }
    return neighbours;
  }

  public void open(int row, int col) {
    validateIndex(row, col);
    int target = xyTo1D(row, col);
    if (!sites[target]) {
        sites[target] = true;
        connectEmpty(row, col);
        openSites++;
    }
  }
  public boolean isOpen(int row, int col) {
    validateIndex(row, col);
    return sites[xyTo1D(row, col)];
  }
  public boolean isFull(int row, int col) {
    validateIndex(row, col);
    return sites[xyTo1D(row, col)] && antiBackwash.connected(virtualTop, xyTo1D(row, col));
  }
  public int numberOfOpenSites() {
    return openSites;
  }
  public boolean percolates() {
    return uf.connected(virtualTop, virtualBottom);
  }
  public static void main(String[] args) {

  }
}
