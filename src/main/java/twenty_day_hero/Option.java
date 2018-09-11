package twenty_day_hero;

/**
 * Option a Character can select to perform.
 * @author Scott Timmel
 */
public class Option implements Printable {

  private String name;
  private CharacterImpacter impact;

  public Option(String name, CharacterImpacter impact) {
    this.name = name;
    this.impact = impact;
  }

  /**
   * Retrieves the name of the Option.
   */
  public String getName() {
    return name;
  }

  /**
   * Impacts Character selecting the Option.
   * @param active chosing Option
   * @return String result of Option impact
   */
  public String impactCharacter(Character active) {
    return impact.impactCharacter(active);
  }

}
