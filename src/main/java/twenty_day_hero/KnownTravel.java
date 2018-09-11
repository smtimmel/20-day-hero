package twenty_day_hero;

/**
 * Travel behavior for when stats are updated through active user playing or DailyHistory restores.
 * @author Scott Timmel
 */
public class KnownTravel extends Travel {

  private boolean change = true;

  /**
   * Checks if updates still coming in, if not travel type is changed.
   */
  protected void first(Character active) {
    if (!change) {
      active.changeTravel();
    } else {
      change = false;
    }
  }

  /**
   * Histories not saved.
   */
  protected void saveHistory(Character active) { }

  /**
   * Informs restore was carried out.
   */
  public void setChange() {
    change = true;
  }

}
