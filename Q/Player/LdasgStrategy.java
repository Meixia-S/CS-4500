package Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import Common.Posn;
import Common.Tile;

/**
 * Represents ldasg strategy that the player can apply when playing Q Game.
 * It chooses the player’s smallest tile that can extend the current map.
 * If there is more than one place, it picks the one that has the most existing neighbors,
 * that is, the most constrained one. Ties among those most-constrained places are broken
 * using the row-column order for coordinates.
 */
public class LdasgStrategy extends AStrategy {

  /**
   * Gets the list of Posns that have the highest number of neighbors
   */
  protected List< Posn> getMostNeighborsPosns() {
    Tile smallestTile = smallestPlaceableTile();
    List<Posn> placeablePosns = getPlaceablePosns(smallestTile);
    HashMap<Integer, List<Posn>> neighborCounter = new HashMap<>();

    for (Posn placeablePosn : placeablePosns) {

      Posn[] adjacentPosns = placeablePosn.adjacentPosns();
      int counter = 0;
      for (Posn adjacentPosn : adjacentPosns) {
        if (this.getMapCopy().containsKey(adjacentPosn)) {
          counter++;
        }
      }

      if (!neighborCounter.containsKey(counter)) {
        List<Posn> posnList = new ArrayList< Posn>();
        posnList.add(placeablePosn);
        neighborCounter.put(counter, posnList);
      } else {
        neighborCounter.get(counter).add(placeablePosn);
      }
    }
    return getMostNeighborPosnsHelper(neighborCounter);
  }

  /**
   * Get the list of Posns that has the highest number of neighbors.
   * @param posnsHashMap a hashmap of possible positions and the number of neighbors for each one
   */
  private List<Posn> getMostNeighborPosnsHelper(HashMap<Integer, List<Posn>> posnsHashMap) {
    int maxNumOfNeighbors = 0;
    for (Integer numOfNeighbors: posnsHashMap.keySet()) {
      maxNumOfNeighbors = Math.max(numOfNeighbors, maxNumOfNeighbors);
    }
    return posnsHashMap.get(maxNumOfNeighbors);
  }

  /**
   * Get the position where the smallest tile will be placed.
   * Use the row column order for coordinates if there are more than one possible place.
   */
  @Override
  public Posn getBestPlacement() {
    List<Posn> mostNeighborsPosns = getMostNeighborsPosns();
    Collections.sort(mostNeighborsPosns, new Posn.PosnComparator());
    return mostNeighborsPosns.get(0);
  }

}