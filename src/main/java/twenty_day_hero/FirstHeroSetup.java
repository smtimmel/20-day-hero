package twenty_day_hero;

import java.util.Random;

/**
 * Behavior for setting up the playable Character for the first time (no previous deaths).
 * @author Scott Timmel
 */
public class FirstHeroSetup implements CharacterImpacter {

  /**
   * Sets up The City of Witches on map and creates a new town for Character to inhabit.
   */
  public String impactCharacter(Character active) {
    World.getWorld().setEssential(genCoords());
    active.setTown(World.getWorld().findTown(null, genCoords()));
    GameController.getController().setImpact(new LaterHeroSetup());
    return null;
  }

  /**
   * Generates random coordinates for new town.
   * @return generated coordinates
   */
  private int[] genCoords() {
    int[] coords = new int[2];
    Random rand = new Random();
    for (int i = 0; i < 2; i++) {
      coords[i] = rand.nextInt(World.getDimension());
    }
    return coords;
  }
}
