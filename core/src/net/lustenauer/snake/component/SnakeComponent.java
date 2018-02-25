package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 14.02.18.
 *
 * @author Patric Hollenstein
 */
public class SnakeComponent implements Component, Pool.Poolable {
    /*
     * ATTRIBUTES
     */
    public final Array<Entity> bodyParts = new Array<Entity>();
    public Entity head;


    /*
     * PUBLIC METHODES
     */
    @Override
    public void reset() {
        head = null;
        bodyParts.clear();
    }

    public boolean hasBodyParts() {
        return bodyParts.size > 0;
    }
}
