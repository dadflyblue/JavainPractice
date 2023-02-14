public class Spirographs {

  // Question 1.5.31 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/rZAs3/optional-enrichment-on-input-and-output
  public static void main(String[] args) {
    double R = Double.parseDouble(args[0]);
    double r = Double.parseDouble(args[1]);
    double a = Double.parseDouble(args[2]);

    double t = 0.0;
    double step = 0.2;
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(-R*3, +R*3);
    StdDraw.setYscale(-R*3, +R*3);
    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
    StdDraw.setPenRadius(0.01);

    for (;;) {
      double x = (R + r)*Math.cos(Math.toRadians(t)) - (r + a)*Math.cos(Math.toRadians((R + r)*t / r));
      double y = (R + r)*Math.sin(Math.toRadians(t)) - (r + a)*Math.sin(Math.toRadians((R + r)*t / r));

      StdDraw.point(x, y);
      StdDraw.show();
      StdDraw.pause(1);
      t += step;
    }
  }
}
