package Referee;

import Common.ObservableGameState;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;


/**
 * Represents a passive observer to an active game.
 */
public class Observer {

  private final List<ObservableGameState> observations;
  private ObserverGUI gui;
  private String directory;

  /**
   * Constructs an Observer wired to the given output directory.
   *
   * @param directory where this Observer will save its information
   */
  public Observer(String directory) {
    this.observations = new ArrayList<>();
    this.directory = directory;
  }

  /**
   * Updates this Observer with the most current ObservableGameState.
   * TODO: Should update() subsequently call view() and save()?
   *       Or should we call those only once at the end?
   */
  public void update(ObservableGameState observation) {
    this.observations.add(observation);
  }


  /**
   * Views all of this Observer's observations in an interactive GUI.
   */
  public void view() {
    gui = new ObserverGUI(this.observations);
    gui.setVisible(true);
    save();
  }

  /**
   * Saves all observations made by this Observer as
   * PNG images in the output directory.
   */
  private void save() {
    for (int i = 0; i < observations.size(); i++) {
      try {
        ImageIO.write(this.observations.get(i).toImage(), "png", new File(this.directory + i + ".png"));
      } catch (IOException e) {
        System.out.println("Could not save the image to this Observer's directory");
      }
    }
  }

  /**
   * Closes this Observer's GUI.
   */
  public void close() {
    gui.setVisible(false);
  }

  /**
   * Sets this Observer's output directory.
   */
  public void setDirectory(String directory) {
    this.directory = directory;
  }
}