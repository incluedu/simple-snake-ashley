package net.lustenauer.snake.entity;

import net.lustenauer.snake.config.GameConfig;

/**
 * Created by Patric Hollenstein on 04.02.18.
 *
 * @author Patric Hollenstein
 */
public class SnakeHead extends EntityBase {

    /*
     * CONSTRUCTORS
     */
    public SnakeHead() {
        setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
    }

    /*
     * PUBLIC METHODES
     */
    public void updateX(float amount) {
        x += amount;
        updateBounds();
    }

    public void updateY(float amount) {
        y += amount;
        updateBounds();
    }

}
