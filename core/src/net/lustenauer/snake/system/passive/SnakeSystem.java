package net.lustenauer.snake.system.passive;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.utils.Logger;
import net.lustenauer.snake.component.SnakeComponent;
import net.lustenauer.snake.util.Mappers;

/**
 * Created by Patric Hollenstein on 14.02.18.
 *
 * @author Patric Hollenstein
 */
public class SnakeSystem extends EntitySystem implements EntityListener {
    /*
     * CONSTANTS
     */
    private static final Logger log = new Logger(SnakeSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();


    /*
     * public methodes
     */

    @Override
    public boolean checkProcessing() {
        // returning false "makes" this System "passive" e.g. update method will not called
        return false;
    }

    @Override
    public void update(float deltaTime) {
        // NOT PROCESSING ANYTHING
    }

    @Override
    public void addedToEngine(Engine engine) {
        log.debug("Snake system addedToEngine adding listener");
        engine.addEntityListener(FAMILY, this);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        log.debug("Snake system removedFromEngine");
        engine.removeEntityListener(this);
    }

    @Override
    public void entityAdded(Entity entity) {
        log.debug("entityAdded entity= " + entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        log.debug("entityRemoved entity:= " + entity);
        SnakeComponent snake = Mappers.SNAKE.get(entity);

        Engine engine = getEngine();
        engine.removeEntity(snake.head);

        for(Entity bodyPart: snake.bodyParts){
            engine.removeEntity(bodyPart);
        }
    }
}
