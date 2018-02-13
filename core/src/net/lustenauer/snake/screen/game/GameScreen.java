package net.lustenauer.snake.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.snake.SimpleSnakeGame;
import net.lustenauer.snake.common.GameManager;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.screen.menu.MenuScreen;
import net.lustenauer.snake.system.GridRenderSystem;
import net.lustenauer.snake.util.GdxUtils;

/**
 * Created by Patric Hollenstein on 12.02.18.
 *
 * @author Patric Hollenstein
 */
public class GameScreen extends ScreenAdapter{
    /*
     * ATTRIBUTES
     */

    private final SimpleSnakeGame game;
    private final AssetManager assetManager;

    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;

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
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();

        engine.addSystem(new GridRenderSystem(viewport,renderer));
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
        viewport.update(width,height,true);
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
