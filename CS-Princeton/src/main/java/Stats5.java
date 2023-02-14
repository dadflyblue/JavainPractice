public class Stats5 {
  public static void main(String[] args) {
    
    double total = 0.0;
    double min = 1.0;
    double max = 0.0;

    double n = Math.random();
    total += n;
    max = Math.max(max, n);
    min = Math.min(min, n);
    System.out.println(n);

    n = Math.random();
    total += n;
    max = Math.max(max, n);
    min = Math.min(min, n);
    System.out.println(n);

    n = Math.random();
    total += n;
    max = Math.max(max, n);
    min = Math.min(min, n);
    System.out.println(n);

    n = Math.random();
    total += n;
    max = Math.max(max, n);
    min = Math.min(min, n);
    System.out.println(n);

    n = Math.random();
    total += n;
    max = Math.max(max, n);
    min = Math.min(min, n);
    System.out.println(n);

    System.out.println("max: " + max);
    System.out.println("min: " + min);
    System.out.println("ave: " + total / 5);
  }
}
