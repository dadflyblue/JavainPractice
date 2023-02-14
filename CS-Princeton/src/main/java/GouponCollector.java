public class GouponCollector {
  public static void main(String[] args) {
    int M = Integer.parseInt(args[0]);
    int trails = Integer.parseInt(args[1]);
    int cards = 0;

    for (int i = 0; i < trails; i++) {
      boolean[] f = new boolean[M];
      int distinct = 0;

      while (distinct != M) {
        cards++;
        int r = (int) (Math.random() * M);

        if (!f[r]) {
          f[r] = true;
          distinct++;
        }
      }
    }

    System.out.println(cards / trails);
  }
}
