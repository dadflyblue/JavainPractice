import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;;

public class Welcome {

  public static void main(String[] args) {
    final var count = Runtime.getRuntime().availableProcessors() * 3;
    final var random = new SecureRandom();
    var workers = new ArrayList<Worker>(count);
    for (int i = 0; i < count ; i++) {
      workers.add(new Worker(1000 + random.nextInt(5000), i));
    }

    testAndAutoClose(workers, ForkJoinPool.commonPool(), "platform common threads pool");
    testAndAutoClose(workers, Executors.newCachedThreadPool(), "platform cached threads pool");
    testAndAutoClose(workers, Executors.newVirtualThreadPerTaskExecutor(), "virtual threads pool");
  }

  private static void testAndAutoClose(Collection<? extends Callable<?>> workers, ExecutorService exec, String name) {
    try (exec) {
      var start = System.currentTimeMillis();
      var fs = exec.invokeAll(workers);
      waitAll(fs);
      var end = System.currentTimeMillis();
      System.out.printf("all workers(%d) from %s returns: %dms%n", workers.size(), name, end-start);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static void waitAll(Collection<? extends Future<?>> fs) {
    fs.forEach(f -> {
      try {
        f.get();
      } catch (InterruptedException | ExecutionException e) {
        throw new RuntimeException(e);
      }
    });
  }

  record Worker(long timeout, long id) implements Callable<Void> {

    @Override
      public Void call() {
        try {
          System.out.println(
              "waiter - " + id + " exec on: " + Thread.currentThread());
          Thread.sleep(timeout);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        return null;
      }
    }
}
