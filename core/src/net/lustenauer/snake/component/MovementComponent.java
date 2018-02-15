package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 15.02.18.
 *
 * @author Patric Hollenstein
 */
public class MovementComponent implements Component, Pool.Poolable{

    /*
     * ATTRIBUTES
     */
    public float xSpeed;
    public float ySpeed;


    /*
     * PUBLIC METHODES
     */

    @Override
    public void reset() {
        xSpeed = 0f;
        ySpeed = 0f;
    }
}
