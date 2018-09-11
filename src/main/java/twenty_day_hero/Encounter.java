package twenty_day_hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Controls actions performed during the travel and battle stages.
 * @author Scott Timmel
 */
public class Encounter {

  private static Encounter enc = new Encounter();
  private HashMap<EncounterLoc, LinkedList<Character>> encounters;
  private EncounterLoc activeEncounter;
  private Character main;
  private String travelResult;

  private Encounter() {
    setup();
  }

  /**
   * Specifies a travel location where battles can occur.
   * @author Scott Timmel
   */
  private class EncounterLoc {

    private Town town;
    private String dir;

    public EncounterLoc(Town town, String dir) {
      this.town = town;
      this.dir = dir;
    }

    /**
     * Retrieves town being traveled from.
     * @return applicable town
     */
    public Town getTown() {
      return town;
    }

    /**
     * Retrieves Direction being traveled.
     * @return applicable Direction
     */
    public String getDir() {
      return dir;
    }

    /**
     * Compares location with another location.
     * @param loc traveled to
     * @return true if locations are the same, false otherwise
     */
    public boolean compareVal(EncounterLoc loc) {
      if (town.equals(loc.getTown()) && dir.equals(loc.getDir())) {
        return true;
      }
      return false;
    }
  }

  /**
   * Retrieves the singleton Encounter object.
   * @return Encounter object
   */
  public static Encounter getEncounter() {
    return enc;
  }

  /**
   * Sets EncounterLoc of the active player.
   * @param main active Player
   * @param town traveled to
   * @param dir traveling Direction
   */
  public void setActiveEncounter(Character main, Town town, String dir) {
    this.main = main;
    activeEncounter = new EncounterLoc(town, dir);
  }

  /**
   * Adds a Character to an enounter location.
   * @param active Character to be added
   */
  public void addCharacter(Character active) {
    EncounterLoc loc = new EncounterLoc(active.getTown(), active.getDirection().getName());
    boolean exist = false;
    for (EncounterLoc check : encounters.keySet()) {
      if (check.compareVal(loc)) {
        exist = true;
        encounters.get(check).add(active);
        break;
      }
    }
    if (!exist) {
      LinkedList<Character> charList = new LinkedList<Character>();
      charList.add(active);
      encounters.put(loc, charList);
    }
  }

  /**
   * Initiates the battles.
   */
  public void battleSequence() {
    for (EncounterLoc loc : encounters.keySet()) {
      if (loc.compareVal(activeEncounter)) {
        StringBuilder travelStr = 
            new StringBuilder("You begin to travel " + main.getDirection().getName() + ".\n");
        travelStr.append(battle(encounters.get(loc), true));
        travelResult = travelStr.toString();
      } else {
        battle(encounters.get(loc), true);
      }
    }
    setup();
  }

  /**
   * Simulates a battle between Characters at a travel location.
   * @param fighters in the battle
   * @param go if survivors should move after battle
   * @return String result of battle
   */
  public String battle(LinkedList<Character> fighters, boolean go) {
    ArrayList<Character> good = new ArrayList<Character>();
    ArrayList<Character> bad = new ArrayList<Character>();
    for (Character check : fighters) {
      if (check.checkAllegiance()) {
        good.add(check);
      } else {
        bad.add(check);
      }
    }
    StringBuilder battleStr = new StringBuilder("");
    if (!good.isEmpty() && !bad.isEmpty()) {
      battleStr.append("You enter into a battle!\n");
      while (!good.isEmpty() && !bad.isEmpty()) {
        int max;
        if (good.size() < bad.size()) {
          max = bad.size();
          battleStr.append("Your side is outnumbered!\n");
        } else {
          max = good.size();
          if (bad.size() < good.size()) {
            battleStr.append("Your side outnumbers the enemies!\n");
          } else {
            battleStr.append("The sides are evenly matched!\n");
          }
        }
        LinkedList<Character> goodRemove = new LinkedList<Character>();
        LinkedList<Character> badRemove = new LinkedList<Character>();
        for (int i = 0; i < max; i++) {
          Character friend = getFighter(good, i);
          Character foe = getFighter(bad, i);
          if (friend.getDef() >= 0 && foe.getDef() >= 0) {
            battleStr.append(friend.getName() + " is facing off against " + foe.getName() + "!\n");
            battleStr.append(statPrint(friend));
            battleStr.append(statPrint(foe));
            if (calcDamage(foe, friend.getAttk())) {
              battleStr.append(foe.getName() + " has been killed!\n");
              badRemove.add(foe);
            }
            if (calcDamage(friend, foe.getAttk())) {
              battleStr.append(friend.getName() + " has been killed!\n");
              goodRemove.add(friend);
            }
          }
        }
        good.removeAll(goodRemove);
        bad.removeAll(badRemove);
      }
      if (main.isAlive()) {
        battleStr.append("The battle is won!\n");
      } else {
        battleStr.append("You did not survive the battle.\n");
        String immunity = main.checkImmunity();
        if (!immunity.equals("")) {
          good.add(main);
          battleStr.append(immunity);
        }
      }
    } 
    if (go) {
      if (!good.isEmpty()) {
        move(good);
      } 
      if (!bad.isEmpty()) {
        move(bad);
      }
    }
    return battleStr.toString();
  }

  /**
   * Retrieves a string of the Character stats.
   * @param active the Character having stats returned
   * @return String stat report
   */
  private String statPrint(Character active) {
    return active.getName() + " has " 
        + active.getAttk() + " attack and " + active.getDef() + " defense!\n";
  }

  /**
   * Does damages calculations on a Character, determines if alive.
   * @param attacked Character being attacked
   * @param attk damage Character taking
   * @return true if Character dies, false otherwise
   */
  private boolean calcDamage(Character attacked, int attk) {
    int def = attacked.getDef();
    attacked.setDef(def - attk);
    if (attacked.getDef() < 0) {
      attacked.setAlive(false);
      return true;
    }
    return false;
  }

  /**
   * Moves the surviving Characters after the battle is over.
   * @param survivors being moved
   */
  private void move(ArrayList<Character> survivors) {
    for (Character survivor : survivors) {
      int[] next = survivor.getDirection().getNext(survivor.getTown().getPos());
      if (survivor.equals(main)) {
        survivor.setTown(World.getWorld().findTown(survivor.getTown(), next));
      } else {
        survivor.setTown(World.getWorld().arrive(survivor.getTown(), next));
      }
    }
  }

  /**
   * Retrieves the result of the travel (battle) stage.
   * @return String result
   */
  public String getResult() {
    return travelResult;
  }

  /**
   * Prepares for next travel (battle) stage.
   */
  private void setup() {
    encounters = new HashMap<EncounterLoc, LinkedList<Character>>();
  }

  /**
   * Gets a fighter to battle.
   * @param charList list of fighters
   * @param i requested fighter index
   * @return selected fighter
   */
  private Character getFighter(ArrayList<Character> charList, int i) {
    if (i > charList.size() - 1) {
      return charList.get(charList.size() - 1);
    }
    return charList.get(i);
  }

}
