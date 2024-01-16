//package Player;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonPrimitive;
//import com.google.gson.JsonStreamParser;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import Common.Map;
//import Common.PlayerState;
//import Common.Posn;
//import Common.Tile;
//import Common.TileColor;
//import Common.TileShape;
//import Common.game_state;
//
///**
// * Represents a test harness named xstrategy.
// * The harness consumes JStrategy designation followed by a JPub object from std in
// * and an JAction produces its results to STDOUT
// */
//public class XStrategy {
//    public static void main(String[] args) throws IOException {
//
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    JsonStreamParser jsonStreamParser = new JsonStreamParser(reader);
//
//    while (jsonStreamParser.hasNext()) {
//      JsonPrimitive jStrategy = jsonStreamParser.next().getAsJsonPrimitive();
//      JsonObject jPub = jsonStreamParser.next().getAsJsonObject();
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
//      String usedStrategy = jStrategy.getAsString();
//
//      Player_Input playerAction = getPlayerAction(usedStrategy, gameMap, refereePileTilesNum, activePlayer);
//
//      // std out
//      if (playerAction.getPlayerMove() == Move.PASS) {
//        System.out.println("\"pass\"");
//      } else if (playerAction.getPlayerMove() == Move.EXCHANGE) {
//        System.out.println("\"replace\"");
//      } else {
//        System.out.println(playerAction.getPlayableTiles());
//      }
//    }
//
//    reader.close();
//  }
//
//  /**
//   * Get the player action using the given strategy
//   * @param usedStrategy
//   * @param map
//   * @param refereePileTilesNum
//   * @param activePlayer
//   * @return
//   */
//  protected static Player_Input getPlayerAction(String usedStrategy, Map map, int refereePileTilesNum,
//                                         PlayerState activePlayer) {
//      strategy strategy;
//      if (usedStrategy.equals("dag")) {
//        strategy = new DagStrategy(map, refereePileTilesNum, activePlayer);
//      } else if (usedStrategy.equals("ldasg")) {
//        strategy = new LdasgStrategy(map, refereePileTilesNum, activePlayer);
//      } else {
//        throw new IllegalArgumentException("wrong strategy");
//      }
//
//      return strategy.determineMoveIteration();
//  }
//
//  /**
//   * Converts the given jMap to a HashMap<Posn, Tile> tiles that represents the map of this game.
//   * @param jMap
//   */
//  public static HashMap<Posn, Tile> jMapToHashmap(JsonArray jMap) {
//    HashMap<Posn, Tile> tiles = new HashMap<Posn, Tile>();
//    for (JsonElement jRowElement : jMap) {
//      JsonArray jRow = jRowElement.getAsJsonArray();
//      int rowIdx = jRow.get(0).getAsInt();
//      for (int i = 1; i<jRow.size(); i++) {
//        JsonArray jCell = jRow.get(i).getAsJsonArray();
//        JsonObject jTile = jCell.get(1).getAsJsonObject();
//
//        int colIdx = jCell.get(0).getAsInt();
//        Posn coordinate = new Posn(rowIdx, colIdx);
//        Tile tile = jTileToTile(jTile);
//
//        tiles.put(coordinate, tile);
//      }
//    }
//
//    return tiles;
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
//
//  /**
//   * Converts to 1Placement.
//   * @param posn
//   * @param tile
//   * @return
//   */
//  private static JsonObject make1Placement(Posn posn, Tile tile) {
//    JsonObject placement = new JsonObject();
//
//    placement.add("coordinate", posnToJCoordinate(posn));
//    placement.add("1tile", tileToJTile(tile));
//
//    return placement;
//  }
//
//  /**
//   * Creates a JCoordinate
//   * @param posn
//   * @return
//   */
//  private static JsonObject posnToJCoordinate(Posn posn) {
//    JsonObject jCoordinate = new JsonObject();
//    jCoordinate.add("row", new JsonPrimitive(posn.getRow()));
//    jCoordinate.add("column", new JsonPrimitive(posn.getCol()));
//
//    return jCoordinate;
//  }
//
//  /**
//   * Creates a JTile.
//   * @param tile
//   * @return
//   */
//  private static JsonObject tileToJTile(Tile tile) {
//    JsonObject jTile = new JsonObject();
//    jTile.add("color", new JsonPrimitive(tile.getColor().tileColorString()));
//    jTile.add("shape", new JsonPrimitive(tile.getShape().tileToShape()));
//
//    return jTile;
//  }
//}
