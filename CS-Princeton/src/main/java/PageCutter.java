public class PageCutter {

  /**
   * <p>
   * Source:
   *  https://www.coursera.org/learn/cs-programming-java/supplement/yvKrY/optional-enrichment-on-recursion
   * </p>
   * 
   * <p>2.3.16 A4 paper. 
   * The width-to-height ratio of paper in the ISO format is the square root of 2 to 1. 
   * Format A0 has an area of 1 square meter. Format A1 is A0 cut with a vertical line into two equal halves, 
   * A2 is A1 cut with a horizontal line into two halves, and so on.
   * Write a program that takes an integer command-line argument n and uses StdDraw to show how to cut a sheet 
   * of A0 paper into 2^n pieces.</p>
   * <p>This function is used to cut and draw the cutting line on the StdDraw.</p>
   * 
   * @param n deepth of cutting an A0 page
   * @param s trace the current deepth
   * @param x0 bottom left coordinate x
   * @param y0 bottom left coordinate y
   * @param x1 top riht coordinate x
   * @param y1 top right coordinate y
   */
  public static void cut(int n, int s, double x0, double y0, double x1, double y1) {
    if (n == s) 
      return;

    if (s % 2 == 0) {
      double x = x0 + (x1-x0)/2;
      StdDraw.line(x, y0, x, y1);

      StdDraw.show();
      StdDraw.pause(300);

      cut(n, s+1, x0, y0, x, y1);
      cut(n, s+1, x, y0, x1, y1);
    } else {
      double y = y0 + (y1-y0)/2;
      StdDraw.line(x0, y, x1, y);

      StdDraw.show();
      StdDraw.pause(300);

      cut(n, s+1, x0, y0, x1, y);
      cut(n, s+1, x0, y, x1, y1);
    }
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    // ISO Page standard: 
    // The width-to-height ratio of paper in the ISO format is the square root of 2 to 1.
    // A0: 1 square meter paper
    double x0 = 0.0, y0 = 0.0;
    double x1 = Math.sqrt(2.0 / Math.sqrt(2.0));
    double y1 = Math.sqrt(1.0 / Math.sqrt(2.0));
    
    double[] x = { 0.0, x1, x1, 0.0 };
    double[] y = { 0.0, 0.0, y1, y1 };
    
    StdDraw.enableDoubleBuffering();
    StdDraw.setYscale(-0.01, Math.sqrt(1.0 / Math.sqrt(2.0)) + 0.01);
    StdDraw.setXscale(-0.01, Math.sqrt(2.0 / Math.sqrt(2.0)) + 0.01);
    StdDraw.polygon(x, y);
    StdDraw.show();

    cut(n, 0, x0, y0, x1, y1);
  }
}
