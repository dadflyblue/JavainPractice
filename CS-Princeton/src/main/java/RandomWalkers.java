public class RandomWalkers {
  public static void main(String[] args) {
    int r = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    
    int total = 0;

    for (int i = 0; i < trials; i++) {
      int steps = 0;
      int x = 0;  // start point x position
      int y = 0;  // start point y position

      for (;;) {  
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

      total += steps;
    }

    System.out.println("average number of steps = " + ((double) total/trials));
  }
}
