package Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is used to test the gui
 */
//public class GuiState {
//    public static void main(String[] args) throws IOException {
//    List<Tile> tilePile = new ArrayList<Tile>();
//    tilePile.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//    tilePile.add(new Tile(TileShape.STAR, TileColor.BLUE));
//    tilePile.add(new Tile(TileShape.DIAMOND, TileColor.PURPLE));
//    tilePile.add(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW));
//    tilePile.add(new Tile(TileShape.CIRCLE, TileColor.RED));
//    tilePile.add(new Tile(TileShape.DIAMOND, TileColor.GREEN));
//
//    List<Tile> availableTile1 = new ArrayList<Tile>();
//    availableTile1.add(new Tile(TileShape.CLOVER, TileColor.PURPLE));
//    availableTile1.add(new Tile(TileShape.STAR, TileColor.RED));
//    availableTile1.add(new Tile(TileShape.DIAMOND, TileColor.BLUE));
//
//    List<Tile> availableTile2 = new ArrayList<Tile>();
//    availableTile2.add(new Tile(TileShape.SQUARE, TileColor.GREEN));
//    availableTile2.add(new Tile(TileShape.EIGHT_STAR, TileColor.PURPLE));
//    availableTile2.add(new Tile(TileShape.CIRCLE, TileColor.ORANGE));
//    availableTile2.add(new Tile(TileShape.CLOVER, TileColor.GREEN));
//
//    PlayerState player1 = new PlayerState(availableTile1,"Ann", 16);
//    PlayerState player2 = new PlayerState(availableTile2, "Kay", 9);
//
//    List<PlayerState> playerStateList = new ArrayList<PlayerState>();
//    playerStateList.add(player1);
//    playerStateList.add(player2);
//
//    Map map = new Map(new Tile(TileShape.CIRCLE, TileColor.RED));
//    map.extendMap(new Tile(TileShape.CLOVER, TileColor.RED), new Posn(0, -1));
//    map.extendMap(new Tile(TileShape.STAR, TileColor.RED), new Posn(0, -2));
//    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.RED), new Posn(0, 1));
//    map.extendMap(new Tile(TileShape.EIGHT_STAR, TileColor.RED), new Posn(0, 2));
//    map.extendMap(new Tile(TileShape.CIRCLE, TileColor.GREEN), new Posn(-1, 0));
//    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.GREEN), new Posn(-1, 1));
//    map.extendMap(new Tile(TileShape.CLOVER, TileColor.GREEN), new Posn(-1, -1));
//    map.extendMap(new Tile(TileShape.CLOVER, TileColor.PURPLE), new Posn(-2, -1));
//    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.PURPLE), new Posn(-2, 1));
//    map.extendMap(new Tile(TileShape.CLOVER, TileColor.BLUE), new Posn(-3, -1));
//    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.BLUE), new Posn(-3, 1));
//    map.extendMap(new Tile(TileShape.CLOVER, TileColor.ORANGE), new Posn(1, -1));
//    map.extendMap(new Tile(TileShape.CIRCLE, TileColor.ORANGE), new Posn(1, 0));
//    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.ORANGE), new Posn(1, 1));
//    map.extendMap(new Tile(TileShape.CLOVER, TileColor.YELLOW), new Posn(2, -1));
//    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.YELLOW), new Posn(2, 1));
//
//    game_state gameState = new game_state(map, tilePile, playerStateList);
//    gameState.toPanel();
//  }
//}