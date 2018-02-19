package net.lustenauer.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Logger;
import net.lustenauer.snake.common.EntityFactory;
import net.lustenauer.snake.common.GameManager;
import net.lustenauer.snake.component.*;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.util.Mappers;

/**
 * Created by Patric Hollenstein on 18.02.18.
 *
 * @author Patric Hollenstein
 */
public class CollisionSystem extends IntervalSystem {
    /*
     * CONSTANTS
     */
    private static final Family SNAKE_FAMILY = Family.all(SnakeComponent.class).get();
    private static final Family COIN_FAMILY = Family.all(CoinComponent.class).get();

    /*
     * ATTRIBUTES
     */
    private final EntityFactory factory;

    /*
     * CONTROLLERS
     */
    public CollisionSystem(EntityFactory factory) {
        super(GameConfig.MOVE_TIME);
        this.factory = factory;
    }

    /*
     * UPDATE
     */
    @Override
    protected void updateInterval() {
        Engine engine = getEngine();
        ImmutableArray<Entity> snakes = engine.getEntitiesFor(SNAKE_FAMILY);
        ImmutableArray<Entity> coins = engine.getEntitiesFor(COIN_FAMILY);

        // head <--> body parts

        for (Entity snakeEntity : snakes) {
            SnakeComponent snake = Mappers.SNAKE.get(snakeEntity);

            for (Entity bodyPartEntity : snake.bodyParts) {
                BodyPartComponent bodyPart = Mappers.BODY_PART.get(bodyPartEntity);

                if (bodyPart.justAdded) {
                    bodyPart.justAdded = false;
                    continue;
                }

                if (overlaps(snake.head, bodyPartEntity)) {
                    GameManager.INSTANCE.setGameOver();
                }

            }
        }

        // head <--> coin
        for (Entity snakeEntity : snakes) {
            for (Entity coinEntity : coins) {
                CoinComponent coin = Mappers.COIN.get(coinEntity);
                SnakeComponent snake = Mappers.SNAKE.get(snakeEntity);

                if (coin.available && overlaps(snake.head, coinEntity)) {
                    // mark coin as not available
                    coin.available = false;

                    // get head position and add body part
                    PositionComponent position = Mappers.POSITION.get(snake.head);
                    Entity bodyPart = factory.createBodyPart(position.x, position.y);
                    snake.bodyParts.insert(0, bodyPart);
                }
            }
        }

    }

    /*
     * PRIVATE METHODES
     */
    private boolean overlaps(Entity firstEntity, Entity secondEntity) {
        BoundsComponent firstBounds = Mappers.BOUNDS.get(firstEntity);
        BoundsComponent secondBounds = Mappers.BOUNDS.get(secondEntity);

        return Intersector.overlaps(firstBounds.rectangle, secondBounds.rectangle);
    }
}
