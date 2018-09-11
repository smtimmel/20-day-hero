package twenty_day_hero;

import java.io.IOException;

/**
 * Controls game loop.
 * @author Scott Timmel
 */
public class GameController extends DeathCounter {

  private static GameController control = new GameController();
  private CharacterImpacter impact = new FirstHeroSetup();
  private int turn = 1;
  private boolean over = false;
  private Character active;

  private GameController() { }

  /**
   * Retrieves the GameController singleton object.
   * @return GameController object
   */
  public static GameController getController() {
    return control;
  }

  /**
   * Runs the main game loop.
   */
  public void runGame() {
    UiView.getView().welcomeMes();
    try {
      while (!over) {
        setup();
        while (active.isAlive()) {
          dayTick();
          turn++;
        }
        reset();
      }
    } catch (IOException e) {
      UiView.getView().end("Input error occurred.");
    } 
  }

  /**
   * Setup for preparing new hero to start game.
   * @throws IOException if input error occurs
   */
  private void setup() throws IOException {
    CharacterFactory.getFactory().setHeroName(UiView.getView()
        .takeInput("Hello hero! What is your name: "));
    active = CharacterFactory.getFactory().createCharacter("hero");
    PlayerUpdater.getUpdater().setHero(active);
    townSelection();
    active.getTown().setDescription("This is your home town!\n");
  }

  /**
   * Steps which are performed each turn.
   * @throws IOException if input error occurs
   */
  private void dayTick() throws IOException {
    Place chosenPlace = (Place)takeMenuInput(active.getTown());
    Option chosenOption = (Option)takeMenuInput(chosenPlace);
    String result = chosenOption.impactCharacter(active);
    if (over) {
      UiView.getView().end(result);
    } else if (!active.isAlive()) {
      checkNext(result);
    } else {
      DirectionManager.getManager().setDescription(result);
      DirectionManager.getManager().setItems(active.getTown().getPos());
      active.setDirection((Direction)takeMenuInput(DirectionManager.getManager()));
      Encounter.getEncounter().setActiveEncounter(active,
          active.getTown(), active.getDirection().getName());
      HistoryLog.getLog().setHistory();
      HistoryLog.getLog().addHistory(active.saveStats());
      for (Town found : World.getWorld().getFound()) {
        for (Character npc : found.generateNpc()) {
          PlayerUpdater.getUpdater().registerCharacter(npc);
        }
      }
      PlayerUpdater.getUpdater().notifyCharacters();
      active.setChange();
      String life = active.checkLife();
      if (!active.isAlive()) {
        checkNext(life);
      } else {
        Encounter.getEncounter().battleSequence();
        if (active.isAlive()) {
          active.getTown().setDescription(life + Encounter.getEncounter().getResult());
        } else {
          checkNext(Encounter.getEncounter().getResult());
        }
      }
    }
  }

  /**
   * Selects town user Character will start in.
   */
  private void townSelection() {
    impact.impactCharacter(active);
  }

  /**
   * Character town selection behavior set.
   * @param impact behavior for Town selection
   */
  public void setImpact(CharacterImpacter impact) {
    this.impact = impact;
  }

  /**
   * Retrieves the current turn.
   * @return current turn
   */
  public int getTurn() {
    return turn;
  }

  /**
   * Presents the menu to the user, takes the input and retrieves the selection.
   * @param printer object providing menu
   * @return selected object
   * @throws IOException if input error occurs
   */
  private Printable takeMenuInput(MenuPrinter printer) throws IOException {
    Integer valid = null;
    while (valid == null || valid > printer.numItems() || valid < 1) {
      try {
        String input = UiView.getView().takeInput(printer.getMenu());
        valid = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        continue;
      }
    }
    return printer.getPrintable(valid);
  }

  /**
   * Checks if user wants to keep playing after dying.
   * @param mes prompt for user
   * @throws IOException if input error occurs
   */
  private void checkNext(String mes) throws IOException {
    deathCount++;
    StringBuilder cont = new StringBuilder(mes);
    cont.append("You have died " + deathCount + " times!\nWould you like to keep playing?(y/n): ");
    if (!UiView.getView().takeInput(cont.toString()).equals("y")) {
      over = true;
      UiView.getView().end("Better luck next time!");
    } 
  }

  /**
   * Resets when player dies and makes new hero.
   */
  private void reset() {
    PlayerUpdater.getUpdater().reset();
    for (Town found : World.getWorld().getFound()) {
      found.setFirst();
    }
    turn = 1;
  }

  /**
   * Starts end of program.
   */
  public void endGame() {
    over = true;
  }

}
