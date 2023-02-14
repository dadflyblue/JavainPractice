public class MultiplyMatrix {
  public static void main(String[] args) {
    double a[][] = {
      {.70, .20, .10},
      {.30, .60, .10},
      {.50, .10, .40}
    };
    double b[][] = {
      {.80, .30, .50},
      {.10, .40, .10},
      {.10, .30, .40}
    };

    if (a[0].length != b.length) {
      System.out.println("they are not legal matrixs for multiplication.");
      return;
    }
    
    double[][] c = multipy(a, b);
    
    for (int i = 0; i < c.length; i++) {
      for (int j = 0; j < c[0].length; j++) {
        System.out.print(String.format("%.2f", c[i][j]) + " ");
      }
      System.out.println();
    }
  }

  private static double[][] multipy(double[][] a, double[][] b) {
    double[][] c = new double[a.length][b[0].length];

    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[i].length; j++) {
        for (int k = 0; k < a[i].length; k++) {
          c[i][j] += a[i][k] * b[k][j]; 
        }
      }
    }

    return c;
  }
}
