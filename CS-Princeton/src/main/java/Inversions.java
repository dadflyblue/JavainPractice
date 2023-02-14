public class Inversions {

  // specification:
  //  https://coursera.cs.princeton.edu/introcs/assignments/performance/specification.php

  // Return the number of inversions in the permutation a[].
  public static long count(int[] a) {
    int n = a.length;
    long c = 0;
    for (int i = 0; i < n; i++) {
      int p = a[i];
      for (int j = i; j < n; j++) {
        if (p > a[j]) 
          c++;
      }
    }
    return c;
  }

  // Return a permutation of length n with exactly k inversions.
  // Use a greedy approach. If k≥n−1, put element n−1 first in the permutation,
  // so that it is inverted with n−1 elements; otherwise put it last in the permutation, 
  // so that it is inverted with 0 elements. 
  // How can you continue this approach to determine where element n−2 should go?
  public static int[] generate(int n, long k) {
    int[] a = new int[n];

    int m = n-1;
    int i = 0;
    int j = n-1;
    while (m >= 0) {
      if (k >= m) {
        a[i] = m;
        k = k - m;
        i++;
      } else {
        a[j] = m;
        j--;
      }
      m--;
    }
    
    return a;
  }

  // Takes an integer n and a long k as command-line arguments,
  // and prints a permutation of length n with exactly k inversions.
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    long k = Long.parseLong(args[1]);

    int[] a = generate(n, k);
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }
}
