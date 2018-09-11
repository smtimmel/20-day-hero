package twenty_day_hero;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Option impact behavior in which a battle with the witch occurs.
 * @author Scott Timmel
 */
public class WitchFight implements CharacterImpacter {

  private HashMap<Integer, LinkedList<Character>> combatants =
      new HashMap<Integer, LinkedList<Character>>();
  private Character witch = CharacterFactory.getFactory().createCharacter("witch");

  /**
   * Performs the battle between the witch and the character, Character wins game if battle won.
   */
  public String impactCharacter(Character active) {
    StringBuilder result = new StringBuilder("");
    if (combatants.containsKey(GameController.getController().getTurn())) {
      combatants.get(GameController.getController().getTurn()).add(active);
      result.append(Encounter.getEncounter().battle(combatants
          .get(GameController.getController().getTurn()), false));
    } else {
      LinkedList<Character> fighters = new LinkedList<Character>();
      fighters.add(witch);
      fighters.add(active);
      combatants.put(GameController.getController().getTurn(), fighters);
      result.append(Encounter.getEncounter().battle(fighters, false));
    }
    if (!witch.isAlive()) {
      result.append("You win the game after " + GameController
          .getController().getDeath() + " hero deaths!\n");
      GameController.getController().endGame();
    } else if (active.isAlive()) {
      result.append(impactCharacter(active));
    } else {
      witch.setDef(witch.getAttk());
    }
    return result.toString();
  }
}
