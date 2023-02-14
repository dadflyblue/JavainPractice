public class Interval {

  // From: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/T9BoD/optional-enrichment-on-creating-data-types
  // 3.2.8 Write a data type Interval that implements the following API: 
  // An interval is defined to be the set of all points on the line greater than 
  // or equal to left and less than or equal to right. In particular, 
  // an interval with right less than left is empty. Write a client that is a filter 
  // that takes a floating-point command-line argument x and prints all of the intervals 
  // on standard input (each defined by a pair of double values) that contain x.

  private final double l, r;
  private final boolean empty;
  public Interval(double left, double right) {
    l = left;
    r = right;
    empty = (right < left);
  }

  public boolean contains(double x) {
    return empty ? false : (l <= x && x <= r);
  }

  public boolean intersects(Interval b) {
    return empty ? false : (b.contains(l) || b.contains(r));
  }

  @Override
  public String toString() {
    return String.format("Interval[%f, %f]", l, r);
  }

  public static void main(String[] args) {
    double x = Double.parseDouble(args[0]);

    while (!StdIn.isEmpty()) {
      double l = StdIn.readDouble();
      double r = StdIn.readDouble();

      Interval it = new Interval(l, r);
      if (it.contains(x)) {
        System.out.println(it);
      }
    }
  }
}
