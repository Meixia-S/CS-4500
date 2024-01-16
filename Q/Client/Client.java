package Client;

import Player.IPlayer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A client that joins a QGame server and remotely participates in a QGame.
 */
public class Client {
  private final int port; //the server port number
  private final String ip; // the host address
  private final IPlayer player; // the concrete player implementation with which this client plays the game
  private Socket clientSocket; //the socket with which this player connects to the server


  public Client(String ip, int port, IPlayer player) {
    if (ip.length() == 9) {
      this.ip = ip;
      this.port = port;
      this.player = player;
    } else {
      throw new IllegalArgumentException("Ip address is not valid");
    }
  }

  /**
   * 
   */
  public void connectToGame() {
    PrintWriter out;
    InputStreamReader in;
    try {
      tryToConnect();
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new InputStreamReader(clientSocket.getInputStream());

      if (player.name().length() == 1) {
        out.write("\"" + player.name() + "padded\"");
        out.flush();
      } else {
        out.write("\"" + player.name() + "\"");
        out.flush();
      }

      ProxyReferee proxyReferee = new ProxyReferee(player);
      proxyReferee.playGame(out, in);
      clientSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.out.println("IO Exception throw in the client java class");
    }
  }

  private void tryToConnect() {
    try {
      clientSocket = new Socket(ip, port);
    } catch (IOException e) {
      System.out.println("Server is not currently open");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException i) {
        System.out.println("thread.sleep threw exception");
      }
      tryToConnect();
    }
  }
}
