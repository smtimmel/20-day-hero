package twenty_day_hero;

import java.util.ArrayList;

/**
 * Base class for classes printing menu options to the user.
 * @author Scott Timmel
 */
public abstract class MenuPrinter {

  protected String description;
  protected ArrayList<Printable> items;

  /**
   * Retrieves menu for user and provides selection.
   * @return menu
   */
  public String getMenu() {
    StringBuilder menuStr = new StringBuilder(description);
    for (int i = 0; i < items.size(); i++) {
      menuStr.append((i + 1) + " - " + items.get(i).getName() + "\n");
    }
    menuStr.append("Choose a valid selection: ");
    return menuStr.toString();
  }

  /**
   * Retrieves an object based on the user menu selection.
   * @param id menu selection
   * @return Printable object from menu
   */
  public Printable getPrintable(int id) {
    return items.get(id - 1);
  }

  /**
   * Retrieves the total number of menu items.
   * @return number of menu items
   */
  public int numItems() {
    return items.size();
  }

  /**
   * Sets all or part of the menu description.
   * @param desc portion provided externally
   */
  public abstract void setDescription(String desc);

}
