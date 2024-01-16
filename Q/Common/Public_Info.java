package Common;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the public information that the referee will show the active player.
 */
public class Public_Info {
  private PlayerState activePlayer;
  private List<Tile> availableTile; // the active player's tiles
  private List<Integer> playersScores;  // all players' scores
  private int refereeRemainingTiles;
  private HashMap<Posn, Tile> map; // copy of the map

  public Public_Info(PlayerState activePlayer, List<Tile> availableTile,
                     List<Integer> playersScores, int refereeRemainingTiles, HashMap<Posn, Tile> map) {
    this.activePlayer = activePlayer;
    this.availableTile = availableTile;
    this.playersScores = playersScores;
    this.refereeRemainingTiles = refereeRemainingTiles;
    this.map = map;
  }

  public Public_Info(HashMap<Posn, Tile> map, int refereeRemainingTiles, PlayerState activePlayer,
      List<Integer> playerScores) {
    this(activePlayer, activePlayer.getPlayerTiles(), playerScores, refereeRemainingTiles, map);
  }

  // Public Information when setting up the game
  // the player is handed the initial map, which is visible to all,
  // plus an initial set of tiles
  public Public_Info(HashMap<Posn, Tile> map, List<Tile> availableTile) {
    this.map = map;
    this.availableTile = availableTile;
  }

  /**
   * Getters for the public info.
   */
  public HashMap<Posn, Tile> getMap() {
    return map;
  }

  public int getRefereeTileNum() {
    return refereeRemainingTiles;
  }

  public PlayerState getActivePlayer() {
    return activePlayer;
  }

  /**
   * @return JsonObject (jPub) that was created by converting this Public_Info
   */
  public JsonObject publicinfoToJPub() {
    JsonObject jPub = new JsonObject();
    Map newMap = new Map(this.map);
    JsonArray map = newMap.mapTojMap();
    jPub.add("map", map);
    JsonPrimitive refereeTiles = new JsonPrimitive(this.refereeRemainingTiles);
    jPub.add("tile*", refereeTiles);
    JsonArray players = new JsonArray();
    players.add(this.activePlayer.playerStateToJPlayer());
    for(int score: this.playersScores)   {
      players.add(new JsonPrimitive(score));
    }
    jPub.add("players", players);
    return jPub;
  }
}