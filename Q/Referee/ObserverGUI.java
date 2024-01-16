package Referee;
import Common.ObservableGameState;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.*;

/**
 * Represents the interactive graphical user interface provided to a
 * passive observer of an active game. Allows observers to toggle
 * back and forth through the various states of the game, as well
 * the ability to save any state as a JSON file.
 */
public class ObserverGUI extends JFrame implements ActionListener {

  private final Color backgroundColor = new Color(162, 232, 245);
  private final List<ObservableGameState> gameStates;
  private final JButton previousButton;
  private final JButton nextButton;
  private final JButton saveButton;
  private JPanel gameStatePanel;
  private int index;

  /**
   * Constructs an ObserverGUI with a list of ObservableGameStates.
   */
  public ObserverGUI(List<ObservableGameState> gameStates) {
    if (gameStates.size() == 0) {
      throw new IllegalArgumentException("Cannot create an ObserverGUI with no ObservableGameStates");
    }
    index = 0;
    this.gameStates = gameStates;
    buildFrame();

    this.previousButton = new JButton("<");
    this.previousButton.setPreferredSize(new Dimension(15, 20));
    this.previousButton.setActionCommand("previous");
    this.previousButton.addActionListener(this);
    this.previousButton.setEnabled(false);

    this.nextButton = new JButton(">");
    this.nextButton.setPreferredSize(new Dimension(15, 20));
    this.nextButton.setActionCommand("next");
    this.nextButton.addActionListener(this);

    this.saveButton = new JButton("save");
    this.saveButton.setPreferredSize(new Dimension(20, 20));
    this.saveButton.setActionCommand("save");
    this.saveButton.addActionListener(this);

    this.add(buildButtonPanel(previousButton), BorderLayout.WEST);
    this.add(buildButtonPanel(nextButton), BorderLayout.EAST);
    this.add(buildButtonPanel(saveButton), BorderLayout.SOUTH);
    this.gameStatePanel = this.gameStates.get(index).toPanel();
    this.add(gameStatePanel);

    this.pack();
    this.repaint();
    this.setVisible(true);
  }

  private void buildFrame() {
    this.setTitle("Q Game GUI");
    this.setPreferredSize(new Dimension(1000, 950));
    this.setLocationRelativeTo(null);
    this.setBackground(backgroundColor);
    this.setLayout(new BorderLayout());
  }

  private JPanel buildButtonPanel(JButton button) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(button, BorderLayout.PAGE_END);
    panel.setPreferredSize(new Dimension(43, 20));
    panel.setBackground(backgroundColor);
    return panel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "previous" -> {
        index--;
        this.updateGamePanel();
        this.repaint();
      }
      case "next" -> {
        index++;
        this.updateGamePanel();
        this.repaint();
      }
      case "save" -> {
        try {
          saveGameState();
        } catch (IOException ex) {
          System.out.println("Could not save game state.");
        }
      }
    }
  }

  @Override
  public void repaint() {
    this.previousButton.setEnabled(index > 0);
    this.nextButton.setEnabled(index < gameStates.size() - 1);
  }

  private void updateGamePanel() {
    this.remove(gameStatePanel);
    this.add(gameStates.get(index).toPanel());
    this.revalidate();
  }

  private void saveGameState() throws IOException {
    File fileToSave;
    JFrame parentFrame = new JFrame();
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to save");
    int userSelection = fileChooser.showSaveDialog(parentFrame);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
      fileToSave = fileChooser.getSelectedFile();
      FileWriter fileWriter = new FileWriter(fileToSave);
      PrintWriter printWriter = new PrintWriter(fileWriter);
      printWriter.write(this.gameStates.get(index).toJSON().toString());
      printWriter.close();
      fileWriter.close();
    }
  }
}