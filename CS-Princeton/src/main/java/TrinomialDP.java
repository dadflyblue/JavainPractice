public class TrinomialDP {

  // specification:
  // https://coursera.cs.princeton.edu/introcs/assignments/recursion/specification.php

  // Also Reference:
  // https://stackoverflow.com/questions/62116948/convert-negative-index-to-positive-index-in-an-array-trinomial-triangle/66199688
  // The matrix of trinomial coefficient should be like bellow:
  /*                          1                             
                          1   1   1                         
                      1   2   3   2   1                     
                  1   3   6   7   6   3   1                 
              1   4   10  16  19  16  10  4   1             
          1   5   15  30  45  51  45  30  15  5   1         
      1   6  21   50  90  126 141 126 90  50  21  6  1     
   */
  // Returns the trinomial coefficient T(n, k).
  public static long trinomial(int n, int k) {
    if (n == 0 && k == 0) {
      return 1;
    }

    if (k < -n || k > n) {
      return 0;
    }

    k = Math.abs(k);
    long[][] opt = new long[n+1][n+2];

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= i; j++) {
        if (i == 0 && j == 0) {
          opt[i][j] = 1;
        } else {
          // i > 0 => i-1 >= 0 
          // for any j > 0, opt[i][-j] == opt[i][j] is true
          opt[i][j] = opt[i-1][Math.abs(j-1)] + opt[i-1][j] + opt[i-1][j+1];
        }
      }
    }
    return opt[n][k];
  }

  // Takes two integer command-line arguments n and k and prints T(n, k).
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int k = Integer.parseInt(args[1]);

    System.out.println(trinomial(n, k));
  }
}
