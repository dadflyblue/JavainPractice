public class Ruler {

  // recursive verion ruler
  public static String rruler(int n) {
    if (n == 0) 
      return " ";

    return rruler(n-1) + n + rruler(n-1);
  }

  // non-recursive version ruler
  public static String nrruler(int n) {
    String ruler = " ";
    for (int i = 1; i <= n; i++) {
      ruler = ruler + i + ruler;
    }
    return ruler;
  }

  /**
   * <p>
   * Source: 
   * https://www.coursera.org/learn/cs-programming-java/supplement/yvKrY/optional-enrichment-on-recursion
   * </p>
   * 
   * 2.3.9 Ruler. Write a recursive program Ruler to plot the subdivisions of a ruler using StdDraw.
   * 
   * @param args command line input for getting ruler middle size.
   */
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    String ruler = rruler(n);

    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
    StdDraw.setXscale(-1, +1);
    StdDraw.setYscale(-1, +1);
    StdDraw.text(0.0, 0.0, ruler);

    System.out.println(nrruler(n));
    System.out.println(rruler(n));
  }
}
