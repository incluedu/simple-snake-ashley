package net.lustenauer.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.lustenauer.snake.component.PositionComponent;
import net.lustenauer.snake.component.WorldWrapComponent;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.util.Mappers;

/**
 * Created by Patric Hollenstein on 18.02.18.
 *
 * @author Patric Hollenstein
 */
public class WorldWrapSystem extends IteratingSystem {
    /*
     * CONSTANTS
     */
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            WorldWrapComponent.class
    ).get();


    /*
     * CONSTRUCTORS
     */
    public WorldWrapSystem() {
        super(FAMILY);
    }

    /*
     * PROCESS
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);


        // check x bounds (left/right))
        if (position.x >= GameConfig.WORLD_WIDTH) {
            position.x = 0;
        } else if (position.x < 0) {
            position.x = GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SPEED;
        }

        // check y bounds (up/down)
        if (position.y >= GameConfig.MAX_Y) {
            position.y = 0;
        } else if (position.y < 0) {
            position.y = GameConfig.MAX_Y - GameConfig.SNAKE_SPEED;
        }
    }

}
