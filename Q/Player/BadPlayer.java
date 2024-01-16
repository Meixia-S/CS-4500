package Player;

import java.util.List;

import Common.Public_Info;
import Common.Tile;

/**
 * Represents a bad player. This player pursues a strategy until the exception method is called.
 */
public class BadPlayer extends Player {
  private String exn;
  private int count;

  public BadPlayer(String name, strategy strategyVersion, String exn) {
    this(name, strategyVersion, exn, 0);
  }

  public BadPlayer(String name, strategy strategyVersion, String exn, int count) {
    super(name, strategyVersion);
    this.exn = exn;
    this.count = count;
  }

  /**
   * This player is handed the initial public gamestate, which is visible to all,
   * plus an initial set of tiles
   * @param publicInfo the public knowledge of the initial gamestate
   * @param tiles the starting tiles for the player (6 tiles)
   * @throws SecurityException the exception method is setup called
   */
  @Override
  public void setup(Public_Info publicInfo, List<Tile> tiles) throws SecurityException {
    if (this.exn.equals("setup")) {
      executePlayerMethod(()-> super.setup(publicInfo, tiles));
    } else {
     super.setup(publicInfo, tiles);
    }
  }

  /**
   * After receiving the public part of state, a player
   * passes,
   * asks the referee to replace its set of tiles, or
   * requests the extension of the map in the given state with some tile placements
   * @param publicInfo
   * @throws SecurityException the exception method is take turn
   */
  @Override
  public Player_Input takeTurn(Public_Info publicInfo) throws SecurityException {
    if (this.exn.equals("take-turn")) {
      if (this.count == 0) {
        throw new SecurityException("Bad Player");
      } else if(this.count == 1) {
        timeOut();
        throw new SecurityException("Timed out");
      } else {
        count--;
        return super.takeTurn(publicInfo);
      }
    } else {
      return super.takeTurn(publicInfo);
    }
  }

  /**
   * This player is handed a new set of tiles.
   * @param tiles the new tiles for the player
   * @throws SecurityException the exception method is new-tiles
   */
  @Override
  public void newTiles(List<Tile> tiles) throws SecurityException {
    if (this.exn.equals("new-tiles")) {
      executePlayerMethod(()-> super.newTiles(tiles));
    } else {
      super.newTiles(tiles);
    }
  }

  /**
   * This player is informed whether they won or not.
   * @throws SecurityException the exception method is win
   */
  @Override
  public void win(boolean winner) throws SecurityException {
    if (this.exn.equals("win")) {
      executePlayerMethod(()-> super.win(winner));
    } else {
      super.win(winner);
    }
  }

  /**
   * Pause the thread for 10 seconds, resulting in this player being removed from the game.
   * @throws SecurityException if the thread is interrupted for some reason.
   */
  private void timeOut() throws SecurityException {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new SecurityException("Player took too long to execute method");
    }
  }

  /**
   * Helper method that abstracts the if else statement that determines if a player should misbehave
   * @param method the method we want to be run if the player should not misbehave
   * @throws SecurityException if this player should misbehave on the given method call.
   */
  private void executePlayerMethod(Runnable method) {
    if (this.count == 0) {
      throw new SecurityException("Bad Player");
    } else if(this.count == 1) {
      timeOut();
      throw new SecurityException("Timed out");
    } else {
      method.run();
      count --;
    }
  }
}