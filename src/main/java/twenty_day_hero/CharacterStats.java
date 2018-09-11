package twenty_day_hero;

import java.util.HashMap;

/**
 * Character state which holds Character stats.
 * @author Scott Timmel
 */
public class CharacterStats {

  private static final int LIFESPAN = 4;
  private Character active;
  private Town town;
  private Direction dir;
  private int attk;
  private int def;
  private boolean alive = true;
  private HashMap<String, Integer> updatableStats = new HashMap<String, Integer>();

  /**
   * CharacterStats of constructor.
   * @param active Character having stats
   * @param attk attack stat of Character
   * @param def defense stat of Character
   */
  public CharacterStats(Character active, int attk, int def) {
    this.attk =  attk;
    this.def = def;
    this.active = active;
    updatableStats.put("Life", LIFESPAN);
    updatableStats.put("Immunity", 0);
  }

  /**
   * Retrieves default Character life span.
   * @return constant LifeSpan
   */
  public static int getLife() {
    return LIFESPAN;
  }

  /**
   * Retrieves attack of Character.
   * @return Character attack
   */
  public int getAttk() {
    return attk;
  }

  /**
   * Sets attack of Character.
   * @param attk of Character
   */
  public void setAttk(int attk) {
    this.attk = attk;
  }

  /**
   * Retrieves defense of Character.
   * @return Character defense
   */
  public int getDef() {
    return def;
  }

  /**
   * Sets defense of Character.
   * @param def of Character
   */
  public void setDef(int def) {
    this.def = def;
  }

  /**
   * Retrieves town Character is in or leaving.
   * @return applicable town
   */
  public Town getTown() {
    return town;
  }

  /**
   * Sets town Character is in or leaving.
   * @param town applicable
   */
  public void setTown(Town town) {
    this.town = town;
  }

  /**
   * Retrieves Direction Character is traveling.
   * @return applicable Direction
   */
  public Direction getDirection() {
    return dir;
  }

  /**
   * Sets Direction Character will travel.
   * @param dir Direction of Character travel
   */
  public void setDirection(Direction dir) {
    this.dir = dir;
  }

  /**
   * Sets life status of Character.
   * @param alive Character life status
   */
  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  /**
   * Determines if Character is alive.
   * @return true if alive, false otherwise
   */
  public boolean isAlive() {
    return alive;
  }

  /**
   * Updates the life or immunity Character stat.
   * @param stat to be updated
   * @param change to be made to stat
   */
  public void changeStat(String stat, int change) {
    int val = updatableStats.get(stat) + change;
    updatableStats.put(stat, val);
  }

  /**
   * Retrieves the life or immunity stat.
   * @param stat to be retrieved
   * @return stat value
   */
  public int getStat(String stat) {
    return updatableStats.get(stat);
  }

  /**
   * Retrieves Character owning the stats.
   * @return active Character
   */
  public Character getActive() {
    return active;
  }

  /**
   * Checks if Character life span is over.
   * @return String result of check
   */
  public String checkLife() {
    changeStat("Life", -1);
    if (updatableStats.get("Life") == 0) {
      alive = false;
      return "Times up for your character!\nYou have died.\n";
    }
    return "You have " + updatableStats.get("Life") + " days left!\n";
  }

  /**
   * Checks if character revived after battle.
   * @return String result of check
   */
  public String checkImmunity() {
    if (updatableStats.get("Immunity") > 0) {
      changeStat("Immunity", -1);
      alive = true;
      def = 0;
      return "Your character was revived by the Immunity potion!\n";
    }
    return "";
  }

}
