public class FindMissingValue {

  // Question 1.5.7 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/rZAs3/optional-enrichment-on-input-and-output
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    // for index of s, it represents number i+1;
    // for value of s, it represents if above number is present.
    boolean[] s = new boolean[n];

    while (!StdIn.isEmpty()) {
      int i = StdIn.readInt();
      s[i-1] = true;
    }

    for (int i = 0; i < n; i++) {
      if (!s[i]) {
        StdOut.println(String.format("The missing value is %d.", i+1));
        break;
      }
    }
  }
}
