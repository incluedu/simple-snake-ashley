package net.lustenauer.snake.common;

/**
 * Created by Patric Hollenstein on 08.02.18.
 *
 * @author Patric Hollenstein
 */
public enum GameState {
    READY,
    PLAYING,
    GAME_OVER;

    /*
     * PUBLIC METHODES
     */
    public boolean isReady() {
        return this == READY;
    }

    public boolean isPlaying() {
        return this == PLAYING;
    }

    public boolean isGameOver() {
        return this == GAME_OVER;
    }

}
