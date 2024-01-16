package Client;

import Common.JSON_Processor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonStreamParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import Common.Public_Info;
import Common.Tile;
import Player.IPlayer;

import static Common.JSON_Processor.jTilesToTiles;

/**
 * This class represents the proxy referee that is used to play the game when players connect
 * to the game server.
 */
//TODO: error handling
public class ProxyReferee {
  private IPlayer player;

  public ProxyReferee(IPlayer player) {
    this.player = player;
  }

  /**
   *
   * @param out
   * @param in
   * @throws IOException
   */
  public void playGame(PrintWriter out, InputStreamReader in) throws IOException {
    JsonStreamParser parser = new JsonStreamParser(in);

    while (parser.hasNext()) {
      JsonElement next = parser.next();
      try {
        JsonElement response = interpretJson(next);
        out.write(response.toString());
        out.flush();
      } catch (Exception e) {
        break;
      }
    }
    out.close();
    in.close();
  }

  /**
   * This method takes in a function call, parses it, and returns/executes the appropriate response
   * @param je function call from a player (one of the player API methods)
   * @return a jChoice or "void"
   * @throws RuntimeException if given malformed input
   */
  private JsonElement interpretJson(JsonElement je) throws RuntimeException {
    JsonArray ja = je.getAsJsonArray();
    JsonPrimitive methodName = ja.get(0).getAsJsonPrimitive();
    JsonArray parameters = ja.get(1).getAsJsonArray();

    switch (methodName.getAsString()) {
      case "setup" -> {
        //TODO: verify that publicInfo active player has correct name?
        Public_Info publicInfo = JSON_Processor.jPubtoPublicInfo(parameters.get(0).getAsJsonObject());
        List<Tile> tiles = JSON_Processor.jTilesToTiles(parameters.get(1).getAsJsonArray());
        this.player.setup(publicInfo, tiles);
        return new JsonPrimitive("void");
      }
      case "take-turn" -> {
        Public_Info publicInfo = JSON_Processor.jPubtoPublicInfo(parameters.get(0).getAsJsonObject());
        return this.player.takeTurn(publicInfo).playerInputTojChoice();
      }
      case "new-tiles" -> {
        List<Tile> tileArray = jTilesToTiles(parameters.get(0).getAsJsonArray());
        this.player.newTiles(tileArray);
        return new JsonPrimitive("void");
      }
      case "win" -> {
        boolean winner = parameters.get(0).getAsBoolean();
        this.player.win(winner);
        return new JsonPrimitive("void");
      }
      default -> {
        throw new IllegalArgumentException("not given valid input");
      }
    }
  }
}
