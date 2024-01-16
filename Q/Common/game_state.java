package Common;

import static Common.Tile.jTileToTile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Represents all the information a referee needs to run the Q game. It keeps track of the current
 * state of the map and players. It also keeps track of the order of players' turns and methods to
 * retrieve all the information a player needs for their turn.
 */
public class game_state implements ObservableGameState {

  private Map map; // the current state of the map
  private List<Tile> pile; // pile of referee's Tiles to draw from
  private List<PlayerState> players; // the players’ states ordered in which they take turns
  private PlayerState activePlayer; // The active player's (the player whose turn it is) index in this.players
  private HashMap<Posn, Tile> currentTurnAction;   // The coordinates of Tile the active Player has placed in their current turn.
  private List<Posn> alreadyPointedRow;  // The coordinates we have already pointed on the row
  private List<Posn> alreadyPointedCol;  // The coordinates we have already pointed on the column
  private int qBonus = 8;
  private int endBonus = 4;

  /**
   * Initializes this game state with a game map, the referee's pile of tiles, and the players.
   */

  /**
   * The new constructor created for milestone 10
   * @param map the starting map
   * @param pile the referee tile inventory
   * @param players the player states of the player participating in this game
   * @param scoreConfig a class that contains the qBonus and endBonus values
   */
  public game_state(Map map, List<Tile> pile, List<PlayerState> players, ScoringConfig scoreConfig) {
    this.map = map;
    this.pile = pile;
    this.players = players;
    this.activePlayer = this.players.get(0);
    this.currentTurnAction = new HashMap<>();
    this.alreadyPointedRow = new ArrayList<Posn>();
    this.alreadyPointedCol = new ArrayList<Posn>();
    this.qBonus = scoreConfig.qBonus();
    this.endBonus = scoreConfig.endBonus();
  }

  public game_state(List<String> playerNames) {
    // mapping over a stream and creating a PlayerState based of the names given
    List<PlayerState> players = playerNames.stream()
            .map(PlayerState::new)
            .toList();
    List<Tile> pile = generateRefTiles(true);
    Map map = new Map(pile.remove(0));
    dealTiles(pile, players);
    this.map = map;
    this.pile = pile;
    this.players = players;
    this.activePlayer = this.players.get(0);
    this.currentTurnAction = new HashMap<>();
    this.alreadyPointedRow = new ArrayList<Posn>();
    this.alreadyPointedCol = new ArrayList<Posn>();
  }

  public game_state(Public_Info publicInfo) {
    this.map = new Map(publicInfo.getMap());
    this.pile = new ArrayList<>(publicInfo.getRefereeTileNum());
    this.activePlayer = publicInfo.getActivePlayer().getCopy();
    this.currentTurnAction = new HashMap<>();
    this.alreadyPointedRow = new ArrayList<Posn>();
    this.alreadyPointedCol = new ArrayList<Posn>();
  }

  private game_state(Map map, LinkedHashMap<Posn, Tile> currentTurnAction, List<Tile> pile,
                     PlayerState currentPlayer, List<PlayerState> players) {
    this.map = map;
    this.pile = pile;
    this.currentTurnAction = currentTurnAction;
    this.activePlayer = currentPlayer;
    this.alreadyPointedRow = new ArrayList<Posn>();
    this.alreadyPointedCol = new ArrayList<Posn>();
    this.players = players;
  }

  // we do not need this one
  public game_state(Map map, List<Tile> pile, List<PlayerState> players) {
    if (players.size() < 1) {
      throw new IllegalArgumentException("Cannot initialize a game with 0 players.");
    }
    this.players = players;
    this.map = map;
    this.pile = pile;
    this.activePlayer = this.players.get(0);
    this.currentTurnAction = new HashMap<>();
    this.alreadyPointedRow = new ArrayList<Posn>();
    this.alreadyPointedCol = new ArrayList<Posn>();
  }

  private static void dealTiles(List<Tile> pile, List<PlayerState> players) {
    for (PlayerState player : players) {
      List<Tile> newHand = pile.subList(0, 6);
      removeHandTiles(pile);
      player.setPlayerInitialTiles(newHand);
    }
  }

  private static void removeHandTiles(List<Tile> pile) {
    for (int i = 0; i < 6; i++) {
      pile.remove(0);
    }
  }

  private static List<Tile> generateRefTiles(boolean shuffle) {
    List<Tile> tiles = new ArrayList<>();
    for (TileColor color : TileColor.values()) {
      for (TileShape shape : TileShape.values()) {
        for (int i = 0; i < 30; i++) {
          tiles.add(new Tile(shape, color));
        }
      }
    }
    if (shuffle) {
      Collections.shuffle(tiles);
    }
    return tiles;
  }

  /**
   * ----------------------------------------------------------------------------------- Returns the
   * list this game's players' names in the order in which they take turn. this.players should
   * already be ordered based on birthday.
   */
  private List<String> getPlayerNameOrder() {
    List<String> playerNamesOrder = new ArrayList<String>();
    for (PlayerState player : this.players) {
      playerNamesOrder.add(player.getName());
    }
    return playerNamesOrder;
  }

  /**
   * Public information that the referee shows the active player
   */
  public Public_Info getPublicInfo() {
    return new Public_Info(this.activePlayer.getCopy(), getAvailableTiles(),
        new ArrayList<>(getPlayersScores().values()), getRefereeRemainingTiles(), getCurrentMap());
  }

  /**
   * @return tiles that are placed in the current turn.
   */
  public HashMap<Posn, Tile> getCurrentTurnAction() {
    return this.currentTurnAction;
  }


  /**
   * @return the list of player states
   */
  public List<PlayerState> getPlayerStates() {
    return this.players.stream()
        .map(PlayerState::getCopy)
        .toList();
  }

  public PlayerState getPlayerState(String name) {
    for (PlayerState playerState : this.players) {
      if (playerState.getName().equals(name)) {
        return playerState;
      }
    }
    throw new IllegalArgumentException("no such player");
  }

  /**
   * @return a copy of referee pile of tiles
   */
  public List<Tile> getTilePile() {
    return new ArrayList<>(this.pile);
  }

  // METHODS for extracting the data to be sent to a player when it is its turn;

  /**
   * Returns a copy of the current map of this game state.
   */
  public HashMap<Posn, Tile> getCurrentMap() {
    return this.map.getTiles();
  }

  /**
   * Returns a copy of the active Player's tiles of this game state.
   */
  public List<Tile> getAvailableTiles() {
    return new ArrayList<>(this.activePlayer.getPlayerTiles());
  }

  /**
   * Returns the all the Players' scores.
   */
  public HashMap<String, Integer> getPlayersScores() {
    HashMap<String, Integer> scores = new HashMap<String, Integer>();
    for (PlayerState player : this.players) {
      scores.put(player.getName(), player.getPlayerScore());
    }

    return scores;
  }

  /**
   * Returns the number of remaining tiles of the referee's pile that the player can draw in this
   * game state.
   */
  public int getRefereeRemainingTiles() {
    return this.pile.size();
  }

  /**
   * Updates the game so that the next Player has their turn. EFFECT: updates this.activePlayerIdx
   * to the next Player's idx.
   */
  public void updateActivePlayer() {
    if (this.players.isEmpty()) {
      return;
    } else if (this.players.size() == 1) {
      this.activePlayer = this.players.get(0);
    } else {
      int activePlayerIdx = this.players.indexOf(this.activePlayer);
      int nextPlayerIdx = (activePlayerIdx + 1) % this.players.size();
      this.activePlayer = this.players.get(nextPlayerIdx);
    }
    this.currentTurnAction = new HashMap<>();
  }

  /**
   * @return active player.
   */
  public PlayerState getActivePlayer() {
    return this.activePlayer;
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * Places the given Tile on this map at the given location for the active Player if this move
   * satisfies the rules of the Q Game
   *
   * @throws IllegalArgumentException if the Tile cannot be placed at the given coordinate because
   *                                  it doesn't match its neighbors or if the Player doesn't have
   *                                  that Tile.
   */
  public void placeTile(Tile tile, Posn posn) throws IllegalArgumentException {
    if (!this.doesPlayerHaveTile(tile)) {
      throw new IllegalArgumentException("The active Player doesn't have this tile.\n");
    }

    if (!(this.isValidMove(tile, posn))) {
      throw new IllegalArgumentException("The tile cannot be placed there.\n");
    }

    placeTileHelper(tile, posn);
  }

  /**
   * Places the tile on this map and updates this game state if the player has the tile and their
   * move is valid. This is a helper for placeTile method. EFFECT: if it's a valid move, adds the
   * tile to the map, removes the tile from the player's roster, adds the posn to
   * this.currentTurnPosns
   */
  protected void placeTileHelper(Tile tile, Posn posn) {
    this.map.extendMap(tile, posn);
    this.currentTurnAction.put(posn, tile);
    this.activePlayer.removePlayerTile(tile);
  }

  /**
   * Update this game state when a player is removed
   */
  public void updateWhenPlayerRemoved(int removedPlayerIdx) {
    this.pile.addAll(this.players.get(removedPlayerIdx).getPlayerTiles());

    if (this.players.indexOf(this.activePlayer) == removedPlayerIdx) {
      this.currentTurnAction = new HashMap<>();
      this.activePlayer = this.players.get((removedPlayerIdx + 1) % this.players.size());
    }

    // actually removing the player form game state knowledge
    this.players.remove(removedPlayerIdx);
  }

  /**
   * Can the active player place the given tile at posn? The given tile must share a side with at
   * least one tile on the map that it extends, shape or color matching rules, and in the same row
   * or col as the previous Tiles placed during this turn.
   */
  public boolean isValidMove(Tile tile, Posn posn) {
    return this.map.getPossibleMoves(tile).contains(posn) && isInSameRowOrCol(posn);
  }

  /**
   * @return
   */
  public java.util.Map<Posn, Tile> generateLegalPlacements() {
    java.util.Map<Posn, Tile> legalPlacements = new HashMap<>();

    for (Tile tile : this.activePlayer.getPlayerTiles()) {
      List<Posn> possiblePosns = this.map.getPossibleMoves(tile);
      for (Posn pos : possiblePosns) {
        if (isValidMove(tile, pos)) {
          legalPlacements.put(pos, tile);
        }
      }
    }
    return legalPlacements;
  }

  /**
   * @return
   */
  public game_state copy() {
    List<PlayerState> copyOfPlayers = this.players.stream().map(PlayerState::getCopy).toList();
    return new game_state(new Map(this.getCurrentMap()),
        new LinkedHashMap<>(this.currentTurnAction),
        new ArrayList<>(this.pile), this.activePlayer.getCopy(), copyOfPlayers);
  }

  /**
   * @return true if all placements are in the same row, false if not
   */
  public boolean isInSameRowOrCol(Posn posn) {
    return isInSameRow(posn) || isInSameCol(posn);
  }

  /**
   * Determines if all the tiles places share the same row
   *
   * @return true if all placements are in the same row, false if not
   */
  private boolean isInSameRow(Posn posn) {
    if (this.currentTurnAction.size() == 0) {
      return true;
    }

    int row = posn.getRow();
    for (Posn key : this.currentTurnAction.keySet()) {
      if (key.getRow() != row) {
        return false;
      }
    }
    return true;
  }

  /**
   * Determines if all the tiles places share the same col
   *
   * @return true if all placements are in the same col, false if not
   */
  private boolean isInSameCol(Posn posn) {
    if (this.currentTurnAction.size() == 0) {
      return true;
    }

    int col = posn.getCol();
    for (Posn key : this.currentTurnAction.keySet()) {
      if (key.getCol() != col) {
        return false;
      }
    }
    return true;
  }

  /**
   * Does the active Player have the given tile in their roster?
   *
   * @return
   */
  private boolean doesPlayerHaveTile(Tile tile) {
    return this.activePlayer.getPlayerTiles().contains(tile);
  }

  /**
   * Withdraws Tiles from the this.pile so that the active player has 6 tiles. If there are not
   * enough tiles in the pile, the player gets all the remaining tiles.
   */
  public List<Tile> replacePlayersTiles() {
    int numTilesToBeReplaced = 6 - this.activePlayer.getPlayerTiles().size();
    List<Tile> replacedTiles;

    if (this.pile.size() < numTilesToBeReplaced) {
      replacedTiles = this.withdrawTiles(this.pile.size());
    } else {
      replacedTiles = this.withdrawTiles(numTilesToBeReplaced);
    }

    this.activePlayer.updatePlayerTiles(replacedTiles);
    return replacedTiles;
  }

  /**
   * Lets the active player to place all their tiles into the referee tile pile
   *
   * @throws IllegalArgumentException when there isn't enough tiles left to exchange
   */
  public void exchangeTiles() throws IllegalArgumentException {
    this.pile.addAll(this.activePlayer.getPlayerTiles());
    for (Tile tile : this.activePlayer.getPlayerTiles()) {
      this.activePlayer.removePlayerTile(tile);
    }
  }

  /**
   * @return the list of tiles referee hands player when they exchange
   */
  public List<Tile> exchangeHandNewTiles() {
    if (this.pile.size() < 6) {
      throw new IllegalArgumentException("There are not enough tiles left to exchange.\n");
    }
    List<Tile> withdrawnTiles = this.withdrawTiles(6);
    this.activePlayer.updatePlayerTiles(withdrawnTiles);
    return withdrawnTiles;
  }

  /**
   * Withdraws the first n tiles from this pile or all of the remaining tiles in the pile if there
   * are not enough tiles.
   *
   * @param drawTilesNumber
   */
  public List<Tile> withdrawTiles(int drawTilesNumber) {
    List<Tile> refereeTiles = this.pile.subList(0, drawTilesNumber);
    int lenPile = this.pile.size();
    List<Tile> newPile = this.pile.subList(drawTilesNumber, lenPile);
    this.pile = newPile;

    return refereeTiles;
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * Getting the largest Y value on the Map
   */
  private int getDiff(int num1, int num2) {
    return (Math.abs(num1) + Math.abs(num2)) + 1;
  }

  /**
   * Getting the size of the Map with the largest and smallest row and column values.
   */
  private List<Integer> getMapSize() {
    int maxRow = 0;
    int minRow = 0;
    int maxCol = 0;
    int minCol = 0;
    for (Posn key : this.map.getTiles().keySet()) {
      maxRow = Math.max(key.getRow(), maxRow);
      minRow = Math.min(key.getRow(), minRow);
      maxCol = Math.max(key.getCol(), maxCol);
      minCol = Math.min(key.getCol(), minCol);
    }
    List<Integer> mapSize = new ArrayList<Integer>(Arrays.asList(maxRow, minRow, maxCol, minCol));
    return mapSize;
  }

  /**
   * Converts Posns of the map tiles to positive values by adding minRow and minCol in order to
   * render the map
   *
   * @param minRow
   * @param minCol
   */
  public Map makeMapPosnsPositive(int minRow, int minCol) {
    if (minRow == 0 && minCol == 0) {
      return this.map;
    }

    HashMap<Posn, Tile> updatedTiles = new HashMap<Posn, Tile>();
    for (java.util.Map.Entry<Posn, Tile> entry : this.map.getTiles().entrySet()) {
      Posn currPos = entry.getKey();
      int positiveRow = currPos.getRow() + Math.abs(minRow);
      int positiveCol = currPos.getCol() + Math.abs(minCol);
      Posn positivePosn = new Posn(positiveRow, positiveCol);
      updatedTiles.put(positivePosn, entry.getValue());
    }
    return new Map(updatedTiles);
  }

  /**
   * ----------------------------------------------------------------------------------------------
   * Gets the score a player got by their placements in one turn This will be added to the player's
   * score
   *
   * @return the score the player received from one turn of placements
   */
  public int turnScore() {
    int score = this.currentTurnAction.size();

    for (Posn key : this.currentTurnAction.keySet()) {
      int currRow = key.getRow();
      int currCol = key.getCol();

      score = score + rowScoreHelper(currRow, currCol);
      score = score + colScoreHelper(currRow, currCol);
    }

    this.alreadyPointedRow = new ArrayList<>();
    this.alreadyPointedCol = new ArrayList<>();
    score += getAllTilesPlacedBonus();
    return score;
  }

  /**
   * @return Gets the total score of a column by calling on a helper
   */
  private int rowScoreHelper(int currRow, int currCol) {
    List<TileColor> sequenceColors = new ArrayList<>();
    List<TileShape> sequenceShapes = new ArrayList<>();
    int addScore = 0;

    addCurrentTileToQSets(currRow, currCol, sequenceColors, sequenceShapes);
    // go to the left
    addScore += gettingRowTotalScore(-1, currRow, currCol, sequenceColors, sequenceShapes);
    // go to the right
    addScore += gettingRowTotalScore(1, currRow, currCol, sequenceColors, sequenceShapes);
    // row bonus
    addScore += getQBonus(sequenceColors, sequenceShapes);

    return addScore;
  }

  /**
   * @return Gets the total score of a row by calling on a helper
   */
  private int colScoreHelper(int currRow, int currCol) {
    List<TileColor> sequenceColors = new ArrayList<>();
    List<TileShape> sequenceShapes = new ArrayList<>();
    int addScore = 0;

    addCurrentTileToQSets(currRow, currCol, sequenceColors, sequenceShapes);
    // check top tile
    addScore += gettingColTotalScore(-1, currRow, currCol, sequenceColors, sequenceShapes);
    // check bottom tile
    addScore += gettingColTotalScore(1, currRow, currCol, sequenceColors, sequenceShapes);
    // col bonus
    addScore += getQBonus(sequenceColors, sequenceShapes);

    return addScore;
  }

  /**
   * Get the total score for a row.
   *
   * @param direction      which direction the remaining tiles in the row are
   * @param row            the row of the current tile we are pointing
   * @param col            the column of the current tile we are pointing (in the currentTurnAction
   *                       array)
   * @param sequenceColors the set that keeps track of the colors that have already been covered
   * @param sequenceShapes the set that keeps track of the shape that have already been covered
   * @return the score of the points from the entire row
   */
  private int gettingRowTotalScore(int direction, int row, int col,
      List<TileColor> sequenceColors, List<TileShape> sequenceShapes) {
    int score = 0;
    if (!alreadyOnMap(row, col - 1) && !alreadyOnMap(row, col + 1)) {
      return 0;
    }
    while (alreadyOnMap(row, col)) {
      if (!alreadyAccountedFor(this.alreadyPointedRow, row, col)) {
        Posn addPosn = new Posn(row, col);
        this.alreadyPointedRow.add(addPosn);
        score++;

        updateQColorSet(this.map.getTiles().get(addPosn).getColor(), sequenceColors);
        updateQShapeSet(this.map.getTiles().get(addPosn).getShape(), sequenceShapes);
      }
      col = col + direction;
    }
    return score;
  }

  /**
   * Counts the amount of points a placement get when going in the given direction
   *
   * @param direction the direction the line of tiles is going
   * @param row       the row of the tile has been placed
   * @param col       the col of the tile has been placed
   * @return the number of points the player gets from the tiles in the given direction
   */
  private int gettingColTotalScore(int direction, int row, int col,
      List<TileColor> sequenceColors, List<TileShape> sequenceShapes) {
    int score = 0;
    if (!alreadyOnMap(row - 1, col) && !alreadyOnMap(row + 1, col)) {
      return 0;
    }
    while (alreadyOnMap(row, col)) {
      if (!alreadyAccountedFor(this.alreadyPointedCol, row, col)) {
        Posn addPosn = new Posn(row, col);
        this.alreadyPointedCol.add(addPosn);
        score++;

        updateQColorSet(this.map.getTiles().get(addPosn).getColor(), sequenceColors);
        updateQShapeSet(this.map.getTiles().get(addPosn).getShape(), sequenceShapes);
      }
      row = row + direction;
    }
    return score;
  }

  /**
   * Determines whether-or-not a tile exists on the board on a given coordinate
   *
   * @param row the x coordinate of the tile
   * @param col the y coordinate of the tile
   * @return true if a tile is placed on the given coordinate and false if it does not
   */
  private boolean alreadyOnMap(int row, int col) {
    Posn pos = new Posn(row, col);
    return (this.map.getTiles().containsKey(pos));
  }

  /**
   * Adds the color of the tile that is currently being pointed
   *
   * @param color          the color that we want to add to the set
   * @param sequenceColors the set that keeps track of the colors that have already been covered
   */
  private void updateQColorSet(TileColor color, List<TileColor> sequenceColors) {
    sequenceColors.add(color);
  }

  /**
   * Adds the shape of the tile that is currently being pointed
   *
   * @param shape          the shape that we want to add to the set
   * @param sequenceShapes the set that keeps track of the shape that have already been covered
   */
  private void updateQShapeSet(TileShape shape, List<TileShape> sequenceShapes) {
    sequenceShapes.add(shape);
  }

  /**
   * Adds the tile color and shape to the arrays that keep track of those two characteristics
   *
   * @param row      the row the tile is on
   * @param col      the col the tile is on
   * @param colorSet the set that keeps track of the colors that have already been covered
   * @param shapeSet the set that keeps track of the shapes that have already been covered
   */
  private void addCurrentTileToQSets(int row, int col, List<TileColor> colorSet,
      List<TileShape> shapeSet) {
    updateQColorSet(this.currentTurnAction.get(new Posn(row, col)).getColor(), colorSet);
    updateQShapeSet(this.currentTurnAction.get(new Posn(row, col)).getShape(), shapeSet);
  }

  /**
   * Determines whether not a given tile has already been accounted for
   *
   * @param alreadyPointed all the tiles we have already accounted for point wise
   * @param row            the row in which the tile in question exists on
   * @param col            the col in which the tile in question exists on
   * @return whether-or-not the tile has already been pointed
   */
  private boolean alreadyAccountedFor(List<Posn> alreadyPointed, int row, int col) {
    Posn posn = new Posn(row, col);
    return alreadyPointed.contains(posn);
  }

  /**
   * Determines if the player gets the 6 bonus points for making a Q or
   *
   * @param colorQ the set that keeps track of the colors that have already been covered
   * @param shapeQ the set that keeps track of the shapes that have already been covered
   * @return the number of bonus points get added to the score
   */
  protected int getQBonus(List<TileColor> colorQ, List<TileShape> shapeQ) {
    int bonus = 0;
    if ((colorQ.size() == TileColor.values().length
        && new HashSet<>(colorQ).size() == TileColor.values().length)
        || (shapeQ.size() == TileShape.values().length
        && new HashSet<>(shapeQ).size() == TileShape.values().length)) {
      bonus = qBonus;
      return bonus;
    }
    return bonus;
  }

  /**
   * Return 6 bonus points for placing all their tiles, otherwise 0.
   */
  private int getAllTilesPlacedBonus() {
    int bonus = 0;
    //TODO: this only works if called after tiles removed, before new tiles dealt
    if (this.activePlayer.getPlayerTiles().isEmpty()) {
      bonus = endBonus;
    }
    return bonus;
  }

  /**
   * ----------------------------------------------------------------------------------------------
   * Converts jPile list of jTile to the referee's list of tiles.
   *
   * @param jPile
   */
  public static List<Tile> jPileToRefereePile(JsonArray jPile) {
    List<Tile> refereePile = new ArrayList<>();
    for (JsonElement e : jPile) {
      JsonObject jTile = e.getAsJsonObject();
      Tile tile = jTileToTile(jTile);
      refereePile.add(tile);
    }
    return refereePile;
  }

  /** --------------------------------------------------------------------------------------------*/
  /**
   * Retrieves the dimensions of the map
   * @return
   */
  private List<Integer> mapDimensions() {
    List<Integer> mapSize = getMapSize();
    int rowNumDiff = getDiff(mapSize.get(0), mapSize.get(1));
    int colNumDiff = getDiff(mapSize.get(2), mapSize.get(3));
    int rowNum = mapSize.get(1);
    int colNum = mapSize.get(3);
    List<Integer> mapDem = new ArrayList<>();
    mapDem.add(rowNumDiff);
    mapDem.add(colNumDiff);
    mapDem.add(rowNum);
    mapDem.add(colNum);
    return mapDem;
  }

  /**
   * @return a JPanel of the current game state
   */
  @Override
  public JPanel toPanel() {
    List<Integer> mapDem = mapDimensions();
    Map gameMap = makeMapPosnsPositive(Math.abs(mapDem.get(2)), Math.abs(mapDem.get(3)));
    if (this.players == null) {
      throw new RuntimeException("There are no players in this game");
    }
    Game_State_GUI gameStateGui = new Game_State_GUI(gameMap, this.pile.size(), mapDem.get(0), mapDem.get(1), this.players);
    return gameStateGui.buildPanel();
  }

  /**
   * @return converts the JPanel into an Image
   */
  @Override
  public BufferedImage toImage() {
    int width = 900;
    int height = 800;
    List<Integer> mapDem = mapDimensions();
    int rowNum = mapDem.get(0);
    int colNum = mapDem.get(1);
    if (rowNum > 8 && colNum > 8) {
      width = 2000;
      height = 1000;
    } else if (rowNum > 8 && colNum < 8) {
      height = 1000;
    } else if (rowNum < 8 && colNum > 8) {
      width = 2000;
    }

    JFrame bufferedFrameImg = new JFrame();
    bufferedFrameImg.add(this.toPanel());
    bufferedFrameImg.pack();
    Container c = bufferedFrameImg.getContentPane();
    BufferedImage img =
            new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    c.paint(img.getGraphics());
    return img;
  }

  @Override
  public JsonObject toJSON() {
    JsonObject jState = new JsonObject();
    jState.add("map", JSON_Processor.makeJMap(this.map.getTiles()));
    JsonArray jTiles = new JsonArray();
    for (Tile t : this.pile) {
      jTiles.add(t.tileTojTile());
    }
    jState.add("tile*", jTiles);
    JsonArray players = new JsonArray();
    for (PlayerState p : this.players) {
      players.add(p.playerStateToJPlayer());
    }
    jState.add("players", players);
    return jState;
  }
}