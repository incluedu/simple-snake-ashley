package net.lustenauer.snake.common;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import net.lustenauer.snake.component.*;
import net.lustenauer.snake.config.GameConfig;

/**
 * Created by Patric Hollenstein on 14.02.18.
 *
 * @author Patric Hollenstein
 */
public class EntityFactory {

    /*
     * ATTRIBUTES
     */
    private final PooledEngine engine;

    /*
     * CONSTRUCTORS
     */
    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    /*
     * PUBLIC METHODES
     */
    public Entity createSnake(){

        // snake
        SnakeComponent snake = engine.createComponent(SnakeComponent.class);
        snake.head=createSnakeHead();

        // entity
        Entity entity = engine.createEntity();
        entity.add(snake);

        // add to engine
        engine.addEntity(entity);

        return entity;
    }


    public Entity createSnakeHead() {
        // position
        PositionComponent position = engine.createComponent(PositionComponent.class);

        // dimension
        DimensionComponent dimesion = engine.createComponent(DimensionComponent.class);
        dimesion.width = GameConfig.SNAKE_SIZE;
        dimesion.height = GameConfig.SNAKE_SIZE;

        // bounds
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimesion.width, dimesion.height);

        // direction
        DirectionComponent direction = engine.createComponent(DirectionComponent.class);

        // movement
        MovementComponent movement = engine.createComponent(MovementComponent.class);

        // player
        PlayerComponent player = engine.createComponent(PlayerComponent.class);

        // entity
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimesion);
        entity.add(bounds);
        entity.add(direction);
        entity.add(movement);
        entity.add(player);

        // add to engine
        engine.addEntity(entity);

        return entity;
    }

}
