public class LG {

  // Question 2.1.10 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/wS82C/optional-enrichment-on-functions-and-libraries
  public static int lg(int n) {
    int i = 0;
    int a = 2;

    while (n >= a) {
      i++;
      a *= 2;
    }
    
    return i;
  }

  public static void main(String[] args) {

    int n = 16;
    for (int i = 1; i <= n; i++) {
      System.out.print(lg(i) + " ");
    }
    System.out.println();
    
  }
}
