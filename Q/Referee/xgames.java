package Referee;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import Common.Public_Info;
import Common.Tile;
import Common.game_state;
import Player.IPlayer;

import static Common.Map.jMapToHashmap;
import static Common.PlayerState.jPlayersToPlayerStates;
import static Common.game_state.jPileToRefereePile;

/**
 * Test harness. The inputs of xgames consist of a JState followed by an array of JActors.
 * The test harness creates a series of players that are going to use the specified strategies.
 * It then hands these players and the given state to the referee, which runs the game to the end
 * and computes the names of the winner(s) and drop-out(s).
 */
//public class xgames {
//  public static void main(String[] args) throws IOException {
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    JsonStreamParser jsonStreamParser = new JsonStreamParser(reader);
//
//    while (jsonStreamParser.hasNext()) {
//      JsonObject jState = jsonStreamParser.next().getAsJsonObject();
//      JsonArray jActors = jsonStreamParser.next().getAsJsonArray();
//
//      JsonArray jMap = jState.getAsJsonArray("map");
//      HashMap<Posn, Tile> tiles = jMapToHashmap(jMap);
//      Map gameMap = new Map(tiles);
//
//      JsonArray jPile = jState.get("tile*").getAsJsonArray();
//      List<Tile> refereePile = jPileToRefereePile(jPile);
//
//      JsonArray jPlayers = jState.get("players").getAsJsonArray();
//      if (jActors.size() < 2 || jActors.size() >4) {
//        throw new IllegalArgumentException("A JActors contains at least 2 and at most 4 players");
//      }
//      if (jPlayers.size() != jActors.size()) {
//        throw new IllegalArgumentException("The array of JPlayers in JState contains as many players as the JActors");
//      }
//
//      List<PlayerState> playerStates = jPlayersToPlayerStates(jPlayers);
//
//      game_state gameState = new game_state(gameMap, refereePile, playerStates);
//
//      Public_Info publicInfo = gameState.getPublicInfo();
//
//      List<IPlayer> players = jPlayersToPlayers(jPlayers, jActors, publicInfo);
//      if (players.size() == 0) {
//        throw new IllegalArgumentException("players must be non-empty");
//      }
//
//      Referee referee = new Referee(players, gameState);
//      List<List<String>> winnersAndDropOuts = referee.playGame();
//
//      List<String> winners = winnersAndDropOuts.get(0);
//      List<String> winnersOutput = new ArrayList<>();
//
//      for (String w: winners) {
//        String newW = "\"" + w + "\"";
//        winnersOutput.add(newW);
//      }
//
//      List<String> exiled = winnersAndDropOuts.get(1);
//      List<String> exliedOutput = new ArrayList<>();
//
//      for (String e: exiled) {
//        String newE = "\"" + e + "\"";
//        exliedOutput.add(newE);
//      }
//
//      List<List<String>> winnersAndDropOutsOutput = new ArrayList<>();
//
//      System.out.println("[" + winnersOutput + "," + exliedOutput+ "]");
//    }
//    reader.close();
//  }
//
//  /**
//   * Get the name of the player given jActorSpec
//   * @param jActorSpec
//   */
//  public static String jActorSpecGetName(JsonArray jActorSpec) {
//    return jActorSpec.get(0).getAsString();
//  }
//
//
//  /**
//   * Get the strategy (string) of the player given jActorSpec
//   * @param jActorSpec
//   */
//  public static String jActorSpecGetStrategy(JsonArray jActorSpec) {
//    return jActorSpec.get(1).getAsString();
//  }
//
//  /**
//   * Get the exception string of the player given jActorSpec
//   * @param jActorSpec
//   */
//  public static String jActorSpecGetJExn(JsonArray jActorSpec) {
//    return jActorSpec.get(2).getAsString();
//  }
//}