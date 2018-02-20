package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 20.02.18.
 *
 * @author Patric Hollenstein
 */
public class ZOrderComponent implements Component, Pool.Poolable {
    /*
     * ATTRIBUTES
     */
    public int z = -1;

    /*
     * PUBLIC METHODES
     */
    @Override
    public void reset() {
        z = -1;
    }
}
