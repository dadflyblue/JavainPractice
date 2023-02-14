public class HistogramSum {

  // Question 2.1.19 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/wS82C/optional-enrichment-on-functions-and-libraries
  public static int[] histogram(int[] a, int m) {
    int[] freq = new int[m];

    for (int i = 0; i < a.length; i++) {
      freq[a[i]]++;
    }

    return freq;
  }

  public static void main(String[] args) {
    int[] a = { 3, 3, 4, 5, 2, 1, 4, 0, 2, 2, 0, 1, 1, 5, 3, 0, 2, 2, 4, 4 };
    int[] freq = histogram(a, 6);
    
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.println();

    for (int i = 0; i < freq.length; i++) {
      System.out.println(i + ": " + freq[i]);
    }
  }
}
