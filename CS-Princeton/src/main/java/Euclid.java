public class Euclid {
  // recursive version of geting greatest common divisor(gcd).
  public static int rgcd(int x, int y) {
    if (y == 0)
      return x;
    return rgcd(y, x % y);
  }

  // non-recursive version of gcd.
  public static int nrgcd(int x, int y) {
    while (y != 0) {
      int r = x % y; 
      x = y; 
      y = r;
    }
    return x;
  }

  public static void main(String[] args) {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);

    StdOut.printf("recursive gcd(%d, %d) = %d%n", x, y, rgcd(x, y));
    StdOut.printf("non-recursive gcd(%d, %d) = %d%n", x, y, nrgcd(x, y));
  }
}
