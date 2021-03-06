package twenty_day_hero;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents a location the Character visits.
 * @author Scott Timmel
 */
public class Town extends MenuPrinter {

  private String suffix;
  private int[] pos;
  private LinkedList<Direction> generate = new LinkedList<Direction>();
  private int firstTurn = 0;
  private int nextFirst = 0;
  private int lastTurn = 0;

  /**
   * Town constructor.
   * @param name of Town
   * @param pos location of Town
   * @param items Places in Town Character can go
   */
  public Town(String name, int[] pos, ArrayList<Printable> items) {
    suffix = "Welcome to " + name + "!\nWhere would you like to go here?\n";
    for (Printable dir : DirectionManager.getManager().setItems(pos)) {
      generate.add((Direction)dir);
    }
    this.pos = pos;
    this.items = items;
  }

  /**
   * Retrieves the positions of the Town.
   * @return the Town position
   */
  public int[] getPos() {
    return pos;
  }

  /**
   * Sets the description of the Town.
   */
  public void setDescription(String desc) {
    description = desc + suffix;
  }

  /**
   * Generates NPC characters which are active during the tarvel stage and fight in battles.
   * @return list of generated Characters.
   */
  public LinkedList<Character> generateNpc() {
    LinkedList<Character> charList = new LinkedList<Character>();
    if (GameController.getController().getTurn() 
        < firstTurn || GameController.getController().getTurn() > lastTurn) {
      if (nextFirst == firstTurn) {
        nextFirst = GameController.getController().getTurn();
      }
      for (Direction dir : generate) {
        Character gen = CharacterFactory.getFactory().createCharacter("npc");
        gen.setTown(this);
        gen.setDirection(dir);
        charList.add(gen);
      } 
    }
    return charList;
  }
  
  public void setFirst() {
    firstTurn = nextFirst;
    lastTurn = GameController.getController().getTurn();
  }

  /**
   * Adds a Character which was generated by this Town to the collection 
   * of previously generated Characters.
   * @param dir Direction character moves
   * @param active Character being generated
   */
}
