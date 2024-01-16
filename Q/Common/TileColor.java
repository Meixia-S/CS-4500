package Common;

import java.awt.Color;
import java.util.Comparator;

/**
 * Represents Qwirkle 6 tile colors along with their corresponding
 * String and RGB value.
 */
public enum TileColor {
  RED("red", new Color(234, 51, 35), 1),
  GREEN("green", new Color(117, 251, 76), 2),
  BLUE("blue", new Color(0, 0, 245), 3),
  YELLOW("yellow", new Color(255, 255, 84), 4),
  ORANGE("orange", new Color(242, 169, 59), 5),
  PURPLE("purple", new Color(147, 44, 231), 6);

  // String parsed from BObj
  private final String colorName;
  private final Color color;
  private final int colorOrder;

  TileColor(String colorName, Color color, int colorOrder) {
    this.colorName = colorName;
    this.color = color;
    this.colorOrder = colorOrder;
  }


  /**
   * Converts the given colorName to corresponding enum value.
   * @param colorName parsed from a BObj
   * @return colorName's corresponding enum value
   */
  public static TileColor fromString(String colorName) {
    for (TileColor color : TileColor.values()) {
      if (color.colorName.equalsIgnoreCase(colorName)) {
        return color;
      }
    }
    throw new IllegalArgumentException("No TileColor named " + colorName);
  }

  public String tileColorString() {
    return this.colorName;
  }


  /**
   * comparator that helps to compare color order.
   */
  public static class TileColorComparator implements Comparator<TileColor> {
    @Override
    public int compare(TileColor color1, TileColor color2) {
      return color1.colorOrder - color2.colorOrder;
    }
  }
}