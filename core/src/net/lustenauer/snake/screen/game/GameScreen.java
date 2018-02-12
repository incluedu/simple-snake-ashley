package net.lustenauer.snake.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import net.lustenauer.snake.SimpleSnakeGame;
import net.lustenauer.snake.util.GdxUtils;

/**
 * Created by Patric Hollenstein on 12.02.18.
 *
 * @author Patric Hollenstein
 */
public class GameScreen extends ScreenAdapter{
    /*
     * ATTRIBUTES
     */

    private final SimpleSnakeGame game;


    /*
     * CONSTRUCTORS
     */
    public GameScreen(SimpleSnakeGame game) {
        this.game = game;
    }

    /*
     * PUBLIC METHODES
     */

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
    }




}
