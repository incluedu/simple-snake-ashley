package net.lustenauer.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.lustenauer.snake.component.DirectionComponent;
import net.lustenauer.snake.component.MovementComponent;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.util.Mappers;

/**
 * Created by Patric Hollenstein on 15.02.18.
 *
 * @author Patric Hollenstein
 */
public class DirectionSystem extends IteratingSystem {
    /*
     * CONSTANTS
     */
    private static final Family FAMILY = Family.all(
            DirectionComponent.class,
            MovementComponent.class
    ).get();

    /*
     * CONSTRUCTORS
     */
    public DirectionSystem() {
        super(FAMILY);
    }

    /*
     * PROCESS
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent direction = Mappers.DIRECTION.get(entity);
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        // reset speed to 0
        movement.xSpeed = 0f;
        movement.ySpeed = 0f;

        // based on direction set speed
        if (direction.isRight()) {
            movement.xSpeed = GameConfig.SNAKE_SPEED;
        } else if (direction.isLeft()) {
            movement.xSpeed = -GameConfig.SNAKE_SPEED;
        } else if (direction.isUp()) {
            movement.ySpeed = GameConfig.SNAKE_SPEED;
        } else if (direction.isDown()) {
            movement.ySpeed = -GameConfig.SNAKE_SPEED;
        }
    }
}
