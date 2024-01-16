package Server;

import Common.Utils;
import Player.IPlayer;
import Referee.Referee;
import Referee.RefereeConfig;
import com.google.gson.JsonStreamParser;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * A server that can accept remote client players and run a Q-game with these players.
 */
public class Server {

  private final ServerSocket serverSocket;
  private final List<Socket> clientSockets;
  private RefereeConfig refereeConfig;
  private int waitingRoundNum = 2;
  private int connectTimeOutTime = 20;
  private int signUpTimeOutTime = 3;
  private static final int MIN_PLAYERS = 2;
  private static final int MAX_PLAYERS = 4;

  /**
   * The new constructor created for milestone 10
   *
   * @param port
   * @param waitingRoundNum
   * @param connectTimeOutTime
   * @param signUpTimeOutTime
   * @throws RuntimeException
   */
  public Server(int port, int waitingRoundNum, int connectTimeOutTime, int signUpTimeOutTime,
      RefereeConfig refereeConfig) throws RuntimeException {
    this.serverSocket = ioSafe(() -> new ServerSocket(port));
    this.clientSockets = new ArrayList<>();
    this.waitingRoundNum = waitingRoundNum;
    this.connectTimeOutTime = connectTimeOutTime;
    this.signUpTimeOutTime = signUpTimeOutTime;
    this.refereeConfig = refereeConfig;
  }

  /**
   * Creates a server, binding the server socket to the given port. Initializes clientSockets to an
   * empty list.
   *
   * @param port the port number of this server's ServerSocket.
   * @throws RuntimeException if the creation of a new ServerSocket fails.
   */
  public Server(int port) throws RuntimeException {
    this.serverSocket = ioSafe(() -> new ServerSocket(port));
    this.clientSockets = new ArrayList<>();
  }

  /**
   * Attempts to connect remote players to this server and play a QGame if at least the minimum
   * number of client players join the server after the waiting periods have occured.
   *
   * If fewer than the minimum number of players join the server during the signup period, or if the
   * server encounters any internal errors while trying to sign up players, it will shut down
   * gracefully.
   *
   * @return the game results; an empty list if the server must shut down before completing a game.
   */
  public List<List<String>> startGame() {
    List<List<String>> gameResults = List.of(new ArrayList<>(), new ArrayList<>());
    List<IPlayer> players = new ArrayList<>();

    try {
      this.runSignUpPeriod(players);
    } catch (RuntimeException e) {
      System.out.println("System failed during sign-up");
      this.shutDownGracefully();
    }

    if (players.size() >= MIN_PLAYERS) {
      Referee referee = new Referee(players, this.refereeConfig);
      try {
        gameResults = referee.playGame();
      } catch (InternalError e) {
        this.shutDownGracefully();
      }
    }

    this.shutDownGracefully();
    return gameResults;
  }

  /**
   * Shut down the server gracefully by closing all open sockets.
   */
  private void shutDownGracefully() {
    try {
      clientSockets.forEach(Server::closeSocket);
      closeSocket(serverSocket);
    } catch (IllegalStateException e) {
      System.out.println("hopefully doesn't happen");
    }
  }

  /**
   * Runs a 20-second sign-up periods during which client players can connect to this server. If
   * fewer than the minimum number (2) of clients have connected to the server after 20 seconds,
   * runs a second 20-second sign-up period. Terminates if, during any point during the sign-up
   * periods, the maximum number (4) of clients have joined the server.
   *
   * @param players list to which client players will be added once they have joined the server.
   */
  private void runSignUpPeriod(List<IPlayer> players) {
    for (int i = 0; i < this.waitingRoundNum; i++) {
      if (players.size() < MIN_PLAYERS) {
        ignoreTimeoutException(
            () -> Utils.runVoidMethodWithTimeout(
                () -> {
                  try {
                    signUp(players);
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                }, this.connectTimeOutTime,
                "not enough players signed up in given time window"));
      } else {
        break;
      }
    }

    closeSocket(serverSocket);
  }

  /**
   * Attempts to sign up client players to the server. Once a client player has connected, it has 3
   * seconds to send its name to the server, otherwise it will not be added to the given list of
   * players and its port will be closed. Terminates if the maximum number (4) of players have
   * connected to the server (and subsequently sent their names).
   *
   * @param players the list of players to add clients to once they connect to the server and send
   *                their names within the allowed time limit.
   */
  private void signUp(List<IPlayer> players) throws IOException {
    while (players.size() < MAX_PLAYERS) {
      new threadedPlayer(serverSocket.accept(), players, this.clientSockets).start();
    }

  }

  /**
   * Wrapper for a call to an ioSupplier's get method. Returns the result of calling the given
   * ioSupplier's get method, catching an IOException if one occurs and throwing a
   * RuntimeException.
   *
   * @param method a supplier of a method that may throw an IOException.
   * @param <T>    the return type of the method.
   * @return the result of calling the given ioSupplier's get method.
   * @throws RuntimeException if the call to the given ioSupplier's get method produces an
   *                          IOException.
   */
  private <T> T ioSafe(exceptionSupplier<T, IOException> method) throws RuntimeException {
    try {
      return method.get();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Closes the given Closable (socket), catching an IOException if the call to the close method
   * produces one, throwing an Illegal state exception instead.
   *
   * @param socket the socket to be closed.
   */
  private static void closeSocket(Closeable socket) throws IllegalStateException {
    try {
      socket.close();
    } catch (IOException e) {
      throw new IllegalStateException("could not close");
    }
  }

  /**
   * Calls the Void method encapsulated by the given Supplier, catching and ignoring a timeout
   * exception if one is thrown.
   *
   * @param method a supplier of a method that may throw a timeout exception.
   */
  private void ignoreTimeoutException(exceptionRunnable<TimeoutException> method) {
    try {
      method.run();
    } catch (TimeoutException ignored) {
    }
  }

  /**
   * A wrapper for a client that runs its connectToGame method in a separate thread.
   */
  private static class threadedPlayer extends Thread {

    private final Socket playerSocket;
    private final List<IPlayer> players;
    private List<Socket> clientSockets;

    private threadedPlayer(Socket playerSocket, List<IPlayer> players, List<Socket> clientSockets) {
      this.playerSocket = playerSocket;
      this.players = players;
      this.clientSockets = clientSockets;
    }

    @Override
    public void run() {
      String name;
      JsonStreamParser parser;
      try {
        parser = new JsonStreamParser(new InputStreamReader(playerSocket.getInputStream()));
        name = Utils.runMethodWithTimeout(parser::next, 6,
            "player took too long to send name to server").getAsString();

        if (name.endsWith("padded")) {
          name = name.substring(0, name.length() - 6);
        }

        players.add(new ProxyPlayer(name, playerSocket));
        clientSockets.add(playerSocket);
      } catch (TimeoutException e) {
        System.out.println("couldn't get name");
        closeSocket(playerSocket);
      } catch (IOException e) {
        System.out.println("io error");
        throw new RuntimeException(e);
      }

    }
  }

  /**
   * A functional Interface for a supplier that may throw an IOException.
   */
  @FunctionalInterface
  private interface exceptionSupplier<T, E extends Exception> {

    T get() throws E;
  }

  @FunctionalInterface
  private interface exceptionRunnable<E extends Exception> {

    void run() throws E;
  }
}