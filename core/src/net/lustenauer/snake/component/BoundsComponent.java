package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 14.02.18.
 *
 * @author Patric Hollenstein
 */
public class BoundsComponent implements Component, Pool.Poolable {

    /*
     * ATTRIBUTES
     */
    public final Rectangle rectangle = new Rectangle(0, 0, 1, 1);


    /*
     * PUBLIC METHODES
     */
    @Override
    public void reset() {
        rectangle.setPosition(0, 0);
        rectangle.setSize(1, 1);
    }
}
