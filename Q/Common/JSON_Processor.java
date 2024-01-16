package Common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.*;

import static Common.PlayerState.jPlayerToPlayerState;

public class JSON_Processor {

  /**
   * Converts the given jMap to a HashMap<Posn, Tile> tiles that represents the map of this game.
   *
   * @param jMap
   */
  public static HashMap<Posn, Tile> jMapToHashmap(JsonArray jMap) {
    HashMap<Posn, Tile> tiles = new HashMap<Posn, Tile>();
    for (JsonElement jRowElement : jMap) {
      JsonArray jRow = jRowElement.getAsJsonArray();
      int rowIdx = jRow.get(0).getAsInt();
      for (int i = 1; i < jRow.size(); i++) {
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
   * @param mapTiles the current tiles on the Map
   * @return the Json STDIN format of the Map
   */
  public static JsonArray makeJMap(HashMap<Posn, Tile> mapTiles) {
    List<Posn> sortedPosns = new ArrayList<Posn>();
    for (java.util.Map.Entry<Posn, Tile> entry : mapTiles.entrySet()) {
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
      JsonElement jTile = mapTiles.get(new Posn(currRowIdx, colIdx)).tileTojTile();
      jCell.add(jTile);
      // add jCell to jRow
      jRow.add(jCell);
    }
    return jMap;
  }

  /**
   * @return a tile that has been converted the given jTile to a Tile with tileShape and tileColor.
   */
  public static Tile jTileToTile(JsonObject jTile) {
    TileColor tileColor = TileColor.fromString(jTile.get("color").getAsString());
    TileShape tileShape = TileShape.fromString(jTile.get("shape").getAsString());

    return new Tile(tileShape, tileColor);
  }

  /**
   * @return the list of tiles that where converted from jTiles
   */
  public static List<Tile> jTilesToTiles(JsonArray jTiles) {
    List<Tile> tiles = new ArrayList<>();
    for (JsonElement je : jTiles) {
      tiles.add(jTileToTile(je.getAsJsonObject()));
    }
    return tiles;
  }

  /**
   * @return a posn by converting the given jPosn (JsonObject)
   */
  public static Posn jCoordinateToPosn(JsonObject jCoordinate) {
    int row = jCoordinate.get("row").getAsInt();
    int col = jCoordinate.get("column").getAsInt();

    return new Posn(row, col);
  }

  /**
   * @return the JsonArray of JTiles converted from an array of tiles
   */
  public static JsonArray TilesToJTiles(List<Tile> tiles) {
    JsonArray jTiles = new JsonArray();
    for (Tile t : tiles) {
      jTiles.add(t.tileTojTile());
    }
    return jTiles;
  }

  /**
   * @return returns a linkedHashMap of posn and tiles from the given jPlacement (JsonElement)
   */
  public static LinkedHashMap<Posn, Tile> jPlacementToPlacement(JsonElement je) {
    JsonArray placementArray;
    try {
      placementArray = (JsonArray) je;
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("not a json array");
    }

    LinkedHashMap<Posn, Tile> placeableTiles = new LinkedHashMap<>();
    for (JsonElement placement : placementArray) {
      try {
        JsonObject jo = (JsonObject) placement;
        ensurejPlacement(jo);
        placeableTiles.put(jCoordinateToPosn((JsonObject) jo.get("coordinate")),
                jTileToTile((JsonObject) jo.get("1tile")));
      } catch (ClassCastException | IllegalArgumentException e) {
        throw new IllegalStateException("placeable array is not formatted correctly");
      }
    }
    return placeableTiles;
  }

  /**
   * @return Public_Info that was created by converting a JsonObject (jPub)
   */
  public static Public_Info jPubtoPublicInfo(JsonObject jp) {
    if (!validatejPub(jp)) {
      throw new IllegalArgumentException("jPub is not well formed");
    }
    Map map = new Map(jMapToHashmap(jp.get("map").getAsJsonArray()));
    int refRemainingTiles = jp.get("tile*").getAsInt();
    JsonArray playerArray = jp.get("players").getAsJsonArray();
    PlayerState playerState = jPlayerToPlayerState(playerArray.get(0).getAsJsonObject());
    List<Integer> playerScores = playerArray.asList()
            .subList(1, playerArray.size())
            .stream()
            .map(JsonElement::getAsInt)
            .toList();

    return new Public_Info(map.getTiles(), refRemainingTiles, playerState, playerScores);
  }

  private static boolean validatejPub(JsonObject jpub) {
    return jpub.keySet().contains("map")
            && jpub.keySet().contains("tile*")
            && (jpub.get("tile*").getAsInt() >= 0)
            && jpub.keySet().contains("players")
            && jpub.keySet().size() == 3;
  }

  /**
   * To check if the given JsonObject is a jPlacement
   */
  private static void ensurejPlacement(JsonObject jo) {
    if (!jo.has("coordinate") || !jo.has("1tile")) {
      throw new IllegalArgumentException("the jsonObject is not a jPlacement");
    }
  }
}