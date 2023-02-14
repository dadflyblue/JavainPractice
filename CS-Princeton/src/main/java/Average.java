public class Average {
  public static void main(String[] args) {
    double sum = 0.0;
    int c = 0;

    for (;;) {
      if (StdIn.isEmpty())
        break;

      double d = StdIn.readDouble();
      sum += d;
      c++;
    }

    StdOut.println(sum / c);
  }
}
