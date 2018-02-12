package net.lustenauer.snake.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import net.lustenauer.snake.SimpleSnakeGame;
import net.lustenauer.snake.assets.AssetDescriptors;
import net.lustenauer.snake.collision.CollisionListener;
import net.lustenauer.snake.common.GameManager;
import net.lustenauer.snake.screen.menu.MenuScreen;

/**
 * Created by Patric Hollenstein on 03.02.18.
 *
 * @author Patric Hollenstein
 */
public class GameScreen extends ScreenAdapter {

    /*
     * ATTRIBUTES
     */
    private final SimpleSnakeGame game;
    private final AssetManager assetManager;
    private final CollisionListener listener;

    private GameRenderer renderer;
    private GameController controller;

    private Sound coinSound;
    private Sound loseSound;


    /*
     * CONSTRUCTORS
     */
    public GameScreen(SimpleSnakeGame game) {
        this.game = game;
        this.assetManager = game.getAssetManager();

        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);

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
        controller = new GameController(listener);
        renderer = new GameRenderer(game.getBatch(), assetManager, controller);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
