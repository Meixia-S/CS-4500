package Player;

import java.util.List;

import Common.Public_Info;
import Common.Tile;

/**
 * This interface represents all types of players this game can have:
 * - misbehaving players
 * - misbehaving players with a count
 * - a cheating player
 * - a regular player that implements the dag or ldasg strategy
 * - a regular player that implements the DFS strategy
 */
public interface IPlayer {

  /**
   * @return the name of the player
   */
  String name();

  /**
   * Assigns the player their tiles and the starting information about the game_state
   * @param publicInfo public information about the current game_state
   * @param playerTiles the players starting tiles
   */
  void setup(Public_Info publicInfo, List<Tile> playerTiles);

  /**
   * Prompts the player to play out their turn and pass the move information back to the referee
   * in the form of a Player_Input.
   * @param publicInfo the public information about the game_state
   * @return Player_Input (includes which play actions this player wants to do)
   */
  Player_Input takeTurn(Public_Info publicInfo);

  /**
   * Assigning the new tiles to the player's tile inventory
   * @param tiles the tiles the player is getting
   */
  void newTiles(List<Tile> tiles);

  /**
   * Lets the player know if they won the game
   */
  void win(boolean isWinner);
}