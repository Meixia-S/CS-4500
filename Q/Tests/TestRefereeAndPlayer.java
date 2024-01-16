//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import Common.Map;
//import Common.PlayerState;
//import Common.Posn;
//import Common.Public_Info;
//import Common.Tile;
//import Common.TileColor;
//import Common.TileShape;
//import Common.game_state;
//import Player.Move;
//import Player.Player_Input;
//import Player.player;
//import Referee.referee;

//public class TestRefereeAndPlayer {

//    public Map mapSetUp1() {
//    Map map = new Map(new Tile(TileShape.STAR, TileColor.RED));
//    return map;
//    }
//
//    public List<player> players1() {
//        List<player> players = new ArrayList<player>();
//
//        List<Tile> ps1tiles = new ArrayList<>();
//        PlayerState ps1 = new PlayerState(ps1tiles, new Date(2000, 12, 03), "Jenny", 0);
//        player p1 = new player(ps1);
//
//        List<Tile> ps2tiles = new ArrayList<>();
//        PlayerState ps2 = new PlayerState(ps1tiles, new Date(1990, 12, 20), "Meixia", 0);
//        player p2 = new player(ps2);
//
//        players.add(p1);
//        players.add(p2);
//        return players;
//    }
//
//    public game_state gameStateSetpUp1() {
//        List<Tile> tilePile = new ArrayList<Tile>();
//        tilePile.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//        tilePile.add(new Tile(TileShape.STAR, TileColor.RED));
//        tilePile.add(new Tile(TileShape.CIRCLE, TileColor.GREEN));
//        tilePile.add(new Tile(TileShape.EIGHT_STAR, TileColor.RED));
//        tilePile.add(new Tile(TileShape.DIAMOND, TileColor.ORANGE));
//        tilePile.add(new Tile(TileShape.DIAMOND, TileColor.BLUE));
//
//        tilePile.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//        tilePile.add(new Tile(TileShape.STAR, TileColor.RED));
//        tilePile.add(new Tile(TileShape.SQUARE, TileColor.PURPLE));
//        tilePile.add(new Tile(TileShape.EIGHT_STAR, TileColor.RED));
//        tilePile.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//        tilePile.add(new Tile(TileShape.CIRCLE, TileColor.BLUE));
//
//        tilePile.add(new Tile(TileShape.CIRCLE, TileColor.RED));
//        tilePile.add(new Tile(TileShape.SQUARE, TileColor.GREEN));
//        tilePile.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//        tilePile.add(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW));
//        tilePile.add(new Tile(TileShape.STAR, TileColor.PURPLE));
//        tilePile.add(new Tile(TileShape.DIAMOND, TileColor.BLUE));
//
//        List<PlayerState> playerStates = new ArrayList<>();
//        playerStates.add(players1().get(0).getPlayerState());
//        playerStates.add(players1().get(1).getPlayerState());
//
//        game_state gameState = new game_state(mapSetUp1(), tilePile, playerStates);
//        return gameState;
//    }
//
//    public referee referee1() {
//        referee referee1 = new referee(players1(), gameStateSetpUp1());
//        return referee1;
//    }
//
//    @Test
//    public void testSetUp() {
//        referee1().setUpPlayers();
//        int player1Score = players1().get(0).getPlayerTileNum();
//        int player2Score = players1().get(1).getPlayerTileNum();;
//        int tilePileNum = gameStateSetpUp1().getTilePile().size();
//
//        assertEquals(6, player1Score);
//        assertEquals(6, player2Score);
//        assertEquals(6, tilePileNum);
//    }
//
//    @Test
//    public void testGameOverAllTilesPlaced() {
//      Map m = new Map(new Tile(TileShape.DIAMOND, TileColor.RED));
//
//      List<Tile> ps1Tiles = new ArrayList<>();
//      ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.STAR, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.CIRCLE, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.EIGHT_STAR, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//      PlayerState ps1 = new PlayerState(ps1Tiles, new Date(2000, 12, 03), "Jenny", 0);
//      player p1 = new player(ps1);
//
//      PlayerState ps2 = new PlayerState(ps1Tiles, new Date(1990, 12, 20), "Meixia", 0);
//      player p2 = new player(ps2);
//
//      List<PlayerState> playerStates = new ArrayList<>();
//      playerStates.add(p1.getPlayerState());
//      playerStates.add(p2.getPlayerState());
//
//      List<Tile> tilePile = new ArrayList<Tile>();
//      tilePile.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//
//      game_state gameState = new game_state(m, tilePile, playerStates);
//
//      referee r = new referee(new ArrayList<>(Arrays.asList(p1, p2)), gameState);
//
//      r.processPlayerAction();
//      // p1 can place all tiles
//      assertEquals(true, r.isGameOver());
//    }
//
//    @Test
//    public void testGameOverNoPlayerLeft() {
//      Map m = new Map(new Tile(TileShape.DIAMOND, TileColor.RED));
//
//      List<Tile> ps1Tiles = new ArrayList<>();
//      ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.STAR, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.CIRCLE, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.EIGHT_STAR, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//      PlayerState ps1 = new PlayerState(ps1Tiles, new Date(2000, 12, 03), "Jenny", 0);
//      player p1 = new player(ps1);
//
//      PlayerState ps2 = new PlayerState(ps1Tiles, new Date(1990, 12, 20), "Meixia", 0);
//      player p2 = new player(ps2);
//
//      List<PlayerState> playerStates = new ArrayList<>();
//      playerStates.add(p1.getPlayerState());
//      playerStates.add(p2.getPlayerState());
//
//      List<Tile> tilePile = new ArrayList<Tile>();
//      tilePile.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//
//      game_state gameState = new game_state(m, tilePile, playerStates);
//
//      referee r = new referee(new ArrayList<>(Arrays.asList(p1, p2)), gameState);
//
//      // remove players for exchanging when referee has 1 tile
//      gameState.exchangeHandNewTiles();
//      gameState.updateActivePlayer();
//      gameState.exchangeHandNewTiles();
//      assertEquals(true, r.isGameOver());
//    }
//
//    @Test
//    public void testGameOverPassExchange() {
//      Map m = new Map(new Tile(TileShape.DIAMOND, TileColor.GREEN));
//
//      List<Tile> ps1Tiles = new ArrayList<>();
//      ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.STAR, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.CIRCLE, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.EIGHT_STAR, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//      ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//      PlayerState ps1 = new PlayerState(ps1Tiles, new Date(2000, 12, 03), "Jenny", 0);
//      player p1 = new player(ps1);
//
//      PlayerState ps2 = new PlayerState(ps1Tiles, new Date(1990, 12, 20), "Meixia", 0);
//      player p2 = new player(ps2);
//
//      List<PlayerState> playerStates = new ArrayList<>();
//      playerStates.add(p1.getPlayerState());
//      playerStates.add(p2.getPlayerState());
//
//      List<Tile> tilePile = new ArrayList<Tile>();
//      tilePile.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//
//      game_state gameState = new game_state(m, tilePile, playerStates);
//
//      referee r = new referee(new ArrayList<>(Arrays.asList(p1, p2)), gameState);
//
//      // both players need to pass as the tile on map is green, and they have only red tiles
//      r.processPlayerAction();
//      r.processPlayerAction();
//      assertEquals(true, r.isGameOver());
//    }
//
//    @Test
//    public void testRemovePlayer() {
//      referee1().removePlayer();
//      assertEquals(1, referee1().playerNum());
//
//      referee1().removePlayer();
//      assertEquals(0, referee1().playerNum());
//    }
//    //--------------------------P L A Y E R -- T E S T S-------------------------------------------------
//    public Map mapSetUp2() {
//        Map map = new Map(new Tile(TileShape.STAR, TileColor.RED));
//        map.extendMap(new Tile(TileShape.DIAMOND, TileColor.RED), new Posn(0, 1));
//        map.extendMap(new Tile(TileShape.STAR, TileColor.RED), new Posn(0, 2));
//        map.extendMap(new Tile(TileShape.CIRCLE, TileColor.GREEN), new Posn(0,-1));
//        map.extendMap(new Tile(TileShape.EIGHT_STAR, TileColor.RED), new Posn(0, -2));
//        return map;
//    }
//
//    @Test
//    public void testName() {
//        String name = players1().get(1).name();
//        assertEquals("Jenny", name);
//    }
//
//    @Test
//    public void testSetup() {
//        List<Tile> ps1Tiles = new ArrayList<Tile>();
//        ps1Tiles.add(new Tile(TileShape.DIAMOND, TileColor.RED));
//        ps1Tiles.add(new Tile(TileShape.STAR, TileColor.PURPLE));
//        ps1Tiles.add(new Tile(TileShape.CIRCLE, TileColor.GREEN));
//
//        players1().get(0).getPlayerTiles().remove(0);
//        players1().get(0).getPlayerTiles().remove(1);
//        players1().get(0).getPlayerTiles().remove(2);
//        players1().get(0).getPlayerTiles().remove(3);
//
//        players1().get(0).setup(mapSetUp2(), ps1Tiles);
//        int newTileNum = players1().get(0).getPlayerTileNum();
//        assertEquals(mapSetUp2(), players1().get(0).getPublicInfo().getMap());
//        assertEquals(5, newTileNum);
//    }
//
//    public Public_Info publicInfo1() {
//        PlayerState activePlayer = players1().get(1).getPlayerState();
//
//        List<String> playerOrder = new ArrayList<>();
//        playerOrder.add(players1().get(0).name());
//        playerOrder.add(players1().get(2).name());
//
//        List<Tile> availableTile = gameStateSetpUp1().getTilePile();
//
//        HashMap<String, Integer> playersScores = new HashMap<>();
//        playersScores.put("Jenny", 0);
//        playersScores.put("Meixia", 0);
//
//        int refereeRemainingTiles = gameStateSetpUp1().getRefereeRemainingTiles();
//
//        HashMap<Posn, Tile> map = mapSetUp2().getTiles();
//
//        Public_Info publicInfo = new Public_Info(activePlayer, playerOrder, availableTile, playersScores, refereeRemainingTiles, map);
//
//        return publicInfo;
//    }
//
//    @Test
//    public void testTakeTurn() {
//      Player_Input playerInput = players1().get(0).takeTurn(publicInfo1());
//      assertEquals(Move.PLACE, playerInput.getPlayerMove());
//    }
//
//    public Public_Info publicInfo2() {
//      PlayerState activePlayer = players1().get(1).getPlayerState();
//
//      List<String> playerOrder = new ArrayList<>();
//      playerOrder.add(players1().get(0).name());
//      playerOrder.add(players1().get(2).name());
//
//      List<Tile> availableTile = gameStateSetpUp1().getTilePile();
//
//      HashMap<String, Integer> playersScores = new HashMap<>();
//      playersScores.put("Jenny", 0);
//      playersScores.put("Meixia", 0);
//
//      HashMap<Posn, Tile> map = mapSetUp2().getTiles();
//
//      Public_Info publicInfo = new Public_Info(activePlayer, playerOrder, availableTile, playersScores, 2, map);
//
//      return publicInfo;
//    }
//
//    @Test
//    public void testTakeTurn2() {
//      players1().get(1).getPlayerTiles().remove(0);
//      players1().get(1).getPlayerTiles().remove(1);
//      players1().get(1).getPlayerTiles().remove(2);
//      players1().get(1).getPlayerTiles().remove(3);
//      players1().get(1).getPlayerTiles().remove(4);
//      players1().get(1).getPlayerTiles().remove(5);
//      players1().get(1).getPlayerTiles().add(new Tile(TileShape.CLOVER,TileColor.BLUE));
//      players1().get(1).getPlayerTiles().add(new Tile(TileShape.SQUARE,TileColor.BLUE));
//      players1().get(1).getPlayerTiles().add(new Tile(TileShape.CLOVER,TileColor.ORANGE));
//      players1().get(1).getPlayerTiles().add(new Tile(TileShape.SQUARE,TileColor.ORANGE));
//      players1().get(1).getPlayerTiles().add(new Tile(TileShape.CLOVER,TileColor.YELLOW));
//      players1().get(1).getPlayerTiles().add(new Tile(TileShape.SQUARE,TileColor.YELLOW));
//      Player_Input playerInput = players1().get(1).takeTurn(publicInfo2());
//      assertEquals(Move.PASS, playerInput.getPlayerMove());
//    }
//
//    public Public_Info publicInfo3() {
//      PlayerState activePlayer = players1().get(1).getPlayerState();
//
//      List<String> playerOrder = new ArrayList<>();
//      playerOrder.add(players1().get(0).name());
//      playerOrder.add(players1().get(2).name());
//
//      List<Tile> availableTile = gameStateSetpUp1().getTilePile();
//
//      HashMap<String, Integer> playersScores = new HashMap<>();
//      playersScores.put("Jenny", 0);
//      playersScores.put("Meixia", 0);
//
//      HashMap<Posn, Tile> map = mapSetUp2().getTiles();
//
//      Public_Info publicInfo = new Public_Info(activePlayer, playerOrder, availableTile, playersScores, 7, map);
//
//      return publicInfo;
//    }
//
//    @Test
//    public void testTakeTurn3() {
//      Player_Input playerInput = players1().get(0).takeTurn(publicInfo3());
//      assertEquals(Move.EXCHANGE, playerInput.getPlayerMove());
//    }
//
//    @Test
//    public void testNewTilesPlace() {
//      List<Tile> tiles = new ArrayList<>();
//      tiles.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//      tiles.add(new Tile(TileShape.CIRCLE, TileColor.BLUE));
//
//      Player_Input playerInput = new Player_Input(Move.PLACE, 10);
//      players1().get(0).setPlayerInput(playerInput);
//      players1().get(0).getPlayerTiles().remove(0);
//      players1().get(0).getPlayerTiles().remove(1);
//      players1().get(0).newTiles(tiles);
//      assertEquals(6, players1().get(0).getPlayerTiles().size());
//
//      tiles.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//      tiles.add(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW));
//      tiles.add(new Tile(TileShape.STAR, TileColor.PURPLE));
//      tiles.add(new Tile(TileShape.DIAMOND, TileColor.BLUE));
//
//      assertEquals(tiles, players1().get(0).getPlayerTiles());
//    }
//
//    @Test
//    public void testNewTilesExchange() {
//      List<Tile> tiles = new ArrayList<>();
//      tiles.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//      tiles.add(new Tile(TileShape.CIRCLE, TileColor.BLUE));
//      tiles.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
//      tiles.add(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW));
//      tiles.add(new Tile(TileShape.STAR, TileColor.PURPLE));
//      tiles.add(new Tile(TileShape.DIAMOND, TileColor.BLUE));
//
//      Player_Input playerInput = new Player_Input(Move.EXCHANGE, 20);
//      players1().get(0).setPlayerInput(playerInput);
//      players1().get(0).newTiles(tiles);
//      assertEquals(6, players1().get(0).getPlayerTiles().size());
//      assertEquals(tiles, players1().get(0).getPlayerTiles());
//    }
//
//    @Test
//    public void testWin() {
//      players1().get(1).win(true);
//      assertEquals(true,  players1().get(1).getWinnerField());
//    }
//
//    @Test
//    public void testWin2() {
//      players1().get(0).win(false);
//      assertEquals(false,  players1().get(1).getWinnerField());
//    }
//}