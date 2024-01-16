//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import Common.Posn;
//import Common.Tile;
//import Common.Map;
//import Common.TileColor;
//import Common.TileShape;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;

//public class TestMap {
//  Tile refTile = new Tile(TileShape.STAR, TileColor.RED);
//
//  public Map setUpMap() {
//    Map map = new Map(this.refTile);
//
//    // Add Blue Square to red star's right
//    Tile blueSquare = new Tile(TileShape.SQUARE, TileColor.GREEN);
//    map.extendMap(blueSquare, new Posn(0,1));
//
//    // Add yellow circle to red star's left
//    Tile yellCircle = new Tile(TileShape.CIRCLE, TileColor.YELLOW);
//    map.extendMap(yellCircle, new Posn(0,-1));
//
//    // add purple diamond to blue square's bottom
//    Tile purpDia = new Tile(TileShape.DIAMOND, TileColor.PURPLE);
//    map.extendMap(purpDia, new Posn(1,1));
//
//    // add green 8star to blue square's top
//    Tile greenEight = new Tile(TileShape.EIGHT_STAR, TileColor.GREEN);
//    map.extendMap(greenEight, new Posn(-1,1));
//
//    return map;
//  }
//
//  @Test
//  public void testExtendMap() {
//    Map map = setUpMap();
//
//    HashMap<Posn, Tile> expectedTiles = new HashMap<Posn, Tile>();
//    expectedTiles.put(new Posn(0, 0), new Tile(TileShape.STAR, TileColor.RED));
//    Tile blueSquare = new Tile(TileShape.SQUARE, TileColor.GREEN);
//    expectedTiles.put(new Posn(0,1), blueSquare);
//    Tile yellCircle = new Tile(TileShape.CIRCLE, TileColor.YELLOW);
//    expectedTiles.put(new Posn(0,-1), yellCircle);
//    Tile purpDia = new Tile(TileShape.DIAMOND, TileColor.PURPLE);
//    expectedTiles.put(new Posn(1,1), purpDia);
//    Tile greenEight = new Tile(TileShape.EIGHT_STAR, TileColor.GREEN);
//    expectedTiles.put(new Posn(-1,1), greenEight);
//
//    assertEquals(expectedTiles, map.getTiles());
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testExtendBoardException() {
//    Map map = setUpMap();
//    map.extendMap(new Tile(TileShape.EIGHT_STAR, TileColor.GREEN), new Posn(2, -1));
//  }
//
//  public Map setUpMap2() {
//    Map map = new Map(new Tile(TileShape.STAR, TileColor.RED));
//    map.extendMap(new Tile(TileShape.EIGHT_STAR, TileColor.RED), new Posn(1,0));
//    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.RED), new Posn(2,0));
//    map.extendMap(new Tile(TileShape.STAR, TileColor.GREEN), new Posn(0,1));
//
//    return map;
//  }
//
////  @Test
////  public void testCanBePlaced() {
////    Map map = setUpMap2();
////    // matches top color, left shape
////    assertTrue(map.matchesNeighbors(new Tile(TileShape.EIGHT_STAR, TileColor.GREEN),
////            new Posn(1, 1)));
////    // matches left shape, not top
////    assertFalse(map.matchesNeighbors(new Tile(TileShape.EIGHT_STAR, TileColor.PURPLE), new Posn(1, 1)));
////
////    // matches top color, left right shape
////    map.extendMap(new Tile(TileShape.STAR, TileColor.PURPLE),
////            new Posn(0, 2));
////    map.extendMap(new Tile(TileShape.EIGHT_STAR, TileColor.GREEN),
////            new Posn(1, 2));
////    assertTrue(map.matchesNeighbors(new Tile(TileShape.EIGHT_STAR, TileColor.GREEN),
////            new Posn(1, 1)));
////
////    // matches left right shape, not top
////    assertFalse(map.matchesNeighbors(new Tile(TileShape.EIGHT_STAR, TileColor.BLUE),
////            new Posn(1, 1)));
////
////    // matches right color
////    assertTrue(map.matchesNeighbors(new Tile(TileShape.STAR, TileColor.PURPLE),
////            new Posn(0, -1)));
////
////    // matches right shape
////    assertTrue(map.matchesNeighbors(new Tile(TileShape.DIAMOND, TileColor.ORANGE),
////            new Posn(2, -1)));
////
////    // doesn't have neighbors
////    // This case returns TRUE but the given tile will always be adjacent when canBePlaced() is called
////    assertTrue(map.matchesNeighbors(new Tile(TileShape.STAR, TileColor.PURPLE),
////            new Posn(5, 5)));
////
////    // matches top bottom color, left right shape
////    map.extendMap(new Tile(TileShape.DIAMOND, TileColor.GREEN), new Posn(2, 1));
////    assertTrue(map.matchesNeighbors(new Tile(TileShape.EIGHT_STAR, TileColor.GREEN),
////            new Posn(1, 1)));
////
////    // matches bottom shape
////    assertTrue(map.matchesNeighbors(new Tile(TileShape.STAR, TileColor.ORANGE),
////            new Posn(-1, 0)));
////
////    // matches bottom color
////    assertTrue(map.matchesNeighbors(new Tile(TileShape.SQUARE, TileColor.RED),
////            new Posn(-1, 0)));
////
////    // matches bottom color and shape
////    assertTrue(map.matchesNeighbors(new Tile(TileShape.STAR, TileColor.RED),
////            new Posn(-1, 0)));
////
////    // matches nothing
////    assertFalse(map.matchesNeighbors(new Tile(TileShape.CIRCLE, TileColor.YELLOW),
////            new Posn(1, 1)));
////  }
//
//  @Test
//  public void testGetPossibleMoves() {
//    Map map = setUpMap2();
//    ArrayList<Posn> expectedResult = new ArrayList<Posn>();
//    expectedResult.add(new Posn(1,-1));
//    expectedResult.add(new Posn(1,1));
//    expectedResult.add(new Posn(0, 2));
//    expectedResult.add(new Posn(-1,1));
//
//    List<Posn> actual = map.getPossibleMoves(new Tile(TileShape.EIGHT_STAR, TileColor.GREEN));
//    assertEquals(expectedResult, actual);
//  }
//
//  @Test
//  public void testGetPossibleMovesNoMoves() {
//    Map map = setUpMap2();
//    List<Posn> actual = map.getPossibleMoves(new Tile(TileShape.SQUARE, TileColor.ORANGE));
//    assertEquals(new ArrayList<Posn>(), actual);
//  }
//}
