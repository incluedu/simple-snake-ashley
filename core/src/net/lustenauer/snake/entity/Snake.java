package net.lustenauer.snake.entity;

import com.badlogic.gdx.utils.Array;
import net.lustenauer.snake.config.GameConfig;


/**
 * Created by Patric Hollenstein on 06.02.18.
 *
 * @author Patric Hollenstein
 */
public class Snake {

    /*
     * ATTRIBUTES
     */
    private final Array<BodyPart> bodyParts = new Array<BodyPart>();
    private SnakeHead head;
    private Direction direction = Direction.RIGHT;

    private float xBeforeUpdate;
    private float yBefoerUpdate;

    /*
     * CONSTRUCTORS
     */

    public Snake() {
        head = new SnakeHead();
    }

    /*
     * PUBLIC METHODES
     */
    public void move() {
        xBeforeUpdate = head.getX();
        yBefoerUpdate = head.getY();

        if (direction.isRight()) {
            head.updateX(GameConfig.SNAKE_SPEED);
        } else if (direction.isLeft()) {
            head.updateX(-GameConfig.SNAKE_SPEED);
        } else if (direction.isUp()) {
            head.updateY(GameConfig.SNAKE_SPEED);
        } else if (direction.isDown()) {
            head.updateY(-GameConfig.SNAKE_SPEED);
        }
        updateBodyParts();
    }

    public void setDirection(Direction newDirection) {
        if (!direction.isOposit(newDirection) || bodyParts.size == 0) {
            direction = newDirection;
        }
    }

    public SnakeHead getHead() {
        return head;
    }

    public Array<BodyPart> getBodyParts() {
        return bodyParts;
    }

    public void insertBodyPart() {
        BodyPart bodyPart = new BodyPart();
        bodyPart.setPosition(head.getX(), head.getY());
        bodyParts.insert(0, bodyPart);
    }

    public void reset() {
        bodyParts.clear();
        direction = Direction.RIGHT;
        head.setPosition(0, 0);
    }

    /*
     * PRIVATE METHODES
     */
    private void updateBodyParts() {
        if (bodyParts.size > 0) {
            BodyPart tail = bodyParts.removeIndex(0);
            tail.setPosition(xBeforeUpdate, yBefoerUpdate);
            bodyParts.add(tail);
        }
    }

}
