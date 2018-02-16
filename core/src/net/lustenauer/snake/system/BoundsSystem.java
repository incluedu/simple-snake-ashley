package net.lustenauer.snake.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.lustenauer.snake.component.BoundsComponent;
import net.lustenauer.snake.component.DimensionComponent;
import net.lustenauer.snake.component.PositionComponent;
import net.lustenauer.snake.util.Mappers;

/**
 * Created by Patric Hollenstein on 16.02.18.
 *
 * @author Patric Hollenstein
 */
public class BoundsSystem extends IteratingSystem{
    /*
     * CONSTANTS
     */
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            BoundsComponent.class
    ).get();


    /*
     * CONSTRUCTORS
     */
    public BoundsSystem() {
        super(FAMILY);
    }

    /*
     * PROCESS
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMEMSION.get(entity);
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);

        bounds.rectangle.setPosition(position.x,position.y);
        bounds.rectangle.setSize(dimension.width,dimension.height);
    }
}
