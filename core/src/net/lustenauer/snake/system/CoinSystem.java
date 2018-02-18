package net.lustenauer.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import net.lustenauer.snake.component.CoinComponent;
import net.lustenauer.snake.component.PositionComponent;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.util.Mappers;

/**
 * Created by Patric Hollenstein on 18.02.18.
 *
 * @author Patric Hollenstein
 */
public class CoinSystem extends IteratingSystem {
    /*
     * CONSTANTS
     */
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            CoinComponent.class
    ).get();


    /*
     * CONSTRUCTOR
     */
    public CoinSystem() {
        super(FAMILY);
    }

    /*
     * PROCESS
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CoinComponent coin = Mappers.COIN.get(entity);
        PositionComponent positon = Mappers.POSITION.get(entity);

        if(!coin.available){
            positon.x = MathUtils.random((int) (GameConfig.WORLD_WIDTH-GameConfig.COIN_SIZE));
            positon.y = MathUtils.random((int) (GameConfig.MAX_Y-GameConfig.COIN_SIZE));
            coin.available=true;
        }
    }
}
