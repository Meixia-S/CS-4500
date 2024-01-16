package Player;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Common.Posn;
import Common.Public_Info;
import Common.Tile;
import Common.game_state;

public class DFSStrategy implements strategy {
  private game_state gameState;

  @Override
  public Player_Input determineMove(Public_Info publicInfo) {
    this.gameState = new game_state(publicInfo);

    if (determinePlaceableTiles().size() == 0 &&
            this.gameState.getTilePile().size() < this.gameState.getActivePlayer().getPlayerTiles().size()) {
      return new Player_Input(Move.PASS);
    } else if (determinePlaceableTiles().size() == 0 &&
            this.gameState.getTilePile().size() < this.gameState.getActivePlayer().getPlayerTiles().size()) {
      return new Player_Input(Move.EXCHANGE);
    } else {
      LinkedHashMap<Posn, Tile> turnAction = recursiveTurnGenerator(new LinkedHashMap<>(),
              this.gameState, new AbstractMap.SimpleEntry<>(0, new LinkedHashMap<>())).getValue();
      return new Player_Input(Move.PLACE, turnAction);
    }
  }

  /**
   * Returns list of tiles that can be placed on the board from a players inventory.
   */
  protected List<Tile> determinePlaceableTiles() {
    List<Tile> placeableTiles = new ArrayList<>();
    for (Tile playerTile : this.gameState.getActivePlayer().getPlayerTiles()) {
      if (getPlaceablePosns(playerTile).size() > 0) {
        placeableTiles.add(playerTile);
      }
    }
    return placeableTiles;
  }

  /**
   * Determines which coordinates a tile can be placed on.
   *
   * @param playerTile the tile to be placed on the board (if possible)
   * @return A list of posn the given tile can be placed
   */
  protected List<Posn> getPlaceablePosns(Tile playerTile) {
    List<Posn> possiblePosns = new ArrayList<Posn>();
    List<Posn> possibleMoves = new Common.Map(this.gameState.getCurrentMap()).getPossibleMoves(playerTile);

    if (possibleMoves.size() > 0) {
      for (Posn posn : possibleMoves) {
        if (gameState.isInSameRowOrCol(posn)) {
          possiblePosns.add(posn);
        }
      }
    }
    return possiblePosns;
  }

  /**
   *
   * @param placementsSoFar
   * @param currentState
   * @param highestScoringTurn
   * @return
   */
  private Map.Entry<Integer, LinkedHashMap<Posn, Tile>> recursiveTurnGenerator(
          LinkedHashMap<Posn, Tile> placementsSoFar, game_state currentState,
          Map.Entry<Integer, LinkedHashMap<Posn, Tile>> highestScoringTurn) {
    int currentScore = currentState.turnScore();

    if (currentScore > highestScoringTurn.getKey()) {
      highestScoringTurn = new AbstractMap.SimpleEntry<>(currentScore, placementsSoFar);
    }

    for (Map.Entry<Posn, Tile> placement : currentState.generateLegalPlacements().entrySet()) {
      LinkedHashMap<Posn, Tile> updatedPlacements = new LinkedHashMap<>(placementsSoFar);
      updatedPlacements.put(placement.getKey(), placement.getValue());
      highestScoringTurn = getHigherScoring(highestScoringTurn,
              recursiveTurnGenerator(updatedPlacements, currentState.copy(), highestScoringTurn));

    }
    return highestScoringTurn;
  }

  /**
   *
   * @param highestSoFar
   * @param newTurn
   * @return
   */
  private Map.Entry<Integer, LinkedHashMap<Posn, Tile>> getHigherScoring(
          Map.Entry<Integer, LinkedHashMap<Posn, Tile>> highestSoFar,
          Map.Entry<Integer, LinkedHashMap<Posn, Tile>> newTurn) {

    if (highestSoFar.getKey() < newTurn.getKey()) {
      return newTurn;
    } else {
      return highestSoFar;
    }
  }
}