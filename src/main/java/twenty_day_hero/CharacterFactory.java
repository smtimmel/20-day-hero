package twenty_day_hero;

import java.util.Random;

/**
 * Factory which generates Character objects.
 * @author Scott Timmel
 */
public class CharacterFactory {

  private static CharacterFactory factory = new CharacterFactory();
  private static final String[] TITLES = 
      {"Sir", "Lord", "Baron", "Warlock", "Peasant",
          "Sorcerer", "Prince", "King", "Warlord", "Father"};
  private static final String[] NAMES = 
      {"Edward", "Robert", "Arthur", "William",
          "Steve", "James", "Paul", "Ron", "Lancelot", "Thomas"};
  private static final String[] TRAITS = 
      {"Wise", "Strong", "Good", "Tall", "Honest", "Angry", "Evil", "Ugly", "Insane", "Crazy"};
  private static final int HERO_STAT = 10;
  private static final int WITCH_LOW = 80;
  private static final int WITCH_HIGH = 100;
  private static final int ENEMY_LOW = 3;
  private static final int ENEMY_HIGH = 5;
  private static final int ALLY_RATIO = 3;

  private Random rand = new Random();
  private String heroName;

  private CharacterFactory() { }

  /**
   * Retrieves the singleton CharacterFactory object.
   * @return factory
   */
  public static CharacterFactory getFactory() {
    return factory;
  }

  /**
   * Generates a new Character object.
   * @param type of Character to be created
   * @return instantiated Character
   */
  public Character createCharacter(String type) {
    if (type.equals("hero")) {
      return new Character(new KnownTravel(), heroName, HERO_STAT, true);
    } else if (type.equals("npc")) {
      return new Character(new UnknownTravel(true), TITLES[rand.nextInt(TITLES.length)]
          + " " + NAMES[rand.nextInt(NAMES.length)] + " the " + TRAITS[rand.nextInt(TRAITS.length)],
              ENEMY_LOW + rand.nextInt(ENEMY_HIGH - ENEMY_LOW) + 1, getAllegiance());
    } else {
      return new Character(new KnownTravel(),
          "The Witch", WITCH_LOW + (WITCH_HIGH - WITCH_LOW) + 1, false);
    }
  }

  /**
   * Set the name for generated Hero Characters.
   * @param heroName name of new hero
   */
  public void setHeroName(String heroName) {
    this.heroName = heroName;
  }

  /**
   * Generates an allegiance for Characters.
   * @return generated allegiance
   */
  private boolean getAllegiance() {
    int check = rand.nextInt(ALLY_RATIO);
    if (check > 0) {
      return false;
    }
    return true;
  }

}
