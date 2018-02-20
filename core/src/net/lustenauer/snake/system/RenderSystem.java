package net.lustenauer.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.snake.component.DimensionComponent;
import net.lustenauer.snake.component.PositionComponent;
import net.lustenauer.snake.component.TextureComponent;
import net.lustenauer.snake.component.ZOrderComponent;
import net.lustenauer.snake.util.Mappers;
import net.lustenauer.snake.util.ZOrderComparator;

/**
 * Created by Patric Hollenstein on 20.02.18.
 *
 * @author Patric Hollenstein
 */
public class RenderSystem extends IteratingSystem {
    /*
     * CONSTANTS
     */
    private static final Family FAMILY = Family.all(
            TextureComponent.class,
            PositionComponent.class,
            DimensionComponent.class,
            ZOrderComponent.class
    ).get();

    /*
     * ATTRIBUTES
     */
    private final SpriteBatch batch;
    private final Viewport viewport;
    private final Array<Entity> renderQueue = new Array<Entity>();

    /*
     * CONSTRUCTORS
     */

    public RenderSystem(SpriteBatch batch, Viewport viewport) {
        super(FAMILY);
        this.batch = batch;
        this.viewport = viewport;
    }

    /*
     * PROCESS
     */

    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);

        renderQueue.sort(ZOrderComparator.INSTANCE);
        draw();
        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    /*
     * PRIVATE METHODS
     */
    private void draw() {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        for (Entity entity : renderQueue) {


            PositionComponent position = Mappers.POSITION.get(entity);
            DimensionComponent dimension = Mappers.DIMENSION.get(entity);
            TextureComponent texture = Mappers.TEXTURE.get(entity);

            batch.draw(texture.region,
                    position.x, position.y,
                    dimension.width, dimension.height
            );
        }

        batch.end();
    }
}
