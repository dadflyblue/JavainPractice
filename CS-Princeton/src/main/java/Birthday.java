public class Birthday {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/arrays/specification.php
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);

    // This is a trick way to hold all possible statistic data.
    // I guess it can be resolved by sort of dynamic array.
    int[] s = new int[n*10];

    for (int i = 0; i < trials; i++) {
      // Does someone get an age in it.
      boolean[] p = new boolean[n]; 

      int people = 0;
      for (;;) {
        people++;
        int r = (int) (Math.random() * n);
        if (p[r]) {
          s[people]++;
          break;
        }
        p[r] = true;
      }
    }

    double c = 0.0;
    for (int i = 1; i < s.length; i++) {
      c += s[i];
      double f = c/trials;

      System.out.println(i + "    " + s[i] + "    " + f);

      if (f >= 0.5) {
        break;
      }
    }
  }
}
