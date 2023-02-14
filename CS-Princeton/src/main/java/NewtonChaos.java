import java.awt.Color;

public class NewtonChaos {
  // From: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/T9BoD/optional-enrichment-on-creating-data-types
  // 3.2.34 Chaos with Newton’s method. 

  private static final Complex ONE = new Complex(1, 0);
  private static final Complex FOUR = new Complex(4, 0);
  private static final Complex MINUS_ONE = new Complex(-1, 0);
  private static final Complex I = new Complex(0, 1);
  private static final Complex MINUS_I = new Complex(0, -1);
  
  public static Color newton(double x, double y) {
    Complex z = new Complex(x, y);
    for (int i = 0; i < 100; i++) {
      if (z.equals(ONE)) {
        return Color.WHITE;
      } else if (z.equals(MINUS_ONE)) {
        return Color.RED;
      } else if (z.equals(I)) {
        return Color.GREEN;
      } else if (z.equals(MINUS_I)) {
        return Color.BLUE;
      }

      Complex a = z.multi(z).multi(z).multi(z).mins(ONE);
      Complex b = z.multi(z).multi(z).multi(FOUR);
      z = z.mins(a.div(b));
    }
    return Color.BLACK;
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    Picture p = new Picture(n, n);
    double size = 2;

    for (int col = 0; col < n; col++) {
      for (int row = 0; row < n; row++) {
        double x = col * size/n - size/2;
        double y = row * size/n - size/2;

        Color c = newton(x, y);
        p.set(col, n-1-row, c);
      }
    }
    p.show();
  }
}
