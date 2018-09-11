package twenty_day_hero;

import java.util.ArrayList;

/**
 * Location where Options are held.
 * @author Scott Timmel
 */
public class Place extends MenuPrinter implements Printable {

  private String name;

  /**
   * Place constructor.
   * @param name of Place
   * @param items Options Place holds
   */
  public Place(String name, ArrayList<Printable> items) {
    this.name = name;
    this.items = items;
    description = "Welcome to the " + name + ".\nWhat would you like?\n";
  }

  /**
   * Retrieves the name of the Place.
   */
  public String getName() {
    return name;
  }

  /**
   * No description needs to be set.
   */
  public void setDescription(String desc) { }

}
