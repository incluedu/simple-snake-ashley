package net.lustenauer.snake.util;

import com.badlogic.ashley.core.ComponentMapper;
import net.lustenauer.snake.component.BoundsComponent;
import net.lustenauer.snake.component.DimensionComponent;
import net.lustenauer.snake.component.PositionComponent;

/**
 * Created by Patric Hollenstein on 14.02.18.
 *
 * @author Patric Hollenstein
 */
public final class Mappers {

    /*
     * CONSTANTS
     */
    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMEMSION =
            ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);


    /*
     * CONSTRUCTORS
     */
    private Mappers() {
    }
}
