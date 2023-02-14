public class PokerStats {

  // Question 2.2.26 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/wS82C/optional-enrichment-on-functions-and-libraries
  public static void main(String[] args) {
    int trails = 100000;

    // one pair, two pair, three of a kind, a full house, and a flush 
    // straight and straight flush
    int[] freq = new int[7];
    String[] desc = {
      "one pair", "two pair", "three of a kind", 
      "full house", "flush", "straight", "straight flush"
    };

    for (int t = 0; t < trails; t++) {
      stats(freq, dealCards(shuffle(createDeck()), 5));
    }

    for (int i = 0; i < desc.length; i++) {
      System.out.println(desc[i] + ": " + ((double) freq[i] / trails));
    }
  }

  private static final int ONE_PAIR = 0;
  private static final int TWO_PAIR = 1;
  private static final int THREE = 2;
  private static final int FULL = 3;
  private static final int FLUSH = 4;
  private static final int STRAIGHT = 5;
  private static final int STRAIGHT_FLUSH = 6;

  public static void stats(int[] freq, int[][] cards) {
    int[] cs = new int[4]; // count suits
    int[] cr = new int[13]; // count ranks

    for (int[] card : cards) {
      int rank = card[0];
      int suit = card[1];
      cr[rank]++;
      cs[suit]++;
    }

    boolean areSameSuits = false;
    for (int c : cs) {
      if (c == 5) {
        areSameSuits = true;
        break;
      }
    }

    int twos = 0; // number of pairs
    int straight = 1;
    boolean isThree = false;
    boolean isTwo = false;
    
    for (int i = 0; i < cr.length; i++) {
      int rankCount = cr[i];
      if (rankCount == 3) {
        freq[THREE]++;
        isThree = true;
        if (isTwo) {
          freq[FULL]++;
          freq[THREE]--;
          freq[TWO_PAIR]--;
        }
      } else if (rankCount == 2) {
        isTwo = true;
        twos++;
        freq[ONE_PAIR]++;
        if (twos == 2) {
          freq[TWO_PAIR]++;
          freq[ONE_PAIR]--;
        } 
        
        if (isThree) {
          freq[FULL]++;
          freq[THREE]--;
          freq[TWO_PAIR]--;
        }
      } else if (rankCount == 1) {
        if (i > 0 && cr[i-1] == 1) {
          straight++;
        }
      }
    }

    boolean isStraight = (straight == 5);
    if (isStraight && areSameSuits) {
      freq[STRAIGHT_FLUSH]++;
    } else if (isStraight) {
      freq[STRAIGHT]++;
    } else if (areSameSuits) {
      freq[FLUSH]++;
    }
  }

  public static int[][] shuffle(int[][] deck) {
    for (int i = 0; i < deck.length; i++) {
      int r = StdRandom.uniformInt(i, deck.length);
      int[] d = { deck[i][0], deck[i][1] };
      deck[i][0] = deck[r][0]; deck[i][1] = deck[r][1];
      deck[r][0] = d[0]; deck[r][1] = d[1];
    }
    return deck;
  }

  public static int[][] dealCards(int[][] deck, int n) {
    if (n > deck.length) {
      throw new IllegalArgumentException("dealCards n > " + deck.length);
    }

    int[][] d = new int[n][2];
    for (int i = 0; i < n; i++) {
      d[i][0] = deck[i][0];
      d[i][1] = deck[i][1];
    }
    return d;
  }

  public static int[][] createDeck() {
    // "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" 
    int[] rank = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
    // "♣", "♦", "♥", "♠" 
    int[] suit = { 0, 1, 2, 3 };
    int[][] deck = new int[52][2];

    for (int i = 0; i < rank.length; i++) {
      for (int j = 0; j < suit.length; j++) {
        int[] card = deck[i + rank.length * j];
        card[0] = rank[i];
        card[1] = suit[j];
      }
    }
    return deck;
  }
}
