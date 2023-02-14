public class Goupon {
  public static void main(String[] args) {
    int M = Integer.parseInt(args[0]);
    int cards = 0;
    int distinct = 0;
    boolean[] f = new boolean[M]; // all items are false

    for (;;) {
      if (distinct == M) {
        break;
      }

      cards++;
      int r = (int) (Math.random() * M);
      
      if (!f[r]) {
        f[r] = true;
        distinct++;
      }
    }
    
    System.out.println(cards);
  }
}
