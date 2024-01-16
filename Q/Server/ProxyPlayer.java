package Server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonStreamParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.List;

import Common.Posn;
import Common.Public_Info;
import Common.Tile;
import Player.Move;
import Player.Player_Input;
import Player.IPlayer;

import static Common.JSON_Processor.TilesToJTiles;
import static Common.JSON_Processor.jPlacementToPlacement;

/**
 * This class implements the IPlayer interface as it represents the proxy player for when
 * players play the game by joining the game server.
 */
public class ProxyPlayer implements IPlayer {
  private final String name;
  private final Writer out;
  private final JsonStreamParser parser;

  public ProxyPlayer(String name, Socket clientSocket) throws IOException {
    this.name = name;
    this.out = new PrintWriter(clientSocket.getOutputStream(), true);
    this.parser = new JsonStreamParser(new InputStreamReader(clientSocket.getInputStream()));
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public void setup(Public_Info publicInfo, List<Tile> playerTiles) {
    try {
      JsonArray setupJson = generateMethodJson("setup", publicInfo.publicinfoToJPub(), TilesToJTiles(playerTiles));
      this.out.write(setupJson.toString());
      this.out.flush();
      receiveVoid();
    } catch (IOException e) {
      throw new InternalError(e.getMessage());
    }
  }

  @Override
  public Player_Input takeTurn(Public_Info publicInfo) {
    JsonObject jPub = publicInfo.publicinfoToJPub();
    JsonArray takeTurnArray = generateMethodJson("take-turn", jPub);

    try {
      this.out.write(takeTurnArray.toString());
      this.out.flush();
    } catch (IOException e) {
      throw new InternalError(e.getMessage());
    }

    if (this.parser.hasNext()) {
      JsonElement nextElement = this.parser.next();
      switch (nextElement.toString()) {
        case "\"pass\"":
          return new Player_Input(Move.PASS);
        case "\"replace\"":
          return new Player_Input(Move.EXCHANGE);
        default:
          LinkedHashMap<Posn, Tile> placements = jPlacementToPlacement(nextElement);
          return new Player_Input(Move.PLACE, placements);
      }
    } else {
      //TODO: are we certain that this is caused by the client
      throw new SecurityException("Player has not returned anything");
    }
  }

  @Override
  public void newTiles(List<Tile> tiles) {
    try {
      JsonArray jTiles = TilesToJTiles(tiles);
      JsonArray newTilesJson = generateMethodJson("new-tiles", jTiles);
      this.out.write(newTilesJson.toString());
      this.out.flush();
      receiveVoid();
    } catch (IOException e) {
      throw new InternalError(e.getMessage());
    }
  }

  @Override
  public void win(boolean isWinner) {
    try {
      JsonArray winJson = generateMethodJson("win", new JsonPrimitive(isWinner));
      this.out.write(winJson.toString());
      this.out.flush();
      receiveVoid();
      this.out.close();
    } catch (IOException e) {
      throw new InternalError(e.getMessage());
    }
  }

  private JsonArray generateMethodJson(String methodName, JsonElement... params) {
    JsonArray methodJson = new JsonArray();
    methodJson.add(new JsonPrimitive(methodName));
    methodJson.add(this.generateParametersJson(params));

    return methodJson;
  }

  private JsonArray generateParametersJson(JsonElement... params) {
    JsonArray parameterArray = new JsonArray();
    for (JsonElement param : params) {
      parameterArray.add(param);
    }
    return parameterArray;
  }

  private void receiveVoid() {
    if (this.parser.hasNext()) {
      JsonElement nextElement = this.parser.next();
      if(!nextElement.equals(new JsonPrimitive("void"))) {
        throw new SecurityException("player did not return the string void");
      }
    } else {
      throw new SecurityException("player unresponsive");
    }
  }
}