package twenty_day_hero;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Care taker for Character DailyHistory stat containers.
 * @author Scott Timmel
 */
public class HistoryLog {

  private static HistoryLog log = new HistoryLog();
  private HashMap<Integer, LinkedList<DailyHistory>> history =
      new HashMap<Integer, LinkedList<DailyHistory>>();

  private HistoryLog() { }

  /**
   * Retrieves singleton HistoryLog object.
   * @return HistoryLog object
   */
  public static HistoryLog getLog() {
    return log;
  }

  /**
   * Adds the DailyHistory to be held and later restored.
   * @param hist to be added
   */
  public void addHistory(DailyHistory hist) {
    if (history.containsKey(GameController.getController().getTurn())) {
      history.get(GameController.getController().getTurn()).add(hist);
    } else {
      LinkedList<DailyHistory> histList = new LinkedList<DailyHistory>();
      histList.add(hist);
      history.put(GameController.getController().getTurn(), histList);
    }
  }

  /**
   * Restores the stats for all Characters.
   */
  public void setHistory() {
    if (history.containsKey(GameController.getController().getTurn())) {
      for (DailyHistory hist : history.get(GameController.getController().getTurn())) {
        hist.setStats();
      }
    } else {
      LinkedList<DailyHistory> record = new LinkedList<DailyHistory>();
      history.put(GameController.getController().getTurn(), record);
    }
  }

}
