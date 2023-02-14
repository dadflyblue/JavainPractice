public class MaximumSquareSubmatrix {

  // specification:
  //  https://coursera.cs.princeton.edu/introcs/assignments/performance/specification.php

  // Returns the size of the largest contiguous square submatrix
  // of a[][] containing only 1s.
  /*
    For square7.txt, the opt matrix dynamic operation steps should be like below:

      0  1  1  0  1  1  1
      1  1  0  1  1  2  2
      0  1  1  1  2  2  3
      1  1  2  2  2  0  1
      1  2  2  3  3  1  0
      1  2  3  3  0  1  1
      1  2  3  4  0  1  2
    */
  public static int size(int[][] a) {
    int n = a.length;
    // let value of opt[i][j] as the largest size of contiguous square 
    // whose lower right point located at (i,j).
    int[][] opt = new int[n+1][n+1];
    int max = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (a[i-1][j-1] == 1) {
          opt[i][j] = 1 + Math.min(
            opt[i-1][j-1], Math.min(opt[i][j-1], opt[i-1][j]));
          if (opt[i][j] > max) {
            max = opt[i][j];
          }
        }
      }
    }

    return max;
  }

  // Reads an n-by-n matrix of 0s and 1s from standard input
  // and prints the size of the largest contiguous square submatrix
  // containing only 1s.
  public static void main(String[] args) {
    int n = StdIn.readInt();
    int[][] a = new int[n][];

    for (int i = 0; i < n; i++) {
      a[i] = new int[n];
      for (int j = 0; j < n; j++) {
        a[i][j] = StdIn.readInt();
      }
    }

    System.out.println(size(a));
  }
}
