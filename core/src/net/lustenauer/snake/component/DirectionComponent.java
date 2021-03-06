package net.lustenauer.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import net.lustenauer.snake.common.Direction;

/**
 * Created by Patric Hollenstein on 15.02.18.
 *
 * @author Patric Hollenstein
 */
public class DirectionComponent implements Component, Pool.Poolable {

    /*
     * ATTRIBUTES
     */
    public Direction direction = Direction.RIGHT;

    /*
     * PUBLIC METHODES
     */

    public boolean isLeft() {
        return direction.isLeft();
    }

    public boolean isRight() {
        return direction.isRight();
    }

    public boolean isUp() {
        return direction.isUp();
    }

    public boolean isDown() {
        return direction.isDown();
    }

    @Override
    public void reset() {
        direction = Direction.RIGHT;
    }

    public boolean isOpposite(Direction otherDirection) {
        return otherDirection.isOposit(otherDirection);
    }
}
