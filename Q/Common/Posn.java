package Common;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a coordinate with a row and column.
 */
public class Posn {

  private int row;
  private int col;

  public Posn(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * @return a copy of this posn
   */
  public Posn getCopy() {
    return new Posn(this.row, this.col);
  }

  /**
   * Getters for posn
   */
  public int getRow() {
    return this.row;
  }
  public int getCol() {
    return this.col;
  }

  /**
   * Return this tile's neighbors - adjacent tiles
   */
  public Posn[] adjacentPosns() {
    return new Posn[] {new Posn(this.row, this.col - 1),
            new Posn(this.row, this.col + 1),
            new Posn(this.row - 1, this.col),
            new Posn(this.row + 1, this.col)};
  }

  /**
   * Get the right, left, top, bottom position of this posn
   */
  public Posn getRight() {
    return new Posn(this.row, this.col + 1);
  }
  public Posn getLeft() {
    return new Posn(this.row, this.col - 1);
  }
  public Posn getTop() {
    return new Posn(this.row - 1, this.col);
  }
  public Posn getBottom() { return new Posn(this.row + 1, this.col); }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Posn posn = (Posn) o;
    return this.row == posn.getRow() &&
            this.col == posn.getCol();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.row, this.col);
  }

  @Override
  public String toString() {
    return "(R=" + this.row + " , C= " + this.col + ")";
  }

  /**
   * Compares two Posns based on their row and col values. We first compare for the smaller
   * row, then smaller col if they have the same row.
   * p1 < p2 = -1
   * p1 == p2 = 0
   * p2 > p1 = 1
   */
  public static class PosnComparator implements Comparator<Posn> {
    @Override
    public int compare(Posn p1, Posn p2) {
      if (p1.getRow() == p2.getRow()) {
        return p1.getCol() - p2.getCol();
      }
      else {
        return p1.getRow() - p2.getRow();
      }

    }
  }

  /**
   * @return JsonObject (JPosn) that was created by converting a posn
   */
  public JsonObject posnToJPosn() {
    JsonObject jCoordinate = new JsonObject();
    jCoordinate.add("column", new JsonPrimitive(this.col));
    jCoordinate.add("row", new JsonPrimitive(this.row));
    return jCoordinate;
  }
}
