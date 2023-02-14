public class DealCards {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);

    String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    String[] suit = { "♣", "♦", "♥", "♠" };
    String[] deck = new String[52];

    for (int i = 0; i < rank.length; i++) {
      for (int j = 0; j < suit.length; j++) {
        deck[i + rank.length*j] = rank[i] + suit[j];
      }
    }

    for (int i = 0; i < deck.length; i++) {
      int r = i + (int) (Math.random() * (52-i));
      String t = deck[r];
      deck[r] = deck[i];
      deck[i] = t;
    }

    for (int i = 0; i < N; i++) {
      System.out.print(deck[i]);
    }
    System.out.println();
  }
}
