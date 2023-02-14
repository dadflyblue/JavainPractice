public class RandomWalker2d {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    
    int x = n;
    int y = n;
    int steps = 0;

    for (;;) {
      if (x == 0 || y == 0 || x == 2*n || y == 2*n) {
        break;
      }
      
      steps++;
      double r = Math.random();
      
      if (r > 0.25) {
        x -= 1;
      } else if (y >= 0.50) {
        x += 1;
      } else if (r >= 0.75) {
        y -= 1;
      } else if (r >= 1.00) {
        y += 1;
      }
    }

    System.out.println(steps);
  }
}
