package Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import Common.Public_Info;
import Common.game_state;
import Common.Map;
import Common.Posn;
import Common.Tile;

/**
 * Abstract class for strategies that players can apply when playing this Q game.
 */
public abstract class AStrategy implements strategy {
  private game_state gameState;

  /**
   * Iteration method.
   * Player can apply a strategy until they get the longest series of placements.
   */
  public Player_Input determineMove(Public_Info publicInfo) {
    this.gameState = new game_state(publicInfo);
    Player_Input playerInput;
    if (determinePlaceableTiles().isEmpty()) {
      playerInput = exchangeOrPass(publicInfo.getRefereeTileNum());
    } else {
      LinkedHashMap<Posn, Tile> placementMove = getLongestSeriesPlacements();
      playerInput = new Player_Input(Move.PLACE, placementMove);
    }
    return playerInput;
  }

  /**
   * Returns the player input when they need to exchange or pass.
   */
  private Player_Input exchangeOrPass(int refTiles) {
    Player_Input playerInput;
    if (refTiles >= 6) {
      playerInput = new Player_Input(Move.EXCHANGE);
    } else {
      playerInput = new Player_Input(Move.PASS);
    }
    return playerInput;
  }

  /**
   * Apply this strategy as far as possible to obtain the longest possible series of placements.
   */
  protected LinkedHashMap<Posn, Tile> getLongestSeriesPlacements() {
    LinkedHashMap<Posn, Tile> placements = new LinkedHashMap<Posn, Tile>();
    List<Tile> placeableTiles = determinePlaceableTiles();

    for (int i = 0; i < placeableTiles.size(); i ++) {
      Tile tile = smallestPlaceableTile();
      Posn posn = getBestPlacement();

      if (gameState.isValidMove(tile, posn)) {
        placements.put(posn, tile);
        gameState.placeTile(tile, posn);
        this.gameState.getActivePlayer().getPlayerTiles().remove(tile);
        placeableTiles = determinePlaceableTiles();
        i --;
      } else {
        break;
      }

    }
    return placements;
  }


  /**
   * Returns list of tiles that can be placed on the board from a players inventory.
   */
  protected List<Tile> determinePlaceableTiles() {
    List<Tile> placeableTiles = new ArrayList<>();
    for (Tile playerTile : this.gameState.getActivePlayer().getPlayerTiles()) {
      if (!getPlaceablePosns(playerTile).isEmpty()) {
        placeableTiles.add(playerTile);
      }
    }
    return placeableTiles;
  }

  /**
   * Determines which coordinates a tile can be placed on.
   * @param playerTile the tile to be placed on the board (if possible)
   * @return A list of posn the given tile can be placed
   */
  protected List<Posn> getPlaceablePosns(Tile playerTile) {

    return new Map(this.gameState.getCurrentMap()).getPossibleMoves(playerTile);
  }

  /**
   * Get the smallest tile from the given list of tiles.
   * The lexicographic ordering on tiles says that tile p is less than tile q
   * if p’s shape is less than q’s. If p’s shape is identical to q’s,
   * p is below q iff p’s color is below q’s.
   */
  public Tile smallestPlaceableTile() throws IllegalArgumentException {
    List<Tile> placeableTiles = determinePlaceableTiles();
    if(placeableTiles.isEmpty()) {
      throw new IllegalArgumentException("No tiles to place");
    }
    List<Tile> copyTiles = new ArrayList<>(placeableTiles);
    Collections.sort(copyTiles, new Tile.TileComparator());
    return copyTiles.get(0);
  }

  /**
   * Determines the best placement for the tiles in the player's inventory based on its desired
   * strategy
   * @return a single placement coordinate for the smallest tile
   */
  public abstract Posn getBestPlacement();

  /**
   * Get a copy of the game map.
   */
  protected HashMap<Posn, Tile> getMapCopy() {
    return new HashMap<>(this.gameState.getCurrentMap());
  }
}