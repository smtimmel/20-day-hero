package twenty_day_hero;

import java.util.Random;

/**
 * Option impact for a Character gambling a certain amount of stats.
 * @author Scott Timmel
 */
public class GambleImpact extends OptionImpact {

  private static final int CUTOFF = 10;
  private static final int MAX = 21;
  private static final int PAYOUT = 2;
  private String bet;
  private int ratio;
  private Random rand = new Random();

  public GambleImpact(String bet, int ratio) {
    this.bet = bet;
    this.ratio = ratio;
  }

  /**
   * Amount taken set to percent of Character stats.
   */
  protected boolean setTake(Character active) {
    attkChange = active.getAttk() / ratio;
    defChange = active.getDef() / ratio;
    return true;
  }

  /**
   * Determines if Character wins or loses gamble, payout occurs accordingly.
   */
  protected String setGive(Character active) {
    StringBuilder gamble = new StringBuilder("You bet " + bet 
        + " of your total stats.\nIf you get lower than " + CUTOFF + " you double your bet!\n");
    int roll = rand.nextInt(MAX);
    gamble.append("You rolled a " + roll + ".\n");
    if (roll < 10) {
      attkChange *= PAYOUT;
      defChange *= PAYOUT;
      gamble.append("You won!\n");
    } else {
      attkChange = 0;
      defChange = 0;
      gamble.append("You lost.\n");
    }
    return gamble.toString();
  }

}
