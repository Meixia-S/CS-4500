package Referee;

import Common.PlayerState;
import Common.Posn;
import Common.Tile;
import Common.Utils;
import Common.game_state;
import Player.Move;
import Player.Player_Input;
import Player.IPlayer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Represents a referee that runs this Q game. The fields are: - players a list of all the active
 * players - gameState the current state of the game - playerActionsInAround a list of all the
 * player moves within one round - misbehavedPlayers a list of all players who break the rules or
 * disconnect mid-game
 */
public class Referee {

  private final List<IPlayer> players;
  private final game_state gameState;
  private List<Move> playerActionsInRound;
  private int numPlayersAtRoundStart;
  private Player_Input activePlayerAction;
  private final List<IPlayer> misbehavedPlayers;
  private List<IPlayer> winners;
  private final List<Observer> observers;
  private int playerCallTimeOut = 10;
  private boolean placedAllTilesInHand = false;

  /**
   * The new constructor created for milestone 10
   * @param players the players that are participating in the game
   * @param refereeConfig a class that contains the game state, the time a player has to made their
   *                      move, and the determiner if the game should create a GUI for an observer(s)
   */
  public Referee(List<IPlayer> players, RefereeConfig refereeConfig) {
    this.players = players;
    this.gameState = refereeConfig.gameState();
    this.playerActionsInRound = new ArrayList<>();
    this.misbehavedPlayers = new ArrayList<>();
    this.playerCallTimeOut = refereeConfig.playerCallTimeout();
    this.observers = new ArrayList<>();

    if(refereeConfig.observer()) {
      addObserver("Q/Referee/Tmp");
    }
  }

  public Referee(List<IPlayer> players, game_state gameState) {
    this.players = players;
    this.gameState = gameState;
    this.playerActionsInRound = new ArrayList<>();
    this.misbehavedPlayers = new ArrayList<>();
    this.observers = new ArrayList<>();
  }

  public Referee(List<IPlayer> players) {
    this.players = players;
    this.playerActionsInRound = new ArrayList<>();
    this.misbehavedPlayers = new ArrayList<>();
    this.observers = new ArrayList<>();
    // mapping over a stream and getting the names out of the player object
    this.gameState = new game_state(this.players.stream().map(IPlayer::name).toList());
  }

  public void addObserver(String directory) {
    this.observers.add(new Observer(directory));
  }

  /**
   * This referee runs this game till completion. Returns a list that comprises a list of the names
   * of the winning player(s) and a list of the names those players that misbehaved.
   */
  public List<List<String>> playGame() {
    List<List<String>> winnersAndMisbehavedPlayers = new ArrayList<>();
    setUpPlayers();
    updateObservers();
    numPlayersAtRoundStart = this.players.size();

    while (!isGameOver()) {
      if (this.playerActionsInRound.size() == this.numPlayersAtRoundStart) {
        this.playerActionsInRound = new ArrayList<>();
        this.numPlayersAtRoundStart = this.players.size();
      }
      try {
        processPlayerAction();
        // this only catches cheating players and timeouts
      } catch (RuntimeException e) {
        removePlayer(activePlayer());
      }
      updateObservers();
    }
    this.winners = getWinners();
    this.win();
    List<String> sortedWinners = getNames(winners).stream().sorted().toList();
    winnersAndMisbehavedPlayers.add(sortedWinners);
    winnersAndMisbehavedPlayers.add(getNames(misbehavedPlayers));

    if (this.observers.size() > 0) {
      this.observers.get(0).view();
    }

    return winnersAndMisbehavedPlayers;
  }

  /**
   * This referee sets up the game with an initial map (with one referee's tile) and hands players
   * an initial set of tiles
   */
  public void setUpPlayers() {
    for (int i = 0; i < this.players.size(); i++) {
      IPlayer p = players.get(i);
      List<Tile> playerStartTiles = this.gameState.getPlayerState(p.name()).getPlayerTiles();
      try {
        Utils.runVoidMethodWithTimeout(()->p.setup(this.gameState.getPublicInfo(), playerStartTiles),
                this.playerCallTimeOut, "setup: player took to long to execute method");
      } catch (SecurityException | TimeoutException e) {
        removePlayer(p);
        if (i == 0) {
          i = 0;
        }
        i--;
      }
    }
  }

  /**
   * Updates all of this Referee's Observers with the current game_state.
   */
  private void updateObservers() {
    for (Observer o : this.observers) {
      game_state gameState = this.gameState.copy();
      o.update(gameState);
    }
  }

  /**
   * This referee informs and lets the active player takes turn and receives their desired action.
   */
  protected void getPlayerAction() {
    try {
      this.activePlayerAction = Utils.runMethodWithTimeout(() -> activePlayer().takeTurn(gameState.getPublicInfo()),
              this.playerCallTimeOut, "takeTurn: player took to long to execute method");
    } catch (TimeoutException e) {
      throw new SecurityException("In Referee: " + e.getMessage());
    }
  }

  /**
   * This referee receives the player's desired move and updates the game state.
   */
  public void processPlayerAction() {
    try {
      getPlayerAction();
      if (activePlayerAction.getPlayerMove().equals(Move.PLACE)) {
        this.playerActionsInRound.add(Move.PLACE);
        processPlaceAction(activePlayerAction);
      } else if (activePlayerAction.getPlayerMove().equals(Move.EXCHANGE)) {
        this.playerActionsInRound.add(Move.EXCHANGE);
        processExchangeAction();
      } else {
        this.playerActionsInRound.add(Move.PASS);
        updateActivePlayer();
      }
    } catch (SecurityException e) {
      removePlayer(activePlayer());
      this.numPlayersAtRoundStart--;
    }
  }

  /**
   * Processes these Placements and updates the game state.
   */
  private void processPlaceAction(Player_Input playerAction) {
    LinkedHashMap<Posn, Tile> placeableTiles = playerAction.getPlayableTiles();
    int handSize = this.gameState.getActivePlayer().getPlayerTiles().size();

    for (java.util.Map.Entry<Posn, Tile> placement : placeableTiles.entrySet()) {
      Posn posn = placement.getKey();
      Tile tile = placement.getValue();
      try {
        this.gameState.placeTile(tile, posn);
        this.gameState.getActivePlayer().getPlayerTiles().remove(tile);
      } catch (Exception e) {
        removePlayer(activePlayer());
        return;
      }
    }

    int playerScoreInTurn = this.gameState.turnScore();
    this.gameState.getActivePlayer().setPlayerScore(playerScoreInTurn);

    if (handSize == this.gameState.getCurrentTurnAction().size()) {
      this.placedAllTilesInHand = true;
      return;
    }

    try {
      Utils.runVoidMethodWithTimeout(() -> activePlayer().newTiles(distributeNewTiles()), this.playerCallTimeOut,
              "newTiles: player took to long to execute method");
      updateActivePlayer();
    } catch (TimeoutException | SecurityException e) {
      removePlayer(activePlayer());
    }
  }

  /**
   * Processes this Exchange and updates the game state.
   */
  protected void processExchangeAction() {
    try {
      this.gameState.exchangeTiles();
      Utils.runVoidMethodWithTimeout(()->activePlayer().newTiles(distributeNewTiles()), this.playerCallTimeOut,
              "newTiles: player took to long to execute method");
      updateActivePlayer();
    } catch (SecurityException | TimeoutException e) {
      removePlayer(activePlayer());
    }
  }

  /**
   * Updates the active player. Move to the next player if the current player is done with their
   * turn.
   */
  private void updateActivePlayer() {
    this.gameState.updateActivePlayer();
  }

  /**
   * Returns player whose is the active playerstate in this current game state.
   */
  private IPlayer activePlayer() {
    PlayerState activePlayerState = this.gameState.getActivePlayer();

    if (this.players.size() == 1) {
      return this.players.get(0);
    }

    for (IPlayer p : this.players) {
      if (p.name().equals(activePlayerState.getName())) {
        return p;
      }
    }

    throw new IllegalStateException("blow up");
  }

  /**
   * Removes this player and updates the game state.'
   * Made public for testing
   * <p>
   * Modified to do follow XGames requirements
   */
  private void removePlayer(IPlayer removedPlayer) {
    int removedPlayerIdx = this.players.indexOf(removedPlayer);
    this.misbehavedPlayers.add(removedPlayer);
    this.players.remove(removedPlayerIdx);
    this.gameState.updateWhenPlayerRemoved(removedPlayerIdx);
  }

  /**
   * Returns list of tiles that the referee hands back to player if they place or exchange.
   */
  protected List<Tile> distributeNewTiles() {
    if (activePlayerAction.getPlayerMove() == Move.PLACE) {
      List<Tile> newTiles = this.gameState.replacePlayersTiles();
      return newTiles;
    } else {
      return this.gameState.exchangeHandNewTiles();
    }
  }

  /**
   * ------------------------------------------------------------------------------------------------------------------
   * Returns true if the game is over, otherwise returns false. A game is over if: - at the end of a
   * round if all remaining players pass or replace their tiles, or - at the end of a turn if a
   * player has placed all tiles in its possession, or - there are no players left after a turn.
   * <p>
   * Once a player has requested the placement of tiles, the current round of turns cannot end the
   * game even if this player drops out (for whatever reason) and even if all other players pass or
   * request exchanges.
   */
  //TODO: investigate if stateless player causes bug
  public boolean isGameOver() {
    return (this.players.isEmpty() || isAllPassOrExchange() ||
        ((activePlayer() == null) && (this.players.isEmpty())) ||
        this.placedAllTilesInHand);
  }

  /**
   * Returns true if all players pass or exchange in one round, otherwise returns false.
   */
  private boolean isAllPassOrExchange() {
    int counter = 0;
    for (Move move : this.playerActionsInRound) {
      if (move.equals(Move.PASS) || move.equals(Move.EXCHANGE)) {
        counter++;
      }
    }
    return counter == this.numPlayersAtRoundStart;
  }

  /**
   * Inform all players if they win or not.
   */
  public void win() {
    for (int i = 0; i < this.players.size(); i++) {
      IPlayer currPlayer = this.players.get(i);
      try {
        Utils.runVoidMethodWithTimeout(() -> currPlayer.win(winners.contains(currPlayer)), this.playerCallTimeOut,
                "Win: player took to long to execute method");
      } catch (SecurityException | TimeoutException e) {
        removePlayer(currPlayer);
        winners.remove(currPlayer);
        i--;
      }
    }
  }

  /**
   * Returns all winners when this game is over.
   */
  protected List<IPlayer> getWinners() {
    int maxScore = 0;
    List<IPlayer> winners = new ArrayList<>();

    for (int i = 0; i < players.size(); i++) {
      IPlayer player = players.get(i);
      int playerScore = this.gameState.getPlayersScores().get(player.name());
      if (playerScore > maxScore) {
        maxScore = playerScore;
        winners = new ArrayList<>(List.of(player));
      } else if (playerScore == maxScore) {
        winners.add(player);
      }
    }
    return winners;
  }

  /**
   * Get the names of all players in the given list.
   *
   * @param players
   */
  private List<String> getNames(List<IPlayer> players) {
    List<String> names = new ArrayList<>();
    for (IPlayer p : players) {
      names.add(p.name());
    }
    return names;
  }

//  /**
//   * Helper method that abstracts the runTimeException try catch needed for each method call in the
//   * IPlayer interface (player API)
//   * @param method the method we want to execute
//   * @param timoutMsg error message to be thrown if the call times out
//   * @return the result of calling the specified method (i.e., calling get() on the given supplier)
//   * @throws SecurityException if the player takes to long to execute method, a SecurityException is thrown to identify
//   *         the player as a "badPlayer"
//   */
//  private <T> T callMethodOnPlayer(Callable<T> method, String timoutMsg) throws SecurityException {
//    try {
//      return Utils.runMethodWithTimeout(method, this.playerCallTimeOut, timoutMsg);
//    } catch (TimeoutException e) {
//      throw new SecurityException(timoutMsg);
//    }
//  }
}