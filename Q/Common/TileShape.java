package Common;

import java.util.Comparator;

/**
 * Represents the Qwirkle game's 6 tile shapes and their corresponding
 * String representation.
 */
public enum TileShape {
  STAR("star", 1),
  EIGHT_STAR("8star", 2),
  SQUARE("square", 3),
  CIRCLE("circle", 4),
  CLOVER("clover", 5),
  DIAMOND("diamond", 6);


  // String parsed from BObj
  private final String shapeName;
  private final int shapeOrder;

  TileShape(String shapeName, int shapeOrder) {
    this.shapeName = shapeName;
    this.shapeOrder = shapeOrder;
  }

  /**
   * Converts the given shapeName to corresponding enum value.
   * @param shapeName parsed from a BObj
   * @return shapeName's corresponding enum value
   */
  public static TileShape fromString(String shapeName) {
    for (TileShape shape : TileShape.values()) {
      if (shape.shapeName.equalsIgnoreCase(shapeName)) {
        return shape;
      }
    }
    throw new IllegalArgumentException("No TileShape named " + shapeName);
  }

  public String tileShapeString() {
    return this.shapeName;
  }

  /**
   * comparator that helps compare shape order
   */
  public static class TileShapeComparator implements Comparator<TileShape> {
    @Override
    public int compare(TileShape shape1, TileShape shape2) {
      return shape1.shapeOrder - shape2.shapeOrder;
    }
  }
}