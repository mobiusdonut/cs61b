public class PercolationStats {
  private int sizeOfSide;
  private int numberOfTrials;
  private double[] results;

  public PercolationStats(int N, int T) {
      if (N <= 0 || T <= 0) {
          throw new java.lang.IllegalArgumentException();
      }

      sizeOfSide = N;
      numberOfTrials = T;
      results = new double[T];
      runTrials();
  }

  private void runTrials() {
      for (int i = 0; i < numberOfTrials; i++) {
          Percolation perc = new Percolation(sizeOfSide);
          double openSites = 0;

          while (!perc.percolates()) {
              int x = StdRandom.uniform(0, sizeOfSide);
              int y = StdRandom.uniform(0, sizeOfSide);
              if (!perc.isOpen(y, x)) {
                  perc.open(y, x);
                  openSites++;
              }
          }
          results[i] = openSites / (sizeOfSide * sizeOfSide);
      }
  }

  public double mean() {
      return StdStats.mean(results);
  }

  public double stddev() {
      return StdStats.stddev(results);
  }

  public double confidenceLo() {
      return mean() - 1.96 * stddev() / Math.sqrt(numberOfTrials);
  }

  public double confidenceHi() {
      return mean() + 1.96 * stddev() / Math.sqrt(numberOfTrials);
  }

  public static void main(String[] args) {
      PercolationStats ps = new PercolationStats(200, 1000);
      System.out.println("mean = " + ps.mean());
      System.out.println("stddev = " + ps.stddev());
      System.out.println("interval = " + ps.confidenceLo() + " " + ps.confidenceHi());
  }
}
