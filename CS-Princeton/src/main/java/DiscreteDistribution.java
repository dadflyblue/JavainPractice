public class DiscreteDistribution {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/arrays/specification.php
  public static void main(String[] args) {
    int m = Integer.parseInt(args[0]);
    int n = args.length - 1;

    int[] a = new int[n+1];
    for (int i = 1; i <= n; i++) {
      a[i] = Integer.parseInt(args[i]);
    }

    // the cumulative sums Si=a1+a2+…+ai, with S0=0.
    int[] s = new int[n+1];
    for (int i = 1; i <= n; i++) {
      s[i] = a[i] + s[i-1]; 
    }

    for (int i = 0; i < m; i++) {
      if (i > 0 && i % 25 == 0) {
        System.out.println();
      }

      int r = (int) (Math.random() * s[n]);
      int index = 1;
      for (; index < s.length; index++) {
        if (s[index] > r) {
          break;
        }
      }
      System.out.print(index + " ");
    }

    System.out.println();
  }
}
