package Tests;

import Common.*;
import Referee.Observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverTest {

    static Observer o;
    static List<ObservableGameState> states;

    public static void main(String[] args) {
        init();
        o.view();
    }

    public static void init() {

        Common.Map map = new Common.Map(new Tile(TileShape.CIRCLE, TileColor.RED));

        List<PlayerState> players = new ArrayList<>();
        PlayerState ps = new PlayerState(new ArrayList<>(), "TestPlayer", 0);
        PlayerState ps2 = new PlayerState(new ArrayList<>(), "TestPlayer", 10);
        players.add(ps);
        players.add(ps2);

        List<Tile> tilePile = new ArrayList<Tile>();
        tilePile.add(new Tile(TileShape.CLOVER, TileColor.ORANGE));
        tilePile.add(new Tile(TileShape.STAR, TileColor.BLUE));
        tilePile.add(new Tile(TileShape.DIAMOND, TileColor.PURPLE));
        tilePile.add(new Tile(TileShape.EIGHT_STAR, TileColor.YELLOW));
        tilePile.add(new Tile(TileShape.CIRCLE, TileColor.RED));
        tilePile.add(new Tile(TileShape.DIAMOND, TileColor.GREEN));

        ObservableGameState gs = new game_state(map, tilePile, players);

        Common.Map map2 = new Common.Map(new Tile(TileShape.SQUARE, TileColor.BLUE));
        ObservableGameState gs2 = new game_state(map2, tilePile, players);

        states = new ArrayList<>();
        states.add(gs);
        states.add(gs2);

        o = new Observer("N/A");
        o.update(states.get(0));
        o.update(states.get(1));

    }
}
