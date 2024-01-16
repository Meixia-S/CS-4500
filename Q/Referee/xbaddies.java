package Referee;

import Common.*;
import Player.IPlayer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static Common.Map.jMapToHashmap;
import static Common.PlayerState.jPlayersToPlayerStates;
import static Common.game_state.jPileToRefereePile;
import static Player.Player.jActorsToPlayers;
import static java.util.Collections.sort;

public class xbaddies {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    JsonStreamParser jsonStreamParser = new JsonStreamParser(reader);

    while (jsonStreamParser.hasNext()) {
      JsonObject jState = jsonStreamParser.next().getAsJsonObject();
      JsonArray jActors = jsonStreamParser.next().getAsJsonArray();

      JsonArray jMap = jState.getAsJsonArray("map");
      HashMap<Posn, Tile> tiles = jMapToHashmap(jMap);
      Map gameMap = new Map(tiles);

      JsonArray jPile = jState.get("tile*").getAsJsonArray();
      List<Tile> refereePile = jPileToRefereePile(jPile);

      JsonArray jPlayers = jState.get("players").getAsJsonArray();

      if (jActors.size() < 2 || jActors.size() >4) {
        throw new IllegalArgumentException("A JActors contains at least 2 and at most 4 players");
      }
      if (jPlayers.size() != jActors.size()) {
        throw new IllegalArgumentException("The array of JPlayers in JState contains as many players as the JActors");
      }

      List<PlayerState> playerStates = jPlayersToPlayerStates(jPlayers);

      game_state gameState = new game_state(gameMap, refereePile, playerStates);
      List<IPlayer> players = jActorsToPlayers(jActors);

      if (players.size() == 0) {
        throw new IllegalArgumentException("players must be non-empty");
      }

      Referee referee = new Referee(players, gameState);
      referee.addObserver("Q/Referee/Tmp");
      if (args.length > 0 && args[0].equals("-show")) {
        referee.addObserver("Q/Referee/Tmp");
      }
      List<List<String>> winnersAndDropOuts = referee.playGame();

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
    reader.close();
  }
}