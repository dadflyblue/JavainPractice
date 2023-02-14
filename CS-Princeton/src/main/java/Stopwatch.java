public class Stopwatch {

  private final long start;
  public Stopwatch() {
    start = System.currentTimeMillis();
  }

  public double elapsedTime() {
    long now = System.currentTimeMillis();
    return (now - start) / 1000.0;
  }

  public static void main(String[] args) throws InterruptedException {
    Stopwatch w = new Stopwatch();
    Thread.sleep(2000L);
    System.out.println(w.elapsedTime());
  }
}
