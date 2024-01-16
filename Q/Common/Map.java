package Common;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static Common.Tile.jTileToTile;

/**
 * Represents an arrangement of continuous Tiles for the Q Game. Tiles placed must always
 * share at least one edge (left, right, top, bottom) with an existing Tile on the map.
 */
public class Map {

  /**
   * Represents Tiles, with their location, on the board.
   *
   * The origin is at (0,0)
   * Tiles left of the origin have - col
   * Tiles right of the origin have + col
   * Tiles below of the origin have + row
   * Tiles above of the origin have - row
   */
  private HashMap<Posn, Tile> tiles;

  /**
   * Initializes the game map with the referee Tile at the origin (0,0)
   * @param refTile
   */
  public Map(Tile refTile) {
    this.tiles = new HashMap<Posn, Tile>();
    this.tiles.put(new Posn(0,0), refTile);
  }

  /**
   * Initializes the game map the given hashmap of coordinates and tiles
   * @param tiles
   */
  public Map(HashMap<Posn, Tile> tiles) {
    this.tiles = tiles;
  }

  /**
   * Extends this Map with tiles at the given coordinate such that it shares a side with
   * an existing Tile, regardless of the color or shape of either one;
   * @throws IllegalArgumentException if tile doesn't share at least one side with existing tile
   */
  public void extendMap(Tile newTile, Posn coordinate) throws IllegalArgumentException {
    if (this.isAdjacent(coordinate) && !this.tiles.containsKey(coordinate)) {
      this.tiles.put(coordinate, newTile);
    }
    else {
      throw new IllegalArgumentException("Cannot place Tile at " + coordinate + ".\n");
    }
  }

  /**
   * Determines all those places where the given Tile can be inserted on this map so that it fits
   * according to the matching rules of The Q Game.
   * @return List of Posns of where newTile can be placed
   */
  public List<Posn> getPossibleMoves(Tile newTile) {
    List<Posn> result = new ArrayList<Posn>();

    for (Posn tilePosn: this.tiles.keySet()) {
      for (Posn neighbor: tilePosn.adjacentPosns()) {
        if (!this.isThereTileAtPosn(neighbor)
                && this.matchesNeighbors(newTile, neighbor)
                && (!result.contains(neighbor))) { // is it not in the result set?
          result.add(neighbor);
        }
      }
    }
    return result;
  }

  public List<Posn> allOpenNeighbors() {
    List<Posn> result = new ArrayList<Posn>();

    for (Posn tilePosn: this.tiles.keySet()) {
      for (Posn neighbor: tilePosn.adjacentPosns()) {
        if (!this.isThereTileAtPosn(neighbor)
            && (!result.contains(neighbor))) { // is it not in the result set?
          result.add(neighbor);
        }
      }
    }
    return result;
  }

  /**
   * Can the given Tile be placed at the posn on this map? The tile must match its top
   * and bottom neighbors in shape or color, and match its left and right in shape or color.
   * neighbor in shape or color.
   */
  public boolean matchesNeighbors(Tile tile, Posn posn) {
    return matchesTwoNeighbors(tile, posn.getTop(), posn.getBottom())
            && matchesTwoNeighbors(tile, posn.getLeft(), posn.getRight());
  }

  /**
   * Checks if the given tile matches both of its neighbors' shape or both of its neighbors' color.
   * NOTE: This method will return true if there isn't a tile at neighbor1 and neighbor2
   * @param tile to be checked
   * @param neighbor1 coordinate of neighboring tile
   * @param neighbor2 coordinate of another neighboring tile that is along the same row or col as
   *                  neighbor1
   */
  //TODO: simplify
  private boolean matchesTwoNeighbors(Tile tile, Posn neighbor1, Posn neighbor2) {
    boolean matchesNeighbor1Shape = true;
    boolean matchesNeighbor2Shape = true;
    boolean matchesNeighbor1Color = true;
    boolean matchesNeighbor2Color = true;

    if (this.tiles.containsKey(neighbor1)) {
      matchesNeighbor1Shape = tile.isSameShape(this.tiles.get(neighbor1));
      matchesNeighbor1Color = tile.isSameColor(this.tiles.get(neighbor1));
    }
    if (this.tiles.containsKey(neighbor2)) {
      matchesNeighbor2Shape = tile.isSameShape(this.tiles.get(neighbor2));
      matchesNeighbor2Color = tile.isSameColor(this.tiles.get(neighbor2));
    }

    return (matchesNeighbor1Shape && matchesNeighbor2Shape) || (matchesNeighbor1Color && matchesNeighbor2Color);
  }

  /**
   * Is the given Posn adjacent to a Tile on the board?
   */
  protected boolean isAdjacent(Posn posn) {
    for (Posn neighborPosn : posn.adjacentPosns()) {
      if (this.tiles.containsKey(neighborPosn)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a copy of the current game map.
   */
  public HashMap<Posn, Tile> getTiles() {
    return new HashMap<>(this.tiles);
  }

  /**
   * Is there a tile at the given coordinate (posn) on this map?
   */
  protected boolean isThereTileAtPosn(Posn posn) {
    return this.tiles.containsKey(posn);
  }

  protected Tile getTileAtPosn(Posn posn) throws IllegalArgumentException {
    if (!this.isThereTileAtPosn(posn)) {
      throw new IllegalArgumentException("A tile doesn't at" + posn.toString());
    }
    else {
      return this.tiles.get(posn);
    }
  }

  /**
   * Converts the given jMap to a HashMap<Posn, Tile> tiles that represents the map of this game.
   * @param jMap
   */
  public static HashMap<Posn, Tile> jMapToHashmap(JsonArray jMap) {
    HashMap<Posn, Tile> tiles = new HashMap<Posn, Tile>();
    for (JsonElement jRowElement : jMap) {
      JsonArray jRow = jRowElement.getAsJsonArray();
      int rowIdx = jRow.get(0).getAsInt();
      for (int i = 1; i<jRow.size(); i++) {
        JsonArray jCell = jRow.get(i).getAsJsonArray();
        JsonObject jTile = jCell.get(1).getAsJsonObject();

        int colIdx = jCell.get(0).getAsInt();
        Posn coordinate = new Posn(rowIdx, colIdx);
        Tile tile = jTileToTile(jTile);

        tiles.put(coordinate, tile);
      }
    }
    return tiles;
  }

  /**
   * Generates the JMap from the given Json STDIN
   *
   * @return the Json STDIN format of the Map
   */
  public JsonArray mapTojMap() {
    List<Posn> sortedPosns = new ArrayList<Posn>();
    for (java.util.Map.Entry<Posn, Tile> entry : this.tiles.entrySet()) {
      sortedPosns.add(entry.getKey());
    }

    Collections.sort(sortedPosns, new Posn.PosnComparator());

    JsonArray jMap = new JsonArray();
    int currRowIdx = sortedPosns.get(0).getRow();
    JsonPrimitive jRowIdx = new JsonPrimitive(currRowIdx);
    JsonArray jRow = new JsonArray();
    jRow.add(jRowIdx);
    jMap.add(jRow);

    for (int i = 0 ; i < sortedPosns.size(); i ++) {
      // new row
      if(sortedPosns.get(i).getRow() != currRowIdx) {
        currRowIdx = sortedPosns.get(i).getRow();
        jRowIdx = new JsonPrimitive(currRowIdx);
        jRow = new JsonArray();
        jRow.add(jRowIdx);
        // add the new row to the jMap
        jMap.add(jRow);
      }

      // adding cell
      JsonArray jCell = new JsonArray();
      // add column idx
      int colIdx = sortedPosns.get(i).getCol();
      jCell.add(colIdx);
      // add jTile
      JsonElement jTile = this.tiles.get(new Posn(currRowIdx, colIdx)).tileTojTile();
      jCell.add(jTile);
      // add jCell to jRow
      jRow.add(jCell);
    }
    return jMap;
  }
}