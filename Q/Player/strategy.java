package Player;

import Common.Public_Info;

/**
 * Represents an interface for strategies.
 */
public interface strategy {
  /**
   * Sends the referee the information about the players requested actions during their turn
   * @return a Player_Input object with all the information needed
   */
  public Player_Input determineMove(Public_Info publicInfo);
}