package net.lustenauer.snake.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.snake.component.BoundsComponent;
import net.lustenauer.snake.component.MovementComponent;
import net.lustenauer.snake.util.Mappers;

/**
 * Created by Patric Hollenstein on 14.02.18.
 *
 * @author Patric Hollenstein
 */
public class DebugRenderSystem extends IteratingSystem {

    /*
     * CONSTANTS
     */
    private static final Family FAMILY = Family.all(
            BoundsComponent.class
    ).get();

    /*
     * ATTRIBUTES
     */
    private final Viewport viewport;
    private final ShapeRenderer renderer;


    /*
     * CONSTRUCTORS
     */
    public DebugRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }

    /*
     * UPDATE
     */

    @Override
    public void update(float deltaTime) {
        Color oldColor = renderer.getColor();

        viewport.apply();
        renderer.setColor(Color.RED);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        // calls processEntity internal in for loop
        super.update(deltaTime);

        renderer.end();
        renderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);
        renderer.rect(bounds.rectangle.x, bounds.rectangle.y, bounds.rectangle.width, bounds.rectangle.height);
    }
}
