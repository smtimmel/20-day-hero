package twenty_day_hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Factory for creating Town objects.
 * @author Scott Timmel
 */
public class TownFactory {

  private static TownFactory factory = new TownFactory();
  private static final int NUM_LOCATIONS = 3;
  private static final int ARMORY_ATTK = 0;
  private static final int ARMORY_DEF = 10;
  private static final int BLACKSMITH_STAT = 4;
  private static final String[] NATURE = 
      {"Wind", "Water", "Fire", "Earth", "Life", "Moon", "Star", "Sun", "Horse", "Harvest"};
  private static final String[] DESC = 
      {"breaker", "destroyer", "forger", "wielder", "caller",
          "bringer", "keeper", "taker", "giver", "grower"};
  private static final String[] TYPE = 
      {"Town", "City", "Village", "Port", "Castle", "Fortress", "Temple", "Palace", "Land", "Camp"};
  private HashMap<String, CharacterImpacter> optionType = new HashMap<String, CharacterImpacter>();
  private HashMap<String, String[]> placeType = new HashMap<String, String[]>();
  private Random rand = new Random();

  private TownFactory() {
    String[] witch = {"Fight", "Leave"};
    placeType.put("Witch Hut", witch);
    String[] armory = {"Get 10 Defense", "Leave"};
    placeType.put("Armory", armory);
    String[] blacksmith = {"Get 4 Offense and 4 Defense", "Leave"};
    placeType.put("Blacksmith", blacksmith);
    String[] potionShop = {"Buy Life Potion", "Buy Immunity Potion", "Leave"};
    placeType.put("Potion Shop", potionShop);
    String[] festival = {"Bet All Stats", "Bet Half Stats", "Bet Quarter Stats", "Leave"};
    placeType.put("Festival", festival);
    String[] graveyard = {"Mourn Dead", "Pray", "Leave"};
    placeType.put("Graveyard", graveyard);
    optionType.put(armory[0], new GiveOnlyImpact("", ARMORY_ATTK, ARMORY_DEF));
    optionType.put(armory[1], new GiveOnlyImpact("You are leaving this location", 0, 0));
    optionType.put(blacksmith[0], new GiveOnlyImpact("", BLACKSMITH_STAT, BLACKSMITH_STAT));
    optionType.put(potionShop[0], new StatImpact("Life", 2));
    optionType.put(potionShop[1], new StatImpact("Immunity", 1));
    optionType.put(festival[0], new GambleImpact("all", 1));
    optionType.put(festival[1], new GambleImpact("half", 2));
    optionType.put(festival[2], new GambleImpact("quarter", 4));
    optionType.put(graveyard[0], new GraveImpact(PlayerUpdater.getUpdater(),
        "You mourn the few who have died.\n",
            "Many have died, you will need strength to survive\n", 10));
    optionType.put(graveyard[1], new GraveImpact(GameController.getController(), 
        "The gods answer 'Be strong, not many heros will die.'\n",
            "The gods answer 'Many heros will fall, we give you strength.'\n", 1));
    optionType.put(witch[0], new WitchFight());
  }

  /**
   * Retrieves the TownFactory singleton object.
   * @return the TownFactory object
   */
  public static TownFactory getFactory() {
    return factory;
  }

  /**
   * Creates a new Town object.
   * @param special determines if specialized city
   * @param pos location of city
   * @return created Town
   */
  public Town createTown(boolean special, int[] pos) {
    ArrayList<Printable> places = new ArrayList<Printable>(NUM_LOCATIONS);
    int remainLoc = NUM_LOCATIONS;
    String name;
    if (special) {
      name = "The City of Witches";
      places.add(new Place("Witch Hut", getOptions("Witch Hut")));
      placeType.remove("Witch Hut");
      remainLoc--;
    } else {
      name = NATURE[rand.nextInt(NATURE.length)] + DESC[rand.nextInt(DESC.length)] 
          + " " + TYPE[rand.nextInt(TYPE.length)];
    }
    HashSet<String> chosen = new HashSet<String>();
    String place;
    for (int i = 0; i < remainLoc; i++) {
      do {
        place = (String)placeType.keySet().toArray()[rand.nextInt(placeType.size())];
      } while (chosen.contains(place));
      chosen.add(place);
      places.add(new Place(place, getOptions(place)));
    }
    return new Town(name, pos, places);
  }

  /**
   * Retrieves all options which can be chosen at a Place.
   * @param place name containing Options
   * @return Option list
   */
  private ArrayList<Printable> getOptions(String place) {
    String[] optionStr = placeType.get(place);
    ArrayList<Printable> options = new ArrayList<Printable>(optionStr.length);
    for (String opt : optionStr) {
      options.add(new Option(opt, optionType.get(opt)));
    }
    return options;
  }

}
