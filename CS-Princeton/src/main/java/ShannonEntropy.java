public class ShannonEntropy {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/io/specification.php
  public static void main(String[] args) {
    int m  = Integer.parseInt(args[0]);

    int[] x = new int[m+1];
    int c = 0;

    while (!StdIn.isEmpty()) {
      int s = StdIn.readInt();
      x[s]++;
      c++;
    }

    double h = 0.0;
    
    for (int i = 1; i <= m; i++) {
      double pi = (double) x[i] / c;
      if (pi != 0) {
        h += -(pi * Math.log(pi) / Math.log(2));
      }
    }
    
    StdOut.println(String.format("%.4f", h));
  }
}
