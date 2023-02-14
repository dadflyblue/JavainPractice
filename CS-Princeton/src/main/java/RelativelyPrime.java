public class RelativelyPrime {
  public static void main(String[] args) {
    int n  = Integer.parseInt(args[0]);

    for (int r = 1; r <= n; r++) {
      for (int c = 1; c <= n; c++) {
        if (gcd(r, c) == 1) {
          System.out.print('*');
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }

  private static int gcd(int x, int y) {
    for (;;) {
      int r = x % y;
      if (r == 0) {
        break;
      }
      x = y;
      y = r;
    }
    return y;
  }
}
