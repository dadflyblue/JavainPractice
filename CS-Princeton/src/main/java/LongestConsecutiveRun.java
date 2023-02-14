public class LongestConsecutiveRun {
  
  // Question 1.5.5 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/rZAs3/optional-enrichment-on-input-and-output
  public static void main(String[] args) {
    if (StdIn.isEmpty()) {
      System.out.println("No longest consecutive run.");
      return;
    }

    int i = StdIn.readInt();
    int c = 1;
    int best = i;
    int bestCount = c;

    while (!StdIn.isEmpty()) {
      int x = StdIn.readInt();
      if (x == i) {
        c++;
      } else {
        i = x;
        c = 1;
      }

      if (c > bestCount) {
        best = i;
        bestCount = c;
      }
    }

    System.out.println(String.format("Longest run: %d consecutive %ds", best, bestCount));
  }
}
