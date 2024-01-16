package Referee;

import Common.*;

import java.util.ArrayList;
import java.util.List;


//public class xobserver {
//  static observer o;
//  static List<ObservableGameState> states;
//
//  public static void main(String[] args) {
//    init();
//    o.view();
//  }
//
//  public static void init() {
//    List<Tile> availableTile1 = new ArrayList<>();
//    availableTile1.add(new Tile(TileShape.CLOVER, TileColor.PURPLE));
//    availableTile1.add(new Tile(TileShape.STAR, TileColor.RED));
//    availableTile1.add(new Tile(TileShape.DIAMOND, TileColor.BLUE));
//    availableTile1.add(new Tile(TileShape.CIRCLE, TileColor.GREEN));
//    availableTile1.add(new Tile(TileShape.EIGHT_STAR, TileColor.ORANGE));
//    availableTile1.add(new Tile(TileShape.DIAMOND, TileColor.BLUE));
//
//    List<Tile> availableTile2 = new ArrayList<>();
//    availableTile2.add(new Tile(TileShape.SQUARE, TileColor.GREEN));
//    availableTile2.add(new Tile(TileShape.EIGHT_STAR, TileColor.PURPLE));
//    availableTile2.add(new Tile(TileShape.CIRCLE, TileColor.ORANGE));
//    availableTile2.add(new Tile(TileShape.CLOVER, TileColor.GREEN));
//    availableTile2.add(new Tile(TileShape.STAR, TileColor.RED));
//    availableTile2.add(new Tile(TileShape.SQUARE, TileColor.YELLOW));
//
//    List<PlayerState> players = new ArrayList<>();
//    PlayerState ps1 = new PlayerState(availableTile1, "Meixia", 0);
//    PlayerState ps2 = new PlayerState(availableTile2, "Aiden", 0);
//    players.add(ps1);
//    players.add(ps2);
//
//    List<Tile> tilePile = new ArrayList<>();
//    tilePile.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//    tilePile.add(new Tile(TileShape.STAR, TileColor.BLUE));
//    tilePile.add(new Tile(TileShape.DIAMOND, TileColor.PURPLE));
//    tilePile.add(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW));
//    tilePile.add(new Tile(TileShape.CIRCLE, TileColor.RED));
//    tilePile.add(new Tile(TileShape.DIAMOND, TileColor.GREEN));
//
//    Map map = new Map(new Tile(TileShape.CIRCLE, TileColor.RED));
//    ObservableGameState gs1 = new game_state(map, tilePile, players);
//
//    map.extendMap(new Tile(TileShape.STAR, TileColor.RED), new Posn(-1, 0));
//    map.extendMap(new Tile(TileShape.CIRCLE, TileColor.GREEN), new Posn(0, -1));
//    availableTile1.remove(new Tile(TileShape.STAR, TileColor.RED));
//    availableTile1.remove(new Tile(TileShape.CIRCLE, TileColor.GREEN));
//    availableTile1.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//    availableTile1.add(new Tile(TileShape.STAR, TileColor.BLUE));
//    tilePile.remove(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//    tilePile.remove(new Tile(TileShape.STAR, TileColor.BLUE));
//    ObservableGameState gs2 = new game_state(map, tilePile, players);
//
//    map.extendMap(new Tile(TileShape.STAR, TileColor.RED), new Posn(-2, 0));
//    map.extendMap(new Tile(TileShape.CLOVER, TileColor.GREEN), new Posn(0, -2));
//    map.extendMap(new Tile(TileShape.SQUARE, TileColor.GREEN), new Posn(0, -3));
//    map.extendMap(new Tile(TileShape.CIRCLE, TileColor.ORANGE), new Posn(0, 1));
//    map.extendMap(new Tile(TileShape.SQUARE, TileColor.YELLOW), new Posn(-1, -2));
//    availableTile2.remove(new Tile(TileShape.STAR, TileColor.RED));
//    availableTile2.remove(new Tile(TileShape.CLOVER, TileColor.GREEN));
//    availableTile2.remove(new Tile(TileShape.SQUARE, TileColor.GREEN));
//    availableTile2.remove(new Tile(TileShape.CIRCLE, TileColor.ORANGE));
//    availableTile2.remove(new Tile(TileShape.SQUARE, TileColor.YELLOW));
//    availableTile2.add(new Tile(TileShape.DIAMOND, TileColor.PURPLE));
//    availableTile2.add(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW));
//    availableTile2.add(new Tile(TileShape.CIRCLE, TileColor.RED));
//    availableTile2.add(new Tile(TileShape.DIAMOND, TileColor.GREEN));
//    tilePile.remove(new Tile(TileShape.DIAMOND, TileColor.PURPLE));
//    tilePile.remove(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW));
//    tilePile.remove(new Tile(TileShape.CIRCLE, TileColor.RED));
//    tilePile.remove(new Tile(TileShape.DIAMOND, TileColor.GREEN));
//    ObservableGameState gs3 = new game_state(map, tilePile, players);
//
//    map.extendMap(new Tile(TileShape.STAR, TileColor.BLUE), new Posn(-3, 0));
//    map.extendMap(new Tile(TileShape.CLOVER, TileColor.ORANGE), new Posn(0, 2));
//    availableTile1.remove(new Tile(TileShape.STAR, TileColor.RED));
//    availableTile1.remove(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//    ObservableGameState gs4 = new game_state(map, tilePile, players);
//
//    map.extendMap(new Tile(TileShape.CIRCLE, TileColor.RED), new Posn(1, 0));
//    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.GREEN), new Posn(0, -4));
//    map.extendMap(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW), new Posn(-2, -2));
//    map.extendMap(new Tile(TileShape.EIGHT_STAR, TileColor.PURPLE), new Posn(-3, -2));
//    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.PURPLE), new Posn(-4, -2));
//    availableTile2.remove(new Tile(TileShape.DIAMOND, TileColor.PURPLE));
//    availableTile2.remove(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW));
//    availableTile2.remove(new Tile(TileShape.CIRCLE, TileColor.RED));
//    availableTile2.remove(new Tile(TileShape.DIAMOND, TileColor.GREEN));
//    availableTile2.remove(new Tile(TileShape.EIGHT_STAR, TileColor.PURPLE));
//    ObservableGameState gs5 = new game_state(map, tilePile, players);
//
//    states = new ArrayList<>();
//    states.add(gs1);
//    states.add(gs2);
//    states.add(gs3);
//    states.add(gs4);
//    states.add(gs5);
//
//    o = new observer("N/A");
//    o.update(states.get(0));
//    o.update(states.get(1));
//    o.update(states.get(2));
//    o.update(states.get(3));
//    o.update(states.get(4));
//  }
//}