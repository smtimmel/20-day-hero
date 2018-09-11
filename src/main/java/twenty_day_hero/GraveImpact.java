package twenty_day_hero;

/**
 * Option impact for situations in which what Character is given is based on death counts.
 * @author Scott Timmel
 */
public class GraveImpact extends OptionImpact {

  private static final int CUTOFF = 5;
  private DeathCounter counter;
  private String low;
  private String high;
  private int per;

  /**
   * GraveImpact constructor.
   * @param counter DeathCounter used to find death totals
   * @param low message when deaths are low
   * @param high message when deaths are high
   * @param per death ratio
   */
  public GraveImpact(DeathCounter counter, String low, String high, int per) {
    this.counter = counter;
    this.low = low;
    this.high = high;
    this.per = per;
  }

  /**
   * Nothing taken from Character.
   */
  protected boolean setTake(Character active) {
    return true;
  }

  /**
   * What is given based on ratio of death counts.
   */
  protected String setGive(Character active) {
    int reward = counter.getDeath() / per;
    attkChange = reward;
    defChange = reward;
    if (reward > CUTOFF) {
      return high;
    }
    return low;
  }

}
