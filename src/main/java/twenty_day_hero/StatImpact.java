package twenty_day_hero;

/**
 * Impact of Option which sells a stat changing item to the Character.
 * @author Scott Timmel
 */
public class StatImpact extends OptionImpact {

  private static final int PRICE = 10;
  private String stat;
  private int change;

  public StatImpact(String stat, int change) {
    this.stat = stat;
    this.change = change;
  }

  /**
   * Sets the price of the item to be purchased.
   */
  protected boolean setTake(Character active) {
    if (active.getAttk() >= PRICE) {
      attkChange = PRICE;
      return true;
    }
    return false;
  }

  /**
   * Perform the stat change on the user.
   */
  protected String setGive(Character active) {
    attkChange = 0;
    active.changeStat(stat, change);
    return "Purchase successful!\n";
  }

}
