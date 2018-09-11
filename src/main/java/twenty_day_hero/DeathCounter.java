package twenty_day_hero;

/**
 * Base class for keeping track of Character deaths.
 * @author Scott Timmel
 */
public abstract class DeathCounter {

  protected int deathCount = 0;

  /**
   * Retrieves the Character death count.
   * @return death count
   */
  public int getDeath() {
    return deathCount;
  }

}
