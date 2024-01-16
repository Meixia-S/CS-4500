package Player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import Common.Posn;
import Common.Tile;

/**
 * The information a Player sends to the Referee during their turn.
 * The fields are:
 *        - Move the action the players wants to make during their turn
 *        - playableTiles the tiles the player wants to place
 *        - currentScore the current score of the player
 * Tile and Coordinate are only for testing
 */
public class Player_Input {
  private Move move;
  private LinkedHashMap<Posn, Tile> playableTiles;
  private Posn coordinate;
  private Tile tile;

  /**
   * Player wants to place their tiles.
   * @param move type of move they want to make (place)
   * @param playableTiles the tiles the player wants to place on the board
   */
  public Player_Input(Move move, LinkedHashMap<Posn,Tile> playableTiles) {
    if(move == Move.PLACE) {
      this.move = move;
      this.playableTiles = playableTiles;
    } else {
      throw new IllegalArgumentException("Must chose the Place inorder to pass the playableTiles hashmap");
    }
  }

  /**
   * Player wants to place their tiles. This constructor is for Testing Purposes Only
   * @param move type of move they want to make
   * @param posn the position the player want to place the tile
   * @param tile the tile the player wants to place
   */
  public Player_Input(Move move, Posn posn, Tile tile) {
    if(move == Move.PLACE) {
      this.move = move;
      this.coordinate = posn;
      this.tile = tile;
    } else {
      throw new IllegalArgumentException("Must choose the Place and make 1 placement");
    }
  }

  /**
   * Player wants to place their tiles.
   * @param move type of move they want to make (exchange or pass)
   */
  public Player_Input(Move move) {
    if(move != Move.EXCHANGE && move != Move.PASS) {
      throw new IllegalArgumentException("Must choose the Exchange or Pass ENUM inorder to pass the Tiles array");
    } else {
      this.move = move;
    }
  }

  /**
   * Following two methods are getters to retrieve the player Move and playable tiles, respectively
   */
  public Move getPlayerMove() {
    return this.move;
  }

  public LinkedHashMap<Posn, Tile> getPlayableTiles() { return new LinkedHashMap<Posn, Tile>(this.playableTiles); }

  /**
   * @return JsonElement (jChoice) by converting this Player_Input
   */
  public JsonElement playerInputTojChoice() {
    return switch (this.move) {
      case PASS -> new JsonPrimitive("pass");
      case EXCHANGE -> new JsonPrimitive("replace");
      case PLACE -> placementsTojPlacements();
    };
  }

  /**
   * @return JsonArray (jPlacement) by converting this Player_Input
   */
  public JsonArray placementsTojPlacements() {
    JsonArray jPlacements = new JsonArray();
    for(Map.Entry<Posn, Tile> entry : this.playableTiles.entrySet()) {
      JsonObject placement = new JsonObject();
      placement.add("coordinate", entry.getKey().posnToJPosn());
      placement.add("1tile", entry.getValue().tileTojTile());
      jPlacements.add(placement);
    }
    return jPlacements;
  }
}