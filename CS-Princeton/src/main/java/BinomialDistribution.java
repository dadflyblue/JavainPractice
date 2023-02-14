public class BinomialDistribution {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    int[][] m = new int[n+1][]; // Pascal's triangle.

    m[1] = new int[1+2];
    m[1][1] = 1;

    // fill up the Pascal's triangle.
    for (int i = 2; i <= n; i++) {
      m[i] = new int[i+2];
      for (int k = 1; k < m[i].length - 1; k++) {
        m[i][k] = m[i-1][k] + m[i-1][k-1];
      }
    }

    // print out binomial distribution
    int denominator = 1;
    for (int i = 1; i < m.length; i++) {
      for (int k = 1; k < m[i].length - 1; k++) {
        System.out.print(m[i][k] + "/" + denominator + " ");
      }
      denominator += denominator;
      System.out.println();
    }
  }
}
