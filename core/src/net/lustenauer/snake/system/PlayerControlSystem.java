package net.lustenauer.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.lustenauer.snake.common.Direction;
import net.lustenauer.snake.component.DirectionComponent;
import net.lustenauer.snake.component.PlayerComponent;
import net.lustenauer.snake.util.Mappers;

/**
 * Created by Patric Hollenstein on 16.02.18.
 *
 * @author Patric Hollenstein
 */
public class PlayerControlSystem extends IteratingSystem {
    /*
     * CONSTANTS
     */
    public static final Family FAMILY = Family.all(
            PlayerComponent.class,
            DirectionComponent.class
    ).get();

    /*
     * CONSTRUCTORS
     */
    public PlayerControlSystem() {
        super(FAMILY);
    }

    /*
     * PROCESS
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        DirectionComponent directionComponent = Mappers.DIRECTION.get(entity);

        if (leftPressed) {
            updateDirection(directionComponent, Direction.LEFT);
        } else if (rightPressed) {
            updateDirection(directionComponent, Direction.RIGHT);
        } else if (upPressed) {
            updateDirection(directionComponent, Direction.UP);
        } else if (downPressed) {
            updateDirection(directionComponent, Direction.DOWN);
        }
    }

    /*
     * PRIVATE METHODES
     */
    private void updateDirection(DirectionComponent directionComponent, Direction direction) {

        if(!directionComponent.isOpposite(direction)){
            directionComponent.direction = direction;
        }
    }
}
