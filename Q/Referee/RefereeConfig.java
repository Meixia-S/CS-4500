package Referee;

import Common.game_state;

public record RefereeConfig(game_state gameState, int playerCallTimeout, boolean observer) {

}