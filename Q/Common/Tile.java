package Common;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a Tile in the Q Game with a TileShape and TileColor.
 */
public final class Tile {

  private final TileShape shape;
  private final TileColor color;
  // coordinates representing this Tile's location on the Map relative to the Referee Tile at (0,0)

  public Tile(TileShape shape, TileColor color) {
    this.shape = shape;
    this.color = color;
  }

  /**
   * @return copy of this tile shape
   */
  public TileShape getShape() {
    return TileShape.valueOf(this.shape.name());
  }

  /**
   * @return copy of this tile color
   */
  public TileColor getColor() {
    return TileColor.valueOf(this.color.name());
  }

  /**
   * @return a copy of this tile
   */
  public Tile getCopy() {
    return new Tile(this.shape, this.color);
  }

  /**
   * @param t
   * @return true if the given tile has the same shape as this tile
   */
  public boolean isSameShape(Tile t) {
    return this.shape.toString().equals(t.getShape().toString());
  }

  /**
   * @return true if the given tile has the same shape as this tile
   * @param t
   */
  public boolean isSameColor(Tile t) {
    return this.color.toString().equals(t.getColor().toString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tile tile = (Tile) o;
    return shape == tile.shape &&
            color == tile.color;
  }

  @Override
  public int hashCode() {
    return Objects.hash(shape, color);
  }



  /**
   *  Compares 2 tiles based on shape and color.
   *  p is less than tile q if p’s shape is less than q’s.
   *  If p’s shape is identical to q’s, p is below q iff p’s color is below q’s.
   */
  public static class TileComparator implements Comparator<Tile> {
    @Override
    public int compare(Tile tile1, Tile tile2) {
      // Compare shapes first
      int shapeComparison = tile1.getShape().compareTo(tile2.getShape());

      // If shapes are the same, compare colors
      if (shapeComparison == 0) {
        return tile1.getColor().compareTo(tile2.getColor());
      } else {
        return shapeComparison;
      }
    }
  }

  /**
   * @return returns a tile by converting the given jTile to a Tile with tileShape and tileColor.
   */
  public static Tile jTileToTile(JsonObject jTile) {
    TileColor tileColor = TileColor.fromString(jTile.get("color").getAsString());
    TileShape tileShape = TileShape.fromString(jTile.get("shape").getAsString());

    return new Tile(tileShape, tileColor);
  }

  /**
   * @return a JsonObject (jTile) by converting this tile
   */
  public JsonObject tileTojTile() {
    JsonObject jTile = new JsonObject();
    jTile.add("color", new JsonPrimitive(this.color.tileColorString()));
    jTile.add("shape", new JsonPrimitive(this.shape.tileShapeString()));
    return jTile;
  }
}
