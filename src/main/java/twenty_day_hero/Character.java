package twenty_day_hero;

/**
 * Represents a character moving through the world.
 * @author Scott Timmel
 */
public class Character {

  private CharacterStats stats;
  private Travel travel;
  private String name;
  private boolean ally;
  private boolean first = true;

  /**
   * Character constructor.
   * @param travel behavior of Character
   * @param name of Character
   * @param stat Character attack and defense stat
   * @param ally allegiance of Character
   */
  public Character(Travel travel, String name, int stat, boolean ally) {
    this.travel = travel;
    this.name = name;
    this.ally = ally;
    stats = new CharacterStats(this, stat, stat);
  }

  /**
   * Called each turn by observable to indicate beginning of travel step.
   */
  public void update() {
    travel.impactCharacter(this);
  }

  /**
   * Retrieves character attack.
   * @return Character attack
   */
  public int getAttk() {
    return stats.getAttk();
  }

  /**
   * Sets attack of Character.
   * @param attk new attack
   */
  public void setAttk(int attk) {
    stats.setAttk(attk);
  }

  /**
   * Retrieves defense of Character.
   * @return Character defense
   */
  public int getDef() {
    return stats.getDef();
  }

  /**
   * Sets defense of Character.
   * @param def new defense
   */
  public void setDef(int def) {
    stats.setDef(def);
  }

  /**
   * Retrieves Town Character is in or leaving.
   * @return applicable town
   */
  public Town getTown() {
    return stats.getTown();
  }

  /**
   * Sets town Character is in or leaving.
   * @param town applicable
   */
  public void setTown(Town town) {
    stats.setTown(town);
  }

  /**
   * Gets Direction Character is traveling.
   * @return traveling Direction
   */
  public Direction getDirection() {
    return stats.getDirection();
  }

  /**
   * Sets Direction Character is traveling.
   * @param dir new Direction
   */
  public void setDirection(Direction dir) {
    stats.setDirection(dir);
  }

  /**
   * Sets if the Character is dead or alive.
   * @param alive life status
   */
  public void setAlive(boolean alive) {
    stats.setAlive(alive);
  }

  /**
   * Determines if Character is alive.
   * @return true if alive, false otherwise
   */
  public boolean isAlive() {
    return stats.isAlive();
  }

  /**
   * Retrieves name of Character.
   * @return String name
   */
  public String getName() {
    return name;
  }

  /**
   * Updates the life or immunity stat.
   * @param stat to be updated
   * @param change difference in stat
   */
  public void changeStat(String stat, int change) {
    stats.changeStat(stat, change);
  }

  /**
   * Check if Character on user team.
   * @return true if friendly, false otherwise
   */
  public boolean checkAllegiance() {
    return ally;
  }

  /**
   * Sets a new DailyHistory memento object to store the Character status for the turn.
   * @return DailyHistory memento
   */
  public DailyHistory saveStats() {
    CharacterStats update = new CharacterStats(this, stats.getAttk(), stats.getDef());
    update.setTown(stats.getTown());
    update.setDirection(stats.getDirection());
    update.setAlive(stats.isAlive());
    update.changeStat("Life", stats.getStat("Life") - CharacterStats.getLife());
    update.changeStat("Immunity", stats.getStat("Immunity"));
    if (first) {
      first = false;
      return new DailyHistory(update, true);
    }
    return new DailyHistory(update, false);
  }

  /**
   * Sets the stats of the Character to match previous stats.
   * @param hist DailyHistory of Character for active turn
   */
  public void restoreStats(DailyHistory hist) {
    CharacterStats update = 
        new CharacterStats(this, hist.getStats().getAttk(), hist.getStats().getDef());
    update.setTown(hist.getStats().getTown());
    update.setDirection(hist.getStats().getDirection());
    update.setAlive(hist.getStats().isAlive());
    update.changeStat("Life", hist.getStats().getStat("Life") - CharacterStats.getLife());
    update.changeStat("Immunity", hist.getStats().getStat("Immunity"));
    stats = update;
  }

  /**
   * Turns Character from receiving mementos to determine stats to actively determining stats.
   */
  public void changeTravel() {
    travel = new UnknownTravel(false);
    travel.impactCharacter(this);
  }

  /**
   * Sets NPC character to one that receives memento stat updates.
   */
  public void updatedCharacter() {
    travel = new KnownTravel();
  }

  /**
   * Checks if Character 20 day life span is over.
   * @return result of check
   */
  public String checkLife() {
    return stats.checkLife();
  }

  /**
   * Checks if Character revived from battle death.
   * @return result of check
   */
  public String checkImmunity() {
    return stats.checkImmunity();
  }

  /**
   * Informs Character that memento updates still coming in so no need to change travel behavior.
   */
  public void setChange() {
    travel.setChange();
  }

}
