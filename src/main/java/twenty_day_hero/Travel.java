package twenty_day_hero;

/**
 * Behavior performed by Characters during the Travel stage.
 * @author Scott Timmel
 */
public abstract class Travel implements CharacterImpacter {

  /**
   * Behavior impacting the Character during the Travel stage.
   */
  public String impactCharacter(Character active) {
    first(active);
    if (active.isAlive()) {
      Encounter.getEncounter().addCharacter(active);
    } else {
      PlayerUpdater.getUpdater().removeLater(active);
    }
    saveHistory(active);
    return null;
  }

  /**
   * First step of the travel stage.
   * @param active Character performing travel
   */
  protected abstract void first(Character active);

  /**
   * Saves a memento of Character stats.
   * @param active Character whose stats are changed
   */
  protected abstract void saveHistory(Character active);

  /**
   * Informs that memento restore has been had.
   */
  public abstract void setChange();


}
