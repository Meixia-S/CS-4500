package Server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonStreamParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Common.Map;
import Common.PlayerState;
import Common.Posn;
import Common.ScoringConfig;
import Common.Tile;
import Common.game_state;
import Referee.RefereeConfig;

import static Common.Map.jMapToHashmap;
import static Common.PlayerState.jPlayersToPlayerStates;
import static Common.game_state.jPileToRefereePile;
import static java.util.Collections.sort;

public class xServer {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    JsonStreamParser jsonStreamParser = new JsonStreamParser(reader);

    int portNum = 10000;
    if (args.length == 1) {
      portNum = Integer.parseInt(args[0]);
    }

    JsonObject jServerConfig = jsonStreamParser.next().getAsJsonObject();
    Server server = createServer(jServerConfig, portNum);
    printResults(server.startGame());

    reader.close();
  }

  /**
   * @return Creates a server class object with the given JsonObject named jServerConfig
   */
  private static Server createServer(JsonObject jServerConfig, int portNum) {
    int jServerTries = jServerConfig.getAsJsonPrimitive("server-tries").getAsInt();
    int jServerWait = jServerConfig.getAsJsonPrimitive("server-wait").getAsInt();
    int jWaitForSignup = jServerConfig.getAsJsonPrimitive("wait-for-signup").getAsInt();
    Boolean jQuiet = jServerConfig.getAsJsonPrimitive("quiet").getAsBoolean();
    JsonObject jRefereeConfig = jServerConfig.getAsJsonObject("ref-spec");

    RefereeConfig refereeConfig = createRefereeConfig(jRefereeConfig);
    return new Server(portNum, jServerTries, jServerWait, jWaitForSignup, refereeConfig);
  }

  /**
   * @return Creates a RefereeConfig class object with the given JsonObject named jRefereeConfig
   */
  private static RefereeConfig createRefereeConfig(JsonObject jRefereeConfig) {
    JsonObject jState= jRefereeConfig.getAsJsonObject("state0");
    JsonObject scoringConfig = jRefereeConfig.getAsJsonObject("config-s");

    game_state gameState = createGamestate(jState, createScoringConfig(scoringConfig));

    JsonPrimitive jQuiet = jRefereeConfig.getAsJsonPrimitive("quiet");
    JsonPrimitive jPerTurn = jRefereeConfig.getAsJsonPrimitive("per-turn");
    JsonPrimitive jObserver = jRefereeConfig.getAsJsonPrimitive("observe");

    return new RefereeConfig(gameState, jPerTurn.getAsInt(), jObserver.getAsBoolean());
  }

  /**
   * @return Creates a RefereeConfig class object with the given JsonObject named jState and
   *         a preconfigured ScoringConfig object named scoringConfig
   */
  private static game_state createGamestate(JsonObject jState, ScoringConfig scoringConfig) {
    JsonArray jMap = jState.getAsJsonArray("map");
    HashMap<Posn, Tile> tiles = jMapToHashmap(jMap);
    Map gameMap = new Map(tiles);

    JsonArray jPile = jState.get("tile*").getAsJsonArray();
    List<Tile> refereePile = jPileToRefereePile(jPile);

    JsonArray jPlayers = jState.get("players").getAsJsonArray();
    List<PlayerState> playerStates = jPlayersToPlayerStates(jPlayers);

    game_state gameState = new game_state(gameMap, refereePile, playerStates, scoringConfig);
    return gameState;
  }

  /**
   * Create a ScoringConfig object from a RefereeStateConfig json object.
   * @param jRefereeStateConfig a json object with fields specifying the bonus for creating a Q
   *                            and for placing all tiles in ones hand.
   * @return the created ScoringConfig object.
   */
  private static ScoringConfig createScoringConfig(JsonObject jRefereeStateConfig) {
    JsonPrimitive jQBonus = jRefereeStateConfig.getAsJsonPrimitive("qbo");
    JsonPrimitive jEndBonus = jRefereeStateConfig.getAsJsonPrimitive("fbo");

    ScoringConfig scoringConfig = new ScoringConfig(jQBonus.getAsInt(), jEndBonus.getAsInt());
    return scoringConfig;
  }

  /**
   * Print the results of playing a game to standard out.
   * @param winnersAndDropOuts the game results returned by the server's startGame method.
   */
  private static void printResults(List<List<String>> winnersAndDropOuts) {
    List<String> winners = winnersAndDropOuts.get(0);
    List<String> winnersOutput = new ArrayList<>();

    for (String w: winners) {
      String newW = "\"" + w + "\"";
      winnersOutput.add(newW);
    }

    sort(winnersOutput);

    List<String> exiled = winnersAndDropOuts.get(1);
    List<String> exliedOutput = new ArrayList<>();

    for (String e: exiled) {
      String newE = "\"" + e + "\"";
      exliedOutput.add(newE);
    }

    System.out.println("[" + winnersOutput + "," + exliedOutput+ "]");
  }
}