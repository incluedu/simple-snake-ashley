package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 13.02.18.
 *
 * @author Patric Hollenstein
 */
public class DimensionComponent implements Component, Pool.Poolable {
    /*
     * ATTRIBUTES
     */
    public float width = 1f;
    public float height = 1f;

    /*
     * PUBLIC METHODES
     */
    @Override
    public void reset() {
        width = 1f;
        height = 1f;
    }
}
