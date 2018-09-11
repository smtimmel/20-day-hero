package twenty_day_hero;

import java.util.LinkedList;

/**
 * Observable class for notifying Characters during the travel stage.
 * @author Scott Timmel
 */
public class PlayerUpdater extends DeathCounter {

  private static PlayerUpdater control = new PlayerUpdater();
  private LinkedList<Character> heros = new LinkedList<Character>();
  private LinkedList<Character> remove = new LinkedList<Character>();
  private Character active;

  private PlayerUpdater() { }

  /**
   * Retrieves the PlayerUpdater object.
   * @return the PlayerUpdater object
   */
  public static PlayerUpdater getUpdater() {
    return control;
  }
  
  /**
   * Sets Character as player Character.
   * @param active Character
   */
  public void setHero(Character active) {
    this.active = active;
  }

  /**
   * Registers a Character to be notified of the travel stage.
   * @param addition Character to be added
   */
  public void registerCharacter(Character addition) {
    heros.add(addition);
  }

  /**
   * Removes Characters who died from the notification list.
   */
  private void removeCharacter() {
    heros.removeAll(remove);
  }

  /**
   * Notifies Characters of the travel stage, then removes all 
   * Characters off the list who have died. 
   */
  public void notifyCharacters() {
    for (Character hero : heros) {
      hero.update();
    }
    active.update();
    removeCharacter();
    remove = new LinkedList<Character>();
  }

  /**
   * Stores the Characters who have died to later be removed from the notification list.
   * @param active Character to be removed
   */
  public void removeLater(Character active) {
    remove.add(active);
    deathCount++;
  }

  /**
   * Resets the notification list and death count after new hero created.
   */
  public void reset() {
    heros.clear();
    deathCount = 0;
  }

}
