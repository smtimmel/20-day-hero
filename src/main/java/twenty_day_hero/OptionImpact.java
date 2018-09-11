package twenty_day_hero;

/**
 * Base class for behaviors Options can have for impacting Characters.
 * @author Scott Timmel
 */
public abstract class OptionImpact implements CharacterImpacter {

  protected int attkChange = 0;
  protected int defChange = 0;

  /**
   * Option events occur and Character is impacted.
   */
  public String impactCharacter(Character active) {
    StringBuilder impact = new StringBuilder("");
    if (setTake(active)) {
      impact.append(perform(active, false, "Taking", "from"));
      impact.append(setGive(active));
      impact.append(perform(active, true, "Giving", "to"));
    } else {
      impact.append("Not enough stats!");
    }
    reset();
    return impact.toString();
  }

  /**
   * Sets the amount of stats to be taken from Character, determines if Character has enough.
   * @param active Character being taken from
   * @return true if Character has enough, false otherwise
   */
  protected abstract boolean setTake(Character active);
  
  /**
   * Sets the amount of stats to be given to Character, returns String of determining steps.
   * @param active Character to receive stats
   * @return String of steps to determine how much to give
   */
  protected abstract String setGive(Character active);

  /**
   * Performs the giving or taking of stats from Character.
   * @param active Character being given to or taken from
   * @param plus true is giving, false if taking
   * @param word either giving or taking
   * @param other either to or from
   * @return String result of give or take
   */
  private String perform(Character active, boolean plus, String word, String other) {
    int attk = active.getAttk();
    int def = active.getDef();
    if (plus) {
      active.setAttk(attk + attkChange);
      active.setDef(def + defChange);
    } else {
      active.setAttk(attk - attkChange);
      active.setDef(def - defChange);
    }
    StringBuilder takeStr = new StringBuilder("");
    if (attkChange > 0) {
      takeStr.append(word + " " + attkChange + " attack " + other + " " + active.getName() + "\n");
    }
    if (defChange > 0) {
      takeStr.append(word + " " + defChange + " defense " + other + " " + active.getName() + "\n");
    }
    return takeStr.toString();
  }

  /**
   * Resets the amount of stats to give or take.
   */
  private void reset() {
    attkChange = 0;
    defChange = 0;
  }

}
