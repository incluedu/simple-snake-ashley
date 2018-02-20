package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 20.02.18.
 *
 * @author Patric Hollenstein
 */
public class TextureComponent implements Component, Pool.Poolable{

    /*
     * ATTRIBUTES
     */
    public TextureRegion region;



    /*
     * PUBLIC METHODES
     */
    @Override
    public void reset() {
        region = null;
    }
}
