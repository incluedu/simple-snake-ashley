package net.lustenauer.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import net.lustenauer.snake.component.MovementComponent;
import net.lustenauer.snake.component.PositionComponent;
import net.lustenauer.snake.component.SnakeComponent;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.util.Mappers;

/**
 * Created by Patric Hollenstein on 16.02.18.
 *
 * @author Patric Hollenstein
 */
public class SnakeMovementSystem extends IntervalIteratingSystem {

    /*
     * CONSTANTS
     */
    private static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    /*
     * ATTRIBUTES
     */
    private float beforeUpdateHeadX;
    private float beforeUpdateHeadY;

    /*
     * CONSTRUCTORS
     */
    public SnakeMovementSystem() {
        super(FAMILY, GameConfig.MOVE_TIME);

    }

    /*
     * PROCESS
     */
    @Override
    protected void processEntity(Entity entity) {
        SnakeComponent snake = Mappers.SNAKE.get(entity);

        moveHead(snake.head);
        moveBodyParts(snake);
    }


    /*
     * PRIVATE METHODES
     */
    private void moveHead(Entity head) {
        MovementComponent movement = Mappers.MOVEMENT.get(head);
        PositionComponent position = Mappers.POSITION.get(head);

        beforeUpdateHeadX = position.x;
        beforeUpdateHeadY = position.y;

        position.x += movement.xSpeed;
        position.y += movement.ySpeed;


    }

    private void moveBodyParts(SnakeComponent snake) {
        if (snake.hasBodyParts()){
            Entity tail = snake.bodyParts.removeIndex(0);
            PositionComponent position = Mappers.POSITION.get(tail);
            position.x = beforeUpdateHeadX;
            position.y = beforeUpdateHeadY;
            snake.bodyParts.add(tail);
        }
    }


}
