package twenty_day_hero;

import java.util.LinkedList;

/**
 * Holds the world map and the Towns it consists of.
 * @author smtse
 */
public class World {

  private static World map = new World();
  private Town[][] worldMap = new Town[EDGE_DIMENSION][EDGE_DIMENSION];
  private LinkedList<Town> foundLocations = new LinkedList<Town>();
  private static final int EDGE_DIMENSION = 3;

  private World() { }

  /**
   * Retrieves the World singleton object.
   * @return the World object
   */
  public static World getWorld() {
    return map;
  }

  /**
   * Sets the location of the city of witches.
   * @param coords of the city of witches
   */
  public void setEssential(int[] coords) {
    worldMap[coords[0]][coords[1]] = TownFactory.getFactory().createTown(true, coords);
  }

  /**
   * Retrieves a list of Towns which have been found by playable Characters.
   * @return
   */
  public LinkedList<Town> getFound() {
    return foundLocations;
  }

  /**
   * Retrieves the board size.
   * @return size of board
   */
  public static int getDimension() {
    return EDGE_DIMENSION;
  }

  /**
   * Sets the Town a Character finds and registers it as found.
   * @param current Town which Character came from
   * @param coords position of new Town
   * @return Town Character finds
   */
  public Town findTown(Town current, int[] coords) {
    Town destination = arrive(current, coords);
    foundLocations.add(destination);
    return destination;
  }

  /**
   * Gets the Town a Character arrives at.
   * @param current Character previously at
   * @param coords of new Town
   * @return Town Character arrives at
   */
  public Town arrive(Town current, int[] coords) {
    try {
      if (worldMap[coords[0]][coords[1]] == null) {
        worldMap[coords[0]][coords[1]] = TownFactory.getFactory().createTown(false, coords);
      }
      return worldMap[coords[0]][coords[1]];
    } catch (Exception e) {
      return current;
    }
  }
}
