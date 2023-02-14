import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Welcome {
  public static void main(String[] args) {

    for (int i = 0; i < Runtime.getRuntime().availableProcessors() + 10 ; i++) {
      ForkJoinPool.commonPool().submit(new Waiter());
    }

    boolean b = ForkJoinPool.commonPool().awaitQuiescence(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    System.out.println("hello " + b);
  }

  static class Waiter implements Runnable {
    static final AtomicInteger C = new AtomicInteger();

    @Override
    public void run() {
      try {
        System.out.println("waiter - " + C.incrementAndGet());
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
