package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 18.02.18.
 *
 * @author Patric Hollenstein
 */
public class CoinComponent implements Component,Pool.Poolable{
    /*
     * ATTRIBUTES
     */
    public boolean available;

    /*
     * PUBLIC METHODES
     */
    @Override
    public void reset() {
        available = false;
    }
}
