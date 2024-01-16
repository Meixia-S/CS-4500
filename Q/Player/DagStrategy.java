package Player;

import java.util.Collections;
import java.util.List;

import Common.Posn;
import Common.Tile;

/**
 * Represents dag strategy that the player can apply when playing Q Game.
 * It chooses the player’s smallest tile that can extend the current map.
 * If there is more than one place, it breaks the tie using the row-column order for coordinates.
 */
public class DagStrategy extends AStrategy {

  /**
   * Get the position where the smallest tile will be placed.
   * Use the row column order for coordinates if there are more than one possible place.
   */
  @Override
  public Posn getBestPlacement() {
    Tile smallestTile = smallestPlaceableTile();
    List<Posn> placeablePosns = getPlaceablePosns(smallestTile);
    Collections.sort(placeablePosns, new Posn.PosnComparator());
    return placeablePosns.get(0);
  }
}