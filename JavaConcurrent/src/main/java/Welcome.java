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

    long start = System.currentTimeMillis();
    var fs = ForkJoinPool.commonPool().invokeAll(workers);
    waitAll(fs);
    long end = System.currentTimeMillis();
    System.out.printf("all workers(%d) from platform common thread pool returns: %dms%n", count, end-start);

    try (var exec = Executors.newCachedThreadPool()) {
      start = System.currentTimeMillis();
      fs = exec.invokeAll(workers);
      waitAll(fs);
      end = System.currentTimeMillis();
      System.out.printf("all workers(%d) from platform cached threads pool returns: %dms%n", count, end-start);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    try (var exec = Executors.newVirtualThreadPerTaskExecutor()) {
      start = System.currentTimeMillis();
      fs = exec.invokeAll(workers);
      waitAll(fs);
      end = System.currentTimeMillis();
      System.out.printf("all workers(%d) from virtual threads returns: %dms%n", count, end-start);
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
              "waiter - " + id + " at: " + Thread.currentThread());
          Thread.sleep(timeout);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        return null;
      }
    }
}
