public class Gambler {

  // Facts(known via mathematical analysis for centuries)
  // - Probalility of winning =  stake / goal.
  // - Expected number of bets = stake * design gain.
  public static void main(String[] args) {
    int stake = Integer.parseInt(args[0]);
    int goal = Integer.parseInt(args[1]);
    int trials = Integer.parseInt(args[2]);

    int wins = 0;
    int bets = 0;
    for (int i = 0; i < trials; ++i) {
        int cash = stake;

        while (cash > 0 && cash < goal) {
          cash = (Math.random() < 0.5) ? (cash+1) : (cash-1);
          bets++;
        }

        if (cash == goal) {
          wins++;
        }
    }

    System.out.println(wins + " wins of " + trials + " with " + bets + " bets");
  }
}
