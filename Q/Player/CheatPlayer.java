package Player;

import java.util.LinkedHashMap;
import Common.Public_Info;
import Common.TileColor;
import Common.TileShape;
import java.util.List;
import Common.Tile;
import Common.Posn;
import Common.Map;

/**
 * A CheatPlayer is a Player who returns invalid Player_Input based on the provided cheating mechanism.
 */
public class CheatPlayer extends Player {
  private final String JCheat;
  private Public_Info publicInfo;
  private String name;

  /**
   * Constructs a CheatPlayer.
   * @param JCheat the particular way in which this CheatPlayer "cheats" its placements on the board.
   */
  public CheatPlayer(String name, strategy strategy, String JCheat) {
    super(name, strategy);
    this.JCheat = JCheat;
  }

  @Override
  public Player_Input takeTurn(Public_Info publicInfo) {
    this.publicInfo = publicInfo;
    return switch (this.JCheat) {
      case "non-adjacent-coordinate" -> nonAdjacentCoordinateHelper();
      case "tile-not-owned" -> tileNotOwnedHelper();
      case "not-a-line" -> notALineHelper();
      case "bad-ask-for-tiles" -> new Player_Input(Move.EXCHANGE);
      case "no-fit" -> noFit();
      default -> new Player_Input(Move.PASS);
    };
  }

  /**
   * @return a Player_Input where the move is non-adjacent to any existing
   * tiles on the board.
   */
  private Player_Input nonAdjacentCoordinateHelper() {
    Map map = new Map(this.publicInfo.getMap());
    int maxRow = 0;
    for (Posn key : map.getTiles().keySet()) {
      maxRow = Math.max(key.getRow(), maxRow);
    }
    Posn pos = new Posn(maxRow + 2, 0);
    LinkedHashMap<Posn, Tile> move = new LinkedHashMap<>();
    move.put(pos, this.publicInfo.getActivePlayer().getPlayerTiles().get(0));
    return new Player_Input(Move.PLACE, move);
  }

  /**
   * @return a Player_Input where the move contains tiles not owned by this player.
   */
  private Player_Input tileNotOwnedHelper() {
    List<Tile> inventory = this.publicInfo.getActivePlayer().getPlayerTiles();
    Map map = new Map(this.publicInfo.getMap());
    Tile tile = null;
    Posn pos = null;
    for(TileColor c : TileColor.values()) {
      for (TileShape s : TileShape.values()) {
        tile = new Tile(s, c);
        if(!inventory.contains(tile)) {
          List<Posn> possibleMoves = map.getPossibleMoves(tile);
          if (possibleMoves.size() > 0) {
            pos = possibleMoves.get(0);
            break;
          }
        }
      }
    }
    LinkedHashMap<Posn, Tile> move = new LinkedHashMap<>();
    move.put(pos, tile);
    return new Player_Input(Move.PLACE, move);
  }

  /**
   * @return a Player_Input where the move that does not match any existing
   * tiles on the board.
   */
  private Player_Input noFit() {
    LinkedHashMap<Posn, Tile> move = new LinkedHashMap<>();
    Map map = new Map(this.publicInfo.getMap());
    for (Tile t : this.publicInfo.getActivePlayer().getPlayerTiles()) {
      for (Posn p : map.allOpenNeighbors()) {
        if (!map.matchesNeighbors(t, p)) {
          move.put(p, t);
          break;
        }
      }
    }

    // if there are no tiles that can be placed illegally
    if (move.size() == 0) { return this.strategy.determineMove(publicInfo);
    } else { return new Player_Input(Move.PLACE, move); }
  }


  /**
   * @return A Player_Input with two tile placements that are neither in the same row nor column.
   */
  private Player_Input notALineHelper() {
    List<Tile> tileInInventory = this.publicInfo.getActivePlayer().getPlayerTiles();
    LinkedHashMap<Posn, Tile> move = new LinkedHashMap<>();
    Map map = new Map(this.publicInfo.getMap());
    Posn pos1 = null;
    Posn pos2;

    for(Tile tile: tileInInventory) {
      List<Posn> possibleMoves = map.getPossibleMoves(tile);
      if (possibleMoves.size() > 0 && move.size() < 1) {
        pos1 = possibleMoves.get(0);
        move.put(pos1, tile);
      }
      else if (possibleMoves.size() > 0) {
        for (Posn pos : possibleMoves) {
          if (pos.getCol() != pos1.getCol() && pos.getRow() != pos1.getRow()) {
            pos2 = pos;
            move.put(pos2, tile);
            break;
          }
        }
        break;
      }
    }

    if (move.size() < 2) {
      return this.strategy.determineMove(publicInfo);
    } else {
      return new Player_Input(Move.PLACE, move);
    }
  }
}