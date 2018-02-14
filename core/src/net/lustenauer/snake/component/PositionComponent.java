package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 13.02.18.
 *
 * @author Patric Hollenstein
 */
public class PositionComponent implements Component, Pool.Poolable{

    /*
     * ATTRIBUTES
     */
    public float x;
    public float y;

    /*
     * PUBLIC METHODES
     */
    @Override
    public void reset() {
        x = 0f;
        y = 0f;
    }
}
