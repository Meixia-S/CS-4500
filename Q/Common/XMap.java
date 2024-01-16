package Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Consumes two JSON values: a JMap object followed by a JTile from STD IN then
 * sends an array of JCoordinates, listing all JCoordinates where this JTile tile
 * could be added to the given JMap in row-column sorted order, to STD OUT.
 */
//public class XMap {

//    public static void main(String[] args) throws IOException {
//
//      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//      JsonStreamParser jsonStreamParser = new JsonStreamParser(reader);
//
//      while (jsonStreamParser.hasNext()) {
//        JsonArray jMap = jsonStreamParser.next().getAsJsonArray();
//        JsonObject jTile = jsonStreamParser.next().getAsJsonObject();
//
//        HashMap<Posn, Tile> tiles = jMapToHashmap(jMap);
//        Map map = new Map(tiles);
//        List<Posn> posns = map.getPossibleMoves(jTileToTile(jTile));
//        Collections.sort(posns, new Posn.PosnComparator());
//
//        System.out.println(ListPosnToJCoordinates(posns));
//      }
//
//      reader.close();
//    }

//  /**
//   * Converts the given jMap to a HashMap<Posn, Tile> tiles that represents the map of this game.
//   * @param jMap
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
//
//      return tiles;
//  }
//
//  /**
//   * Converts the given jTile to a Tile with tileShape and tileColor.
//   */
//  public static Tile jTileToTile(JsonObject jTile) {
//      TileColor tileColor = TileColor.fromString(jTile.get("color").getAsString());
//      TileShape tileShape = TileShape.fromString(jTile.get("shape").getAsString());
//
//      return new Tile(tileShape, tileColor);
//  }

  /**
   * Converts a list of Posns to a JsonArray of JCoordinates.
   * @param posns
   */
/*  public static JsonArray ListPosnToJCoordinates(List<Posn> posns) {
      JsonArray jCoordinates = new JsonArray();
      for (Posn p : posns) {
        JsonObject jCoordinate = new JsonObject();
        jCoordinate.add("column", new JsonPrimitive(p.getCol()));
        jCoordinate.add("row", new JsonPrimitive(p.getRow()));

        jCoordinates.add(jCoordinate);
      }
      return jCoordinates;
    }*/
//}
