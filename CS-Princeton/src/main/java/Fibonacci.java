public class Fibonacci {
  public static long nrfib(int n) {
    long[] fr = new long[n+1];
    fr[0] = 0;
    fr[1] = 1;

    for (int i = 2; i <= n; i++) {
      fr[i] = fr[i-1] + fr[i-2];
    }
    return fr[n];
  }

  public static long rfib(int n) {
    long[] fr = new long[n+1];
    return rfibHelper(n, fr);
  }

  private static long rfibHelper(int n, long[] fr) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    if (fr[n] != 0) return fr[n];
    
    return rfibHelper(n-1, fr) + rfibHelper(n-2, fr);
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    StdOut.printf("non-recursive fibonacci(%d) = %d%n", n, nrfib(n));
    StdOut.printf("recursive fibonacci(%d) = %d%n", n, rfib(n));
  }
}
