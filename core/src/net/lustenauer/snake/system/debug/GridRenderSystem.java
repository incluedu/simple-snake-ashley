package net.lustenauer.snake.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.snake.util.ViewportUtils;

/**
 * Created by Patric Hollenstein on 13.02.18.
 *
 * @author Patric Hollenstein
 */
public class GridRenderSystem extends EntitySystem{

    /*
     * ATTRIBUTES
     */
    private final Viewport viewport;
    private final ShapeRenderer renderer;

    /*
     * CONSTRUCTORS
     */
    public GridRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        this.viewport = viewport;
        this.renderer = renderer;
    }

    /*
     * UPDATE
     */
    @Override
    public void update(float deltaTime) {
        viewport.apply();
        ViewportUtils.drawGrid(viewport,renderer);
    }
}
