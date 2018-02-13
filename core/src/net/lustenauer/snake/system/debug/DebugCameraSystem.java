package net.lustenauer.snake.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import net.lustenauer.snake.util.debug.DebugCameraController;

/**
 * Created by Patric Hollenstein on 13.02.18.
 *
 * @author Patric Hollenstein
 */
public class DebugCameraSystem extends EntitySystem {

    /*
     * CONSTANTS
     */
    private static final DebugCameraController DEBUG_CAMERA_CONTROLLER = new DebugCameraController();

    /*
     * ATTRIBUTES
     */

    private final OrthographicCamera camera;

    /*
     * CONSTRUCTORS
     */

    public DebugCameraSystem(float startX, float startY, OrthographicCamera camera) {
        this.camera = camera;
        DEBUG_CAMERA_CONTROLLER.setStartPosition(startX, startY);
    }

    /*
     * UPDATE
     */

    @Override
    public void update(float deltaTime) {
        DEBUG_CAMERA_CONTROLLER.handleDebugInput(deltaTime);
        DEBUG_CAMERA_CONTROLLER.applyTo(camera);
    }
}
