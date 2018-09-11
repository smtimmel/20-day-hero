package twenty_day_hero;

import java.util.ArrayList;

/**
 * Manages all possible travel Directions.
 * @author Scott Timmel
 */
public class DirectionManager extends MenuPrinter {

  private static DirectionManager direct = new DirectionManager();
  private Direction[] allDir = {new Direction("north", 0, 0, World.getDimension()),
      new Direction("south", 0, -1, World.getDimension() - 1),
      new Direction("east", 1, -1, World.getDimension() - 1),
      new Direction("west", 1, 0, World.getDimension())};
  private String suffix = 
      "Time to travel to the next town!\nWhich direction would you like to travel?\n";

  private DirectionManager() { }

  /**
   * Retrieves the DirectionManager singleton object.
   * @return the DirectionManager
   */
  public static DirectionManager getManager() {
    return direct;
  }

  /**
   * Sets Direction list to list of all possible travel Directions from a location.
   * @param pos current position
   * @return list of Directions
   */
  public ArrayList<Printable> setItems(int[] pos) {
    items = new ArrayList<Printable>();
    for (Direction dir : allDir) {
      if (dir.check(pos)) {
        items.add(dir);
      }
    }
    return items;
  }

  /**
   * Sets description of Direction selection menu.
   */
  public void setDescription(String desc) {
    description = desc + suffix;
  }

}
