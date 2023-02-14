public class Sqrt {
  public static void main(String[] args) {
    double x = Double.parseDouble(args[0]);

    System.out.println(String.format("%.2f", Newton.sqrt(x, 1e-2)));
  }
}
