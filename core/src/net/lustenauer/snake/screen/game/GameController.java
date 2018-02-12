package net.lustenauer.snake.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Logger;
import net.lustenauer.snake.collision.CollisionListener;
import net.lustenauer.snake.common.Direction;
import net.lustenauer.snake.common.GameManager;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.entity.*;

/**
 * Created by Patric Hollenstein on 03.02.18.
 *
 * @author Patric Hollenstein
 */
@Deprecated
public class GameController {

    /*
     * constants
     */
    private static final Logger log = new Logger(GameController.class.getName(), Logger.DEBUG);

    /*
     * ATTRIBUTES
     */
    private final CollisionListener listener;

    private Snake snake;
    private float timer;

    private Coin coin;

    /*
     * CONSTRUCTORS
     */

    public GameController(CollisionListener listener) {
        this.listener = listener;
        snake = new Snake();
        coin = new Coin();

        restart();
    }

    /*
     * PUBLIC METHODES
     */
    public void update(float delta) {
        GameManager.INSTANCE.updateDisplayScore(delta);

        if (GameManager.INSTANCE.isPlaying()) {
            queryInput();
            queryDebugInput();

            timer += delta;
            if (timer >= GameConfig.MOVE_TIME) {
                timer = 0;
                snake.move();

                checkOutOfBounds();
                checkCollision();
            }

            spawnCoin();
        } else {
            checkForRestart();
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Coin getCoin() {
        return coin;
    }

    /*
     * PRIVATE METHODES
     */
    private void queryInput() {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (leftPressed) {
            snake.setDirection(net.lustenauer.snake.common.Direction.LEFT);
        } else if (rightPressed) {
            snake.setDirection(net.lustenauer.snake.common.Direction.RIGHT);
        } else if (upPressed) {
            snake.setDirection(net.lustenauer.snake.common.Direction.UP);
        } else if (downPressed) {
            getSnake().setDirection(Direction.DOWN);
        }
    }

    private void queryDebugInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.PLUS)) {
            snake.insertBodyPart();
        }
    }

    private void checkOutOfBounds() {
        SnakeHead head = snake.getHead();

        // check x bounds (left, right)
        if (head.getX() >= GameConfig.WORLD_WIDTH) {
            head.setX(0);
        } else if (head.getX() < 0) {
            head.setX(GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SPEED);
        }

        // check y bounds ()up, down)
        if (head.getY() >= GameConfig.MAX_Y) {
            head.setY(0);
        } else if (head.getY() < 0) {
            head.setY(GameConfig.MAX_Y - GameConfig.SNAKE_SPEED);
        }
    }

    private void spawnCoin() {
        if (!coin.isAvailable()) {
            float coinX = (int) MathUtils.random(GameConfig.WORLD_WIDTH - GameConfig.COIN_SIZE);
            float coinY = (int) MathUtils.random(GameConfig.MAX_Y - GameConfig.COIN_SIZE);
            coin.setAvailable(true);
            coin.setPosition(coinX, coinY);
        }
    }

    private void checkCollision() {
        // head <--> coin collision
        SnakeHead head = snake.getHead();
        Rectangle headBounds = head.getBounds();
        Rectangle coinBounds = coin.getBounds();

        boolean overlaps = Intersector.overlaps(headBounds, coinBounds);

        if (coin.isAvailable() && overlaps) {
            listener.hitCoin();
            snake.insertBodyPart();
            coin.setAvailable(false);
            GameManager.INSTANCE.incrementScore(GameConfig.COIN_SCORE);
        }

        // head <--> body parts collision
        for (BodyPart bodyPart : snake.getBodyParts()) {
            if (bodyPart.isJustAdded()) {
                bodyPart.setJustAdded(false);
                continue;
            }

            Rectangle bodyPartBounds = bodyPart.getBounds();
            if (Intersector.overlaps(headBounds, bodyPartBounds)) {
                listener.lose();
                GameManager.INSTANCE.updateHighScore();
                GameManager.INSTANCE.setGameOver();
            }

        }

    }

    private void checkForRestart() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            restart();
        }
    }

    private void restart() {
        GameManager.INSTANCE.reset();
        snake.reset();
        coin.setAvailable(false);
        timer = 0;
    }


}
