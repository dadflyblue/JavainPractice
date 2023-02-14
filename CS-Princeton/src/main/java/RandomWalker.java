public class RandomWalker {
  public static void main(String[] args) {
    int r = Integer.parseInt(args[0]);

    int steps = 0;
    int x = 0;  // start point x position
    int y = 0;  // start point y position

    for (;;) {
      System.out.println("(" + x + ", " + y + ")");

      if (Math.abs(x) + Math.abs(y) == r) {
        break;
      }

      steps++;
      double d = Math.random();

      if (d < 0.25) {
        x += 1;
      } else if (d <= 0.50) {
        x -= 1;
      } else if (d <= 0.75) {
        y += 1;
      } else if (d <= 1.00) {
        y -= 1;
      }
    }

    System.out.println("steps = " + steps);
  }
}
