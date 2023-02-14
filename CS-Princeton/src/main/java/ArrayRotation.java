public class ArrayRotation {
  
  // specification: 
  //  https://www.coursera.org/learn/cs-programming-java/supplement/EAANO/optional-enrichment-on-performance
  /*
   * 4.1.33 Array rotation. Given an array of n elements, 
   * give a linear-time algorithm to rotate the array k positions. 
   * That is, if the array contains a[0], a[1], …, a[n–1] , 
   * the rotated array is a[k], a[k+1], …, a[n–1], a[0], …, a[k–1]. 
   * Use at most a constant amount of extra memory. Hint : Reverse three subarrays.
   */

  public static void rotate(int[] a, int k) {
    int N = a.length;
    if (k >= N) {
      throw new IllegalArgumentException("k should be less than length of a.");
    }
    reverse(a, 0, N-1);
    reverse(a, 0, N-k-1);
    reverse(a, N-k, N-1);
  }

  private static void reverse(int[] a, int s, int e) {
    int mid = s + (e - s) / 2;
    for (int i = s, j = e; i <= mid; i++, j--) {
      int temp = a[i];
      a[i] = a[j];
      a[j] = temp;
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    print(a);
    rotate(a, 8);
    print(a);
  }

  private static void print(int[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }
}
