package twenty_day_hero;

import java.util.Random;

/**
 * Set travel behavior to be unknown and thus needing to determine actions and stat increases.
 * @author Scott Timmel
 */
public class UnknownTravel extends Travel {

  private static final int increase = 2;
  private Random rand = new Random();
  private boolean first;

  public UnknownTravel(boolean first) {
    this.first = first;
  }

  /**
   * Directions are set and stat increases are performed.
   */
  protected void first(Character active) {
    if (!first) {
      active.setDirection((Direction)DirectionManager.getManager().setItems(active.getTown()
          .getPos()).get(rand.nextInt(DirectionManager.getManager()
              .setItems(active.getTown().getPos()).size())));
    } else {
      first = false;
    }
    int attk = active.getAttk();
    int def = active.getDef();
    active.setAttk(attk + increase);
    active.setDef(def + increase);
  }

  /**
   * Changes made to Character stats logged.
   */
  protected void saveHistory(Character active) {
    HistoryLog.getLog().addHistory(active.saveStats());
  }

  /**
   * Not implemented.
   */
  public void setChange() { }

}
