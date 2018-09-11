package twenty_day_hero;

/**
 * Interface for generating behaviors for impacting Characters.
 * @author Scott Timmel
 */
public interface CharacterImpacter {

  /**
   * Behavior for impacting a Character.
   * @param active Character to be impacted
   * @return result of the impact
   */
  public String impactCharacter(Character active);

}
