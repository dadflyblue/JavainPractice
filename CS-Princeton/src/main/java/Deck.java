public class Deck {
  public static void main(String[] args) {
    String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    String[] suit = { "♣", "♦", "♥", "♠" };
    String[] deck = new String[52];

    for (int i = 0; i < suit.length; i++) {
      for (int j = 0; j < rank.length; j++) {
        deck[j+i*rank.length] = rank[j] + suit[i];
      }
    }

    for (int i = 0; i < deck.length; i++) {
      System.out.print(deck[i] + " ");
    }
    System.out.println();
  }
}
