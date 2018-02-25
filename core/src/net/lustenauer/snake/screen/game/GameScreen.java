package net.lustenauer.snake.screen.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.snake.SimpleSnakeGame;
import net.lustenauer.snake.assets.AssetDescriptors;
import net.lustenauer.snake.collision.CollisionListener;
import net.lustenauer.snake.common.EntityFactory;
import net.lustenauer.snake.common.GameManager;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.screen.menu.MenuScreen;
import net.lustenauer.snake.system.*;
import net.lustenauer.snake.system.debug.DebugCameraSystem;
import net.lustenauer.snake.system.debug.DebugInputSystem;
import net.lustenauer.snake.system.debug.DebugRenderSystem;
import net.lustenauer.snake.system.debug.GridRenderSystem;
import net.lustenauer.snake.system.passive.SnakeSystem;
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
    private final SpriteBatch batch;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private EntityFactory factory;
    private BitmapFont font;
    private Sound coinSound;
    private Sound loseSound;
    private CollisionListener listener;

    /*
     * CONSTRUCTORS
     */
    public GameScreen(SimpleSnakeGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
        batch = game.getBatch();

        listener = new CollisionListener() {
            @Override
            public void hitCoin() {
                coinSound.play();
            }

            @Override
            public void lose() {
                loseSound.play();
            }
        };
    }

    /*
     * PUBLIC METHODES
     */

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HIGHT);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        factory = new EntityFactory(engine, assetManager);
        font = assetManager.get(AssetDescriptors.UI_FONT);
        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);

        engine.addSystem(new DebugCameraSystem(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y, camera));
        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugRenderSystem(viewport, renderer));
        engine.addSystem(new DebugInputSystem());
        engine.addSystem(new SnakeSystem());
        engine.addSystem(new DirectionSystem());
        engine.addSystem(new SnakeMovementSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CoinSystem());
        engine.addSystem(new CollisionSystem(factory, listener));
        engine.addSystem(new RenderSystem(batch, viewport));
        engine.addSystem(new HudRenderSystem(batch, hudViewport, font));

        factory.createBackground();
        factory.createSnake();
        factory.createCoin();

        GameManager.INSTANCE.reset();
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
        hudViewport.update(width, height, true);
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
