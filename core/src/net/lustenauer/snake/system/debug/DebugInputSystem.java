package net.lustenauer.snake.system.debug;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Patric Hollenstein on 25.02.18.
 *
 * @author Patric Hollenstein
 */
public class DebugInputSystem extends EntitySystem {
    /*
     * ATTRIBUTES
     */
    private boolean debugGrid = false;
    private boolean debugRender = false;

    private EntitySystem gridRenderSystem;
    private EntitySystem debugRenderSystem;

    /*
     * CONSTRUCTORS
     */
    public DebugInputSystem() {
    }

    /*
     * PUBLIC METHODES
     */

    @Override
    public void addedToEngine(Engine engine) {
        gridRenderSystem = engine.getSystem(GridRenderSystem.class);
        debugRenderSystem = engine.getSystem(DebugRenderSystem.class);
        toggleSystems();
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            debugGrid = !debugGrid;
            toggleSystems();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
            debugRender = !debugRender;
            toggleSystems();
        }

    }

    /*
     * PRIVATE METHODES
     */
    private void toggleSystems() {
            gridRenderSystem.setProcessing(debugGrid);
            debugRenderSystem.setProcessing(debugRender);
    }
}
