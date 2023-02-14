public class StatsSignals {

  // Question 1.5.17 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/rZAs3/optional-enrichment-on-input-and-output
  public static void main(String[] args) {
    if (StdIn.isEmpty()) {
      StdOut.println("No signals in.");
      return;
    }

    double s = StdIn.readDouble();
    double p = s;

    double ms = Math.abs(s); // magnitude sum
    double ps = Math.pow(s, 2); // power sum
    int c = 1; // count the number.
    int zc = 0; //  number of zero crossings
    while (!StdIn.isEmpty()) {
      s = StdIn.readDouble();

      if (s == -p) {
        zc++;
      }

      c++;
      p = s;
      ms += Math.abs(s);
      ps += Math.pow(s, 2);
    }

    StdOut.println(String.format("average magnitude is: %f", ms / c));
    StdOut.println(String.format("average power is: %f", ps / c));
    StdOut.println(String.format("number of zero crossings is: %d", zc));
  }
}
