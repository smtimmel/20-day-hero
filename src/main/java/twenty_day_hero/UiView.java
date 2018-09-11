package twenty_day_hero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Controls all messages printed to user.
 * @author Scott Timmel
 */
public class UiView {

  private static UiView view = new UiView();
  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  private UiView() { }

  /**
   * Retrieves the singleton UiView object.
   * @return UiView object
   */
  public static UiView getView() {
    return view;
  }

  /**
   * Prints a welcome message to the user.
   */
  public void welcomeMes() {
    System.out.println("Welcome to 20 Day Hero!");
    System.out.println("A witch has cursed you with 4 days to live.");
    System.out.println("You must travel to The City of Witches and defeat her before time is up.");
  }

  /**
   * Takes input from the user.
   * @param mes prompt
   * @return String input taken
   * @throws IOException if input error
   */
  public String takeInput(String mes) throws IOException {
    System.out.print(mes);
    return br.readLine();
  }

  /**
   * Prints message to user if invalid input given.
   */
  public void invalidInput() {
    System.out.println("Invalid selection, please try again.");
  }

  /**
   * Prints program termination message to user.
   * @param mes to be printed
   */
  public void end(String mes) {
    System.out.println(mes);
    System.out.println("Terminating program");
    try {
      br.close();
    } catch (IOException e) {
      return;
    }
  }


}
