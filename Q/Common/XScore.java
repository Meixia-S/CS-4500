package Common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static Common.JSON_Processor.jMapToHashmap;

/**
 * Computes the score after player takes turn.
 * The inputs of xscore consist of the two JSON values:
 * a JMap object, which describes the current state of the game map;
 * a JPlacements, which describes the placement that produced this state of affairs.
 *
 * The expected output is the score that this placement gathers
 */
//public class XScore {
//  public static void main(String[] args) throws IOException {
//
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    JsonStreamParser jsonStreamParser = new JsonStreamParser(reader);
//
//    while (jsonStreamParser.hasNext()) {
//      JsonArray jMap = jsonStreamParser.next().getAsJsonArray();
//      JsonArray jPlacements = jsonStreamParser.next().getAsJsonArray();
//
//      HashMap<Posn, Tile> tiles = jMapToHashmap(jMap);
//      Map map = new Map(tiles);
//
//      HashMap<Posn, Tile> placements = getPlacements(jPlacements);
//
//      game_state gameState = new game_state(map, placements);
//      int score = gameState.turnScore();
//
//      System.out.print(score);
//    }
//
//    reader.close();
//  }
//
//  /**
//   * Converts the given jTile to a Tile with tileShape and tileColor.
//   */
//  public static Tile jTileToTile(JsonObject jTile) {
//    TileColor tileColor = TileColor.fromString(jTile.get("color").getAsString());
//    TileShape tileShape = TileShape.fromString(jTile.get("shape").getAsString());
//
//    return new Tile(tileShape, tileColor);
//  }
//
//  /**
//   * Coverts jPlacements to a HashMap<Posn, Tile> placements that represents the sequence of
//   * placements that the active player wishes to perform.
//   *
//   * @param jPlacements the placements the user
//   * @throws RuntimeException
//   * TODO figure out why placements are sorted
//   */
//  protected static HashMap<Posn, Tile> jPlacementsToHashMap(JsonArray jPlacements) throws RuntimeException {
//    HashMap<Posn, Tile> placements = new HashMap<>();
//    List<Posn> placementsOrder = new ArrayList<Posn>();
//    for (JsonElement e : jPlacements) {
//      JsonObject placement = e.getAsJsonObject();
//      Posn coordinate = jCoordinateToPosn(placement.getAsJsonObject("coordinate"));
//      Tile tile = jTileToTile(placement.getAsJsonObject("1tile"));
//
//      placements.put(coordinate, tile);
//      placementsOrder.add(coordinate);
//    }
//    return placements;
//  }
//
//  /**
//   * Converts jPlacements when the order of placements
//   * @param jPlacements
//   */
//  protected static List<Posn> jPlacementsOrder(JsonArray jPlacements) {
//    List<Posn> placementsOrder = new ArrayList<Posn>();
//    for (JsonElement e : jPlacements) {
//      JsonObject placement = e.getAsJsonObject();
//      Posn coordinate = JSON_Processor.jCoordinateToPosn(placement.getAsJsonObject("coordinate"));
//      Tile tile = jTileToTile(placement.getAsJsonObject("1tile"));
//
//      placementsOrder.add(coordinate);
//    }
//    return placementsOrder;
//  }
//
//  /**
//   * Get original order of placements
//   */
//  protected static HashMap<Posn, Tile> getPlacements(JsonArray jPlacements) {
//    HashMap<Posn, Tile> finalPlacements = new HashMap<>();
//    List<Posn> placementsOrder = jPlacementsOrder(jPlacements);
//    HashMap<Posn, Tile> placements = jPlacementsToHashMap(jPlacements);
//    for (Posn placementPosn : placementsOrder) {
//        Posn posn = placementPosn;
//        Tile tile = placements.get(placementPosn);
//        finalPlacements.put(posn, tile);
//    }
//    return finalPlacements;
//  }
//
//  /**
//   * Converts jCoordinate to Posn
//   * @param jCoordinate
//   */
//  private static Posn jCoordinateToPosn(JsonObject jCoordinate) {
//    int row = jCoordinate.get("row").getAsInt();
//    int col = jCoordinate.get("column").getAsInt();
//
//    return new Posn(row, col);
//  }
//}
