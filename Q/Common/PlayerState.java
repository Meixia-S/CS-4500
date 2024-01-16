package Common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static Common.Tile.jTileToTile;

/**
 * Represents all the information about a Player for the Q game. It keeps track of this player's
 * available tiles, their birthday, name, and current score.
 */
public class PlayerState {
  private List<Tile> playerTiles;
  private int score;
  private String name;

  /**
   * Initializes a PlayerState with given list of tiles, birthday, and score
   * @param playerTiles The tiles this player can place on the map
   */
  public PlayerState(List<Tile> playerTiles, String name, int score) {
    this.playerTiles = playerTiles;
    this.score = score;
    this.name = name;
  }

  public PlayerState(String name) {
    this.name = name;
    this.playerTiles = new ArrayList<>();
    this.score = 0;
  }

  /**
   * @return name of this PlayerState
   */
  public String getName() {
    return this.name;
  }

  public List<Tile> handInTiles() {
    List<Tile> tiles = new ArrayList<>(this.playerTiles);
    this.playerTiles.clear();
    return tiles;
  }

  /**
   * @return copy of list of tiles this player has
   */
  public List<Tile> getPlayerTiles() { return new ArrayList<>(this.playerTiles); }

  /**
   * @return this player's score
   */
  public int getPlayerScore() {
    return this.score;
  }

  /**
   * sets the score of this player
   * @param score
   */
  public void setPlayerScore(int score) {
    this.score = this.score + score;
  }


  /**
   * Adds tiles to this player's available roster of tiles.
   */
  public void updatePlayerTiles(List<Tile> tiles) {
    this.playerTiles.addAll(tiles);
  }

  /**
   * Removes the given tile from this player's list of tiles
   * @param tile
   */
  public void removePlayerTile(Tile tile) {
    this.playerTiles.remove(tile);
  }

  /**
   * Exchange all of this player's tiles for newTiles.
   * @throws IllegalArgumentException if newTiles has less than 6 tiles.
   */
  public void setPlayerTiles(List<Tile> newTiles)  {
    if (newTiles.size() != 6) {
      throw new IllegalArgumentException("Referee doesn't have enough tiles to exchange");
    }
    this.playerTiles = newTiles;
  }

  public void setPlayerInitialTiles(List<Tile> newTiles) {
    this.playerTiles = newTiles;
  }

  /**
   * @return a copy of this player
   */
  public PlayerState getCopy() {
    List<Tile> copyOfArray = new ArrayList<>(this.getPlayerTiles());
    return new PlayerState(copyOfArray, this.name, this.score);
  }


  /**
   * Converts jPlayer to PlayerState
   *
   * @param jPlayer the JsonObject that will be converted into a Player State
   * @return the Player State object formed based off of the jPlayer object
   */
  public static PlayerState jPlayerToPlayerState(JsonObject jPlayer) {
    int score = jPlayer.get("score").getAsInt();
    String name = jPlayer.get("name").getAsString();
    List<Tile> playerTiles = new ArrayList<>();

    JsonArray jPlayerTiles = jPlayer.getAsJsonArray("tile*");
    for (JsonElement e : jPlayerTiles) {
      playerTiles.add(jTileToTile(e.getAsJsonObject()));
    }

    return new PlayerState(playerTiles, name, score);
  }

  /**
   * converts list of jPlayers to list of PlayerStates.
   * @param jPlayers
   */
  public static List<PlayerState> jPlayersToPlayerStates(JsonArray jPlayers) {
    List<PlayerState> playerStates = new ArrayList<>();

    for (int i =0; i<jPlayers.size(); i++) {
      JsonObject jPlayer = jPlayers.get(i).getAsJsonObject();
      PlayerState ps = jPlayerToPlayerState(jPlayer);
      playerStates.add(ps);
    }

    return playerStates;
  }

  public JsonObject playerStateToJPlayer() {
    JsonObject jPlayer = new JsonObject();
    jPlayer.addProperty("score", this.getPlayerScore());
    jPlayer.add("tile*", JSON_Processor.TilesToJTiles(this.getPlayerTiles()));
    jPlayer.add("name", new JsonPrimitive(this.name));

    return jPlayer;
  }
}