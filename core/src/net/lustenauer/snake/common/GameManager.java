package net.lustenauer.snake.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import net.lustenauer.snake.SimpleSnakeGame;

/**
 * Created by Patric Hollenstein on 08.02.18.
 *
 * @author Patric Hollenstein
 */
public class GameManager {

    /*
     * CONSTANTS
     */
    public static final GameManager INSTANCE = new GameManager();
    private static final String HIGH_SCORE_KEY = "highScore";

    /*
     * ATTRIBUTES
     */
    private GameState state = GameState.READY;
    private int score;
    private int highScore;
    private int displayScore;
    private int displayHighScore;

    private Preferences prefs;

    /*
     * CONSTRUCTORS
     */
    private GameManager() {
        prefs = Gdx.app.getPreferences(SimpleSnakeGame.class.getSimpleName());
        highScore = prefs.getInteger(HIGH_SCORE_KEY,0);
        reset();
    }

    /*
     * PUBLIC METHODES
     */
    public boolean isReady() {
        return state.isReady();
    }

    public boolean isPlaying() {
        return state.isPlaying();
    }

    public boolean isGameOver() {
        return state.isGameOver();
    }

    public void setPlaying() {
        state = GameState.PLAYING;
    }

    public void setGameOver() {
        state = GameState.GAME_OVER;
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }

    public void incrementScore(int amount) {
        score += amount;

        if (score >= highScore) {
            highScore = score;
        }
    }

    public void reset() {
        setPlaying();
        score = 0;
        displayScore = 0;
        displayHighScore = highScore;
    }

    public void updateDisplayScore(float delta) {
        if (displayScore < score) {
            displayScore = Math.min(score, displayScore + (int) (100 * delta));
        }

        if (displayHighScore < highScore) {
            displayHighScore = Math.min(highScore, displayHighScore + (int) (100 * delta));
        }
    }

    public void updateHighScore(){
        if (score < highScore){
            return;
        }

        highScore = score;
        prefs.putInteger(HIGH_SCORE_KEY,highScore);
        prefs.flush();
    }
}
