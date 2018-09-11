package twenty_day_hero;

/**
 * Option impact for situations in which stats only given to user, not taken.
 * @author Scott Timmel
 */
public class GiveOnlyImpact extends OptionImpact {

  private String mes;
  private int attk;
  private int def;

  /**
   * GiveOnlyImpact constructor.
   * @param mes additional message given to user
   * @param attk to be given
   * @param def to be given
   */
  public GiveOnlyImpact(String mes, int attk, int def) {
    this.mes = mes;
    this.attk = attk;
    this.def = def;
  }

  /**
   * No stats are taken from Character.
   */
  protected boolean setTake(Character active) {
    return true;
  }

  /**
   * Static amount to give set.
   */
  protected String setGive(Character active) {
    attkChange = attk;
    defChange = def;
    return mes;
  }

}
