public class Point {

  // From: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/T9BoD/optional-enrichment-on-creating-data-types
  // 3.2.11 Write a data type Point that implements the following API:
  
  private final double x, y;
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  private double distanceTo(Point q) {
    return Math.sqrt(Math.pow((x-q.x), 2) + Math.pow((y-q.y), 2));
  }

  @Override
  public String toString() {
    return String.format("Point(%.2f, %.2f)", x, y);
  }

  public static void main(String[] args) {
    Point p1 = new Point(1.0, 2.0);
    Point p2 = new Point(3.0, 4.0);

    System.out.println(p1 + " distance to " + p2 + " = " + p1.distanceTo(p2));
  }
}
