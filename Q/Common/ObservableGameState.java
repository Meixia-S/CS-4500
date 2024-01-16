package Common;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Represents the functionality for observing/viewing a game state.
 * Does not include any functionality for modifying the state.
 */
public interface ObservableGameState {

    /**
     * @return a JPanel representation of this ObservableGameState
     */
    JPanel toPanel();

    /**
     * @return a BufferedImage representation of this ObservableGameState
     */
    BufferedImage toImage();


    /**
     * @return a JSON representation of this ObservableGameState
     */
    JsonObject toJSON();
}