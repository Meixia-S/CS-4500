package Common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Renders the visual representation of the game state at any given moment of the game
 * The fields are:
 *  map: the current map of the board
 *  rowNum: the number of rows on the board
 *  colNum: the number of columns on the board
 *  mainFrame: the frame that houses the mainPanel
 *  mainPanel: the panel that houses the gird board layout
 */
public class Game_State_GUI {
  private final Map map;
  private int rowNum;
  private int colNum;
  private int refereeTilePile;
  private  List<PlayerState> playerStates;
  private JPanel mapPanel;
  private JPanel playerPanel;

  public Game_State_GUI(Map map, int refereeTilePile, int rowNum, int colNum, List<PlayerState> playerStates) {
    this.map = map;
    this.refereeTilePile = refereeTilePile;
    this.rowNum = rowNum;
    this.colNum = colNum;
    this.playerStates = playerStates;
    this.mapPanel = new JPanel();
    this.playerPanel = new JPanel();
  }

  /**
   * The main method that sets of the JFrame and JPanels that makeup the visual representation of the
   * board
   */
  public JPanel buildPanel() {
    Color c0 = new Color(162, 232, 245);
    Color c1 = new Color(255, 240, 230);
    Color c2 = new Color(209, 237, 242);
    Font f1 = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    int width = 400;
    int height = 400;

    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(c0);
    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints gridContraints = new GridBagConstraints();
    gridContraints.fill = GridBagConstraints.HORIZONTAL;

    if (rowNum > 8 && colNum > 8) {
      height = 1000;
    } else if (rowNum > 8 && colNum < 8) {
      height = 1000;
    } else if (rowNum < 8 && colNum > 8) {
      width = 1100;
    }

    playerPanel.setPreferredSize(new Dimension(300, height));
    playerPanel.setLayout(new GridLayout(9, 1));

    //JPanel playerInfoDivider
    JTextArea player1Text = new JTextArea(1, 2);
    player1Text.setBackground(c1);
    player1Text.setFont(f1);
    player1Text.setText(textAreaHelper(0));
    player1Text.setEditable(false);
    playerPanel.add(player1Text);
    JPanel player1Panel = new JPanel();
    playerInventoryHelper(0, player1Panel);
    playerPanel.add(player1Panel);

    JTextArea player2Text = new JTextArea(1, 2);
    player2Text.setBackground(c1);
    player2Text.setFont(f1);
    player2Text.setText(textAreaHelper(1));
    player2Text.setEditable(false);
    playerPanel.add(player2Text);
    JPanel player2Panel = new JPanel();
    playerInventoryHelper(1, player2Panel);
    playerPanel.add(player2Panel);

    JTextArea player3Text = new JTextArea(1, 10);
    player3Text.setBackground(c1);
    player3Text.setFont(f1);
    player3Text.setText(textAreaHelper(2));
    player3Text.setEditable(false);
    playerPanel.add(player3Text);
    JPanel player3Panel = new JPanel();
    playerInventoryHelper(2, player3Panel);
    playerPanel.add(player3Panel);

    JTextArea player4Text = new JTextArea(1, 10);
    player4Text.setBackground(c1);
    player4Text.setFont(f1);
    player4Text.setText(textAreaHelper(3));
    player4Text.setEditable(false);
    playerPanel.add(player4Text);
    JPanel player4Panel = new JPanel();
    playerInventoryHelper(3, player4Panel);
    playerPanel.add(player4Panel);

    JTextArea refereeInfo = new JTextArea(1, 20);
    refereeInfo.setBackground(c1);
    refereeInfo.setFont(f1);
    refereeInfo.setText(" Referee Tile Pile Count: " + this.refereeTilePile);
    playerPanel.add(refereeInfo);

    mainPanel.add(playerPanel);

    mapPanel = new JPanel(new GridLayout(rowNum, colNum));
    mapPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
    mapPanel.setPreferredSize(new Dimension(width, height));
    mapPanel.setBackground(c2);
    mapPanel = mapDisplayHelper();
    mainPanel.add(mapPanel);

    return mainPanel;
  }

  /**
   * Renders player inventory.
   * @param index
   * @param playerPanel
   * @return
   * @throws IOException
   */
  private JPanel playerInventoryHelper(int index, JPanel playerPanel) {
    Tile tile;
    String color;
    String shape;
    BufferedImage image;

    if(this.playerStates.size() - 1 < index) {
      return playerPanel;
    }

    for (int i = 0; i < this.playerStates.get(index).getPlayerTiles().size(); i++) {
      tile = this.playerStates.get(index).getPlayerTiles().get(i);
      color = tile.getColor().toString().toLowerCase();
      shape = tile.getShape().toString().toLowerCase();
      if (shape.equals("eight_star")) {
        shape = "8star";
      }
      try {
        image = ImageIO.read(new File("Q/Images/" + color + "_" + shape + ".png"));
        Image imgScale = image.getScaledInstance(25, 25, 10);
        ImageIcon imageIcon = new ImageIcon(imgScale);
        playerPanel.add(new JLabel(imageIcon));
      }
      catch (Exception e) {
        System.out.println("Image reading failed.");
      }
    }
    return playerPanel;
  }

  /**
   * @return the panel that represents map
   * @throws IOException
   */
  private JPanel mapDisplayHelper() {
    Posn pos;
    Tile tile;
    String color;
    String shape;
    BufferedImage image;

    for (int r = 0; r < rowNum; r++) {
      for (int c = 0; c < colNum; c++) {
        pos = new Posn(r, c);
        if (this.map.getTiles().containsKey(pos)) {
          tile = this.map.getTileAtPosn(pos);
          color = tile.getColor().toString().toLowerCase();
          shape = tile.getShape().toString().toLowerCase();
          if (shape.equals("eight_star")) {
            shape = "8star";
          }
          try {
            image = ImageIO.read(new File("Q/Images/" + color + "_" + shape + ".png"));
            Image imgScale = image.getScaledInstance(50, 50, 10);
            ImageIcon imageIcon = new ImageIcon(imgScale);
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            mapPanel.add(new JLabel(imageIcon));
          } catch (Exception e) {
            System.out.println("Image reading failed.");
          }
        } else {
          try {
            image = ImageIO.read(new File("Q/Images/blank_space.png"));
            Image imgScale = image.getScaledInstance(50, 50, 10);
            ImageIcon imageIcon = new ImageIcon(imgScale);
            mapPanel.add(new JLabel(imageIcon));
          } catch (Exception e) {
            System.out.println("Image reading failed.");
          }
        }
      }
    }
    return mapPanel;
  }

  /**
   * Helper that shows text areas that helps to represent players
   * @param index
   * @return
   */
  private String textAreaHelper(int index) {
    String text = "";
    if (this.playerStates.size() - 1 < index) {
      switch (index) {
        case 0:
          text = " - No First Player - ";
          break;
        case 1:
          text = " - No Second Player - ";
          break;
        case 2:
          text = " - No Third Player - ";
          break;
        case 3:
          text = " - No Fourth Player - ";
          break;
      }
    } else {
      text = this.playerStates.get(index).getName() + "             Score:" + this.playerStates.get(index).getPlayerScore();
    }
    return text;
  }
}