public class Ramanujan {

  // specification:
  //  https://coursera.cs.princeton.edu/introcs/assignments/performance/specification.php

  // Is n a Ramanujan number?
  public static boolean isRamanujan(long n) {
    long a = 1;
    int c = 0;
    double m = Math.pow(n, 1.0/3.0);

    // backup first time found value of b
    long bb = 0;
    while (a < m && c < 2) {
      long b = Math.round(Math.pow((n - Math.pow(a, 3.0)), 1.0/3.0));
      // Don't use Math.pow here, since it will cause float numbers comparing problem.
      // if a == bb means found a duplicated pair
      if (n == a*a*a + b*b*b && a != bb) {
        bb = b;
        c++;
      }
      a++;
    }

    return c == 2;
  }

  // Takes a long integer command-line arguments n and prints true if
  // n is a Ramanujan number, and false otherwise.
  public static void main(String[] args) {
    long n = Long.parseLong(args[0]);
    System.out.println(isRamanujan(n));
  }
}
