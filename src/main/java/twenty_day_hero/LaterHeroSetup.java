package twenty_day_hero;

import java.util.Random;

/**
 * Character initial setup for heros after first (hero deaths have occurred).
 * @author Scott Timmel
 *
 */
public class LaterHeroSetup implements CharacterImpacter {

  private Random rand = new Random();

  /**
   * Character set to start in random already found Town.
   */
  public String impactCharacter(Character active) {
    active.setTown(World.getWorld().getFound()
        .get(rand.nextInt(World.getWorld().getFound().size())));
    return null;
  }

}
