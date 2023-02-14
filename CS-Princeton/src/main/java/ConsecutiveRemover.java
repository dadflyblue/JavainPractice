public class ConsecutiveRemover {

  // Question 1.5.6 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/rZAs3/optional-enrichment-on-input-and-output
  public static void main(String[] args) {
    if (StdIn.isEmpty()) {
      return;
    }

    int x = StdIn.readInt();
    int p = x;
    while (!StdIn.isEmpty()) {
      x = StdIn.readInt();
      if (p != x) {
        StdOut.print(p + " ");
        p = x;
      }
    }

    StdOut.println();
  }
}
