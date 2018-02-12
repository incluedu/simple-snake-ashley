package net.lustenauer.snake.entity;

import net.lustenauer.snake.config.GameConfig;

/**
 * Created by Patric Hollenstein on 06.02.18.
 *
 * @author Patric Hollenstein
 */
@Deprecated
public class Coin extends EntityBase {

    /*
     * ATTRIBUTES
     */
    private boolean available;


    /*
     * CONSTRUCTORS
     */
    public Coin() {
        setSize(GameConfig.COIN_SIZE, GameConfig.COIN_SIZE);
    }

    /*
     * PUBLIC METHODES
     */

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
