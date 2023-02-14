public class FindingInteger {
  
  // specification: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/EAANO/optional-enrichment-on-performance


  // (a) Given an array of n integers from 1 to n with one value repeated twice and one missing, 
  // design an algorithm that finds the duplicated value, in linear time and constant extra memory. 
  // Integer overflow is not allowed.
  // 
  // input: [1, 4, 3, 4, 5, 6]
  // output: 2
  public static int qa(int[] a, int n) {
    // the actual sum
    long actual = 0;
    int duplicated = 0;

    for (int i = 0; i < n; i++) {
      int k = Math.abs(a[i]);
      actual += k;

      if (a[k-1] < 0) {
        duplicated = k;
      } else {
        a[k-1] = -a[k-1];
      }
    }

    // the sum value of 1 to n.
    // d = (actual - sum)
    // d = a - b; 
    // =>
    // b = a - d;
    long sum = n*(n+1) / 2;
    return duplicated - (int) (actual - sum);
  }

  // (b) Given a read-only array of n integers, where each value from 1 to n–1 occurs once 
  // and one occurs twice, design an algorithm that finds the duplicated value, 
  // in linear time and constant extra memory.
  // 
  // input: [1, 3, 1, 4, 5, 2], n = 6
  // output: 1
  public static int qb(int[] a, int n) {
    long actual = 0;
    int k = n - 1;
    
    for (int i = 0; i < n; i++) {
      actual += a[i];
    }

    long sum = k*(k+1) / 2;
    return (int) (actual - sum);
  }

  // (c) Given a read-only array of n integers with values between 1 and n–1, 
  // design an algorithm that finds a duplicated value, in linear time and constant extra memory.
  //
  // input: [3, 1, 2, 4, 5, 1]
  // ouput: 1
  public static int qc(int[] a) {
    int slow = a[0];
    int fast = a[a[0]];

    while (slow != fast) {
      slow = a[slow];
      fast = a[a[fast]];
    }

    slow = a[0];
    fast = a[fast];
    while (slow != fast) {
      slow = a[slow];
      fast = a[fast];
    }

    return slow;
  }

  public static void main(String[] args) {
    int[] a = { 6, 4, 5, 1, 3, 4 };
    System.out.println(qa(a, 6));
    int[] b = { 6, 2, 5, 1, 3, 2 };
    System.out.println(qa(b, 6));

    int[] c = { 1, 3, 1, 4, 5, 2 };
    System.out.println(qb(c, 6));
    
    int[] d = {3, 1, 2, 4, 5, 1};
    System.out.println(qc(d));
  }
}
