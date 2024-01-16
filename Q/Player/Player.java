package Player;

import Common.Public_Info;
import Common.Tile;

import com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player that joins the Q Game.
 */
public class Player implements IPlayer {

  protected strategy strategy;
  protected String name;

  public Player(String name, strategy strategy) {
    this.name = name;
    this.strategy = strategy;
  }

  /**
   * @return the name of this player.
   */
  @Override
  public String name() {
    return this.name;
  }

  /**
   * This player is handed the initial map, which is visible to all, plus an initial set of tiles
   *
   * @param tiles the starting tiles for the player (6 tiles)
   */
  @Override
  public void setup(Public_Info publicInfo, List<Tile> tiles) {
  }

  /**
   * After receiving the public part of state, a player passes, asks the referee to replace its set
   * of tiles, or requests the extension of the map in the given state with some tile placements
   *
   * @param publicInfo the current publicInfo
   */
  @Override
  public Player_Input takeTurn(Public_Info publicInfo) {
    return this.strategy.determineMove(publicInfo);
  }

  /**
   * This player is handed a new set of tiles.
   *
   * @param tiles the new tiles for the player
   */
  @Override
  public void newTiles(List<Tile> tiles) {
  }

  /**
   * This player is informed whether they won or not.
   */
  @Override
  public void win(boolean winner) {
  }


  /**
   * Converts list of JPlayers given their names in JActors to list of players.
   */
  public static List<IPlayer> jActorsToPlayers(JsonArray jActors) {
    List<IPlayer> players = new ArrayList<>();

    for (int i = 0; i < jActors.size(); i++) {
      JsonArray jActorSpec = jActors.get(i).getAsJsonArray();

      String name = jActorSpec.get(0).getAsString();
      String usedStrategyString = jActorSpecGetStrategy(jActorSpec);
      strategy usedStrategy = getStrategy(usedStrategyString);

      IPlayer p;
      if (jActorSpec.size() == 2) {
        p = new Player(name, usedStrategy);
      } else if (jActorSpec.size() == 3) {
        String jExn = jActorSpecGetJExn(jActorSpec);
        p = new BadPlayer(name, usedStrategy, jExn);
      } else if (jActorSpec.size() == 4) {
        try {
          String jExn = jActorSpecGetJExn(jActorSpec);
          int count = jActorSpec.get(3).getAsInt();
          p = new BadPlayer(name, usedStrategy, jExn, count);
        } catch (UnsupportedOperationException | NumberFormatException e) {
          String cheat = jActorSpec.get(3).getAsString();
          p = new CheatPlayer(name, usedStrategy, cheat);
        }
      } else {
        throw new IllegalArgumentException("inappropriate jActorSpec");
      }
      players.add(p);
    }
    return players;
  }

  /**
   * Get the strategy used based on the public info and strategy version.
   *
   * @param strategyVersion a string that determines which strategy the player should apply
   */
  private static strategy getStrategy(String strategyVersion) {
    strategy usedStrategy;

    if (strategyVersion.equals("dag")) {
      usedStrategy = new DagStrategy();
    } else if (strategyVersion.equals("ldasg")) {
      usedStrategy = new LdasgStrategy();
    } else {
      throw new IllegalArgumentException(
          strategyVersion + " does not exist, please chose between dag and ldasg");
    }
    return usedStrategy;
  }

  /**
   * Get the strategy (string) of the player given jActorSpec
   * @param jActorSpec
   */
  public static String jActorSpecGetStrategy(JsonArray jActorSpec) {
    return jActorSpec.get(1).getAsString();
  }

  /**
   * Get the exception string of the player given jActorSpec
   * @param jActorSpec
   */
  public static String jActorSpecGetJExn(JsonArray jActorSpec) {
    return jActorSpec.get(2).getAsString();
  }
}