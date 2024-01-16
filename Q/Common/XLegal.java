package Common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonStreamParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Check if the player's placement is legal.
 * Takes JPub (referee knowledge of the state of the game) and JPlacements as STD IN
 * and outputs false or the newly constructed JMap if the placement is legal.
 */
//public class XLegal {
//
//  /**
//   * The main function that reads in Json STDIN and out put either a JsonBoolean or a JMap which
//   * is a JsonArray.
//   *
//   * @param args
//   * @throws IOException
//   */
//  public static void main(String[] args) throws IOException {
//
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    JsonStreamParser jsonStreamParser = new JsonStreamParser(reader);
//
//    while (jsonStreamParser.hasNext()) {
//      JsonObject jPub = jsonStreamParser.next().getAsJsonObject();
//      JsonArray jPlacements = jsonStreamParser.next().getAsJsonArray();
//
//      HashMap<Posn, Tile> placements = jPlacementsToHashMap(jPlacements);
//      List<Posn> placementsOrder = jPlacementsOrder(jPlacements);
//
//      JsonArray jMap = jPub.getAsJsonArray("map");
//      HashMap<Posn, Tile> tiles = jMapToHashmap(jMap);
//      Map gameMap = new Map(tiles);
//
//      int refereePileTilesNum = jPub.get("tile*").getAsInt();
//
//      JsonObject jPlayer = jPub.getAsJsonArray("players").get(0).getAsJsonObject();
//      PlayerState activePlayer = jPlayerToPlayerState(jPlayer);
//
//      game_state gameState = new game_state(gameMap, refereePileTilesNum, activePlayer);
//      System.out.println(isPlacementLegal(gameState, placements, placementsOrder));
//    }
//    reader.close();
//  }
//
//  /**
//   * Checks to see if all placements are legal and returns an JsonElement if they are.
//   *
//   * @param gameState the game state the board is being rendered off of
//   * @param placements the placements the player makes on the board to the game state
//   * @param placementsOrder the order in which the player places the tiles
//   * @return can either return a JsonPrimitive data type, false, or a JMap if all placements are legal
//   */
//  protected static JsonElement isPlacementLegal(Game_State gameState, HashMap<Posn, Tile> placements, List<Posn> placementsOrder) {
//    for (Posn placementPosn : placementsOrder) {
//      try {
//        Posn posn = placementPosn;
//        Tile tile = placements.get(placementPosn);
//        gameState.placeTile(tile, posn);
//      } catch(Exception e) {
//        return new JsonPrimitive(false);
//      }
//    }
//    return (makeJMap(gameState.getCurrentMap()));
//  }
//
//  /**
//   * Generates the JMap from the given Json STDIN
//   *
//   * @param mapTiles the current tiles on the Map
//   * @return the Json STDIN format of the Map
//   */
//  protected static JsonArray makeJMap(HashMap<Posn, Tile> mapTiles) {
//    List<Posn> sortedPosns = new ArrayList<Posn>();
//    for (java.util.Map.Entry<Posn, Tile> entry : mapTiles.entrySet()) {
//      sortedPosns.add(entry.getKey());
//    }
//
//    Collections.sort(sortedPosns, new Posn.PosnComparator());
//
//    JsonArray jMap = new JsonArray();
//    int currRowIdx = sortedPosns.get(0).getRow();
//    JsonPrimitive jRowIdx = new JsonPrimitive(currRowIdx);
//    JsonArray jRow = new JsonArray();
//    jRow.add(jRowIdx);
//    jMap.add(jRow);
//
//    for (int i = 0 ; i < sortedPosns.size(); i ++) {
//      // new row
//      if(sortedPosns.get(i).getRow() != currRowIdx) {
//        currRowIdx = sortedPosns.get(i).getRow();
//        jRowIdx = new JsonPrimitive(currRowIdx);
//        jRow = new JsonArray();
//        jRow.add(jRowIdx);
//        // add the new row to the jMap
//        jMap.add(jRow);
//      }
//
//      // adding cell
//      JsonArray jCell = new JsonArray();
//      // add column idx
//      int colIdx = sortedPosns.get(i).getCol();
//      jCell.add(colIdx);
//      // add jTile
//      JsonObject jTile = tileToJTile(mapTiles.get(new Posn(currRowIdx, colIdx)));
//      jCell.add(jTile);
//      // add jCell to jRow
//      jRow.add(jCell);
//    }
//    return jMap;
//  }
//
//  /**
//   * Coverts a Tile to JsonObject JTile
//   *
//   * @param tile the Tile object that will be converted into a JsonObject, the JTile
//   * @return returns the converted Tile
//   */
//  private static JsonObject tileToJTile(Tile tile) {
//    JsonObject jTile = new JsonObject();
//    jTile.add("color", new JsonPrimitive(tile.getColor().tileColorString()));
//    jTile.add("shape", new JsonPrimitive(tile.getShape().tileToShape()));
//    return jTile;
//  }
//
//  /**
//   * Coverts jPlacements to a HashMap<Posn, Tile> placements that represents the sequence of
//   * placements that the active player wishes to perform.
//   *
//   * @param jPlacements the placements the user
//   * @return
//   * @throws RuntimeException
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
//      Posn coordinate = jCoordinateToPosn(placement.getAsJsonObject("coordinate"));
//      Tile tile = jTileToTile(placement.getAsJsonObject("1tile"));
//
//      placementsOrder.add(coordinate);
//    }
//    return placementsOrder;
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
//
//  /**
//   * Converts the given jTile to a Tile with tileShape and tileColor.
//   *
//   * @param jTile converting the Json Object, jTile to a Tile
//   */
//  private static Tile jTileToTile(JsonObject jTile) {
//      TileColor tileColor = TileColor.fromString(jTile.get("color").getAsString());
//      TileShape tileShape = TileShape.fromString(jTile.get("shape").getAsString());
//
//      return new Tile(tileShape, tileColor);
//  }
//
//
//  /**
//   * Converts the given jMap to a HashMap<Posn, Tile> tiles that represents the map of this game.
//   *
//   * @param jMap converting the JsonObject to a Map
//   */
//  public static HashMap<Posn, Tile> jMapToHashmap(JsonArray jMap) {
//      HashMap<Posn, Tile> tiles = new HashMap<Posn, Tile>();
//      for (JsonElement jRowElement : jMap) {
//        JsonArray jRow = jRowElement.getAsJsonArray();
//        int rowIdx = jRow.get(0).getAsInt();
//        for (int i = 1; i<jRow.size(); i++) {
//          JsonArray jCell = jRow.get(i).getAsJsonArray();
//          JsonObject jTile = jCell.get(1).getAsJsonObject();
//
//          int colIdx = jCell.get(0).getAsInt();
//          Posn coordinate = new Posn(rowIdx, colIdx);
//          Tile tile = jTileToTile(jTile);
//
//          tiles.put(coordinate, tile);
//        }
//      }
//      return tiles;
//    }
//
//  /**
//   * Converts jPlayer to PlayerState
//   *
//   * @param jPlayer the JsonObject that will be converted into a Player State
//   * @return the Player State object formed based off of the jPlayer object
//   */
//  private static PlayerState jPlayerToPlayerState(JsonObject jPlayer) {
//    int score = jPlayer.get("score").getAsInt();
//    List<Tile> playerTiles = new ArrayList<>();
//
//    JsonArray jPlayerTiles = jPlayer.getAsJsonArray("tile*");
//    for (JsonElement e : jPlayerTiles) {
//      playerTiles.add(jTileToTile(e.getAsJsonObject()));
//    }
//
//    return new PlayerState(playerTiles, score);
//  }
//}