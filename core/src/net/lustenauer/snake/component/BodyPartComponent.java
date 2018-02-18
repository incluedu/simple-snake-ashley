package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 18.02.18.
 *
 * @author Patric Hollenstein
 */
public class BodyPartComponent implements Component, Pool.Poolable{
    /*
     * ATTRIBUTES
     */

    // NOTE: just a flag to indicate that body part was just added
    // we need to avoid collision with just added body parts
    public boolean justAdded;

    /*
     * PUBLIC METHODES
     */
    @Override
    public void reset() {
        justAdded=true;
    }
}
