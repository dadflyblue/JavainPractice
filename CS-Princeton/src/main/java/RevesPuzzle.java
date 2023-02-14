public class RevesPuzzle {

  // specification:
  // https://coursera.cs.princeton.edu/introcs/assignments/recursion/specification.php

  // Reves Puzzzle:
  // n: number of disc, s: source pole, d: dest pole, t1: transfer 1, t2: transfer 2
  private static void reves(int n, char s, char d, char t1, char t2) {
    if (n == 0) {
      return;
    }

    int k = (int) Math.round(n + 1 - Math.sqrt(2*n+1));
    
    reves(k, s, t1, d, t2);
    hanoi(n, k, s, d, t2);
    reves(k, t1, d, s, t2);
  }

  // move n-k
  // hanoi: s: source pole, d: dest pole, t: transfer pole.
  private static void hanoi(int n, int k, char s, char d, char t) {
    if (k == n) {
      return;
    }

    hanoi(n-1, k, s, t, d);
    StdOut.printf("Move disc %d from %c to %c%n", n, s, d);
    hanoi(n-1, k, t, d, s);
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    reves(n, 'A', 'D', 'B', 'C');
  }
}
