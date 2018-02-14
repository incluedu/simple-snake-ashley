package net.lustenauer.snake.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.snake.SimpleSnakeGame;
import net.lustenauer.snake.common.EntityFactory;
import net.lustenauer.snake.common.GameManager;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.screen.menu.MenuScreen;
import net.lustenauer.snake.system.debug.DebugCameraSystem;
import net.lustenauer.snake.system.debug.GridRenderSystem;
import net.lustenauer.snake.util.GdxUtils;

/**
 * Created by Patric Hollenstein on 12.02.18.
 *
 * @author Patric Hollenstein
 */
public class GameScreen extends ScreenAdapter {
    /*
     * CONSTANTS
     */
    private static final Logger log = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    /*
     * ATTRIBUTES
     */

    private final SimpleSnakeGame game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private EntityFactory factory;

    /*
     * CONSTRUCTORS
     */
    public GameScreen(SimpleSnakeGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    /*
     * PUBLIC METHODES
     */

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        factory = new EntityFactory(engine);

        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugCameraSystem(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y, camera));

        log.debug("entity count before adding head= " + engine.getEntities().size());
        factory.createSnakeHead();
        log.debug("entity count after adding head= " + engine.getEntities().size());
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        engine.update(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        engine.removeAllEntities();
    }
}
