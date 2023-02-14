public class Prime {
  public static void main(String[] args) {
    long n = Long.parseLong(args[0]);

    boolean r = isPrime(n);

    if (r)
      System.out.println(n + " is prime");
    else
      System.out.println(n + " is not prime");
  }

  private static boolean isPrime(long n) {
    if (n < 2)
      return false;
    
    // try all possible factors of n
    // if if n has a factor, then it has one less than or equal to sqrt(n),
    // so for efficiency we only need to check factor <= sqrt(n) or
    // equivalently factor*factor <= n
    for (int i = 2; i <= n/i; i++) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }

}
