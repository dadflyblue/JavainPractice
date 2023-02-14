public class ThueMorse {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/arrays/specification.php
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    boolean[] a = new boolean[n];

    // initialize Thue-Morse n sequence.
    for (int i = 1; i < n;) {
      int mark = i;
      for (int j = 0; j < mark && i < n; j++) {
        a[i] = !a[j];
        i++;
      }
    }

    boolean[][] m = new boolean[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        m[i][j] = a[i] == a[j];
      }
    }

    // prints out metrix of Thue-Morse n sequence.
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (m[i][j]) {
          System.out.print("+  ");
        } else {
          System.out.print("-  ");
        }
      }
      System.out.println();
    }
  }
}
