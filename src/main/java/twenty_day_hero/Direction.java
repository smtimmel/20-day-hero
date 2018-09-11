package twenty_day_hero;

/**
 * Specifies movement details in a certain direction.
 * @author Scott Timmel
 */
public class Direction implements Printable {

  private String name;
  private int affectedAxis;
  private int lowerBound;
  private int upperBound;

  /**
   * Direction constructor.
   * @param name of the Direction
   * @param affectedAxis by the Direction
   * @param lowerBound Direction travel allows
   * @param upperBound Direction travel allows
   */
  public Direction(String name, int affectedAxis, int lowerBound, int upperBound) {
    this.name = name;
    this.affectedAxis = affectedAxis;
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  /**
   * Retrieves the name of the direction.
   */
  public String getName() {
    return name;
  }

  /**
   * Checks if direction can be traveled.
   * @param coords of current position
   * @return true if travel possible, false otherwise
   */
  public boolean check(int[] coords) {
    if (coords[affectedAxis] > lowerBound && coords[affectedAxis] < upperBound) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Determines the next position after travel in the direction.
   * @param coords current position
   * @return coordinates of next position
   */
  public int[] getNext(int[] coords) {
    int[] next = new int[2];
    if (lowerBound == 0) {
      next[affectedAxis] = coords[affectedAxis] - 1;
    } else {
      next[affectedAxis] = coords[affectedAxis] + 1;
    }
    next[(affectedAxis + 1) % 2] = coords[(affectedAxis + 1) % 2];
    return next;
  }

}
