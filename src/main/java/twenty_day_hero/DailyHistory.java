package twenty_day_hero;

/**
 * Memento object which holds Character stats for a specified turn.
 * @author Scott Timmel
 */
public class DailyHistory {

  private CharacterStats stats;
  private boolean first;

  public DailyHistory(CharacterStats stats, boolean first) {
    this.stats = stats;
    this.first = first;
  }

  /**
   * Retrieves the held Character stats.
   * @return held stats
   */
  public CharacterStats getStats() {
    return stats;
  }

  /**
   * Restore the Character stats to the stats held.
   */
  public void setStats() {
    if (first) {
      stats.getActive().updatedCharacter();
      PlayerUpdater.getUpdater().registerCharacter(stats.getActive());
    }
    stats.getActive().restoreStats(this);
    stats.getActive().setChange();
  }

}
