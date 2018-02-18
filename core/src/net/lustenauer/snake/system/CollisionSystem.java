package net.lustenauer.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import net.lustenauer.snake.component.BoundsComponent;
import net.lustenauer.snake.component.CoinComponent;
import net.lustenauer.snake.component.SnakeComponent;
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
    private static final Family SNAKE_FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    private static final Family COIN_FAMILY = Family.all(
            CoinComponent.class
    ).get();

    /*
     * CONTROLLERS
     */
    public CollisionSystem() {
        super(GameConfig.MOVE_TIME);
    }

    /*
     * UPDATE
     */
    @Override
    protected void updateInterval() {
        Engine engine = getEngine();
        ImmutableArray<Entity> snakes = engine.getEntitiesFor(SNAKE_FAMILY);
        ImmutableArray<Entity> coins = engine.getEntitiesFor(COIN_FAMILY);


        // head <--> coin
        for (Entity snakeEntity : snakes) {
            for (Entity coinEntity : coins) {
                CoinComponent coin = Mappers.COIN.get(coinEntity);
                SnakeComponent snake = Mappers.SNAKE.get(snakeEntity);

                if (coin.available && overlaps(snake.head, coinEntity)) {
                    coin.available = false;
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
