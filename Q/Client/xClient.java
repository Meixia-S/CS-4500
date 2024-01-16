package Client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import Player.IPlayer;
import Player.Player;

public class xClient {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    JsonStreamParser jsonStreamParser = new JsonStreamParser(reader);
    int portNum = 10000;
    if (args.length == 1) {
      portNum = Integer.parseInt(args[0]);
    }
    JsonObject jClientConfig = jsonStreamParser.next().getAsJsonObject();
    createClients(jClientConfig, portNum);

    reader.close();
  }

  /**
   * A method that parses the given JsonObject and create n numbers of clients with their own threads
   */
  private static void createClients(JsonObject jClientConfig, int portNum) {
    String jHost = jClientConfig.getAsJsonPrimitive("host").getAsString();
    int jWait = jClientConfig.getAsJsonPrimitive("wait").getAsInt();
    Boolean jQuiet = jClientConfig.getAsJsonPrimitive("quiet").getAsBoolean();
    JsonArray jActors = jClientConfig.getAsJsonArray("players");

    List<IPlayer> players = Player.jActorsToPlayers(jActors);
    for (IPlayer p : players) {
      new threadedClient(new Client(jHost, portNum, p)).start();
      try {
        Thread.sleep(jWait * 1000L);
      } catch (InterruptedException e) {
        throw new SecurityException("Player order has been compromised");
      }
    }
  }

  /**
   * A wrapper for a client that runs its connectToGame method in a separate thread.
   */
  private static class threadedClient extends Thread {
    private final Client client;

    private threadedClient(Client client) {
      this.client = client;
    }

    @Override
    public void run() {
      client.connectToGame();
    }
  }
}