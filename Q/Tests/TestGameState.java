//import org.junit.Test;
//import Common.game_state;
//import Common.Posn;
//import Common.Tile;
//import Common.Map;
//import Common.TileColor;
//import Common.TileShape;
//import static org.junit.Assert.assertEquals;
//import java.util.HashMap;

//public class TestGameState {
//
//  Tile redCircle = new Tile(TileShape.CIRCLE, TileColor.RED);
//  Tile redSquare = new Tile(TileShape.SQUARE, TileColor.RED);
//  Tile redDiamond = new Tile(TileShape.DIAMOND, TileColor.RED);
//  Tile redStar = new Tile(TileShape.STAR, TileColor.RED);
//  Tile red8Star = new Tile(TileShape.EIGHT_STAR, TileColor.RED);
//  Tile redClover = new Tile(TileShape.CLOVER, TileColor.RED);
//
//  Tile blueClover = new Tile(TileShape.CLOVER, TileColor.BLUE);
//  Tile purpleClover = new Tile(TileShape.CLOVER, TileColor.PURPLE);
//  Tile yellowClover = new Tile(TileShape.CLOVER, TileColor.YELLOW);
//  Tile orangeClover = new Tile(TileShape.CLOVER, TileColor.ORANGE);
//  Tile greenClover = new Tile(TileShape.CLOVER, TileColor.GREEN);
//
//  Tile purpleStar = new Tile(TileShape.STAR, TileColor.PURPLE);
//  Tile purple8Star = new Tile(TileShape.EIGHT_STAR, TileColor.PURPLE);
//  Tile purpleSquare = new Tile(TileShape.SQUARE, TileColor.PURPLE);
//
//  Tile yellowCircle = new Tile(TileShape.CIRCLE, TileColor.YELLOW);
//  Tile yellowSquare = new Tile(TileShape.SQUARE, TileColor.YELLOW);
//  Tile yellow8Star = new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW);
//  Tile green8Star = new Tile(TileShape.EIGHT_STAR, TileColor.GREEN);
//  Tile orangeSquare = new Tile(TileShape.SQUARE, TileColor.ORANGE);
//
//  Tile yellowDiamond = new Tile(TileShape.DIAMOND, TileColor.YELLOW);
//  Tile orangeDiamond = new Tile(TileShape.DIAMOND, TileColor.ORANGE);
//  Tile greenDiamond = new Tile(TileShape.DIAMOND, TileColor.GREEN);
//  Tile blueDiamond = new Tile(TileShape.DIAMOND, TileColor.BLUE);
//  Tile purpleDiamond = new Tile(TileShape.DIAMOND, TileColor.PURPLE);
//
//  public Map setUpMap0() {
//    Map map = new Map(redCircle);
//    return map;
//  }
//
//  public game_state gameState0() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(1, 0), redSquare);
//    game_state gameState = new game_state(setUpMap0(), currentTurnAction);
//    gameState.placeTile(redSquare, new Posn(1, 0));
//    return gameState;
//  }
//
//  /**
//   * Testing like colors
//   */
//  @Test
//  public void testRunScore0() {
//    int actualScore = gameState0().turnScore();
//    assertEquals(4, actualScore);
//  }
//
//  public Map setUpMap1() {
//    Map map = new Map(redCircle);
//    map.extendMap(redSquare, new Posn(1, 0));
//    map.extendMap(yellowCircle, new Posn(0, 1));
//    return map;
//  }
//
//  public game_state gameState1() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(1, 1), yellowSquare);
//    game_state gameState = new game_state(setUpMap1(), currentTurnAction);
//    gameState.placeTile(yellowSquare, new Posn(1, 1));
//    return gameState;
//  }
//
//  /**
//   * Testing like shapes and colors (mini L)
//   */
//  @Test
//  public void testRunScore1() {
//    int actualScore = gameState1().turnScore();
//    assertEquals(6, actualScore);
//  }
//
//  public Map setUpMap2() {
//    Map map = new Map(redCircle);
//    map.extendMap(redSquare, new Posn(0, -1));
//    map.extendMap(redDiamond, new Posn(0, -2));
//    map.extendMap(redStar, new Posn(0, 1));
//    map.extendMap(red8Star, new Posn(0, 2));
//    return map;
//  }
//
//  public game_state gameState2() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(0, 3), redClover);
//    game_state gameState = new game_state(setUpMap2(), currentTurnAction);
//    gameState.placeTile(redClover, new Posn(0, 3));
//    return gameState;
//  }
//
//  /**
//   * Testing 1 Q (horizontal capital I)
//   */
//  @Test
//  public void testRunScore2() {
//    int actualScore = gameState2().turnScore();
//    assertEquals(18, actualScore);
//  }
//
//  public Map setUpMap3() {
//    Map map = new Map(redCircle);
//    map.extendMap(redSquare, new Posn(1, 0));
//    map.extendMap(redDiamond, new Posn(2, 0));
//    map.extendMap(redStar, new Posn(3, 0));
//    map.extendMap(red8Star, new Posn(-1, 0));
//    return map;
//  }
//
//  public game_state gameState3() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(-2, 0), redClover);
//    game_state gameState = new game_state(setUpMap3(), currentTurnAction);
//    gameState.placeTile(redClover, new Posn(-2, 0));
//    return gameState;
//  }
//
//  /**
//   * Testing 1 Q (capital I)
//   */
//  @Test
//  public void testRunScore3() {
//    int actualScore = gameState3().turnScore();
//    assertEquals(18, actualScore);
//  }
//
//  public Map setUpMap4() {
//    Map map = new Map(redCircle);
//    map.extendMap(redSquare, new Posn(1, 0));
//    map.extendMap(redDiamond, new Posn(2, 0));
//    map.extendMap(redStar, new Posn(3, 0));
//    map.extendMap(red8Star, new Posn(-1, 0));
//
//    map.extendMap(yellow8Star, new Posn(-1, 1));
//    map.extendMap(green8Star, new Posn(-1, -1));
//
//    map.extendMap(greenClover, new Posn(-2, -1));
//    map.extendMap(purpleClover, new Posn(-2, -2));
//    map.extendMap(blueClover, new Posn(-2, -3));
//    map.extendMap(yellowClover, new Posn(-2, 1));
//    map.extendMap(orangeClover, new Posn(-2, 2));
//
//    return map;
//  }
//
//  public game_state gameState4() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(-2, 0), redClover);
//    game_state gameState = new game_state(setUpMap4(), currentTurnAction);
//    gameState.placeTile(redClover, new Posn(-2, 0));
//    return gameState;
//  }
//
//  /**
//   * Testing 2 Qs (Large T)
//   */
//  @Test
//  public void testRunScore4() {
//    int actualScore = gameState4().turnScore();
//    assertEquals(25, actualScore);
//  }
//
//  public Map setUpMap5() {
//    Map map = new Map(redCircle);
//    map.extendMap(redSquare, new Posn(1, 0));
//    map.extendMap(redDiamond, new Posn(2, 0));
//    map.extendMap(redStar, new Posn(3, 0));
//    map.extendMap(red8Star, new Posn(-1, 0));
//    map.extendMap(redClover, new Posn(-2, 0));
//
//    map.extendMap(greenClover, new Posn(-2, -1));
//    map.extendMap(yellowClover, new Posn(-2, 1));
//    map.extendMap(purpleClover, new Posn(-2, -2));
//    map.extendMap(orangeClover, new Posn(-2, 2));
//    map.extendMap(blueClover, new Posn(-2, -3));
//
//    map.extendMap(purple8Star, new Posn(-1, -2));
//    map.extendMap(purpleStar, new Posn(0, -2));
//    map.extendMap(purpleSquare, new Posn(1, -2));
//    return map;
//  }
//
//  public game_state gameState5() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(1, 1), yellowSquare);
//    currentTurnAction.put(new Posn(1, -1), orangeSquare);
//    game_state gameState = new game_state(setUpMap5(), currentTurnAction);
//    gameState.placeTile(yellowSquare, new Posn(1, 1));
//    gameState.placeTile(orangeSquare, new Posn(1, -1));
//    return gameState;
//  }
////_________________________________________________________________________________
//  /**
//   * Testing two tiles in the same row
//   */
//  @Test
//  public void tes5tRunScore5() {
//    int actualScore = gameState5().turnScore();
//    assertEquals(6, actualScore);
//  }
//
//  public Map setUpMap6() {
//    Map map = new Map(redCircle);
//    map.extendMap(redSquare, new Posn(1, 0));
//
//    return map;
//  }
//
//  public game_state gameState6() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(-1, 0), red8Star);
//    currentTurnAction.put(new Posn(-2, 0), redDiamond);
//    currentTurnAction.put(new Posn(2, 0), redStar);
//    game_state gameState = new game_state(setUpMap6(), currentTurnAction);
//    gameState.placeTile(red8Star, new Posn(-1, 0));
//    gameState.placeTile(redDiamond, new Posn(-2, 0));
//    gameState.placeTile(redStar, new Posn(2, 0));
//    return gameState;
//  }
//
//  /**
//   * Testing two tiles in the same col with 2 placed tiles right next to each other
//   */
//  @Test
//  public void testRunScore6() {
//    int actualScore = gameState6().turnScore();
//    assertEquals(7, actualScore);
//  }
//
//  public Map setUpMap7() {
//    Map map = new Map(redCircle);
//    map.extendMap(redSquare, new Posn(1, 0));
//    map.extendMap(redDiamond, new Posn(2, 0));
//    map.extendMap(redStar, new Posn(3, 0));
//    map.extendMap(red8Star, new Posn(-1, 0));
//
//    map.extendMap(yellow8Star, new Posn(-1, 1));
//    map.extendMap(green8Star, new Posn(-1, -1));
//
//    map.extendMap(greenClover, new Posn(-2, -1));
//    map.extendMap(purpleClover, new Posn(-2, -2));
//    map.extendMap(blueClover, new Posn(-2, -3));
//    map.extendMap(yellowClover, new Posn(-2, 1));
//
//    return map;
//  }
//
//  public game_state gameState7() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(-2, 0), redClover);
//    currentTurnAction.put(new Posn(-2, 2), orangeClover);
//    game_state gameState = new game_state(setUpMap7(), currentTurnAction);
//    gameState.placeTile(redClover, new Posn(-2, 0));
//    gameState.placeTile(orangeClover, new Posn(-2, 2));
//    return gameState;
//  }
//
//  /**
//   * Testing 2 Qs (Large T) pt. 2
//   */
//  @Test
//  public void testRunScore7() {
//    int actualScore = gameState7().turnScore();
//    assertEquals(26, actualScore);
//  }
//
//  public Map setUpMap8() {
//    Map map = new Map(redCircle);
//    return map;
//  }
//
//  public game_state gameState8() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(1, 0), redSquare);
//    currentTurnAction.put(new Posn(2, 0), redDiamond);
//    currentTurnAction.put(new Posn(3, 0), redStar);
//    currentTurnAction.put(new Posn(-1, 0), red8Star);
//    currentTurnAction.put(new Posn(-2, 0), redClover);
//    currentTurnAction.put(new Posn(1, 1), yellowSquare);
//    game_state gameState = new game_state(setUpMap8(), currentTurnAction);
//    gameState.placeTile(redSquare, new Posn(1, 0));
//    gameState.placeTile(redDiamond, new Posn(2, 0));
//    gameState.placeTile(redStar, new Posn(3, 0));
//    gameState.placeTile(red8Star, new Posn(-1, 0));
//    gameState.placeTile(redClover, new Posn(-2, 0));
//    gameState.placeTile(yellowSquare, new Posn(1, 1));
//    return gameState;
//  }
//
//  /**
//   * Testing 1 Q and using all tiles in inventory (vertical)
//   */
//  @Test
//  public void testRunScore8() {
//    int actualScore = gameState8().turnScore();
//    assertEquals(20, actualScore);
//  }
//
//  public Map setUpMap9() {
//    Map map = new Map(redCircle);
//    return map;
//  }
//
//  public game_state gameState9() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(0, 1), redSquare);
//    currentTurnAction.put(new Posn(0, 2), redDiamond);
//    currentTurnAction.put(new Posn(0, 3), redStar);
//    currentTurnAction.put(new Posn(0, -1), red8Star);
//    currentTurnAction.put(new Posn(0, -2), redClover);
//    currentTurnAction.put(new Posn(1, 1), yellowSquare);
//    game_state gameState = new game_state(setUpMap9(), currentTurnAction);
//    gameState.placeTile(redSquare, new Posn(0, 1));
//    gameState.placeTile(redDiamond,new Posn(0, 2));
//    gameState.placeTile(redStar, new Posn(0, 3));
//    gameState.placeTile(red8Star, new Posn(0, -1));
//    gameState.placeTile(redClover, new Posn(0, -2));
//    gameState.placeTile(yellowSquare, new Posn(1, 1));
//    return gameState;
//  }
//
//  /**
//   * Testing 1 Q and using all tiles in inventory (horizontal)
//   */
//  @Test
//  public void testRunScore9() {
//    int actualScore = gameState9().turnScore();
//    assertEquals(20, actualScore);
//  }
//
//  public Map setUpMap10() {
//    Map map = new Map(redCircle);
//    map.extendMap(redSquare, new Posn(0, 1));
//    map.extendMap(redDiamond, new Posn(0, 2));
//    map.extendMap(redStar, new Posn(0, 3));
//    map.extendMap(red8Star, new Posn(0, -1));
//    map.extendMap(redClover, new Posn(0, -2));
//
//    map.extendMap(orangeClover, new Posn(1, -2));
//    map.extendMap(yellowClover, new Posn(2, -2));
//    map.extendMap(greenClover, new Posn(3, -2));
//
//    map.extendMap(purpleDiamond, new Posn(-1, 2));
//    map.extendMap(blueDiamond, new Posn(-2, 2));
//    map.extendMap(greenDiamond, new Posn(-3, 2));
//    return map;
//  }
//
//  public game_state gameState10() {
//    HashMap<Posn, Tile> currentTurnAction = new HashMap<Posn, Tile>();
//    currentTurnAction.put(new Posn(-1, -2), blueClover);
//    currentTurnAction.put(new Posn(-2, -2), purpleClover);
//    currentTurnAction.put(new Posn(1, 2), orangeDiamond);
//    currentTurnAction.put(new Posn(2, 2), yellowDiamond);
//    game_state gameState = new game_state(setUpMap10(), currentTurnAction);
//    gameState.placeTile(blueClover, new Posn(-1, -2));
//    gameState.placeTile(purpleClover, new Posn(-2, -2));
//    gameState.placeTile(orangeDiamond, new Posn(1, 2));
//    gameState.placeTile(yellowDiamond, new Posn(2, 2));
//    return gameState;
//  }
//
//  /**
//   * Testing when there are 2 Q with the same similarity
//   */
//  @Test
//  public void testRunScore10() {
//    int actualScore = gameState10().turnScore();
//    assertEquals(28, actualScore);
//  }
//}